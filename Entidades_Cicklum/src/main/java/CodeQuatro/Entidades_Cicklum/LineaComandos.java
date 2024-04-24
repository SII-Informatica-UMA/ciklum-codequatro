package CodeQuatro.Entidades_Cicklum;


import java.util.ArrayList;
import java.util.List;

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

@Component
public class LineaComandos implements CommandLineRunner {
	
	@Autowired
	private RutinasRepository repoRutinas;

	@Autowired
	private EjerciciosRepository repoEjercicio;

	public LineaComandos(EjerciciosRepository repoEjercicio, RutinasRepository repoRutinas ) {
		this.repoEjercicio = repoEjercicio;
		this.repoRutinas = repoRutinas;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		

		//Ejercicio 1
		SubEntidad_Ejercicio ejercicio1 = new SubEntidad_Ejercicio();
		ejercicio1.setNombre("Sentadillas");
		ejercicio1.setDescripcion("Ejercicio para piernas");
		ejercicio1.setTipo("Fuerza");
		ejercicio1.setMusculosTrabajados("Piernas");
		ejercicio1.setMaterial("Barra");
		ejercicio1.setDificultad("Media");
		ejercicio1.setMultimedia("https://www.youtube.com/watch?v=QKKZ9AGYTi4");
		repoEjercicio.save(ejercicio1);

		//Ejercicio 2
		SubEntidad_Ejercicio ejercicio2 = new SubEntidad_Ejercicio();
		ejercicio2.setNombre("Flexiones");
		ejercicio2.setDescripcion("Ejercicio para pectorales");
		ejercicio2.setTipo("Fuerza");
		ejercicio2.setMusculosTrabajados("Pectorales");
		ejercicio2.setMaterial("Ninguno");
		ejercicio2.setDificultad("Baja");
		ejercicio2.setMultimedia("https://www.youtube.com/watch?v=4dF1DOWzf20");
		repoEjercicio.save(ejercicio2);

		//Ejercicio 3
		SubEntidad_Ejercicio ejercicio3 = new SubEntidad_Ejercicio();
		ejercicio3.setNombre("Plancha");
		ejercicio3.setDescripcion("Ejercicio para abdomen");
		ejercicio3.setTipo("Fuerza");
		ejercicio3.setMusculosTrabajados("Abdomen");
		ejercicio3.setMaterial("Ninguno");
		ejercicio3.setDificultad("Baja");
		ejercicio3.setMultimedia("https://www.youtube.com/watch?v=ASdvN_XEl_c");
		repoEjercicio.save(ejercicio3);

		//Ejercicio 4
		SubEntidad_Ejercicio ejercicio4 = new SubEntidad_Ejercicio();
		ejercicio4.setNombre("Dominadas");
		ejercicio4.setDescripcion("Ejercicio para espalda");
		ejercicio4.setTipo("Fuerza");
		ejercicio4.setMusculosTrabajados("Espalda");
		ejercicio4.setMaterial("Barra");
		ejercicio4.setDificultad("Alta");
		ejercicio4.setMultimedia("https://www.youtube.com/watch?v=8jyhJUd2Z4U");
		repoEjercicio.save(ejercicio4);

		//Ejercicio 5
		SubEntidad_Ejercicio ejercicio5 = new SubEntidad_Ejercicio();
		ejercicio5.setNombre("Burpees");
		ejercicio5.setDescripcion("Ejercicio para todo el cuerpo");
		ejercicio5.setTipo("Fuerza");
		ejercicio5.setMusculosTrabajados("Todo el cuerpo");
		ejercicio5.setMaterial("Ninguno");
		ejercicio5.setDificultad("Alta");
		ejercicio5.setMultimedia("https://www.youtube.com/watch?v=JZQA08SlJnM");
		repoEjercicio.save(ejercicio5);

		//Ejercicio 6
		SubEntidad_Ejercicio ejercicio6 = new SubEntidad_Ejercicio();
		ejercicio6.setNombre("Zancadas");
		ejercicio6.setDescripcion("Ejercicio para piernas");
		ejercicio6.setTipo("Fuerza");
		ejercicio6.setMusculosTrabajados("Piernas");
		ejercicio6.setMaterial("Ninguno");
		ejercicio6.setDificultad("Media");
		ejercicio6.setMultimedia("https://www.youtube.com/watch?v=QKKZ9AGYTi4");
		repoEjercicio.save(ejercicio6);

		//Ejercicio 7
		SubEntidad_Ejercicio ejercicio7 = new SubEntidad_Ejercicio();
		ejercicio7.setNombre("Flexiones diamante");
		ejercicio7.setDescripcion("Ejercicio para triceps");
		ejercicio7.setTipo("Fuerza");
		ejercicio7.setMusculosTrabajados("Triceps");
		ejercicio7.setMaterial("Ninguno");
		ejercicio7.setDificultad("Media");
		ejercicio7.setMultimedia("https://www.youtube.com/watch?v=4dF1DOWzf20");
		repoEjercicio.save(ejercicio7);

		//Ejercicio 8
		SubEntidad_Ejercicio ejercicio8 = new SubEntidad_Ejercicio();
		ejercicio8.setNombre("Plancha lateral");
		ejercicio8.setDescripcion("Ejercicio para oblicuos");
		ejercicio8.setTipo("Fuerza");
		ejercicio8.setMusculosTrabajados("Oblicuos");
		ejercicio8.setMaterial("Ninguno");
		ejercicio8.setDificultad("Media");
		ejercicio8.setMultimedia("https://www.youtube.com/watch?v=ASdvN_XEl_c");
		repoEjercicio.save(ejercicio8);

		//Ejercicio 9
		SubEntidad_Ejercicio ejercicio9 = new SubEntidad_Ejercicio();
		ejercicio9.setNombre("Dominadas agarre cerrado");
		ejercicio9.setDescripcion("Ejercicio para biceps");
		ejercicio9.setTipo("Fuerza");
		ejercicio9.setMusculosTrabajados("Biceps");
		ejercicio9.setMaterial("Barra");
		ejercicio9.setDificultad("Alta");
		ejercicio9.setMultimedia("https://www.youtube.com/watch?v=8jyhJUd2Z4U");
		repoEjercicio.save(ejercicio9);
		
		//Ejercicio 10
		SubEntidad_Ejercicio ejercicio10 = new SubEntidad_Ejercicio();
		ejercicio10.setNombre("Burpees con flexion");
		ejercicio10.setDescripcion("Ejercicio para todo el cuerpo");
		ejercicio10.setTipo("Fuerza");
		ejercicio10.setMusculosTrabajados("Todo el cuerpo");
		ejercicio10.setMaterial("Ninguno");
		ejercicio10.setDificultad("Alta");
		ejercicio10.setMultimedia("https://www.youtube.com/watch?v=JZQA08SlJnM");
		repoEjercicio.save(ejercicio10);
		
		//Ejercicio 11
		SubEntidad_Ejercicio ejercicio11 = new SubEntidad_Ejercicio();
		ejercicio11.setNombre("Zancadas con salto");
		ejercicio11.setDescripcion("Ejercicio para piernas");
		ejercicio11.setTipo("Fuerza");
		ejercicio11.setMusculosTrabajados("Piernas");
		ejercicio11.setMaterial("Ninguno");
		ejercicio11.setDificultad("Media");
		ejercicio11.setMultimedia("https://www.youtube.com/watch?v=QKKZ9AGYTi4");
		repoEjercicio.save(ejercicio11);
		

		



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
