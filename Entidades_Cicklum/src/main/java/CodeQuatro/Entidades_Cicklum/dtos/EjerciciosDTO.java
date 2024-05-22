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
public class EjerciciosDTO {
    private Long idEjercicio;
    private String nombre;
    private String descripcion;
    private String observaciones;
    private String tipo;
    private String musculosTrabajados;
    private String material;
    private String dificultad;
    private List<String> multimedia;
    @JsonProperty("_links")
    private Links links;
    private Long idEntrenador;
    
    public static EjerciciosDTO fromEjercicios(Ejercicios ej , Function<Long, URI> uriBuilder){
        EjerciciosDTO dto = new EjerciciosDTO();
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
        Ejercicios ej = new Ejercicios();
        ej.setIdEjercicio(idEjercicio);
        ej.setNombre(nombre);
        ej.setDescripcion(descripcion);
        ej.setTipo(tipo);
        ej.setMusculosTrabajados(musculosTrabajados);
        ej.setMaterial(material);
        ej.setDificultad(dificultad);
        ej.setMultimedia(multimedia);
        ej.setIdEntrenador(idEntrenador);
        return ej;
    }

    public Long getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(Long idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public Long getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(Long idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMusculosTrabajados() {
        return musculosTrabajados;
    }

    public void setMusculosTrabajados(String musculosTrabajados) {
        this.musculosTrabajados = musculosTrabajados;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public List<String> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<String> multimedia) {
        this.multimedia = multimedia;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
