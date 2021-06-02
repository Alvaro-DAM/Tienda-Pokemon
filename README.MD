# Tienda pokemon
Este programa simula (no de manera exacta) el funcionamiento de una tienda pokemon.

Podra comprar objetos (como pociones, pokeballs...) y venderlos, asi como comprar Pokemons.

Ademas, tambien podra realizar varias acciones con los objetos que vaya obteniendo, como comprobar sus detalles o tirar alguno si es necesario.

Con los pokemons que obtenga, podra comprobar sus *stats*.

## Instrucciones para su ejecuccion
Tanto si se usa ***Intelli J*** como ***Eclipse***, simplemente clone el repositorio y ejecutelo

Si desea hacerlo desde la linea de comandos puede seguir los siguientes pasos:
- Primero habrá que localizar los ficheros **log4j-core-2.14.1.jar** y **log4j-api-2.14.1.jar** e
incorporarlos en el CLASSPATH. En este caso se encuentran en la carpeta **lib**.
- Para compilarlos se usa el comando: **javac –d** (Donde queremos guardar las clases compiladas) **-cp lib/log4j-core-2.14.1.jar:lib/log4j-api-2.14.1.jar** (Sin parentesis, los ficheros que deseamos compilar)
-Para ejecutarlo: ** java -cp .:lib/log4j-core-2.14.1.jar:lib/log4j-api-2.14.1.jar TiendaPokemonMain**
