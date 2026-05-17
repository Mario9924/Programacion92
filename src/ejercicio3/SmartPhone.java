/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio3;

import java.io.Serializable;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Mario_
 */
public class SmartPhone implements Serializable{
    private transient static Scanner reader = new Scanner(System.in);
    private transient String color;
    private transient String imei;
    private transient final int precio = 1100;
    private transient final String marca = "πj-iphone";
    private int brillo;
    private int volumen;
    private HashMap<String, String> listadoLlamadas;
    
    public SmartPhone(String colorIn){
        this.color = colorIn;
        this.brillo = (int) Math.floor(Math.random()*11);
        this.volumen = (int) Math.floor(Math.random()*101);
    }
    
   private HashMap<String,String> getListado(){
       return this.listadoLlamadas;
   }
    
    
    public void hacerLlamada(String contactoIn){
        if (this.getListado().size() == 5){
            System.out.println("Se han realizado 5 llamadas, pasamos a eliminar una de ellas");
            Iterator<String> iterador = this.getListado().keySet().iterator();
            while (iterador.hasNext()){
                iterador.next();
                iterador.remove();
            }
            
            System.out.println(this.getListado());
        } else {
            try {
                int opcionIn = 0;
                String tipoLlamada = "Entrante";
                while (opcionIn < 0 || opcionIn > 1){
                    System.out.println("La llamada hacia " + contactoIn + "es :"
                            + "\n0-Entrante"
                            + "\n1-Saliente");
                    opcionIn = reader.nextInt();
                }
                if (opcionIn == 1){
                    tipoLlamada = "Saliente";
                }
                this.getListado().put(contactoIn, tipoLlamada);
            } catch (InputMismatchException ime){
                System.err.println("No se permite el dato introducido: " + ime);
                reader.nextLine();
            }
        }
    }
}
