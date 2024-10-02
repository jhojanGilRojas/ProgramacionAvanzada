package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.dto.carrito.ActualizarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.DetalleCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.EliminarEventoCarritoDTO;

public interface CarritoServicio {

    String crearCarrito(String idUsuario) throws Exception;
    String agregarEventoCarrito(DetalleCarritoDTO detalleCarritoDTO) throws Exception;
    String eliminarEventoCarrito(EliminarEventoCarritoDTO eliminarEventoCarritoDTO) throws Exception;
    String actualizarEventoCarrito(ActualizarEventoCarritoDTO actualizarEventoCarritoDTO) throws Exception;


    String limpiarCarrito(String idUsuario) throws Exception;
    String obtenerInformacionCarrito(String idUsuario) throws Exception;

}
