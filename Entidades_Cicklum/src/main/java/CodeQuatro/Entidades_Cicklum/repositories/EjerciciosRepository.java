package CodeQuatro.Entidades_Cicklum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;

@Repository
public interface EjerciciosRepository extends JpaRepository<Ejercicios, Long> {
	List<Ejercicios> findEjercicioByNombre(String nombre);
	
	@Query("select e from Ejercicios e where e.nombre = :nombre")
	List<Ejercicios> miConsultaCompleja(@Param("nombre") String nombre);

}