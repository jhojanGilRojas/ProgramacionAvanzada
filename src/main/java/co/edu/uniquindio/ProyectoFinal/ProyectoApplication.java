package co.edu.uniquindio.ProyectoFinal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ProyectoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
    }
//
//    @GetMapping ("/hello")
//    public String hello(@RequestParam(value = "name",defaultValue = "JhojanGil")String name){
//        return String.format("<h1>hola %s!</h1> ",name);
//    }


}

