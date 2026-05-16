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
 * 2) Desarrollar un programa nuevo para gestionar recetas de cocina en un
 * fichero. Debe permitir: 
 * • Añadir nuevas recetas al archivo (no se puede introducir otra con un nombre que ya exista, idem al ejercicio anterior...).
 * • Listar todas las guardadas actualmente. 
 * • Buscar todas las recetas con un ingrediente. 
 * 
 * Cada receta puede tener asociada una lista de ingredientes y pasos para la preparación. 
 * El formato del archivo será el que sigue:
 * NOMBRE_RECETA | INGREDIENTE1 ; INGREDIENTE2 ;... ; INGREDIENTE N | PASO1;
 * PASO 2; ... ; PASO M.
 * 
 * 
 * @author Mario Gutiérrez
 * @see https://classroom.google.com/c/ODA3NDY5OTY5MTcz/a/ODYyOTYzMjczMzkw/details
 * @version 1.0
 */
public class Ejercicio2 {
    public static void main(String[] args) {
        // Declaración de variables
        Scanner reader = new Scanner(System.in);
        HashMap<String, String> recetas = new HashMap<>();
        int opcionIn = 0;
        String informacionStringIn = ""; // Variable que guardará el nombre de la receta o del ingrediente
        boolean continuar = true;
        File fichero = new File(".\\src\\ficheros\\recetas.txt");
        if (fichero.exists()){
            System.out.println("El fichero existe");
            //Guardamos la información
            recetas = obtenerInformacion(fichero);
            
            while (continuar){
                try {
                    informacionStringIn = "";
                    opcionIn=0;
                    // Comenzamos el menú
                    System.out.println("Bienvenid@ al recetario de Mario, a continuación mostramos las opciones disponibles:"
                            + "\n1- Listar todas las recetas disponibles"
                            + "\n2- Añadir una nueva receta"
                            + "\n3- Buscar recetas a partir de un ingrediente"
                            + "\nOtro - Salir del prorgrama");
                    opcionIn = reader.nextInt();
                    switch (opcionIn) {
                        case 1:
                            System.out.println("Pasamos a mostrar la información disponible de las recetas: ");
                            mostrarRecetas(recetas);
                            break;
                        case 2:
                            reader.nextLine();
                            System.out.println("Vamos a añadir una receta, por favor introduce el nombre: ");
                            informacionStringIn = reader.nextLine();
                            if (comprobarNombreReceta(recetas, informacionStringIn)){
                                System.out.println("Lo sentimos pero el nombre de la receta ya existe");
                            } else {
                                System.out.println("Perfecto! Podemos añadir la receta");
                                recetas.putAll(addRecipe(recetas, informacionStringIn));
                            }
                            break;
                        case 3:
                            reader.nextLine();
                            System.out.println("Introduce el nombre del ingrediente que vamos a buscar por favor: ");
                            informacionStringIn = reader.nextLine();
                            buscarIngrediente(recetas, informacionStringIn);
                            break;
                        default:
                            System.out.println("Hasta luego!");
                            continuar = false;
                            break;
                    }
                } catch (InputMismatchException ime){
                    System.err.println("El dato introducido es incorrecto: " + ime);
                    reader.nextLine();
                } catch (Exception e){
                    System.err.println("ERROR INESPERADO: " + e);
                    reader.nextLine();
                }
            }
        } else {
            System.out.println("El fichero no existe");
        }
        
        
    }
    
    
    /**
     * Esta función permite almacenar toda la información disponible del fichero de recetas en un HashMap
     *  El formato es siempre el mismo, Key = Nombre receta, Value = Ingredientes y pasos
     */
    public static HashMap<String, String> obtenerInformacion(File ficheroRecetas){
        HashMap<String,String> recetas = new HashMap<>();
        try {
            //Creamos un flujo de datos para obtener la información del fichero
            Scanner rf = new Scanner(ficheroRecetas);
            while(rf.hasNext()){
                String[] informacion = rf.nextLine().split("\\|");
                // Almacenamos los datos de las recetas en un hashmap
                recetas.put(informacion[0], informacion[1]+ "|" + informacion[2]); // Si no añadimos | de nuevo, no tenemos delimitador
            }
            rf.close();
        } catch (FileNotFoundException fnfex) {
            System.err.println("Error a la hora de trabajar con el fichero: " + fnfex);
        }
        return recetas;
    }
    
