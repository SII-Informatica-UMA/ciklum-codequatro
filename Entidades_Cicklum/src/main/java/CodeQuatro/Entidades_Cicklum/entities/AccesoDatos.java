package CodeQuatro.Entidades_Cicklum.entities;

import java.io.Closeable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Esta clase servirá para centralizar todas las operaciones relacionadas con la 
 * gestión de los datos.
 * Recuerde que las operaciones que modifiquen la base de datos deben ejecutarse dentro
 * de una transacción.
 *
 */
public class AccesoDatos implements Closeable {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	/**
	 * Constructor por defecto. Crea un contexto de persistencia.
	 */
	public AccesoDatos() {
		emf = Persistence.createEntityManagerFactory("ciklum_codequatro");
		em = emf.createEntityManager();
	}
	
	/**
	 * Cierra el contexto de persistencia.
	 */
	@Override
	public void close() {
		em.close();
		emf.close();
	}	
}
