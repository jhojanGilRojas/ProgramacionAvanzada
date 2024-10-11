package co.edu.uniquindio.ProyectoFinal.dto.carrito;

import co.edu.uniquindio.ProyectoFinal.model.Localidad;

public record DetalleCarritoDTO(
        String idCarrito,
        String idEvento,
        int cantidad,
        String localidad
) {
}
