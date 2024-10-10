package co.edu.uniquindio.ProyectoFinal.services.implement;

import java.security.SecureRandom;

import co.edu.uniquindio.ProyectoFinal.dto.cuenta.*;
import co.edu.uniquindio.ProyectoFinal.model.CodigoValidacion;
import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import co.edu.uniquindio.ProyectoFinal.model.documents.Cuenta;
import co.edu.uniquindio.ProyectoFinal.model.documents.Evento;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoCuenta;
import co.edu.uniquindio.ProyectoFinal.model.enums.Rol;
import co.edu.uniquindio.ProyectoFinal.repositories.CuentaRepo;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CuentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CuentaServicioImpl implements CuentaServicio {

    private final CuentaRepo cuentaRepo;

    @Override
    public String crearCuenta(CrearCuentaDTO cuenta) throws Exception{


        if (existeEmail(cuenta.correo())){
            throw new Exception("El correo ya existe");
        }
        String codigoAleatorio = generalCodigo();
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setEmail(cuenta.correo());
        nuevaCuenta.setPassword(cuenta.correo());
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

        return "Se ha creado el usuario correctamente";
    }

    private String generalCodigo() {

        String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder codigo = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int indiceAleatorio = random.nextInt(CARACTERES.length());
            codigo.append(CARACTERES.charAt(indiceAleatorio));
        }

        return codigo.toString();
    }

    private boolean existeEmail(String email){
        return cuentaRepo.buscarEmail(email).isPresent();
    }
    private boolean existeCedula(String cedula){
        return false ;
    }
    // hacer lo mismo con la cedula

    @Override
    public String editarCuenta(EditarCuentaDTO cuenta) throws Exception {
        return "";
    }

    @Override
    public String eliminarCuenta(String id) throws Exception {
        return "";
    }

    @Override
    @Transactional(readOnly = true)//Este metodo solo va a utilizar recurso para lectura
    public InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception {
        return null;
    }

    @Override
    public String enviarCodigoRecuperacionPassword(String correo) throws Exception {
        return "";
    }


    @Override
    public String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        return "";
    }

    @Override
    public String iniciarSesion(LoginDTO loginDTO) throws Exception {
        return "";
    }

    @Override
    public Cuenta obtenerPorID(String idCuenta) throws Exception {
        return cuentaRepo.findById(idCuenta).orElseThrow(()-> new Exception("No se encontro el evento con el id: "+idCuenta));
    }
}
