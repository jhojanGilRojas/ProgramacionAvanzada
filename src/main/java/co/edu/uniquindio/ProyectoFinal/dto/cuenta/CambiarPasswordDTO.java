package co.edu.uniquindio.ProyectoFinal.dto.cuenta;

public record CambiarPasswordDTO(
        String codigoVerificacion,
        String passwordNueva
) {
}
