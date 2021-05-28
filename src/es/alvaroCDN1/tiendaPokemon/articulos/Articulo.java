package es.alvaroCDN1.tiendaPokemon.articulos;

public class Articulo {
    private String nombre;
    private String descripcion;
    private int precio;

    public Articulo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrecio() {
        return this.precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
