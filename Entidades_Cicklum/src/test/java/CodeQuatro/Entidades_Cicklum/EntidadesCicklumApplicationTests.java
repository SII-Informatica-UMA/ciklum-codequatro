package CodeQuatro.Entidades_Cicklum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CodeQuatro.Entidades_Cicklum.entities.AccesoDatos;
import CodeQuatro.Entidades_Cicklum.servicios.EntidadesCicklumApplication;

@SpringBootTest(classes=EntidadesCicklumApplication.class)
class EntidadesCicklumApplicationTests {

 private AccesoDatos ad;

    @BeforeEach
    public void setup() {
        ad = new AccesoDatos();
    }

    @AfterEach
    public void teardown() {
        ad.close();
    }


   @Test
	void contextLoads() {
	}

}
