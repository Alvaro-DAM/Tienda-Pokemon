package es.alvaroCDN1.tiendaPokemon.pokemon;

public class Pokemon {
    private String nombre;
    private String tipo;
    private String habilidad;
    private String[] movimientos;
    private String entrenadorOriginal;
    private int nivel;
    private int next; // Primera posicion libre en la lista de movimientos

    public Pokemon(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.movimientos = new String[4];
        this.next = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return this.nivel;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String[] getMovimientos() {
        return movimientos;
    }

    public String getEntrenadorOriginal() {
        return this.entrenadorOriginal;
    }

    public String getHabilidad() {
        return this.habilidad;
    }

    protected void setTipo(String tipo) {
        this.tipo = tipo;
    }

    protected void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    protected void setMovimientos(String movimiento) {
        try {
            this.movimientos[next] = movimiento;
            this.next++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Numero maximo de movimientos alcanzado");
        }
    }

}
