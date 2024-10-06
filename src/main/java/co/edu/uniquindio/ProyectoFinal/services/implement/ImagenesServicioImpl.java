package co.edu.uniquindio.ProyectoFinal.services.implement;

import co.edu.uniquindio.ProyectoFinal.services.interfaces.ImagenesServicio;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@Service
public class ImagenesServicioImpl implements ImagenesServicio {

    @Override
    public String subirImagen(MultipartFile multipartFile) throws Exception{
        Bucket bucket = StorageClient.getInstance().bucket();


        String fileName = String.format( "%s-%s", UUID.randomUUID().toString(), multipartFile.getOriginalFilename() );


        Blob blob = bucket.create( fileName, multipartFile.getInputStream(), multipartFile.getContentType() );


        return String.format(
                "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                bucket.getName(),
                blob.getName()
        );
    }


    @Override
    public void eliminarImagen(String nombreImagen) throws Exception{
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.get(nombreImagen);
        blob.delete();
    }

}
