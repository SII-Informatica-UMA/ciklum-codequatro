package CodeQuatro.Entidades_Cicklum;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;

import CodeQuatro.Entidades_Cicklum.dtos.EjerciciosDTO;
import CodeQuatro.Entidades_Cicklum.dtos.RutinasDTO;
import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;
import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
import CodeQuatro.Entidades_Cicklum.repositories.EjerciciosRepository;
import CodeQuatro.Entidades_Cicklum.repositories.RutinasRepository;
import CodeQuatro.Entidades_Cicklum.security.JwtUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = EntidadesCicklumApplication.class)
@DisplayName("------En el servicio Cicklum--------")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class EntidadesCicklumApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Value(value = "${local.server.port}")
	private int port;

	@Autowired
	private RutinasRepository rutinaRepository;
	@Autowired
	private EjerciciosRepository ejerciciosRepository;

	private final BCryptPasswordEncoder encoder2 = new BCryptPasswordEncoder();

	
	@Autowired
	private JwtUtil jwtUtil;
	private UserDetails userDetails;
	private String token;
	

	@BeforeEach
	public void initializeDatabase() {
		rutinaRepository.deleteAll();
		ejerciciosRepository.deleteAll();
		 userDetails = jwtUtil.createUserDetails("1", "", List.of("ROLE_USER"));
		 token = jwtUtil.generateToken(userDetails);
	}


	// metodos auxiliares
	private URI uri(String scheme, String host, int port, String... paths) {

		UriBuilderFactory ubf = new DefaultUriBuilderFactory();
		UriBuilder ub = ubf.builder()
				.scheme(scheme)
				.host(host).port(port);
		for (String path : paths) {
			ub = ub.path(path);
		}
		return ub.build();
	}

	private RequestEntity<Void> get(String scheme, String host, int port, String path) {

			URI uri = uri(scheme, host, port, path);
			//String token = jwtUtil.doGenerateToken(null, null);
			var peticion = RequestEntity.get(uri)
					.accept(MediaType.APPLICATION_JSON)
					.header("Authorization", "Bearer " + token)
				.build();
			System.out.println("TOKEN -----------------"+token);
		return peticion;
	}

	private RequestEntity<Void> delete(String scheme, String host, int port, String path) {
		URI uri = uri(scheme, host, port, path);
		var peticion = RequestEntity.delete(uri)
				.header("Authorization", "Bearer " + token)
				.build();
		return peticion;
	}

	private <T> RequestEntity<T> post(String scheme, String host, int port, String path, T object) {
		URI uri = uri(scheme, host, port, path);
		var peticion = RequestEntity.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + token)
				.body(object);
		return peticion;
	}

	private <T> RequestEntity<T> put(String scheme, String host, int port, String path, T object) {
		URI uri = uri(scheme, host, port, path);
		var peticion = RequestEntity.put(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + token)
				.body(object);
		return peticion;
	}

	private void compruebaCampos(Rutinas expected, Rutinas actual) {
		assertThat(actual.getNombre()).isEqualTo(expected.getNombre());
		assertThat(actual.getDescripcion()).isEqualTo(expected.getDescripcion());
	}

	private void compruebaCampos(Ejercicios expected, Ejercicios actual) {
		assertThat(actual.getNombre()).isEqualTo(expected.getNombre());
		assertThat(actual.getDescripcion()).isEqualTo(expected.getDescripcion());

	}
	/* BASE DE DATOS VACIA */
	@Nested
	@DisplayName("cuando la base de datos esta vacía")
	public class BaseDatosVacia {

		// -----------------MÉTODOS GET-----------------
		@Test
		@DisplayName("Devuelve error al obtener un ejercicio concreto")
		public void errorConEjercicioConcreto() {
			var peticion = get("http", "localhost", port, "/ejercicios/1");

			var respuesta = restTemplate.exchange(peticion,
					new ParameterizedTypeReference<EjerciciosDTO>() {
					});

			assertThat(respuesta.getStatusCode().value()).isEqualTo(404); // comprueba el resultado - 404 no encontrado
		}

		// -----------------MÉTODOS PUT-----------------
		@Test
		@DisplayName("Devuelve error al modificar un ejercicio que no existe")
		public void modificarEjercicioInexistente() {
			var ejercicio = EjerciciosDTO.builder().nombre("Sentadilla").build();
			var peticion = put("http", "localhost", port, "/ejercicios/1", ejercicio);

			var respuesta = restTemplate.exchange(peticion, Void.class);

			assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
		}

		// Metodos Delete
		@Test
		@DisplayName("devuelve error al eliminar un ejercicio que no existe")
		public void eliminarEjercicioInexistente() {
			var peticion = delete("http", "localhost", port, "/ejercicios/1");

			var respuesta = restTemplate.exchange(peticion, Void.class);

			assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
		}

		// -----------------MÉTODOS POST-----------------
		@Test
		@DisplayName("Inserta un ejercicio correctamente")
		public void insertaEjercicio() {
			var ejercicio = EjerciciosDTO.builder()
					.nombre("Curl de biceps")
					.descripcion("descripcion")
					.build();

					
			var peticion = post("http", "localhost", port, "/ejercicios", ejercicio);

			var respuesta = restTemplate.exchange(peticion, Void.class);

			assertThat(respuesta.getStatusCode().value()).isEqualTo(201);
			assertThat(respuesta.getHeaders().get("Location").get(0))
					.startsWith("http://localhost:" + port + "/ejercicios");

			List<Ejercicios> ejerciciosBD = ejerciciosRepository.findAll();
			assertThat(ejerciciosBD).hasSize(1);
			assertThat(respuesta.getHeaders().get("Location").get(0))
					.endsWith("/" + ejerciciosBD.get(0).getIdEjercicio());
			compruebaCampos(ejercicio.ejercicios(), ejerciciosBD.get(0));
		}

		@Test
		@DisplayName("Devuelve error al insertar un ejercicio cuando no aporto informacion sobre el mismo")
		public void errorInsertarEjercicioSinInformacion() {
			var ejercicio = EjerciciosDTO.builder().build();
			var peticion = post("http", "localhost", port, "/ejercicios", ejercicio);

			var respuesta = restTemplate.exchange(peticion, Void.class);

			assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
		}
		// Tests de las rutinas
	}


	/*---------------- BASE DE DATOS CON DATOS ---------------------*/


	@Nested
	@DisplayName("----------cuando la base de datos contiene datos----------")
	public class BaseDatosConDatos {

		/*INSERCION DE DATOS PARA LOS TEST */

		@BeforeEach
		public void insertaEjercicio() {
			var sentadilla = new Ejercicios();
			sentadilla.setNombre("sentadilla");
		sentadilla.setDescripcion("descripcion");
		sentadilla.setDificultad("dificultad");
		sentadilla.setMusculosTrabajados("musculos");
		sentadilla.setMaterial("material");
		sentadilla.setTipo("tipo");
		sentadilla.setMultimedia(null);
			ejerciciosRepository.save(sentadilla);
			/*
			 * var rutina1 = new Rutinas();
			 * rutina1.setNombre("Calentamiento");
			 * rutina1.setEjercicios(Collections.singletonList(flexiones));
			 */
		}

		/* TESTS  */

		// -----------------MÉTODOS POST-----------------
		@Test
		@DisplayName("Devuelve error al insertar un ejercicio que ya existe")
		public void errorConEjercicioExistente() {
			var ejercicio = EjerciciosDTO.builder().nombre("Flexiones").build();
			var peticion = post("http", "localhost", port, "/ejercicios", ejercicio);
			var respuesta = restTemplate.exchange(peticion, Void.class);
			assertThat(respuesta.getStatusCode().value()).isEqualTo(409);
		}
	}

	

	// ----------METODOS GET----------
	@Test
	@DisplayName("Devuelve un ejercicio correctamente")
	public void devuelveEjercicio() {
		var peticion = get("http", "localhost", port, "/ejercicios/1");
		var respuesta = restTemplate.exchange(peticion,
				new ParameterizedTypeReference<EjerciciosDTO>() {
				});

		assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
		assertThat(respuesta.getBody().getNombre()).isEqualTo("Flexiones");
	}

	@Test
	@DisplayName("Devuelve una lista de ejercicios")
	public void devuelveListaEjercicios() {
		var peticion = get("http", "localhost", port, "/ejercicios");
		var respuesta = restTemplate.exchange(peticion,
				new ParameterizedTypeReference<List<EjerciciosDTO>>() {
				});

		assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
		assertThat(respuesta.getBody()).hasSize(1);

	}

	// -----------------MÉTODOS PUT-----------------
	@Test
	@DisplayName("Modifica un ejercicio correctamente")
	public void modificarEjercicio() {
		var ejercicio = EjerciciosDTO.builder().nombre("Flexiones").build();
		var peticion = put("http", "localhost", port, "/ejercicios/1", ejercicio);

		var respuesta = restTemplate.exchange(peticion, Void.class);

		assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
		assertThat(ejerciciosRepository.findById(1L).get().getNombre()).isEqualTo("Sentadilla");
	}

	// Metodos Delete
	@Test
	@DisplayName("Elimina un ejercicio correctamente")
	public void eliminarEjercicio() {
		var sentadilla = new Ejercicios();
		sentadilla.setNombre("sentadilla");
		sentadilla.setDescripcion("descripcion");
		sentadilla.setDificultad("dificultad");
		sentadilla.setMusculosTrabajados("musculos");
		sentadilla.setMaterial("material");
		sentadilla.setTipo("tipo");
		sentadilla.setMultimedia(null);
		ejerciciosRepository.save(sentadilla);
		var peticion = delete("http", "localhost", port, "/ejercicios/2");

		var respuesta = restTemplate.exchange(peticion, Void.class);

		assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
		assertThat(ejerciciosRepository.count()).isEqualTo(1);
	}

	
	  @Test // 1 get de todas las rutinas
	  
	  @DisplayName("consulta todas las rutinas")
	  public void devuelveListaVaciaRutinas() {
	  var peticion = get("http", "localhost",port, "/rutinas");
	  
	  var respuesta = restTemplate.exchange(peticion,
	  new ParameterizedTypeReference<List<Rutinas>>() {});
	  
	  assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
	  assertThat(respuesta.getBody()).isEmpty();
	  }
	 /* 
	 * @Test // 2 post de todas las rutinas (crea una nueva rutina)
	 * 
	 * @DisplayName("inserta correctamente una rutina")
	 * public void insertarRutina() {
	 * 
	 * // Preparamos la rutina a insertar
	 * var rutina = RutinasDTO.builder()
	 * .nombre("Rutina")
	 * .build();
	 * // Preparamos la petición con el ingrediente dentro
	 * var peticion = post("http", "localhost",port, "/rutinas", rutina);
	 * 
	 * // Invocamos al servicio REST
	 * var respuesta = restTemplate.exchange(peticion,Void.class);
	 * 
	 * // Comprobamos el resultado
	 * assertThat(respuesta.getStatusCode().value()).isEqualTo(201);
	 * assertThat(respuesta.getHeaders().get("Location").get(0))
	 * .startsWith("http://localhost:"+port+"/rutinas");
	 * 
	 * List<Rutinas> rutinasBD = rutinaRepository.findAll();
	 * assertThat(rutinasBD).hasSize(1);
	 * assertThat(respuesta.getHeaders().get("Location").get(0))
	 * .endsWith("/"+rutinasBD.get(0).getIdRutinas());
	 * compruebaCampos(rutina.rutina(), rutinasBD.get(0));
	 * }
	 * 
	 * @Test // 3 get de una rutina concreta
	  
	  @DisplayName("devuelve error al acceder a una rutina concreta")
	  public void errorConRutinaConcreta() {
	  var peticion = get("http", "localhost",port, "/rutinas/1");
	  
	  var respuesta = restTemplate.exchange(peticion,
	  new ParameterizedTypeReference<RutinasDTO>() {});
	  
	  assertThat(respuesta.getStatusCode().value()).isEqualTo(404); // comprueba el resultado - 404 no encontrado
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
	 */
}
