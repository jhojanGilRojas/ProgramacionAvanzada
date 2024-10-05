package co.edu.uniquindio.ProyectoFinal.dto.cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginDTO(
        @NotBlank @Email String correo,
        @NotBlank @Length(min = 8, max = 20) String password
) {
}
