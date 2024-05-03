package CodeQuatro.Entidades_Cicklum.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import CodeQuatro.Entidades_Cicklum.entities.Rutinas;

public interface RutinasRepository  extends JpaRepository<Rutinas, Long>{

    List<Rutinas> findRutinaByName(String nombre);
    
    @Query("select r from Rutina r where r.nombre = :nombre")
    List<Rutinas> miConsultaCompleja(@Param("nombre") String nombre);
    boolean existsBynombre(String nombre);

    
} 
