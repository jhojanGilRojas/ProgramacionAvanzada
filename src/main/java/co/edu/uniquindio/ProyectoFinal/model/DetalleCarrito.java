package co.edu.uniquindio.ProyectoFinal.model;
import lombok.*;
import org.springframework.data.annotation.Id;

import org.bson.types.ObjectId;


@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DetalleCarrito {
    @Id
    private String idEvento;
    private String nombreLocalidad;
    private int cantidad;

    @Builder
    public DetalleCarrito(String idEvento, String nombreLocalidad, int cantidad) {
        this.idEvento = idEvento;
        this.nombreLocalidad = nombreLocalidad;
        this.cantidad = cantidad;
    }
}
