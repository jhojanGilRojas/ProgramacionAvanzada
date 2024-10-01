package co.edu.uniquindio.ProyectoFinal.services.implement;

import co.edu.uniquindio.ProyectoFinal.dto.carrito.DetalleCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.dto.carrito.EliminarEventoCarritoDTO;
import co.edu.uniquindio.ProyectoFinal.model.DetalleCarrito;
import co.edu.uniquindio.ProyectoFinal.model.documents.Carrito;
import co.edu.uniquindio.ProyectoFinal.model.documents.Cuenta;
import co.edu.uniquindio.ProyectoFinal.repositories.CarritoRepo;
import co.edu.uniquindio.ProyectoFinal.repositories.CuentaRepo;
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
    private final CuentaRepo cuentaRepo;

    public CarritoServicioImpl(CarritoRepo carritoRepo, CuentaRepo cuentaRepo) {
        this.carritoRepo = carritoRepo;
        this.cuentaRepo = cuentaRepo;
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
    public String agregarProductoCarrito(DetalleCarritoDTO detalle) throws Exception {
        Optional<Carrito> optionalCarrito = carritoRepo.findById(detalle.idCarrito());
        if(optionalCarrito.isEmpty()){
            throw new Exception("No se encontro el carrito con el id: "+detalle.idCarrito());
        }
        Carrito carrito = optionalCarrito.get();

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
    public String eliminarProductoCarrito(EliminarEventoCarritoDTO eliminarEventoCarritoDTO) throws Exception {
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
    public String limpiarCarrito(String idUsuario) throws Exception {
        return "";
    }

    @Override
    public String obtenerCarrito(String idUsuario) throws Exception {
        return "";
    }

    @Override
    public String obtenerInformacionCarrito(String idUsuario) throws Exception {
        return "";
    }

    @Override
    public String actualizarCarrito(String id, String idUsuario) throws Exception {
        return "";
    }
}
