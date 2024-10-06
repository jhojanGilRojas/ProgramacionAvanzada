package co.edu.uniquindio.ProyectoFinal.dto.evento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ItemEventoDTO(
        @NotBlank String nombre,
        @NotNull LocalDateTime fecha,
        @NotBlank String direccion,
        @NotBlank String ciudad,
        @NotBlank String urlImagenPoster
) {
}
