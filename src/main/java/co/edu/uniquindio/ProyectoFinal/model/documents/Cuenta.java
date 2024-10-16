package co.edu.uniquindio.ProyectoFinal.model.documents;

import co.edu.uniquindio.ProyectoFinal.model.CodigoValidacion;
import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoCuenta;
import co.edu.uniquindio.ProyectoFinal.model.enums.Rol;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("cuentas")
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cuenta {
    @Id
//  @EqualsAndHashCode
    private String idCuenta;

    private String password;
    private String email;
    private Rol rol;
    private CodigoValidacion codigoValidacionRegistro;
    private CodigoValidacion codigoValidacionPassword;
    private EstadoCuenta estadoCuenta;
    private Usuario usuario;
    private LocalDateTime fechaRegistro;

    // construtor Builder
    @Builder
    public Cuenta (String email,String password,Rol rol,CodigoValidacion codigoValidacionRegistro,CodigoValidacion codigoValidacionPassword,EstadoCuenta estadoCuenta,LocalDateTime fechaRegistro, Usuario usuario) {

        this.password = password;
        this.email = email;
        this.rol = rol;
        this.codigoValidacionRegistro = codigoValidacionRegistro;
        this.codigoValidacionPassword = codigoValidacionPassword;
        this.estadoCuenta = estadoCuenta;
        this.usuario = usuario;
        this.fechaRegistro = fechaRegistro;

    }

}
