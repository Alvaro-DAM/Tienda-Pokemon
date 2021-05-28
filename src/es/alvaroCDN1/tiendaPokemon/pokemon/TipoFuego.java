package es.alvaroCDN1.tiendaPokemon.pokemon;

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
}
