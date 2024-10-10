package co.edu.uniquindio.ProyectoFinal.repositories;

import co.edu.uniquindio.ProyectoFinal.model.documents.Evento;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoEvento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventoRepo extends MongoRepository<Evento, String> {

    Optional<Evento> findByFechaAndCiudadAndDireccion(LocalDate fecha, String ciudad, String direccion);

    List<Evento> findByCiudad(String ciudad);
    List<Evento> findByTipo(String tipo);
    List<Evento> findByNombreContainingIgnoreCase(String nombre);
    List<Evento> findByNombreContainingIgnoreCaseAndTipoAndCiudad(String nombre, String tipo, String ciudad);
    List<Evento> findByNombreContainingIgnoreCaseAndTipo(String nombre, String tipo);
    List<Evento> findByNombreContainingIgnoreCaseAndCiudad(String nombre, String ciudad);
    List<Evento> findByEstado(EstadoEvento estado);
}