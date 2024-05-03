package CodeQuatro.Entidades_Cicklum.dtos;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RutinasDTO {

    private Long idRutinas;
    private String nombre;
    private String descripcion; 
    private String observaciones;
    private List<SubEntidadDTO> ejercicios;
    @JsonProperty("_links")
    private Links links;

    public static RutinasDTO fromRutinas(Rutinas rut, 
            Function<Long, URI> rutinaUriBuilder, 
            Function<Long, URI> ejerciciosUriBuilder){
        var dto = new RutinasDTO();
        dto.setIdRutinas(rut.getIdRutinas());
        dto.setNombre(rut.getNombre());
        dto.setDescripcion(rut.getDescripcion());
        dto.setObservaciones(rut.getObservaciones());
        dto.setLinks( 
            Links.builder()
                .self(rutinaUriBuilder.apply(rut.getIdRutinas()))
                .build());
        dto.setEjercicios(rut.getEjercicios().stream()
                    .map(i -> SubEntidadDTO.fromSubEntidad_Ejercicio(i,ejerciciosUriBuilder, rutinaUriBuilder))
                    .collect(Collectors.toList())); 
        return dto;
    }

    public Rutinas rutina(){
        var rut = new Rutinas();
        rut.setIdRutinas(idRutinas);
        rut.setNombre(nombre);
        rut.setDescripcion(descripcion);
        rut.setObservaciones(observaciones);
        rut.setEjercicios(
            Optional.ofNullable(ejercicios)
            .orElse((List<SubEntidadDTO>)Collections.EMPTY_LIST)
            .stream()
                    .map(SubEntidadDTO:: subEntidad)
                    .collect(Collectors.toList())
        );
                
        return rut;
    }
}
