package es.alvaroCDN1.tiendaPokemon.pokemon;

import java.util.Arrays;

public class TipoFuego extends Pokemon {
    private final String[] debilA = {"AGUA, TIERRA"};
    private final String[] fuerteContra = {"PLANTA, ACERO"};

    public TipoFuego(String nombre, int nivel) {
        super(nombre, nivel);
        setTipo("FUEGO");
    }

    public String[] getDebilA() {
        return debilA;
    }

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
