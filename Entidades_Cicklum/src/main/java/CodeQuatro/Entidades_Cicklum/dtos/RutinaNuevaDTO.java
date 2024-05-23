package CodeQuatro.Entidades_Cicklum.dtos;

import java.util.List;

import CodeQuatro.Entidades_Cicklum.entities.Rutinas;

public class RutinaNuevaDTO {
   private String nombre;
   private String descripcion;
   private String observaciones;
   private List<FragmentoRutinaDTO> ejercicios;

   public Rutinas toEntity() {
      return Rutinas.builder().nombre(this.nombre).descripcion(this.descripcion).observaciones(this.observaciones).ejercicios(FragmentoRutinaDTO.toEntityList(this.ejercicios)).build();
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

   public List<FragmentoRutinaDTO> getEjercicios() {
      return this.ejercicios;
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

   public void setEjercicios(final List<FragmentoRutinaDTO> ejercicios) {
      this.ejercicios = ejercicios;
   }

   public RutinaNuevaDTO() {
   }

   public RutinaNuevaDTO(final String nombre, final String descripcion, final String observaciones, final List<FragmentoRutinaDTO> ejercicios) {
      this.nombre = nombre;
      this.descripcion = descripcion;
      this.observaciones = observaciones;
      this.ejercicios = ejercicios;
   }
}
