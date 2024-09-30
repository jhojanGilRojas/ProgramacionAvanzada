package co.edu.uniquindio.ProyectoFinal.model.documents;

import co.edu.uniquindio.ProyectoFinal.model.DetalleCarrito;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("carritos")
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Carrito {

    @Id
    private String id;

    private String idUsuario; //-> objectID por qué?
    private List<DetalleCarrito> items;
    private LocalDateTime fecha;

    @Builder
    public Carrito(String idUsuario, List<DetalleCarrito> items, LocalDateTime fecha) {
        this.idUsuario = idUsuario;
        this.items = items;
        this.fecha = fecha;
    }

}
