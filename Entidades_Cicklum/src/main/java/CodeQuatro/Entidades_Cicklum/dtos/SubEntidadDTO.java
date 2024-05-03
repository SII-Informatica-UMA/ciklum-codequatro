package CodeQuatro.Entidades_Cicklum.dtos;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class SubEntidadDTO {
    private Long series;
    private Long repeticiones;
    private Long duracionMinutos;
    private List<RutinasDTO> rutinas;
    @JsonProperty("_links")
    private Links links;

    public static SubEntidadDTO fromSubEntidad_Ejercicio(SubEntidad_Ejercicio sub , Function<Long, URI> subUriBuilder, Function<Long, URI> rutinasUriBuilder){  
        var dto = new SubEntidadDTO();
        dto.setSeries(sub.getSeries());
        dto.setRepeticiones(sub.getRepeticiones());
        dto.setDuracionMinutos(sub.getDuracionMinutos());
        dto.setLinks( 
            Links.builder()
                .self(subUriBuilder.apply(sub.getIdEjercicio()))
                .build());
        dto.setRutinas(
            sub.getRelacionRutina().stream()
                .map(i -> RutinasDTO.fromRutinas(i,rutinasUriBuilder,subUriBuilder))
                .collect(Collectors.toList()));
        return dto;
    }

    public SubEntidad_Ejercicio subEntidad(){
        var sub = new SubEntidad_Ejercicio();
        sub.setSeries(series);
        sub.setRepeticiones(repeticiones);
        sub.setDuracionMinutos(duracionMinutos);
        sub.setRelacionRutina(
            Optional.ofNullable(rutinas)
            .orElse((List<RutinasDTO>)Collections.EMPTY_LIST)
            .stream()
                .map(RutinasDTO::rutina)
                .collect(Collectors.toList())          
        );
        return sub;
    }

    
}
