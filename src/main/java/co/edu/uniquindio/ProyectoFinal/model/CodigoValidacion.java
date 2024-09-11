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
public class CodigoValidacion {

    private String codigo;
    private LocalDateTime fechaCreacion;
}
