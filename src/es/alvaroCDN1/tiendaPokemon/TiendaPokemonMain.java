package es.alvaroCDN1.tiendaPokemon;

import es.alvaroCDN1.tiendaPokemon.articulos.Articulo;

import java.util.HashMap;
import java.util.Scanner;

public class TiendaPokemonMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombreEntrenador = "";

        do {
            System.out.println("Bienvenido a la tienda pokemon. ¿Como se llama?");
            nombreEntrenador = sc.nextLine();
        } while (nombreEntrenador.isBlank());

        new TiendaPokemon().start(new Entrenador(nombreEntrenador));

        sc.close();
    }
}
