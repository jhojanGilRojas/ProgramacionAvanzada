package co.edu.uniquindio.ProyectoFinal.repositories;

import co.edu.uniquindio.ProyectoFinal.model.documents.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdenRepo extends MongoRepository<Orden, String>{


}
