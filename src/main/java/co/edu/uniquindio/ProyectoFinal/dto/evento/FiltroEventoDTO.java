package co.edu.uniquindio.ProyectoFinal.dto.evento;

import co.edu.uniquindio.ProyectoFinal.model.enums.TipoEvento;

public record FiltroEventoDTO(

        String nombre,
        TipoEvento tipo,
        String ciudad
) {
}
