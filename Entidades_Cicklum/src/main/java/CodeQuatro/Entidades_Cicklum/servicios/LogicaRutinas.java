package CodeQuatro.Entidades_Cicklum.servicios;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
import CodeQuatro.Entidades_Cicklum.excepciones.RutinaExistente;
import CodeQuatro.Entidades_Cicklum.excepciones.RutinaNoEncontrada;
import CodeQuatro.Entidades_Cicklum.repositories.RutinasRepository;

@Service
@Transactional
public class LogicaRutinas {
   private RutinasRepository rutinaRepo;

   public LogicaRutinas(RutinasRepository rutinaRepo) {
      this.rutinaRepo = rutinaRepo;
   }

   public List<Rutinas> obtenerRutinas(Long idEntrenador) {
      this.comprobarPermiso(idEntrenador);
      return this.rutinaRepo.findByIdEntrenador(idEntrenador);
   }

   private void comprobarPermiso(Long idEntrenador) {
   }

   private void comprobarPermiso(Rutinas rutina) {
   }

   public Optional<Rutinas> obtenerRutina(Long idRutina) {
      Optional<Rutinas> rutina = this.rutinaRepo.findById(idRutina);
      rutina.ifPresent(this::comprobarPermiso);
      return rutina;
   }

   public void eliminarRutina(Long idRutina) {
      Optional<Rutinas> rutina = this.obtenerRutina(idRutina);
      rutina.ifPresent(this::comprobarPermiso);
      rutina.orElseThrow(RutinaNoEncontrada::new);
      this.rutinaRepo.deleteById(idRutina);
   }

   public List<Rutinas> getTodasRutinas() {
      return this.rutinaRepo.findAll();
   }

   public Long aniadirRutina(Rutinas nuevaRutina) {
      if (!rutinaRepo.findByIdEntrenador(nuevaRutina.getIdEntrenador()).isEmpty()) {
         throw new RutinaExistente("La rutina ya existe en la base de datos");
      }
      nuevaRutina.setIdRutinas(null);
      rutinaRepo.save(nuevaRutina);
      return nuevaRutina.getIdRutinas();
   }

   public Rutinas getRutinasById(Long id) {
      var rut = rutinaRepo.findById(id);
      if (rut.isPresent()) {
         return rut.get();
      } else {
         throw new RutinaNoEncontrada("Rutina no encontrada en la base de datos");
      }
   }

   public void modificarRutina(Long id, Rutinas rutina) {
      if (rutinaRepo.findByNombre(rutina.getNombre()) != null) {
         throw new RutinaExistente("Ya existe una rutina con ESE nombre en la bbdd");
      } else {
         if (rutinaRepo.existsById(id)) {
            Optional<Rutinas> rutinasModificar = rutinaRepo.findById(id);

            rutinasModificar.ifPresent(l -> {
               l.setNombre(rutina.getNombre());
               l.setDescripcion(rutina.getDescripcion());
               l.setIdEntrenador(rutina.getIdEntrenador());
               l.setObservaciones(rutina.getObservaciones());
               l.setFragmentoRutina(rutina.getFragmentoRutina());
               rutinaRepo.save(l); // Guardamos la rutina modificada
            });
         } else {
            throw new RutinaNoEncontrada("La rutina no existe en la base de datos");
         }
      }
   }
}
