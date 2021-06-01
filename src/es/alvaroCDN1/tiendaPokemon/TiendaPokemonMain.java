package es.alvaroCDN1.tiendaPokemon;

import java.util.Scanner;

/**
 * Clase principal
 */
public class TiendaPokemonMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombreEntrenador;

        System.out.println("Bienvenido a la tienda pokemon. ¿Como se llama?");

        do {
            nombreEntrenador = sc.nextLine();

            if (nombreEntrenador.isBlank()) {
                System.out.println("Por favor, debe introducir un nombre.\n");
            }

        } while (nombreEntrenador.isBlank());

        new TiendaPokemon().start(new Entrenador(nombreEntrenador));

        sc.close();
    }
}
