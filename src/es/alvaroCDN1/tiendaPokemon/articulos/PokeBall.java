package es.alvaroCDN1.tiendaPokemon.articulos;

public class PokeBall extends Articulo{

    private double multiplicadorRatio;

    public PokeBall(String nombre, String descripcion, int precio,double ratioCaptura) {
        super(nombre);
        setDescripcion(descripcion);
        setPrecio(precio);
        this.multiplicadorRatio = ratioCaptura;
    }
}
