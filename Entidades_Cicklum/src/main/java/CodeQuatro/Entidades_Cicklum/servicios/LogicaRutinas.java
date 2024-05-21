package CodeQuatro.Entidades_Cicklum.servicios;
import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
import CodeQuatro.Entidades_Cicklum.excepciones.RutinaExistente;
import CodeQuatro.Entidades_Cicklum.excepciones.RutinaNoEncontrada;
import CodeQuatro.Entidades_Cicklum.repositories.RutinasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LogicaRutinas {
	
	private RutinasRepository repo;
	
	@Autowired
	public LogicaRutinas(RutinasRepository repo) {
		this.repo=repo;
	}
	
	public List<Rutinas> getTodasRutinas() {
		return repo.findAll();
	}

	public Optional<Rutinas> getRutinasById(Long id) {
		return repo.findById(id);
	}

	public Rutinas aniadirRutina(Rutinas nuevaRutina){
		if (repo.findRutinaByNombre(nuevaRutina.getNombre()).isPresent()) {
            throw new RutinaExistente("La rutina ya existe en la base de datos");
        }
		nuevaRutina.setIdRutinas(null); // null?????
		repo.save(nuevaRutina);
		return nuevaRutina;
	}
	
	public void modificarRutina(Long id, Rutinas rutina){

		if (repo.findRutinaByNombre(rutina.getNombre()).isPresent()) { // si le quieres poner el nombre de una rutina que ya existe, lanza excepcion
            throw new RutinaExistente("La rutina ya existe en la base de datos");
        }
		
		else{
			if(repo.existsById(id)){
				Optional<Rutinas> rutinasModificar = repo.findById(id);

				rutinasModificar.ifPresent(l->l.setNombre(rutina.getNombre()));
				rutinasModificar.ifPresent(l->l.setDescripcion(rutina.getDescripcion()));
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
