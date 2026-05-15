/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicios92;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author mario.gutgon
 */
public class Ejercicios92 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // DeclaraciÃ³n de variables
        Scanner reader = new Scanner(System.in);
        HashMap<String, String> diario = leerFichero();
        leerDiario(diario);
        
        
        String dia = "";
        String mes = "";
        String anio = "";
        String fecha = "";
        int informacionIn = 0;
        String entradaDiario = "";
        
        
        do {
            try {
            fecha = "";

            // Pedimos el dia 
            do {
                System.out.println("Dame el dÃ­a de la semana de la entrada del diario: ");
                informacionIn = reader.nextInt();
            } while (informacionIn <= 0 || informacionIn > 31);

            if (informacionIn < 10) {
                dia = "0" + informacionIn;
            } else {
                dia = informacionIn + "";
            }

            // Pedimos el mes 
            do {
                System.out.println("Dame el mes de la entrada del diario: ");
                informacionIn = reader.nextInt();
            } while (informacionIn <= 0 || informacionIn > 12);

            if (informacionIn < 10) {
                mes = "0" + informacionIn;
            } else {
                mes = informacionIn + "";
            }

            // Pedimos el aÃ±o 
            do {
                System.out.println("Dame el aÃ±o de la entrada del diario: ");
                informacionIn = reader.nextInt();
            } while (informacionIn <= 2000 || informacionIn > 2999);

            anio = informacionIn + "";

            fecha = dia + "/" + mes + "/" + anio;
            System.out.println("La fecha de entrada es: " + fecha);
            
            if (diario.containsKey(fecha)){
                System.out.println("Lo sentimos, ya hay una entrada para ese dÃ­a");
            }
            
            } catch (InputMismatchException ime){
                System.err.println("El dato introducido es invÃ¡lido: " + ime);
            } catch (Exception e){
                System.err.println("ERROR INESPERADO: " + e);
            }
        } while(diario.containsKey(fecha));
        
        
        reader.nextLine();
        // Pedimos al usuario lo que ha pasado ese dÃ­a
        
        System.out.println("Introduce lo que haya pasado ese dÃ­a por favor: ");
        entradaDiario = reader.nextLine();
        
        diario.put(fecha, entradaDiario);
        System.out.println("Se ha creado la entrada en tu diario");
    }
    
    public static HashMap<String, String> leerFichero(){
        HashMap<String, String> informacion = new HashMap<>();
        
        try {
            File diario = new File(".\\src\\Ficheros\\diario.txt");
            Scanner rf = new Scanner(diario);
            while (rf.hasNext()){
                String[] lineaDiario = rf.nextLine().split(";");
                informacion.put(lineaDiario[0], lineaDiario[1]);
            }
            rf.close();
        } catch (FileNotFoundException ex) {
            System.getLogger(Ejercicios92.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return informacion;
    }
    
    
    public static void leerDiario(HashMap<String, String> informacion){
        for (String key : informacion.keySet()){
            System.out.println("DÃ­a: " + key + " \"" + informacion.get(key) + "\"");
        }
    }
    
    
    public static void leerEntrada(HashMap<String, String> informacion){
        Scanner reader = new Scanner(System.in);
        int entradaIn = 0;
        do {
            for (String key : informacion.keySet()) {
                System.out.println("DÃ­a: " + key);
            }
        } while(true);
    }
}
