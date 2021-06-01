package es.alvaroCDN1.tiendaPokemon.articulos;

/**
 * Clase que representa un articulo.
 * En el caso del entrenador seran llamados 'objetos'
 */
public class Articulo {
    private String nombre;
    private String descripcion;
    private int precio;

    /**
     * Costructor de la clase Articulo
     *
     * @param nombre El nombre que queremos que tenga el articulo
     */
    public Articulo(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nombre del articulo
     *
     * @return El nombre del articulo
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve la descripcion del articulo
     *
     * @return La descripcion del articulo
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Devuelve el precio del articulo
     *
     * @return El precio del articulo
     */
    public int getPrecio() {
        return this.precio;
    }

    /**
     * Establece un nombre para el articulo
     *
     * @param nombre El nombre que deseamos darle al articulo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece una descripcion para el articulo
     *
     * @param descripcion La descripcion que deseamos poner al articulo
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Establece el precio del articulo
     *
     * @param precio El precio que queremos que tenga el articulo
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {

        String string = "Nombre: " + getNombre() + "\n\t" +
                getDescripcion() + '\n';

        return string;
    }
}
