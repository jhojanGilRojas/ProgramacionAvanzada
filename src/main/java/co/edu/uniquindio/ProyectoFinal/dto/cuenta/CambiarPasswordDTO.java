package co.edu.uniquindio.ProyectoFinal.dto.cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CambiarPasswordDTO(
        @Email String email,
        @NotBlank String codigoVerificacion,
        @NotBlank @Length(min = 7,max = 20)  String passwordNueva
) {
}
