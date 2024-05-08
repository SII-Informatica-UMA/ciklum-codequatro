package CodeQuatro.Entidades_Cicklum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import CodeQuatro.Entidades_Cicklum.entities.AccesoDatos;
import CodeQuatro.Entidades_Cicklum.repositories.RutinasRepository;
import CodeQuatro.Entidades_Cicklum.servicios.EntidadesCicklumApplication;

@SpringBootTest(classes=EntidadesCicklumApplication.class)
class EntidadesCicklumApplicationTests {

 private AccesoDatos ad;
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


   @Test
	void contextLoads() {
	}

}
