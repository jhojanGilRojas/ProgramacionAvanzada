package co.edu.uniquindio.ProyectoFinal;

import co.edu.uniquindio.ProyectoFinal.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.ProyectoFinal.model.documents.Orden;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.OrdenServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
