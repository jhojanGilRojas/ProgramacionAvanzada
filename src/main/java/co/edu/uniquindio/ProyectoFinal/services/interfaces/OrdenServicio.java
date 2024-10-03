package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.ProyectoFinal.dto.orden.InformacionOrdenDTO;

public interface OrdenServicio {

    String crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception;

    String cancelarOrden(String id) throws Exception;

    InformacionOrdenDTO obtenerInformacionOrden(String id) throws Exception;

    void recibirNotificacionMercadoPago();
}
