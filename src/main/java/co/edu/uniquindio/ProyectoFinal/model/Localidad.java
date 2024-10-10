package co.edu.uniquindio.ProyectoFinal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Localidad {

    private String nombre;
    private float precio;
    private int entradasVendidas;
    private int capacidadMaxima;


}
