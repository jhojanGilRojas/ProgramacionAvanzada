package co.edu.uniquindio.ProyectoFinal.model.documents;

import co.edu.uniquindio.ProyectoFinal.model.DetalleOrden;
import co.edu.uniquindio.ProyectoFinal.model.Pago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("Ordenes")
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Orden {

    @Id
    private String idOrden;

    private LocalDateTime fecha;
    private String codigoPasarela;
    private Pago pago;
    private ObjectId idCliente;
    private ObjectId idCupon;
    private List<DetalleOrden> items;
    private float total;


    // construtor Builder
}
