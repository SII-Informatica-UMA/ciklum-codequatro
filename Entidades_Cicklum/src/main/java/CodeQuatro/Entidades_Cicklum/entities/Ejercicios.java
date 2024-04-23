package CodeQuatro.Entidades_Cicklum.entities;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Ejercicios {
    private Long idEjercicio;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String musculosTrabajados;
    private String material;
    private String dificultad;
    private List<String> multimedia;
    @ManyToMany
    private Set<Rutinas> rutinas; // Coleccion para la relacion de mucho a mucho entre ejercicio y rutinas

   
    @Id
    @GeneratedValue
    @Column(name = "ID" , nullable = false, length = 50)
    public Long getIdEjercicio() {
        return idEjercicio;
    }
    
    public void setIdEjercicio(Long idEjercicio) {
        this.idEjercicio = idEjercicio;
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
    @Column(name = "TIPO" , nullable = false, length = 50)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    @Column(name = "MUSCULOS_TRABAJADOS" , nullable = false, length = 50)
    public String getMusculosTrabajados() {
        return musculosTrabajados;
    }

    public void setMusculosTrabajados(String musculosTrabajados) {
        this.musculosTrabajados = musculosTrabajados;
    }
    @Column(name = "MATERIAL" , nullable = false, length = 50)
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    @Column(name = "DIFICULTAD" , nullable = false, length = 50)
    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
    @Column(name = "MULTIMEDIA" , nullable = false, length = 50)
    public List<String> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<String> multimedia) {
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
