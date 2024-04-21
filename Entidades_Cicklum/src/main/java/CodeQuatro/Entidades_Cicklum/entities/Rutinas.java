package CodeQuatro.Entidades_Cicklum.entities;

import jakarta.persistence.Entity;

@Entity
public class Rutinas {

    private Long idRutinas;
    private String nombre;
    private String descripcion;
    private String observaciones;
    /* Hay que revisar como hacer la coleccion de ejercicios porque en el swagger, en rutinas, el atributo ejercicios tienen
     * series, repeticiones... 
     */
    private Ejercicios[] ejercicios;

    public Long getIdRutinas() {
        return idRutinas;
    }

    public void setIdRutinas(Long id) {
        this.idRutinas = id;
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

    public Ejercicios[] getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(Ejercicios[] ejercicios) {
        this.ejercicios = ejercicios;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idRutinas);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Rutinas) {
            Rutinas aux = (Rutinas) obj;
            return this.idRutinas == aux.getIdRutinas();
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Rutina [id= ");
        sb.append(idRutinas);
        sb.append(", nombre= ");
        sb.append(nombre);
        sb.append(", descripcion= ");
        sb.append(descripcion);
        sb.append(", observaciones= ");
        sb.append(observaciones);
       

        return sb.toString();
    }

}
