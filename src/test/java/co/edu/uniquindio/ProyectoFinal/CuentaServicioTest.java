package co.edu.uniquindio.ProyectoFinal;

import co.edu.uniquindio.ProyectoFinal.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CuentaServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CuentaServicioTest {

    @Autowired
    private CuentaServicio cuentaServicio;

    @Test
    public void crearCuentaTest() {
        // Crear un DTO con los datos para crear una nueva cuenta
        CrearCuentaDTO crearCuentaDTO = new CrearCuentaDTO(
                "123",
                "Pepito perez",
                "12121",
                "Calle 123",
                "pepitoperez@email.com",
                "password"
        );


        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = cuentaServicio.crearCuenta(crearCuentaDTO);

            System.out.println(id);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

}

