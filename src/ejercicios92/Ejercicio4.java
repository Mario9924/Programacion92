package ejercicios92;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * Se pretende simular el juego Saber y Ganar, el programa consta de 3 rondas
 * Ronda 1 - En 1 minuto se han de contestar tantas preguntas como se pueda
 * Ronda 2 - Se han de contestar 6 preguntas en total o al menos, dos más que el cazador
 * Ronda 3 - En 2 minutos el cazador y el concursante han de contestar unas preguntas, aquel que conteste más preguntas, ganará
 * 
 * Cada ronda tiene una serie de ficheros llenos de preguntas para su uso
 * 
 * @author Mario Gutiérrez
 * @see https://classroom.google.com/c/ODA3NDY5OTY5MTcz/a/ODYyOTYzMjczMzkw/details
 * @version 1.0
 */
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Declaración de variables
        Scanner reader = new Scanner(System.in);
        String respuestaIn = "";
        int contadorAciertosConcursante = 0;
        int contadorAciertosCazador = 0;
        int dineros = 0;
        int opcionIn = 0;
        int contadorPreguntas = 0; // Se usará en la segunda ronda para contabilizar las 6 preguntas que ha de contestar el concursante
        long tiempoInicio = System.currentTimeMillis();
        long tiempoFin = tiempoInicio + 60000;
        
        /*
            Se declaran e instancian las distintas rondas del programa
        */
        
        HashMap<String, String> ronda1 = new HashMap<>();
        File ficheroRonda1 = new File(".\\src\\ficheros\\ejercicio41.txt");
        ronda1 = leerFichero(ficheroRonda1);
        
        File ficheroRonda2 = new File(".\\src\\ficheros\\ejercicio42.txt");
        HashMap<String, String> ronda2 = new HashMap<>();
        
        File ficheroRonda3 = new File(".\\src\\ficheros\\ejercicio43.txt");
        HashMap<String, String> ronda3 = leerFichero(ficheroRonda3);
        

        // Primera ronda - Se contestan tantas preguntas como se puedan en 1 minuto
        System.out.println("Comienza la primera ronda");
        for (String key : ronda1.keySet()) {
            System.out.println(key);
            respuestaIn = reader.nextLine();
            if (respuestaIn.replace(" ", "").equalsIgnoreCase(ronda1.get(key).replace(" ", ""))) {
                contadorAciertosConcursante++;
            } else {
                System.out.println("No es correcto");
            }
            /*
                Tenemos que comprobar que estemos en el minuto permitido, de otro modo se saldrá del programa
            */
            if (System.currentTimeMillis() > tiempoFin) {
                System.out.println("Se acabó el tiempo!!");
                break;
            }
        }
        dineros = contadorAciertosConcursante * 1000;
        System.out.println("Has acertado " + contadorAciertosConcursante + " preguntas y has ganado " + dineros + " dineros");

        contadorAciertosConcursante = 0;

        // Segunda ronda
        /*
            Ahora el concursante se enfrenta al cazador y para vencerle ha de:
            - acertar como mínimo 2 menos que el cazador
        Ejemplo: El cazador acierta 6, entonces el concursante tiene que acertar 4 
         */
        
        //Simulamos que juega el cazador
        for (int i = 0; i < 6; i++) {
            double random = Math.random();
            if (random < 0.50) {
                contadorAciertosCazador++;
            }
        }

        System.out.println("El cazador ha acertado: " + contadorAciertosCazador 
                + "\nTienes que acertar al menos " + (contadorAciertosCazador - 2) + " preguntas");        
       
        // Cargamos en el HashMap correspondiente las preguntas y respuestas de la segunda ronda
        ronda2 = leerFicheroMultiple(ficheroRonda2);

        for (String key : ronda2.keySet()) {
            if (contadorPreguntas == 6) {
                System.out.println("Se terminó la segunda ronda");
                break;
            } else {
                System.out.println(key);
                /*
                    Este fichero tiene la siguiente estructura:
                    Pregunta|respuesta1;respuesta2;respuesta3
                    De modo que al acceder a la Key correspondiente obtenemos un string con "resp1;resp2;resp3"
                */
                String[] respuestas = ronda2.get(key).split(";");
                do {
                    System.out.println("Elige una respuesta: ");
                    for (int i = 0; i < respuestas.length; i++) {
                        System.out.println((i + 1) + " - " + respuestas[i]);
                    }
                    opcionIn = reader.nextInt();
                } while (opcionIn <= 0 || opcionIn > 3);
                opcionIn--;
                if (opcionIn == 0) {
                    System.out.println("HAS ACERTADO!!");
                    contadorAciertosConcursante++;
                } else {
                    System.out.println("INCORRECTO!");
                }
                contadorPreguntas++;
            }
        }

        System.out.println("\nACIERTOS CAZADOR: " + contadorAciertosCazador 
                + "\nACIERTOS JUGADOR: " + contadorAciertosConcursante + "\n");

        // Finaliza la segunda ronda
        if (contadorAciertosConcursante < contadorAciertosCazador) {
            System.out.println("Que mala pata, has perdido el juego y los dineros ganados");
        } else {
            // Continuamos el juego

            System.out.println("Comienza la última ronda. Ahora con preguntas de Dragon Ball");
            // Dejamos los contadores a 0 de nuevo
            contadorAciertosCazador = 0;
            contadorAciertosConcursante = 0;

            tiempoFin = System.currentTimeMillis() + 120000;
            for (String key : ronda3.keySet()) {
                System.out.println(key);
                respuestaIn = reader.nextLine();
                if (respuestaIn.replace(" ", "").equalsIgnoreCase(ronda1.get(key).replace(" ", ""))) {
                    contadorAciertosConcursante++;
                } else {
                    System.out.println("No es correcto");
                }
                if (System.currentTimeMillis() > tiempoFin) {
                    System.out.println("Se acabó el tiempo!!");
                    break;
                }
            }

            //Simulamos que juega el cazador
            for (int i = 0; i < 11; i++) {
                double random = Math.random();
                if (random < 0.50) {
                    contadorAciertosCazador++;
                }
            }

            System.out.println("\nACIERTOS CAZADOR: " + contadorAciertosCazador 
                    + "\nACIERTOS JUGADOR: " + contadorAciertosConcursante + "\n");

            if (contadorAciertosCazador >= contadorAciertosConcursante) {
                System.out.println("Que mala pata, has perdido contra el cazador, devuelve los dineros que te vas para casa");
            } else {
                System.out.println("Que bien!!! Has ganado, te llevas " + dineros + " dineros");
            }

        }
    }

    /**
     * Esta función devuelve la información de un fichero de preguntas cuyo
     * formato sea pregunta;respuesta
     *
     * @param ficheroIn fichero del que vamos a sacar las preguntas
     * @return HashMap<String, String> con el conjunto de preguntas y respuestas
     */
    public static HashMap<String, String> leerFichero(File ficheroIn) {
        HashMap<String, String> preguntas = new HashMap<>();
        try {
            // Volcamos la información del fichero en un hashmap de modo que la información queda así; Key-Pregunta , Value-Respuesta
            Scanner rf = new Scanner(ficheroIn);
            while (rf.hasNext()) {
                String[] informacion = rf.nextLine().split(";");
                preguntas.put(informacion[0], informacion[1]);
            }
            rf.close();
        } catch (FileNotFoundException fnfex) {
            System.err.println("Error al intentar acceder al fichero" + fnfex);
        }
        return preguntas;
    }

    /**
     * Esta función devuelve la información de un fichero de preguntas cuyo
     * formato sea pregunta;respuesta1;respuesta2;respuesta3
     *
     * @param ficheroIn fichero del que vamos a sacar las preguntas
     * @return HashMap<String, String> con el conjunto de preguntas y respuestas
     */
    public static HashMap<String, String> leerFicheroMultiple(File ficheroIn) {
        HashMap<String, String> preguntas = new HashMap<>();
        try {
            // Volcamos la información del fichero en un hashmap de modo que la información queda así; Key-Pregunta , Value-Respuesta
            Scanner rf = new Scanner(ficheroIn);
            while (rf.hasNext()) {
                String[] informacion = rf.nextLine().split("\\|");
                preguntas.put(informacion[0], informacion[1]);
            }
            rf.close();
        } catch (FileNotFoundException fnfex) {
            System.err.println("Error al intentar acceder al fichero" + fnfex);
        }
        return preguntas;
    }
}
