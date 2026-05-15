package ejercicios92;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Mario Gutiérrez
 * @see https://classroom.google.com/c/ODA3NDY5OTY5MTcz/a/ODYyOTYzMjczMzkw/details
 * @version 1.0
 */
public class Ejercicio2 {
    public static void main(String[] args) {
        // Declaración de variables
        Scanner reader = new Scanner(System.in);
        HashSet<String> listadoRecetas = new HashSet<>();
        File fichero = new File(".\\src\\ficheros\\ejercicio2.txt");
        if (fichero.exists()){
            System.out.println("El fichero existe");
            try {
                Scanner rf = new Scanner(fichero);
                while (rf.hasNext()){
                    String[] informacion = rf.nextLine().split("\\|");
                    listadoRecetas.add(informacion[0]);
                }
                rf.close();
                System.out.println(listadoRecetas);
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
            if (listadoRecetas.contains(nombreReceta) || listadoRecetas.contains(nombreReceta.replace(" ", "")) || listadoRecetas.contains(nombreReceta.toLowerCase().replace(" ", ""))){
                System.out.println("El nombre elegido ya existe, elige otro por favor");
            } else {
                break;
                //nombreExiste = true;
            }
        }
        
        System.out.println("Has elegido el nomre: " + nombreReceta);
    }
}
