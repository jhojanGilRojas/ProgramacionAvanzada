package co.edu.uniquindio.ProyectoFinal;

import co.edu.uniquindio.ProyectoFinal.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.ProyectoFinal.dto.cuenta.LoginDTO;
import co.edu.uniquindio.ProyectoFinal.dto.jws.TokenDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CuentaServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServicioCuentaTest {

    @Autowired
    private CuentaServicio cuentaServicio;

    @Test
    void crearCuentaTest(){
        CrearCuentaDTO crearCuentaDTO = new CrearCuentaDTO(
                "1066",
                "Jhojan Gil",
                "314532",
                "Calle 123",
                "jhogillds@gmail.com",
                "password"
        );


        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = cuentaServicio.crearCuenta(crearCuentaDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }

    @Test
    void iniciarSesionTest(){

        LoginDTO loginDTO = new LoginDTO(
                "jhogillds@gmail.com",
                "password"
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se inicia sesion y retorna el token JWS
            TokenDTO tokenDTO = cuentaServicio.iniciarSesion(loginDTO);
            System.out.println(tokenDTO.token());
            // Se espera que el valor del token no sea nulo
            assertNotNull(tokenDTO);
        } );

    }
}
