package co.edu.uniquindio.ProyectoFinal.repositories;

import co.edu.uniquindio.ProyectoFinal.model.documents.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventoRepo extends MongoRepository<Evento, String> {

    Optional<Evento> findByFechaAndCiudadAndDireccion(LocalDateTime fecha, String ciudad, String direccion);

    List<Evento> findByCiudad(String ciudad);
    List<Evento> findByTipo(String tipo);
    List<Evento> findByNombreContainingIgnoreCase(String nombre);
    List<Evento> findByNombreContainingIgnoreCaseAAndAndTipoAndCiudad(String nombre, String tipo, String ciudad);
    List<Evento> findByNombreContainingIgnoreCaseAndTipo(String nombre, String tipo);
    List<Evento> findByNombreContainingIgnoreCaseAndCiudad(String nombre, String ciudad);
}