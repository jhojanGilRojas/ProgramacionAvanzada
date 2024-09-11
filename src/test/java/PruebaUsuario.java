import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@DataMongoTest
@SpringJUnitConfig
public class PruebaUsuario {


    @Test
    public void crearUsuario() {

        Usuario usuario = new Usuario("100","31132","calle 18 # 20","10066","carlos");
    }
}

