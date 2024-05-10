package CodeQuatro.Entidades_Cicklum.controladores;

import java.net.URI;
import java.util.List;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import CodeQuatro.Entidades_Cicklum.dtos.EjerciciosDTO;
import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;
import CodeQuatro.Entidades_Cicklum.servicios.LogicaEjercicios;
import CodeQuatro.Entidades_Cicklum.servicios.excepciones.EjercicioExistenteException;
import CodeQuatro.Entidades_Cicklum.servicios.excepciones.EjercicioNoEncontradoException;

@RestController
@RequestMapping("/ejercicios")
public class EjerciciosRest {

    private final LogicaEjercicios logicaEjercicios;

    public EjerciciosRest(LogicaEjercicios logicaEjercicios) {
        this.logicaEjercicios = logicaEjercicios;
    }

    @GetMapping
    public List<Ejercicios> getTodosEjercicios(UriComponentsBuilder uriBuilder) {
        return logicaEjercicios.getTodosEjercicios();
    }

    public static Function<Long, URI> ejercicioUriBuilder(UriComponents uriComponents) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance().uriComponents(uriComponents);
        return id -> uriBuilder.path("/ejercicios/" + id).build().toUri();

}

    @PostMapping
    public ResponseEntity<?> aniadirEjercicio(@RequestBody EjerciciosDTO ejercicio, UriComponentsBuilder uriBuilder){
        Long id = logicaEjercicios.aniadirEjercicio(ejercicio.ejercicios());
        return ResponseEntity.created(ejercicioUriBuilder(uriBuilder.build()).apply(id)).build();
    }

    @GetMapping("/{id}")
    public EjerciciosDTO getEjercicioById(@PathVariable Long id, UriComponentsBuilder uriBuilder){
        var ejercicio = logicaEjercicios.getEjercicioById(id);
        return EjerciciosDTO.fromEjercicios(ejercicio, ejercicioUriBuilder(uriBuilder.build()));
    }

    @PutMapping("/{id}")
    public void actualizarEjercicio(@PathVariable Long id, @RequestBody EjerciciosDTO ejercicio){
        Ejercicios ej = ejercicio.ejercicios();
        ejercicio.setIdEjercicio(id);
        logicaEjercicios.actualizarEjercicio(ej);
    }

    @DeleteMapping("/{id}")
    public void eliminarEjercicio(@PathVariable Long id){
        logicaEjercicios.eliminarEjercicio(id);
    }

    @ExceptionHandler(EjercicioNoEncontradoException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public void noEncontrado() {}

    @ExceptionHandler(EjercicioExistenteException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public void existente() {}
}