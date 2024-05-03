package CodeQuatro.Entidades_Cicklum.dtos;

import java.net.URI;
import java.util.List;
import java.util.function.Function;

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

    public static SubEntidadDTO fromSubEntidad_Ejercicio(SubEntidad_Ejercicio sub , Function<Long, URI> subUriBuilder){
        var dto = new SubEntidadDTO();
    }
}
