package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.dto.carrito.DetalleCarritoDTO;

public interface CarritoServicio {

    String crearCarrito(String idUsuario) throws Exception;

    String agregarProductoCarrito(DetalleCarritoDTO detalleCarritoDTO) throws Exception;
    String eliminarProductoCarrito(String idProducto, String idUsuario) throws Exception;
    String limpiarCarrito(String idUsuario) throws Exception;
    String obtenerCarrito(String idUsuario) throws Exception;
    String obtenerInformacionCarrito(String idUsuario) throws Exception;
    String actualizarCarrito(String id, String idUsuario) throws Exception;

}
