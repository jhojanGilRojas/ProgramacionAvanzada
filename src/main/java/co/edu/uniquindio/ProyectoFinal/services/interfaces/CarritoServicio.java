package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.dto.carrito.ActualizarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.CarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.DetalleCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.EliminarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.model.documents.Carrito;

public interface CarritoServicio {

    void crearCarrito(String idUsuario) throws Exception;
    void agregarEventoCarrito(DetalleCarritoDTO detalleCarritoDTO) throws Exception;
    void eliminarEventoCarrito(EliminarEventoCarritoDTO eliminarEventoCarritoDTO) throws Exception;
    void actualizarEventoCarrito(ActualizarEventoCarritoDTO actualizarEventoCarritoDTO) throws Exception;
    void limpiarCarrito(String idUsuario) throws Exception;
    CarritoDTO obtenerInformacionCarrito(String idUsuario) throws Exception;
    Carrito obtenerCarritoPorId(String idCarrito) throws Exception;

}
