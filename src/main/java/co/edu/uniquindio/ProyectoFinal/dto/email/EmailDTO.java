package co.edu.uniquindio.ProyectoFinal.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmailDTO(
        @NotBlank  String asunto,
        @NotBlank  String cuerpo,
        @NotBlank @Email String destinatario
) {

    }
