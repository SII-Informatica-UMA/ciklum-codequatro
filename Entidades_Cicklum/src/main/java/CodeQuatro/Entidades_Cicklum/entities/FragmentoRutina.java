package CodeQuatro.Entidades_Cicklum.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FragmentoRutina {

    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    @Column(name = "ID" , nullable = false, length = 50)
    private Long idFragmentoRutina;
    @Column(name = "NUMERO DE SERIES" , nullable = false, length = 50)
    private Long numSeries;
    @Column(name = "NUMERO DE REPETICIONES" , nullable = false, length = 50)
    private Long numRepeticiones;
    @Column(name = "DURACION EN MINUTOS" , nullable = false, length = 50)
    private Long duracionMinutos;
    @ManyToOne
    private Ejercicios ejercicios;



    public Long getIdFragmentoRutina() {
        return idFragmentoRutina;
    }

    public void setIdFragmentoRutina(Long idFragmentoRutina) {
        this.idFragmentoRutina = idFragmentoRutina;
    }

    public Long getNumSeries() {
        return numSeries;
    }

    public void setNumSeries(Long numSeries) {
        this.numSeries = numSeries;
    }

    public Long getNumRepeticiones() {
        return numRepeticiones;
    }

    public void setNumRepeticiones(Long numRepeticiones) {
        this.numRepeticiones = numRepeticiones;
    }

    public Long getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(Long duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }
    public Ejercicios getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(Ejercicios ejercicios) {
        this.ejercicios = ejercicios;
    }
}
