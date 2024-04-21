package CodeQuatro.Entidades_Cicklum;


//CLASE PARA IMPLEMENTAR EL INTREPETE DE LINEA DE COMANDOS PARA HACER BUSQUEDAS


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/* 

@Component
public class LineaComandos implements CommandLineRunner {
	private BookRepository repository;
	public LineaComandos(BookRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		for (String s: args) {
			System.out.println(s);
		}

		if (args.length > 0) {
			for (Book b: repository.findByNombre(args[0])) {
				System.out.println(b);
			}
		}
	}

}
*/