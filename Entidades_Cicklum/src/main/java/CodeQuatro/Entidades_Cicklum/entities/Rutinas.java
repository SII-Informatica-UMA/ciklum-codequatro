package CodeQuatro.Entidades_Cicklum.entities;

import java.util.List;
import java.io.Serializable;

import jakarta.persistence.*;


@Entity
@Access (AccessType.FIELD)
public class Rutinas implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "ID" , nullable = false, length = 50)
    private Long idRutinas;
    @Column(name = "NOMBRE" , nullable = false, length = 50)
    private String nombre;
    @Column(name = "DESCRIPCION" , nullable = false, length = 50)
    private String descripcion;
    @Column(name = "OBSERVACIONES" , nullable = false, length = 50)
    private String observaciones;
    @ManyToMany 
    @JoinTable (name = "relRutinaEjercicio")
    private List<SubEntidad_Ejercicio> ejercicios;

    public Rutinas() {
    }
    public Rutinas (Long idRutinas, String nombre, String descripcion, String observaciones, List<SubEntidad_Ejercicio> ejercicios){
        this.idRutinas = idRutinas;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.ejercicios = ejercicios;
    }

    
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

    public List<SubEntidad_Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<SubEntidad_Ejercicio> ejercicios) {
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
    public void setId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }

}
