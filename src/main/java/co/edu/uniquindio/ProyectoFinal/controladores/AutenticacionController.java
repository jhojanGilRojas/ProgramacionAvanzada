package co.edu.uniquindio.ProyectoFinal.controladores;
import co.edu.uniquindio.ProyectoFinal.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.ProyectoFinal.dto.jws.MensajeDTO;
import co.edu.uniquindio.ProyectoFinal.dto.jws.TokenDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CuentaServicio;
import co.edu.uniquindio.ProyectoFinal.dto.cuenta.LoginDTO;
import co.edu.uniquindio.ProyectoFinal.dto.cuenta.ValidarCuentaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AutenticacionController {
    private final CuentaServicio cuentaServicio;

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesion(@Valid @RequestBody LoginDTO loginDTO) throws Exception{
        TokenDTO token = cuentaServicio.iniciarSesion(loginDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, token));
    }

    @PutMapping("/validar-correo{id}")
    public ResponseEntity<MensajeDTO<String>> validarCorreo(@Valid @RequestBody ValidarCuentaDTO validarCuentaDTO ) throws  Exception{

        cuentaServicio.validarCuenta(validarCuentaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Correo validado, se ha activado su cuenta"));
    }

    @PostMapping("/crear-cuenta")
    public ResponseEntity<MensajeDTO<String>> crearCuenta(@Valid @RequestBody CrearCuentaDTO cuenta) throws Exception{
        String codigo = cuentaServicio.crearCuenta(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta creada exitosamente, su codigo es: "+codigo ));
    }

    @PutMapping("/codigo-recuperacion")
    public ResponseEntity<MensajeDTO<String>> enviarCodigoRecuperacionPassword(@RequestBody String email) throws Exception {
        cuentaServicio.enviarCodigoRecuperacionPassword(email);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Código de recuperación enviado exitosamente"));
    }

}
