package es.alvaroCDN1.tiendaPokemon.pokemon;

import java.util.Arrays;

/**
 * Clase que representa a los pokemon de tipo fuego
 */
public class TipoFuego extends Pokemon {
    private final String[] debilA = {"AGUA, TIERRA"};
    private final String[] fuerteContra = {"PLANTA, ACERO"};

    /**
     * Constructor de la clase TipoFuego
     *
     * @param nombre El nombre del pokemon que vamos a crear
     * @param nivel  El nivel al cual deseamos que este
     */
    public TipoFuego(String nombre, int nivel) {
        super(nombre, nivel);
        setTipo("FUEGO");
    }

    /**
     * Obtiene los tipos a los cuales es debil el pokemon
     *
     * @return Los tipos a los cuales es debil
     */
    public String[] getDebilA() {
        return debilA;
    }

    /**
     * Obtiene los tipos contra los cuales es fuerte el pokemon
     *
     * @return Los tipos contra los cuales es fuerte
     */
    public String[] getFuerteContra() {
        return fuerteContra;
    }

    @Override
    public String toString() {
        String string = super.toString() +
                "Tipo:" + super.getTipo() + " (Debil contra: " + Arrays.toString(this.debilA) + ", Fuerte contra: " +
                Arrays.toString(this.fuerteContra) + '\n';

        return string;
    }
}
