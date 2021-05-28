package es.alvaroCDN1.tiendaPokemon.excepciones;

/**
 * Excepcion lanzada por metodos para indicar que no hay 'stock' de un articulo
 */
public class NoExisteArticuloException extends Exception{

    public NoExisteArticuloException() {

    }

    public NoExisteArticuloException(String msg) {
        super(msg);
    }
}
