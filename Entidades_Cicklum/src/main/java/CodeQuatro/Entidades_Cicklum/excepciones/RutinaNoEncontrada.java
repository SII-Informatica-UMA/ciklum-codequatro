package CodeQuatro.Entidades_Cicklum.excepciones;

public class RutinaNoEncontrada extends RuntimeException{
    
    public RutinaNoEncontrada() {
        super();
    }

    public RutinaNoEncontrada(String msg) {
        super(msg);
    }
    
}
