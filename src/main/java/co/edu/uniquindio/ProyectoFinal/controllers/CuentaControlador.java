package co.edu.uniquindio.ProyectoFinal.controllers;
import co.edu.uniquindio.ProyectoFinal.dto.cuenta.*;
import co.edu.uniquindio.ProyectoFinal.dto.jws.MensajeDTO;
import co.edu.uniquindio.ProyectoFinal.dto.jws.TokenDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CuentaServicio;

import java.util.List;

@RestController
@RequestMapping("/api/cuenta")
@RequiredArgsConstructor
public class CuentaControlador {

    private final CuentaServicio cuentaServicio;

    @PostMapping("/crear-cuenta")
    public ResponseEntity<MensajeDTO<String>> crearCuenta(@Valid @RequestBody CrearCuentaDTO cuenta) throws Exception{
        cuentaServicio.crearCuenta(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta creada exitosamente"));
    }

    @PutMapping("/editar-perfil")
    public ResponseEntity<MensajeDTO<String>> editarCuenta(@Valid @RequestBody EditarCuentaDTO cuenta) throws Exception{
        cuentaServicio.editarCuenta(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta editada exitosamente"));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String id) throws Exception{
        cuentaServicio.eliminarCuenta(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada exitosamente"));
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<MensajeDTO<InformacionCuentaDTO>> obtenerInformacionCuenta(@PathVariable String id) throws Exception{
        InformacionCuentaDTO info = cuentaServicio.obtenerInformacionCuenta(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    @GetMapping("/listar-todo")
    public ResponseEntity<List<ItemCuentaDTO>> listarCuentas() throws Exception {
        List<ItemCuentaDTO> cuentas = cuentaServicio.listarCuentas();
        return ResponseEntity.ok(cuentas);
    }

    @PutMapping("/cambiar-password/{id}")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@RequestBody CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        cuentaServicio.cambiarPassword(cambiarPasswordDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Contraseña cambiada exitosamente"));
    }

}
