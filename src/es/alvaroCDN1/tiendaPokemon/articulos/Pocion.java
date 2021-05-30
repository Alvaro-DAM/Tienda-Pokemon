package es.alvaroCDN1.tiendaPokemon.articulos;

public class Pocion extends Articulo {

    private int hpRecupera;

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
