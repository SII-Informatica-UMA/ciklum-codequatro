package CodeQuatro.Entidades_Cicklum.controladores;

<<<<<<< Updated upstream
public class EjerciciosRest {
    
=======
import java.net.URI;
import java.util.List;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import CodeQuatro.Entidades_Cicklum.entities.AccesoDatos;
import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;
import CodeQuatro.Entidades_Cicklum.dtos.EjerciciosDTO;
import CodeQuatro.Entidades_Cicklum.servicios.excepciones.*;
import CodeQuatro.Entidades_Cicklum.entities.SubEntidad_Ejercicio;

@RestController
@RequestMapping("/ejercicios")
public class EjerciciosRest {

    private AccesoDatos accesoDatos = new AccesoDatos();

    public EjerciciosRest(AccesoDatos accesoDatos) {
    }
>>>>>>> Stashed changes
}
