package co.edu.uniquindio.ProyectoFinal.dto.cuenta;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public record ValidarCuentaDTO(

        @Id String id,
        @NotBlank String codigo
) {
}
