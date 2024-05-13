package CodeQuatro.Entidades_Cicklum;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.http.MediaType;
import static org.assertj.core.api.Assertions.assertThat;

import CodeQuatro.Entidades_Cicklum.dtos.RutinasDTO;
import CodeQuatro.Entidades_Cicklum.entities.AccesoDatos;
import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
import CodeQuatro.Entidades_Cicklum.repositories.RutinasRepository;
import CodeQuatro.Entidades_Cicklum.servicios.EntidadesCicklumApplication;

@SpringBootTest(classes=EntidadesCicklumApplication.class)
class EntidadesCicklumApplicationTests {

 private AccesoDatos ad;
    
	@Autowired
	private TestRestTemplate restTemplate;

	@Value(value="${local.server.port}")
	private int port;

    
    @Autowired
	private RutinasRepository rutinaRepository;

    @BeforeEach
	public void initializeDatabase() {
		rutinaRepository.deleteAll();
	}

    @AfterEach
    public void teardown() {
        ad.close();
    }


    // metodos auxiliares
    private URI uri(String scheme, String host, int port, String ...paths) {
		UriBuilderFactory ubf = new DefaultUriBuilderFactory();
		UriBuilder ub = ubf.builder()
				.scheme(scheme)
				.host(host).port(port);
		for (String path: paths) {
			ub = ub.path(path);
		}
		return ub.build();
	}

	private RequestEntity<Void> get(String scheme, String host, int port, String path) {
		URI uri = uri(scheme, host,port, path);
		var peticion = RequestEntity.get(uri)
				.accept(MediaType.APPLICATION_JSON)
				.build();
		return peticion;
	}

	private RequestEntity<Void> delete(String scheme, String host, int port, String path) {
		URI uri = uri(scheme, host,port, path);
		var peticion = RequestEntity.delete(uri)
				.build();
		return peticion;
	}

	private <T> RequestEntity<T> post(String scheme, String host, int port, String path, T object) {
		URI uri = uri(scheme, host,port, path);
		var peticion = RequestEntity.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.body(object);
		return peticion;
	}

	private <T> RequestEntity<T> put(String scheme, String host, int port, String path, T object) {
		URI uri = uri(scheme, host,port, path);
		var peticion = RequestEntity.put(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.body(object);
		return peticion;
	}


	private void compruebaCampos(Rutinas expected, Rutinas actual) {	
		assertThat(actual.getNombre()).isEqualTo(expected.getNombre());
		assertThat(actual.getDescripcion()).isEqualTo(expected.getDescripcion());
		assertThat(actual.getEjercicios()).isEqualTo(expected.getEjercicios());
	}


	@Disabled
    @Nested
    @DisplayName("cuando la base de datos esta vacía")
    public class BaseDatosVacia {
        
        @Test // 1 get de todas las rutinas
        @DisplayName("consulta todas las rutinas")
        public void devuelveListaVaciaRutinas() {
		var peticion = get("http", "localhost",port, "/rutinas");
			
		var respuesta = restTemplate.exchange(peticion,
				new ParameterizedTypeReference<List<Rutinas>>() {});
			
		assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
		assertThat(respuesta.getBody()).isEmpty();
		}

        @Test // 2 post de todas las rutinas (crea una nueva rutina)
		@DisplayName("inserta correctamente una rutina")
		public void insertarRutina() {

			// Preparamos la rutina a insertar
			var rutina = RutinasDTO.builder()
					.nombre("Rutina")
					.build();
			// Preparamos la petición con el ingrediente dentro
			var peticion = post("http", "localhost",port, "/rutinas", rutina);

			// Invocamos al servicio REST 
			var respuesta = restTemplate.exchange(peticion,Void.class);

			// Comprobamos el resultado
			assertThat(respuesta.getStatusCode().value()).isEqualTo(201);
			assertThat(respuesta.getHeaders().get("Location").get(0))
			.startsWith("http://localhost:"+port+"/rutinas");

			List<Rutinas> rutinasBD = rutinaRepository.findAll();
			assertThat(rutinasBD).hasSize(1);
			assertThat(respuesta.getHeaders().get("Location").get(0))
			.endsWith("/"+rutinasBD.get(0).getIdRutinas());
			compruebaCampos(rutina.rutina(), rutinasBD.get(0));
		}

        @Test // 3 get de una rutina concreta
		@DisplayName("devuelve error al acceder a una rutina concreta")
		public void errorConRutinaConcreta() {
			var peticion = get("http", "localhost",port, "/rutinas/1");
			
			var respuesta = restTemplate.exchange(peticion,
					new ParameterizedTypeReference<RutinasDTO>() {});
			
			assertThat(respuesta.getStatusCode().value()).isEqualTo(404);  // comprueba el resultado - 404 no encontrado
		}


        @Test // 4 put de una rutina (modifica una rutina)
		@DisplayName("devuelve error al modificar una rutina que no existe")
		public void modificarRutinaInexistente() {
			var rutina = RutinasDTO.builder().nombre("Rutina").build();
			var peticion = put("http", "localhost",port, "/rutinas/1", rutina);

			var respuesta = restTemplate.exchange(peticion, Void.class);

			assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
		}

        @Test // 5 delete de una rutina
		@DisplayName("devuelve error al eliminar una rutina que no existe")
		public void eliminarRutinaInexistente() {
			var peticion = delete("http", "localhost",port, "/rutinas/1");

			var respuesta = restTemplate.exchange(peticion, Void.class);

			assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
		}

    }
}