package vista;

import javax.swing.*;
import java.awt.*;

public class GUI_INI extends JFrame{
    private ImageIcon imageIcon;
    private JLabel jLabel;
    private JPanel jPanel;
    private FondoPanel fondoPanel;

    public GUI_INI(){

        inicio();

        //Default JFrame configuration
        this.setTitle("menu");
        this.setUndecorated(true);
        this.setBackground(new Color(255,255,255,0)); //Quita el fondo de la ventana tiene que estar despues de  setUndecorated y antes de para que funcione
        this.pack();
        this.setSize(new Dimension(760,500));
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
//        this.setAlwaysOnTop(true); //Hace que esta ventana siempre este visible
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//
    private void inicio() {
        fondoPanel = new FondoPanel();
        fondoPanel.set_ruta_Icon("/recursos/fondo1.png");
        this.setContentPane(fondoPanel);
    }


}