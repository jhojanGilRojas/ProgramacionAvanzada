package co.edu.uniquindio.ProyectoFinal.model.documents;

import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoCupon;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoCupon;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("cupones")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Cupon {

    @Id
    private String id;

    private String codigo;
    private String nombre;
    private EstadoCupon estadoCupon;
    private TipoCupon tipoCupon;
    private LocalDateTime fechaVencimiento;
    private float descuento;

    @Builder

    public Cupon(String codigo, String nombre, EstadoCupon estadoCupon, TipoCupon tipoCupon, LocalDateTime fecha, float descuento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.estadoCupon = estadoCupon;
        this.tipoCupon = tipoCupon;
        this.fechaVencimiento = fecha;
        this.descuento = descuento;
    }
}
