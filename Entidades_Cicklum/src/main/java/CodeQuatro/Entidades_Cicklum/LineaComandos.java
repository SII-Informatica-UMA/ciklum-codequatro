package CodeQuatro.Entidades_Cicklum;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//CLASE PARA IMPLEMENTAR EL INTREPETE DE LINEA DE COMANDOS PARA HACER BUSQUEDAS


import org.springframework.boot.CommandLineRunner;
import org.springframework.transaction.annotation.Transactional;

import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
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
		

	}

}
