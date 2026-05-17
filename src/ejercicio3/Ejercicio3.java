package ejercicio3;
import java.io.File;
import java.util.Scanner;

/**
 * 3 - Se pretende crear una serie de objetos de la clase Smartphone para más adelante poder guardar la información, recuperarla
 *   y/o borrarla si se indica.
 *
 * @author Mario Gutiérrez
 * @see https://classroom.google.com/c/ODA3NDY5OTY5MTcz/a/ODYyOTYzMjczMzkw/details
 * @version 1.0
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Declaración de variables
        File ficheroConfiguracion = new File("");
        Scanner reader = new Scanner(System.in);
        
        SmartPhone miMovil = new SmartPhone("Negro");
        
        /*
            Hacemos un par de pruebas de funcionamiento
        */
        
        miMovil.mostrarLlamadas(); // No mostrará nada ya que no hay llamadas por el momento
        
        miMovil.hacerLlamada("Antonio", "Entrante");
        miMovil.hacerLlamada("Maria", "Saliente");
        miMovil.hacerLlamada("Lola", "Saliente");
        
        miMovil.mostrarLlamadas(); // Mostrará las llamadas anteriores
        
        System.out.println("");
        // Hacemos 2 más
        
        miMovil.hacerLlamada("Pepe", "Entrante");
        miMovil.hacerLlamada("Laura", "Entrante");
        
        miMovil.mostrarLlamadas();
        
        // Si ahora añadimos una más, quitará la primera entrada que encuentre
        System.out.println("Patata");
        miMovil.hacerLlamada("Chelo");
        miMovil.mostrarLlamadas();
        
        
    }
    
}
