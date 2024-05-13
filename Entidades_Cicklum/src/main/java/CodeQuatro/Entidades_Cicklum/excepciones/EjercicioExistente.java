package CodeQuatro.Entidades_Cicklum.excepciones;

public class EjercicioExistente extends RuntimeException {

    public EjercicioExistente() {
        super();
    }
    public EjercicioExistente(String msg) {
        super(msg);
    }
}
