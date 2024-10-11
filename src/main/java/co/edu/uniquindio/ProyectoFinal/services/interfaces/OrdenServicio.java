package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.ProyectoFinal.dto.orden.InformacionOrdenDTO;
import com.mercadopago.resources.preference.Preference;

import java.util.List;
import java.util.Map;

public interface OrdenServicio {

    String crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception;
    void cancelarOrden(String idOrden) throws Exception;
    InformacionOrdenDTO obtenerInformacionOrden(String idOrden) throws Exception;
    void recibirNotificacionMercadoPago(Map<String, Object> request);
    Preference realzarPagoOrden(String idOrden) throws Exception;
    List<InformacionOrdenDTO> obtenerOrdenes(String idUsuario);

}
