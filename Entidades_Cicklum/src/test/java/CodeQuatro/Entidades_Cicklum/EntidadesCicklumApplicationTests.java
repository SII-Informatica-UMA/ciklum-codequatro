package CodeQuatro.Entidades_Cicklum;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;

import CodeQuatro.Entidades_Cicklum.dtos.EjercicioNuevoDTO;
import CodeQuatro.Entidades_Cicklum.dtos.EjerciciosDTO;
import CodeQuatro.Entidades_Cicklum.dtos.FragmentoRutinaDTO;
import CodeQuatro.Entidades_Cicklum.dtos.RutinasDTO;
import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;
import CodeQuatro.Entidades_Cicklum.entities.FragmentoRutina;
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

	//private final BCryptPasswordEncoder encoder2 = new BCryptPasswordEncoder();

	
	@Autowired
	private JwtUtil jwtUtil;
	private UserDetails userDetails;
	private String token;
	

	@BeforeEach
	public void initializeDatabase() {
		rutinaRepository.deleteAll();
		ejerciciosRepository.deleteAll();
		userDetails = jwtUtil.createUserDetails("1", "", List.of("ROLE_USER"));
		token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzE2NDczMTc3LCJleHAiOjE3MjAwNzMxNzd9.7tWskuEFkvIuPKHSyy9wTOczfK9LcwvV1sqhghyMAsImtNkL2KJZPpzG-e7SUF8ks-SI7rKkA7fBBU71MOCc4g";
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
			var peticion = get("http", "localhost", port, "/ejercicio/1"); // entrenador=1");

			var respuesta = restTemplate.exchange(peticion,
					new ParameterizedTypeReference<EjerciciosDTO>() {
					});

			assertThat(respuesta.getStatusCode().value()).isEqualTo(404); // comprueba el resultado - 404 no encontrado
		}

		@Test
		@DisplayName("Devuelve una lista vacía de ejercicios")
		public void devuelveListaVaciaEjercicios() {
			var peticion = get("http", "localhost", port, "/ejercicio"); //?entrenador=1");

			var respuesta = restTemplate.exchange(peticion,
					new ParameterizedTypeReference<EjerciciosDTO>() {});
			
			System.out.println("-------RESPUESTA----------" + respuesta);
			assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
			//assertThat(respuesta.getBody());
			//assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		}

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

		@Test
		@DisplayName("Test hashCode con el mismo objeto ejercicio")
		public void testHashCodeEjercicios() {
			Ejercicios ejercicio = new Ejercicios(
				1L, "Push-up", "Ejercicio de pecho", "Realizar con espalda recta",
				"Calistenia", "Pecho, Tríceps", "Ninguno", "Fácil",
				null, 1L
			);
	
			assertEquals(Long.hashCode(1L), ejercicio.hashCode());
		}
		
		@Test
		@DisplayName("Test Equals con el mismo objeto")
		public void testCrearEjercicioConTodosLosCampos() {
			Ejercicios ejercicio = new Ejercicios(
				1L, "Push-up", "Ejercicio de pecho", "Realizar con espalda recta",
				"Calistenia", "Pecho, Tríceps", "Ninguno", "Fácil",
				Arrays.asList("video1.mp4", "imagen1.jpg"), 1L
			);
	
			assertEquals(1L, ejercicio.getIdEjercicio());
			assertEquals("Push-up", ejercicio.getNombre());
			assertEquals("Ejercicio de pecho", ejercicio.getDescripcion());
			assertEquals("Realizar con espalda recta", ejercicio.getObservaciones());
			assertEquals("Calistenia", ejercicio.getTipo());
			assertEquals("Pecho, Tríceps", ejercicio.getMusculosTrabajados());
			assertEquals("Ninguno", ejercicio.getMaterial());
			assertEquals("Fácil", ejercicio.getDificultad());
			assertEquals(Arrays.asList("video1.mp4", "imagen1.jpg"), ejercicio.getMultimedia());
			assertEquals(1L, ejercicio.getIdEntrenador());
		}

		@Test
		@DisplayName("Test Equals con el mismo objeto")
		public void testIgualdadEjercicios() {
			Ejercicios ejercicio1 = new Ejercicios(
				1L, "Push-up", "Ejercicio de pecho", "Realizar con espalda recta",
				"Calistenia", "Pecho, Tríceps", "Ninguno", "Fácil",
				Arrays.asList("video1.mp4", "imagen1.jpg"), 1L
			);
	
			Ejercicios ejercicio2 = new Ejercicios(
				1L, "Push-up", "Ejercicio de pecho", "Realizar con espalda recta",
				"Calistenia", "Pecho, Tríceps", "Ninguno", "Fácil",
				Arrays.asList("video1.mp4", "imagen1.jpg"), 1L
			);
	
			assertEquals(ejercicio1, ejercicio2);
		}
		
		 @Test
		 @DisplayName("Test rutina con todos los campos")
    public void testCrearRutinaConTodosLosCampos() {
        FragmentoRutina fragmento = new FragmentoRutina(1L, 3L, 12L, 30L, null);
        Rutinas rutina = new Rutinas(
            1L, "Rutina de pecho", "Rutina para desarrollar el pecho",
            "Realizar con moderación", 1L, Arrays.asList(fragmento)
        );

        assertEquals(1L, rutina.getIdRutinas());
        assertEquals("Rutina de pecho", rutina.getNombre());
        assertEquals("Rutina para desarrollar el pecho", rutina.getDescripcion());
        assertEquals("Realizar con moderación", rutina.getObservaciones());
        assertEquals(1L, rutina.getIdEntrenador());
        assertEquals(Arrays.asList(fragmento), rutina.getFragmentoRutina());
    }

	@Test
	@DisplayName("Test Equals con el mismo objeto")
    public void testIgualdadRutinas() {
        FragmentoRutina fragmento = new FragmentoRutina(1L, 3L, 12L, 30L, null);

        Rutinas rutina1 = new Rutinas(
            1L, "Rutina de pecho", "Rutina para desarrollar el pecho",
            "Realizar con moderación", 1L, Arrays.asList(fragmento)
        );

        Rutinas rutina2 = new Rutinas(
            1L, "Rutina de pecho", "Rutina para desarrollar el pecho",
            "Realizar con moderación", 1L, Arrays.asList(fragmento)
        );

        assertEquals(rutina1, rutina2);
    }

	@Test
	@DisplayName("Test getters y setters Ejercicios")
    public void testGettersAndSetters() {
        Ejercicios ejercicio = new Ejercicios();
        List<String> multimediaList = Arrays.asList("video1.mp4", "imagen1.jpg");

        ejercicio.setIdEjercicio(1L);
        ejercicio.setNombre("Push-up");
        ejercicio.setDescripcion("Ejercicio de pecho");
        ejercicio.setObservaciones("Realizar con espalda recta");
        ejercicio.setTipo("Calistenia");
        ejercicio.setMusculosTrabajados("Pecho, Tríceps");
        ejercicio.setMaterial("Ninguno");
        ejercicio.setDificultad("Fácil");
        ejercicio.setMultimedia(multimediaList);
        ejercicio.setIdEntrenador(1L);

        assertEquals(1L, ejercicio.getIdEjercicio());
        assertEquals("Push-up", ejercicio.getNombre());
        assertEquals("Ejercicio de pecho", ejercicio.getDescripcion());
        assertEquals("Realizar con espalda recta", ejercicio.getObservaciones());
        assertEquals("Calistenia", ejercicio.getTipo());
        assertEquals("Pecho, Tríceps", ejercicio.getMusculosTrabajados());
        assertEquals("Ninguno", ejercicio.getMaterial());
        assertEquals("Fácil", ejercicio.getDificultad());
        assertEquals(multimediaList, ejercicio.getMultimedia());
        assertEquals(1L, ejercicio.getIdEntrenador());
    }

	@Test
	@DisplayName("Test toString Ejercicios")
    public void testToString() {
        Ejercicios ejercicio = new Ejercicios(
            1L, "Push-up", "Ejercicio de pecho", "Realizar con espalda recta",
            "Calistenia", "Pecho, Tríceps", "Ninguno", "Fácil",
            Arrays.asList("video1.mp4", "imagen1.jpg"), 1L
        );

        String expected = "Ejercicio [id= 1, nombre= Push-up, descripcion= Ejercicio de pechoObservaciones= Realizar con espalda recta, tipo= Calistenia, musculos trabajados= Pecho, Tríceps, material= Ninguno, dificultad= Fácil, multimedia= [video1.mp4imagen1.jpg]]";
        assertEquals(expected, ejercicio.toString());
    }

	@Test
	@DisplayName("Test multimedia Ejercicios")
    public void testMultimedia() {
        List<String> multimediaList = new ArrayList<>();
        multimediaList.add("video1.mp4");

        Ejercicios ejercicio = new Ejercicios();
        ejercicio.setMultimedia(multimediaList);

        // Verificar que el elemento se añadió correctamente
        assertEquals(1, ejercicio.getMultimedia().size());
        assertTrue(ejercicio.getMultimedia().contains("video1.mp4"));

        // Añadir otro elemento
        ejercicio.getMultimedia().add("imagen1.jpg");
        assertEquals(2, ejercicio.getMultimedia().size());
        assertTrue(ejercicio.getMultimedia().contains("imagen1.jpg"));

        // Eliminar un elemento
        ejercicio.getMultimedia().remove("video1.mp4");
        assertEquals(1, ejercicio.getMultimedia().size());
        assertFalse(ejercicio.getMultimedia().contains("video1.mp4"));
    }

	  @Test
	  @DisplayName("Test del constructor por defecto de Ejercicios")
    public void testDefaultConstructor() {
        Ejercicios ejercicio = new Ejercicios();

        assertNull(ejercicio.getIdEjercicio());
        assertNull(ejercicio.getNombre());
        assertNull(ejercicio.getDescripcion());
        assertNull(ejercicio.getObservaciones());
        assertNull(ejercicio.getTipo());
        assertNull(ejercicio.getMusculosTrabajados());
        assertNull(ejercicio.getMaterial());
        assertNull(ejercicio.getDificultad());
        assertNull(ejercicio.getMultimedia());
        assertNull(ejercicio.getIdEntrenador());
    }

	@Test
	@DisplayName("Test del constructor completo de Ejercicios")
    public void testFullConstructor() {
        Ejercicios ejercicio = new Ejercicios(
            1L, "Push-up", "Ejercicio de pecho", "Realizar con espalda recta",
            "Calistenia", "Pecho, Tríceps", "Ninguno", "Fácil",
            Arrays.asList("video1.mp4", "imagen1.jpg"), 1L
        );

        assertEquals(1L, ejercicio.getIdEjercicio());
        assertEquals("Push-up", ejercicio.getNombre());
        assertEquals("Ejercicio de pecho", ejercicio.getDescripcion());
        assertEquals("Realizar con espalda recta", ejercicio.getObservaciones());
        assertEquals("Calistenia", ejercicio.getTipo());
        assertEquals("Pecho, Tríceps", ejercicio.getMusculosTrabajados());
        assertEquals("Ninguno", ejercicio.getMaterial());
        assertEquals("Fácil", ejercicio.getDificultad());
        assertEquals(Arrays.asList("video1.mp4", "imagen1.jpg"), ejercicio.getMultimedia());
        assertEquals(1L, ejercicio.getIdEntrenador());
    }


	@Test
	@DisplayName("Test null multimedia")
    public void testNullMultimedia() {
        Ejercicios ejercicio = new Ejercicios();
        ejercicio.setMultimedia(null);
        assertNull(ejercicio.getMultimedia());

        // Setting an empty list
        ejercicio.setMultimedia(Collections.emptyList());
        assertTrue(ejercicio.getMultimedia().isEmpty());
    }

	@Test
	@DisplayName("Test null values")
    public void testNullValues() {
        Ejercicios ejercicio = new Ejercicios();
        ejercicio.setIdEjercicio(null);
        ejercicio.setNombre(null);
        ejercicio.setDescripcion(null);
        ejercicio.setObservaciones(null);
        ejercicio.setTipo(null);
        ejercicio.setMusculosTrabajados(null);
        ejercicio.setMaterial(null);
        ejercicio.setDificultad(null);
        ejercicio.setMultimedia(null);
        ejercicio.setIdEntrenador(null);

        assertNull(ejercicio.getIdEjercicio());
        assertNull(ejercicio.getNombre());
        assertNull(ejercicio.getDescripcion());
        assertNull(ejercicio.getObservaciones());
        assertNull(ejercicio.getTipo());
        assertNull(ejercicio.getMusculosTrabajados());
        assertNull(ejercicio.getMaterial());
        assertNull(ejercicio.getDificultad());
        assertNull(ejercicio.getMultimedia());
        assertNull(ejercicio.getIdEntrenador());
    }

	@Test
	@DisplayName("Test getter de rutinas")
    public void testRutinasCreationAndGetters() {
        FragmentoRutina fragmento1 = new FragmentoRutina(1L, 3L, 15L, 10L, null);
        FragmentoRutina fragmento2 = new FragmentoRutina(2L, 4L, 12L, 8L, null);

        Rutinas rutina = new Rutinas(
            1L, "Rutina de fuerza", "Rutina para desarrollar fuerza",
            "Realizar con buena técnica", 1L,
            Arrays.asList(fragmento1, fragmento2)
        );

        assertEquals(1L, rutina.getIdRutinas());
        assertEquals("Rutina de fuerza", rutina.getNombre());
        assertEquals("Rutina para desarrollar fuerza", rutina.getDescripcion());
        assertEquals("Realizar con buena técnica", rutina.getObservaciones());
        assertEquals(1L, rutina.getIdEntrenador());
        assertEquals(2, rutina.getFragmentoRutina().size());
    }

	@Test
	@DisplayName("Test setters de rutinas")
    public void testRutinasSetters() {
        Rutinas rutina = new Rutinas();

        FragmentoRutina fragmento1 = new FragmentoRutina(1L, 3L, 15L, 10L, null);
        FragmentoRutina fragmento2 = new FragmentoRutina(2L, 4L, 12L, 8L, null);

        rutina.setIdRutinas(2L);
        rutina.setNombre("Rutina de resistencia");
        rutina.setDescripcion("Rutina para mejorar la resistencia");
        rutina.setObservaciones("Realizar con cuidado");
        rutina.setIdEntrenador(2L);
        rutina.setFragmentoRutina(Arrays.asList(fragmento1, fragmento2));

        assertEquals(2L, rutina.getIdRutinas());
        assertEquals("Rutina de resistencia", rutina.getNombre());
        assertEquals("Rutina para mejorar la resistencia", rutina.getDescripcion());
        assertEquals("Realizar con cuidado", rutina.getObservaciones());
        assertEquals(2L, rutina.getIdEntrenador());
        assertEquals(2, rutina.getFragmentoRutina().size());
    }

	@Test
	@DisplayName("Test equals y hashCode de rutinas")
    public void testEqualsAndHashCode() {
        FragmentoRutina fragmento1 = new FragmentoRutina(1L, 3L, 15L, 10L, null);
        FragmentoRutina fragmento2 = new FragmentoRutina(2L, 4L, 12L, 8L, null);

        Rutinas rutina1 = new Rutinas(
            1L, "Rutina de fuerza", "Rutina para desarrollar fuerza",
            "Realizar con buena técnica", 1L,
            Arrays.asList(fragmento1, fragmento2)
        );

        Rutinas rutina2 = new Rutinas(
            1L, "Rutina de fuerza", "Rutina para desarrollar fuerza",
            "Realizar con buena técnica", 1L,
            Arrays.asList(fragmento1, fragmento2)
        );

        Rutinas rutina3 = new Rutinas(
            2L, "Rutina de resistencia", "Rutina para mejorar la resistencia",
            "Realizar con cuidado", 2L,
            Arrays.asList(fragmento1, fragmento2)
        );

        assertEquals(rutina1, rutina2);
        assertNotEquals(rutina1, rutina3);
        assertEquals(rutina1.hashCode(), rutina2.hashCode());
        assertNotEquals(rutina1.hashCode(), rutina3.hashCode());
    }

	@Test
	@DisplayName("Test constructor con parametros Ejercicios")
    public void testConstructorWithParametersEjercicios() {
        Ejercicios ejercicio = new Ejercicios(
            1L, "Push Up", "Ejercicio de pecho", "Realizar correctamente",
            "Calistenia", "Pectorales", "Ninguno", "Intermedio",
            Arrays.asList("video1.mp4", "video2.mp4"), 1L
        );

        assertEquals(1L, ejercicio.getIdEjercicio());
        assertEquals("Push Up", ejercicio.getNombre());
        assertEquals("Ejercicio de pecho", ejercicio.getDescripcion());
        assertEquals("Realizar correctamente", ejercicio.getObservaciones());
        assertEquals("Calistenia", ejercicio.getTipo());
        assertEquals("Pectorales", ejercicio.getMusculosTrabajados());
        assertEquals("Ninguno", ejercicio.getMaterial());
        assertEquals("Intermedio", ejercicio.getDificultad());
        assertEquals(2, ejercicio.getMultimedia().size());
        assertEquals(1L, ejercicio.getIdEntrenador());
    }

    @Test
	@DisplayName("Test toString de ejercicios")
    public void testToStringEjercicios() {
        Ejercicios ejercicio = new Ejercicios(
            1L, "Push Up", "Ejercicio de pecho", "Realizar correctamente",
            "Calistenia", "Pectorales", "Ninguno", "Intermedio",
            Arrays.asList("video1.mp4", "video2.mp4"), 1L
        );
        String expected = "Ejercicio [id= 1, nombre= Push Up, descripcion= Ejercicio de pechoObservaciones= Realizar correctamente, tipo= Calistenia, musculos trabajados= Pectorales, material= Ninguno, dificultad= Intermedio, multimedia= [video1.mp4video2.mp4]]";
        assertEquals(expected, ejercicio.toString());
    }
	@Test
	@DisplayName("Test constructor con parametros de rutinas")
    public void testConstructorWithParametersRutinas() {
        FragmentoRutina fragmento1 = new FragmentoRutina(1L, 3L, 15L, 10L, null);
        FragmentoRutina fragmento2 = new FragmentoRutina(2L, 4L, 12L, 8L, null);

        Rutinas rutina = new Rutinas(
            1L, "Rutina de fuerza", "Rutina para desarrollar fuerza",
            "Realizar con buena técnica", 1L,
            Arrays.asList(fragmento1, fragmento2)
        );

        assertEquals(1L, rutina.getIdRutinas());
        assertEquals("Rutina de fuerza", rutina.getNombre());
        assertEquals("Rutina para desarrollar fuerza", rutina.getDescripcion());
        assertEquals("Realizar con buena técnica", rutina.getObservaciones());
        assertEquals(1L, rutina.getIdEntrenador());
        assertEquals(2, rutina.getFragmentoRutina().size());
    }

	@Test
	@DisplayName("Test constructor con parametros de fragmento rutina")
    public void testConstructorWithParametersFR() {
        Ejercicios ejercicio = new Ejercicios(
            1L, "Push Up", "Ejercicio de pecho", "Realizar correctamente",
            "Calistenia", "Pectorales", "Ninguno", "Intermedio",
            Arrays.asList("video1.mp4", "video2.mp4"), 1L
        );

        FragmentoRutina fragmento = new FragmentoRutina(
            1L, 3L, 15L, 10L, ejercicio
        );

        assertEquals(1L, fragmento.getIdFragmentoRutina());
        assertEquals(3L, fragmento.getNumSeries());
        assertEquals(15L, fragmento.getNumRepeticiones());
        assertEquals(10L, fragmento.getDuracionMinutos());
        assertEquals(ejercicio, fragmento.getEjercicios());
    }
	
    @Test
	@DisplayName("Test toString de rutinas")
    public void testToStringRutinas() {
        Rutinas rutina = new Rutinas(
            1L, "Rutina de fuerza", "Rutina para desarrollar fuerza",
            "Realizar con buena técnica", 1L,
            Arrays.asList(new FragmentoRutina(1L, 3L, 15L, 10L, null))
        );
        String expected = "Rutina [id= 1, nombre= Rutina de fuerza, descripcion= Rutina para desarrollar fuerza, observaciones= Realizar con buena técnica";
        assertEquals(expected, rutina.toString());
    }



	@Test
	@DisplayName("Test hashCode con el mismo objeto rutina")
    public void testHashCodeRutinas() {
        Rutinas rutina = new Rutinas(
            1L, "Rutina de pecho", "Rutina para desarrollar el pecho",
            "Realizar con moderación", 1L, null
        );

        assertEquals(Long.hashCode(1L), rutina.hashCode());
    }
		

	
		@Test
		@DisplayName("consulta todas las rutinas")
		public void devuelveListaVaciaRutinas() {
			var peticion = get("http", "localhost", port, "/rutinas");

			var respuesta = restTemplate.exchange(peticion,
					new ParameterizedTypeReference<List<Rutinas>>() {});

			assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
			assertThat(respuesta.getBody()).isEmpty();
		}

		@Test
		@DisplayName("Test setter y getter de la propiedad idEjercicio en la clase Ejercicios")
		public void testIdEjercicioSetterGetter() {
			Ejercicios ejercicio = new Ejercicios();
			ejercicio.setIdEjercicio(1L);
			assertEquals(1L, ejercicio.getIdEjercicio());
		}
	
		@Test
		@DisplayName("Test setter y getter de la propiedad idRutinas en la clase Rutinas")
		public void testIdRutinasSetterGetter() {
			Rutinas rutina = new Rutinas();
			rutina.setIdRutinas(1L);
			assertEquals(1L, rutina.getIdRutinas());
		}
	
		@Test
		@DisplayName("Test setter y getter de la propiedad idFragmentoRutina en la clase FragmentoRutina")
		public void testIdFragmentoRutinaSetterGetter() {
			FragmentoRutina fragmento = new FragmentoRutina();
			fragmento.setIdFragmentoRutina(1L);
			assertEquals(1L, fragmento.getIdFragmentoRutina());
		}
	
	
		@Test
		@DisplayName("Test equals y hashCode de Ejercicios")
		public void testEqualsAndHashCodeEjercicios() {
			Ejercicios ejercicio1 = new Ejercicios(1L, "Push Up", "Ejercicio de pecho", "Realizar correctamente",
				"Calistenia", "Pectorales", "Ninguno", "Intermedio",
				Arrays.asList("video1.mp4", "video2.mp4"), 1L);
	
			Ejercicios ejercicio2 = new Ejercicios(1L, "Push Up", "Ejercicio de pecho", "Realizar correctamente",
				"Calistenia", "Pectorales", "Ninguno", "Intermedio",
				Arrays.asList("video1.mp4", "video2.mp4"), 1L);
	
			assertEquals(ejercicio1, ejercicio2);
			assertEquals(ejercicio1.hashCode(), ejercicio2.hashCode());
		}
	
		@Test
		@DisplayName("Test equals y hashCode de Rutinas")
		public void testEqualsAndHashCodeRutinas() {
			Rutinas rutina1 = new Rutinas(1L, "Rutina de fuerza", "Rutina para desarrollar fuerza",
				"Realizar con buena técnica", 1L,
				Arrays.asList(new FragmentoRutina(1L, 3L, 15L, 10L, null)));
	
			Rutinas rutina2 = new Rutinas(1L, "Rutina de fuerza", "Rutina para desarrollar fuerza",
				"Realizar con buena técnica", 1L,
				Arrays.asList(new FragmentoRutina(1L, 3L, 15L, 10L, null)));
	
			assertEquals(rutina1, rutina2);
			assertEquals(rutina1.hashCode(), rutina2.hashCode());
		}
	@Test
	@DisplayName("Test entidad ejercicio nuevo DTO")
    public void testToEntity() {
        // Crear un objeto EjercicioNuevoDTO para usar en la prueba
        EjercicioNuevoDTO ejercicioDTO = EjercicioNuevoDTO.builderNuevo()
            .nombre("Ejercicio1")
            .descripcion("Descripción del ejercicio 1")
            // Otros atributos...
            .build();

        // Convertir el objeto EjercicioNuevoDTO a Ejercicios usando el método toEntity
        Ejercicios ejercicio = ejercicioDTO.toEntity();

        // Verificar que el resultado no sea nulo
        assertNotNull(ejercicio);
        // Verificar que los atributos se hayan copiado correctamente
        assertEquals(ejercicioDTO.getNombre(), ejercicio.getNombre());
        assertEquals(ejercicioDTO.getDescripcion(), ejercicio.getDescripcion());
        // Verificar otros atributos...
    }

	@Test
	@DisplayName("Test fromEntity EjerciciosDTO")
    public void testFromEntity() {
        // Crear un objeto Ejercicios para usar en la prueba
        Ejercicios ejercicio = new Ejercicios();
        ejercicio.setIdEjercicio(1L);
        ejercicio.setNombre("Ejercicio1");
        ejercicio.setDescripcion("Descripción del ejercicio 1");
        // Otros atributos...

        // Convertir el objeto Ejercicios a EjerciciosDTO usando el método fromEntity
        EjerciciosDTO ejercicioDTO = EjerciciosDTO.fromEntity(ejercicio);

        // Verificar que el resultado no sea nulo
        assertNotNull(ejercicioDTO);
        // Verificar que los atributos se hayan copiado correctamente
        assertEquals(ejercicio.getIdEjercicio(), ejercicioDTO.getIdEjercicio());
        assertEquals(ejercicio.getNombre(), ejercicioDTO.getNombre());
        assertEquals(ejercicio.getDescripcion(), ejercicioDTO.getDescripcion());
        // Verificar otros atributos...
    }

    @Test
	@DisplayName("Test toEntity de EjerciciosDTO")
    public void testToEntityEJDTO() {
        // Crear un objeto EjerciciosDTO para usar en la prueba
        EjerciciosDTO ejercicioDTO = EjerciciosDTO.builder()
            .idEjercicio(1L)
            .nombre("Ejercicio1")
            .descripcion("Descripción del ejercicio 1")
            // Otros atributos...
            .build();

        // Convertir el objeto EjerciciosDTO a Ejercicios usando el método toEntity
        Ejercicios ejercicio = ejercicioDTO.toEntity();

        // Verificar que el resultado no sea nulo
        assertNotNull(ejercicio);
        // Verificar que los atributos se hayan copiado correctamente
        assertEquals(ejercicioDTO.getIdEjercicio(), ejercicio.getIdEjercicio());
        assertEquals(ejercicioDTO.getNombre(), ejercicio.getNombre());
        assertEquals(ejercicioDTO.getDescripcion(), ejercicio.getDescripcion());

    }

	@Test
	@DisplayName("Crear con atributos nulos")
    public void createWithNullAttributes_ReturnsDTOWithNullAttributes() {
        // Arrange & Act
        EjercicioNuevoDTO ejercicioNuevoDTO = EjercicioNuevoDTO.builderNuevo()
                .nombre(null)
                .descripcion(null)
                .observaciones(null)
                .tipo(null)
                .musculosTrabajados(null)
                .material(null)
                .dificultad(null)
                .multimedia(null)
                .idEntrenador(null)
                .build();

        // Assert
        assertNull(ejercicioNuevoDTO.getNombre());
        assertNull(ejercicioNuevoDTO.getDescripcion());
        assertNull(ejercicioNuevoDTO.getObservaciones());
        assertNull(ejercicioNuevoDTO.getTipo());
        assertNull(ejercicioNuevoDTO.getMusculosTrabajados());
        assertNull(ejercicioNuevoDTO.getMaterial());
        assertNull(ejercicioNuevoDTO.getDificultad());
        assertNull(ejercicioNuevoDTO.getMultimedia());
        assertNull(ejercicioNuevoDTO.getIdEntrenador());
    }

    @Test
	@DisplayName("Crear con lista de multimedia vacía")
    public void createWithEmptyMultimediaList_ReturnsDTOWithEmptyMultimediaList() {
        // Arrange & Act
        EjercicioNuevoDTO ejercicioNuevoDTO = EjercicioNuevoDTO.builderNuevo()
                .multimedia(Collections.emptyList())
                .build();

        // Assert
        assertNotNull(ejercicioNuevoDTO.getMultimedia());
        assertEquals(0, ejercicioNuevoDTO.getMultimedia().size());
    }


    @Test
	@DisplayName("Rutina devuelve entidad correcta")
    public void rutina_ReturnsCorrectEntity() {
        // Arrange
        RutinasDTO rutinasDTO = new RutinasDTO();
        rutinasDTO.setIdRutinas(2L);
        rutinasDTO.setNombre("Rutina 2");
        rutinasDTO.setDescripcion("Descripción de la rutina 2");
        rutinasDTO.setObservaciones("Observaciones de la rutina 2");

        // Act
        Rutinas rutinas = rutinasDTO.rutina();

        // Assert
        assertNotNull(rutinas);
        assertEquals(2L, rutinas.getIdRutinas());
        assertEquals("Rutina 2", rutinas.getNombre());
        assertEquals("Descripción de la rutina 2", rutinas.getDescripcion());
        assertEquals("Observaciones de la rutina 2", rutinas.getObservaciones());
    }

	 @Test
	 @DisplayName("toEntity devuelve entidad correcta")
    public void toEntity_ReturnsCorrectEntity() {
        // Arrange
        FragmentoRutinaDTO fragmentoRutinaDTO = new FragmentoRutinaDTO();
        fragmentoRutinaDTO.setNumSeries(3L);
        fragmentoRutinaDTO.setNumRepeticiones(12L);
        fragmentoRutinaDTO.setDuracionMinutos(30L);
        EjerciciosDTO ejercicioDTO = new EjerciciosDTO();
        ejercicioDTO.setIdEjercicio(1L);
        fragmentoRutinaDTO.setEjercicio(ejercicioDTO);

        // Act
        FragmentoRutina fragmentoRutina = fragmentoRutinaDTO.toEntity();

        // Assert
        assertNotNull(fragmentoRutina);
        assertEquals(3L, fragmentoRutina.getNumSeries());
        assertEquals(12L, fragmentoRutina.getNumRepeticiones());
        assertEquals(30L, fragmentoRutina.getDuracionMinutos());
        assertNotNull(fragmentoRutina.getEjercicios());
        assertEquals(1L, fragmentoRutina.getEjercicios().getIdEjercicio());
    }

    @Test
	@DisplayName("fromEntity DTO correcto")
    public void fromEntity_ReturnsCorrectDTO() {
        // Arrange
        FragmentoRutina fragmentoRutina = new FragmentoRutina();
        fragmentoRutina.setNumSeries(4L);
        fragmentoRutina.setNumRepeticiones(10L);
        fragmentoRutina.setDuracionMinutos(25L);
        EjerciciosDTO ejercicioDTO = new EjerciciosDTO();
        ejercicioDTO.setIdEjercicio(2L);
        fragmentoRutina.setEjercicios(ejercicioDTO.toEntity());

        // Act
        FragmentoRutinaDTO fragmentoRutinaDTO = FragmentoRutinaDTO.fromEntity(fragmentoRutina);

        // Assert
        assertNotNull(fragmentoRutinaDTO);
        assertEquals(4L, fragmentoRutinaDTO.getNumSeries());
        assertEquals(10L, fragmentoRutinaDTO.getNumRepeticiones());
        assertEquals(25L, fragmentoRutinaDTO.getDuracionMinutos());
        assertNotNull(fragmentoRutinaDTO.getEjercicio());
        assertEquals(2L, fragmentoRutinaDTO.getEjercicio().getIdEjercicio());
    }

	
	}
}