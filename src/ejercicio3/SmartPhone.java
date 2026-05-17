package ejercicio3;

import java.io.Serializable;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * Clase SmartPhone que contiene una serie de atributos como: brillo - volumen -
 * listadoLlamadas - imei (los cuales se van a almacenar) color - precio - marca
 * (que no vamos a guardar)
 *
 * @author Mario Gutiérrez
 * @see
 * https://classroom.google.com/c/ODA3NDY5OTY5MTcz/a/ODYyOTYzMjczMzkw/details
 * @version 1.0
 */
public class SmartPhone implements Serializable {

    private transient static Scanner reader = new Scanner(System.in);
    private transient String color;
    private transient final int precio = 1100;
    private transient final String marca = "πj-iphone";
    private String imei;
    private int brillo;
    private int volumen;
    private HashMap<String, String> listadoLlamadas;

    public SmartPhone(String colorIn) {
        this.color = colorIn;
        this.brillo = (int) Math.floor(Math.random() * 11);
        this.volumen = (int) Math.floor(Math.random() * 101);
        this.imei = setImei();
        this.listadoLlamadas = new HashMap<>(); // Si no se inicializa siempre será null
    }

    private HashMap<String, String> getListado() {
        return this.listadoLlamadas;
    }

    public String getImei() {
        return this.imei;
    }

    private String setImei() {
        int numeroAleatorio = 0; //+65
        String imeiGenerado = "";
        for (int i = 0; i < 18; i++) {
            numeroAleatorio = (int) Math.floor((Math.random() * 26) + 65);
            if (i == 6 || i == 9 || i == 16) {
                imeiGenerado += "/";
            } else {
                imeiGenerado += (char) numeroAleatorio;
            }
        }
        return imeiGenerado;
    }

    public void hacerLlamada(String contactoIn, String tipoLlamada) {
        if (this.getListado().size() == 5) {
            System.out.println("Se han realizado 5 llamadas, pasamos a eliminar una de ellas");
            Iterator<String> iterador = this.getListado().keySet().iterator();
            while (iterador.hasNext()) {
                iterador.next();
                iterador.remove();
                break;
            }    
        }
        this.getListado().put(contactoIn, tipoLlamada);

    }

    public void hacerLlamada(String contactoIn) {
        if (this.getListado().size() == 5) {
            System.out.println("Se han realizado 5 llamadas, pasamos a eliminar una de ellas");
            Iterator<String> iterador = this.getListado().keySet().iterator();
            while (iterador.hasNext()) {
                iterador.next();
                iterador.remove();
                break;
            }
        }
        int opcionIn = 0;
            String tipoLlamada = "Entrante";
            do {
                try {
                    System.out.println("La llamada hacia " + contactoIn + " es :"
                            + "\n0-Entrante"
                            + "\n1-Saliente");
                    opcionIn = reader.nextInt();
                } catch (InputMismatchException ime) {
                    System.err.println("No se permite el dato introducido: " + ime);
                    reader.nextLine();
                }

            } while (opcionIn < 0 || opcionIn > 1);
            if (opcionIn == 1) {
                tipoLlamada = "Saliente";
            }
            this.getListado().put(contactoIn, tipoLlamada);
    }

    public void mostrarLlamadas() {
        if (this.getListado() == null) {
            System.out.println("No se han realizado llamadas por el momento");
        } else {
            for (String key : this.getListado().keySet()) {
                System.out.println(key + " - " + this.getListado().get(key));
            }
        }
    }

}
