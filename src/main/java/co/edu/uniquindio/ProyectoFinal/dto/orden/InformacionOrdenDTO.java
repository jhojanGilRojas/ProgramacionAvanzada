package co.edu.uniquindio.ProyectoFinal.dto.orden;

import co.edu.uniquindio.ProyectoFinal.model.DetalleOrden;
import co.edu.uniquindio.ProyectoFinal.model.Pago;

import java.time.LocalDateTime;
import java.util.List;

public record InformacionOrdenDTO(
        String idOrden,
        LocalDateTime fecha,
        String codigoPasarela,
        List<DetalleOrden> items,
        Pago pago,
        float total,
        String idCupon
) {
}
