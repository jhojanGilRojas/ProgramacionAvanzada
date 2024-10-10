package co.edu.uniquindio.ProyectoFinal.repositories;

import co.edu.uniquindio.ProyectoFinal.model.documents.Cupon;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CuponRepo extends MongoRepository <Cupon,String> {

    @Query("{codigo: ?0}")
    Optional<Cupon> findByCodigo(String codigo);
}
