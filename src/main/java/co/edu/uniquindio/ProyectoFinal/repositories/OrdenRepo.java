package co.edu.uniquindio.ProyectoFinal.repositories;

import co.edu.uniquindio.ProyectoFinal.model.documents.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrdenRepo extends MongoRepository <Orden,String> {
     Optional<Orden> findByIdClienteAndIdCupon(String idCliente, String IdCupon);

}
