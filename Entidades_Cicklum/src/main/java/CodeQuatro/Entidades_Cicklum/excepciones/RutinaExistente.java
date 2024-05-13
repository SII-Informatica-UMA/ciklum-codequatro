package CodeQuatro.Entidades_Cicklum.excepciones;

public class RutinaExistente extends RuntimeException{
    
    public RutinaExistente() {
        super();
    }
    public RutinaExistente(String msg) {
        super(msg);
    }
}