    /**
     * Esta función comprueba la existencia de una receta en nuestro recetario
     * @param recetas un HashMap con la información completa del recetario
     * @param nombreIn el nombre de la nueva receta
     * @return true si existe / false si no existe
     */
    public static boolean comprobarNombreReceta(HashMap<String,String> recetas, String nombreIn){
        boolean nombreRepetido = false;
        HashSet<String> nombresRecetas = new HashSet<>();
        for (String receta : recetas.keySet()){
            nombresRecetas.add(receta.replace(" ", "").toLowerCase());
        }
        
        if (nombresRecetas.contains(nombreIn.toLowerCase().replace(" ", ""))){
            nombreRepetido = true;
        }
        
        return nombreRepetido;
    }
    
    /**
     * Esta función permite junto a la función comprobarNombreReceta() el crear una receta
     * @param recetas HashMap con la información de todas las recetas
     * @param nombreIn nombre de la receta
     * @return otro HashMap con la información actualizada
     */
    public static HashMap<String,String> addRecipe(HashMap<String,String> recetas, String nombreIn){
        HashMap<String, String> nuevaReceta = new HashMap<>();
        // Volcamos la información que ya teníamos en el HashMap con el que vamos a trabajar ahora
        nuevaReceta.putAll(recetas);
        Scanner reader = new Scanner(System.in);
        System.out.println("Pasamos a añadir la receta indicada");
            String ingredientes = ""; // guardamos todos los ingredientes seguidos
            String pasos = ""; // guardamos todos los ingredientes seguidos
            String informacionIn = "";
            /*
                Guarda la información de los String ingredientes y pasos con una | de seperación
                    ya que lo necesitamos más adelante para añadirlo al HashMap y al fichero de recetas correspondiente
            */
            String valueHashMap = "";
            int opcionIn = 0;
            
            // Añadimos los ingredientes que queramos
            while(true){
                try {
                    System.out.println("Introduce el nombre del ingrediente necesario: ");
                    informacionIn = reader.nextLine();
                    System.out.println("¿Quieres seguir? Pulsa 0 para continuar y 1 para salir");
                    opcionIn = reader.nextInt();
                    if (opcionIn == 0) {
                        System.out.println("Continuamos!\n");
                        // Si vamos a seguir tenemos que añadir el delimitador ';' detrás de cada palabra introducida
                        ingredientes += informacionIn + ";";
                    } else {
                        System.out.println("Pasamos al siguiente punto.");
                        // Si no vamos a guardar más información, el último ingrediente no tiene que tener ningún delimitador
                        ingredientes += informacionIn;
                        break;
                    }
                    reader.nextLine();
                } catch (InputMismatchException ime){
                    System.err.println("Lo sentimos pero el dato introducido es incorrecto: " + ime);
                    reader.nextLine();
                }
            }   
            
            // Añadimos los pasos
            informacionIn = "";
            opcionIn = 0;
            while (true){
                try {
                    reader.nextLine();
                    System.out.println("Introduce el paso necesario para la receta: ");
                    informacionIn = reader.nextLine();
                    System.out.println("¿Quieres seguir? Pulsa 0 para continuar y 1 para salir");
                    opcionIn = reader.nextInt();
                    if (opcionIn == 0) {
                        System.out.println("Continuamos!\n");
                        // Si vamos a seguir tenemos que añadir el delimitador ';' detrás de cada palabra introducida
                        pasos += informacionIn + ";";
                    } else {
                        System.out.println("Pasamos al siguiente punto.");
                        // Si no vamos a guardar más información, el último paso no tiene que tener ningún delimitador
                        pasos += informacionIn;
                        break;
                    }
                } catch (InputMismatchException ime){
                    System.err.println("Lo sentimos pero el dato introducido es incorrecto: " + ime);
                    reader.nextLine();
                }
            }
            // Guardamos en el string correspondiente la informacion de los ingredientes y recetas, se usará más adelante
            valueHashMap = ingredientes + "|" + pasos;
            
            // Guardamos la nueva receta en nuestro HashMap
            recetas.put(nombreIn, valueHashMap);
            
            // Guardamos la nueva receta en nuestro fichero gracias al nombre introducido por el usuario y la combinación de ingredientes y pasos anterior
            escribirReceta(nombreIn + "|" + valueHashMap);
            System.out.println("Perfecto, hemos añadido tu receta al recetario");
            
        
        return nuevaReceta;
    }
    
