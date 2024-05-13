package CodeQuatro.Entidades_Cicklum.servicios;
import java.util.List;

import org.springframework.stereotype.Service;

import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;
import CodeQuatro.Entidades_Cicklum.excepciones.EjercicioExistenteException;
import CodeQuatro.Entidades_Cicklum.excepciones.EjercicioNoEncontradoException;
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

    public void actualizarEjercicio(Ejercicios ejercicio){
        if(repo.existsById(ejercicio.getIdEjercicio())){
            repo.save(ejercicio);
        }else{
            throw new EjercicioNoEncontradoException("ERROR: no se ha podido modificar el ejercicio con id: "
             + ejercicio.getIdEjercicio() + " -> no encontrado en la base de datos");
        }
    }
}
