package CodeQuatro.Entidades_Cicklum;

import org.junit.jupiter.api.Test;

import CodeQuatro.Entidades_Cicklum.entities.AccesoDatos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;


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
