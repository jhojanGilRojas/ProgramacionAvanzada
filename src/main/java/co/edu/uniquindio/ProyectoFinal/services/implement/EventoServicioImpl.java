package co.edu.uniquindio.ProyectoFinal.services.implement;

import co.edu.uniquindio.ProyectoFinal.dto.evento.*;
import co.edu.uniquindio.ProyectoFinal.model.Localidad;
import co.edu.uniquindio.ProyectoFinal.model.documents.Evento;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoEvento;
import co.edu.uniquindio.ProyectoFinal.repositories.EventoRepo;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.EventoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        // validar que no haya un evento en la misma fecha y lugar
        if(existeEvento(nuevoEvento.getFecha(), nuevoEvento.getDireccion(), nuevoEvento.getCiudad())){
            throw new Exception("Ya hay un evento programado en ese lugar el mismo dia");
        }
        eventoRepo.save(nuevoEvento);  // Guardar el evento en la base de datos
        return nuevoEvento.getIdEvento();  // Retornar el ID del nuevo evento
    }

    private boolean existeEvento(LocalDateTime fecha, String direccion, String ciudad) {
        LocalDate soloFecha = fecha.toLocalDate();  // Extraer solo la fecha
        return eventoRepo.findByFechaAndCiudadAndDireccion(soloFecha, ciudad, direccion).isPresent();
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
    public void editarEvento(EditarEventoDTO eventoEditado) throws Exception {
        if(existeEvento(eventoEditado.fecha(), eventoEditado.direccion(), eventoEditado.ciudad())){
            throw new Exception("Ya hay un evento programado en ese lugar en la misma fecha");
        }
        Evento eventoModificado = buscarEventoPorId(eventoEditado.id());
        eventoModificado.setNombre(eventoEditado.nombre());
        eventoModificado.setDescripcion(eventoEditado.descripcion());
        eventoModificado.setFecha(eventoEditado.fecha());
        eventoModificado.setTipo(eventoEditado.tipoEvento());
        eventoModificado.setDireccion(eventoEditado.direccion());
        eventoModificado.setCiudad(eventoEditado.ciudad());
        eventoModificado.setLocalidades(crearLocalidades(eventoEditado.localidades()));
        eventoModificado.setImagenPortada(eventoEditado.imagenPortada());
        eventoModificado.setImagenLocalidades(eventoEditado.imagenLocalidades());
        eventoRepo.save(eventoModificado);
    }

    @Override
    public InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception {
        Evento evento = buscarEventoPorId(id);
        if(evento.getEstado().equals(EstadoEvento.INACTIVA)){
            throw new Exception("El evento no se encuentra activo");
        }
        return new InformacionEventoDTO(
                evento.getIdEvento(),
                evento.getNombre(),
                evento.getDescripcion(),
                evento.getFecha(),
                evento.getTipo(),
                evento.getDireccion(),
                evento.getCiudad(),
                evento.getLocalidades().stream().map(localidad ->
                        new LocalidadDTO(
                                localidad.getNombre(),
                                localidad.getCapacidadMaxima(),
                                localidad.getPrecio()

                        )
                ).collect(Collectors.toList()),
                evento.getImagenPortada(),
                evento.getImagenLocalidades()
        );
    }

    @Override
    public void eliminarEvento(String id) throws Exception {
        Optional<Evento> eventoOptional = eventoRepo.findById(id);

        if (eventoOptional.isEmpty()) {
            throw new Exception("No se encontró el evento con id: " + id);
        }
        Evento evento = eventoOptional.get();
        evento.setEstado(EstadoEvento.INACTIVA);
        eventoRepo.save(evento);
    }


    @Override
    public List<ItemEventoDTO> listarEventos() {
        List<Evento> eventos = eventoRepo.findAll();
        List<ItemEventoDTO> itemsEvento = new ArrayList<>();
        for (Evento evento : eventos) {
            itemsEvento.add(new ItemEventoDTO(evento.getNombre(), evento.getFecha(), evento.getDireccion(), evento.getCiudad(), evento.getImagenPortada()));
        }
        return itemsEvento;
    }
    private Evento buscarEventoPorId(String id) throws Exception {
        return eventoRepo.findById(id).orElseThrow(() -> new Exception("No se encontró el evento con id: " + id));
    }

    @Override
    public List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO eventoFiltro) {
        List<Evento> eventos;

        // Filtra según los campos presentes en el DTO
        if (eventoFiltro.ciudad() != null && !eventoFiltro.ciudad().isEmpty()) {
            eventos = eventoRepo.findByCiudad(eventoFiltro.ciudad());
        }
        else if (eventoFiltro.tipoEvento() != null) {
            eventos = eventoRepo.findByTipo(eventoFiltro.tipoEvento().name());
        }
        else if (eventoFiltro.nombre() != null && !eventoFiltro.nombre().isEmpty()) {
            eventos = eventoRepo.findByNombreContainingIgnoreCase(eventoFiltro.nombre());
        }
        else if (eventoFiltro.nombre() != null && !eventoFiltro.nombre().isEmpty()&& eventoFiltro.tipoEvento()!=null) {
            eventos = eventoRepo.findByNombreContainingIgnoreCaseAndTipo(eventoFiltro.nombre(), eventoFiltro.tipoEvento().name());
        }
        else if (eventoFiltro.nombre() != null && !eventoFiltro.nombre().isEmpty()&& eventoFiltro.ciudad()!=null && !eventoFiltro.ciudad().isEmpty()) {
            eventos = eventoRepo.findByNombreContainingIgnoreCaseAndCiudad(eventoFiltro.nombre(),eventoFiltro.ciudad());

        } else if (eventoFiltro.tipoEvento()!=null && eventoFiltro.ciudad()!=null&& !eventoFiltro.ciudad().isEmpty()){
            eventos = eventoRepo.findByTipo(eventoFiltro.tipoEvento().name());
            eventos = eventos.stream().filter(evento -> evento.getCiudad().equals(eventoFiltro.ciudad())).collect(Collectors.toList());
        }
        else if (eventoFiltro.ciudad() != null && !eventoFiltro.ciudad().isEmpty()&& eventoFiltro.nombre() != null && !eventoFiltro.nombre().isEmpty()&& eventoFiltro.tipoEvento()!=null) {
            eventos = eventoRepo.findByNombreContainingIgnoreCaseAndTipoAndCiudad(eventoFiltro.nombre(),eventoFiltro.tipoEvento().name(),eventoFiltro.ciudad());

        } else {
            // Si no hay filtro, retorna todos los eventos
            eventos = eventoRepo.findAll();
        }

        // Convierte la lista de eventos a ItemEventoDTO
        return eventos.stream()
                .map(evento -> new ItemEventoDTO(evento.getNombre(), evento.getFecha(), evento.getDireccion(), evento.getCiudad(), evento.getImagenPortada()))
                .collect(Collectors.toList());
    }


}

