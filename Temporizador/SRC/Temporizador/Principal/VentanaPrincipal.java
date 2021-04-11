/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Temporizador.Principal;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author iesu
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    

    //variables de la ventana principal
    JLabel txt_horas;
    JLabel txt_minutos;
    JLabel txt_segundos;
    JLabel txt_rondas;
    JTextField campoDeTexto_horas;
    JTextField campoDeTexto_minutos;
    JTextField campoDeTexto_segundos;
    JTextField campoDeTexto_rondas;
    JButton botonAceptar;



    public VentanaPrincipal() {

        iniciarVentana();
        

    }




    private void iniciarVentana() {

        setTitle("Ajustar Temporizador");
        setSize(295, 200);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        txt_horas = new JLabel("HORAS:");
        txt_horas.setBounds(35, 20, 60, 30);
        txt_minutos = new JLabel("MINUTOS:");
        txt_minutos.setBounds(35, 50, 60, 30);
        txt_segundos = new JLabel("SEGUNDOS:");
        txt_segundos.setBounds(35, 80, 90, 30);
        txt_rondas=new JLabel("RONDAS");
        txt_rondas.setBounds(35,110,90,30);
        campoDeTexto_horas = new JTextField();
        campoDeTexto_horas.setBounds(115, 20, 100, 30);

        campoDeTexto_minutos = new JTextField();
        campoDeTexto_minutos.setBounds(115, 50, 100, 30);

        campoDeTexto_segundos = new JTextField();
        campoDeTexto_segundos.setBounds(115, 80, 100, 30);
        campoDeTexto_rondas=new JTextField();
        campoDeTexto_rondas.setBounds(115, 110, 100,30);
        botonAceptar = new JButton();
        botonAceptar.setText("ACEPTAR");
        botonAceptar.setBounds(75, 145, 100, 27);
        botonAceptar.addActionListener(new AccionDelBoton(this));//accion del boton

        add(txt_horas);
        add(txt_minutos);
        add(txt_segundos);
        add(txt_rondas);
        add(campoDeTexto_horas);
        add(campoDeTexto_minutos);
        add(campoDeTexto_segundos);
        add(campoDeTexto_rondas);
        add(botonAceptar);

    }




    /**
     * ejecuta el programa Temporizador
     *
     * @param args
     */
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }

        });

    }

}
