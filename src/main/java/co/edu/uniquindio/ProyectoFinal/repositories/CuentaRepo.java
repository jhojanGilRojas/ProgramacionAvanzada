package co.edu.uniquindio.ProyectoFinal.repositories;

import co.edu.uniquindio.ProyectoFinal.model.documents.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepo extends MongoRepository <Cuenta, String> {

    @Query("{email: ?0}")
    Optional<Cuenta> buscarEmail(String email);

//    @Query("{usuario.cedula: ?0}")
//    Optional<Cuenta> buscarCedula(String cedula);

    Optional<Cuenta> findByUsuarioCedula(String cedula);


    @Query("{email:  ?0, password:  ?1}")
    Optional<Cuenta> validarDatosAutenticacion(String email,String password);

}
