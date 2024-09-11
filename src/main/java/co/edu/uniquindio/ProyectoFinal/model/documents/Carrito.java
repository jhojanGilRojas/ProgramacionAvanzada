package co.edu.uniquindio.ProyectoFinal.model.documents;

import co.edu.uniquindio.ProyectoFinal.model.DetalleCarrito;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("carritos")
@AllArgsConstructor
@Getter
@Setter

public class Carrito {

    @Id
    private String id;

    private ObjectId idUsuario;
    private List<DetalleCarrito> items;
    private LocalDateTime fecha;

}
