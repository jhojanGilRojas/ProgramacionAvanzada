package co.edu.uniquindio.ProyectoFinal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class DetalleOrden {

    private String id;
    private String nombreLocalidad;
    private ObjectId idEvento;
    private float precio;
    private int cantidad;


}
