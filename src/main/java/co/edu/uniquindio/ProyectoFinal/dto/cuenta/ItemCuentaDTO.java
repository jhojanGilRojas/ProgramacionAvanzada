package co.edu.uniquindio.ProyectoFinal.dto.cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

public record ItemCuentaDTO(

        @Id String id,
        @NotBlank @Length (max = 30) String nombre,
        @NotBlank @Email String email,
        @NotBlank @Length (max = 30) String telefono

) {
}
