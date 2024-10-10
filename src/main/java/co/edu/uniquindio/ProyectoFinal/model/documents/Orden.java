package co.edu.uniquindio.ProyectoFinal.model.documents;

import co.edu.uniquindio.ProyectoFinal.model.DetalleOrden;
import co.edu.uniquindio.ProyectoFinal.model.Pago;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("Ordenes")
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Orden {

    @Id
    private String idOrden;

    private LocalDateTime fecha;
    private String codigoPasarela;
    private Pago pago;
    private String idCliente;
    private String idCupon;
    private List<DetalleOrden> items;
    private float total;

    public Orden() {
        this.items = new ArrayList<>();
    }
}
