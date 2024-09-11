package co.edu.uniquindio.ProyectoFinal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Localidad {

    private String nombre;
    private float precio;
    private int entradasVendidas;
    private int capacidadMaxima;
}
