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
        // TODO code application logic here
    }
    
    
    public static HashMap<String, String> leerFichero(File ficheroIn){
        HashMap<String, String> preguntas = new HashMap<>();
        try {
            // Volcamos la información del fichero en un hashmap de modo que la información queda así; Key-Pregunta , Value-Respuesta
            Scanner rf = new Scanner(ficheroIn);
            while (rf.hasNext()){
                String[] informacion = rf.nextLine().split(";");
                preguntas.put(informacion[0], informacion[1]);
            }
            rf.close();
        } catch (FileNotFoundException ex) {
            System.getLogger(Ejercicio4.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return preguntas;
    }
    
    
}
