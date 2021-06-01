package es.alvaroCDN1.tiendaPokemon.pokemon;

import java.util.Arrays;

/**
 * Clase que representa a un pokemon 'Litten'
 */
public class Litten extends TipoFuego {

    private double peso;
    private double altura;

    /**
     * Constructor de la clase Litten
     *
     * @param nivel El nivel al que deseamos que este
     */
    public Litten(int nivel) {
        super("Litten", nivel);

        this.peso = 4.7;
        this.altura = 0.4;

        setHabilidad("Mar llamas");

        setMovimientos("Arañazo");
        setMovimientos("Gruñido");
        setMovimientos("Ascuas");
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
