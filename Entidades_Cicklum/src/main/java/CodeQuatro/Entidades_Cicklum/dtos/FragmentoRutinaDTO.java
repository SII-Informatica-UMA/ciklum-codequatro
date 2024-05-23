package CodeQuatro.Entidades_Cicklum.dtos;

import java.util.List;

import CodeQuatro.Entidades_Cicklum.entities.FragmentoRutina;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FragmentoRutinaDTO {
private Long numSeries;
private Long numRepeticiones;
private Long duracionMinutos;
private EjerciciosDTO ejercicio;


public FragmentoRutina toEntity() {
    return FragmentoRutina.builder().numSeries(this.numSeries).numRepeticiones(this.numRepeticiones).duracionMinutos(this.duracionMinutos).ejercicios(this.ejercicio.toEntity()).build();
 }

 public static FragmentoRutinaDTO fromEntity(FragmentoRutina fragmentoRutina) {
    return FragmentoRutinaDTO.builder().numSeries(fragmentoRutina.getNumSeries()).numRepeticiones(fragmentoRutina.getNumRepeticiones()).duracionMinutos(fragmentoRutina.getDuracionMinutos()).ejercicio(EjerciciosDTO.fromEntity(fragmentoRutina.getEjercicios())).build();
 }

 public static List<FragmentoRutina> toEntityList(List<FragmentoRutinaDTO> lista) {
    return lista == null ? null : lista.stream().map(FragmentoRutinaDTO::toEntity).toList();
 }

 public static List<FragmentoRutinaDTO> fromEntityList(List<FragmentoRutina> lista) {
    return lista == null ? null : lista.stream().map(FragmentoRutinaDTO::fromEntity).toList();
 }

public Long getNumSeries() {
    return numSeries;
}

public void setNumSeries(Long numSeries) {
    this.numSeries = numSeries;
}

public Long getNumRepeticiones() {
    return numRepeticiones;
}

public void setNumRepeticiones(Long numRepeticiones) {
    this.numRepeticiones = numRepeticiones;
}

public Long getDuracionMinutos() {
    return duracionMinutos;
}

public void setDuracionMinutos(Long duracionMinutos) {
    this.duracionMinutos = duracionMinutos;
}

public EjerciciosDTO getEjercicio() {
    return ejercicio;
}

public void setEjercicio(EjerciciosDTO ejercicio) {
    this.ejercicio = ejercicio;
}


 

}
