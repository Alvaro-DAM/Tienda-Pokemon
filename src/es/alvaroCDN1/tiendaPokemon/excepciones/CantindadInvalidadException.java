package es.alvaroCDN1.tiendaPokemon.excepciones;

/**
 * Excepcion que lanzan algunos metodos para indicar que la cantidad de objetos introducida no es valida
 */
public class CantindadInvalidadException extends Exception {
    public CantindadInvalidadException() {

    }

    public CantindadInvalidadException(String msg) {
        super(msg);
    }
}
