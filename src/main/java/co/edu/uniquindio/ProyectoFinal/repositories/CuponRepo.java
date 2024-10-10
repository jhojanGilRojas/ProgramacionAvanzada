package co.edu.uniquindio.ProyectoFinal.repositories;

import co.edu.uniquindio.ProyectoFinal.model.documents.Cuenta;
import co.edu.uniquindio.ProyectoFinal.model.documents.Cupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CuponRepo extends MongoRepository<Cupon, String>{


    /*@Query(value = "findOne{ 'codigo': ?0 }")
    Optional<Cupon> findByCodigoCupon(String codigoCupon);*/

    @Query("{ codigo:?0 }")
    Optional<Cupon> findByCodigoCupon(String codigoCupon);





}
