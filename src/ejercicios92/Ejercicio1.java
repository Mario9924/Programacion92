package ejercicios92;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * Ejercicio 1 - Diario Permitiremos
 *
 * - Escribir en el diario si la fecha de la entrada no está repetida - Obtener
 * la entrada del diario en función de la fecha
 *
 * @author Mario Gutiérrez
 * @see
 * https://classroom.google.com/c/ODA3NDY5OTY5MTcz/a/ODYyOTYzMjczMzkw/details
 * @version 1.0
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Declaración de variables
        Scanner reader = new Scanner(System.in);
        File fichero = new File(".\\src\\ficheros\\ejemplo.txt");
        HashMap<String, String> diario = leerFichero(fichero);
        int opcionIn = 0;
        boolean continuar = true;
        while (continuar) {
            try {
                System.out.println("Bienvenid@ a tu diario, elige una opción por favor:"
                        + "\n1- Escribir en el diario"
                        + "\n2- Leer una entrada del diario"
                        + "\nOtro - Salir del programa");
                opcionIn = reader.nextInt();
                switch (opcionIn) {
                    case 1:
                        System.out.println("Vamos a añadir una entrada al diario.");
                        addEntrada(diario, fichero);
                        break;
                    case 2:
                        System.out.println("Has elegido leer una entrada del diario.");
                        if (diario.isEmpty()) {
                            System.out.println("No hay nada que mostrar por el momento");
                        } else {
                            leerEntrada(diario);
                        }
                        break;
                    default:
                        System.out.println("Hasta luego!!");
                        continuar = false;
                        break;
                }
            } catch (InputMismatchException ime) {
                System.err.println("El dato introducido es inválido: " + ime);
                reader.nextLine();
            } catch (Exception e) {
                System.err.println("Error inesperado: " + e);
                reader.nextLine();
            }
        }

    }

    /**
     * Esta función permite desencriptar una entrada para que tenga un formato
     * más legible
     *
     * @param entradaIn
     * @return Un string con la entrada de forma legible
     */
    public static String desencriptarEntrada(String entradaIn) {
        String entradaDesencriptada = "";
        char[] values = entradaIn.toCharArray();
        for (int i = 0; i < values.length; i++) {
            char temporal = ' ';
            if (values[i] != ' ') {
                temporal = (char) (values[i] - 10);
                entradaDesencriptada += temporal;
            } else {
                entradaDesencriptada += ' ';
            }
        }
        return entradaDesencriptada;
    }

    /**
     * Esta función permite encriptar la entrada correspondiente para que no se
     * pueda leer
     *
     * @param entradaIn
     * @return String con la entrada codificada
     */
    public static String encriptarEntrada(String entradaIn) {
        String entradaEncriptada = "";
        char[] values = entradaIn.toCharArray();
        for (int i = 0; i < values.length; i++) {
            char temporal = ' ';
            if (values[i] != ' ') {
                temporal = (char) (values[i] + 10);
                entradaEncriptada += temporal;
            } else {
                entradaEncriptada += ' ';
            }
        }
        return entradaEncriptada;
    }

    /**
     * Esta función vuelca la información del fichero diario en un HashMap
     *
     * @return HashMap con la información del diario, siendo la Key el día de la
     * entrada y el Value la entrada en concreto
     */
    public static HashMap<String, String> leerFichero(File ficheroIn) {
        HashMap<String, String> informacion = new HashMap<>();

        try {
            Scanner rf = new Scanner(ficheroIn);
            while (rf.hasNext()) {
                String[] lineaDiario = rf.nextLine().split(";");
                informacion.put(lineaDiario[0], desencriptarEntrada(lineaDiario[1]));
            }
            rf.close();
        } catch (FileNotFoundException ex) {
            System.getLogger(Ejercicio1.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return informacion;
    }

    /**
     * Función que devuelve la información del diario en un formato amigable
     *
     * @param informacion HashMap con la información del diario
     */
    public static void leerDiario(HashMap<String, String> informacion) {
        for (String key : informacion.keySet()) {
            System.out.println("Dí­a: " + key + " \"" + informacion.get(key) + "\"");
        }
    }

    /**
     * Permite al usuario leer una entrada en concreto de nuestro diario
     *
     * @param informacion HashMap con la información del diario
     */
    public static void leerEntrada(HashMap<String, String> informacion) {
        Scanner reader = new Scanner(System.in);
        String entradaElegida = "";
        int opcionIn = 0;
        try {
            for (String key : informacion.keySet()) {
                System.out.println("Día: " + key);
                System.out.println("¿Quieres leer esta entrada? Pulsa 0 para leer, pulsa 1 para seguir:");
                opcionIn = reader.nextInt();
                if (opcionIn == 0) {
                    System.out.println("Se ha seleccionado el día \"" + key + "\"");
                    entradaElegida = key;
                    break;
                }
            }
            System.out.println(informacion.get(entradaElegida));
        } catch (InputMismatchException ime) {
            System.err.println("El dato introducido es inválido: " + ime);
            reader.nextLine();
        } catch (Exception e) {
            System.err.println("ERROR INESPERADO: " + e);
            reader.nextLine();
        }

    }

    /**
     * Esta función devuelve un HashSet con la fecha de las entradas del diario
     *
     * @param informacion HashMap con el diario volcado
     * @return HashSet con un listado de los días de las entradas
     */
    public static HashSet<String> listadoEntradas(HashMap<String, String> informacion) {
        HashSet<String> entradas = new HashSet<>();

        for (String info : informacion.keySet()) {
            entradas.add(info);
        }

        return entradas;
    }

    /**
     * Esta función permite añadir una entrada al diario siempre y cuando no
     * exista previamente. Se mostrará por pantalla si se ha podido añadir o no
     *
     * @param informacion HashMap con la información del fichero
     */
    public static void addEntrada(HashMap<String, String> informacion, File ficheroIn) {
        Scanner reader = new Scanner(System.in);
        HashSet<String> listado = listadoEntradas(informacion);
        String fecha = transformarFecha();

        if (listado.contains(fecha)) {
            System.out.println("Lo sentimos pero la fecha introducida ya existe en el diario.");
        } else {
            // Añadimos la entrada correspondiente
            String entradaDiario = "";
            System.out.println("Introduce lo que ha ocurrido el día " + fecha);
            entradaDiario = reader.nextLine();
            informacion.put(fecha, entradaDiario);
            // Llamamos a la función correspondiente para escribir en el fichero
            escribirEntradaFichero(fecha, encriptarEntrada(entradaDiario), ficheroIn);
            System.out.println("Entrada añadida al diario");
        }
    }

    /**
     * Esta función permite transformar los datos dia, mes y year en una fecha
     * separada por '/0'
     *
     * @return retorna un String con la fecha en formato 'DD/MM/YYYY'
     */
    public static String transformarFecha() {
        Scanner reader = new Scanner(System.in);
        String fecha = "";
        int dia = 0;
        int mes = 0;
        int year = 0;
        // Pedimos el dia 
        do {
            System.out.println("Dame el dÃ­a de la semana de la entrada del diario: ");
            dia = reader.nextInt();
        } while (dia <= 0 || dia > 31);

        if (dia < 10) {
            fecha = "0" + dia + "/";
        } else {
            fecha = dia + "/";
        }

        // Pedimos el mes 
        do {
            System.out.println("Dame el mes de la entrada del diario: ");
            mes = reader.nextInt();
        } while (mes <= 0 || mes > 12);

        if (mes < 10) {
            fecha += "0" + mes + "/";
        } else {
            fecha += mes + "/";
        }

        // Pedimos el aÃ±o 
        do {
            System.out.println("Dame el aÃ±o de la entrada del diario: ");
            year = reader.nextInt();
        } while (year <= 2000 || year > 2999);

        fecha += year;
        System.out.println("La fecha de entrada es: " + fecha);

        return fecha;
    }

    /**
     * Esta función permite la escritura de una entrada en el fichero del diario
     *
     * @param fechaIn String con la fecha de la entrada
     * @param entradaIn string con la información de lo que ha pasado
     * determinado día
     */
    public static void escribirEntradaFichero(String fechaIn, String entradaIn, File ficheroIn) {
        try {
            FileWriter fw = new FileWriter(ficheroIn, Charset.forName("ISO-8859-1"), true);
            fw.write(fechaIn + ";" + entradaIn + "\n");
            fw.close();
            System.out.println("Fichero escrito correctamente");
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (Exception e) {
            System.out.println("Error desconocido");
        }
    }
}
