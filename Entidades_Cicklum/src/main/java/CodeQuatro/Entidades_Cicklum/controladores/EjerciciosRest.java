package CodeQuatro.Entidades_Cicklum.controladores;

import java.net.URI;
import java.util.List;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import CodeQuatro.Entidades_Cicklum.dtos.EjercicioNuevoDTO;
import CodeQuatro.Entidades_Cicklum.dtos.EjerciciosDTO;
import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;
import CodeQuatro.Entidades_Cicklum.excepciones.EjercicioNoEncontradoException;
import CodeQuatro.Entidades_Cicklum.servicios.LogicaEjercicios;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping({ "/ejercicios" })

public class EjerciciosRest {
    private LogicaEjercicios logicaEjercicios;

    public EjerciciosRest(LogicaEjercicios logicaEjercicios) {
        this.logicaEjercicios = logicaEjercicios;
    }
    public static Function<Long, URI> ejerciciosUriBuilder(UriComponents uriBuilder) {
		;
		return id -> UriComponentsBuilder.newInstance().uriComponents(uriBuilder).path("/ejercicios")
				.path(String.format("/%d", id))
				.build()
				.toUri();
	}

    @GetMapping
    @Operation(description = "Permite consultar todos los ejercicios a un entrenador. Solo lo puede hacer el entrenador.", responses = {
        @ApiResponse(responseCode = "200", description = "Devuelve la lista de ejercicios de un entrenador."),
        @ApiResponse(responseCode = "403", description = "Acceso no autorizado", content = {
            @Content(schema = @Schema(implementation = Void.class)) }) })        
    public List<EjerciciosDTO> obtenerEjercicios(
        @RequestParam(value = "entrenador", required = false) Long idEntrenador) {
        return this.logicaEjercicios.obtenerEjercicios(idEntrenador).stream().map(EjerciciosDTO::fromEntity).toList();
    }

    @PostMapping
    @Operation(description = "Permite crear un ejercicio nuevo a un entrenador. ", responses = {
            @ApiResponse(responseCode = "201", description = "Se crea el ejercicio y lo devuelve", headers = {
                    @Header(name = "Location", description = "URI del nuevo recurso", schema = @Schema(type = "string", subTypes = {
                            URI.class })) }),
            @ApiResponse(responseCode = "403", description = "Acceso no autorizado", content = {
                    @Content(schema = @Schema(implementation = Void.class)) }) })
    public ResponseEntity<EjerciciosDTO> crearEjercicio(
            @RequestParam(value = "entrenador", required = true) Long idEntrenador,
            @RequestBody EjercicioNuevoDTO ejercicioNuevoDTO, UriComponentsBuilder uriBuilder) {
        Ejercicios g = ejercicioNuevoDTO.toEntity();
        g.setIdEjercicio((Long) null);
        g.setIdEntrenador(idEntrenador);
        g = this.logicaEjercicios.crearActualizarEjercicio(g);
        return ResponseEntity.created((URI) this.generadorUri(uriBuilder.build()).apply(g))
                .body(EjerciciosDTO.fromEntity(g));
    }

    private Function<Ejercicios, URI> generadorUri(UriComponents uriBuilder) {
        return (g) -> {
            return UriComponentsBuilder.newInstance().uriComponents(uriBuilder).path("/ejercicio")
                    .path(String.format("/%d", g.getIdEjercicio())).build().toUri();
        };
    }

    @GetMapping({ "/{idEjercicio}" })
    @Operation(description = "Obtiene un ejercicio concreto. Sollo pued ehacerlo el entrenador que lo ha creado y los clientes que entrena.", responses = {
            @ApiResponse(responseCode = "200", description = "El ejercicio existe"),
            @ApiResponse(responseCode = "404", description = "El ejercicio no existe", content = {
                    @Content(schema = @Schema(implementation = Void.class)) }),
            @ApiResponse(responseCode = "403", description = "Acceso no autorizado", content = {
                    @Content(schema = @Schema(implementation = Void.class)) }) })
    public ResponseEntity<EjerciciosDTO> getEjercicio(@PathVariable Long idEjercicio) {
        return ResponseEntity.of(this.logicaEjercicios.obtenerEjercicio(idEjercicio).map(EjerciciosDTO::fromEntity));
    }

    @PutMapping({ "/{idEjercicio}" })
    @Operation(description = "Actualiza un ejercicio. Solo puede hacerlo el entrenador que lo ha creado.", responses = {
            @ApiResponse(responseCode = "200", description = "El ejercicio se ha actualizado"),
            @ApiResponse(responseCode = "404", description = "El ejercicio no existe", content = {
                    @Content(schema = @Schema(implementation = Void.class)) }),
            @ApiResponse(responseCode = "403", description = "Acceso no autorizado", content = {
                    @Content(schema = @Schema(implementation = Void.class)) }) })
    public EjerciciosDTO actualizarEjercicio(@PathVariable Long idEjercicio, @RequestBody EjerciciosDTO ejercicio) {
        Ejercicios original = (Ejercicios) this.logicaEjercicios.obtenerEjercicio(idEjercicio).orElseThrow(() -> {
            return new EjercicioNoEncontradoException();
        });
        Ejercicios g = ejercicio.toEntity();
        g.setIdEjercicio(idEjercicio);
        g.setIdEntrenador(original.getIdEntrenador());
        g = this.logicaEjercicios.crearActualizarEjercicio(g);
        return EjerciciosDTO.fromEntity(g);
    }

    @DeleteMapping({ "/{idEjercicio}" })
    @Operation(description = "Elimina el ejercicio. Solo puede hacerlo el entrenador que lo ha creado.", responses = {
            @ApiResponse(responseCode = "200", description = "El ejercicio se ha elminado"),
            @ApiResponse(responseCode = "404", description = "El ejercicio no existe", content = {
                    @Content(schema = @Schema(implementation = Void.class)) }),
            @ApiResponse(responseCode = "417", description = "El ejercicio aparece en una rutina y no se puede eliminar", content = {
                    @Content(schema = @Schema(implementation = Void.class)) }),
            @ApiResponse(responseCode = "403", description = "Acceso no autorizado", content = {
                    @Content(schema = @Schema(implementation = Void.class)) }) })
    public ResponseEntity<Void> eliminarEjercicio(@PathVariable Long idEjercicio) {
        this.logicaEjercicios.obtenerEjercicio(idEjercicio).orElseThrow(EjercicioNoEncontradoException::new);

        try {
            this.logicaEjercicios.eliminarEjercicio(idEjercicio);
        } catch (EjercicioNoEncontradoException var3) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({ EjercicioNoEncontradoException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void ejercicioNoEncontrado() {
    }
}