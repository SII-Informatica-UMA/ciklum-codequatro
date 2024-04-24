package CodeQuatro.Entidades_Cicklum;


//CLASE PARA IMPLEMENTAR EL INTREPETE DE LINEA DE COMANDOS PARA HACER BUSQUEDAS


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;
import CodeQuatro.Entidades_Cicklum.repositories.EjerciciosRepository;

@Component
public class LineaComandos implements CommandLineRunner {
	private EjerciciosRepository repository;
	public LineaComandos(EjerciciosRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		for (String s: args) {
			System.out.println(s);
		}

		if (args.length > 0) {
			for (Ejercicios e: repository.findEjercicioByName(args[0])) {
				System.out.println("hola");
			}
		}
	}

}
