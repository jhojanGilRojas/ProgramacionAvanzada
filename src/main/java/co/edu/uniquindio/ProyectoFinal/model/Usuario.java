package co.edu.uniquindio.ProyectoFinal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Usuario {


    private String id;

    private String telefono;
    private String direccion;
    private String cedula;
    private String nombre;
}
