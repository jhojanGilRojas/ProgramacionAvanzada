package co.edu.uniquindio.ProyectoFinal.services.implement;

import co.edu.uniquindio.ProyectoFinal.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.ProyectoFinal.dto.orden.InformacionOrdenDTO;
import co.edu.uniquindio.ProyectoFinal.model.DetalleOrden;
import co.edu.uniquindio.ProyectoFinal.model.Localidad;
import co.edu.uniquindio.ProyectoFinal.model.Pago;
import co.edu.uniquindio.ProyectoFinal.model.documents.Carrito;
import co.edu.uniquindio.ProyectoFinal.model.documents.Cupon;
import co.edu.uniquindio.ProyectoFinal.model.documents.Evento;
import co.edu.uniquindio.ProyectoFinal.model.documents.Orden;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoCupon;
import co.edu.uniquindio.ProyectoFinal.repositories.OrdenRepo;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CarritoServicio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.CuponServicio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.EventoServicio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.OrdenServicio;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.resources.preference.PreferenceItem;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrdenServicioImpl  implements OrdenServicio {

    private final OrdenRepo ordenRepo;
    private final CuponServicio cuponServicio;
    private final CarritoServicio carritoServicio;
    private final EventoServicio eventoServicio;


    @Override
    public String crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception {
        Cupon cupon = cuponServicio.obtenerCuponPorCodigo(crearOrdenDTO.codigoCupon());
        Carrito carrito = carritoServicio.obtenerCarritoPorId(crearOrdenDTO.idCarrito());

        Orden orden = new Orden();
        orden.setIdCliente(crearOrdenDTO.idUser());
        orden.setFecha(LocalDateTime.now());

        if(validarCupon(cupon)){
            throw new Exception("El cupon no es valido");
        }
        orden.setIdCupon(crearOrdenDTO.codigoCupon());

        carrito.getItems().forEach(item -> {
            DetalleOrden detalleOrden = new DetalleOrden();
            detalleOrden.setIdEvento(item.getIdEvento());
            detalleOrden.setCantidad(item.getCantidad());
            detalleOrden.setNombreLocalidad(item.getNombreLocalidad());
            //buscar el evento, buscar la localidad y obtener el precio
            Evento evento = null;
            try {
                evento = eventoServicio.obtenerPorID(item.getIdEvento());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            evento.getLocalidades().forEach(localidad -> {
                if(localidad.getNombre().equals(item.getNombreLocalidad())){
                    detalleOrden.setPrecio(localidad.getPrecio());
                }
            });
            orden.getItems().add(detalleOrden);
        });

        float total = 0;
        for(DetalleOrden detalleOrden : orden.getItems()){
            total += detalleOrden.getPrecio() * detalleOrden.getCantidad();
        }
        total = total - cupon.getDescuento();
        orden.setTotal(total);
        ordenRepo.save(orden);
        return "Se ha creado la orden";
    }

    @Override
    public String cancelarOrden(String id) throws Exception {
        return "";
    }

    @Override
    public InformacionOrdenDTO obtenerInformacionOrden(String id) throws Exception {
        return null;
    }

    @Override
    public void recibirNotificacionMercadoPago(Map<String, Object> request) {
        try {
            Object tipo = request.get("type");

            // Si la notificación es de un pago entonces obtener el pago y la orden asociada
            if ("payment".equals(tipo)) {

                // Capturamos el JSON que viene en el request y lo convertimos a un String
                String input = request.get("data").toString();

                // Extraemos los números de la cadena, es decir, el id del pago
                String idPago = input.replaceAll("\\D+", "");

                // Se crea el cliente de MercadoPago y se obtiene el pago con el id
                PaymentClient client = new PaymentClient();
                Payment payment = client.get( Long.parseLong(idPago) );

                // Obtener el id de la orden asociada al pago que viene en los metadatos
                String idOrden = payment.getMetadata().get("id_orden").toString();

                // Se obtiene la orden guardada en la base de datos y se le asigna el pago
                Orden orden = obtenerOrden(idOrden);
                Pago pago = crearPago(payment);
                orden.setPago(pago);
                ordenRepo.save(orden);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    private Pago crearPago(Payment payment) {
        Pago pago = new Pago();
        pago.setCodigo(payment.getId().toString());
        pago.setFecha( payment.getDateCreated().toLocalDateTime() );
        pago.setEstado(payment.getStatus());
        pago.setDetalleEstado(payment.getStatusDetail());
        pago.setTipoPago(payment.getPaymentTypeId());
        pago.setMoneda(payment.getCurrencyId());
        pago.setCodigoAutorizacion(payment.getAuthorizationCode());
        pago.setValorTransaccion(payment.getTransactionAmount().floatValue());
        return pago;
    }


    @Override
    public Preference realzarPagoOrden(String idOrden) throws Exception {
        Orden ordenGuardada = obtenerOrden(idOrden);
        List<PreferenceItemRequest> itemsPasarela = new ArrayList<>();

        for(DetalleOrden detalleOrden : ordenGuardada.getItems()){
            Evento evento = eventoServicio.obtenerPorID(detalleOrden.getIdEvento());
            Localidad localidad = evento.obtenerLocalidad(detalleOrden.getNombreLocalidad());

            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(evento.getIdEvento())
                    .title(evento.getNombre())
                    .pictureUrl(evento.getImagenPortada())
                    .categoryId(evento.getTipo().name())
                    .quantity(detalleOrden.getCantidad())
                    .currencyId("COP")
                    .unitPrice(BigDecimal.valueOf(localidad.getPrecio()))
                    .build();

            itemsPasarela.add(itemRequest);

        }

        MercadoPagoConfig.setAccessToken("ACCESS TOKEN");

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("URL PAGO EXITOSO")
                .failure("URL PAGO FALLIDO")
                .pending("URL PAGO PENDIENTE")
                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .backUrls(backUrls)
                .items(itemsPasarela)
                .metadata(Map.of("id_orden", ordenGuardada.getIdOrden()))
                .notificationUrl("URL NOTIFICACION")
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        ordenGuardada.setCodigoPasarela( preference.getId() );
        ordenRepo.save(ordenGuardada);

        return preference;

    }

    @Override
    public void obtenerOrdenes(String idUsuario) {
        ordenRepo.findAll();

    }

    private boolean validarCupon(Cupon cupon){
        if(cupon.getEstadoCupon().equals(EstadoCupon.NO_DISPONIBLE)) return true;
        if(cupon.getFechaVencimiento().isAfter(LocalDateTime.now())) return true;
        return false;
    }

    private Orden obtenerOrden(String idOrden){
        Optional<Orden> orden = ordenRepo.findById(idOrden);
        if(orden.isEmpty()){
            throw new RuntimeException("La orden no existe");
        }
        return orden.get();
    }

}
