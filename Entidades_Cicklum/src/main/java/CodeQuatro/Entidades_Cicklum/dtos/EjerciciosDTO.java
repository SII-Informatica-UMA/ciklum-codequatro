package CodeQuatro.Entidades_Cicklum.dtos;

import java.net.URI;
import java.util.List;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonProperty;

import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.var;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EjerciciosDTO {
    private Long idEjercicio;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String musculosTrabajados;
    private String material;
    private String dificultad;
    private List<String> multimedia;
    @JsonProperty("_links")
    private Links links;
    
    public static EjerciciosDTO fromEjercicios(Ejercicios ej , Function<Long, URI> uriBuilder){
        var dto = new EjerciciosDTO();
        dto.setIdEjercicio(ej.getIdEjercicio());
        dto.setNombre(ej.getNombre());
        dto.setDescripcion(ej.getDescripcion());
        dto.setTipo(ej.getTipo());
        dto.setMusculosTrabajados(ej.getMusculosTrabajados());
        dto.setMaterial(ej.getMaterial());
        dto.setDificultad(ej.getDificultad());
        dto.setMultimedia(ej.getMultimedia());
        dto.setLinks(
				Links.builder()
					.self(uriBuilder.apply(ej.getIdEjercicio()))
					.build());
        return dto;
    }


    public Ejercicios ejercicios(){
        var ej = new Ejercicios();
        ej.setIdEjercicio(idEjercicio);
        ej.setNombre(nombre);
        ej.setDescripcion(descripcion);
        ej.setTipo(tipo);
        ej.setMusculosTrabajados(musculosTrabajados);
        ej.setMaterial(material);
        ej.setDificultad(dificultad);
        ej.setMultimedia(multimedia);
        return ej;
    }
}
