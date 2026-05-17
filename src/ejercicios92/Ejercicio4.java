/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicios92;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Mario_
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
        long tiempoInicio = System.currentTimeMillis();
        long tiempoFin = tiempoInicio + 60000;
        HashMap<String, String> ronda1 = new HashMap<>();
        File ficheroRonda1 = new File(".\\src\\ficheros\\ejercicio41.txt");
        ronda1 = leerFichero(ficheroRonda1);

        // Primera ronda - Se contestan tantas preguntas como se puedan en 1 minuto
        /*
        System.out.println("Comienza la primera ronda");
        for (String key : ronda1.keySet()) {
            System.out.println(key);
            respuestaIn = reader.nextLine();
            if (respuestaIn.replace(" ", "").equalsIgnoreCase(ronda1.get(key).replace(" ", ""))) {
                contadorAciertosConcursante++;
            }
            if (System.currentTimeMillis() > tiempoFin){
                System.out.println("Se acabó el tiempo!!");
                break;
            }
        }
        dineros = contadorAciertosConcursante * 1000;
        System.out.println("Has acertado " + contadorAciertosConcursante + " preguntas y has ganado " + dineros + " dineros");
        */
        contadorAciertosConcursante = 0;
        
        // Segunda ronda
        /*
            Ahora el concursante se enfrenta al cazador y para vencerle ha de:
            - acertar como mínimo 2 menos que el cazador
        Ejemplo: El cazador acierta 6, entonces el concursante tiene que acertar 4 
        */
        
        //Simulamos que juega el cazador
        for (int i = 0; i < 6; i++){
            double random = Math.random();
            if (random < 0.50){
                contadorAciertosCazador++;
            }
        }
        
        System.out.println("El cazador ha acertado: " + contadorAciertosCazador + "\nTienes que acertar al menos " + (contadorAciertosCazador-2) + " preguntas");
        
        HashMap<String, String> ronda2 = new HashMap<>();
        File ficheroRonda2 = new File(".\\src\\ficheros\\ejercicio42.txt");
        ronda2 = leerFicheroMultiple(ficheroRonda2);
        int contadorPreguntas = 0;
        
        for (String key : ronda2.keySet()){
            if (contadorPreguntas == 5){
                System.out.println("Se terminó la segunda ronda");
            } else {
                System.out.println(key);
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
        
        // Finaliza la segunda ronda
        
        if (contadorAciertosConcursante < contadorAciertosCazador){
            System.out.println("Que mala pata, has perdido el juego y los dineros ganados");
        } else {
            // Continuamos el juego
            File ficheroRonda3 = new File(".\\src\\ficheros\\ejercicio43.txt");
            HashMap<String, String> ronda3 = leerFichero(ficheroRonda3);
            
            System.out.println("Comienza la última ronda");
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
