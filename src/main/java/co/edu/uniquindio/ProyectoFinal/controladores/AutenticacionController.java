package co.edu.uniquindio.ProyectoFinal.controladores;
import co.edu.uniquindio.ProyectoFinal.dto.jws.MensajeDTO;
import co.edu.uniquindio.ProyectoFinal.dto.jws.TokenDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CuentaServicio;
import co.edu.uniquindio.ProyectoFinal.dto.cuenta.LoginDTO;
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

    @PutMapping("/codigo-recuperacion")
    public ResponseEntity<MensajeDTO<String>> enviarCodigoRecuperacionPassword(@RequestBody String email) throws Exception {
        cuentaServicio.enviarCodigoRecuperacionPassword(email);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Código de recuperación enviado exitosamente"));
    }

}
