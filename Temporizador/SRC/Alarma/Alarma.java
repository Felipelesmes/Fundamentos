/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alarma;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author iesu
 */
public class Alarma extends Thread {

    //variable que reproduce la alarma
    private Player reproductor;

    
    //impide o permite la reproduccion de la alarma
    private boolean permitirReproduccion = true;

    
    //archivo mp3 que se va a reproducir
    FileInputStream archivoMp3;




    public Alarma() {

        reiniciarReproductor();

    }




    /**
     * al asignar el audio de alarma, el reproductor se reinicia automaticamente
     */
    private void reiniciarReproductor() {

        try {

            archivoMp3 = new FileInputStream("alarma.mp3");
            reproductor = new Player(archivoMp3);

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "no se encontro el archivo mp3");
            
        } catch (JavaLayerException ex) {
        }
    }




    /**
     * cambia el valor de la variable de control de reproduccion
     * @param permitir 
     */
    public void PermitirReproduccion(boolean permitir) {

        this.permitirReproduccion = permitir;
        
    }




    /**
     * Reproduce la alarma hasta que la variable booleana lo impida
     */
    @Override
    public void run() {
        try {

            while (permitirReproduccion) {

                while (true) {

                    if (!reproductor.play(1) | !permitirReproduccion) {
                        break;
                    }
                }
                reiniciarReproductor();
            }

        } catch (JavaLayerException e) {
        }
    }
    

}
