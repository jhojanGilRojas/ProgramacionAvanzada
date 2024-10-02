package co.edu.uniquindio.ProyectoFinal.dto.carrito;

import co.edu.uniquindio.ProyectoFinal.model.DetalleCarrito;

import java.time.LocalDateTime;
import java.util.List;

public record CarritoDTO(
        String id,
        LocalDateTime fehca,
        List<DetalleCarrito> item,
        String idUsuario
) {
}
