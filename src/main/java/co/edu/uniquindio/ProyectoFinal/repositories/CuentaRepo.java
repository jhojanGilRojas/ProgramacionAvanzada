package co.edu.uniquindio.ProyectoFinal.repositories;

import co.edu.uniquindio.ProyectoFinal.model.documents.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepo extends MongoRepository <Cuenta, String> {

    @Query("findOne({email: ?0})") // ni especificar la bases de datos ni la colecion es necesario ya el
        // sabe que va para cuentas y ademas por defecto tambien hace el findOne el ?0 es un index del parametro
    Optional<Cuenta> buscarEmail(String email);

    @Query("findOne({usuario.cedula: ?0})")
    Optional<Cuenta> buscarCedula(String cedula);

    @Query("{email: ?0, password: ?1}")
    Optional<Cuenta> validarDatosAutenticacion(String email, String password);
}
