/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Temporizador.Principal;

import Alarma.Alarma;
import Temporizador.VentanaDeTiempo.ventanaDeTiempoRestante;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author iesu
 */
public class AccionDelBoton implements java.awt.event.ActionListener {

    //variables de control de tiempo
    int segundos;
    String tiempoRestante;
    
    //control de la ventana de tiempo
    ventanaDeTiempoRestante ventanaDeTiempo;

    //control de temporizador
    Timer temporizador;
    
    //tarea del temporizador
    TimerTask tarea;
    
    //control de alarma
    Alarma alarma;
    
    //controlar ventana principal
    VentanaPrincipal principal;
    
    
    
    public AccionDelBoton(VentanaPrincipal principal) {
        
        this.principal = principal;
        
        ventanaDeTiempo = new ventanaDeTiempoRestante();
        
    }




    /**
     * accion ejecutada por el boton 'botonAceptar'
     *
     * @param e
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        
        

       for(int rondas=Integer.parseInt(principal.campoDeTexto_rondas.getText());rondas>=0;rondas--){
        recibirDatos();
           principal.dispose();//cierra la ventana principal

        alarma = new Alarma();//crea la alarma
        
        //iniciando la ventana de tiempo restante        
        ventanaDeTiempo.setVisible(true);        
        
        //crea y ajusta el temporizador con la tarea a ejecutar
        temporizador = new Timer();
        tarea = new TimerTask() {

            @Override
            public void run() {

                //se actualizan el tiempo restante y la ventana
                actualizarTiempo();
                actualizar_ventanaDeTiempo();

                if (segundos == 0) {//si se acabo el tiempo...

                    //cierra la ventana de tiempo restante y cancela el temporizador para ejecutar la alarma                        
                    ventanaDeTiempo.dispose();
                    temporizador.cancel();
                    alarma.start();

                    //poner ventana de advertencia con respecto a la ventana de Tiempo restante
                    JOptionPane.showMessageDialog(ventanaDeTiempo, "SE ACABO EL TIEMPO !!!", "ADVERTENCIA", 2);
                    
                    

                }
                segundos--;
                if(segundos<0){
                   ventanaDeTiempo.dispose();
                    temporizador.cancel();
                }
            }
            
        };
        
        temporizador.schedule(tarea, 0, 1000);//repetir tarea cada segundo
    }
    }




    /**
     * recibe los datos de los campos de texto para Horas, minutos y segundos, y
     * transforma el tiempo en segundos, verifica si los campos estan vacios
     * para evitar errores
     */
    void recibirDatos() {

        try {

            segundos = 0;

            if (!principal.campoDeTexto_horas.getText().equals("")) {
                segundos += (3600 * Integer.parseInt(principal.campoDeTexto_horas.getText()));
            }

            if (!principal.campoDeTexto_minutos.getText().equals("")) {
                segundos += (60 * Integer.parseInt(principal.campoDeTexto_minutos.getText()));
            }

            if (!principal.campoDeTexto_segundos.getText().equals("")) {
                segundos += Integer.parseInt(principal.campoDeTexto_segundos.getText());
            }

        } catch (NumberFormatException nfe) {

            JOptionPane.showMessageDialog(principal, "Solo se aceptan numeros !!!", "ADVERTENCIA", 0);

            JOptionPane.showMessageDialog(principal, "Saliendo del sistema", "MENSAJE", 2);

            System.exit(0);

        }
    }




    /**
     * actualizando el tiempo que será mostrado en pantalla
     */
    void actualizarTiempo() {
        
        tiempoRestante = calcular_tiempo_restante();
        ventanaDeTiempo.tiempoRestante.setText(tiempoRestante);
        
    }




    /**
     * calcula el tiempo restante a partir de los segundos extra: tambien
     * calcula hasta dias
     *
     * @return
     */
    String calcular_tiempo_restante() {

        int seg, min, horas, dias;
        // condicional anidado
        dias = (segundos >= 86400) ? (int) (segundos / 86400) : 0;
        horas = (segundos >= 3600) ? (int) ((segundos - (dias * 86400)) / 3600) : 0;
        min = (segundos >= 60) ? (int) ((segundos - (dias * 86400) - (horas * 3600)) / 60) : 0;
        seg = (int) (segundos - (min * 60) - (horas * 3600) - (dias * 86400));

        return "    Quedan " + (dias) + " dias, " + (horas) + " horas, " + (min) + " minutos y " + (seg) + " segundos";
    }




    /**
     * revalida y actualiza la ventana de tiempo restante
     * 
     * este proceso es necesario porque los atributos de una ventana
     * son estaticos, por lo que hay que usar estos métodos del JFrame
     * para que la ventana se actualice
     */
    void actualizar_ventanaDeTiempo() {

        ventanaDeTiempo.revalidate();
        ventanaDeTiempo.repaint();

    }





    
    
}
