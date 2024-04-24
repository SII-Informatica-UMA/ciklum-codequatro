package CodeQuatro.Entidades_Cicklum.entities;

import java.util.List;

import jakarta.persistence.*;


/*ESTA CLASE SIRVE COMO CLASE AUXILIAR PARA EL ARRAY DE EJERCICIOS DE LAS RUTINAS */

@Entity
@Table(name = "EjercicioR" )
@Access (AccessType.PROPERTY)
public class SubEntidad_Ejercicio extends Ejercicios {
    private Long series;
    private Long repeticiones;
    private Long duracionMInutos;
    @ManyToMany (mappedBy = "ejercicios")
    private List<Rutinas> relacionRutina;

    @Column(name = "SERIES" , nullable = false, length = 50)
    public Long getSeries() {
        return series;
    }
    public void setSeries(Long series) {
        this.series = series;
    }
    @Column(name = "REPETICIONES" , nullable = false, length = 50)
    public Long getRepeticiones() {
        return repeticiones;
    }
    public void setRepeticiones(Long repeticiones) {
        this.repeticiones = repeticiones;
    }
    @Column(name = "DURACION_MINUTOS" , nullable = false, length = 50)
    public Long getDuracionMInutos() {
        return duracionMInutos;
    }
    public void setDuracionMInutos(Long duracionMInutos) {
        this.duracionMInutos = duracionMInutos;
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
        sb.append(this.getDuracionMInutos());
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
