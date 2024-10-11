package co.edu.uniquindio.ProyectoFinal.services.implement;

import co.edu.uniquindio.ProyectoFinal.dto.carrito.ActualizarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.CarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.DetalleCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.EliminarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.model.DetalleCarrito;
import co.edu.uniquindio.ProyectoFinal.model.documents.Carrito;
import co.edu.uniquindio.ProyectoFinal.model.documents.Evento;
import co.edu.uniquindio.ProyectoFinal.repositories.CarritoRepo;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CarritoServicio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CuentaServicio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.EventoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarritoServicioImpl implements CarritoServicio {

    private final CarritoRepo carritoRepo;
    private final EventoServicio eventoServicio;
    private final CuentaServicio cuentaServicio;

    @Override
    public void crearCarrito(String idUsuario) throws Exception {
        cuentaServicio.obtenerPorID(idUsuario);
        Carrito carrito = new Carrito();
        carrito.setIdUsuario(idUsuario);
        carrito.setFecha(LocalDateTime.now());
        carrito.setItems(new ArrayList<>());
        carritoRepo.save(carrito);
        //return "Se ha creado el carrito de compras con el id: ";
    }

    @Override
    public void agregarEventoCarrito(DetalleCarritoDTO detalle) throws Exception {
        Optional<Carrito> optionalCarrito = carritoRepo.findById(detalle.idCarrito());
        Evento evento = eventoServicio.buscarEventoPorId(detalle.idEvento());

        if(optionalCarrito.isEmpty()){
            throw new Exception("No se encontro el carrito con el id: "+detalle.idCarrito());
        }

        evento.getLocalidades().forEach((localidad)->{
            if(localidad.equals(detalle.localidad())){
                if(localidad.getCapacidadMaxima() < detalle.cantidad()){
                    throw new RuntimeException("Ya están agotadas las entradas a la localidad");
                }
            }
        });
        Carrito carrito = optionalCarrito.get();

        carrito.getItems().forEach((item)->{
            if(item.getIdEvento().equals(detalle.idEvento())){
                throw new RuntimeException("El evento ya se encuentra en el carrito");
            }
        });
        DetalleCarrito detalleCarrito = new DetalleCarrito();
        detalleCarrito.setCantidad(detalle.cantidad());
        detalleCarrito.setIdEvento(detalle.idEvento());
        detalleCarrito.setNombreLocalidad(detalle.localidad());
        carrito.getItems().add(detalleCarrito);
        carritoRepo.save(carrito);
    }

    @Override
    public void eliminarEventoCarrito(EliminarEventoCarritoDTO eliminarEventoCarritoDTO) throws Exception {
        Optional<Carrito> optionalCarrito = carritoRepo.findById(eliminarEventoCarritoDTO.idCarrito());
        if(optionalCarrito.isEmpty()){
            throw new Exception("No se encontro el carrito con el id: "+eliminarEventoCarritoDTO.idCarrito());
        }

        Carrito carrito = optionalCarrito.get();

        if(carrito.getItems().isEmpty()){
            throw new Exception("El carrito esta vacio");
        }

        carrito.getItems().removeIf((item) -> {
            if(item.getIdEvento().equals(eliminarEventoCarritoDTO.idEvento())){
                return true;
            }else{
                throw new RuntimeException("No se encontro el evento con el id: "+eliminarEventoCarritoDTO.idEvento());
            }
        });

        carritoRepo.save(carrito);
    }

    @Override
    public void limpiarCarrito(String idCarrito) throws Exception {

        Optional<Carrito> optionalCarrito = carritoRepo.findById(idCarrito);
        if(optionalCarrito.isEmpty()){
            throw new Exception("No se encontro el carrito con el id: "+idCarrito);
        }

        Carrito carrito = optionalCarrito.get();
        carrito.getItems().clear();
        carritoRepo.save(carrito);
    }

    @Override
    public CarritoDTO obtenerInformacionCarrito(String idCarrito) throws Exception {
        Optional<Carrito> optionalCarrito = carritoRepo.findById(idCarrito);
        if(optionalCarrito.isEmpty()){
            throw new Exception("No se encontro el carrito con el id: "+idCarrito);
        }

        Carrito carrito = optionalCarrito.get();
        CarritoDTO carritoDTO = new CarritoDTO(
                carrito.getId(),
                carrito.getFecha(),
                carrito.getItems(),
                carrito.getIdUsuario()
        );
        return carritoDTO;
    }

    @Override
    public void actualizarEventoCarrito(ActualizarEventoCarritoDTO actualizarEventoCarritoDTO) throws Exception {
        Optional<Carrito> optionalCarrito = carritoRepo.findById(actualizarEventoCarritoDTO.idCarrito());
        eventoServicio.buscarEventoPorId(actualizarEventoCarritoDTO.idEvento());
        if(optionalCarrito.isEmpty()){
            throw new Exception("No se encontro el carrito con el id: "+actualizarEventoCarritoDTO.idCarrito());
        }

        Carrito carrito = optionalCarrito.get();
        carrito.getItems().forEach((item)->{
            if(item.getIdEvento().equals(actualizarEventoCarritoDTO.idEvento())){
                item.setCantidad(actualizarEventoCarritoDTO.cantidad());
                item.setNombreLocalidad(actualizarEventoCarritoDTO.nombreLocalidad());
            }
        });
        carritoRepo.save(carrito);
    }

    @Override
    public Carrito obtenerCarritoPorId(String idCarrito) throws Exception {
        return carritoRepo.findById(idCarrito).orElseThrow(() -> new Exception("No se encontro el carrito con el id: "+idCarrito));
    }

}
