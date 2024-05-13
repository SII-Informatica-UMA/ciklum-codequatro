package CodeQuatro.Entidades_Cicklum.excepciones;

public class EjercicioNoEncontradoException extends RuntimeException{

    public EjercicioNoEncontradoException() {
        super();
    }
    public EjercicioNoEncontradoException(String msg) {
        super(msg);
    }
}
