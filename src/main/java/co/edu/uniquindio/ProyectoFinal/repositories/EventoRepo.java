package co.edu.uniquindio.ProyectoFinal.repositories;

import co.edu.uniquindio.ProyectoFinal.model.documents.Carrito;
import co.edu.uniquindio.ProyectoFinal.model.documents.Cuenta;
import co.edu.uniquindio.ProyectoFinal.model.documents.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface EventoRepo extends MongoRepository<Evento, String> {

    @Query(value = "{'_id': ?0}")
    Optional<Evento> buscarEvento(String idEvento);
}
