package co.edu.uniquindio.ProyectoFinal;

import co.edu.uniquindio.ProyectoFinal.model.Localidad;
import co.edu.uniquindio.ProyectoFinal.model.documents.Evento;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoEvento;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoEvento;
import co.edu.uniquindio.ProyectoFinal.repositories.EventoRepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventoTest {

    @Autowired
    private EventoRepo eventoRepo;

    //Guardamos el evento en la base de datos
    @Test
    public void crearEventoTest() {
        Localidad localidad1 = Localidad.builder()
                .nombre("Localidad de prueba")
                .precio(10000)
                .entradasVendidas(0)
                .capacidadMaxima(100)
                .build();

        Evento evento = Evento.builder()
                .nombre("Evento de prueba")
                .descripcion("Evento de prueba")
                .direccion("Calle 18 # 20")
                .imagenPortada("imagen1.jpg")
                .imagenLocalidades("imagen2.jpg")
                .ciudad("Armenia")
                .fecha(LocalDateTime.now())
                .localidades(List.of(localidad1))
                .estado(EstadoEvento.ACTIVA)
                .tipo(TipoEvento.CONCIERTO)
                .build();
        Evento eventoCreado = eventoRepo.save(evento);
        //Verificamos que se haya guardado validando que no sea null
        assertNotNull(eventoCreado);
    }

    @Test
    public void crearEventoTest2() {
        Localidad localidad1 = Localidad.builder()
                .nombre("Grada Baja")
                .precio(120000)
                .entradasVendidas(0)
                .capacidadMaxima(100)
                .build();
        Localidad localidad2 = Localidad.builder()
                .nombre("Grada Alta")
                .precio(80000)
                .entradasVendidas(0)
                .capacidadMaxima(150)
                .build();
        LocalDateTime fechaEvento = LocalDateTime.of(2025, 10, 12, 20, 30);

        Evento evento = Evento.builder()
                .nombre("Concierto de Rock")
                .descripcion("Concierto de rock en Armenia")
                .direccion("Calle 18 # 20")
                .imagenPortada("imagen1.jpg")
                .imagenLocalidades("imagen2.jpg")
                .ciudad("Armenia")
                .fecha(fechaEvento)
                .localidades(List.of(localidad1, localidad2))
                .estado(EstadoEvento.ACTIVA)
                .tipo(TipoEvento.CONCIERTO)
                .build();
        Evento eventoCreado = eventoRepo.save(evento);
        //Verificamos que se haya guardado validando que no sea null
        assertNotNull(eventoCreado);
    }
    @Test
    public void actualizarTest() {
        //Obtenemos el evento con el id
        Evento evento = eventoRepo.findById("66fab99717c4bd789db57c54").orElseThrow();
        //Modificar la ciudad del evento
        evento.setCiudad("Pereira");


        //Guardamos el evento con la ciudad modificada
        eventoRepo.save(evento);


        //Obtenemos el evento con el id
        Evento eventoActualizado = eventoRepo.findById("66fab99717c4bd789db57c54").orElseThrow();


        //Verificamos que la ciudad del evento haya sido modificada
        assertEquals("Pereira", eventoActualizado.getCiudad());
    }

    @Test
    public void listarEventosTest() {
        //Listamos los eventos
        List<Evento> eventos = eventoRepo.findAll();
        //Imprimimos la lista de eventos
        eventos.forEach(System.out::println);
        assertEquals(2, eventos.size());
    }

    @Test
    public void eliminarEventoTest() {
        //Eliminamos el evento con el id
        eventoRepo.deleteById("66fab99717c4bd789db57c54");
        //Obtenemos la cuenta del usuario con el id XXXXXXX
        Evento evento = eventoRepo.findById("66fab99717c4bd789db57c54").orElse(null);


        //Verificamos que la cuenta no exista (sea null) ya que fue eliminado
        assertNull(evento);
    }
}
