package CodeQuatro.Entidades_Cicklum.dtos;

import java.util.List;

import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "builderNuevo")
public class EjercicioNuevoDTO {
   private String nombre;
   private String descripcion;
   private String observaciones;
   private String tipo;
   private String musculosTrabajados;
   private String material;
   private String dificultad;
   private List<String> multimedia;
   private Long idEntrenador;





   public Ejercicios toEntity() {
      return Ejercicios.builder().nombre(this.getNombre()).descripcion(this.getDescripcion()).observaciones(this.getObservaciones()).tipo(this.getTipo()).musculosTrabajados(this.getMusculosTrabajados()).material(this.getMaterial()).dificultad(this.getDificultad()).multimedia(this.getMultimedia()).idEntrenador(this.getIdEntrenador()).build();
   }
   
   public Long getIdEntrenador() {
      return idEntrenador;
   }

   public void setIdEntrenador(Long idEntrenador) {
      this.idEntrenador = idEntrenador;
   }



   public String getNombre() {
      return this.nombre;
   }

   public String getDescripcion() {
      return this.descripcion;
   }

   public String getObservaciones() {
      return this.observaciones;
   }

   public String getTipo() {
      return this.tipo;
   }

   public String getMusculosTrabajados() {
      return this.musculosTrabajados;
   }

   public String getMaterial() {
      return this.material;
   }

   public String getDificultad() {
      return this.dificultad;
   }

   public List<String> getMultimedia() {
      return this.multimedia;
   }

   public void setNombre(final String nombre) {
      this.nombre = nombre;
   }

   public void setDescripcion(final String descripcion) {
      this.descripcion = descripcion;
   }

   public void setObservaciones(final String observaciones) {
      this.observaciones = observaciones;
   }

   public void setTipo(final String tipo) {
      this.tipo = tipo;
   }

   public void setMusculosTrabajados(final String musculosTrabajados) {
      this.musculosTrabajados = musculosTrabajados;
   }

   public void setMaterial(final String material) {
      this.material = material;
   }

   public void setDificultad(final String dificultad) {
      this.dificultad = dificultad;
   }

   public void setMultimedia(final List<String> multimedia) {
      this.multimedia = multimedia;
   }


}