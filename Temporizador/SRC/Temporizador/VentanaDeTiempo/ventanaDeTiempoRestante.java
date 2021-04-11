/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Temporizador.VentanaDeTiempo;


import javax.swing.JLabel;

/**
 *
 * @author iesu
 */
public class ventanaDeTiempoRestante extends javax.swing.JFrame {

    //tiempo restante, que será actualizado constantemente
    public JLabel tiempoRestante;
        
    
    //sirve para poner la ventana de tiempo en la esquina superior derecha de la pantalla
    int anchoDePantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;




    public ventanaDeTiempoRestante() {
        
        iniciarVentana();
        
    }




    /**
     * como su nombre lo dice, inicia la ventana
     */
    private void iniciarVentana() {
        
        setTitle("TIEMPO RESTANTE");
        setSize(400, 60);
        
        setResizable(false);
        setAlwaysOnTop(true);        

    //posiciona la ventana en la esquina superior derecha
    setLocation((anchoDePantalla - getWidth()), 0);

    setDefaultCloseOperation(EXIT_ON_CLOSE);

        tiempoRestante = new JLabel();
        tiempoRestante.setBounds(0, 5, 100, 50);

        add(tiempoRestante);
        
    }
    

}
