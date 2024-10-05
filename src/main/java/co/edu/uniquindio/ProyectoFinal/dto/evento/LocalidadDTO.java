package co.edu.uniquindio.ProyectoFinal.dto.evento;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record LocalidadDTO (
        @NotBlank @Length(max = 100) String nombre,
        @NotNull @Min(1) Integer capacidadMaxima,
        @NotNull @Min(0) Integer precio){
}
