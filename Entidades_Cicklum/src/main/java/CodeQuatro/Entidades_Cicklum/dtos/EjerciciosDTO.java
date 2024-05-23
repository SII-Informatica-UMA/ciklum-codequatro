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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EjerciciosDTO extends EjercicioNuevoDTO{
    private Long idEjercicio;
    @JsonProperty("_links")
    private Links links;


    @Builder
    public EjerciciosDTO (Long idEjercicio, String nombre, String descripcion, String observaciones, String tipo, String musculosTrabajados, String material, String dificultad, List<String> multimedia, Long idEntrenador, Links links){
        super(nombre, descripcion, observaciones, tipo, musculosTrabajados, material, dificultad, multimedia, idEntrenador);
        this.idEjercicio = idEjercicio;
        this.links = links;
    }

    public static EjerciciosDTO fromEntity(Ejercicios ejercicios){
        return EjerciciosDTO.builder()
        .idEjercicio(ejercicios.getIdEjercicio())
        .nombre(ejercicios.getNombre())
        .descripcion(ejercicios.getDescripcion())
        .observaciones(ejercicios.getObservaciones())
        .tipo(ejercicios.getTipo())
        .musculosTrabajados(ejercicios.getMusculosTrabajados())
        .material(ejercicios.getMaterial())
        .dificultad(ejercicios.getDificultad())
        .multimedia(ejercicios.getMultimedia())
        .idEntrenador(ejercicios.getIdEntrenador())
        .build();
    }

    public Ejercicios toEntity(){
        return Ejercicios.builder()
        .idEjercicio(this.idEjercicio)
        .nombre(this.getNombre())
        .descripcion(this.getDescripcion())
        .observaciones(this.getObservaciones())
        .tipo(this.getTipo())
        .musculosTrabajados(this.getMusculosTrabajados())
        .material(this.getMaterial())
        .dificultad(this.getDificultad())
        .multimedia(this.getMultimedia())
        .idEntrenador(this.getIdEntrenador())
        .build();
    }

    public Long getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(Long idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    
    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}