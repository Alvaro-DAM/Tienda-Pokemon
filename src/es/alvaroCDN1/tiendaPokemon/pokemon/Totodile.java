package es.alvaroCDN1.tiendaPokemon.pokemon;

import java.util.Arrays;

/**
 * Clase que representa a un pokemon 'Totodile'
 */
public class Totodile extends TipoAgua{

    private double peso;
    private double altura;

    /**
     * Constructor de la clase Totodile
     *
     * @param nivel El nivel al que deseamos que este
     */
    public Totodile(int nivel) {
        super("Totodile", nivel);

        this.peso = 9.5;
        this.altura = 0.6;

        setHabilidad("Torrente");

        setMovimientos("Arañazo");
        setMovimientos("Malicioso");
        setMovimientos("Pistola Agua");
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
