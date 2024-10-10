package co.edu.uniquindio.ProyectoFinal.services.implement;

import co.edu.uniquindio.ProyectoFinal.model.documents.Cupon;
import co.edu.uniquindio.ProyectoFinal.repositories.CuponRepo;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CuponServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CuponServicioImpl implements CuponServicio {

    private final CuponRepo cuponRepo;


    @Override
    public Cupon obtenerCupon(String idCupon) throws Exception {
        return cuponRepo.findById(idCupon).orElseThrow(() -> new Exception("El cupon no existe"));
    }

    @Override
    public Cupon obtenerCuponPorCodigo(String codigoCupon) throws Exception{
        return cuponRepo.findByCodigoCupon(codigoCupon).orElseThrow(() -> new Exception("El cupon no es valido"));
    }
}
