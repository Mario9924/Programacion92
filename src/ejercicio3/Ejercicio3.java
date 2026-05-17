package ejercicio3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * 3 - Se pretende crear una serie de objetos de la clase Smartphone para más
 * adelante poder guardar la información, recuperarla y/o borrarla si se indica.
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
        File ficheroConfiguracion = new File(".\\src\\ficheros\\ejercicio3.txt");
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

        // Probamos a guardar la información del objeto en un fichero
        guardarInformacion(ficheroConfiguracion, miMovil);
        System.out.println("Información del móvil actual: \n" + miMovil);
        
        
        // Ahora probamos a recuperar la información
        SmartPhone nuevoMovil = recuperarInformacion(ficheroConfiguracion);
        System.out.println("Información tras recuperar el fichero: \n" + nuevoMovil);
    }

    public static void guardarInformacion(File ficheroIn, SmartPhone telfIn) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            // Generamos un flujo de datos para guardar en el fichero la información
            fos = new FileOutputStream(ficheroIn);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(telfIn);
            System.out.println("Se ha guardado la información");
        } catch (FileNotFoundException fnfex) {
            System.err.println("Error de fichero: " + fnfex);
        } catch (IOException ioex) {
            System.err.println("Error a al ahora de escribir el fichero" + ioex);
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException ioex) {
                System.err.println("Error a la hora de escribir el fichero" + ioex);
            }
        }
    }

    public static SmartPhone recuperarInformacion(File ficheroIn) {
        SmartPhone objetoRecuperado = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(ficheroIn);
            ois = new ObjectInputStream(fis);
            objetoRecuperado = (SmartPhone) ois.readObject();
        } catch (FileNotFoundException fnfex) {
            System.err.println("Error de fichero: " + fnfex);
        } catch (IOException ioex) {
            System.err.println("Error a al ahora de escribir el fichero" + ioex);
        } catch (ClassNotFoundException cnfex) {
            System.err.println("Error la clase indicada no se encuentra" + cnfex);
        } finally {
            try {
                ois.close();
                fis.close();
            } catch (IOException ioex) {
                System.err.println("Error a la hora de recuperar información del fichero" + ioex);
            }

        }
        return objetoRecuperado;
    }

}
