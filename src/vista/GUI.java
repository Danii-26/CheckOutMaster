package vista;

import control.Controlador;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class GUI extends JFrame {

    private Header header;
    private JPanel jpDadosActivos, jpDadosInactivos, jpPuntos, jpDadosUsados, jpMenu;
    private JLabel[] dado, dadoPoints;
    private ImageIcon dadoImagen;
    private GridBagConstraints constraints; // Referencias del grid
    private JButton lanzar, menu = null, atras, salir, entrar, salir1; // Declaracion de los botones del juego
    private Escucha escucha;
    private Menu menu1;// Ventana que contiene el menu para salir del juego
    private Controlador control;
    private int uso_boton_lanzar;
    private GUI_INI guiIni;
    private FondoPanel fondoPanel;


    public GUI(){


        entrarVentana();
        inicio_GUI();

        //Default JFrame configuration
        this.setTitle("Geek of master");
        this.isOpaque();

        this.setUndecorated(true);//Quita los trs botones de la ventana
        this.setBackground(new Color(255,255,255,0)); //Quita el fondo de la ventana tiene que estar despues de  setUndecorated y antes de para que funcione
        this.pack();
        this.setSize(new Dimension(750,500));
        this.setResizable(true);
        this.setVisible(false);// Oculta la ventana del juego, espera un evento que lo active
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    private void entrarVentana(){

        escucha = new GUI.Escucha();
        guiIni = new GUI_INI();
        guiIni.getContentPane().setLayout(new GridBagLayout());//Obtiene el contenedor por defecto de la ventana y pone un layout del tipo "GridBagLayout"
        constraints = new GridBagConstraints();//Se crea un objeto "constrain" para configurar el "GridBagLayout" cuando se esten ubicando los componetes de la ventana

        //Añado Boton a la ventana
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=1;
        constraints.gridheight=1;// combina 13 celdas para el titulo.
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        constraints.insets = new Insets(100,0,0,0);

        entrar = new JButton("ENTRAR");
        entrar.addActionListener(escucha);
        guiIni.add(entrar,constraints);

        //Añado Boton a la ventana
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.gridheight=1;// combina 13 celdas para el titulo.
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        constraints.insets = new Insets(10,0,0,0);

        salir1 = new JButton("SALIR");
        salir1.addActionListener(escucha);
        guiIni.add(salir1,constraints);

    }

    private void inicio_GUI() {
        fondoPanel = new FondoPanel();
        fondoPanel.set_ruta_Icon("/recursos/fondo2.png");
        this.setContentPane(fondoPanel);

        uso_boton_lanzar = 0;// '0' = botón lanzar sin usar
        dado = new JLabel[10];//Creacion de los dados
        dadoPoints = new JLabel[11];//Creacion de las carillas para los puntos

        //Ciclo que instancia los dados y las casillas y les pone una imagen
        for (int i=0;i<=9;i++){
            dadoImagen =new ImageIcon(getClass().getResource("/recursos/comodin.png"));
            dado[i] = new JLabel(dadoImagen);
            dadoImagen =new ImageIcon(getClass().getResource("/recursos/p"+i+".png"));
            dadoPoints[i] = new JLabel(dadoImagen);
        }

        dadoImagen =new ImageIcon(getClass().getResource("/recursos/puntaje.png"));
        dadoPoints[10] = new JLabel(dadoImagen);

        //Set up JFrame Container's Layout
        control = new Controlador();

        //Create Listener Object and Control Object
        escucha = new Escucha();

        //Instancio el menú del juego para utilizarlo luego el metodo escucha
        menu1 = null;

        //Obtiene el contenedor por defecto de la ventana y pone un layout del tipo "GridBagLayout"
        this.getContentPane().setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();//Se crea un objeto "constrain" para configurar el "GridBagLayout" cuando se esten ubicando los componetes de la ventana
        constraints.insets = new Insets(3,30,3,30);// Inserta margenes en los componentes insertados en la ventana

        //Set up JComponents
        //Configuración de los botones


        atras = new JButton("ATRAS");
        atras.setPreferredSize(new Dimension(100,50));
        atras.addActionListener(escucha);

        salir = new JButton("SALIR");
        salir.setPreferredSize(new Dimension(100,50));
        salir.addActionListener(escucha);



        //Texto de cabecera y coordenadas constrain para añadirlo a la ventana
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2; // combina 2 celdas para el titulo.
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;

        header = new Header("The Geek Out! Master", null);
        this.add(header,constraints); //Change this line if you change JFrame Container's Layout


        //Añado botón de Menú
        constraints.gridx=0;
        constraints.gridy=0;

        constraints.gridwidth=2; // combina 13 celdas para el titulo.
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;

        menu = new JButton("MENU");
        menu.addActionListener(escucha);
        this.add(menu,constraints); //Change this line if you change JFrame Container's Layout


        //Zona_1: Dados usados
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;

        jpDadosUsados = new JPanel();
        jpDadosUsados.setPreferredSize(new Dimension(250,200));
        jpDadosUsados.setBorder(BorderFactory.createTitledBorder(null, "Dados Usados", TitledBorder.CENTER,TitledBorder.CENTER,null,Color.WHITE));
        jpDadosUsados.setBackground(new Color(13, 64, 123, 128));
        this.add(jpDadosUsados,constraints); //Change this line if you change JFrame Container's Layout

        //Zona_2: Dados inctivos
        jpDadosInactivos = new JPanel();
        jpDadosInactivos.setBorder(BorderFactory.createTitledBorder(null,"Dadso Inactivos", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION,null,Color.WHITE));
        jpDadosInactivos.setBackground(new Color(13, 64, 123, 128));
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;

        for (int i=0;i<=3-1;i++){
            jpDadosInactivos.add(dado[i]);
        }

        this.add(jpDadosInactivos,constraints); //Change this line if you change JFrame Container's Layout

        //Zona_3: Dados puntos
        jpPuntos = new JPanel();
        jpPuntos.setPreferredSize(new Dimension(250,200));
        jpPuntos.setBorder(BorderFactory.createTitledBorder(null,"Puntuación",TitledBorder.CENTER,TitledBorder.DEFAULT_JUSTIFICATION,null,Color.WHITE));
        jpPuntos.setBackground(new Color(13, 64, 123, 128));
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;

        jpPuntos.add(dadoPoints[10]);

        for (int i=0;i<=10-1;i++){
            jpPuntos.add(dadoPoints[i]);
        }

        this.add(jpPuntos,constraints); //Change this line if you change JFrame Container's Layout


        //Zona_4 dedos Activos
        jpDadosActivos = new JPanel();
        jpDadosActivos.setPreferredSize(new Dimension(250,200));
        jpDadosActivos.setBorder(BorderFactory.createTitledBorder(null,"Dados Activos", TitledBorder.CENTER,TitledBorder.DEFAULT_JUSTIFICATION,null,Color.WHITE));
        jpDadosActivos.setBackground(new Color(13, 64, 123, 128));
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;

        for (int i=4-1;i<=10-1;i++){
            jpDadosActivos.add(dado[i],BorderLayout.SOUTH);
        }

        this.add(jpDadosActivos,constraints); //Change this line if you change JFrame Container's Layout

        //Zona_5 grgar boton lanzar
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;

        lanzar = new JButton("LANZAR DADOS");
        lanzar.addActionListener(escucha);
        this.add(lanzar,constraints); //Change this line if you change JFrame Container's Layout

    }


    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    public class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand() == "MENU"){
                menu1 = new Menu();
                menu.setEnabled(false);//Deshabilita el botón menu
                lanzar.setEnabled(false); //Deshabilita el botón lanzar
                jpMenu = new JPanel();//Contiene los botones del menu
                jpMenu.add(atras,BorderLayout.CENTER);
                jpMenu.add(salir,BorderLayout.SOUTH);
                menu1.add(jpMenu);
            }
            if (e.getActionCommand() == "ATRAS" && menu1 != null){
                menu1.dispose(); //Cierra la ventana de menu sin cerrar el programa
                menu.setEnabled(true);// Habilita el boton menu
                if (uso_boton_lanzar == 0){
                    lanzar.setEnabled(true);
                }

            }
            if (e.getSource() == salir){
                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea volver al Inicio?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION){
                    System.out.println("salir");
//                    System.exit(0);
                    dispose();
                    entrarVentana();
                }
                else if(opcion == JOptionPane.NO_OPTION){
                    System.out.println("nada");
                    menu1.dispose();
                    menu.setEnabled(true);
                }
            }
            if (e.getSource() == lanzar){
                for (int i=0;i<=9;i++){
                    control.inicio(10);
                    Vector<Integer> face = control.getCara();
                    dadoImagen =new ImageIcon(getClass().getResource("/recursos/"+face.get(0)+".png"));
                    dado[i].setIcon(dadoImagen);
                    System.out.println("test");
                }
            //    lanzar.setEnabled(false); //Deshabilita el boton lanzar
                uso_boton_lanzar = 1; // Indica que el botón lanzar ya fue usado

            }
            if (e.getSource() == entrar){
                guiIni.dispose();
                setVisible(true);
            }

            if (e.getSource() == salir1){
                System.exit(0);
            }

        }
    }
}