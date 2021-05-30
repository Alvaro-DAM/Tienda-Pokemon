package es.alvaroCDN1.tiendaPokemon.excepciones;

public class NoExistePokemonException extends Exception{

    public NoExistePokemonException() {

    }

    public NoExistePokemonException(String msg) {
        super(msg);
    }
}
