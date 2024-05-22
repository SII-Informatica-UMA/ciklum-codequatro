package CodeQuatro.Entidades_Cicklum.servicios;
import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
import CodeQuatro.Entidades_Cicklum.excepciones.EjercicioNoEncontradoException;
import CodeQuatro.Entidades_Cicklum.excepciones.RutinaExistente;
import CodeQuatro.Entidades_Cicklum.excepciones.RutinaNoEncontrada;
import CodeQuatro.Entidades_Cicklum.repositories.RutinasRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LogicaRutinas {
	
	private RutinasRepository repo;
	
	public LogicaRutinas(RutinasRepository repo) {
		this.repo=repo;
	}
	
	public List<Rutinas> getTodasRutinas() {
		return repo.findAll();
	}

	public Rutinas getRutinasById(Long id) {
		var rut = repo.findById(id);
        if(rut.isPresent()){
            return rut.get();
        }else{
            throw new RutinaNoEncontrada("Rutina no encontrada en la base de datos");
        }
	}

	public Long aniadirRutina(Rutinas nuevaRutina){
		if (repo.findByNombre(nuevaRutina.getNombre()).isPresent()) {
            throw new RutinaExistente("La rutina ya existe en la base de datos");
        }
		nuevaRutina.setIdRutinas(null); // null?????
		repo.save(nuevaRutina);
		return nuevaRutina.getIdRutinas();
	}
	
	public void modificarRutina(Long id, Rutinas rutina){

		if (repo.findByNombre(rutina.getNombre()).isPresent()) { // si le quieres poner el nombre de una rutina que ya existe, lanza excepcion
            throw new RutinaExistente("La rutina ya existe en la base de datos");
        }
		
		else{
			if(repo.existsById(id)){
				Optional<Rutinas> rutinasModificar = repo.findById(id);

				rutinasModificar.ifPresent(l->l.setDescripcion(rutina.getDescripcion()));
				rutinasModificar.ifPresent(l->l.setIdEntrenador(rutina.getIdEntrenador()));
				rutinasModificar.ifPresent(l->l.setObservaciones(rutina.getObservaciones()));
				rutinasModificar.ifPresent(l->l.setFragmentoRutina(rutina.getFragmentoRutina()));

			}
			else{
				throw new RutinaExistente("La rutina ya existe en la base de datos");
			}
		}
	}

	public void eliminarRutina(Long id){
		if(repo.existsById(id)){
			repo.deleteById(id);
		}
		else{
			throw new RutinaNoEncontrada("La rutina no existe en la base de datos");
		}
	}
}
