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
import CodeQuatro.Entidades_Cicklum.servicios.LogicaEjercicios;
import CodeQuatro.Entidades_Cicklum.servicios.LogicaRutinas;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/ejericios")
public class EjerciciosRest {
	private LogicaEjercicios servicio;

	public EjerciciosRest() {
	}

	

	public static Function<Long, URI> ejerciciosUriBuilder(UriComponents uriBuilder) {
		;
		return id -> UriComponentsBuilder.newInstance().uriComponents(uriBuilder).path("/ejercicios")
				.path(String.format("/%d", id))
				.build()
				.toUri();
	}

	
}

