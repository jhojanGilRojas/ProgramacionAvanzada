package co.edu.uniquindio.ProyectoFinal.dto.evento;

import co.edu.uniquindio.ProyectoFinal.model.enums.TipoEvento;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record FiltroEventoDTO(

        @Length(max = 100)String nombre,
        TipoEvento tipoEvento,
        @Length(max = 100)String ciudad
) {
}
