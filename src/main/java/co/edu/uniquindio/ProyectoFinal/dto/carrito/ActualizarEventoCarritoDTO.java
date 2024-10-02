package co.edu.uniquindio.ProyectoFinal.dto.carrito;

public record ActualizarEventoCarritoDTO(
        String idCarrito,
        String idEvento,
        int cantidad,
        String localidad
) {
}
