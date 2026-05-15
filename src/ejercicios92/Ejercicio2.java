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
    
    public static void addRecipe(){
        
    }
    
    public static void mostrarRecetas(){
        
    }
    
    
    public static void buscarIngrediente(String ingrediente){
        
    }
}
