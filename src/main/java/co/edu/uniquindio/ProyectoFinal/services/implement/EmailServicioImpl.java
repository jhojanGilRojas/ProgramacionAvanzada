package co.edu.uniquindio.ProyectoFinal.services.implement;

import co.edu.uniquindio.ProyectoFinal.dto.email.EmailDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.EmailServicio;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailServicioImpl implements EmailServicio {


    String host = "smtp.gmail.com";
    String username = "proyectofinal255@gmail.com";
    int port = 587;
    String password = "juxjbzgwbsdkthvq";
    @Override
    @Async
    public void enviarCorreo(EmailDTO emailDTO) throws Exception {
        System.out.println(username+ port + password + "El error esta aqui");

        Email email = EmailBuilder.startingBlank()
                .from(username)
                .to(emailDTO.destinatario())
                .withSubject(emailDTO.asunto())
                .withPlainText(emailDTO.cuerpo())
                .buildEmail();


        try (Mailer mailer = MailerBuilder
                .withSMTPServer(host, port, username, password)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {


            mailer.sendMail(email);
        }

    }

}

