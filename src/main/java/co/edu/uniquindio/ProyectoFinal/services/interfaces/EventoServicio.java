package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.dto.evento.*;

import java.util.List;

public interface EventoServicio {

    String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception;

    void editarEvento(EditarEventoDTO editarEventoDTO) throws Exception;

    void eliminarEvento(String id) throws Exception;

    InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception;

    List<ItemEventoDTO> listarEventos();

    List<ItemEventoDTO> listarEventosCliente();

    List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroEventoDTO);

}
