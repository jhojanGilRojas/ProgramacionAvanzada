package co.edu.uniquindio.ProyectoFinal.services.implement;

import co.edu.uniquindio.ProyectoFinal.dto.carrito.ActualizarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.CarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.DetalleCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.EliminarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.model.DetalleCarrito;
import co.edu.uniquindio.ProyectoFinal.model.documents.Carrito;
import co.edu.uniquindio.ProyectoFinal.model.documents.Cuenta;
import co.edu.uniquindio.ProyectoFinal.repositories.CarritoRepo;
import co.edu.uniquindio.ProyectoFinal.repositories.CuentaRepo;
import co.edu.uniquindio.ProyectoFinal.repositories.EventoRepo;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CarritoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class CarritoServicioImpl implements CarritoServicio {

    private final CarritoRepo carritoRepo;
    private final EventoRepo eventoRepo;
    private final CuentaRepo cuentaRepo;

    public CarritoServicioImpl(CarritoRepo carritoRepo, CuentaRepo cuentaRepo, EventoRepo  eventoRepo) {
        this.carritoRepo = carritoRepo;
        this.cuentaRepo = cuentaRepo;
        this.eventoRepo = eventoRepo;
    }

    @Override
    public String crearCarrito(String idUsuario) throws Exception {
        Optional<Cuenta> optionalCuenta = cuentaRepo.findById(idUsuario);

        if(optionalCuenta.isEmpty()){
            throw new Exception("No se encontro la cuenta con el id: "+idUsuario);
        }

        Carrito carrito = new Carrito();
        carrito.setIdUsuario(idUsuario);
        carrito.setFecha(LocalDateTime.now());
        carrito.setItems(new ArrayList<>());
        carritoRepo.save(carrito);
        return "Se ha creado el carrito de compras con el id: ";
    }

    @Override
    public String agregarEventoCarrito(DetalleCarritoDTO detalle) throws Exception {
        Optional<Carrito> optionalCarrito = carritoRepo.findById(detalle.idCarrito());
        if(optionalCarrito.isEmpty()){
            throw new Exception("No se encontro el carrito con el id: "+detalle.idCarrito());
        }
        Carrito carrito = optionalCarrito.get();

        if(!existeEvento(detalle.idEvento())){
            throw new Exception("No se encontro el evento con el id: "+detalle.idEvento());
        }

        carrito.getItems().forEach((item)->{
            if(item.getIdEvento().equals(detalle.idEvento())){
                throw new RuntimeException("El evento ya se encuentra en el carrito");
            }
        });

        DetalleCarrito detalleCarrito = new DetalleCarrito();
        detalleCarrito.setCantidad(detalle.cantidad());
        detalleCarrito.setIdEvento(detalle.idEvento());
        detalleCarrito.setNombreLocalidad(detalle.nombreLocalidad());
        carrito.getItems().add(detalleCarrito);
        carritoRepo.save(carrito);

        return "Se ha añadido el item al carrito";
    }

    @Override
    public String eliminarEventoCarrito(EliminarEventoCarritoDTO eliminarEventoCarritoDTO) throws Exception {
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
        return "Se ha eliminado el evento del carrito correctamente";
    }

    @Override
    public String limpiarCarrito(String idCarrito) throws Exception {

        Optional<Carrito> optionalCarrito = carritoRepo.findById(idCarrito);
        if(optionalCarrito.isEmpty()){
            throw new Exception("No se encontro el carrito con el id: "+idCarrito);
        }

        Carrito carrito = optionalCarrito.get();
        carrito.getItems().clear();
        carritoRepo.save(carrito);

        return "Se ha vaciado el carrito de compras correctamente";
    }

    @Override
    public String obtenerInformacionCarrito(String idCarrito) throws Exception {
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
        return ""+carritoDTO;
    }

    @Override
    public String actualizarEventoCarrito(ActualizarEventoCarritoDTO actualizarEventoCarritoDTO) throws Exception {
        Optional<Carrito> optionalCarrito = carritoRepo.findById(actualizarEventoCarritoDTO.idCarrito());

        if(optionalCarrito.isEmpty()){
            throw new Exception("No se encontro el carrito con el id: "+actualizarEventoCarritoDTO.idCarrito());
        }

        if(!existeEvento(actualizarEventoCarritoDTO.idEvento())){
            throw new Exception("No se encontro el evento con el id: "+actualizarEventoCarritoDTO.idEvento());
        }

        Carrito carrito = optionalCarrito.get();
        carrito.getItems().forEach((item)->{
            if(item.getIdEvento().equals(actualizarEventoCarritoDTO.idEvento())){
                item.setCantidad(actualizarEventoCarritoDTO.cantidad());
                item.setNombreLocalidad(actualizarEventoCarritoDTO.localidad());
            }
        });
        carritoRepo.save(carrito);
        return "Se ha actualizado el evento del carrito correctamente";
    }

    private boolean existeEvento(String idEvento){
        return eventoRepo.buscarEvento(idEvento).isPresent();
    }

}
