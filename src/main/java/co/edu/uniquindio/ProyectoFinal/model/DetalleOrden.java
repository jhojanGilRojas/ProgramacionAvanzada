package co.edu.uniquindio.ProyectoFinal.model;

import lombok.*;
import org.bson.types.ObjectId;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class DetalleOrden {

    private String id;
    private String nombreLocalidad;
    private String idEvento;
    private float precio;
    private int cantidad;


    @Builder
    public DetalleOrden(){

    }
}
