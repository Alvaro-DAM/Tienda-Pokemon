package es.alvaroCDN1.tiendaPokemon.excepciones;

/**
 * Excepcion lanzada por metodos para indicar que no existe el pokemon que se busca
 */
public class NoExistePokemonException extends Exception {

    public NoExistePokemonException() {

    }

    public NoExistePokemonException(String msg) {
        super(msg);
    }
}
