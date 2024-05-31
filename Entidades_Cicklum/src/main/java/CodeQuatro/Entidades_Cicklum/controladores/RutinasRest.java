package CodeQuatro.Entidades_Cicklum.controladores;

import org.springframework.http.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import CodeQuatro.Entidades_Cicklum.dtos.EjerciciosDTO;
import CodeQuatro.Entidades_Cicklum.dtos.RutinasDTO;
import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
import CodeQuatro.Entidades_Cicklum.excepciones.RutinaExistente;
import CodeQuatro.Entidades_Cicklum.excepciones.RutinaNoEncontrada;
import CodeQuatro.Entidades_Cicklum.servicios.LogicaRutinas;

import java.net.URI;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/rutina")
public class RutinasRest {

	private LogicaRutinas servicio;

	public RutinasRest(LogicaRutinas servicioRutinas) {
		this.servicio = servicioRutinas;
	}

	public static Function<Long, URI> rutinasUriBuilder(UriComponents uriBuilder) {
		;
		return id -> UriComponentsBuilder.newInstance().uriComponents(uriBuilder).path("/rutina")
				.path(String.format("/%d", id))
				.build()
				.toUri();
	}

	@GetMapping
	public List<RutinasDTO> obtenerTodasLasRutinas (UriComponentsBuilder uriBuilder) {
		var rutinas = servicio.getTodasRutinas();
		Function<Rutinas, RutinasDTO> mapper = (r -> 
			RutinasDTO.fromRutinas(r, 
					rutinasUriBuilder(uriBuilder.build()), 
					EjerciciosRest.ejerciciosUriBuilder(uriBuilder.build())));
		return rutinas.stream().map(mapper).toList();
	}


	 @PostMapping
	 public ResponseEntity<?> aniadirRutina(@RequestBody RutinasDTO rutina, UriComponentsBuilder uriBuilder) {
	 	Rutinas rut = rutina.rutina();
	 	Long id = servicio.aniadirRutina(rut);
	 	return ResponseEntity.created(
	 			rutinasUriBuilder(uriBuilder.build()).apply(id))
	 			.build();
	}


	 @GetMapping("{id}")
	 public RutinasDTO getRutinaById(@PathVariable Long id, UriComponentsBuilder uriBuilder) {
	 	var rutina = servicio.getRutinasById(id);
        return RutinasDTO.fromRutinas(rutina, rutinasUriBuilder(uriBuilder.build()), EjerciciosRest.ejerciciosUriBuilder(uriBuilder.build()));
    }
	

	@PutMapping("{id}")
	public void modificarRutina(@PathVariable Long id, @RequestBody RutinasDTO rutina) {
		Rutinas entidadRutina = rutina.rutina();
		entidadRutina.setIdRutinas(id);
		servicio.modificarRutina(id,entidadRutina);
	}
	
	@DeleteMapping("{id}")
	public void eliminarRutina(@PathVariable Long id) {
		servicio.eliminarRutina(id);
	}

	@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RutinaNoEncontrada.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleRutinaNoEncontrada(RutinaNoEncontrada ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(ex.getMessage());
    }

   
	
}

}