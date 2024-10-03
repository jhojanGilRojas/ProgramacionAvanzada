package co.edu.uniquindio.ProyectoFinal.dto.cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegistroClienteDTO(
        @NotBlank @Length (max = 10) String cedula,
        @NotBlank @Length (max = 100) String nombre,
        @NotBlank @Length (max = 10) String telefono,
        @NotBlank @Length (max = 100) String direccion,
        @NotBlank @Length (min = 8, max = 20) String password,
        @NotBlank @Email String email
) {

}
