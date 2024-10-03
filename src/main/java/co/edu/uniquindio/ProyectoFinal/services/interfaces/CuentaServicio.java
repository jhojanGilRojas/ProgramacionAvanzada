package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.dto.cuenta.*;
import co.edu.uniquindio.ProyectoFinal.model.documents.Cuenta;

public interface CuentaServicio {


    String crearCuenta(CrearCuentaDTO cuenta) throws Exception;

    String editarCuenta(EditarCuentaDTO cuenta) throws Exception;

    String eliminarCuenta(String id) throws Exception;

    InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception;

    String enviarCodigoRecuperacionPassword(String correo) throws Exception;

    String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception;

    String iniciarSesion(LoginDTO loginDTO) throws Exception;

    public Cuenta obtenerPorID(String idCuenta) throws Exception;

}
