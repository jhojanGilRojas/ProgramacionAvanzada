package co.edu.uniquindio.ProyectoFinal.dto.cuenta;

public record RegistroClienteDTO(
        String cedula,
        String nombre,
        String telefono,
        String direccion,
        String password,
        String email
) {

}
