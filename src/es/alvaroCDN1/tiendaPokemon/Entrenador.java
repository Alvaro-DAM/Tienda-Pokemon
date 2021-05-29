package es.alvaroCDN1.tiendaPokemon;

import es.alvaroCDN1.tiendaPokemon.articulos.Articulo;
import es.alvaroCDN1.tiendaPokemon.articulos.Medicina;
import es.alvaroCDN1.tiendaPokemon.articulos.Pocion;
import es.alvaroCDN1.tiendaPokemon.articulos.PokeBall;
import es.alvaroCDN1.tiendaPokemon.excepciones.CantindadInvalidadException;
import es.alvaroCDN1.tiendaPokemon.excepciones.NoExisteArticuloException;
import es.alvaroCDN1.tiendaPokemon.pokemon.Pokemon;

import java.util.HashMap;

public class Entrenador {
    private HashMap<Articulo, Integer> bolsilloPocion;
    private HashMap<Articulo, Integer> bolsilloMedicina;
    private HashMap<Articulo, Integer> bolsilloPokeball;

    private final int CAPACIDAD_MAX_MOCHILA = 42;
    private int monedero;

    private Pokemon[] equipoPokemon;

    private String nombre;

    public Entrenador(String nombre) {
        this.nombre = nombre;

        this.bolsilloPocion = new HashMap<Articulo, Integer>();
        this.bolsilloMedicina = new HashMap<>();
        this.bolsilloPokeball = new HashMap<>();

        this.monedero = 900000;

        this.equipoPokemon = new Pokemon[3];
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getMonedero() {
        return this.monedero;
    }

    public int getCantidadPocion(Pocion pocion) {
        return this.bolsilloPocion.get(pocion);
    }

    public int getCantidadMedicina(Medicina medicina) {
        return this.bolsilloMedicina.get(medicina);
    }

    public int getCantidadPokeball(PokeBall pokeBall) {
        return this.bolsilloPokeball.get(pokeBall);
    }

    public void setMonedero(int dinero) {
        this.monedero += dinero;
    }

    public boolean comprobarSiPocion(Articulo objeto) {
        return objeto instanceof Pocion;
    }

    public boolean comprobarSiMedicina(Articulo objeto) {
        return objeto instanceof Medicina;
    }

    public boolean comprobarSiPokeball(Articulo objeto) {
        return objeto instanceof PokeBall;
    }

    /**
     * Comprueba si la cantidad de articulos que se pide es valida
     *
     * @param objeto   El objeto del cual queremos comprobar si la cantidad es correta
     * @param cantidad La cantidad a comprobar
     * @return <code>true</code> si la cantidad es correcta y <code>false</code> en caso contrario
     * @throws CantindadInvalidadException
     */
    public boolean comprobarCantidad(Articulo objeto, int cantidad) throws CantindadInvalidadException {
        boolean cantidadCorrecta = false;

        int stockArticulo = 0;

        if (comprobarSiPocion(objeto)) {
            stockArticulo = getCantidadPocion((Pocion) objeto);
        } else if (comprobarSiMedicina(objeto)) {
            stockArticulo = getCantidadMedicina((Medicina) objeto);
        } else if (comprobarSiPokeball(objeto)) {
            stockArticulo = getCantidadPokeball((PokeBall) objeto);
        }

        if (cantidad > stockArticulo || cantidad <= 0) {
            throw new CantindadInvalidadException();
        } else {
            cantidadCorrecta = true;
        }

        return cantidadCorrecta;
    }

    /**
     * Anade un objeto al bolsillo correpondiente, simulando una mochila
     *
     * @param objeto El objeto que deseamos anadir al bolsillo
     * @param cantidad La cantidad de ese objeto que vamos a anadir
     * @return <code>true</code> si se ha anadido correctamente y <code>false</code> si no
     */
    public boolean anadirObjeto(Articulo objeto, int cantidad) {
        boolean anadido = true;

        if ((getCantidadTotalObjetos() + cantidad) < CAPACIDAD_MAX_MOCHILA && !mochilaLlena()) {
            if (comprobarSiPocion(objeto)) {
                if (!this.bolsilloPocion.containsKey(objeto)) {
                    this.bolsilloPocion.put(objeto, cantidad);
                } else {
                    this.bolsilloPocion.put(objeto, getCantidadPocion((Pocion) objeto) + cantidad);
                }
            } else if (comprobarSiMedicina(objeto)) {
                if (!this.bolsilloMedicina.containsKey(objeto)) {
                    this.bolsilloMedicina.put(objeto, cantidad);
                } else {
                    this.bolsilloMedicina.put(objeto, getCantidadMedicina((Medicina) objeto) + cantidad);
                }
            } else if (comprobarSiPokeball(objeto)) {
                if (!this.bolsilloPokeball.containsKey(objeto)) {
                    this.bolsilloPokeball.put(objeto, cantidad);
                } else {
                    this.bolsilloPokeball.put(objeto, getCantidadPokeball((PokeBall) objeto) + cantidad);
                }
            }
        } else {
            System.out.println("Vaya, no tienes espacio suficiente para guardar tantos objetos");
            anadido = false;
        }

        return anadido;
    }

    /**
     * Retira un objeto de la "mochila"
     * Si la cantidad del objeto es 0, el objeto es removido del bolsillo
     *
     * @param objeto El objeto que deseamos retirar del bolsillo
     * @param cantidad La cantidad del mismo que vamos a retirar
     */
    public void retirarObjeto(Articulo objeto, int cantidad) {
        anadirObjeto(objeto, (cantidad * -1));

        if (comprobarSiPocion(objeto)) {
            if (getCantidadPocion((Pocion) objeto) <= 0) {
                this.bolsilloPocion.remove(objeto);
            }
        } else if (comprobarSiMedicina(objeto)) {
            if (getCantidadMedicina((Medicina) objeto) <= 0) {
                this.bolsilloMedicina.remove(objeto);
            }
        } else if (comprobarSiPokeball(objeto)) {
            if (getCantidadPokeball((PokeBall) objeto) <= 0) {
                this.bolsilloPokeball.remove(objeto);
            }
        }
    }

    public int getCantidadTotalObjetos() {
        int cantidad = 0;

        for (Articulo objeto : this.bolsilloPocion.keySet()) {
            cantidad += getCantidadPocion((Pocion) objeto);
        }
        for (Articulo objeto : this.bolsilloPokeball.keySet()) {
            cantidad += getCantidadPokeball((PokeBall) objeto);
        }
        for (Articulo objeto : this.bolsilloMedicina.keySet()) {
            cantidad += getCantidadMedicina((Medicina) objeto);
        }

        return cantidad;
    }

    public boolean mochilaLlena() {
        return getCantidadTotalObjetos() >= CAPACIDAD_MAX_MOCHILA;
    }

    /**
     * Lista todos los objetos del bolsillo de "Pociones"
     */
    public String listarPociones() {
        StringBuilder listaPociones = new StringBuilder();

        for (Articulo pocion : this.bolsilloPocion.keySet()) {
            listaPociones.append(pocion.getNombre() + " x" + getCantidadPocion((Pocion) pocion) + '\n');
        }

        return listaPociones.toString();
    }

    /**
     * Lista todos los objetos del bolsillo de "Medicinas"
     */
    public String listarMedicinas() {
        StringBuilder listaMedicinas = new StringBuilder();

        for (Articulo medicina : this.bolsilloMedicina.keySet()) {
            listaMedicinas.append(medicina.getNombre() + " x" + getCantidadMedicina((Medicina) medicina) + '\n');
        }

        return listaMedicinas.toString();
    }

    /**
     * Lista todos los objetos del bolsillo de "Pokeballs"
     */
    public String listarPokeBalls() {
        StringBuilder listaPokeball = new StringBuilder();

        for (Articulo pokeball : this.bolsilloPokeball.keySet()) {
            listaPokeball.append(pokeball.getNombre() + " x" + getCantidadPokeball((PokeBall) pokeball) + '\n');
        }
        return listaPokeball.toString();
    }

    /**
     * Lista todos los objetos de todos los bolsillos
     */
    public String listarObjetos() {
        StringBuilder listaObjetos = new StringBuilder();

        listaObjetos.append(listarPociones());
        listaObjetos.append(listarMedicinas());
        listaObjetos.append(listarPokeBalls());

        return listaObjetos.toString();
    }

    /**
     * Obtiene un articulo de los bolsillos de la mochila
     *
     * @param nombreArticulo El nombre del articulo que queremos obtener
     * @return El articulo que buscamos
     * @throws NoExisteArticuloException Si el articulo no existe
     */
    public Articulo obtenerObjeto(String nombreArticulo) throws NoExisteArticuloException {
        Articulo articulo = null;

        for (Articulo pocion : this.bolsilloPocion.keySet()) {
            if (pocion.getNombre().equalsIgnoreCase(nombreArticulo)) {
                articulo = pocion;
            }
        }

        if (articulo == null) {
            for (Articulo medicina : this.bolsilloMedicina.keySet()) {
                if (medicina.getNombre().equalsIgnoreCase(nombreArticulo)) {
                    articulo = medicina;
                }
            }
        }

        if (articulo == null) {
            for (Articulo pokeball : this.bolsilloPokeball.keySet()) {
                if (pokeball.getNombre().equalsIgnoreCase(nombreArticulo)) {
                    articulo = pokeball;
                }
            }
        }

        if (articulo == null) {
            throw new NoExisteArticuloException();
        }

        return articulo;
    }
}
