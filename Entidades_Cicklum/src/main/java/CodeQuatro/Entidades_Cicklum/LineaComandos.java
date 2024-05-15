package CodeQuatro.Entidades_Cicklum;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//CLASE PARA IMPLEMENTAR EL INTREPETE DE LINEA DE COMANDOS PARA HACER BUSQUEDAS


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;
import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
import CodeQuatro.Entidades_Cicklum.entities.SubEntidad_Ejercicio;
import CodeQuatro.Entidades_Cicklum.repositories.EjerciciosRepository;
import CodeQuatro.Entidades_Cicklum.repositories.RutinasRepository;

//@Component
public class LineaComandos implements CommandLineRunner {
	
	@Autowired
	private RutinasRepository repoRutinas;

	@Autowired
	private EjerciciosRepository repoEjercicio;

	private static Logger LOG = LoggerFactory.getLogger(LineaComandos.class);

	public LineaComandos(EjerciciosRepository repoEjercicio, RutinasRepository repoRutinas ) {
		this.repoEjercicio = repoEjercicio;
		this.repoRutinas = repoRutinas;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		LOG.info("EXECUTING : command line runner");
		List<String> mult = new ArrayList<>();
		mult.add("https://youtube.com");
		mult.add("ejemplo.png");
		//Ejercicio 1
		SubEntidad_Ejercicio ejercicio1 = new SubEntidad_Ejercicio();
		ejercicio1.setNombre("Sentadillas");
		ejercicio1.setDescripcion("Ejercicio para piernas");
		ejercicio1.setTipo("Fuerza");
		ejercicio1.setMusculosTrabajados("Piernas");
		ejercicio1.setMaterial("Barra");
		ejercicio1.setDificultad("Media");
		ejercicio1.setMultimedia(mult);
		repoEjercicio.save(ejercicio1);

		//Ejercicio 2
		SubEntidad_Ejercicio ejercicio2 = new SubEntidad_Ejercicio();
		ejercicio2.setNombre("Flexiones");
		ejercicio2.setDescripcion("Ejercicio para pectorales");
		ejercicio2.setTipo("Fuerza");
		ejercicio2.setMusculosTrabajados("Pectorales");
		ejercicio2.setMaterial("Ninguno");
		ejercicio2.setDificultad("Baja");
		ejercicio2.setMultimedia(mult);
		repoEjercicio.save(ejercicio2);

		
		

		



		//Rutina 1
		Rutinas rutina1 = new Rutinas();
		rutina1.setNombre("Rutina de piernas");
		rutina1.setDescripcion("Rutina para fortalecer las piernas");
		rutina1.setObservaciones("Realizar 3 veces por semana");
		List<SubEntidad_Ejercicio> ejercicio = new ArrayList<SubEntidad_Ejercicio>();
		ejercicio.add(ejercicio1);
		rutina1.setEjercicios(new ArrayList<SubEntidad_Ejercicio>(ejercicio));
		repoRutinas.save(rutina1);

		//Rutina 2
		Rutinas rutina2 = new Rutinas();
		rutina2.setNombre("Rutina de pecho");
		rutina2.setDescripcion("Rutina para fortalecer el pecho");
		rutina2.setObservaciones("Realizar 2 veces por semana");
		List<SubEntidad_Ejercicio> ejercicioR = new ArrayList<SubEntidad_Ejercicio>();
		ejercicioR.add(ejercicio2);
		rutina2.setEjercicios(new ArrayList<SubEntidad_Ejercicio>(ejercicioR));
		repoRutinas.save(rutina2);


	}

}
