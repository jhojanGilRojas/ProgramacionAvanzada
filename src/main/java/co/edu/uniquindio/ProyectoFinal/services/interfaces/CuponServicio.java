package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.dto.cupon.*;
import co.edu.uniquindio.ProyectoFinal.model.documents.Cupon;

import java.util.List;

public interface CuponServicio {

    String validarCupon (AplicarCuponDTO aplicarCuponDTO) throws Exception;

    String crearCupon (CrearCuponDTO crearCuponDTO) throws Exception;

    String crearCuponUnico (CrearCuponUnicoDTO crearCuponUnicoDTO) throws Exception;

    String EditarCupon (EditarCuponDTO editarCuponDTO) throws Exception;

    CuponInfoDTO buscarCuponPorCodigo(String codigo) throws Exception;

    List<CuponInfoDTO> listarTodosLosCupones() throws Exception;
    public Cupon obtenerCupon(String codigoCupon) throws Exception;
    public Cupon obtenerCuponPorCodigo(String codigoCupon) throws Exception;
}
