package co.edu.uniquindio.ProyectoFinal.services.implement;

import co.edu.uniquindio.ProyectoFinal.dto.evento.*;
import co.edu.uniquindio.ProyectoFinal.model.documents.Evento;
import co.edu.uniquindio.ProyectoFinal.repositories.EventoRepo;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.EventoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoServicioImpl implements EventoServicio {

    private final EventoRepo eventoRepo;
    @Override
    public String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception {
        return "";
    }

    @Override
    public String editarEvento(EditarEventoDTO editarEventoDTO) throws Exception {
        return "";
    }

    @Override
    public String eliminarEvento(String id) throws Exception {
        return "";
    }

    @Override
    public InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception {
        return null;
    }

    @Override
    public List<ItemEventoDTO> listarEventos() {
        return List.of();
    }

    @Override
    public List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroEventoDTO) {
        return List.of();
    }

    @Override
    public Evento obtenerPorID(String id) throws Exception {
        return eventoRepo.findById(id).orElseThrow(()-> new Exception("No se encontro la cuenta con el id: "+id));
    }
}
