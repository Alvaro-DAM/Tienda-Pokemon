package es.alvaroCDN1.tiendaPokemon.articulos;

/**
 * Clase que representa un articulo/objeto del tipo 'Medicina'
 */
public class Medicina extends Articulo {

    private String estadoQueCura;

    /**
     * Constructor de la clase Medicina
     *
     * @param nombre        El nombre de la medicina
     * @param descripcion   La descripcion del objeto
     * @param precio        El precio del objeto
     * @param estadoQueCura El tipo de estado que cura al aplicar a un pokemon
     */
    public Medicina(String nombre, String descripcion, int precio, String estadoQueCura) {
        super(nombre);
        setDescripcion(descripcion);
        setPrecio(precio);
        this.estadoQueCura = estadoQueCura;
    }

    @Override
    public String toString() {

        String string = super.toString() +
                "Cura estado: " + this.estadoQueCura + '\n';

        return string;
    }
}
