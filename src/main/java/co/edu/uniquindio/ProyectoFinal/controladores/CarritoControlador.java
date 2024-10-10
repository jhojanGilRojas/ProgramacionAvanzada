package co.edu.uniquindio.ProyectoFinal.controladores;

import co.edu.uniquindio.ProyectoFinal.dto.carrito.ActualizarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.CarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.DetalleCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.EliminarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CarritoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/carrito")
public class CarritoControlador {

    private final CarritoServicio carritoServicio;

    @PostMapping("/crear-carrito/{idUsuario}")
    public void crearCarrito(@PathVariable String idUsuario) throws Exception {
        carritoServicio.crearCarrito(idUsuario);
    }

    @PostMapping("/agregar-evento")
    public void agregarEvento(@RequestBody DetalleCarritoDTO detalle) throws Exception {
        carritoServicio.agregarEventoCarrito(detalle);
    }

    @DeleteMapping("/eliminar-item/")
    public void eliminarEventoCarrito(@RequestBody EliminarEventoCarritoDTO  eliminarEventoCarritoDTO) throws Exception{
        carritoServicio.eliminarEventoCarrito(eliminarEventoCarritoDTO);
    }

    @DeleteMapping("/vaciar-carrito/{idCarrito}")
    public void vaciarCarrito(@PathVariable String idCarrito) throws Exception {
        carritoServicio.limpiarCarrito(idCarrito);
    }

    @GetMapping("/obtener-info-carrito/{idCarrito}")
    public CarritoDTO obtenerCarrito(@PathVariable String idCarrito) throws Exception {
        return carritoServicio.obtenerInformacionCarrito(idCarrito);
    }

    @PutMapping("/actualizar-carrito/{idCarrito}")
    public void actualizarCarrito(@RequestBody ActualizarEventoCarritoDTO actualizarEventoCarritoDTO) throws Exception {
        carritoServicio.actualizarEventoCarrito(actualizarEventoCarritoDTO);
    }

}
