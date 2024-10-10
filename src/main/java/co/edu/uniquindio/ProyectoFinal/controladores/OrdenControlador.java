package co.edu.uniquindio.ProyectoFinal.controladores;

import co.edu.uniquindio.ProyectoFinal.dto.jws.MensajeDTO;
import co.edu.uniquindio.ProyectoFinal.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.ProyectoFinal.dto.orden.InformacionOrdenDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.OrdenServicio;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orden")
public class OrdenControlador {

    private final OrdenServicio ordenServicio;

    @PostMapping("/crear-orden")
    public ResponseEntity<MensajeDTO<String>> crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception {
        ordenServicio.crearOrden(crearOrdenDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Orden creada correctamente"));
    }

    @PostMapping("/realizar-pago")
    public ResponseEntity<MensajeDTO<Preference>> realizarPago(@RequestParam("idOrden") String idOrden) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.realzarPagoOrden(idOrden)));
    }

    @PostMapping("/cancelar-orden/{idOrden}")
    public void cancelarOrden(@PathVariable String idOrden) throws Exception {
        ordenServicio.cancelarOrden(idOrden);
    }

    @GetMapping("/historial-ordenes/{idUsuario}")
    public ResponseEntity<MensajeDTO<List<InformacionOrdenDTO>>> historialOrdenes(@PathVariable String idUsuario) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.obtenerOrdenes(idUsuario)));
    }

    @GetMapping("/detalle-orden/{idOrden}")
    public ResponseEntity<MensajeDTO<InformacionOrdenDTO>> detalleOrden(@PathVariable String idOrden) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.obtenerInformacionOrden(idOrden)));
    }

    @PostMapping("/notificacion-pago")
    public void recibirNotificacionMercadoPago(@RequestBody Map<String, Object> requestBody) {
        ordenServicio.recibirNotificacionMercadoPago(requestBody);
    }


}
