package CodeQuatro.Entidades_Cicklum.excepciones;

public class EjercicioExistenteException extends RuntimeException {

    public EjercicioExistenteException() {
        super();
    }
    public EjercicioExistenteException(String msg) {
        super(msg);
    }
}
