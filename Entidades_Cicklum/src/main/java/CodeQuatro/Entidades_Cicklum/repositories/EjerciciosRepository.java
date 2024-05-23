package CodeQuatro.Entidades_Cicklum.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;

@Repository
public interface EjerciciosRepository extends JpaRepository<Ejercicios, Long> {
	List<Ejercicios> findByIdEntrenador(Long idEntrenador);
	
}