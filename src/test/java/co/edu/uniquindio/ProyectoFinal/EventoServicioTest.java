package co.edu.uniquindio.ProyectoFinal;

import co.edu.uniquindio.ProyectoFinal.dto.evento.*;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoEvento;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.EventoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventoServicioTest {
    @Autowired
    private EventoServicio eventoServicio;

    @Test
    public void crearEventoTest() {
        LocalidadDTO localidadnorte = new LocalidadDTO("Occidente", 100, 100000.0f);
        LocalidadDTO localidadsur = new LocalidadDTO("Norte", 100, 80000.0f);
        LocalidadDTO localidadoccidente = new LocalidadDTO("Occidente", 100, 60000.0f);
        List<LocalidadDTO> localidades = List.of(localidadnorte, localidadsur, localidadoccidente);
        CrearEventoDTO nuevoEvento = new CrearEventoDTO(
                "nacional vs medellin",
                "Partido liga nacional colombiana",
                LocalDateTime.of(2024, 10, 30, 20, 0),
                TipoEvento.CONCIERTO,
                "Atanasio Girardot",
                "Medellin",
                localidades,
                "NACVSMED.jpg",
                "Localidades.jpg"
        );
        assertDoesNotThrow(() -> {
            String id = eventoServicio.crearEvento(nuevoEvento);
            assertNotNull(id);
        });
    }

    @Test
    public void editarEventoTest() {
        String idEvento = "67032d210938cb769ba4352d";
        EditarEventoDTO eventoEditado = new EditarEventoDTO(
                idEvento,
                "NFrealmusic",
                "Cantante de rap en ingles conocido como nf",
                LocalDateTime.of(2025, 10, 15, 20, 0),
                TipoEvento.CONCIERTO,
                "Estadio Centenario",
                "Armenia",
                List.of(new LocalidadDTO("Norte", 100, 100000.0f)),
                "Nfrealmusic.jpg",
                "Localidades.jpg"
        );
        //Se espera que no se lance ninguna excepción
        assertDoesNotThrow(() -> {
            //Se edita el evento con el id definido
            eventoServicio.editarEvento(eventoEditado);


            //Obtenemos el detalle de la cuenta del usuario con el id definido
            InformacionEventoDTO detalle = eventoServicio.obtenerInformacionEvento(idEvento);


            //Se verifica que la dirección del usuario sea la actualizada
            assertEquals("Estadio Centenario", detalle.direccion());
            assertEquals(LocalDateTime.of(2025, 10, 15, 20, 0), detalle.fecha());
        });

    }
    @Test
    public void eliminarEventoTest(){

        //Se define el id del evento a eliminar, este id está en el dataset.js
        String idEvento = "66a2c476991cff088eb80aaf";

        assertDoesNotThrow(() -> eventoServicio.eliminarEvento(idEvento) );
        //Se espera una excepción ya que el evento debe estar inactivo
        assertThrows(Exception.class, () -> eventoServicio.obtenerInformacionEvento(idEvento) );
    }
    @Test
    public void listarEventosTest(){
        //Se obtiene la lista de los eventos
        List<ItemEventoDTO> lista = eventoServicio.listarEventos();


        //Se verifica que la lista no sea nula y que tenga 2 elementos (o los que hayan)
        assertEquals(2, lista.size());
    }
    @Test
    public void filtrarEventosTest(){
        //Se define un filtro para buscar eventos en Armenia
        FiltroEventoDTO filtro = new FiltroEventoDTO(null, null, "Armenia");

        //Se obtiene la lista de eventos que cumplan con el filtro
        List<ItemEventoDTO> lista = eventoServicio.filtrarEventos(filtro);

        //Se verifica que la lista no sea nula y que tenga 2 elementos (o los que hayan)
        assertEquals(2, lista.size());
        System.out.printf("Eventos en Armenia: %s%n", lista);
    }



}
