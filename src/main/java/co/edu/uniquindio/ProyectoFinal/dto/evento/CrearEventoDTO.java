package co.edu.uniquindio.ProyectoFinal.dto.evento;

import co.edu.uniquindio.ProyectoFinal.model.enums.TipoEvento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

public record CrearEventoDTO(
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank String descripcion,
        @NotNull @Future LocalDateTime fecha,
        @NotNull TipoEvento tipoEvento,
        @NotBlank @Length(max = 100) String direccion,
        @NotBlank @Length(max = 100) String ciudad,
        @NotEmpty List<LocalidadDTO> localidades,
        @NotBlank @Length(max = 200)String imagenPortada,
        @NotBlank @Length(max = 200)String imagenLocalidades) {
}