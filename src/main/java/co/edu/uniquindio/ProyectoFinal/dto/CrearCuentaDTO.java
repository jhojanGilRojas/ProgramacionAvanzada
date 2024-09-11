package co.edu.uniquindio.ProyectoFinal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record CrearCuentaDTO(

        @NotBlank @Length (max = 10) String cedula,
        @NotBlank @Length (max = 100)String nombre,
        @NotBlank @Length (max = 10)String telefono,
        @NotBlank @Length (max = 100) String direccion,
        @NotBlank @Length (max = 40) @Email String correo,
        @NotBlank @Length (min = 7,max = 20) String password
//        @Past LocalDate fechaNacimiento; se valida que la fecha sea anterior a la actual

) {
}
