package co.edu.uniquindio.ProyectoFinal.services.implement;

import co.edu.uniquindio.ProyectoFinal.dto.evento.*;
import co.edu.uniquindio.ProyectoFinal.model.Localidad;
import co.edu.uniquindio.ProyectoFinal.model.documents.Evento;
import co.edu.uniquindio.ProyectoFinal.repositories.EventoRepo;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.EventoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoServicioImpl implements EventoServicio {

    private final EventoRepo eventoRepo;

    @Override
    public String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception {
        Evento nuevoEvento = new Evento();
        nuevoEvento.setNombre(crearEventoDTO.nombre());
        nuevoEvento.setDescripcion(crearEventoDTO.descripcion());
        nuevoEvento.setFecha(crearEventoDTO.fecha());
        nuevoEvento.setTipo(crearEventoDTO.tipoEvento());
        nuevoEvento.setDireccion(crearEventoDTO.direccion());
        nuevoEvento.setCiudad(crearEventoDTO.ciudad());
        nuevoEvento.setLocalidades(crearLocalidades(crearEventoDTO.localidades()));
        nuevoEvento.setImagenPortada(crearEventoDTO.imagenPortada());
        nuevoEvento.setImagenLocalidades(crearEventoDTO.imagenLocalidades());

        eventoRepo.save(nuevoEvento);  // Guardar el evento en la base de datos
        return nuevoEvento.getIdEvento();  // Retornar el ID del nuevo evento
    }

    private List<Localidad> crearLocalidades(List<LocalidadDTO> localidadesDTO) {
        return localidadesDTO.stream().map(localidadDTO ->
                Localidad.builder()
                        .nombre(localidadDTO.nombre())
                        .precio(localidadDTO.precio())
                        .capacidadMaxima(localidadDTO.capacidadMaxima())
                        .entradasVendidas(0)  // Al crear, las entradas vendidas son 0
                        .build()
        ).collect(Collectors.toList());
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
}

