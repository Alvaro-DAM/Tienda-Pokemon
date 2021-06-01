package es.alvaroCDN1.tiendaPokemon.articulos;

/**
 * Clase que representa un articulo/objeto del tipo 'Pokeball'
 */
public class PokeBall extends Articulo {

    private double multiplicadorRatio;

    /**
     * Constructor de la clase Pokeball
     *
     * @param nombre       El nombre de la pokeball
     * @param descripcion  La descripcion del objeto
     * @param precio       El precio del objeto
     * @param ratioCaptura El ratio que aumenta la posibilidad de captura
     */
    public PokeBall(String nombre, String descripcion, int precio, double ratioCaptura) {
        super(nombre);
        setDescripcion(descripcion);
        setPrecio(precio);
        this.multiplicadorRatio = ratioCaptura;
    }

    @Override
    public String toString() {

        String string = super.toString() +
                "Multiplicador de captura: x" + this.multiplicadorRatio + '\n';

        return string;
    }
}
