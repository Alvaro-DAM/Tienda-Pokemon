package es.alvaroCDN1.tiendaPokemon.excepciones;

public class CantindadInvalidadException extends Exception {
    public CantindadInvalidadException() {

    }

    public CantindadInvalidadException(String msg) {
        super(msg);
    }
}
