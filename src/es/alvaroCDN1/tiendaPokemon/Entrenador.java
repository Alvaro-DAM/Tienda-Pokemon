package es.alvaroCDN1.tiendaPokemon;

import es.alvaroCDN1.tiendaPokemon.articulos.Articulo;
import es.alvaroCDN1.tiendaPokemon.articulos.Medicina;
import es.alvaroCDN1.tiendaPokemon.articulos.Pocion;
import es.alvaroCDN1.tiendaPokemon.articulos.PokeBall;
import es.alvaroCDN1.tiendaPokemon.pokemon.Pokemon;

import java.util.HashMap;

public class Entrenador {
    private HashMap<Articulo, Integer> bolsilloPocion;
    private HashMap<Articulo, Integer> bolsilloMedicina;
    private HashMap<Articulo, Integer> bolsilloPokeball;

    private final int CAPACIDAD_MAX_MOCHILA = 50;
    private int monedero;

    private Pokemon[] equipoPokemon;

    private String nombre;

    public Entrenador(String nombre) {
        this.nombre = nombre;

        this.bolsilloPocion = new HashMap<Articulo, Integer>();
        this.bolsilloMedicina = new HashMap<>();
        this.bolsilloPokeball = new HashMap<>();

        this.monedero = 90000;

        this.equipoPokemon = new Pokemon[3];
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getMonedero() {
        return this.monedero;
    }

    public void setMonedero(int dinero) {
        this.monedero += dinero;
    }

    public void anadirObjeto(Articulo articulo, int cantidad) {
        if (articulo instanceof Pocion) {
            if (!this.bolsilloPocion.containsKey(articulo)) {
                this.bolsilloPocion.put(articulo, 1);
            } else {
                this.bolsilloPocion.put(articulo, this.bolsilloPocion.get(articulo) + cantidad);
            }
        } else if (articulo instanceof Medicina) {
            if (!this.bolsilloMedicina.containsKey(articulo)) {
                this.bolsilloMedicina.put(articulo, 1);
            } else {
                this.bolsilloMedicina.put(articulo, this.bolsilloMedicina.get(articulo) + cantidad);
            }
        } else if (articulo instanceof PokeBall) {
            if (!this.bolsilloPokeball.containsKey(articulo)) {
                this.bolsilloPokeball.put(articulo, 1);
            } else {
                this.bolsilloPokeball.put(articulo, this.bolsilloPokeball.get(articulo) + cantidad);
            }
        }
    }
}
