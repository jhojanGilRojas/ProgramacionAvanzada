package co.edu.uniquindio.ProyectoFinal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@ToString

public class Pago {

    //private String moneda;
    private String tipoPago;
    // private String detalleEstado;
    private String codigoAutorizacion;
    private String estado;
    private float valorTransaccion;
    private LocalDateTime fecha;

}
