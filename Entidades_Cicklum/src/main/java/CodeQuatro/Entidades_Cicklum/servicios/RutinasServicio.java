package CodeQuatro.Entidades_Cicklum.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;
import CodeQuatro.Entidades_Cicklum.repositories.RutinasRepository;
import CodeQuatro.Entidades_Cicklum.repositories.EjerciciosRepository;
import CodeQuatro.Entidades_Cicklum.servicios.excepciones.RutinaNoEncontrada;
import CodeQuatro.Entidades_Cicklum.servicios.excepciones.EjercicioNoEncontrado;

@Service
@Transactional
public class RutinasServicio {

    private RutinasRepository rutinasRepositorio;
    private EjerciciosRepository ejerciciosRepositorio;

    public RutinasServicio(RutinasRepository rutinasRepositorio, EjerciciosRepository ejerciciosRepositorio) {
        this.rutinasRepositorio = rutinasRepositorio;
        this.ejerciciosRepositorio = ejerciciosRepositorio;
    }

    public List<Ejercicios> obtenerEjercicios() {
        return ejerciciosRepositorio.findAll();
    }
    
    public Ejercicios obtenerEjercicios(Long id ) {
        var ejercicios = ejerciciosRepositorio.findById(id);
        if (ejercicios.isPresent()) {
            return ejercicios.get();
        } else {
            throw new EjercicioNoEncontrado();
        }
    }

    public Long aniadirEjercicios(Ejercicios ejercicios) {
        ejercicios.setIdEjercicio(null);
        ejerciciosRepositorio.save(ejercicios);
        return ejercicios.getIdEjercicio();

    }

    public void actualizarEjercicios(Long id, Ejercicios ejercicios) {
        if (ejerciciosRepositorio.existsById(id)) {
            ejercicios.setIdEjercicio(id);
            ejerciciosRepositorio.save(ejercicios);
        } else {
            throw new EjercicioNoEncontrado();
        }
    }

    public void borrarEjercicios(Long id) {
        if (ejerciciosRepositorio.existsById(id)) {
            ejerciciosRepositorio.deleteById(id);
        } else {
            throw new EjercicioNoEncontrado();
        }
    }

    public List<Rutinas> obtenerRutinas() {
        return rutinasRepositorio.findAll();
    }

    public Rutinas obtenerRutinas(Long id) {
        var rutinas = rutinasRepositorio.findById(id);
        if (rutinas.isPresent()) {
            return rutinas.get();
        } else {
            throw new RutinaNoEncontrada();
        }
    }

    public Long aniadirRutinas(Rutinas rutinas) {
        if (!rutinasRepositorio.existsBynombre(rutinas.getNombre())) {
            rutinas.setIdRutinas(null);
            rutinasRepositorio.save(rutinas);
            return rutinas.getIdRutinas();
        } else {
            throw new RutinaNoEncontrada();
        }
    }

    public void actualizarRutinas(Long id, Rutinas rutinas) {
        if (rutinasRepositorio.existsById(id)) {
            rutinas.setIdRutinas(id);
            rutinasRepositorio.save(rutinas);
        } else {
            throw new RutinaNoEncontrada();
        }
    }

    public void borrarRutinas(Long id) {
        if (rutinasRepositorio.existsById(id)) {
            rutinasRepositorio.deleteById(id);
        } else {
            throw new RutinaNoEncontrada();
        }
    }
}
