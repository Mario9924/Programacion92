package ejercicios92;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
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
        HashSet<String> nombresRecetas = new HashSet<>();
        File fichero = new File(".\\src\\ficheros\\recetas.txt");
        if (fichero.exists()){
            System.out.println("El fichero existe");
            try {
                Scanner rf = new Scanner(fichero);
                while (rf.hasNext()){
                    String[] informacion = rf.nextLine().split("\\|");
                    nombresRecetas.add(informacion[0]);
                }
                rf.close();
                System.out.println(nombresRecetas);
            } catch (FileNotFoundException ex) {
                System.getLogger(Ejercicio2.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else {
            System.out.println("El fichero no existe");
        }
        
        // Pedir nombre de la receta y comenzar a trabajar con el programa
        
        String nombreReceta  = "";
        //boolean nombreExiste = false;
        
        while (true){
            System.out.println("Dime el nombre de la receta que quieres incluir: ");
            nombreReceta = reader.nextLine();
            if (nombresRecetas.contains(nombreReceta) || nombresRecetas.contains(nombreReceta.replace(" ", "")) || nombresRecetas.contains(nombreReceta.toLowerCase().replace(" ", ""))){
                System.out.println("El nombre elegido ya existe, elige otro por favor");
            } else {
                break;
                //nombreExiste = true;
            }
        }
        
        System.out.println("Has elegido el nombre: " + nombreReceta);
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
                recetas.put(informacion[0], informacion[1]+informacion[2]);
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
            String ingredientes = "";
            String pasos = "";
            String informacionIn = "";
            int opcionIn = 0;
            // Añadimos los ingredientes que queramos
            
            while(true){
                System.err.println("Introduce el nombre del ingrediente necesario: ");
                informacionIn = reader.nextLine();
                ingredientes += informacionIn+";";
                System.out.println("¿Quieres seguir? Pulsa 0 para continuar y 1 para salir");
                opcionIn = reader.nextInt();
                if (opcionIn == 0){
                    System.out.println("Continuamos!\n");
                } else {
                    System.out.println("Pasamos al siguiente punto.");
                    break;
                }
            }   
            
            // Añadimos los pasos
            informacionIn = "";
            opcionIn = 0;
            while (true){
                System.err.println("Introduce el paso necesario para la receta: ");
                informacionIn = reader.nextLine();
                pasos += informacionIn + ";";
                System.out.println("¿Quieres seguir? Pulsa 0 para continuar y 1 para salir");
                opcionIn = reader.nextInt();
                if (opcionIn == 0){
                    System.out.println("Continuamos!\n");
                } else {
                    System.out.println("Pasamos al siguiente punto.");
                    break;
                }
            }
            
            recetas.put(nombreIn, ingredientes + "|" + pasos);
            
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
                 en otros dos arrays, uno para los ingredientes y otro para los pasos a seguir
            */
            String[] ingredientes = informacionReceta[0].split(";");
            String[] pasos = informacionReceta[1].split(";");
            System.out.println("Para la receta: " + nombreReceta+ "\n");
            for (int i = 0; i < ingredientes.length; i++){
                System.out.println("Ingrediente " + (i+1) + " - " + ingredientes[i]);
            }
            System.out.println("Pasos a seguir\n");
            for (int i = 0; i < pasos.length; i++){
                System.out.println("Paso " + (i+1) + " - " + pasos[i]);
            }
        }
    }
    
    
    public static void buscarIngrediente(String ingrediente){
        
    }
}
