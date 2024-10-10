package co.edu.uniquindio.ProyectoFinal.controladores;

import co.edu.uniquindio.ProyectoFinal.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.OrdenServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orden")
public class OrdenControlador {

    private final OrdenServicio ordenServicio;

    @PostMapping("/crear-orden")
    public void crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception {
        ordenServicio.crearOrden(crearOrdenDTO);
    }

    /*@PostMapping("/realizar-pago")
    public ResponseEntity<MensajeDTO<Preference>> realizarPago(@RequestParam("idOrden") String idOrden) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.realizarPago(idOrden)));
    }


    @PostMapping("/notificacion-pago")
    public void recibirNotificacionMercadoPago(@RequestBody Map<String, Object> requestBody) {
        ordenServicio.recibirNotificacionMercadoPago(requestBody);
    }*/


}
