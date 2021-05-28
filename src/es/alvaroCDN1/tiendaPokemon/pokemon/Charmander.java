package es.alvaroCDN1.tiendaPokemon.pokemon;

import java.util.Arrays;
import java.util.Random;

public class Charmander extends TipoFuego {

    private double peso;
    private double altura;

    public Charmander() {
        super("Charmander", 5);

        this.peso = 8.5;
        this.altura = 0.6;

        setHabilidad("Mar llamas");

        setMovimientos("Placaje");
        setMovimientos("Gruñido");
        setMovimientos("Ascuas");
        setMovimientos("Pantalla Humo");
    }

    @Override
    public String toString() {
        StringBuilder descripcion = new StringBuilder();

        descripcion.append("Nombre: " + getNombre() + '\n');
        descripcion.append("Entrenador original: " + getEntrenadorOriginal() + '\n');
        descripcion.append("Lvl: " + getNivel() + '\n');
        descripcion.append("Tipo: " + getTipo() + '\n');
        descripcion.append("Habilidad: " + getHabilidad() + '\n');
        descripcion.append("Peso: " + this.peso + ", Altura: " + this.altura + '\n');
        descripcion.append("Movimientos: " + '\n');
        descripcion.append('\t' + Arrays.toString(getMovimientos()) + '\n');

        return descripcion.toString();
    }

}
