package CodeQuatro.Entidades_Cicklum.excepciones;

public class EjercicioNoEncontrado extends RuntimeException{

    public EjercicioNoEncontrado() {
        super();
    }
    public EjercicioNoEncontrado(String msg) {
        super(msg);
    }
}
