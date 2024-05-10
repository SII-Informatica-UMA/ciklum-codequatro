package CodeQuatro.Entidades_Cicklum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import CodeQuatro.Entidades_Cicklum.servicios.EntidadesCicklumApplication;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CodeQuatro.Entidades_Cicklum.servicios.EntidadesCicklumApplication.class)
class EntidadesCicklumApplicationTests {

        

        @Autowired
        private TestRestTemplate restTemplate;

    }