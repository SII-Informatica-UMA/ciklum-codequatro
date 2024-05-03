package CodeQuatro.Entidades_Cicklum.dtos;

import java.net.URI;
import java.util.List;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonProperty;

import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
import CodeQuatro.Entidades_Cicklum.entities.SubEntidad_Ejercicio;
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
    private List<SubEntidad_Ejercicio> ejercicios;
    @JsonProperty("_links")
    private Links links;

    public static RutinasDTO fromRutinas(Rutinas rut , Function<Long, URI> rutinaUriBuilder , Function<Long, URI> ejerciciosUriBuilder){
        var dto = new RutinasDTO();
        dto.setIdRutinas(rut.getIdRutinas());
        dto.setNombre(rut.getNombre());
        dto.setDescripcion(rut.getDescripcion());
        dto.setObservaciones(rut.getObservaciones());
        dto.setEjercicios(rut.getEjercicios());
        dto.setLinks( 
            Links.builder()
                .self(rutinaUriBuilder.apply(rut.getIdRutinas()))
                .build());
        return dto;
    }
}
