package co.edu.uniquindio.ProyectoFinal.dto.carrito;

public record DetalleCarritoDTO(
        String idCarrito,
        String idEvento,
        int cantidad,
        String nombreLocalidad
) {
}
