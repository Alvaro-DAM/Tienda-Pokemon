package es.alvaroCDN1.tiendaPokemon;

import es.alvaroCDN1.tiendaPokemon.articulos.Medicina;
import es.alvaroCDN1.tiendaPokemon.articulos.Pocion;
import es.alvaroCDN1.tiendaPokemon.articulos.PokeBall;
import es.alvaroCDN1.tiendaPokemon.excepciones.CantindadInvalidadException;
import es.alvaroCDN1.tiendaPokemon.excepciones.DineroInsuficienteException;
import es.alvaroCDN1.tiendaPokemon.excepciones.NoExisteArticuloException;
import es.alvaroCDN1.tiendaPokemon.pokemon.Charmander;
import es.alvaroCDN1.tiendaPokemon.pokemon.Pokemon;
import es.alvaroCDN1.tiendaPokemon.articulos.Articulo;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que simula una tienda pokemon inspiradas en los juegos
 *
 * @author Alvaro
 * @version 1.0
 */
public class TiendaPokemon {
    private HashMap<Articulo, Integer> stock; // Articulo = articulo a guardar, Integer = cantidad del articulo
    private HashMap<Pokemon, Integer> stockPokemon; // Pokemon = Pokemon a guardar, Interger = precio

    private Entrenador entrenadorEnLaTienda;

    /**
     * Constructor de la clase 'Tienda Pokemon'
     */
    public TiendaPokemon() {
        this.stock = new HashMap<>();
        this.stockPokemon = new HashMap<>();
    }

