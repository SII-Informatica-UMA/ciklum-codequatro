package CodeQuatro.Entidades_Cicklum.entities;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Ejercicios {
    @Id
    @GeneratedValue
    private Long idEjercicio;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String musculosTrabajados;
    private String material;
    private String dificultad;
    private String multimedia[];

   

    public Long getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(Long idEjercicio) {
        this.idEjercicio = idEjercicio;
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

    public String[] getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String[] multimedia) {
        this.multimedia = multimedia;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idEjercicio);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ejercicios) {
            Ejercicios aux = (Ejercicios) obj;
            return this.idEjercicio == aux.getIdEjercicio();
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ejercicio [id= ");
        sb.append(idEjercicio);
        sb.append(", nombre= ");
        sb.append(nombre);
        sb.append(", descripcion= ");
        sb.append(descripcion);
        sb.append(", tipo= ");
        sb.append(tipo);
        sb.append(", musculos trabajados= ");
        sb.append(musculosTrabajados);
        sb.append(", material= ");
        sb.append(material);
        sb.append(", dificultad= ");
        sb.append(dificultad);
        sb.append(", multimedia= [");
        for(String a : multimedia){
            sb.append(a);
        }
        sb.append("]]");
        return sb.toString();
    }
}
