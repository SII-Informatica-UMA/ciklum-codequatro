package CodeQuatro.Entidades_Cicklum.entities;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
@Access (AccessType.PROPERTY)
public class Rutinas {

    private Long idRutinas;
    private String nombre;
    private String descripcion;
    private String observaciones;
    @ManyToMany
    private Set<Ejercicios> relacionEjercicio;
    @ElementCollection
    private List<SubEntidad_Ejercicio> ejercicios;


    @Id
    @GeneratedValue
    @Column(name = "ID" , nullable = false, length = 50)
    public Long getIdRutinas() {
        return idRutinas;
    }

    public void setIdRutinas(Long id) {
        this.idRutinas = id;
    }
    @Column(name = "NOMBRE" , nullable = false, length = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Column(name = "DESCRIPCION" , nullable = false, length = 50)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Column(name = "OBSERVACIONES" , nullable = false, length = 50)
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Column(name = "EJERCICIOS" , nullable = false, length = 50)
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
        for(SubEntidad_Ejercicio s : ejercicios){
            sb.append(s.toString());
        }

        return sb.toString();
    }

}
