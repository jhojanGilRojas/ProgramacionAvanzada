package co.edu.uniquindio.ProyectoFinal;

import co.edu.uniquindio.ProyectoFinal.dto.cupon.AplicarCuponDTO;
import co.edu.uniquindio.ProyectoFinal.dto.cupon.CrearCuponDTO;
import co.edu.uniquindio.ProyectoFinal.dto.cupon.CuponInfoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.cupon.EditarCuponDTO;
import co.edu.uniquindio.ProyectoFinal.model.documents.Cupon;
import co.edu.uniquindio.ProyectoFinal.model.documents.Orden;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoCupon;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoCupon;
import co.edu.uniquindio.ProyectoFinal.repositories.CuponRepo;
import co.edu.uniquindio.ProyectoFinal.repositories.OrdenRepo;
import co.edu.uniquindio.ProyectoFinal.services.implement.CuponServicioImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServicioCuponTest {

    @Autowired
    private CuponRepo cuponRepo;

    @Autowired
    private OrdenRepo ordenRepo;

    @Autowired
    private CuponServicioImpl cuponServicio;

    @Test
    public void crearCuponTest() throws Exception {
        CrearCuponDTO cuponDTO = new CrearCuponDTO("DESCUENTO2023", "Cupón Descuento", 15);
        Cupon cupon = Cupon.builder()
                .codigo(cuponDTO.codigo())
                .nombre(cuponDTO.nombre())
                .descuento(cuponDTO.descuento())
                .tipoCupon(TipoCupon.MULTIPLE)
                .estadoCupon(EstadoCupon.DISPONIBLE)
                .fecha(LocalDateTime.now())
                .build();

        Cupon cuponCreado = cuponRepo.save(cupon);
        assertNotNull(cuponCreado);
        assertEquals("DESCUENTO2023", cuponCreado.getCodigo());
    }

    @Test
    public void validarCuponTest() throws Exception {
        String idCuenta = "12345";
        String codigoCupon = "DESCUENTO2023";


        Exception exception = assertThrows(Exception.class, () -> {
            cuponServicio.validarCupon(new AplicarCuponDTO(idCuenta, codigoCupon));
        });

        assertEquals("El cupón ya ha sido redimido por este cliente.", exception.getMessage());
    }


    @Test
    public void buscarCuponPorCodigoTest() throws Exception {
        String codigoCupon = "DESCUENTO2023";

        Cupon cupon = Cupon.builder()
                .codigo(codigoCupon)
                .nombre("Cupón Prueba")
                .descuento(10)
                .tipoCupon(TipoCupon.UNICO)
                .estadoCupon(EstadoCupon.DISPONIBLE)
                .fecha(LocalDateTime.now())
                .build();

        cuponRepo.save(cupon);

        CuponInfoDTO cuponInfoDTO = cuponServicio.buscarCuponPorCodigo(codigoCupon);
        assertNotNull(cuponInfoDTO);
        assertEquals(codigoCupon, cuponInfoDTO.codigo());
    }

    @Test
    public void eliminarCuponTest() {
        Cupon cupon = Cupon.builder()
                .codigo("ELIMINAR123")
                .nombre("Cupón para Eliminar")
                .descuento(20)
                .tipoCupon(TipoCupon.UNICO)
                .estadoCupon(EstadoCupon.DISPONIBLE)
                .fecha(LocalDateTime.now())
                .build();

        Cupon cuponGuardado = cuponRepo.save(cupon);
        assertNotNull(cuponGuardado);

        // Eliminamos el cupón
        cuponRepo.deleteById(cuponGuardado.getId());

        Optional<Cupon> cuponEliminado = cuponRepo.findById(cuponGuardado.getId());
        assertTrue(cuponEliminado.isEmpty());
    }
    @Test
    public void editarCuponTest() throws Exception {
        EditarCuponDTO cuponDTO = new EditarCuponDTO("idCupon","DESCUENTO2023", "Cupón Editado", 30);

        // Editamos el cupón
        cuponServicio.EditarCupon(cuponDTO);

        Cupon cuponGuardado = cuponRepo.findByCodigo(cuponDTO.codigo()).orElse(null);
        assertNotNull(cuponGuardado);
        assertEquals("Cupón Editado", cuponGuardado.getNombre());
        assertEquals(30, cuponGuardado.getDescuento());
    }
    @Test
    public void listarTodosLosCuponesTest() throws Exception {
        List<CuponInfoDTO> cupones = cuponServicio.listarTodosLosCupones();

        assertNotNull(cupones);
        assertFalse(cupones.isEmpty());

        cupones.forEach(cupon -> System.out.println("Cupon: " + cupon));
    }



}
