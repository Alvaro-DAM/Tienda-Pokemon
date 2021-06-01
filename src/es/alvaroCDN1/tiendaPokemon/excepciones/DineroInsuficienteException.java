package es.alvaroCDN1.tiendaPokemon.excepciones;

/**
 * Excepcion lanzada por algunos metodos para indicar que no se dispone del dinero necesario para realizar una compra
 */
public class DineroInsuficienteException extends Exception {

    public DineroInsuficienteException() {

    }

    public DineroInsuficienteException(String msg) {
        super(msg);
    }
}
