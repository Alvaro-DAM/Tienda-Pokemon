package es.alvaroCDN1.tiendaPokemon.pokemon;

import java.util.Arrays;
import java.util.Random;

/**
 * Clase que representa a un pokemon 'Charmander'
 */
public class Charmander extends TipoFuego {

    private double peso;
    private double altura;

    /**
     * Constructor de la clase Charmander
     *
     * @param nivel El nivel al que deseamos que este
     */
    public Charmander(int nivel) {
        super("Charmander", nivel);

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
        descripcion.append(super.toString());
        descripcion.append("Habilidad: " + getHabilidad() + '\n');
        descripcion.append("Peso: " + this.peso + "Kg, Altura: " + this.altura + "m\n");
        descripcion.append("Movimientos: " + '\n');
        descripcion.append('\t' + Arrays.toString(getMovimientos()) + '\n');

        return descripcion.toString();
    }

}
