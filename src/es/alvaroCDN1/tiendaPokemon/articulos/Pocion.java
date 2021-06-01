package es.alvaroCDN1.tiendaPokemon.articulos;

/**
 * Clase que representa un articulo/objeto del tipo 'Pocion'
 */
public class Pocion extends Articulo {

    private int hpRecupera;

    /**
     * Constructor de la clase Pocion
     *
     * @param nombre        El nombre de la pocion
     * @param precio        El precio del objeto
     * @param hpQueRecupera La cantidad de vida que recupera al aplicar a un pokemon
     */
    public Pocion(String nombre, int precio, int hpQueRecupera) {
        super(nombre);
        setPrecio(precio);
        this.hpRecupera = hpQueRecupera;
    }

    @Override
    public String toString() {

        String string = super.toString() +
                "Recupera: " + this.hpRecupera + "HP\n";

        return string;
    }
}
