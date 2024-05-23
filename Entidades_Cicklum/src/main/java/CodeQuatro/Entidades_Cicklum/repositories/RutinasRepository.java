package CodeQuatro.Entidades_Cicklum.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import CodeQuatro.Entidades_Cicklum.entities.Rutinas;

@Repository
public interface RutinasRepository extends JpaRepository<Rutinas, Long>{

    List<Rutinas> findByNombre(String nombre);

    
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM Rutina r WHERE EXISTS (SELECT 1 FROM r.ejercicios f WHERE f.ejercicio.id = :idEjercicio)")
    boolean existsRutinaWithEjercicio(@Param("idEjercicio") Long idEjercicio);
    

    
} 
