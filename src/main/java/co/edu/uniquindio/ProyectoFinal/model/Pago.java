package co.edu.uniquindio.ProyectoFinal.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Pago {
    // no se si deberia ser una collecion
    private String codigo;
    private String moneda;
    private String tipoPago;
    private String detalleEstado;
    private String codigoAutorizacion;
    private String estado;
    private float valorTransaccion;
    private LocalDateTime fecha;

    public Pago() {
    }

}
