package es.alvaroCDN1.tiendaPokemon.articulos;

public class Medicina extends Articulo {

    private String estadoQueCura;

    public Medicina(String nombre, String descripcion, int precio,String estadoQueCura) {
        super(nombre);
        setDescripcion(descripcion);
        setPrecio(precio);
        this.estadoQueCura = estadoQueCura;
    }
}
