package co.edu.uniquindio.ProyectoFinal.controladores;

import co.edu.uniquindio.ProyectoFinal.dto.jws.MensajeDTO;
import co.edu.uniquindio.ProyectoFinal.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.OrdenServicio;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orden")
public class OrdenControlador {

    private final OrdenServicio ordenServicio;

    @PostMapping("/crear-orden")
    public void crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception {
        ordenServicio.crearOrden(crearOrdenDTO);
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
    public ResponseEntity<MensajeDTO> historialOrdenes(@PathVariable String idUsuario) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.obtenerInformacionOrden(idUsuario)));
    }

    @GetMapping("/detalle-orden/{idOrden}")
    public ResponseEntity<MensajeDTO> detalleOrden(@PathVariable String idOrden) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.obtenerInformacionOrden(idOrden)));
    }

    @PostMapping("/notificacion-pago")
    public void recibirNotificacionMercadoPago(@RequestBody Map<String, Object> requestBody) {
        ordenServicio.recibirNotificacionMercadoPago(requestBody);
    }


}
