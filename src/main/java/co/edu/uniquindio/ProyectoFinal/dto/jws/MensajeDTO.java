package co.edu.uniquindio.ProyectoFinal.dto.jws;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
