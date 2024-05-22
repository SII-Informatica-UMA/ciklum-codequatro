package CodeQuatro.Entidades_Cicklum.entities;

import java.util.List;
import java.io.Serializable;

import jakarta.persistence.*;

@Entity
public class Ejercicios implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID" , nullable = false, length = 50)
    private Long idEjercicio;
    @Column(name = "NOMBRE" , nullable = false, length = 50)
    private String nombre;
    @Column(name = "DESCRIPCION" , nullable = false, length = 50)
    private String descripcion;
    @Column(name = "OBSERVACIONES", nullable = false, length = 50)
    private String observaciones;
    @Column(name = "TIPO" , nullable = false, length = 50)
    private String tipo;
    @Column(name = "MUSCULOS_TRABAJADOS" , nullable = false, length = 50)
    private String musculosTrabajados;
    @Column(name = "MATERIAL" , nullable = false, length = 50)
    private String material;
    @Column(name = "DIFICULTAD" , nullable = false, length = 50)
    private String dificultad;
    // @ElementCollection
    // @Column(name = "MULTIMEDIA" , nullable = false, length = 50)
    // @CollectionTable(name = "multimedia")

    @ElementCollection
    @CollectionTable(name = "MULTIMEDIA", joinColumns = @JoinColumn(name = "EJERCICIOS_ID"))
    @Column(name = "MULTIMEDIA", nullable = false, length = 50)
    private List<String> multimedia;
    @Column(name = "ID_ENTRENADOR" , nullable = false, length = 50)
    private Long idEntrenador;

    public Ejercicios() {
    }
    public Ejercicios (Long idEjercicio, String nombre, String descripcion, String observaciones, String tipo, String musculosTrabajados, String material, String dificultad, List<String> multimedia){
        this.idEjercicio = idEjercicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.tipo = tipo;
        this.musculosTrabajados = musculosTrabajados;
        this.material = material;
        this.dificultad = dificultad;
        this.multimedia = multimedia;
    }


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
        sb.append("Observaciones= ");
        sb.append(observaciones);
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
