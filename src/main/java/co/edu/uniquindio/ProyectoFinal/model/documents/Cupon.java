package co.edu.uniquindio.ProyectoFinal.model.documents;

import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoCupon;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoCupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("cupones")
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Cupon {

    @Id
    private String id;

    private String codigo;
    private String nombre;
    private EstadoCupon estadoCupon;
    private TipoCupon tipoCupon;
    private LocalDateTime fechaVencimiento;
    private float descuento;
}
