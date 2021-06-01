package es.alvaroCDN1.tiendaPokemon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Scanner;

/**
 * Clase principal
 */
public class TiendaPokemonMain {

    private static final Logger logger = LogManager.getLogger(TiendaPokemonMain.class);

    public static void main(String[] args) {

        File directorio = new File("log\\");

        if (!directorio.exists()) {
            directorio.mkdir();
        }

        logger.info("## INCIANDO EL PROGRAMA ##\n");

        Scanner sc = new Scanner(System.in);
        String nombreEntrenador;

        System.out.println("Bienvenido a la tienda pokemon. ¿Como se llama?");

        do {
            nombreEntrenador = sc.nextLine();

            if (nombreEntrenador.isBlank()) {
                System.out.println("Por favor, debe introducir un nombre.\n");
                logger.warn("El usuario no ha introducido un nombre.");
            }

        } while (nombreEntrenador.isBlank());

        logger.info("El usuario va a utilizar el nombre: " + nombreEntrenador);

        new TiendaPokemon().start(new Entrenador(nombreEntrenador));

        sc.close();

        logger.info("## CERRANDO EL PROGRAMA ##\n");
    }
}
