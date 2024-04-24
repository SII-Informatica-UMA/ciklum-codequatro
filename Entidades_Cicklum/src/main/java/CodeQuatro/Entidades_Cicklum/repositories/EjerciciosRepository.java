package CodeQuatro.Entidades_Cicklum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;

public interface EjerciciosRepository extends JpaRepository<Ejercicios, Long> {
	List<Ejercicios> findEjercicioByName(String nombre);
	
	@Query("select e from Ejercicio e where e.nombre = :nombre")
	List<Ejercicios> miConsultaCompleja(@Param("nombre") String nombre);
}