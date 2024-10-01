package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.dto.carrito.DetalleCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.EliminarEventoCarritoDTO;

public interface CarritoServicio {

    String crearCarrito(String idUsuario) throws Exception;
    String agregarProductoCarrito(DetalleCarritoDTO detalleCarritoDTO) throws Exception;
    String eliminarProductoCarrito(EliminarEventoCarritoDTO eliminarEventoCarritoDTO) throws Exception;
    String limpiarCarrito(String idUsuario) throws Exception;
    String obtenerCarrito(String idUsuario) throws Exception;
    String obtenerInformacionCarrito(String idUsuario) throws Exception;
    String actualizarCarrito(String id, String idUsuario) throws Exception;

}
