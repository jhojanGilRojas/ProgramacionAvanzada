package co.edu.uniquindio.ProyectoFinal.services.implement;

import java.security.SecureRandom;

import co.edu.uniquindio.ProyectoFinal.config.JWTUtils;
import co.edu.uniquindio.ProyectoFinal.dto.email.EmailDTO;
import co.edu.uniquindio.ProyectoFinal.dto.jws.TokenDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.EmailServicio;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.ProyectoFinal.dto.cuenta.*;
import co.edu.uniquindio.ProyectoFinal.model.CodigoValidacion;
import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import co.edu.uniquindio.ProyectoFinal.model.documents.Cuenta;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoCuenta;
import co.edu.uniquindio.ProyectoFinal.model.enums.Rol;
import co.edu.uniquindio.ProyectoFinal.repositories.CuentaRepo;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CuentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CuentaServicioImpl implements CuentaServicio {

    private final CuentaRepo cuentaRepo;
    private final JWTUtils jwtUtils;
    private final EmailServicio emailServicio;


    @Override
    public String crearCuenta(CrearCuentaDTO cuenta) throws Exception{

        Optional<Cuenta>cuentaOptional = cuentaRepo.buscarEmail(cuenta.correo());
        Optional<Cuenta>cuentaOptional2 = cuentaRepo.findByUsuarioCedula(cuenta.cedula());

        if (cuentaOptional.isPresent()){
            throw new Exception("El correo ya existe");
        }
        if (cuentaOptional2.isPresent()){
            throw new Exception("Ya existe una cuenta asociada a esa cedula");
        }

        String codigoAleatorio = generarCodigo();
        Cuenta nuevaCuenta =new Cuenta();
        nuevaCuenta.setEmail(cuenta.correo());
        nuevaCuenta.setPassword(encriptarPassword(cuenta.password()));
        nuevaCuenta.setRol(Rol.CLIENTE);
        nuevaCuenta.setFechaRegistro(LocalDateTime.now());
        nuevaCuenta.setUsuario(new Usuario(
                cuenta.cedula(),
                cuenta.nombre(),
                cuenta.telefono(),
                cuenta.direccion()
        ));
        nuevaCuenta.setEstadoCuenta(EstadoCuenta.INACTIVO);
        nuevaCuenta.setCodigoValidacionRegistro(
                new CodigoValidacion(codigoAleatorio,LocalDateTime.now())
        );

        cuentaRepo.save(nuevaCuenta);

        emailServicio.enviarCorreo( new EmailDTO("Codigo validación registro", "su codigo es: "+nuevaCuenta.getCodigoValidacionRegistro(), nuevaCuenta.getEmail()) );

        return "Su cuenta se ha creado correctamente";
    }

    private String generarCodigo() {

        String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder codigo = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int indiceAleatorio = random.nextInt(CARACTERES.length());
            codigo.append(CARACTERES.charAt(indiceAleatorio));
        }

        return codigo.toString();
    }


    @Override
    public String editarCuenta(EditarCuentaDTO cuenta) throws Exception {

        Optional<Cuenta> cuentaOptional = cuentaRepo.findById(cuenta.id());
        if (cuentaOptional.isEmpty()) {
            throw new Exception("No existe una cuenta con ese id");
        }
            Cuenta cuentaModificada = cuentaOptional.get();
            cuentaModificada.getUsuario().setNombre(cuenta.nombre());
            cuentaModificada.getUsuario().setDireccion(cuenta.direccion());
            cuentaModificada.getUsuario().setTelefono(cuenta.telefono());
            cuentaModificada.setPassword(cuenta.password());
            cuentaRepo.save(cuentaModificada);

        return cuentaModificada.getIdCuenta();
    }

    @Override
    public String eliminarCuenta(String id) throws Exception {

        Cuenta cuenta = obtenerPorId(id);
        cuenta.setEstadoCuenta(EstadoCuenta.ELIMINADO);
        cuentaRepo.save(cuenta);

        return "Su cuenta se ha eliminado correctamente.";
    }

    @Override
    @Transactional(readOnly = true)//Este metodo solo va a utilizar recurso para lectura
    public InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception {

        Cuenta cuenta = obtenerPorId(id);
        return new InformacionCuentaDTO(
                id,
                cuenta.getUsuario().getCedula(),
                cuenta.getUsuario().getNombre(),
                cuenta.getUsuario().getTelefono(),
                cuenta.getUsuario().getDireccion(),
                cuenta.getEmail()
        );
    }

    private Cuenta obtenerPorId(String id) {

        Optional<Cuenta>cuentaOptional = cuentaRepo.findById(id);
        if (cuentaOptional.isEmpty()){
            return null;
        }
        return cuentaOptional.get();
    }

    @Override
    public String enviarCodigoRecuperacionPassword(String correo) throws Exception {

        Cuenta cuenta = obtenerPorEmail(correo);
        String codigoValidacion = generarCodigo();

        cuenta.setCodigoValidacionPassword(new CodigoValidacion(codigoValidacion,LocalDateTime.now()));

        cuentaRepo.save(cuenta);
        emailServicio.enviarCorreo( new EmailDTO("Codigo validación password", "su codigo es: "+cuenta.getCodigoValidacionRegistro(), cuenta.getEmail()) );

        return "Se ha enviado un correo con el código de validación";
    }


    @Override
    public String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception {

        Cuenta cuenta = obtenerPorEmail(cambiarPasswordDTO.email());

       if (cuenta.getCodigoValidacionPassword().getFechaCreacion().plusMinutes(15).isBefore(LocalDateTime.now())){

           if (cuenta.getCodigoValidacionPassword().getCodigo().equals(cambiarPasswordDTO.codigoVerificacion())){

               cuenta.setPassword(cambiarPasswordDTO.passwordNueva());
               cuentaRepo.save(cuenta);
               return "La contraseña ha sido actualizada correctamente";
           }
           throw new Exception("El código es incorrecto");
       }

       return "El codigo ha expirado";
    }

//    @Override
//    public String iniciarSesion(LoginDTO loginDTO) throws Exception {
//
//        Optional<Cuenta> cuentaOptional = cuentaRepo.buscarEmail(loginDTO.correo());
//
//        if (cuentaOptional.isEmpty()){
//            throw new Exception("El correo dado no está registrado");
//        }
//
//        Cuenta cuenta = cuentaOptional.get();
//
//        if (!cuenta.getPassword().equals(loginDTO.password())){
//            throw new Exception("La contraseña es incorreta");
//        }
//
//        return "";
//    }

    @Override
    public TokenDTO iniciarSesion(LoginDTO loginDTO) throws Exception {

        Cuenta cuenta = obtenerPorEmail(loginDTO.correo());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        if( !passwordEncoder.matches(loginDTO.password(), cuenta.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }


        Map<String, Object> map = construirClaims(cuenta);
        return new TokenDTO( jwtUtils.generarToken(cuenta.getEmail(), map) );
    }

    private Cuenta obtenerPorEmail(String correo) throws Exception {

        Optional<Cuenta> cuentaOptional = cuentaRepo.buscarEmail(correo);

        if (cuentaOptional.isEmpty()){
            throw new Exception("El correo dado no está registrado");
        }

        return cuentaOptional.get();
    }

    private String encriptarPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
    private Map<String, Object> construirClaims(Cuenta cuenta) {
        return Map.of(
                "rol", cuenta.getRol(),
                "nombre", cuenta.getUsuario().getNombre(),
                "id", cuenta.getIdCuenta()
        );
    }

}
