package co.edu.uniquindio.ProyectoFinal.repositories;

import co.edu.uniquindio.ProyectoFinal.model.documents.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventoRepo extends MongoRepository<Evento, String> {
    
}
