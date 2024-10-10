package co.edu.uniquindio.ProyectoFinal.dto.cupon;

import co.edu.uniquindio.ProyectoFinal.model.enums.TipoCupon;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record EditarCuponDTO (

        @Id String idCupon,
        @NotBlank String codigo,
        @NotBlank String nombre,
        @NotBlank float descuento
) {
}
