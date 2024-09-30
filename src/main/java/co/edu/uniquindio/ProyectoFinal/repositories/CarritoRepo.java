package co.edu.uniquindio.ProyectoFinal.repositories;

import co.edu.uniquindio.ProyectoFinal.model.documents.Carrito;
import co.edu.uniquindio.ProyectoFinal.model.documents.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepo extends MongoRepository<Carrito, String> {

}
