package CodeQuatro.Entidades_Cicklum.servicios;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;
import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
import CodeQuatro.Entidades_Cicklum.excepciones.EjercicioExistenteException;
import CodeQuatro.Entidades_Cicklum.excepciones.EjercicioNoEncontradoException;
import CodeQuatro.Entidades_Cicklum.excepciones.RutinaExistente;
import CodeQuatro.Entidades_Cicklum.repositories.EjerciciosRepository;
import CodeQuatro.Entidades_Cicklum.repositories.RutinasRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogicaEjercicios {
   private EjerciciosRepository ejercicioRepo;
   private RutinasRepository rutinaRepo;

   public LogicaEjercicios(EjerciciosRepository ejercicioRepo, RutinasRepository rutinaRepo) {
      this.ejercicioRepo = ejercicioRepo;
      this.rutinaRepo = rutinaRepo;
   }

   public List<Ejercicios> obtenerEjercicios(Long idEntrenador) {
      this.comprobarPermiso(idEntrenador);
      return this.ejercicioRepo.findByIdEntrenador(idEntrenador);
   }

   public Optional<Ejercicios> obtenerEjercicio(Long idEjercicio) {
      Optional<Ejercicios> ejercicio = this.ejercicioRepo.findById(idEjercicio);
      ejercicio.ifPresent(this::comprobarPermiso);
      return ejercicio;
   }

   private void comprobarPermiso(Ejercicios ejercicio) {
   }

   private void comprobarPermiso(Long idEntrenador) {
   }

   public Ejercicios crearActualizarEjercicio(Ejercicios ejercicio) {
      this.comprobarPermiso(ejercicio);
      return (Ejercicios)this.ejercicioRepo.save(ejercicio);
   }

   
   public void eliminarEjercicio(Long id) throws Exception{
      Optional<Ejercicios>aux= ejercicioRepo.findById(id);
      if(aux.isEmpty()){

          throw new Exception();
      }
      ejercicioRepo.deleteById(id);
}
}
