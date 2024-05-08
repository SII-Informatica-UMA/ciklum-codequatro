package CodeQuatro.Entidades_Cicklum.controladores;


import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.hibernate.mapping.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import CodeQuatro.Entidades_Cicklum.dtos.RutinasDTO;
import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
import CodeQuatro.Entidades_Cicklum.servicios.LogicaRutinas;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/rutinas")
public class RutinasRest {
	private LogicaRutinas servicio;

	public RutinasRest(LogicaRutinas servicioRutinas) {
		this.servicio = servicioRutinas;
	}

	@GetMapping
	public List<RutinasDTO> obtenerTodasLasRutinas (UriComponentsBuilder uriBuilder) {
		var rutinas = servicio.getTodasRutinas();
		Function<Rutinas, RutinasDTO> mapper = (p -> 
			RutinasDTO.fromRutinas(p, 
					rutinasUriBuilder(uriBuilder.build()), 
					EjerciciosRest.ejerciciosUriBuilder(uriBuilder.build())));
		return rutinas.stream().map(mapper).toList();
	}

	public static Function<Long, URI> rutinasUriBuilder(UriComponents uriBuilder) {
		;
		return id -> UriComponentsBuilder.newInstance().uriComponents(uriBuilder).path("/rutinas")
				.path(String.format("/%d", id))
				.build()
				.toUri();
	}


	// @PostMapping
	// public ResponseEntity<?> aniadirRutina(@RequestBody RutinasDTO rutina, UriComponentsBuilder uriBuilder) {
	// 	Rutinas rut = rutina.rutina();
	// 	Long id = servicio.aniadirRutina(rut);
	// 	return ResponseEntity.created(
	// 			rutinasUriBuilder(uriBuilder.build()).apply(id))
	// 			.build();
	// }


	// @GetMapping("{id}")
	// @ResponseStatus(code=HttpStatus.OK)
	// public RutinasDTO obtenerProducto(@PathVariable Long id, UriComponentsBuilder uriBuilder) {
	// 	Optional<Rutinas> rutina = servicio.getRutinasById(id);
	// 	return RutinasDTO.fromRutinas(rutina, 
	// 			rutinasUriBuilder(uriBuilder.build()), 
	// 			EjerciciosRest.ejerciciosUriBuilder(uriBuilder.build()));
	// }
	

	@PutMapping("{id}")
	public void modificarRutina(@PathVariable Long id, @RequestBody RutinasDTO rutina) {
		Rutinas entidadRutina = rutina.rutina();
		entidadRutina.setId(id);
		servicio.modificarRutina(id,entidadRutina);
	}
	
	@DeleteMapping("{id}")
	public void eliminarRutina(@PathVariable Long id) {
		servicio.eliminarRutina(id);
	}

	
	
	

	
}

