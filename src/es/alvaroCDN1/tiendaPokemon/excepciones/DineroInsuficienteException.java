package es.alvaroCDN1.tiendaPokemon.excepciones;

public class DineroInsuficienteException extends Exception {

    public DineroInsuficienteException() {

    }

    public DineroInsuficienteException(String msg) {
        super(msg);
    }
}
