package co.edu.uniquindio.ProyectoFinal.controladores;

import co.edu.uniquindio.ProyectoFinal.dto.evento.FiltroEventoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.evento.InformacionEventoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.evento.ItemEventoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.jws.MensajeDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.EventoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente/evento")
public class EventoClienteControlador {

    private final EventoServicio eventoServicio;

    @GetMapping("/obtener-evento/{id}")
    public ResponseEntity<MensajeDTO<InformacionEventoDTO>> obtenerInformacionEvento(@PathVariable String id) throws Exception {
        InformacionEventoDTO informacionEventoDTO = eventoServicio.obtenerInformacionEvento(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, informacionEventoDTO));
    }
    @GetMapping("/obtener-eventos")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> listarEventosCliente() {
        List<ItemEventoDTO> eventos = eventoServicio.listarEventosCliente();
        return ResponseEntity.ok(new MensajeDTO<>(false, eventos));
    }
    @GetMapping("/filtrar-eventos")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventos(@Valid @RequestBody FiltroEventoDTO eventoFiltro) {
        List<ItemEventoDTO> eventos = eventoServicio.filtrarEventos(eventoFiltro);
        return ResponseEntity.ok(new MensajeDTO<>(false, eventos));
    }
}