    /**
     * Esta función toma la información de nuestras recetas a partir de un HashMap que contiene dicha información
     *   y muestra todos los datos debidamente
     * @param recetas HashMap con la información de las recetas
     */
    public static void mostrarRecetas(HashMap<String,String> recetas){
        for (String nombreReceta : recetas.keySet()){
            // 1- La información de la receta proviene del value del key set, el cual viene separado por '|'
            String[] informacionReceta = recetas.get(nombreReceta).split("\\|");
            /*
                Una vez separada la información mediante el caracter '|' podemos guardar la información necesarias 
                 en otros dos arrays, uno para los ingredientes y otro para los pasos a seguir.
                 
                De este modo podemos mostrar la información de forma más sencilla
            */
            String[] ingredientes = informacionReceta[0].split(";");
            String[] pasos = informacionReceta[1].split(";");
            System.out.println("Para la receta: " + nombreReceta);
            System.out.println("\nIngredientes: ");
            for (int i = 0; i < ingredientes.length; i++){
                System.out.println("Ingrediente " + (i+1) + " - " + ingredientes[i]);
            }
            System.out.println("\nPasos a seguir:");
            for (int i = 0; i < pasos.length; i++){
                System.out.println("Paso " + (i+1) + " - " + pasos[i]);
            }
            System.out.println("");
        }
    }
    
    /**
     * Esta función permite añadir una receta al recetario, solo necesitamos la información formateada correctamente para incluirlo en el fichero
     * @param informacionReceta String con el formato NombreReceta|ingrediente1;ingredienteN|paso1;pasoN
     */
    public static void escribirReceta(String informacionReceta){
        try {
            File fichero = new File(".\\src\\ficheros\\recetas.txt");
            FileWriter fw = new FileWriter(fichero, Charset.forName("ISO-8859-1"), true);
            fw.write(informacionReceta + "\n");
            fw.close();
            System.out.println("Fichero escrito correctamente");
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (Exception e) {
            System.out.println("Error desconocido");
        }
    }
    
    /**
     * Esta función devuelve aquellas recetas que contengan un ingrediente introducido
     * @param recetas HashMap con la información de todas las recetas disponibles
     * @param ingredienteIn ingrediente que haya elegido el usuario
     */
    public static void buscarIngrediente(HashMap<String,String> recetas, String ingredienteIn){
        //Guardamos en un HashMap temporal la información encontrada hasta el momento
            HashMap<String, String> recetasEncontradas = new HashMap<>();
            
        // Una vez recibido el HashMap con la información pasamos a buscar el ingrediente
        for (String informacion : recetas.keySet()){
            
            // Separamos la sección de recetas de su 
            String[] datos = recetas.get(informacion).split("\\|");
            String[] ingredientes = datos[0].split(";");
            for (int i = 0; i < ingredientes.length; i++){
                if (ingredientes[i].replace(" ", "").equalsIgnoreCase(ingredienteIn.replace(" ", ""))){
                    // El ingrediente se encuentra
                    recetasEncontradas.put(informacion, recetas.get(informacion));
                    break;
                }
            }
        }
        
        // Una vez terminemos de buscar las recetas las mostramos
        if (recetasEncontradas.isEmpty()){
            System.out.println("Lo sentimos pero no encontramos ninguna receta con el ingrediente \""+ ingredienteIn + "\"");
        } else {
            // Como hemos encontrado al menos una receta, mostramos la información por pantalla
            mostrarRecetas(recetasEncontradas); // Ya teníamos una función que nos lo muestra de forma más amigable
        }
    }
}
