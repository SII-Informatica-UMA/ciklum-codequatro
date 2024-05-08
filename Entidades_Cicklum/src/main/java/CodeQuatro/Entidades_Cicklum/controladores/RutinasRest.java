package CodeQuatro.Entidades_Cicklum.controladores;


import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.hibernate.mapping.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import CodeQuatro.Entidades_Cicklum.dtos.RutinasDTO;
import CodeQuatro.Entidades_Cicklum.servicios.LogicaRutinas;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class RutinasRest {
	private LogicaRutinas servicio;

	public RutinasRest(LogicaRutinas servicioRutinas) {
		servicio = servicioRutinas;
	}

	
}

