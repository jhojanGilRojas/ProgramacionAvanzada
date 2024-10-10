package co.edu.uniquindio.ProyectoFinal.controladores;

import co.edu.uniquindio.ProyectoFinal.dto.evento.*;
import co.edu.uniquindio.ProyectoFinal.dto.jws.MensajeDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.EventoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/administrador/evento")
public class EventoAdministradorControlador {

    private final EventoServicio eventoServicio;

    @PostMapping("/crear-evento")
    public ResponseEntity<MensajeDTO<String>> crearEvento(@Valid @RequestBody CrearEventoDTO crearEventoDTO) throws Exception {
            String id = eventoServicio.crearEvento(crearEventoDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Evento creada exitosamente con el id: " + id));
    }
    @PutMapping("/editar-evento")
    public ResponseEntity<MensajeDTO<String>> editarEvento(@Valid @RequestBody EditarEventoDTO eventoEditado) throws Exception {
            eventoServicio.editarEvento(eventoEditado);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Evento editado exitosamente"));
    }
    @DeleteMapping("/eliminar-evento/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarEvento(@PathVariable String id) throws Exception {
        eventoServicio.eliminarEvento(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Evento eliminado exitosamente"));
    }
    @GetMapping("/obtener-evento/{id}")
    public ResponseEntity<MensajeDTO<InformacionEventoDTO>> obtenerInformacionEvento(@PathVariable String id) throws Exception {
        InformacionEventoDTO informacionEventoDTO = eventoServicio.obtenerInformacionEvento(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, informacionEventoDTO));
    }
    @GetMapping("/listar-eventos")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> listarEventos() {
        List<ItemEventoDTO> eventos = eventoServicio.listarEventos();
        return ResponseEntity.ok(new MensajeDTO<>(false, eventos));
    }
    @GetMapping("/filtrar-eventos")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventos(@Valid @RequestBody FiltroEventoDTO eventoFiltro) {
        List<ItemEventoDTO> eventos = eventoServicio.filtrarEventos(eventoFiltro);
        return ResponseEntity.ok(new MensajeDTO<>(false, eventos));
    }

}

