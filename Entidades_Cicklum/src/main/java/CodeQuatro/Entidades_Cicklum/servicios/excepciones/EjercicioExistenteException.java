package CodeQuatro.Entidades_Cicklum.servicios.excepciones;

public class EjercicioExistenteException extends RuntimeException {

    public EjercicioExistenteException() {
        super();
    }
    public EjercicioExistenteException(String msg) {
        super(msg);
    }
}