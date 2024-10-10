package co.edu.uniquindio.ProyectoFinal.dto.cupon;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public record AplicarCuponDTO (
        @Id String idCuenta,
        @NotBlank String codigoCupon

){
}
