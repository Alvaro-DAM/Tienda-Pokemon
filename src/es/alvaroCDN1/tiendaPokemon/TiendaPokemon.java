package es.alvaroCDN1.tiendaPokemon;

import es.alvaroCDN1.tiendaPokemon.articulos.Medicina;
import es.alvaroCDN1.tiendaPokemon.articulos.Pocion;
import es.alvaroCDN1.tiendaPokemon.articulos.PokeBall;
import es.alvaroCDN1.tiendaPokemon.excepciones.CantindadInvalidadException;
import es.alvaroCDN1.tiendaPokemon.excepciones.DineroInsuficienteException;
import es.alvaroCDN1.tiendaPokemon.excepciones.NoExisteArticuloException;
import es.alvaroCDN1.tiendaPokemon.excepciones.NoExistePokemonException;
import es.alvaroCDN1.tiendaPokemon.pokemon.Charmander;
import es.alvaroCDN1.tiendaPokemon.pokemon.Litten;
import es.alvaroCDN1.tiendaPokemon.pokemon.Pokemon;
import es.alvaroCDN1.tiendaPokemon.articulos.Articulo;
import es.alvaroCDN1.tiendaPokemon.pokemon.Totodile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger logger = LogManager.getLogger(TiendaPokemon.class.getName());

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

        logger.info("El usuario ha accedido al menu principal.");

        rellenarStock();

        int opcion = -1;
        Scanner sc = new Scanner(System.in);

        do {
            logger.info("El usuario esta en el menu principal.");
            logger.info("Esperando a que elija una opcion.");

            System.out.println("Bienvenido/a " + entrenadorEnLaTienda.getNombre() + ". Elija una opcion:");
            System.out.println("(Introduzca un numero del 0 al 5)\n");

            imprimirMenu();

            try {
                opcion = sc.nextInt();

                logger.info("Ha seleccionado la opcion: " + opcion);
                opcion(opcion);
            } catch (InputMismatchException e) {
                logger.error("El usuario no ha introducido un numero.");

                System.out.println("Por favor, introduzca una opcion valida (0 a 5).\n");
                sc.next();
            }

        } while (opcion != 0);

        logger.info("Saliendo del menu principal.");
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
        Articulo articuloADevolver = null;

        for (Articulo articulo : this.stock.keySet()) {
            if (articulo.getNombre().equalsIgnoreCase(nombreArticulo)) {
                articuloADevolver = articulo;
            }
        }

        if (articuloADevolver == null) {
            throw new NoExisteArticuloException();
        }

        return articuloADevolver;
    }

    /**
     * Obtiene un pokemon del 'stock' de pokemons
     *
     * @param nombrePokemon El nombre del pokemon que deseamos obtener
     * @return El pokemon que buscamos
     * @throws NoExistePokemonException En caso de que el pokemon que buscamos no exista o no este en 'stock'
     */
    private Pokemon obtenerPokemon(String nombrePokemon) throws NoExistePokemonException {
        Pokemon pokemonADevolver = null;

        for (Pokemon pokemon : this.stockPokemon.keySet()) {
            if (pokemon.getNombre().equalsIgnoreCase(nombrePokemon)) {
                pokemonADevolver = pokemon;
            }
        }

        if (pokemonADevolver == null) {
            throw new NoExistePokemonException();
        }

        return pokemonADevolver;
    }

    /**
     * Comprueba si la cantidad de articulos que se pide es valida
     *
     * @param articulo El articulo del cual queremos comprobar si la cantidad es correta
     * @param cantidad La cantidad a comprobar
     * @return <code>true</code> si la cantidad es correcta y <code>false</code> en caso contrario
     * @throws CantindadInvalidadException Si la cantidad introducida no es valida
     */
    private boolean comprobarCantidad(Articulo articulo, int cantidad) throws CantindadInvalidadException {
        boolean cantidadCorrecta;

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
        boolean pagado;

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
                logger.info("Accediendo al menu de compra de articulos.");

                menuComprar();
                break;

            case 2:
                logger.info("Accediendo al menu de compra de pokemons.");

                menuComprarPokemon();
                break;

            case 3:
                logger.info("Accediendo al menu de venta de articulos.");

                menuVender();
                break;

            case 4:
                logger.info("Accediendo al menu de la mochila.");

                entrenadorEnLaTienda.revisarMochila();
                break;

            case 5:
                logger.info("Accediendo al menu para revisar el equipo pokemon.");

                entrenadorEnLaTienda.revisarEquipoPokemon();
                break;

            default:
                logger.warn("El usuario ha introducido una opcion no valida.");

                System.out.println("Opcion no existente. Introduzca una opcion del 0 al 5\n");
                break;
        }
    }

    /**
     * Imprime el stock de los articulo y permite al usuario comprar articulos en 'stock'
     */
    private void menuComprar() {
        boolean finalCompra;

        do {
            finalCompra = false;

            int cantidad;

            Scanner sc = new Scanner(System.in);

            String input;

            Articulo articulo = null;

            boolean articuloCorrecto = false;

            while (!articuloCorrecto) {
                logger.info("Esperando que el usuario seleccione un articulo.");

                System.out.println("¿Que desea comprar? (Pokedolares: " + entrenadorEnLaTienda.getMonedero() + ")\n");
                imprimirStock();

                System.out.println("(Si desea obtener ayuda escriba 'ayuda')");

                try {
                    input = sc.nextLine();

                    if (input.equalsIgnoreCase("salir") || input.equalsIgnoreCase("nada")) {
                        logger.info("El usuario ha introducido" + input);

                        finalCompra = true;

                        break;
                    } else if (input.equalsIgnoreCase("ayuda")) {
                        logger.info("Se ha solicitado ayuda. Imprimiendo ayuda.");

                        imprimirAyudaCompraVenta();
                    } else {
                        logger.info("Se ha seleccionado el articulo: " + input + '.');

                        articulo = obtenerArticulo(input);
                    }

                    articuloCorrecto = true;
                } catch (NoExisteArticuloException ex) {
                    logger.error("El usuario ha seleccionado un articulo invalido.");

                    System.out.println("El articulo no existe.\nSeleccione un articulo.\n");
                }
            }

            if (articulo != null && this.stock.get(articulo) > 0) {
                System.out.println(articulo.getNombre() + " perfecto. ¿Cuantos deseas?");

                boolean cantidadCorrecta = false;

                while (!cantidadCorrecta) {
                    try {
                        logger.info("Esperando a que introduzca una cantidad.");

                        cantidad = sc.nextInt();
                        if (comprobarCantidad(articulo, cantidad)) {
                            logger.info("El usuario ha pedido " + cantidad + ' ' + articulo.getNombre());

                            cantidadCorrecta = true;

                            if (!entrenadorEnLaTienda.mochilaLlena()) {
                                if (entrenadorEnLaTienda.anadirObjeto(articulo, cantidad)) {
                                    if (restarDinero(articulo.getPrecio() * cantidad)) {
                                        logger.info("Se ha añadido el articulo a la mochila.");

                                        System.out.println("Muchas gracias. En total seran " +
                                                (articulo.getPrecio() * cantidad) + " pokes.");

                                        logger.info("Se ha descontado " + (articulo.getPrecio() * cantidad)
                                                + " del monedero del usuario");

                                        this.stock.put(articulo, getStockArticulo(articulo) - cantidad);

                                        System.out.println("¿Desea comprar algo mas? (Si o No)");
                                        input = sc.next();

                                        if (input.equalsIgnoreCase("No")) {
                                            logger.info("El usuario no desea comprar mas.");

                                            finalCompra = true;
                                        } else {
                                            logger.info("El usuario sigue comprando.");
                                        }
                                    }
                                }
                            } else {
                                logger.warn("No se ha añadido el articulo. La mochila esta llena.");

                                System.out.println("Oh vaya, tienes la mochila llena.");
                            }
                        }
                    } catch (CantindadInvalidadException e) {
                        logger.error("El usuario ha introducido una cantidad incorrecta.");

                        System.out.println("La cantidad introducida no es valida. Introduce una cantidad validad");
                    } catch (InputMismatchException ex) {
                        logger.error("El usuario no ha introducido un numero.");

                        System.out.println("Por favor introduzca un numero.");
                        sc.next();
                    } catch (DineroInsuficienteException exc) {
                        logger.warn("El usuario no tiene suficiente dinero");

                        System.out.println("Oh vaya, parece que no tienes dinero suficiente");
                    }
                }
            } else if (articulo != null && this.stock.get(articulo) <= 0) {
                logger.warn("El articulo seleccionado esta agotado.");

                System.out.println("El articulo esta agotado");
            }

        } while (!finalCompra);

        logger.info("Saliendo del menu de compra de articulos.");
    }

    /**
     * Imprime la lista de pokemons en 'stock' y permite comprarlos
     * (Solo se permite 1 pokemon de cada por cliente)
     */
    private void menuComprarPokemon() {
        boolean finalCompra;

        do {
            finalCompra = false;

            Scanner sc = new Scanner(System.in);

            String input;

            Pokemon pokemon = null;

            if (this.stockPokemon.keySet().size() <= 0) {
                logger.warn("No quedan pokemons que comprar.");

                System.out.println("Lo siento, no nos quedan mas pokemon. Vuelva otro dia.\n");

                break;
            } else {
                boolean pokemonValido = false;

                while (!pokemonValido) {
                    logger.info("Esperando a que seleccione un pokemon.");

                    System.out.println("¿Que pokemon desea comprar?\n");
                    imprimirStockPokemon();

                    System.out.println("(Si desea obtener ayuda escriba 'ayuda')");

                    input = sc.nextLine();

                    if (input.equalsIgnoreCase("salir") || input.equalsIgnoreCase("nada")) {
                        logger.info("El usuario ha introducido" + input);

                        finalCompra = true;

                        break;
                    } else if (input.equalsIgnoreCase("ayuda")) {
                        logger.info("Se ha solicitado ayuda. Imprimiendo ayuda.");

                        imprimirAyudaCompraVenta();
                    } else {
                        logger.info("Se ha seleccionado el pokemon: " + input);

                        try {
                            pokemon = obtenerPokemon(input);

                            pokemonValido = true;
                        } catch (NoExistePokemonException e) {
                            logger.error("El pokemon seleccionado no existe.");

                            System.out.println("No existe ese pokemon.\n");
                        }
                    }
                }

                if (pokemon != null) {
                    try {
                        if (restarDinero(this.stockPokemon.get(pokemon))) {
                            if (entrenadorEnLaTienda.anadirPokemon(pokemon)) {
                                logger.info("Se ha retirado " + this.stockPokemon.get(pokemon) +
                                        " del monedero del usuario.");

                                logger.info("Se ha añadido el pokemon " + pokemon.getNombre() +
                                        "al equipo pokemon del usuario.");

                                pokemon.setEntrenadorOriginal(this.entrenadorEnLaTienda);

                                System.out.println("Muchas gracias. En total seran " + this.stockPokemon.get(pokemon) +
                                        " pokes.\n");

                                logger.info("Se va a retirar 1 unidad del pokemon: " + pokemon.getNombre());
                                this.stockPokemon.remove(pokemon);

                                System.out.println("¿Desea comprar algo mas? (Si o no)");

                                input = sc.nextLine();

                                if (input.equalsIgnoreCase("no")) {
                                    logger.info("El usuario no desea comprar mas.");

                                    finalCompra = true;
                                } else {
                                    logger.info("El usuario va a comprando.");
                                }
                            } else {
                                System.out.println("Vaya, parece que tu equipo esta completo.");
                            }
                        }
                    } catch (DineroInsuficienteException e) {
                        logger.warn("El usuario no tiene suficiente dinero.");

                        System.out.println("Vaya, parece que no tienes dinero suficiente");
                    }
                }
            }

        } while (!finalCompra);

        logger.info("Saliendo del menu de compra de pokemon.");
    }

    /**
     * Imprime la lista de objetos del entrenador y permite venderlos
     */
    private void menuVender() {
        boolean finalVenta;

        do {
            logger.info("Esperando a que el usuario seleccione un objeto de su mochila");

            finalVenta = false;

            Scanner sc = new Scanner(System.in);

            String input;

            Articulo objeto = null;

            int cantidad = 0;

            System.out.println("¿Que objeto desea vender?\n");

            if (entrenadorEnLaTienda.listarObjetos().isBlank()) {
                logger.warn("La mochila esya vacia.");

                System.out.println("Todavia no hay objetos\n");

                break;
            } else {
                System.out.println(entrenadorEnLaTienda.listarObjetos());
                System.out.println("(Si desea obtener ayuda escriba 'ayuda')");
            }

            boolean objetoValido = false;

            while (!objetoValido) {
                input = sc.nextLine();

                logger.info("El usuario ha seleccionado el objeto: " + input);

                if (input.equalsIgnoreCase("salir") || input.equalsIgnoreCase("nada")) {
                    finalVenta = true;

                    break;
                } else if (input.equalsIgnoreCase("ayuda")) {
                    imprimirAyudaCompraVenta();

                    break;
                } else {
                    try {
                        objeto = entrenadorEnLaTienda.obtenerObjeto(input);

                        objetoValido = true;

                        System.out.println(objeto.getNombre() + " perfecto. ¿Cuantas unidades deseas vender?");

                    } catch (NoExisteArticuloException e) {
                        logger.error("El objeto por el usuario seleccionado no existe.");

                        System.out.println("El articulo no existe.\nSeleccione un articulo valido.\n");
                    }
                }
            }

            if (objeto != null) {
                boolean cantidadCorrecta = false;

                while (!cantidadCorrecta) {
                    try {
                        cantidad = sc.nextInt();

                        logger.info("El usuario ha introducido la cantidad: " + cantidad);

                        if (entrenadorEnLaTienda.comprobarCantidad(objeto, cantidad)) {
                            cantidadCorrecta = true;
                        }
                    } catch (InputMismatchException e) {
                        logger.error("El usuario no ha introducido un numero.");

                        System.out.println("Por favor introduzca un numero");
                        sc.next();
                    } catch (CantindadInvalidadException ex) {
                        logger.warn("El usuario ha introducido un cantidad invalida.");

                        System.out.println("La cantidad introducida no es valida. Introduce una cantidad validad");
                    }
                }

                int precioVenta = (cantidad * objeto.getPrecio()) - (int) (cantidad * objeto.getPrecio() * 0.10);

                System.out.println("Puedo pagarte " + precioVenta + ". ¿Te parece bien? (Si o no)");

                logger.info("Se le ofrece al usuario " + precioVenta + "por sus objetos.");

                input = sc.next();

                if (input.equalsIgnoreCase("si")) {
                    logger.info("El usuario ha aceptado la cantidad ofrecida.");

                    entrenadorEnLaTienda.retirarObjeto(objeto, cantidad);
                    entrenadorEnLaTienda.setMonedero(precioVenta);

                    anadirArticulo(objeto, cantidad);

                    logger.info("Se ha añadido x" + cantidad + "de \"" + objeto.getNombre() +
                            "\" al stock de la tienda.");
                } else {
                    logger.info("El usuario no ha aceptado la cantidad ofrecida.");
                }

                System.out.println("¿Desea vender algo mas?");
                input = sc.next();

                if (input.equalsIgnoreCase("no")) {
                    logger.info("El usuario no desea comprar mas.");

                    finalVenta = true;
                } else {
                    logger.info("El usuario va a seguir vendiendo.");
                }
            }

        } while (!finalVenta);

        logger.info("Saliendo del menu de venta.");
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

        for (Articulo articulo : this.stock.keySet()) {
            System.out.print(articulo.getNombre());

            if (getStockArticulo(articulo) == 0) {
                System.out.print(" (AGOTADO)\n");
            } else {
                System.out.print(". En Stock: " + getStockArticulo(articulo) + '\n');
            }
        }

        System.out.println();
    }

    /**
     * Imprime el 'stock' actual de pokemon
     */
    private void imprimirStockPokemon() {

        for (Pokemon pokemon : this.stockPokemon.keySet()) {
            System.out.println(pokemon.getNombre() + " lvl." + pokemon.getNivel() +
                    " - Precio: " + this.stockPokemon.get(pokemon));
        }

        System.out.println();
    }

    /**
     * Imprime instrucciones sobre como introducir datos para comprar/vender un articulo/objeto
     */
    private void imprimirAyudaCompraVenta() {
        System.out.println("Para seleccionar un articulo/pokemon escriba su nombre.");
        System.out.println("Si el articulo/pokemon existe y no esta agotado (o dispone de suficientes cantidades" +
                " en su mochila en caso de vender), debera introducir una cantidad. ");
        System.out.println("Dicha cantidad debe ser mayor que 0 e igual o menor que la cantidad de stock" +
                " disponible.\n");
        System.out.println("\tSi esta comprando, el dinero se descontara automaticamente de su monedero.");
        System.out.println("\tSi esta vendiendo, se le informara de cuanto dinero se le pagara y usted podra aceptar" +
                " o rechazar (con un 'si' o 'no') la cantidad ofrecida.\n");
        System.out.println("Si desea salir, escriba 'nada' o 'salir'.");
        System.out.println();
    }

    /**
     * Metodo que introduce un 'stock' preparado para la simulacion del programa
     */
    private void rellenarStock() {
        Medicina revivir = new Medicina("Revivir", "Revive a un pokemon debilitado.",
                1500, "Debilitado");

        Pocion superPocion = new Pocion("Super Pocion", 700, 60);
        superPocion.setDescripcion("Restaura 50 puntos de salud de la barra de vida de un Pokemon.");

        PokeBall lujoBall = new PokeBall("Lujo Ball", "Una pokeball para una ocasion especial.",
                1000, 1);

        Charmander charmander = new Charmander(5);
        Litten litten = new Litten(4);
        Totodile totodile = new Totodile(10);
        totodile.setMovimientos("Furia");

        Totodile totodile2 = new Totodile(4);

        this.stock.put(revivir, 10);
        this.stock.put(superPocion, 15);
        this.stock.put(lujoBall, 40);

        this.stockPokemon.put(charmander, 1000);
        this.stockPokemon.put(litten, 2000);
        this.stockPokemon.put(totodile, 3000);
        this.stockPokemon.put(totodile2, 650);

        logger.info("Se ha rellenado el stock.");
    }
}
