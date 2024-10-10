package co.edu.uniquindio.ProyectoFinal.controladores;

import co.edu.uniquindio.ProyectoFinal.dto.carrito.ActualizarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.CarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.DetalleCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.EliminarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.jws.MensajeDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CarritoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cliente/carrito")
public class CarritoControlador {

    private final CarritoServicio carritoServicio;

    @PostMapping("/crear-carrito/{idUsuario}")
    public ResponseEntity<MensajeDTO<String>> crearCarrito(@PathVariable String idUsuario) throws Exception {
        carritoServicio.crearCarrito(idUsuario);
        return ResponseEntity.ok().body(new MensajeDTO<String>(false, "Se ha creado el carrito correctamente"));
    }

    @PostMapping("/agregar-evento")
    public ResponseEntity<MensajeDTO<String>> agregarEvento(@RequestBody DetalleCarritoDTO detalle) throws Exception {
        carritoServicio.agregarEventoCarrito(detalle);
        return ResponseEntity.ok().body(new MensajeDTO<String>(false, "Se ha agregado el evento al carrito correctamente"));
    }

    @DeleteMapping("/eliminar-item/")
    public ResponseEntity<MensajeDTO<String>> eliminarEventoCarrito(@RequestBody EliminarEventoCarritoDTO  eliminarEventoCarritoDTO) throws Exception{
        carritoServicio.eliminarEventoCarrito(eliminarEventoCarritoDTO);
        return ResponseEntity.ok().body(new MensajeDTO<String>(false, "Se ha eliminado el evento del carrito correctamente"));
    }

    @DeleteMapping("/vaciar-carrito/{idCarrito}")
    public ResponseEntity<MensajeDTO<String>> vaciarCarrito(@PathVariable String idCarrito) throws Exception {
        carritoServicio.limpiarCarrito(idCarrito);
        return ResponseEntity.ok().body(new MensajeDTO<String>(false, "Se ha vaciado el carrito correctamente"));
    }

    @GetMapping("/obtener-info-carrito/{idCarrito}")
    public ResponseEntity<MensajeDTO<CarritoDTO>> obtenerCarrito(@PathVariable String idCarrito) throws Exception {
        return  ResponseEntity.ok().body(new MensajeDTO<CarritoDTO>(false, carritoServicio.obtenerInformacionCarrito(idCarrito)));
    }

    @PutMapping("/actualizar-carrito/{idCarrito}")
    public ResponseEntity<MensajeDTO<String>> actualizarCarrito(@RequestBody ActualizarEventoCarritoDTO actualizarEventoCarritoDTO) throws Exception {
        carritoServicio.actualizarEventoCarrito(actualizarEventoCarritoDTO);
        return ResponseEntity.ok().body(new MensajeDTO<String>(false, "Se ha actualizado el carrito correctamente"));
    }

}
