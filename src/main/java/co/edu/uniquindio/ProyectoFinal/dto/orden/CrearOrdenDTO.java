package co.edu.uniquindio.ProyectoFinal.dto.orden;

import co.edu.uniquindio.ProyectoFinal.model.DetalleOrden;
import co.edu.uniquindio.ProyectoFinal.model.Pago;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;


public record CrearOrdenDTO(
        String idUser,
        String idCarrito,
        String codigoCupon
) {
}
