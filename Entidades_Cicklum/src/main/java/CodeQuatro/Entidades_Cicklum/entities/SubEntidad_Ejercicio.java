package CodeQuatro.Entidades_Cicklum.entities;

import java.util.List;
import java.io.Serializable;

import jakarta.persistence.*;


/*ESTA CLASE SIRVE COMO CLASE AUXILIAR PARA EL ARRAY DE EJERCICIOS DE LAS RUTINAS */

@Entity
@Table(name = "Subentidad" )
@Access (AccessType.FIELD)
public class SubEntidad_Ejercicio extends Ejercicios implements Serializable{
    @Column(name = "SERIES" , nullable = false, length = 50)
    private Long series;
    @Column(name = "REPETICIONES" , nullable = false, length = 50)
    private Long repeticiones;
    @Column(name = "DURACION_MINUTOS" , nullable = false, length = 50)
    private Long duracionMinutos;
    @ManyToMany (mappedBy = "ejercicios")
    private List<Rutinas> relacionRutina;

    public SubEntidad_Ejercicio() {
    }
    
    public SubEntidad_Ejercicio (Long idEjercicio, String nombre, String descripcion, String observaciones,String tipo, String musculosTrabajados, String material, String dificultad, List<String> multimedia, Long series, Long repeticiones, Long duracionMinutos){
        super(idEjercicio, nombre, descripcion, observaciones, tipo, musculosTrabajados, material, dificultad, multimedia);
        this.series = series;
        this.repeticiones = repeticiones;
        this.duracionMinutos = duracionMinutos;
    }

    public Long getSeries() {
        return series;
    }
    public void setSeries(Long series) {
        this.series = series;
    }
    public Long getRepeticiones() {
        return repeticiones;
    }
    public void setRepeticiones(Long repeticiones) {
        this.repeticiones = repeticiones;
    }
    public Long getDuracionMinutos() {
        return duracionMinutos;
    }
    public void setDuracionMinutos(Long duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public List<Rutinas> getRelacionRutina(){
        return relacionRutina;
    }

    public void setRelacionRutina(List<Rutinas> relacionRutina){
        this.relacionRutina = relacionRutina;
    }

    @Override
    public boolean equals(Object obj) {
       if(obj instanceof SubEntidad_Ejercicio){
        SubEntidad_Ejercicio aux = (SubEntidad_Ejercicio)obj;
        return this.getIdEjercicio() == aux.getIdEjercicio();
       }
       return false;
    }
    @Override
    public int hashCode() {
        return Long.hashCode(this.getIdEjercicio());
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Series= ");
        sb.append(this.getSeries());
        sb.append(",Repeticiones= ");
        sb.append(this.getRepeticiones());
        sb.append(", Duracion en minutos= ");
        sb.append(this.getDuracionMinutos());
        sb.append("Ejercicio [id= ");
        sb.append(this.getIdEjercicio());
        sb.append(", nombre= ");
        sb.append(this.getNombre());
        sb.append(", descripcion= ");
        sb.append(this.getDescripcion());
        sb.append(", tipo= ");
        sb.append(getTipo());
        sb.append(", musculos trabajados= ");
        sb.append(getMusculosTrabajados());
        sb.append(", material= ");
        sb.append(getMaterial());
        sb.append(", dificultad= ");
        sb.append(getDificultad());
        sb.append(", multimedia= [");
        for(String a : this.getMultimedia()){
            sb.append(a);
        }
        sb.append("]]");
        return sb.toString();
    }

}
