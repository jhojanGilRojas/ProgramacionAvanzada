package co.edu.uniquindio.ProyectoFinal.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Localidad {

    private String nombre;
    private float precio;
    private int entradasVendidas;
    private int capacidadMaxima;

    @Builder

    public Localidad(String nombre, float precio, int entradasVendidas, int capacidadMaxima) {
        this.nombre = nombre;
        this.precio = precio;
        this.entradasVendidas = entradasVendidas;
        this.capacidadMaxima = capacidadMaxima;
    }
}
