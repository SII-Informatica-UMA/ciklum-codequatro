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
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LogicaEjercicios {
    private EjerciciosRepository repo;


    public LogicaEjercicios(EjerciciosRepository repo) {
        this.repo = repo;
    }

    public List<Ejercicios> getTodosEjercicios() {
        return repo.findAll();
    }
    public Ejercicios getEjercicioById(Long id) {
        var Ejercicios = repo.findById(id);
        if(Ejercicios.isPresent()){
            return Ejercicios.get();
        }else{
            throw new EjercicioNoEncontradoException("Ejercicio no encontrado en la base de datos");
        }
    }

    public Long aniadirEjercicio(Ejercicios nuevoEjercicio){
        if(!repo.existsById(nuevoEjercicio.getIdEjercicio())){
            nuevoEjercicio.setIdEjercicio(null);
            repo.save(nuevoEjercicio);
            System.out.println("Ejercicio añadido");
            return nuevoEjercicio.getIdEjercicio();
        }else{
            throw new EjercicioExistenteException("El ejercicio ya está creado");
        }
    }

    public void eliminarEjercicio(Long id){
        if(repo.existsById(id)){
            repo.deleteById(id);
        }else{
            throw new EjercicioNoEncontradoException("Ejercicio no encontrado en la base de datos");
        }
    }

    public void actualizarEjercicio(Long id, Ejercicios ejercicio){

        if (repo.findEjercicioByNombre(ejercicio.getNombre()).isPresent()) { // si le quieres poner el nombre de un ejercicio que ya existe, lanza excepcion
            throw new RutinaExistente("El ejercicio ya existe en la base de datos");
        }
		
		else{
			if(repo.existsById(id)){
				Optional<Ejercicios> ejercicioModificar = repo.findById(id);

				ejercicioModificar.ifPresent(l->l.setDescripcion(ejercicio.getDescripcion()));
                ejercicioModificar.ifPresent(l->l.setDificultad(ejercicio.getDificultad()));
                ejercicioModificar.ifPresent(l->l.setMaterial(ejercicio.getMaterial()));
                ejercicioModificar.ifPresent(l->l.setMusculosTrabajados(ejercicio.getMusculosTrabajados()));
                ejercicioModificar.ifPresent(l->l.setObservaciones(ejercicio.getObservaciones()));
                ejercicioModificar.ifPresent(l->l.setTipo(ejercicio.getTipo()));
                ejercicioModificar.ifPresent(l->l.setMultimedia(ejercicio.getMultimedia()));

			}
			else{
				throw new EjercicioNoEncontradoException("ERROR: no se ha podido modificar el ejercicio con id: "
                + ejercicio.getIdEjercicio() + " -> no encontrado en la base de datos");
			}

    }
}

}