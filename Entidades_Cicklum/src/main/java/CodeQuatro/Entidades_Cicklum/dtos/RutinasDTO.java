package CodeQuatro.Entidades_Cicklum.dtos;

import java.net.URI;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import CodeQuatro.Entidades_Cicklum.entities.FragmentoRutina;
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
    private Long idEntrenador;
    @JsonProperty("_links")
    private Links links;
    private List<FragmentoRutina> ejercicios; // revisar

    public static RutinasDTO fromRutinas(Rutinas rut , Function<Long, URI> rutinaUriBuilder , Function<Long, URI> ejerciciosUriBuilder){
        var dto = new RutinasDTO();
        dto.setIdRutinas(rut.getIdRutinas());
        dto.setNombre(rut.getNombre());
        dto.setDescripcion(rut.getDescripcion());
        dto.setObservaciones(rut.getObservaciones());
        dto.setIdEntrenador(null);
        dto.setLinks( 
            Links.builder()
                .self(rutinaUriBuilder.apply(rut.getIdRutinas()))
                .build());
        dto.setEjercicios(
            rut.getFragmentoRutina().stream()
                .collect(Collectors.toList())); // no se si esta bien
        return dto;
    }

    public Rutinas rutina() {
        var rut = new Rutinas();
        rut.setIdRutinas(idRutinas);
        rut.setNombre(nombre);
        rut.setDescripcion(descripcion);
        rut.setObservaciones(observaciones);
        rut.setIdEntrenador((long) 1);
        
        rut.setFragmentoRutina(ejercicios);
        return rut;
    }

}
