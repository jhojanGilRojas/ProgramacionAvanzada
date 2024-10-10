package co.edu.uniquindio.ProyectoFinal.services.implement;

import co.edu.uniquindio.ProyectoFinal.dto.cupon.*;
import co.edu.uniquindio.ProyectoFinal.model.documents.Cupon;
import co.edu.uniquindio.ProyectoFinal.model.documents.Orden;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoCupon;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoCupon;
import co.edu.uniquindio.ProyectoFinal.repositories.CuentaRepo;
import co.edu.uniquindio.ProyectoFinal.repositories.CuponRepo;
import co.edu.uniquindio.ProyectoFinal.repositories.OrdenRepo;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CuponServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class  CuponServicioImpl implements CuponServicio {

    private final CuponRepo cuponRepo;
    private final OrdenRepo ordenRepo;

    //En este metodo la idea en el momento que se desee redimir el cupón crear el
    // AplicarCuponDTO desde el metodo de la orden
    @Override
    public Boolean validarCupon(AplicarCuponDTO aplicarCuponDTO) throws Exception {

        Optional<Orden> orden = ordenRepo.findByIdClienteAndIdCupon(aplicarCuponDTO.idCuenta(), aplicarCuponDTO.codigoCupon());
        Optional<Cupon>cuponOptional = cuponRepo.findByCodigo(aplicarCuponDTO.codigoCupon());
        Cupon cupon = cuponOptional.get();

        if (cuponOptional.isEmpty()){
            throw new Exception("No exite un cupón con este codigo");
        }
        if (cupon.getFecha().isBefore(LocalDateTime.now())){
            throw new Exception("El cupón ya expiro");
        }
        if (orden.isPresent()) {
            throw new Exception("El cupón ya ha sido redimido por este cliente.");
        }
        return true;
    }

    @Override
    public String crearCupon(CrearCuponDTO crearCuponDTO) throws Exception {

        Optional<Cupon>cuponOptional = cuponRepo.findByCodigo(crearCuponDTO.codigo());
        if (cuponOptional.isPresent()){
            throw new Exception("Ya exite un cupon con este codigo");
        }
        Cupon cupon = new Cupon();

        cupon.setCodigo(crearCuponDTO.codigo());
        cupon.setNombre(crearCuponDTO.nombre());
        cupon.setTipoCupon(TipoCupon.MULTIPLE);
        cupon.setEstadoCupon(EstadoCupon.DISPONIBLE);
        cupon.setDescuento(crearCuponDTO.descuento());
        cupon.setFecha(LocalDateTime.now());
        cuponRepo.save(cupon);

        return "El cupon ha sido creado con exito";
    }

    @Override
    public String crearCuponUnico(CrearCuponUnicoDTO crearCuponUnicoDTO) throws Exception {

        Optional<Cupon>cuponOptional = cuponRepo.findByCodigo(crearCuponUnicoDTO.codigo());
        if (cuponOptional.isPresent()){
            throw new Exception("Ya exite un cupon con este codigo");
        }
        Cupon cupon = new Cupon();

        cupon.setCodigo(crearCuponUnicoDTO.codigo());
        cupon.setNombre(crearCuponUnicoDTO.nombre());
        cupon.setTipoCupon(TipoCupon.MULTIPLE);
        cupon.setEstadoCupon(EstadoCupon.DISPONIBLE);
        cupon.setDescuento(crearCuponUnicoDTO.descuento());
        cupon.setFecha(LocalDateTime.now());
        cuponRepo.save(cupon);

        return "El cupon ha sido creado con exito";
    }

    @Override
    public String EditarCupon(EditarCuponDTO editarCuponDTO) throws Exception {

        Optional<Cupon> cuponOptional = cuponRepo.findByCodigo(editarCuponDTO.idCupon());
        if (cuponOptional.isEmpty()){
            throw new Exception("No existe ningún cupon con ese codigo");
        }

        Cupon cupon = cuponOptional.get();
        cupon.setNombre(editarCuponDTO.nombre());
        cupon.setDescuento(editarCuponDTO.descuento());

        return "El cupon ha sido editado correctamente";
    }

    @Override
    public CuponInfoDTO buscarCuponPorCodigo(String codigo) throws Exception {

        Optional<Cupon> cuponOptional = cuponRepo.findByCodigo(codigo);
        if (cuponOptional.isEmpty()){

            throw new Exception("El cupon no existe");
        }

        Cupon cupon = cuponOptional.get();

        CuponInfoDTO cuponInfoDTO = new CuponInfoDTO(
                cupon.getCodigo(),
                cupon.getNombre(),
                cupon.getTipoCupon(),
                cupon.getFecha(),
                cupon.getDescuento()
        );

        return cuponInfoDTO;
    }

    @Override
    public List<CuponInfoDTO> listarTodosLosCupones() throws Exception {

        List<Cupon>cupones = cuponRepo.findAll();

        if (cupones.isEmpty()){
            throw new Exception("No hay cupones creados");
        }

        List<CuponInfoDTO> items= new ArrayList<>();

        for (Cupon cupon:cupones){
            items.add(new CuponInfoDTO(
                    cupon.getCodigo(),
                    cupon.getNombre(),
                    cupon.getTipoCupon(),
                    cupon.getFecha(),
                    cupon.getDescuento()
            ));
        }

        return items;
    }

}
