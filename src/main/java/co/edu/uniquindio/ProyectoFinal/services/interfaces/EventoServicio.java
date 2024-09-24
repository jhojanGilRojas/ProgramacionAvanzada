package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.dto.evento.*;

import java.util.List;

public interface EventoServicio {

    String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception;

    String editarEvento(EditarEventoDTO editarEventoDTO) throws Exception;

    String eliminarEvento(String id) throws Exception;

    InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception;

    List<ItemEventoDTO> listarEventos();

    List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroEventoDTO);
}
