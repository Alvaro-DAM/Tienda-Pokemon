package es.alvaroCDN1.tiendaPokemon.pokemon;

import es.alvaroCDN1.tiendaPokemon.Entrenador;

/**
 * Clase que reprsenta en general a un pokemon
 */
public class Pokemon {
    private String nombre;
    private String tipo;
    private String habilidad;
    private String[] movimientos;
    private String entrenadorOriginal;
    private int nivel;
    private int next; // Primera posicion libre en la lista de movimientos

    /**
     * Constructor de la clase 'Pokemon'
     *
     * @param nombre El nombre del pokemon a crear
     * @param nivel  El nivel al que deseemos que este
     */
    public Pokemon(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.movimientos = new String[4];
        this.next = 0;
    }

    /**
     * Devuelve el nombre del pokemon
     *
     * @return El nombre del pokemon
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el nivel del pokemon
     *
     * @return El nivel del pokemon
     */
    public int getNivel() {
        return this.nivel;
    }

    /**
     * Devuelve el tipo (principal) del pokemon
     *
     * @return El tipo del pokemon
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Devuelve los movimientos que conoce actualmente el pokemon
     *
     * @return Los movimientos que conoce actualmente el pokemon
     */
    public String[] getMovimientos() {
        return movimientos;
    }

    /**
     * Devuelve el nombre del primer entrenador que tuvo
     *
     * @return El nombre del primer entrenador que tuvo
     */
    public String getEntrenadorOriginal() {
        return this.entrenadorOriginal;
    }

    /**
     * Devuelve la habilidad principal del pokemon
     *
     * @return La habilidad principal del pokemon
     */
    public String getHabilidad() {
        return this.habilidad;
    }

    /**
     * Establecer el nombre del entrenador original
     *
     * @param entrenadorOriginal El entrenador del cual deseamos establecer el nombre
     */
    public void setEntrenadorOriginal(Entrenador entrenadorOriginal) {
        this.entrenadorOriginal = entrenadorOriginal.getNombre();
    }

    /**
     * Establece el tipo (principal) del pokemon
     *
     * @param tipo El tipo del cual deseamos establecerlo
     */
    protected void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Establece la habilidad principal del pokemon
     *
     * @param habilidad La habilidad la cual deseamos establecer
     */
    protected void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    /**
     * Anade un movimiento a la lista de movimientos
     *
     * @param movimiento El movimiento que deseamos anadir
     */
    protected void setMovimientos(String movimiento) {
        try {
            this.movimientos[next] = movimiento;
            this.next++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Numero maximo de movimientos alcanzado");
        }
    }

    @Override
    public String toString() {
        String string = "Nombre: " + this.nombre + '\n';
        string.concat("Entrenador: " + getEntrenadorOriginal() + "\n");

        return string;
    }

}