    /**
     * Metodo que inicia el funcionamiento de la 'Tienda Pokemon'
     *
     * @param entrenador El entrenador que "entra" en la tienda
     */
    public void start(Entrenador entrenador) {
        this.entrenadorEnLaTienda = entrenador;

        rellanarStock();

        int opcion = -1;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Bienvenido/a " + entrenadorEnLaTienda.getNombre() + ". Elija una opcion\n");

            imprimirMenu();

            try {
                opcion = sc.nextInt();
                opcion(opcion);
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduzca una opcion valida (0 a 5).\n");
                sc.next();
            }

        } while (opcion != 0);
    }

    /**
     * Anade un articulo al stock en caso de que no se encuentre en el.
     * Si se encuentra en el, simplemente se incrementara el numero en +1
     *
     * @param articulo El articulo el cual vamos a anadir o incrementar
     */
    public void anadirArticulo(Articulo articulo, int cantidad) {
        if (!this.stock.containsKey(articulo)) {
            this.stock.put(articulo, 1);
        } else {
            this.stock.put(articulo, (this.stock.get(articulo) + cantidad));
        }
    }

    /**
     * Obtiene la cantidad de "stock" que hay de un articulo
     *
     * @param articulo El articulo del cual queremos saber el "stock"
     * @return El "stock" del articulo
     */
    public Integer getStockArticulo(Articulo articulo) {
        return this.stock.get(articulo);
    }

    /**
     * Obtiene un articulo del stock
     *
     * @param nombreArticulo El nombre del articulo que queremos obtener
     * @return El articulo que buscamos
     * @throws NoExisteArticuloException Si el articulo no existe
     */
    private Articulo obtenerArticulo(String nombreArticulo) throws NoExisteArticuloException {
        Articulo articulo1 = null;

        for (Articulo articulo : this.stock.keySet()) {
            if (articulo.getNombre().equalsIgnoreCase(nombreArticulo)) {
                articulo1 = articulo;
            }
        }

        if (articulo1 == null) {
            throw new NoExisteArticuloException();
        }

        return articulo1;
    }

    /**
     * Comprueba si la cantidad de articulos que se pide es valida
     *
     * @param articulo El articulo del cual queremos comprobar si la cantidad es correta
     * @param cantidad La cantidad a comprobar
     * @return <code>true</code> si la cantidad es correcta y <code>false</code> en caso contrario
     * @throws CantindadInvalidadException
     */
    private boolean comprobarCantidad(Articulo articulo, int cantidad) throws CantindadInvalidadException {
        boolean cantidadCorrecta = false;

        int stockArticulo = this.stock.get(articulo);

        if (cantidad > stockArticulo || cantidad <= 0) {
            throw new CantindadInvalidadException();
        } else {
            cantidadCorrecta = true;
        }

        return cantidadCorrecta;
    }

    /**
     * Restamos el dinero del precio al dinero que el entrenador tiene en su monedero
     *
     * @param precio El precio total del articulo
     * @return <code>true</code> si se ha realizado el pago con exito y <code>false</code> en caso contrario
     * @throws DineroInsuficienteException si el pago no se ha realizado
     */
    private boolean restarDinero(int precio) throws DineroInsuficienteException {
        boolean pagado = false;

        if (entrenadorEnLaTienda.getMonedero() > precio) {
            entrenadorEnLaTienda.setMonedero(precio - entrenadorEnLaTienda.getMonedero());
            pagado = true;
        } else {
            throw new DineroInsuficienteException();
        }

        return pagado;
    }

    /**
     * Metodo para ejecutar la opcion seleccionada por el usuario
     *
     * @param opcion La opcion que el usuario a elegido
     */
    private void opcion(int opcion) {
        switch (opcion) {
            case 0:
                break;

            case 1:
                menuComprar();
                break;

            case 2:
                break;

            case 3:
                menuVender();
                break;

            case 4:
                break;

            case 5:
                break;

            default:
                System.out.println("Opcion no existente. Introduzca una opcion del 0 al 5\n");
                break;
        }
    }

    /**
     * Metodo para generar el menu de "Comprar" y sus funciones
     */
    private void menuComprar() {
        boolean finalCompra;

        do {
            finalCompra = false;

            int cantidad = 0;

            Scanner sc = new Scanner(System.in);

            String input = null;

            Articulo articulo = null;

            System.out.println("\n¿Que desea comprar? (Pokedolares: " + entrenadorEnLaTienda.getMonedero() + ')');

            boolean articuloCorrecto = false;

            while (!articuloCorrecto) {
                imprimirStock();
                System.out.println("(Nada o salir)");

                try {
                    input = sc.nextLine();

                    if (input.equalsIgnoreCase("salir") || input.equalsIgnoreCase("nada")) {
                        finalCompra = true;

                        break;
                    } else {
                        articulo = obtenerArticulo(input);
                    }

                    articuloCorrecto = true;
                } catch (NoExisteArticuloException ex) {
                    System.out.println("El articulo no existe.\nSeleccione un articulo.\n");
                }
            }

            if (articulo != null && this.stock.get(articulo) > 0) {
                System.out.println(articulo.getNombre() + " perfecto. ¿Cuantos deseas?");

                boolean cantidadCorrecta = false;

                while (!cantidadCorrecta) {
                    try {
                        cantidad = sc.nextInt();
                        if (comprobarCantidad(articulo, cantidad)) {
                            cantidadCorrecta = true;

                            if (!entrenadorEnLaTienda.mochilaLlena()) {
                                if (entrenadorEnLaTienda.anadirObjeto(articulo, cantidad)) {
                                    if (restarDinero(articulo.getPrecio() * cantidad)) {
                                        System.out.println("Muchas gracias. En total seran " +
                                                (articulo.getPrecio() * cantidad) + " pokes.");

                                        this.stock.put(articulo, getStockArticulo(articulo) - cantidad);

                                        System.out.println("¿Desea comprar algo mas? (Si o No)");
                                        input = sc.next();

                                        if (input.equalsIgnoreCase("No")) {
                                            finalCompra = true;
                                        }
                                    }
                                }
                            } else {
                                System.out.println("Oh vaya, tienes la mochila llena.");
                            }
                        }
                    } catch (CantindadInvalidadException e) {
                        System.out.println("La cantidad introducida no es valida. Introduce una cantidad validad");
                    } catch (InputMismatchException ex) {
                        System.out.println("Por favor introduzca un numero.");
                        sc.next();
                    } catch (DineroInsuficienteException exc) {
                        System.out.println("Oh vaya, parece que no tienes dinero suficiente");
                    }
                }
            } else if (articulo != null && this.stock.get(articulo) <= 0) {
                System.out.println("El articulo esta agotado");
            }

        } while (!finalCompra);
    }

    private void menuVender() {
        boolean finalVenta;

        do {
            finalVenta = false;

            Scanner sc = new Scanner(System.in);

            String input = "";

            Articulo objeto = null;

            int cantidad = 0;

            System.out.println("¿Que objeto desea vender?\n");

            if (entrenadorEnLaTienda.listarObjetos().isBlank()) {
                System.out.println("Todavia no hay objetos\n");

                finalVenta = true;

                break;
            } else {
                System.out.println(entrenadorEnLaTienda.listarObjetos());
            }

            boolean objetoValido = false;

            while (!objetoValido) {
                input = sc.nextLine();

                if (input.equalsIgnoreCase("salir") || input.equalsIgnoreCase("nada")) {
                    finalVenta = true;

                    break;
                }

                try {
                    objeto = entrenadorEnLaTienda.obtenerObjeto(input);

                    objetoValido = true;

                    System.out.println(objeto.getNombre() + " perfecto. ¿Cuantas unidades deseas vender?");

                } catch (NoExisteArticuloException e) {
                    System.out.println("El articulo no existe.\nSeleccione un articulo valido.\n");
                }
            }

            if (objeto != null) {
                boolean cantidadCorrecta = false;

                while (!cantidadCorrecta) {
                    try {
                        cantidad = sc.nextInt();

                        if (entrenadorEnLaTienda.comprobarCantidad(objeto, cantidad)) {
                            cantidadCorrecta = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor introduzca un numero");
                        sc.next();
                    } catch (CantindadInvalidadException ex) {
                        System.out.println("La cantidad introducida no es valida. Introduce una cantidad validad");
                    }
                }

                int precioVenta = (cantidad * objeto.getPrecio()) - (int) (cantidad * objeto.getPrecio() * 0.10);

                System.out.println("Puedo pagarte " + precioVenta + ". ¿Te parece bien?");
                input = sc.next();

                if (input.equalsIgnoreCase("si")) {
                    entrenadorEnLaTienda.retirarObjeto(objeto, cantidad);
                    entrenadorEnLaTienda.setMonedero(precioVenta);
                }

                System.out.println("¿Desea vender algo mas?");
                input = sc.next();

                if (input.equalsIgnoreCase("no")) {
                    finalVenta = true;
                }
            }

        } while (!finalVenta);
    }

    /**
     * Imprime el menu general de la tienda
     */
    private void imprimirMenu() {
        System.out.println("1. Comprar");
        System.out.println("2. Comprar pokemon");
        System.out.println("3. Vender");
        System.out.println("4. Comprobar mochila");
        System.out.println("5. Comprobar equipo pokemon");
        System.out.println("0. Salir\n");
    }

    /**
     * Imprime el nombre y la cantidad de cada articulo que hay en 'stock'
     */
    private void imprimirStock() {
        int i = 1;

        for (Articulo articulo : this.stock.keySet()) {
            System.out.print(i + ". " + articulo.getNombre());

            if (getStockArticulo(articulo) == 0) {
                System.out.print(" (AGOTADO)\n");
            } else {
                System.out.print(". En Stock: " + getStockArticulo(articulo) + '\n');
            }

            i++;
        }

        System.out.println();
    }

    /**
     * Metodo que introduce un 'stock' preparado para la simulacion del programa
     */
    private void rellanarStock() {
        Medicina revivir = new Medicina("Revivir", "Revive a un pokemon debilitado.",
                1500, "Debilitado");

        Pocion superPocion = new Pocion("Super Pocion", 700, 60);

        PokeBall lujoBall = new PokeBall("Lujo Ball", "Una pokeball para una ocasion especial.",
                1000, 1);

        Charmander charmander = new Charmander();

        this.stock.put(revivir, 10);
        this.stock.put(superPocion, 15);
        this.stock.put(lujoBall, 40);

        this.stockPokemon.put(charmander, 1000);
    }
}
