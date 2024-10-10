package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.model.documents.Cupon;

import java.util.Optional;


public interface CuponServicio {
    public Cupon obtenerCupon(String codigoCupon) throws Exception;
    public Cupon obtenerCuponPorCodigo(String codigoCupon) throws Exception;
}
