package co.edu.uniquindio.ProyectoFinal.dto.cupon;

import co.edu.uniquindio.ProyectoFinal.model.enums.TipoCupon;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CuponInfoDTO(
        @NotBlank String codigo,
        @NotBlank String nombre,
        @NotBlank TipoCupon tipoCupon,
        @NotBlank LocalDateTime fecha,
        @NotBlank float descuento
) {
}
