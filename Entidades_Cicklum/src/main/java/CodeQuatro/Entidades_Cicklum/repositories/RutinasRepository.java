package CodeQuatro.Entidades_Cicklum.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import CodeQuatro.Entidades_Cicklum.entities.Rutinas;

@Repository
public interface RutinasRepository extends JpaRepository<Rutinas, Long>{

    Optional<Rutinas> findRutinaByNombre(String nombre);
    
    @Query("select r from Rutinas r where r.nombre = :nombre")
    //List<Rutinas> miConsultaCompleja(@Param("nombre") String nombre);
    boolean existsByNombre(String nombre);

    
} 
