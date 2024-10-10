package co.edu.uniquindio.ProyectoFinal;

import co.edu.uniquindio.ProyectoFinal.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.ProyectoFinal.dto.orden.InformacionOrdenDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.OrdenServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrdernServicioTest {

    @Autowired
    private OrdenServicio ordenServicio;

    @Test
    public void crearOrdenTest() {
        CrearOrdenDTO  crearOrdenDTO = new CrearOrdenDTO(
                "66fb1b8a27ca9803bbb300d3",
                "66fb203c19d1bf7f668c8b52",
                "CUPON13"
        );
        assertDoesNotThrow(() ->{
            ordenServicio.crearOrden(crearOrdenDTO);
        });
    }

    @Test
    public void obtenerOrdenPorIdTest() {
        assertDoesNotThrow(() ->{
            InformacionOrdenDTO orden = ordenServicio.obtenerInformacionOrden("66fb1b8a27ca9803bbb300d3");
            assertNotNull(orden);
        });

    }

    @Test
    public void obtenerOrdenesTest() {
        assertDoesNotThrow(() ->{
            List<InformacionOrdenDTO> ordenDTOList = ordenServicio.obtenerOrdenes("66fb203c19d1bf7f668c8b52");
            assertNotNull(ordenDTOList);
        });
    }

    @Test
    public void cancelarOrdenTest() {
        assertDoesNotThrow(() ->{
            ordenServicio.cancelarOrden("66fb1b8a27ca9803bbb300d3");
        });
    }

}
