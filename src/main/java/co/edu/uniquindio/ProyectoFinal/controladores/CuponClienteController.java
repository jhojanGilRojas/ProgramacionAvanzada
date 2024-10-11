package co.edu.uniquindio.ProyectoFinal.controladores;



import co.edu.uniquindio.ProyectoFinal.dto.cupon.AplicarCuponDTO;
import co.edu.uniquindio.ProyectoFinal.dto.cupon.CrearCuponDTO;
import co.edu.uniquindio.ProyectoFinal.dto.cupon.CuponInfoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.cupon.EditarCuponDTO;
import co.edu.uniquindio.ProyectoFinal.dto.jws.MensajeDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CuponServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente/cupon")
public class CuponClienteController {
    private final CuponServicio cuponServicio;

    @PutMapping ("/validar-cupon")
    public ResponseEntity<MensajeDTO<String>> validarCupon(@Valid @RequestBody AplicarCuponDTO aplicarCuponDTO) throws Exception{

        cuponServicio.validarCupon(aplicarCuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false,"El cupón se ha validado correctamente"));
    }

    @GetMapping ("/obtener-cupon/{id}")
    public ResponseEntity<MensajeDTO<CuponInfoDTO>> obtenerCupon(@PathVariable String id) throws Exception{

        CuponInfoDTO cuponInfoDTO = cuponServicio.buscarCuponPorCodigo(id);
        return ResponseEntity.ok(new MensajeDTO<>(false,cuponInfoDTO));
    }
    @GetMapping ("/obtener-cupones")
    public ResponseEntity<List<CuponInfoDTO>> obtenerCupones() throws Exception{

        List<CuponInfoDTO> cupones = cuponServicio.listarTodosLosCupones();
        return ResponseEntity.ok(cupones);
    }
}
