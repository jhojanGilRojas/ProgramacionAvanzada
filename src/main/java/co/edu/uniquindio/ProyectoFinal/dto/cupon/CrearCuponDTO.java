package co.edu.uniquindio.ProyectoFinal.dto.cupon;

import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoCupon;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoCupon;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record CrearCuponDTO (

        @NotBlank String codigo,
        @NotBlank String nombre,
        @NotBlank float descuento
) {
}
