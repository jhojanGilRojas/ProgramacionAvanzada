package co.edu.uniquindio.ProyectoFinal.dto.carrito;

import co.edu.uniquindio.ProyectoFinal.model.Localidad;

public record ActualizarEventoCarritoDTO(
        String idCarrito,
        String idEvento,
        int cantidad,
        String nombreLocalidad
) {
}
