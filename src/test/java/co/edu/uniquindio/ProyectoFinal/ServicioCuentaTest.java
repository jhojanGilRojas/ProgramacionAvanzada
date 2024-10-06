package co.edu.uniquindio.ProyectoFinal;

import co.edu.uniquindio.ProyectoFinal.config.JWTUtils;
import co.edu.uniquindio.ProyectoFinal.dto.cuenta.*;
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
    @Autowired
    private JWTUtils jwtUtils;

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
            System.out.println(jwtUtils.parseJwt(tokenDTO.token()).getPayload().toString());
            // Se espera que el valor del token no sea nulo
            assertNotNull(tokenDTO);
        } );

    }

    @Test
    public void actualizarCuentaTest(){

        //Se define el id de la cuenta del usuario a actualizar, este id está en el dataset.js
        String idCuenta = "66fe4c64830f155e8afb9743";


        //Se crea un objeto de tipo EditarCuentaDTO
        EditarCuentaDTO editarCuentaDTO = new EditarCuentaDTO(
                idCuenta,
                "Pepito perez",
                "12121",
                "Nueva dirección",
                "password"
        );


        //Se espera que no se lance ninguna excepción
        assertDoesNotThrow(() -> {
            //Se actualiza la cuenta del usuario con el id definido
            cuentaServicio.editarCuenta(editarCuentaDTO);


            //Obtenemos el detalle de la cuenta del usuario con el id definido
            InformacionCuentaDTO detalle = cuentaServicio.obtenerInformacionCuenta(idCuenta);


            //Se verifica que la dirección del usuario sea la actualizada
            assertEquals("Nueva dirección", detalle.direccion());
        });
    }


    @Test
    public void eliminarCuentaTest(){


        //Se define el id de la cuenta del usuario a eliminar, este id está en el dataset.js
        String idCuenta = "66fe4c64830f155e8afb9743";


        //Se elimina la cuenta del usuario con el id definido
        assertDoesNotThrow(() -> cuentaServicio.eliminarCuenta(idCuenta) );


        //Al intentar obtener la cuenta del usuario con el id definido se debe lanzar una excepción
        assertThrows(Exception.class, () -> cuentaServicio.obtenerInformacionCuenta(idCuenta) );
    }



    @Test
    void obtenerInformacionCuentaTest() throws Exception {

        String id = "66fe4c64830f155e8afb9743";
        assertDoesNotThrow(() -> {

            InformacionCuentaDTO detalle = cuentaServicio.obtenerInformacionCuenta(id);
        });


    }

    @Test
    void enviarCodigoRecuperacionPasswordTest(){

        String correo = "pepeperez@email.com";

        assertDoesNotThrow(() ->{

            String respuesta = cuentaServicio.enviarCodigoRecuperacionPassword(correo);
            System.out.println(respuesta);
        });
    }

    @Test
    void cambiarPasswordTest(){

        CambiarPasswordDTO cambiarPasswordDTO = new CambiarPasswordDTO(
                "pepeperez@email.com",
                "SgBoLd",
                "123456"
        );

        assertDoesNotThrow(() ->{

            String respuesta = cuentaServicio.cambiarPassword(cambiarPasswordDTO);
            System.out.println(respuesta);
    });
    }
}
