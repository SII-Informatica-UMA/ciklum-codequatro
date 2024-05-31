package CodeQuatro.Entidades_Cicklum;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.filter;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import CodeQuatro.Entidades_Cicklum.controladores.EjerciciosRest;
import CodeQuatro.Entidades_Cicklum.controladores.RutinasRest;
import CodeQuatro.Entidades_Cicklum.dtos.EjercicioNuevoDTO;
import CodeQuatro.Entidades_Cicklum.dtos.EjerciciosDTO;
import CodeQuatro.Entidades_Cicklum.dtos.FragmentoRutinaDTO;
import CodeQuatro.Entidades_Cicklum.dtos.Links;
import CodeQuatro.Entidades_Cicklum.dtos.RutinaNuevaDTO;
import CodeQuatro.Entidades_Cicklum.dtos.RutinasDTO;
import CodeQuatro.Entidades_Cicklum.entities.Ejercicios;
import CodeQuatro.Entidades_Cicklum.entities.FragmentoRutina;
import CodeQuatro.Entidades_Cicklum.entities.Rutinas;
import CodeQuatro.Entidades_Cicklum.excepciones.EjercicioExistenteException;
import CodeQuatro.Entidades_Cicklum.excepciones.EjercicioNoEncontradoException;
import CodeQuatro.Entidades_Cicklum.excepciones.RutinaExistente;
import CodeQuatro.Entidades_Cicklum.excepciones.RutinaNoEncontrada;
import CodeQuatro.Entidades_Cicklum.repositories.EjerciciosRepository;
import CodeQuatro.Entidades_Cicklum.repositories.RutinasRepository;
import CodeQuatro.Entidades_Cicklum.security.JwtUtil;
import CodeQuatro.Entidades_Cicklum.servicios.LogicaEjercicios;
import CodeQuatro.Entidades_Cicklum.servicios.LogicaRutinas;
import io.jsonwebtoken.Claims;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = EntidadesCicklumApplication.class)
@DisplayName("------En el servicio Cicklum--------")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
class EntidadesCicklumApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value(value = "${local.server.port}")
    private int port;

    @Mock
    private EjerciciosRepository ejercicioRepo;

    @InjectMocks
    private LogicaEjercicios logicaEjercicios;

    @Mock
    private RutinasRepository rutinaRepo;

    @InjectMocks
    private LogicaRutinas logicaRutinas;

    @InjectMocks
    private LineaComandos lineaComandos;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Autowired
    private RutinasRepository rutinaRepository;
    @Autowired
    private EjerciciosRepository ejerciciosRepository;

    @Mock
    LogicaEjercicios logicaEjerciciosMock;

    @Mock
    LogicaRutinas logicaRutinasMock;

    @InjectMocks
    EjerciciosRest ejerciciosRest;

    @InjectMocks
    RutinasRest rutinasRest;

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
        UriBuilder ub = ubf.builder().scheme(scheme).host(host).port(port);
        for (String path : paths) {
            ub = ub.path(path);
        }
        return ub.build();
    }

    private RequestEntity<Void> get(String scheme, String host, int port, String path) {
        URI uri = uri(scheme, host, port, path);
        var peticion = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token).build();
        return peticion;
    }

    private RequestEntity<Void> delete(String scheme, String host, int port, String path) {
        URI uri = uri(scheme, host, port, path);
        var peticion = RequestEntity.delete(uri).header("Authorization", "Bearer " + token).build();
        return peticion;
    }

    private <T> RequestEntity<T> post(String scheme, String host, int port, String path, T object) {
        URI uri = uri(scheme, host, port, path);
        var peticion = RequestEntity.post(uri).contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token).body(object);
        return peticion;
    }

    private <T> RequestEntity<T> put(String scheme, String host, int port, String path, T object) {
        URI uri = uri(scheme, host, port, path);
        var peticion = RequestEntity.put(uri).contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token).body(object);
        return peticion;
    }

    private void compruebaCampos(Rutinas expected, Rutinas actual) {
        assertThat(actual.getNombre()).isEqualTo(expected.getNombre());
        assertThat(actual.getDescripcion()).isEqualTo(expected.getDescripcion());
    }

    @Nested
    public class BaseDatos {

        @Test
        @DisplayName("Devuelve error al obtener un ejercicio concreto")
        public void errorAlObtenerEjercicioConcreto() {
            var peticion = get("http", "localhost", port, "/ejercicio/1");

            var respuesta = restTemplate.exchange(peticion, new ParameterizedTypeReference<EjerciciosDTO>() {
            });

            assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
        }

        // @Test
        // @DisplayName("Devuelve una lista vacía de ejercicios")
        // public void listaVaciaDeEjercicios() {
        //     var peticion = get("http", "localhost", port, "/ejercicio");

        //     var respuesta = restTemplate.exchange(peticion, new ParameterizedTypeReference<EjerciciosDTO>() {});

        //     assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
        // }

        @Test
        @DisplayName("Devuelve error al modificar un ejercicio que no existe")
        public void errorAlModificarEjercicioInexistente() {
            var ejercicio = EjerciciosDTO.builder().nombre("Sentadilla").build();
            var peticion = put("http", "localhost", port, "/ejercicio/1", ejercicio);

            var respuesta = restTemplate.exchange(peticion, Void.class);

            assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
        }

        @Test
        @DisplayName("Devuelve error al eliminar un ejercicio que no existe")
        public void errorAlEliminarEjercicioInexistente() {
            var peticion = delete("http", "localhost", port, "/ejercicio/1");

            var respuesta = restTemplate.exchange(peticion, Void.class);

            assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
        }

        @Test
        @DisplayName("Devuelve una lista vacía de rutinas")
        public void listaVaciaDeRutinas() {
            var peticion = get("http", "localhost", port, "/rutina");
        
            var respuesta = restTemplate.exchange(peticion, new ParameterizedTypeReference<List<RutinasDTO>>() {});
        
            assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
            assertThat(respuesta.getBody()).isEmpty();
        }
        
        @Test
        @DisplayName("Devuelve error al modificar una rutina que no existe")
        public void errorAlModificarRutinaInexistente() {
            var rutina = RutinasDTO.builder().nombre("Rutina de fuerza").build();
            var peticion = put("http", "localhost", port, "/rutina/1", rutina);
        
            var respuesta = restTemplate.exchange(peticion, Void.class);
        
            assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
        }
        
        @Test
        @DisplayName("Devuelve error al eliminar una rutina que no existe")
        public void errorAlEliminarRutinaInexistente() {
            var peticion = delete("http", "localhost", port, "/rutina/1");
        
            var respuesta = restTemplate.exchange(peticion, Void.class);
        
            assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
        }
       


        // @Test
        // @DisplayName("Añadir una rutina correctamente")
        // public void aniadirRutinaCorrectamente() {
        //     // Preparamos la rutina a insertar
        //     var rutinaDTO = RutinasDTO.builder()
        //             .nombre("Rutina Nueva")
        //             .descripcion("Descripción de la rutina nueva")
        //             .observaciones("Observaciones de la rutina nueva")
        //             .build();

        //     // Preparamos la petición con la rutina dentro
        //     URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/rutina").build().toUri();
        //     var peticion = RequestEntity.post(uri)
        //                                 .contentType(MediaType.APPLICATION_JSON)
        //                                 .header("Authorization", "Bearer " + token)
        //                                 .body(rutinaDTO);

        //     // Invocamos al servicio REST
        //     var respuesta = restTemplate.exchange(peticion, Void.class);

        //     // Comprobamos el resultado
        //     assertThat(respuesta.getStatusCode().value()).isEqualTo(201);
        //     assertThat(respuesta.getHeaders().getLocation().toString()).startsWith("http://localhost:" + port + "/rutina/");

        //     // Verificamos que la rutina se ha guardado en la base de datos
        //     List<Rutinas> rutinasBD = rutinaRepo.findAll();
        //     assertThat(rutinasBD).hasSize(1);
        //     Rutinas rutinaGuardada = rutinasBD.get(0);
        //     assertThat(rutinaGuardada.getNombre()).isEqualTo("Rutina Nueva");
        //     assertThat(rutinaGuardada.getDescripcion()).isEqualTo("Descripción de la rutina nueva");
        //     assertThat(rutinaGuardada.getObservaciones()).isEqualTo("Observaciones de la rutina nueva");
        // }

        // @Test
        // @DisplayName("Devuelve error al obtener una rutina que no existe")
        // public void errorAlObtenerRutinaInexistente() {
        //     var peticion = get("http", "localhost", port, "/rutina/1");

        //     var respuesta = restTemplate.exchange(peticion, new ParameterizedTypeReference<RutinasDTO>() {});

        //     assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
        // }

        @Test
        @DisplayName("Devuelve error al modificar una rutina que no existe")
        public void modificarRutinaInexistente() {
            var rutinaDTO = RutinasDTO.builder().nombre("Nombre Modificado").build();
            var peticion = put("http", "localhost", port, "/rutina/1", rutinaDTO);

            var respuesta = restTemplate.exchange(peticion, Void.class);

            assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
        }

        @Test
        @DisplayName("Devuelve error al eliminar una rutina que no existe")
        public void eliminarRutinaInexistente() {
            var peticion = delete("http", "localhost", port, "/rutina/1");

            var respuesta = restTemplate.exchange(peticion, Void.class);

            assertThat(respuesta.getStatusCode().value()).isEqualTo(404);
        }
        

        @Test
        @DisplayName("Comprueba hashCode con el mismo ejercicio")
        public void testHashCodeEjercicio() {
            Ejercicios ejercicio = new Ejercicios(1L, "Push-up", "Ejercicio de pecho", "Realizar con espalda recta",
                    "Calistenia", "Pecho, Tríceps", "Ninguno", "Fácil", null, 1L);

            assertEquals(Long.hashCode(1L), ejercicio.hashCode());
        }

        @Test
        @DisplayName("Crea ejercicio con todos los campos")
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
        @DisplayName("Comprueba equals con el mismo ejercicio")
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
        @DisplayName("Comprueba la creación de rutina con todos los campos")
        public void testCreacionRutinaConTodosLosCampos() {
            FragmentoRutina fragmento = new FragmentoRutina(1L, 3L, 12L, 30L, null);
            Rutinas rutina = new Rutinas(1L, "Rutina de pecho", "Rutina para desarrollar el pecho",
                    "Realizar con moderación", 1L, Arrays.asList(fragmento));

            assertEquals(1L, rutina.getIdRutinas());
            assertEquals("Rutina de pecho", rutina.getNombre());
            assertEquals("Rutina para desarrollar el pecho", rutina.getDescripcion());
            assertEquals("Realizar con moderación", rutina.getObservaciones());
            assertEquals(1L, rutina.getIdEntrenador());
            assertEquals(Arrays.asList(fragmento), rutina.getFragmentoRutina());
        }

        @Test
        @DisplayName("Comprueba equals con la misma rutina")
        public void testIgualdadRutinas() {
            FragmentoRutina fragmento = new FragmentoRutina(1L, 3L, 12L, 30L, null);

            Rutinas rutina1 = new Rutinas(1L, "Rutina de pecho", "Rutina para desarrollar el pecho",
                    "Realizar con moderación", 1L, Arrays.asList(fragmento));

            Rutinas rutina2 = new Rutinas(1L, "Rutina de pecho", "Rutina para desarrollar el pecho",
                    "Realizar con moderación", 1L, Arrays.asList(fragmento));

            assertEquals(rutina1, rutina2);
        }

        @Test
        @DisplayName("Comprueba los getters y setters de Ejercicios")
        public void testGettersYSettersEjercicios() {
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
        @DisplayName("Comprueba el método toString de Ejercicios")
        public void testToStringEjercicios() {
            Ejercicios ejercicio = new Ejercicios(1L, "Push-up", "Ejercicio de pecho", "Realizar con espalda recta",
                    "Calistenia", "Pecho, Tríceps", "Ninguno", "Fácil",
                    Arrays.asList("video1.mp4", "imagen1.jpg"), 1L);

            String expected = "Ejercicio [id= 1, nombre= Push-up, descripcion= Ejercicio de pechoObservaciones= Realizar con espalda recta, tipo= Calistenia, musculos trabajados= Pecho, Tríceps, material= Ninguno, dificultad= Fácil, multimedia= [video1.mp4imagen1.jpg]]";
            assertEquals(expected, ejercicio.toString());
        }

        @Test
        @DisplayName("Comprueba la gestión de la multimedia en Ejercicios")
        public void testGestionMultimediaEjercicios() {
            List<String> multimediaList = new ArrayList<>();
            multimediaList.add("video1.mp4");

            Ejercicios ejercicio = new Ejercicios();
            ejercicio.setMultimedia(multimediaList);

            assertEquals(1, ejercicio.getMultimedia().size());
            assertTrue(ejercicio.getMultimedia().contains("video1.mp4"));

            ejercicio.getMultimedia().add("imagen1.jpg");
            assertEquals(2, ejercicio.getMultimedia().size());
            assertTrue(ejercicio.getMultimedia().contains("imagen1.jpg"));

            ejercicio.getMultimedia().remove("video1.mp4");
            assertEquals(1, ejercicio.getMultimedia().size());
            assertFalse(ejercicio.getMultimedia().contains("video1.mp4"));
        }


    
        // ------------------------------------
        @Test
        @DisplayName("Comprueba equals y hashCode de Rutinas")
        public void testEqualsYHashCodeRutinas() {
            FragmentoRutina fragmento1 = new FragmentoRutina(1L, 3L, 15L, 10L, null);
            FragmentoRutina fragmento2 = new FragmentoRutina(2L, 4L, 12L, 8L, null);

            Rutinas rutina1 = new Rutinas(1L, "Rutina de fuerza", "Rutina para desarrollar fuerza",
                    "Realizar con buena técnica", 1L, Arrays.asList(fragmento1, fragmento2));

            Rutinas rutina2 = new Rutinas(1L, "Rutina de fuerza", "Rutina para desarrollar fuerza",
                    "Realizar con buena técnica", 1L, Arrays.asList(fragmento1, fragmento2));

            Rutinas rutina3 = new Rutinas(2L, "Rutina de resistencia", "Rutina para mejorar la resistencia",
                    "Realizar con cuidado", 2L, Arrays.asList(fragmento1, fragmento2));

            assertEquals(rutina1, rutina2);
            assertNotEquals(rutina1, rutina3);
            assertEquals(rutina1.hashCode(), rutina2.hashCode());
            assertNotEquals(rutina1.hashCode(), rutina3.hashCode());
        }

        @Test
        @DisplayName("Comprueba el constructor con parámetros de Ejercicios")
        public void testConstructorConParametrosEjercicios() {
            Ejercicios ejercicio = new Ejercicios(1L, "Push Up", "Ejercicio de pecho", "Realizar correctamente",
                    "Calistenia", "Pectorales", "Ninguno", "Intermedio",
                    Arrays.asList("video1.mp4", "video2.mp4"), 1L);

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
        @DisplayName("Comprueba constructor con parámetros de Rutinas")
        public void testConstructorConParametrosRutinas() {
            FragmentoRutina fragmento1 = new FragmentoRutina(1L, 3L, 15L, 10L, null);
            FragmentoRutina fragmento2 = new FragmentoRutina(2L, 4L, 12L, 8L, null);

            Rutinas rutina = new Rutinas(1L, "Rutina de fuerza", "Rutina para desarrollar fuerza",
                    "Realizar con buena técnica", 1L, Arrays.asList(fragmento1, fragmento2));

            assertEquals(1L, rutina.getIdRutinas());
            assertEquals("Rutina de fuerza", rutina.getNombre());
            assertEquals("Rutina para desarrollar fuerza", rutina.getDescripcion());
            assertEquals("Realizar con buena técnica", rutina.getObservaciones());
            assertEquals(1L, rutina.getIdEntrenador());
            assertEquals(2, rutina.getFragmentoRutina().size());
        }

        @Test
        @DisplayName("Comprueba constructor con parámetros de fragmento de rutina")
        public void testConstructorConParametrosFragmentoRutina() {
            Ejercicios ejercicio = new Ejercicios(1L, "Push Up", "Ejercicio de pecho", "Realizar correctamente",
                    "Calistenia", "Pectorales", "Ninguno", "Intermedio",
                    Arrays.asList("video1.mp4", "video2.mp4"), 1L);

            FragmentoRutina fragmento = new FragmentoRutina(1L, 3L, 15L, 10L, ejercicio);

            assertEquals(1L, fragmento.getIdFragmentoRutina());
            assertEquals(3L, fragmento.getNumSeries());
            assertEquals(15L, fragmento.getNumRepeticiones());
            assertEquals(10L, fragmento.getDuracionMinutos());
            assertEquals(ejercicio, fragmento.getEjercicios());
        }

        @Test
        @DisplayName("Comprueba toString de Rutinas")
        public void testToStringRutinas() {
            Rutinas rutina = new Rutinas(1L, "Rutina de fuerza", "Rutina para desarrollar fuerza",
                    "Realizar con buena técnica", 1L,
                    Arrays.asList(new FragmentoRutina(1L, 3L, 15L, 10L, null)));
            String expected = "Rutina [id= 1, nombre= Rutina de fuerza, descripcion= Rutina para desarrollar fuerza, observaciones= Realizar con buena técnica";
            assertEquals(expected, rutina.toString());
        }

        @Test
        @DisplayName("Comprueba hashCode con el mismo objeto rutina")
        public void testHashCodeRutinas() {
            Rutinas rutina = new Rutinas(1L, "Rutina de pecho", "Rutina para desarrollar el pecho",
                    "Realizar con moderación", 1L, null);

            assertEquals(Long.hashCode(1L), rutina.hashCode());
        }

        @Test
        @DisplayName("Consulta todas las rutinas")
        public void consultaTodasLasRutinas() {
            var peticion = get("http", "localhost", port, "/rutina");

            var respuesta = restTemplate.exchange(peticion, new ParameterizedTypeReference<List<Rutinas>>() {});

            assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
            assertThat(respuesta.getBody()).isEmpty();
        }

        @Test
        @DisplayName("Comprueba setter y getter de la propiedad idEjercicio en la clase Ejercicios")
        public void testSetterGetterIdEjercicio() {
            Ejercicios ejercicio = new Ejercicios();
            ejercicio.setIdEjercicio(1L);
            assertEquals(1L, ejercicio.getIdEjercicio());
        }

        @Test
        @DisplayName("Comprueba setter y getter de la propiedad idRutinas en la clase Rutinas")
        public void testSetterGetterIdRutinas() {
            Rutinas rutina = new Rutinas();
            rutina.setIdRutinas(1L);
            assertEquals(1L, rutina.getIdRutinas());
        }

        @Test
        @DisplayName("Comprueba setter y getter de la propiedad idFragmentoRutina en la clase FragmentoRutina")
        public void testSetterGetterIdFragmentoRutina() {
            FragmentoRutina fragmento = new FragmentoRutina();
            fragmento.setIdFragmentoRutina(1L);
            assertEquals(1L, fragmento.getIdFragmentoRutina());
        }

        @Test
        @DisplayName("Comprueba equals y hashCode de Ejercicios")
        public void testEqualsYHashCodeEjercicios() {
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
        @DisplayName("Comprueba la conversión a entidad de EjercicioNuevoDTO")
        public void testConversionAEntidadEjercicioNuevoDTO() {
            EjercicioNuevoDTO ejercicioDTO = EjercicioNuevoDTO.builderNuevo().nombre("Ejercicio1")
                    .descripcion("Descripción del ejercicio 1").build();

            Ejercicios ejercicio = ejercicioDTO.toEntity();

            assertNotNull(ejercicio);
            assertEquals(ejercicioDTO.getNombre(), ejercicio.getNombre());
            assertEquals(ejercicioDTO.getDescripcion(), ejercicio.getDescripcion());
        }

        @Test
        @DisplayName("Comprueba la conversión desde entidad a EjerciciosDTO")
        public void testConversionDesdeEntidadEjerciciosDTO() {
            Ejercicios ejercicio = new Ejercicios();
            ejercicio.setIdEjercicio(1L);
            ejercicio.setNombre("Ejercicio1");
            ejercicio.setDescripcion("Descripción del ejercicio 1");

            EjerciciosDTO ejercicioDTO = EjerciciosDTO.fromEntity(ejercicio);

            assertNotNull(ejercicioDTO);
            assertEquals(ejercicio.getIdEjercicio(), ejercicioDTO.getIdEjercicio());
        }

        @Test
        @DisplayName("Comprueba la conversión a entidad de EjerciciosDTO")
        public void testConversionAEntidadEjerciciosDTO() {
            EjerciciosDTO ejercicioDTO = EjerciciosDTO.builder().idEjercicio(1L).nombre("Ejercicio1")
                    .descripcion("Descripción del ejercicio 1").build();

            Ejercicios ejercicio = ejercicioDTO.toEntity();

            assertNotNull(ejercicio);
            assertEquals(ejercicioDTO.getIdEjercicio(), ejercicio.getIdEjercicio());
            assertEquals(ejercicioDTO.getNombre(), ejercicio.getNombre());
            assertEquals(ejercicioDTO.getDescripcion(), ejercicio.getDescripcion());
        }

        @Test
        @DisplayName("Comprueba la creación con atributos nulos en EjercicioNuevoDTO")
        public void testCreacionConAtributosNulosEnEjercicioNuevoDTO() {
            EjercicioNuevoDTO ejercicioNuevoDTO = EjercicioNuevoDTO.builderNuevo().nombre(null).descripcion(null)
                    .observaciones(null).tipo(null).musculosTrabajados(null).material(null).dificultad(null)
                    .multimedia(null).idEntrenador(null).build();

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
        @DisplayName("Comprueba la creación con lista de multimedia vacía en EjercicioNuevoDTO")
        public void testCreacionConListaDeMultimediaVaciaEnEjercicioNuevoDTO() {
            EjercicioNuevoDTO ejercicioNuevoDTO = EjercicioNuevoDTO.builderNuevo().multimedia(Collections.emptyList())
                    .build();

            assertNotNull(ejercicioNuevoDTO.getMultimedia());
            assertEquals(0, ejercicioNuevoDTO.getMultimedia().size());
        }

        @Test
        @DisplayName("Comprueba la conversión a entidad correcta en RutinasDTO")
        public void testConversionAEntidadCorrectaEnRutinasDTO() {
            RutinasDTO rutinasDTO = new RutinasDTO();
            rutinasDTO.setIdRutinas(2L);
            rutinasDTO.setNombre("Rutina 2");
            rutinasDTO.setDescripcion("Descripción de la rutina 2");
            rutinasDTO.setObservaciones("Observaciones de la rutina 2");

            Rutinas rutinas = rutinasDTO.rutina();

            assertNotNull(rutinas);
            assertEquals(2L, rutinas.getIdRutinas());
            assertEquals("Rutina 2", rutinas.getNombre());
            assertEquals("Descripción de la rutina 2", rutinas.getDescripcion());
            assertEquals("Observaciones de la rutina 2", rutinas.getObservaciones());
        }

        @Test
        @DisplayName("Comprueba la conversión a entidad correcta en FragmentoRutinaDTO")
        public void testConversionAEntidadCorrectaEnFragmentoRutinaDTO() {
            FragmentoRutinaDTO fragmentoRutinaDTO = new FragmentoRutinaDTO();
            fragmentoRutinaDTO.setNumSeries(3L);
            fragmentoRutinaDTO.setNumRepeticiones(12L);
            fragmentoRutinaDTO.setDuracionMinutos(30L);
            EjerciciosDTO ejercicioDTO = new EjerciciosDTO();
            ejercicioDTO.setIdEjercicio(1L);
            fragmentoRutinaDTO.setEjercicio(ejercicioDTO);

            FragmentoRutina fragmentoRutina = fragmentoRutinaDTO.toEntity();

            assertNotNull(fragmentoRutina);
            assertEquals(3L, fragmentoRutina.getNumSeries());
            assertEquals(12L, fragmentoRutina.getNumRepeticiones());
            assertEquals(30L, fragmentoRutina.getDuracionMinutos());
            assertNotNull(fragmentoRutina.getEjercicios());
            assertEquals(1L, fragmentoRutina.getEjercicios().getIdEjercicio());
        }

        @Test
        @DisplayName("Comprueba la conversión desde entidad correcta en FragmentoRutinaDTO")
        public void testConversionDesdeEntidadCorrectaEnFragmentoRutinaDTO() {
            FragmentoRutina fragmentoRutina = new FragmentoRutina();
            fragmentoRutina.setNumSeries(4L);
            fragmentoRutina.setNumRepeticiones(10L);
            fragmentoRutina.setDuracionMinutos(25L);
            EjerciciosDTO ejercicioDTO = new EjerciciosDTO();
            ejercicioDTO.setIdEjercicio(2L);
            fragmentoRutina.setEjercicios(ejercicioDTO.toEntity());

            FragmentoRutinaDTO fragmentoRutinaDTO = FragmentoRutinaDTO.fromEntity(fragmentoRutina);

            assertNotNull(fragmentoRutinaDTO);
            assertEquals(4L, fragmentoRutinaDTO.getNumSeries());
            assertEquals(10L, fragmentoRutinaDTO.getNumRepeticiones());
            assertEquals(25L, fragmentoRutinaDTO.getDuracionMinutos());
            assertNotNull(fragmentoRutinaDTO.getEjercicio());
            assertEquals(2L, fragmentoRutinaDTO.getEjercicio().getIdEjercicio());
        }

        @Test
        @DisplayName("Consulta todas las rutinas con token JWT")
        public void consultaTodasLasRutinasConTokenJWT() {
            Rutinas rutina = Rutinas.builder().nombre("Rutina 1").descripcion("Descripción de Rutina 1")
                    .observaciones("Observaciones de Rutina 1").idEntrenador(1L).ejercicios(List.of()).build();
            rutinaRepository.save(rutina);

            RequestEntity<Void> request = get("http", "localhost", port, "/rutina");
            ResponseEntity<List<Rutinas>> response = restTemplate.exchange(request,
                    new ParameterizedTypeReference<List<Rutinas>>() {
                    });

            assertEquals(HttpStatus.OK, response.getStatusCode());

            List<Rutinas> rutinas = response.getBody();
            assertThat(rutinas).isNotNull();
            assertThat(rutinas.size()).isGreaterThan(0);
        }

        @Test
        @DisplayName("Comprueba excepciones EjercicioExistenteException sin mensaje")
        public void testEjercicioExistenteExceptionSinMensaje() {
            EjercicioExistenteException exception = new EjercicioExistenteException();

            assertNotNull(exception);
            assertNull(exception.getMessage());
        }

        @Test
        @DisplayName("Comprueba excepciones EjercicioExistenteException con mensaje")
        public void testEjercicioExistenteExceptionConMensaje() {
            String message = "Mensaje de prueba";
            EjercicioExistenteException exception = new EjercicioExistenteException(message);

            assertNotNull(exception);
            assertEquals(message, exception.getMessage());
        }

        @Test
        @DisplayName("Comprueba excepciones EjercicioNoEncontradoException sin mensaje")
        public void testEjercicioNoEncontradoExceptionSinMensaje() {
            EjercicioNoEncontradoException exception = new EjercicioNoEncontradoException();

            assertNotNull(exception);
            assertNull(exception.getMessage());
        }

        @Test
        @DisplayName("Comprueba excepciones EjercicioNoEncontradoException con mensaje")
        public void testEjercicioNoEncontradoExceptionConMensaje() {
            String message = "Mensaje de prueba";
            EjercicioNoEncontradoException exception = new EjercicioNoEncontradoException(message);

            assertNotNull(exception);
            assertEquals(message, exception.getMessage());
        }

        @Test
        @DisplayName("Comprueba excepciones RutinaExistente sin mensaje")
        public void testRutinaExistenteSinMensaje() {
            RutinaExistente exception = new RutinaExistente();

            assertNotNull(exception);
            assertNull(exception.getMessage());
        }

        @Test
        @DisplayName("Comprueba excepciones RutinaExistente con mensaje")
        public void testRutinaExistenteConMensaje() {
            String message = "Mensaje de prueba";
            RutinaExistente exception = new RutinaExistente(message);

            assertNotNull(exception);
            assertEquals(message, exception.getMessage());
        }
    }



    @Test
    @DisplayName("Constructor sin mensaje en excepción RutinaNoEncontrada")
    public void constructorSinMensajeEnRutinaNoEncontrada() {
        RutinaNoEncontrada exception = new RutinaNoEncontrada();
        assertNotNull(exception);
        assertNull(exception.getMessage());
    }
    
    @Test
    @DisplayName("Constructor con mensaje en excepción RutinaNoEncontrada")
    public void constructorConMensajeEnRutinaNoEncontrada() {
        String message = "Mensaje de prueba";
        RutinaNoEncontrada exception = new RutinaNoEncontrada(message);
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }
    
    @Test
    @DisplayName("Obtiene lista de ejercicios válida")
    void obtenerListaEjerciciosValida() {
        List<Ejercicios> ejercicios = new ArrayList<>();
        ejercicios.add(new Ejercicios());
        ejercicios.add(new Ejercicios());
        when(logicaEjerciciosMock.obtenerEjercicios(null)).thenReturn(ejercicios);
        List<EjerciciosDTO> resultado = ejerciciosRest.obtenerEjercicios(null);
        assertEquals(2, resultado.size());
    }
    
    @Test
    @DisplayName("Crea un nuevo ejercicio")
    void crearNuevoEjercicio() {
        EjercicioNuevoDTO ejercicioNuevoDTO = new EjercicioNuevoDTO();
        ejercicioNuevoDTO.setNombre("Nuevo Ejercicio");
        when(logicaEjerciciosMock.crearActualizarEjercicio(any())).thenReturn(new Ejercicios());
        ResponseEntity<EjerciciosDTO> responseEntity = ejerciciosRest.crearEjercicio(1L, ejercicioNuevoDTO, UriComponentsBuilder.newInstance());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    
    @Test
    @DisplayName("Eliminar una rutina existente")
    void eliminarRutinaExistente() {
        assertDoesNotThrow(() -> rutinasRest.eliminarRutina(1L));
    }
    
    @Test
    @DisplayName("Obtiene ejercicios de un entrenador existente")
    void obtenerEjerciciosDeEntrenadorExistente() {
        Long idEntrenador = 1L;
        when(ejercicioRepo.findByIdEntrenador(idEntrenador)).thenReturn(List.of(new Ejercicios()));
        var result = logicaEjercicios.obtenerEjercicios(idEntrenador);
        assertFalse(result.isEmpty());
        verify(ejercicioRepo).findByIdEntrenador(idEntrenador);
    }
    
    @Test
    @DisplayName("Obtiene un ejercicio existente por ID")
    void obtenerEjercicioExistentePorId() {
        Long idEjercicio = 1L;
        Ejercicios ejercicio = new Ejercicios();
        ejercicio.setIdEjercicio(idEjercicio);
        when(ejercicioRepo.findById(idEjercicio)).thenReturn(Optional.of(ejercicio));
        var result = logicaEjercicios.obtenerEjercicio(idEjercicio);
        assertTrue(result.isPresent());
        assertEquals(idEjercicio, result.get().getIdEjercicio());
    }
    
    @Test
    @DisplayName("Crea o actualiza un ejercicio")
    void crearOActualizarEjercicio() {
        Ejercicios ejercicio = new Ejercicios();
        when(ejercicioRepo.save(ejercicio)).thenReturn(ejercicio);
        Ejercicios result = logicaEjercicios.crearActualizarEjercicio(ejercicio);
        assertNotNull(result);
        assertEquals(ejercicio, result);  
    }

    
    
    @Test
    @DisplayName("Elimina un ejercicio existente")
    void eliminarEjercicioExistente() throws Exception {
        Long idEjercicio = 1L;
        Ejercicios ejercicio = new Ejercicios();
        ejercicio.setIdEjercicio(idEjercicio);
        when(ejercicioRepo.findById(idEjercicio)).thenReturn(Optional.of(ejercicio));
        logicaEjercicios.eliminarEjercicio(idEjercicio);
        when(ejercicioRepo.findById(idEjercicio)).thenReturn(Optional.empty());
        Optional<Ejercicios> ejercicioEliminado = ejercicioRepo.findById(idEjercicio);
        assertFalse(ejercicioEliminado.isPresent());
    }

    
    @Test
    @DisplayName("Lanza excepción al eliminar ejercicio no existente")
    void lanzarExcepcionAlEliminarEjercicioNoExistente() {
        Long idEjercicio = 1L;
        when(ejercicioRepo.findById(idEjercicio)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> logicaEjercicios.eliminarEjercicio(idEjercicio));
    }
    
    @Test
    @DisplayName("Obtiene lista de ejercicios de un entrenador existente")
    void obtenerListaEjerciciosDeEntrenadorExistente() {
        Long idEntrenador = 1L;
        List<Ejercicios> ejercicios = List.of(new Ejercicios(), new Ejercicios());
        when(ejercicioRepo.findByIdEntrenador(idEntrenador)).thenReturn(ejercicios);
        List<Ejercicios> result = logicaEjercicios.obtenerEjercicios(idEntrenador);
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ejercicioRepo).findByIdEntrenador(idEntrenador);
    }
    
    @Test
    @DisplayName("Actualiza un ejercicio existente")
    void actualizarEjercicioExistente() {
        Long idEjercicio = 1L;
        Ejercicios ejercicio = new Ejercicios();
        ejercicio.setIdEjercicio(idEjercicio);
        when(ejercicioRepo.save(ejercicio)).thenReturn(ejercicio);
        Ejercicios result = logicaEjercicios.crearActualizarEjercicio(ejercicio);
        assertNotNull(result);
        assertEquals(idEjercicio, result.getIdEjercicio());
        verify(ejercicioRepo).save(ejercicio);
    }
    
    @Test
    @DisplayName("Obtiene rutinas de un entrenador existente")
    void obtenerRutinasDeEntrenadorExistente() {
        Long idEntrenador = 1L;
        when(rutinaRepo.findByIdEntrenador(idEntrenador)).thenReturn(List.of(new Rutinas()));
        var result = logicaRutinas.obtenerRutinas(idEntrenador);
        assertFalse(result.isEmpty());
        verify(rutinaRepo).findByIdEntrenador(idEntrenador);
    }
    
    @Test
    @DisplayName("Obtiene una rutina existente por ID")
    void obtenerRutinaExistentePorId() {
        Long idRutina = 1L;
        Rutinas rutina = new Rutinas();
        rutina.setIdRutinas(idRutina);
        when(rutinaRepo.findById(idRutina)).thenReturn(Optional.of(rutina));
        var result = logicaRutinas.obtenerRutina(idRutina);
        assertTrue(result.isPresent());
        assertEquals(idRutina, result.get().getIdRutinas());
    }
    

    @Test
    @DisplayName("Lanza excepción al eliminar rutina no existente")
    void lanzarExcepcionAlEliminarRutinaNoExistente() {
        Long idRutina = 1L;
        when(rutinaRepo.findById(idRutina)).thenReturn(Optional.empty());
        assertThrows(RutinaNoEncontrada.class, () -> logicaRutinas.eliminarRutina(idRutina));
    }
    
    @Test
    @DisplayName("Lanza excepción al añadir rutina existente")
    void lanzarExcepcionAlAniadirRutinaExistente() {
        Rutinas rutina = new Rutinas();
        rutina.setIdEntrenador(1L);
        when(rutinaRepo.findByIdEntrenador(rutina.getIdEntrenador())).thenReturn(List.of(rutina));
        assertThrows(RutinaExistente.class, () -> logicaRutinas.aniadirRutina(rutina));
    }
    
    @Test
    @DisplayName("Lanza excepción al modificar rutina no existente")
    void lanzarExcepcionAlModificarRutinaNoExistente() {
        Long idRutina = 1L;
        Rutinas rutina = new Rutinas();
        rutina.setIdRutinas(idRutina);
        when(rutinaRepo.existsById(idRutina)).thenReturn(false);
        assertThrows(RutinaNoEncontrada.class, () -> logicaRutinas.modificarRutina(idRutina, rutina));
    }

    @Test
    @DisplayName("Obtiene lista de rutinas de un entrenador existente")
    void obtenerListaRutinasDeEntrenadorExistente() {
        Long idEntrenador = 1L;
        List<Rutinas> rutinas = List.of(new Rutinas(), new Rutinas());
        when(rutinaRepo.findByIdEntrenador(idEntrenador)).thenReturn(rutinas);
        List<Rutinas> result = logicaRutinas.obtenerRutinas(idEntrenador);
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(rutinaRepo).findByIdEntrenador(idEntrenador);
    }
    
    @Test
    @DisplayName("Obtiene y establece links")
    void obtenerYEstablecerLinks() {
        URI uri = URI.create("http://example.com");
        Links links = new Links();
        links.setSelf(uri);
        assertEquals(uri, links.getSelf());
    }
    
    @Test
    @DisplayName("Constructor de Links con URI")
    void constructorDeLinksConURI() {
        URI uri = URI.create("http://example.com");
        Links links = new Links(uri);
        assertEquals(uri, links.getSelf());
    }
    
    @Test
    @DisplayName("Builder de Links")
    void builderDeLinks() {
        URI uri = URI.create("http://example.com");
        Links links = Links.builder().self(uri).build();
        assertEquals(uri, links.getSelf());
    }
    
    @Test
    @DisplayName("Constructor sin argumentos de Links")
    void constructorSinArgumentosDeLinks() {
        Links links = new Links();
        assertNull(links.getSelf());
    }
    
    @Test
    @DisplayName("Convierte DTO a entidad")
    void convertirDTOAEntidad() {
        String nombre = "Rutina Nueva";
        String descripcion = "Descripción de la nueva rutina";
        String observaciones = "Observaciones de la nueva rutina";
        List<FragmentoRutinaDTO> ejerciciosDTO = Collections.emptyList();
        List<FragmentoRutina> ejercicios = Collections.emptyList();
        RutinaNuevaDTO dto = new RutinaNuevaDTO();
        dto.setNombre(nombre);
        dto.setDescripcion(descripcion);
        dto.setObservaciones(observaciones);
        dto.setEjercicios(ejerciciosDTO);
        Rutinas rutina = dto.toEntity();
        assertEquals(nombre, rutina.getNombre());
        assertEquals(descripcion, rutina.getDescripcion());
        assertEquals(observaciones, rutina.getObservaciones());
        assertEquals(ejercicios, rutina.getFragmentoRutina());
    }
    
    @Test
    @DisplayName("Obtiene y establece valores en RutinaNuevaDTO")
    void obtenerYEstablecerValoresEnRutinaNuevaDTO() {
        String nombre = "Rutina Nueva";
        String descripcion = "Descripción de la nueva rutina";
        String observaciones = "Observaciones de la nueva rutina";
        List<FragmentoRutinaDTO> ejercicios = Collections.emptyList();
        RutinaNuevaDTO dto = new RutinaNuevaDTO();
        dto.setNombre(nombre);
        dto.setDescripcion(descripcion);
        dto.setObservaciones(observaciones);
        dto.setEjercicios(ejercicios);
        assertEquals(nombre, dto.getNombre());
        assertEquals(descripcion, dto.getDescripcion());
        assertEquals(observaciones, dto.getObservaciones());
        assertEquals(ejercicios, dto.getEjercicios());
    }
    
    @Test
    @DisplayName("Constructor con todos los argumentos en RutinaNuevaDTO")
    void constructorConTodosLosArgumentosEnRutinaNuevaDTO() {
        String nombre = "Rutina Nueva";
        String descripcion = "Descripción de la nueva rutina";
        String observaciones = "Observaciones de la nueva rutina";
        List<FragmentoRutinaDTO> ejercicios = Collections.emptyList();
        RutinaNuevaDTO dto = new RutinaNuevaDTO(nombre, descripcion, observaciones, ejercicios);
        assertEquals(nombre, dto.getNombre());
        assertEquals(descripcion, dto.getDescripcion());
        assertEquals(observaciones, dto.getObservaciones());
        assertEquals(ejercicios, dto.getEjercicios());
    }
    
    @Test
    @DisplayName("Constructor sin argumentos en RutinaNuevaDTO")
    void constructorSinArgumentosEnRutinaNuevaDTO() {
        RutinaNuevaDTO dto = new RutinaNuevaDTO();
        assertNotNull(dto);
    }
    
    @Test
    @DisplayName("Convierte DTO a entidad con lista nula")
    void convertirDTOAEntidadConListaNula() {
        String nombre = "Rutina sin Ejercicios";
        String descripcion = "Descripción de la rutina sin ejercicios";
        String observaciones = "Observaciones de la rutina sin ejercicios";
        RutinaNuevaDTO dto = new RutinaNuevaDTO();
        dto.setNombre(nombre);
        dto.setDescripcion(descripcion);
        dto.setObservaciones(observaciones);
        dto.setEjercicios(null);
        Rutinas rutina = dto.toEntity();
        assertEquals(nombre, rutina.getNombre());
        assertEquals(descripcion, rutina.getDescripcion());
        assertEquals(observaciones, rutina.getObservaciones());
        assertNull(rutina.getFragmentoRutina());
    }
    
    @Test
    @DisplayName("Constructor con todos los argumentos en EjerciciosDTO")
    void constructorConTodosLosArgumentosEnEjerciciosDTO() {
        Long idEjercicio = 1L;
        String nombre = "Ejercicio Nuevo";
        String descripcion = "Descripción del ejercicio";
        String observaciones = "Observaciones del ejercicio";
        String tipo = "Tipo de ejercicio";
        String musculosTrabajados = "Músculos trabajados";
        String material = "Material necesario";
        String dificultad = "Dificultad del ejercicio";
        List<String> multimedia = Arrays.asList("link1", "link2");
        Long idEntrenador = 1L;
        Links links = Links.builder().self(URI.create("http://example.com")).build();
        EjerciciosDTO dto = new EjerciciosDTO(idEjercicio, nombre, descripcion, observaciones, tipo, musculosTrabajados, material, dificultad, multimedia, idEntrenador, links);
        assertEquals(idEjercicio, dto.getIdEjercicio());
        assertEquals(nombre, dto.getNombre());
        assertEquals(descripcion, dto.getDescripcion());
        assertEquals(observaciones, dto.getObservaciones());
        assertEquals(tipo, dto.getTipo());
        assertEquals(musculosTrabajados, dto.getMusculosTrabajados());
        assertEquals(material, dto.getMaterial());
        assertEquals(dificultad, dto.getDificultad());
        assertEquals(multimedia, dto.getMultimedia());
        assertEquals(idEntrenador, dto.getIdEntrenador());
        assertEquals(links, dto.getLinks());
    }
    
    @Test
    @DisplayName("Constructor sin argumentos en EjerciciosDTO")
    void constructorSinArgumentosEnEjerciciosDTO() {
        EjerciciosDTO dto = new EjerciciosDTO();
        assertNotNull(dto);
    }
    
    @Test
    @DisplayName("Método builder en EjerciciosDTO")
    void metodoBuilderEnEjerciciosDTO() {
        Long idEjercicio = 1L;
        String nombre = "Ejercicio Nuevo";
        String descripcion = "Descripción del ejercicio";
        String observaciones = "Observaciones del ejercicio";
        String tipo = "Tipo de ejercicio";
        String musculosTrabajados = "Músculos trabajados";
        String material = "Material necesario";
        String dificultad = "Dificultad del ejercicio";
        List<String> multimedia = Arrays.asList("link1", "link2");
        Long idEntrenador = 1L;
        Links links = Links.builder().self(URI.create("http://example.com")).build();
        EjerciciosDTO dto = EjerciciosDTO.builder()
            .idEjercicio(idEjercicio)
            .nombre(nombre)
            .descripcion(descripcion)
            .observaciones(observaciones)
            .tipo(tipo)
            .musculosTrabajados(musculosTrabajados)
            .material(material)
            .dificultad(dificultad)
            .multimedia(multimedia)
            .idEntrenador(idEntrenador)
            .links(links)
            .build();
        assertEquals(idEjercicio, dto.getIdEjercicio());
        
    }
    
    @Test
    @DisplayName("Obtiene nombre de usuario desde token válido")
    void obtenerNombreDeUsuarioDesdeTokenValido() {
        String token = jwtUtil.generateToken(jwtUtil.createUserDetails("testUser", "password", new ArrayList<>()));
        String username = jwtUtil.getUsernameFromToken(token);
        assertEquals("testUser", username);
    }
    
    @Test
    @DisplayName("Obtiene fecha de expiración desde token válido")
    void obtenerFechaDeExpiracionDesdeTokenValido() {
        String token = jwtUtil.generateToken(jwtUtil.createUserDetails("testUser", "password", new ArrayList<>()));
        Date expirationDate = jwtUtil.getExpirationDateFromToken(token);
        assertNotNull(expirationDate);
    }
    
    @Test
    @DisplayName("Obtiene claim desde token válido")
    void obtenerClaimDesdeTokenValido() {
        String token = jwtUtil.generateToken(jwtUtil.createUserDetails("testUser", "password", new ArrayList<>()));
        String username = jwtUtil.getClaimFromToken(token, Claims::getSubject);
        assertEquals("testUser", username);
    }
    
    @Test
    @DisplayName("Token válido no está expirado")
    void tokenValidoNoEstaExpirado() {
        String token = jwtUtil.generateToken(jwtUtil.createUserDetails("testUser", "password", new ArrayList<>()));
        boolean isExpired = jwtUtil.isTokenExpired(token);
        assertFalse(isExpired);
    }
    
    @Test
    @DisplayName("Genera token con claims válidos")
    void generarTokenConClaimsValidos() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("key1", "value1");
        claims.put("key2", "value2");
        String token = jwtUtil.doGenerateToken(claims, "testUser");
        assertNotNull(token);
    }
    
    @Test
    @DisplayName("Valida token con detalles de usuario válidos")
    void validarTokenConDetallesDeUsuarioValidos() {
        String token = jwtUtil.generateToken(jwtUtil.createUserDetails("testUser", "password", new ArrayList<>()));
        UserDetails userDetails = jwtUtil.createUserDetails("testUser", "password", new ArrayList<>());
        boolean isValid = jwtUtil.validateToken(token, userDetails);
        assertTrue(isValid);
    }
    
    @Test
    @DisplayName("Crea UserDetails con argumentos válidos")
    void crearUserDetailsConArgumentosValidos() {
        String username = "testUser";
        String password = "password";
        List<String> roles = List.of("ROLE_USER");
        UserDetails userDetails = jwtUtil.createUserDetails(username, password, roles);
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }
    
    @Test
    @DisplayName("Obtiene rutinas por ID de entrenador")
    void obtenerRutinasPorIdDeEntrenador() {
        Long idEntrenador = 1L;
        List<Rutinas> rutinasList = new ArrayList<>();
        when(rutinaRepo.findByIdEntrenador(idEntrenador)).thenReturn(rutinasList);
        List<Rutinas> result = logicaRutinas.obtenerRutinas(idEntrenador);
        assertNotNull(result);
        assertEquals(rutinasList, result);
    }
    
    @Test
    @DisplayName("Obtiene rutina por ID válido")
    void obtenerRutinaPorIdValido() {
        Long idRutina = 1L;
        Rutinas rutina = new Rutinas();
        when(rutinaRepo.findById(idRutina)).thenReturn(Optional.of(rutina));
        Optional<Rutinas> result = logicaRutinas.obtenerRutina(idRutina);
        assertTrue(result.isPresent());
        assertEquals(rutina, result.get());
    }
    

    @Test
    @DisplayName("Elimina rutina por ID válido")
    void eliminarRutinaPorIdValido() {
        Long idRutina = 1L;
        Rutinas rutina = new Rutinas();
        when(rutinaRepo.findById(idRutina)).thenReturn(Optional.of(rutina));

        logicaRutinas.eliminarRutina(idRutina);

        Optional<Rutinas> foundRutina = rutinaRepo.findById(idRutina);
        assertTrue(foundRutina.isPresent());

        when(rutinaRepo.findById(idRutina)).thenReturn(Optional.empty());

        Optional<Rutinas> deletedRutina = rutinaRepo.findById(idRutina);
        assertFalse(deletedRutina.isPresent());
    }

    
    
    @Test
    @DisplayName("Lanza excepción al añadir rutina con ID de entrenador existente")
    void lanzarExcepcionAlAniadirRutinaConIdDeEntrenadorExistente() {
        Rutinas nuevaRutina = new Rutinas();
        nuevaRutina.setIdEntrenador(1L);
        List<Rutinas> lista = new ArrayList<>();
        lista.add(nuevaRutina);
        when(rutinaRepo.findByIdEntrenador(nuevaRutina.getIdEntrenador())).thenReturn(lista);
        assertThrows(RutinaExistente.class, () -> logicaRutinas.aniadirRutina(nuevaRutina));
    }
    
    @Test
    @DisplayName("Obtiene rutina por ID")
    void obtenerRutinaPorId() {
        Long idRutina = 1L;
        Rutinas rutina = new Rutinas();
        when(rutinaRepo.findById(idRutina)).thenReturn(Optional.of(rutina));
        Rutinas result = logicaRutinas.getRutinasById(idRutina);
        assertEquals(rutina, result);
    }
    
    @Test
    @DisplayName("Lanza excepción al obtener rutina por ID no existente")
    void lanzarExcepcionAlObtenerRutinaPorIdNoExistente() {
        Long idRutina = 1L;
        when(rutinaRepo.findById(idRutina)).thenReturn(Optional.empty());
        assertThrows(RutinaNoEncontrada.class, () -> logicaRutinas.getRutinasById(idRutina));
    }
    
    @Test
    @DisplayName("Obtiene lista de rutinas de un entrenador sin rutinas asociadas")
    void obtenerListaRutinasDeEntrenadorSinRutinasAsociadas() {
        Long idEntrenador = 2L;
        when(rutinaRepo.findByIdEntrenador(idEntrenador)).thenReturn(new ArrayList<>());
        List<Rutinas> result = logicaRutinas.obtenerRutinas(idEntrenador);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    

    @Test
    @DisplayName("Elimina rutina existente")
    void eliminarRutinaExistentePorId() {
        Long idRutina = 1L;
        Rutinas rutina = new Rutinas();
        when(rutinaRepo.findById(idRutina)).thenReturn(Optional.of(rutina));
        logicaRutinas.eliminarRutina(idRutina);
        when(rutinaRepo.findById(idRutina)).thenReturn(Optional.empty());
        Optional<Rutinas> foundRutina = rutinaRepo.findById(idRutina);
        assertFalse(foundRutina.isPresent());
    }

    
    @Test
    @DisplayName("Lanza excepción al eliminar rutina con ID no existente")
    void lanzarExcepcionAlEliminarRutinaConIdNoExistente() {
        Long idRutina = 999L;
        when(rutinaRepo.findById(idRutina)).thenReturn(Optional.empty());
        assertThrows(RutinaNoEncontrada.class, () -> logicaRutinas.eliminarRutina(idRutina));
        Optional<Rutinas> foundRutina = rutinaRepo.findById(idRutina);
        assertFalse(foundRutina.isPresent());
    }

    

    @Test
    @DisplayName("Añadir rutina existente lanza excepción")
    public void aniadirRutinaExistenteLanzaExcepcion() {
        Rutinas nuevaRutina = new Rutinas();
        nuevaRutina.setIdEntrenador(1L);

        when(rutinaRepo.findByIdEntrenador(nuevaRutina.getIdEntrenador())).thenReturn(List.of(nuevaRutina));

        assertThrows(RutinaExistente.class, () -> logicaRutinas.aniadirRutina(nuevaRutina));
    }

    @Test
    @DisplayName("Añadir nueva rutina exitosamente")
    public void aniadirNuevaRutinaExitosamente() {
        Rutinas nuevaRutina = new Rutinas();
        nuevaRutina.setIdEntrenador(1L);
        nuevaRutina.setNombre("Rutina Nueva");
        nuevaRutina.setDescripcion("Descripción de la rutina nueva");

        when(rutinaRepo.findByIdEntrenador(nuevaRutina.getIdEntrenador())).thenReturn(List.of());
        when(rutinaRepo.save(any(Rutinas.class))).thenAnswer(invocation -> {
            Rutinas savedRutina = invocation.getArgument(0);
            savedRutina.setIdRutinas(1L);
            return savedRutina;
        });

        Long result = logicaRutinas.aniadirRutina(nuevaRutina);

        assertNotNull(result);
        assertEquals(1L, result);
        verify(rutinaRepo).save(nuevaRutina);
    }

    @Test
    @DisplayName("Modificar rutina con nombre existente lanza excepción")
    public void modificarRutinaConNombreExistenteLanzaExcepcion() {
        Rutinas rutina = new Rutinas();
        rutina.setIdRutinas(1L);
        rutina.setNombre("Nombre existente");
        
        when(rutinaRepo.findByNombre(rutina.getNombre())).thenReturn(rutina);
        
        assertThrows(RutinaExistente.class, () -> logicaRutinas.modificarRutina(1L, rutina));
    }
    
    @Test
    @DisplayName("Modificar rutina no existente lanza excepción")
    public void modificarRutinaNoExistenteLanzaExcepcion() {
        Long idRutina = 1L;
        Rutinas rutina = new Rutinas();
        rutina.setIdRutinas(idRutina);
        
        when(rutinaRepo.existsById(idRutina)).thenReturn(false);
        
        assertThrows(RutinaNoEncontrada.class, () -> logicaRutinas.modificarRutina(idRutina, rutina));
    }
    


    @Test
    @DisplayName("Modificar rutina exitosamente")
    public void modificarRutinaExitosamente() {
        Long idRutina = 1L;
        Rutinas rutina = new Rutinas();
        rutina.setIdRutinas(idRutina);
        rutina.setNombre("Nombre modificado");
        rutina.setDescripcion("Descripción modificada");
        rutina.setObservaciones("Observaciones modificadas");
    
        Rutinas existingRutina = new Rutinas();
        existingRutina.setIdRutinas(idRutina);
        existingRutina.setNombre("Nombre original");
        existingRutina.setDescripcion("Descripción original");
        existingRutina.setObservaciones("Observaciones originales");
    
        when(rutinaRepo.existsById(idRutina)).thenReturn(true);
        when(rutinaRepo.findById(idRutina)).thenReturn(Optional.of(existingRutina));
        when(rutinaRepo.save(any(Rutinas.class))).thenAnswer(invocation -> invocation.getArgument(0));
    
        logicaRutinas.modificarRutina(idRutina, rutina);
    
        verify(rutinaRepo).save(existingRutina);
    
        assertEquals("Nombre modificado", existingRutina.getNombre());
        assertEquals("Descripción modificada", existingRutina.getDescripcion());
        assertEquals("Observaciones modificadas", existingRutina.getObservaciones());
    }

    
        }
    

   
