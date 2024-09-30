package co.edu.uniquindio.ProyectoFinal.model.documents;

import co.edu.uniquindio.ProyectoFinal.model.Localidad;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoEvento;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoEvento;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Document("eventos")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Evento {

    @Id
    @EqualsAndHashCode.Include
    private String idEvento;

    private String nombre;
    private String descripcion;
    private String direccion;
    private String imagenPortada;
    private String imagenLocalidades;
    private String ciudad;
    private LocalDateTime fecha;
    private List<Localidad> localidades;
    private EstadoEvento estado;
    private TipoEvento tipo;

    @Builder

    public Evento(String nombre, String descripcion, String direccion, String imagenPortada, String imagenLocalidades, String ciudad, LocalDateTime fecha, List<Localidad> localidades, EstadoEvento estado, TipoEvento tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.imagenPortada = imagenPortada;
        this.imagenLocalidades = imagenLocalidades;
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.localidades = localidades;
        this.estado = estado;
        this.tipo = tipo;
    }
}
