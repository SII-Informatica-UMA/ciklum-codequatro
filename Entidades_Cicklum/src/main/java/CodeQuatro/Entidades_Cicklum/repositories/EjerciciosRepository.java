package CodeQuatro.Entidades_Cicklum.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;

@Repository
public interface EjerciciosRepository extends JpaRepository<Ejercicios, Long> {
	Optional<Ejercicios> findEjercicioByNombre(String nombre);
	
}