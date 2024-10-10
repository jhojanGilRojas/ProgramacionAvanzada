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
@AllArgsConstructor
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


    public Localidad obtenerLocalidad(String nombreLocalidad) {
        for (Localidad localidad : localidades) {
            if (localidad.getNombre().equalsIgnoreCase(nombreLocalidad)) {
                return localidad;
            }
        }
        return null;
    }
}
