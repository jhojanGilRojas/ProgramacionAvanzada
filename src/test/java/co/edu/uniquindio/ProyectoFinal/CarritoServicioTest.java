package co.edu.uniquindio.ProyectoFinal;

import co.edu.uniquindio.ProyectoFinal.dto.carrito.DetalleCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.EliminarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CarritoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CarritoServicioTest {

    @Autowired
    private CarritoServicio carritoServicio;

    @Test
    void crearCarritoTest() {
        String idUsuario = "66fb1b8a27ca9803bbb300d3";
        assertDoesNotThrow(() ->{
            String respuesta = carritoServicio.crearCarrito(idUsuario);
            assertNotNull(respuesta);
        });
    }

    @Test
    void agregarEventoCarritoTest() {
        DetalleCarritoDTO detalleCarritoDTO = new DetalleCarritoDTO(
                "66fb203c19d1bf7f668c8b52",
                "66f846fff4ebbe46dd1963ad",
                2,
                "Platino"
        );
        assertDoesNotThrow(() ->{
            String respuesta = carritoServicio.agregarEventoCarrito(detalleCarritoDTO);
            assertNotNull(respuesta);
        });
    }

    @Test
    void eliminarEventoCarritoTest() {
        EliminarEventoCarritoDTO eliminarEventoCarritoDTO = new EliminarEventoCarritoDTO(
                "66fb203c19d1bf7f668c8b52",
                "66f844d7f44928092f864e78"
        );
        assertDoesNotThrow(() ->{
            String respuesta = carritoServicio.eliminarEventoCarrito(eliminarEventoCarritoDTO);
            assertNotNull(respuesta);
        });
    }

    @Test
    void limpiarCarritoTest() {
        String idCarrito = "66fb203c19d1bf7f668c8b52";
        assertDoesNotThrow(() ->{
            String respuesta = carritoServicio.limpiarCarrito(idCarrito);
            assertNotNull(respuesta);
        });
    }
}
