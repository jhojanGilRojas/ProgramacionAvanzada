package co.edu.uniquindio.ProyectoFinal.dto.cupon;

import co.edu.uniquindio.ProyectoFinal.model.enums.TipoCupon;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record CrearCuponUnicoDTO (

        @Id String idCuenta,

        @NotBlank String codigo,
        @NotBlank String nombre,
        @NotBlank LocalDateTime fecha,
        @NotBlank float descuento
){
}
