package co.edu.uniquindio.ProyectoFinal;

import co.edu.uniquindio.ProyectoFinal.dto.carrito.ActualizarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.CarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.DetalleCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.EliminarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.ProyectoFinal.model.Localidad;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CarritoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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
            carritoServicio.crearCarrito(idUsuario);
        });
    }

    @Test
    void agregarEventoCarritoTest() {
        DetalleCarritoDTO detalleCarritoDTO = new DetalleCarritoDTO(
                "67075285b8d4077f18ead4e9",
                "66f846fff4ebbe46dd1963ad",
                2,
                "Localidad de prueba"
        );
        assertDoesNotThrow(() ->{
             carritoServicio.agregarEventoCarrito(detalleCarritoDTO);
        });
    }

    @Test
    void eliminarEventoCarritoTest() {
        EliminarEventoCarritoDTO eliminarEventoCarritoDTO = new EliminarEventoCarritoDTO(
                "66fb203c19d1bf7f668c8b52",
                "66f844d7f44928092f864e78"
        );
        assertDoesNotThrow(() ->{
             carritoServicio.eliminarEventoCarrito(eliminarEventoCarritoDTO);
        });
    }

    @Test
    void limpiarCarritoTest() {
        String idCarrito = "66fb203c19d1bf7f668c8b52";
        assertDoesNotThrow(() ->{
            carritoServicio.limpiarCarrito(idCarrito);
        });
    }

    @Test
    void obtenerCarritoTest() {
        String idCarrito = "66fb203c19d1bf7f668c8b52";
        assertDoesNotThrow(() ->{
            CarritoDTO respuesta = carritoServicio.obtenerInformacionCarrito(idCarrito);
        });
    }

    @Test
    void actualizarEventoCarritoTest(){
        ActualizarEventoCarritoDTO actualizarEventoCarritoDTO = new ActualizarEventoCarritoDTO(
                "66fb203c19d1bf7f668c8b52",
                "66f846fff4ebbe46dd1963ad",
                3,
                "Platino"
        );
        assertDoesNotThrow(() ->{
            carritoServicio.actualizarEventoCarrito(actualizarEventoCarritoDTO);
        });
    }
}
