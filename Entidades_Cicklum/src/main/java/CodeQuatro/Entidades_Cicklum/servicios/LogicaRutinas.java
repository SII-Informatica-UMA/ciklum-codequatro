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
      rutina.ifPresent((r) -> {
         this.comprobarPermiso(r);
      });
      rutina.orElseThrow(RutinaNoEncontrada::new);
      this.rutinaRepo.deleteById(idRutina);
   }

   public Rutinas crearActualizarRutina(Rutinas rutina) {
      if (rutina.getIdRutinas() != null) {
         this.obtenerRutina(rutina.getIdRutinas()).ifPresentOrElse((r) -> {
            this.comprobarPermiso(r);
         }, RutinaNoEncontrada::new);
      }

      this.rutinaRepo.save(rutina);
      rutina = (Rutinas)this.rutinaRepo.findById(rutina.getIdRutinas()).get();
      return rutina;
   }

   public List<Rutinas> getTodasRutinas() {
      return this.rutinaRepo.findAll();
   }

public Long aniadirRutina(Rutinas nuevaRutina){
		if (rutinaRepo.findByIdEntrenador(nuevaRutina.getIdEntrenador())!=null) {
            throw new RutinaExistente("La rutina ya existe en la base de datos");
        }
		nuevaRutina.setIdRutinas(null); // null?????
		rutinaRepo.save(nuevaRutina);
		return nuevaRutina.getIdRutinas();
	}
/* 
   public Rutinas getRutinasById(Long id) {
		List<Rutinas> rut = rutinaRepo.findByIdEntrenador(id);
        if(rut!=null){
            return rut.get(id.intValue());
        }else{
            throw new RutinaNoEncontrada("Rutina no encontrada en la base de datos");
        }
      }
*/

public Rutinas getRutinasById(Long id) {
   var rut = rutinaRepo.findById(id);
     if(rut.isPresent()){
         return rut.get();
     }else{
         throw new RutinaNoEncontrada("Rutina no encontrada en la base de datos");
     }
}

public void modificarRutina(Long id, Rutinas rutina){

   if (rutinaRepo.findByNombre(rutina.getNombre())!=null) { // si le quieres poner el nombre de una rutina que ya existe, lanza excepcion
         throw new RutinaExistente("Ya existe una rutina con ESE nombre en la bbdd");
     }
   
   else{
      if(rutinaRepo.existsById(id)){
         Optional<Rutinas> rutinasModificar = rutinaRepo.findById(id);

         rutinasModificar.ifPresent(l->l.setDescripcion(rutina.getDescripcion()));
         rutinasModificar.ifPresent(l->l.setIdEntrenador(rutina.getIdEntrenador()));
         rutinasModificar.ifPresent(l->l.setObservaciones(rutina.getObservaciones()));
         rutinasModificar.ifPresent(l->l.setFragmentoRutina(rutina.getFragmentoRutina()));

      }
      else{
         throw new RutinaNoEncontrada("La rutina no existe en la base de datos");
      }
   }
}

}