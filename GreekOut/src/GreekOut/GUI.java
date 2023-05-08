package GreekOut;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class GUI extends JFrame{
    public static final String CREDITOS = "CRÉDITOS\n-> VENUS JULIANA PAIPILLA\n-> DANIEL";
    public static final String HELP = "-> El juego cuenta con 10 Dados, de los cuales se toman 3 y se posicionan en \"Dados Inactivos\". Los 7 dados restantes se lanzan y pasan a ser los \"Dados Activos\"\n-> Se eligem los dados a utilizar de acuerdo a las habilidades de sus caras y se posiciona en el sector de \"Dados Utilizados\"\n-> Si no quedan acciones disponibles y hay uno o más dragones activos se pierden todos los puntos. Si usa este dado también perderá todos los puntos de la ronda y los acumulados \n-> Un único jugador puede hacer la partida y ganará si logra sumar 30 puntos en 5 rondas consecutivas de juego. ";
    private Header header;
    private JLabel mano;
    private JLabel textoPuntaje;
    private JLabel textoPuntajeTotal;
    private JLabel textoRonda;
    private JButton lanzar;
    private JButton ayuda;
    private JButton salir;
    private JButton creditos;
    private JButton botonExplicacion;
    private JButton continuarReiniciar;
    private JPanel panelDadosActivos;
    private JPanel panelDadosUtilizados;
    private JPanel panelDadosInactivos;
    private JPanel panelPuntaje;
    private JPanel panelRonda;
    private ImageIcon imageMano;
    private ImageIcon imageExplicacion;
    private ImageIcon imageDado;
    private JTextArea mensajesSalida;
    private Listener escucha;
    private CambiarImagen cambiarImagen;
    private AccionSuperHeroe superheroe;
    private AccionCorazon corazon;
    private AccionCohete cohete;
    private ModelCraps modelDados;
    private ArrayList<JButton> botones;
    private ArrayList<JButton> botonesUtilizados;
    private ArrayList<JButton> botonesInactivos;
    private HashMap<String, JButton> valorBotones;
    private HashMap<JButton, String> botonANombre;
    private int nuevoEscucha = 0;
    private int puntaje;
    private int puntajeRonda;
    private int ronda;
    private int dragon;
    private int estadoDelJuego;

    public GUI() {
        this.initGUI();
        this.setTitle("Geek Out Masters");
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo((Component)null);
    }

    private void initGUI() {
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        this.escucha = new Listener();
        this.cambiarImagen = new CambiarImagen();
        this.superheroe = new AccionSuperHeroe();
        this.corazon = new AccionCorazon();
        this.cohete = new AccionCohete();
        this.modelDados = new ModelCraps();
        this.botones = new ArrayList();
        this.botonesUtilizados = new ArrayList();
        this.botonesInactivos = new ArrayList();
        this.valorBotones = new HashMap();
        this.botonANombre = new HashMap();
        this.puntaje = 0;
        this.puntajeRonda = 0;
        this.ronda = 1;
        this.dragon = 0;
        this.estadoDelJuego = 0;
        this.header = new Header("Geek Out Masters", Color.BLACK);
        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.fill = 10;
        this.add(this.header, constraints);
        this.ayuda = new JButton(" help ");
        this.ayuda.addActionListener(this.escucha);
        this.ayuda.setBackground(Color.green);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = 0;
        constraints.anchor = 10;
        this.add(this.ayuda, constraints);
        this.creditos = new JButton(" Créditos ");
        this.creditos.addActionListener(this.escucha);
        this.creditos.setBackground(Color.yellow);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = 10;
        constraints.anchor = 21;
        this.add(this.creditos, constraints);
        this.salir = new JButton("Salir");
        this.salir.addActionListener(this.escucha);
        this.salir.setBackground(Color.red);
        constraints.gridx = 8;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = 0;
        constraints.anchor = 22;
        this.add(this.salir, constraints);
        this.botonExplicacion = new JButton("Explicación dados");
        this.botonExplicacion.addActionListener(this.escucha);
        this.botonExplicacion.setBackground(Color.ORANGE);
        constraints.gridx = 6;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.fill = 0;
        constraints.anchor = 10;
        this.add(this.botonExplicacion, constraints);
        this.imageMano = new ImageIcon(this.getClass().getResource("/utilidad/mano apretada.png"));
        this.mano = new JLabel();
        this.mano.setIcon(this.imageMano);
        this.textoPuntaje = new JLabel();
        this.textoPuntaje.setHorizontalAlignment(0);
        this.textoPuntajeTotal = new JLabel();
        this.textoPuntajeTotal.setHorizontalAlignment(0);
        this.textoRonda = new JLabel();
        this.panelDadosActivos = new JPanel();
        this.panelDadosActivos.setPreferredSize(new Dimension(300, 300));
        this.panelDadosActivos.setBorder(BorderFactory.createTitledBorder("Dados Activos"));
        this.panelDadosActivos.setBackground(Color.white);
        this.panelDadosActivos.add(this.mano);
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.fill = 1;
        constraints.anchor = 10;
        this.add(this.panelDadosActivos, constraints);
        this.panelDadosUtilizados = new JPanel();
        this.panelDadosUtilizados.setPreferredSize(new Dimension(300, 350));
        this.panelDadosUtilizados.setBorder(BorderFactory.createTitledBorder("Dados utilizados"));
        this.panelDadosUtilizados.setBackground(Color.white);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.fill = 1;
        constraints.anchor = 10;
        this.add(this.panelDadosUtilizados, constraints);
        this.panelDadosInactivos = new JPanel();
        this.panelDadosInactivos.setPreferredSize(new Dimension(300, 300));
        this.panelDadosInactivos.setBorder(BorderFactory.createTitledBorder("Dados Inactivos"));
        this.panelDadosInactivos.setBackground(Color.white);
        constraints.gridx = 6;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.fill = 1;
        constraints.anchor = 10;
        this.add(this.panelDadosInactivos, constraints);
        this.panelPuntaje = new JPanel();
        this.panelPuntaje.setLayout(new GridLayout(0, 1));
        this.panelPuntaje.setPreferredSize(new Dimension(200, 100));
        this.panelPuntaje.setBorder(BorderFactory.createTitledBorder("Puntaje"));
        this.panelPuntaje.setBackground(new Color(112, 215, 163, 255));
        this.panelPuntaje.add(this.textoPuntajeTotal);
        this.panelPuntaje.add(this.textoPuntaje);
        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.fill = 1;
        constraints.anchor = 10;
        this.add(this.panelPuntaje, constraints);
        this.panelRonda = new JPanel();
        this.panelRonda.setPreferredSize(new Dimension(200, 100));
        this.panelRonda.setBorder(BorderFactory.createTitledBorder("Ronda"));
        this.panelRonda.setBackground(new Color(69, 201, 248, 255));
        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.fill = 1;
        constraints.anchor = 10;
        this.add(this.panelRonda, constraints);
        this.lanzar = new JButton("Tirar los dados");
        this.lanzar.addActionListener(this.escucha);
        this.lanzar.setBackground(Color.pink);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.fill = 10;
        constraints.anchor = 10;
        this.add(this.lanzar, constraints);
        this.continuarReiniciar = new JButton();
        this.continuarReiniciar.setText("Continuar ronda");
        this.continuarReiniciar.addActionListener(this.escucha);
        this.continuarReiniciar.setName("continuarReiniciar");
        this.continuarReiniciar.setBackground(Color.cyan);
        this.continuarReiniciar.setEnabled(false);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.fill = 10;
        constraints.anchor = 10;
        this.add(this.continuarReiniciar, constraints);
        this.mensajesSalida = new JTextArea(2, 28);
        this.mensajesSalida.setText("Usa el botón (help) para ver las reglas del juego");
        this.mensajesSalida.setBorder(BorderFactory.createTitledBorder("Atención: "));
        this.mensajesSalida.setEditable(false);
        this.mensajesSalida.setBackground(new Color(241, 113, 113, 255));
        constraints.gridx = 3;
        constraints.gridy = 5;
        constraints.gridwidth = 3;
        constraints.fill = 0;
        constraints.anchor = 10;
        this.add(this.mensajesSalida, constraints);
        this.modelDados.lanzamientoDados();
        this.inicializarBotones();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new GUI();
        });
    }

    public void inicializarBotones() {
        int dado;
        Class var10003;
        ModelCraps var10004;
        String var10005;
        for(dado = 0; dado < this.modelDados.listaDados("activos").size(); ++dado) {
            this.botones.add(new JButton());
            ((JButton)this.botones.get(dado)).setName("dado" + String.valueOf(dado + 1));
            ((JButton)this.botones.get(dado)).setBorder((Border)null);
            ((JButton)this.botones.get(dado)).setBackground(Color.white);
            ((JButton)this.botones.get(dado)).addMouseListener(this.escucha);
            ((JButton)this.botones.get(dado)).setVisible(false);
            var10003 = this.getClass();
            var10004 = this.modelDados;
            var10005 = "dado" + String.valueOf(dado + 1);
            this.imageDado = new ImageIcon(var10003.getResource("/recursos/" + var10004.getAccionDado(var10005, "activos") + ".png"));
            ((JButton)this.botones.get(dado)).setIcon(new ImageIcon(this.imageDado.getImage().getScaledInstance(80, 80, 1)));
            this.panelDadosActivos.add((Component)this.botones.get(dado));
        }

        for(dado = 0; dado < this.modelDados.listaDados("inactivos").size(); ++dado) {
            this.botonesInactivos.add(new JButton());
            ((JButton)this.botonesInactivos.get(dado)).setName("dado" + String.valueOf(dado + 1));
            ((JButton)this.botonesInactivos.get(dado)).setBorder((Border)null);
            ((JButton)this.botonesInactivos.get(dado)).setBackground(Color.white);
            ((JButton)this.botonesInactivos.get(dado)).setVisible(false);
            var10003 = this.getClass();
            var10004 = this.modelDados;
            var10005 = "dado" + String.valueOf(dado + 1);
            this.imageDado = new ImageIcon(var10003.getResource("/recursos/" + var10004.getAccionDado(var10005, "inactivos") + ".png"));
            ((JButton)this.botonesInactivos.get(dado)).setIcon(new ImageIcon(this.imageDado.getImage().getScaledInstance(80, 80, 1)));
            this.panelDadosInactivos.add((Component)this.botonesInactivos.get(dado));
        }

    }

    public void actualizarPanel(String nombrePanel) {
        int boton;
        if (nombrePanel == "activos") {
            this.panelDadosActivos.removeAll();
            this.panelDadosActivos.add(this.mano);

            for(boton = 0; boton < this.botones.size(); ++boton) {
                this.panelDadosActivos.add((Component)this.botones.get(boton));
            }

            this.panelDadosActivos.updateUI();
        } else if (nombrePanel == "inactivos") {
            this.panelDadosInactivos.removeAll();

            for(boton = 0; boton < this.botonesInactivos.size(); ++boton) {
                this.panelDadosInactivos.add((Component)this.botonesInactivos.get(boton));
            }

            this.panelDadosInactivos.updateUI();
        } else {
            this.panelDadosUtilizados.removeAll();

            for(boton = 0; boton < this.botonesUtilizados.size(); ++boton) {
                this.panelDadosUtilizados.add((Component)this.botonesUtilizados.get(boton));
            }

            this.panelDadosUtilizados.updateUI();
        }

    }

    public void renombrarBotones(String nombreArray) {
        int boton;
        if (nombreArray == "activos") {
            for(boton = 0; boton < this.botones.size(); ++boton) {
                ((JButton)this.botones.get(boton)).setName("dado" + String.valueOf(boton + 1));
            }
        } else if (nombreArray == "inactivos") {
            for(boton = 0; boton < this.botonesInactivos.size(); ++boton) {
                ((JButton)this.botonesInactivos.get(boton)).setName("dado" + String.valueOf(boton + 1));
            }
        } else {
            for(boton = 0; boton < this.botonesUtilizados.size(); ++boton) {
                ((JButton)this.botonesUtilizados.get(boton)).setName("dado" + String.valueOf(boton + 1));
            }
        }

    }

    public JButton mappingJButton(String nombreArray, String nombreDado) {
        int boton;
        if (nombreArray == "activos") {
            for(boton = 0; boton < this.botones.size(); ++boton) {
                this.valorBotones.put(((JButton)this.botones.get(boton)).getName(), (JButton)this.botones.get(boton));
            }
        } else if (nombreArray == "inactivos") {
            for(boton = 0; boton < this.botonesInactivos.size(); ++boton) {
                this.valorBotones.put(((JButton)this.botonesInactivos.get(boton)).getName(), (JButton)this.botonesInactivos.get(boton));
            }
        } else {
            for(boton = 0; boton < this.botonesUtilizados.size(); ++boton) {
                this.valorBotones.put(((JButton)this.botonesUtilizados.get(boton)).getName(), (JButton)this.botonesUtilizados.get(boton));
            }
        }

        return (JButton)this.valorBotones.get(nombreDado);
    }

    public void rondas() {
        int dados42 = 0;
        int dadosDragon = 0;
        boolean seguirLanzando = false;
        String resultadoPuntaje = "";
        int boton;
        if (this.botones.size() == 0) {
            this.puntajeRonda = 0;
            this.puntaje += this.puntajeRonda;
            ++this.ronda;
            this.estadoDelJuego = 1;
        } else if (this.botones.size() == 1) {
            if (this.modelDados.getAccionDado(((JButton)this.botones.get(0)).getName(), "activos") == "corazon") {
                this.nuevoEscucha = 0;
                this.estadoDelJuego = 0;
                seguirLanzando = true;
                this.escuchas();
            } else if (this.modelDados.getAccionDado(((JButton)this.botones.get(0)).getName(), "activos") == "42") {
                this.puntajeRonda = 1;
                this.puntaje += this.puntajeRonda;
                ++this.ronda;
                this.estadoDelJuego = 1;
            } else if (this.modelDados.getAccionDado(((JButton)this.botones.get(0)).getName(), "activos") == "dragon") {
                this.puntajeRonda = 0;
                this.puntaje = this.puntajeRonda;
                ++this.ronda;
                this.estadoDelJuego = 1;
            } else {
                this.puntajeRonda = 0;
                this.puntaje += this.puntajeRonda;
                ++this.ronda;
                this.estadoDelJuego = 1;
            }
        } else {
            for(boton = 0; boton < this.botones.size(); ++boton) {
                if (this.modelDados.getAccionDado(((JButton)this.botones.get(boton)).getName(), "activos") == "42") {
                    ++dados42;
                } else if (this.modelDados.getAccionDado(((JButton)this.botones.get(boton)).getName(), "activos") == "dragon") {
                    ++dadosDragon;
                }
            }

            if (dados42 == this.botones.size()) {
                switch (dados42) {
                    case 2:
                        this.puntajeRonda = 3;
                        this.puntaje += this.puntajeRonda;
                        ++this.ronda;
                        this.estadoDelJuego = 1;
                        break;
                    case 3:
                        this.puntajeRonda = 6;
                        this.puntaje += this.puntajeRonda;
                        ++this.ronda;
                        this.estadoDelJuego = 1;
                        break;
                    case 4:
                        this.puntajeRonda = 10;
                        this.puntaje += this.puntajeRonda;
                        ++this.ronda;
                        this.estadoDelJuego = 1;
                        break;
                    case 5:
                        this.puntajeRonda = 15;
                        this.puntaje += this.puntajeRonda;
                        ++this.ronda;
                        this.estadoDelJuego = 1;
                        break;
                    case 6:
                        this.puntajeRonda = 21;
                        this.puntaje += this.puntajeRonda;
                        ++this.ronda;
                        this.estadoDelJuego = 1;
                        break;
                    case 7:
                        this.puntajeRonda = 28;
                        this.puntaje += this.puntajeRonda;
                        ++this.ronda;
                        this.estadoDelJuego = 1;
                        break;
                    case 8:
                        this.puntajeRonda = 36;
                        this.puntaje += this.puntajeRonda;
                        ++this.ronda;
                        this.estadoDelJuego = 1;
                        break;
                    case 9:
                        this.puntajeRonda = 45;
                        this.puntaje += this.puntajeRonda;
                        ++this.ronda;
                        this.estadoDelJuego = 1;
                        break;
                    case 10:
                        this.puntajeRonda = 55;
                        this.puntaje += this.puntajeRonda;
                        ++this.ronda;
                        this.estadoDelJuego = 1;
                }
            } else if (dados42 + dadosDragon == this.botones.size()) {
                this.puntajeRonda = 0;
                this.puntaje = 0;
                ++this.ronda;
                this.estadoDelJuego = 1;
            } else {
                seguirLanzando = true;
                this.estadoDelJuego = 0;
            }
        }

        if (this.dragon == 1) {
            this.puntajeRonda = 0;
            this.puntaje = this.puntajeRonda;
            this.textoPuntajeTotal.setText("Puntaje total: " + String.valueOf(this.puntaje));
        }

        if (seguirLanzando) {
            resultadoPuntaje = "¡Sigue lanzando!";
        } else {
            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(this.puntajeRonda);
        }

        if (this.estadoDelJuego == 1) {
            for(boton = 0; boton < this.botones.size(); ++boton) {
                ((JButton)this.botones.get(boton)).removeMouseListener(this.escucha);
            }

            this.modelDados.listaDados("activos").clear();
            this.modelDados.listaDados("inactivos").clear();
            this.modelDados.listaDados("utilizados").clear();
            this.botones.clear();
            this.botonesInactivos.clear();
            this.botonesUtilizados.clear();
            this.modelDados.lanzamientoDados();
            this.textoPuntajeTotal.setText("Puntaje total: " + String.valueOf(this.puntaje));
            this.textoPuntaje.setText(resultadoPuntaje);
            this.inicializarBotones();
            if (this.ronda < 6 && this.puntaje < 29) {
                this.continuarReiniciar.setEnabled(true);
            } else {
                if (this.ronda < 6 && this.puntaje > 29) {
                    resultadoPuntaje = "Tu puntaje es: " + String.valueOf(this.puntaje) + " ¡Has ganado!";
                } else {
                    resultadoPuntaje = "Tu puntaje es: " + String.valueOf(this.puntaje) + " ¡Has perdido!";
                }

                this.puntaje = 0;
                this.puntajeRonda = 0;
                this.ronda = 1;
                this.continuarReiniciar.setText("Jugar de nuevo");
                this.continuarReiniciar.setEnabled(true);
            }

            this.dragon = 0;
        }

        this.textoPuntaje.setText(resultadoPuntaje);
    }

    public void escuchas() {
        class GetEscuchas implements MouseListener {
            GetEscuchas() {
            }

            public void mouseClicked(MouseEvent e) {
                switch (GUI.this.nuevoEscucha) {
                    case 0:
                        GUI.this.escucha.mouseClicked(e);
                        break;
                    case 1:
                        GUI.this.cambiarImagen.mouseClicked(e);
                        break;
                    case 2:
                        GUI.this.superheroe.mouseClicked(e);
                        break;
                    case 3:
                        GUI.this.corazon.mouseClicked(e);
                        break;
                    case 4:
                        GUI.this.cohete.mouseClicked(e);
                }

            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        }

    }

    private class Listener implements ActionListener, MouseListener {
        private Listener() {
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == GUI.this.lanzar) {
                GUI.this.mano.setVisible(false);
                GUI.this.lanzar.setEnabled(false);
                GUI.this.textoRonda.setText("Ronda: " + String.valueOf(GUI.this.ronda));
                GUI.this.panelRonda.add(GUI.this.textoRonda);
                GUI.this.continuarReiniciar.setText("Continuar ronda");
                GUI.this.textoPuntajeTotal.setText("Puntaje total: " + String.valueOf(GUI.this.puntaje));
                GUI.this.textoPuntaje.setText("¡Lanza un dado!");

                int dado;
                for(dado = 0; dado < GUI.this.botones.size(); ++dado) {
                    ((JButton)GUI.this.botones.get(dado)).setVisible(true);
                }

                for(dado = 0; dado < GUI.this.botonesInactivos.size(); ++dado) {
                    ((JButton)GUI.this.botonesInactivos.get(dado)).setVisible(true);
                    ((JButton)GUI.this.botonesInactivos.get(dado)).setEnabled(false);
                }
            } else if (e.getSource() == GUI.this.creditos) {
                JOptionPane.showMessageDialog((Component)null, "CRÉDITOS\n-> BRAYAN STIVEN SANCHEZ LEON\n-> MAYRA ALEJANDRA SANCHEZ SALINAS");
            } else if (e.getSource() == GUI.this.ayuda) {
                JOptionPane.showMessageDialog((Component)null, "-> De los 10 dados que trae el juego se toman 3 y se colocan en el sector de \"Dados Inactivos\". Los otros 7 dados se tiran y pasan a ser los \"Dados Activos\"\n-> Se van eligiendo los dados a utilizar según las habilidades de sus caras y se pasan al sector de \"Dados Utilizados\"\n-> Si no hay más acciones disponibles y hay uno o más dragones activos pierde todos los puntos. Si usa este dado también perderá todos los puntos de la ronda y acumulados \n-> Este juego lo jugará un único jugador y ganará si logra sumar 30 puntos en 5 rondas consecutivas de juego. ");
            } else if (e.getSource() == GUI.this.botonExplicacion) {
                GUI.this.imageExplicacion = new ImageIcon(this.getClass().getResource("/utilidad/explicacion.png"));
                JOptionPane.showMessageDialog((Component)null, "", "Explicacion de cada cara del dado", -1, GUI.this.imageExplicacion);
            } else if (e.getSource() == GUI.this.continuarReiniciar) {
                GUI.this.actualizarPanel("activos");
                GUI.this.actualizarPanel("inactivos");
                GUI.this.actualizarPanel("utilizados");
                GUI.this.textoRonda.setText("Ronda: " + String.valueOf(GUI.this.ronda));
                GUI.this.textoPuntajeTotal.setText("Puntaje total: " + String.valueOf(GUI.this.puntaje));
                GUI.this.textoPuntaje.setText((String)null);
                GUI.this.continuarReiniciar.setEnabled(false);
                GUI.this.lanzar.setEnabled(true);
                GUI.this.mano.setVisible(true);
            } else {
                System.exit(0);
            }

        }

        public void mouseClicked(MouseEvent e) {
            String nombreBoton = "";
            String nombreAccion = "";
            nombreBoton = e.getComponent().getName();
            nombreAccion = GUI.this.modelDados.getAccionDado(nombreBoton, "activos");
            GUI.this.mappingJButton("activos", nombreBoton).setEnabled(false);
            GUI.this.mappingJButton("activos", nombreBoton).removeMouseListener(this);
            GUI.this.botonesUtilizados.add(GUI.this.mappingJButton("activos", nombreBoton));
            GUI.this.renombrarBotones("utilizados");
            GUI.this.botones.remove(GUI.this.mappingJButton("activos", nombreBoton));
            GUI.this.renombrarBotones("activos");
            GUI.this.modelDados.dadosUtilizados(nombreBoton);
            GUI.this.actualizarPanel("activos");
            GUI.this.actualizarPanel("utilizados");
            GUI.this.valorBotones.clear();
            int boton;
            if (nombreAccion == "mepple") {
                for(boton = 0; boton < GUI.this.botones.size(); ++boton) {
                    ((JButton)GUI.this.botones.get(boton)).removeMouseListener(this);
                    ((JButton)GUI.this.botones.get(boton)).addMouseListener(GUI.this.cambiarImagen);
                }

                GUI.this.nuevoEscucha = 1;
                GUI.this.textoPuntaje.setText("Accion mepple activado");
                GUI.this.escuchas();
            } else if (nombreAccion == "superheroe") {
                if (GUI.this.botones.size() == 1 && GUI.this.modelDados.getAccionDado(((JButton)GUI.this.botones.get(0)).getName(), "activos") == "superheroe") {
                    GUI.this.rondas();
                } else {
                    for(boton = 0; boton < GUI.this.botones.size(); ++boton) {
                        ((JButton)GUI.this.botones.get(boton)).removeMouseListener(this);
                        ((JButton)GUI.this.botones.get(boton)).addMouseListener(GUI.this.superheroe);
                    }

                    GUI.this.textoPuntaje.setText("Accion superheroe activado");
                    GUI.this.nuevoEscucha = 2;
                    GUI.this.escuchas();
                }
            } else if (nombreAccion == "dragon") {
                GUI.this.dragon = 1;
                GUI.this.nuevoEscucha = 0;
                GUI.this.puntaje = 0;
                GUI.this.escuchas();
                GUI.this.rondas();
            } else if (nombreAccion == "corazon") {
                if (GUI.this.botonesInactivos.size() > 0) {
                    for(boton = 0; boton < GUI.this.botones.size(); ++boton) {
                        ((JButton)GUI.this.botones.get(boton)).removeMouseListener(this);
                    }

                    for(boton = 0; boton < GUI.this.botonesInactivos.size(); ++boton) {
                        ((JButton)GUI.this.botonesInactivos.get(boton)).setEnabled(true);
                        ((JButton)GUI.this.botonesInactivos.get(boton)).addMouseListener(GUI.this.corazon);
                    }

                    GUI.this.nuevoEscucha = 3;
                    GUI.this.textoPuntaje.setText("Accion corazon activado");
                    GUI.this.escuchas();
                } else {
                    GUI.this.nuevoEscucha = 0;
                    GUI.this.escuchas();
                    GUI.this.rondas();
                }
            } else if (nombreAccion == "cohete") {
                for(boton = 0; boton < GUI.this.botones.size(); ++boton) {
                    ((JButton)GUI.this.botones.get(boton)).removeMouseListener(this);
                    ((JButton)GUI.this.botones.get(boton)).addMouseListener(GUI.this.cohete);
                }

                GUI.this.nuevoEscucha = 4;
                GUI.this.textoPuntaje.setText("Accion cohete activado");
                GUI.this.escuchas();
            } else {
                GUI.this.nuevoEscucha = 0;
                GUI.this.escuchas();
                GUI.this.rondas();
            }

        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    private class CambiarImagen implements MouseListener {
        private CambiarImagen() {
        }

        public void mouseClicked(MouseEvent e) {
            String botonSecundario = "";
            botonSecundario = e.getComponent().getName();
            GUI.this.modelDados.accionMepple(botonSecundario);
            GUI var10000 = GUI.this;
            Class var10003 = this.getClass();
            String var10004 = GUI.this.modelDados.getAccionDado(botonSecundario, "activos");
            var10000.imageDado = new ImageIcon(var10003.getResource("/recursos/" + var10004 + ".png"));
            GUI.this.mappingJButton("activos", botonSecundario).setIcon(new ImageIcon(GUI.this.imageDado.getImage().getScaledInstance(80, 80, 1)));

            for(int boton = 0; boton < GUI.this.botones.size(); ++boton) {
                ((JButton)GUI.this.botones.get(boton)).removeMouseListener(this);
                ((JButton)GUI.this.botones.get(boton)).addMouseListener(GUI.this.escucha);
            }

            GUI.this.nuevoEscucha = 0;
            GUI.this.escuchas();
            GUI.this.rondas();
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    private class AccionSuperHeroe implements MouseListener {
        private AccionSuperHeroe() {
        }

        public void mouseClicked(MouseEvent e) {
            String botonSecundario = "";
            botonSecundario = e.getComponent().getName();
            if (GUI.this.modelDados.getAccionDado(botonSecundario, "activos") == "superheroe") {
                GUI.this.textoPuntaje.setText("No se puede girar otro superheroe");
                GUI.this.nuevoEscucha = 2;
                GUI.this.escuchas();
            } else {
                GUI.this.modelDados.accionSuperHeroe(botonSecundario);
                GUI var10000 = GUI.this;
                Class var10003 = this.getClass();
                String var10004 = GUI.this.modelDados.getAccionDado(botonSecundario, "activos");
                var10000.imageDado = new ImageIcon(var10003.getResource("/recursos/" + var10004 + ".png"));
                GUI.this.mappingJButton("activos", botonSecundario).setIcon(new ImageIcon(GUI.this.imageDado.getImage().getScaledInstance(80, 80, 1)));

                for(int boton = 0; boton < GUI.this.botones.size(); ++boton) {
                    ((JButton)GUI.this.botones.get(boton)).removeMouseListener(this);
                    ((JButton)GUI.this.botones.get(boton)).addMouseListener(GUI.this.escucha);
                }

                GUI.this.nuevoEscucha = 0;
                GUI.this.escuchas();
                GUI.this.rondas();
            }

        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    private class AccionCorazon implements MouseListener {
        private AccionCorazon() {
        }

        public void mouseClicked(MouseEvent e) {
            String botonSecundario = "";
            botonSecundario = e.getComponent().getName();
            GUI.this.modelDados.accionCorazon(botonSecundario);
            GUI.this.botones.add(GUI.this.mappingJButton("inactivos", botonSecundario));
            GUI.this.renombrarBotones("activos");
            GUI.this.botonesInactivos.remove(GUI.this.mappingJButton("inactivos", botonSecundario));
            GUI.this.renombrarBotones("inactivos");
            GUI var10000 = GUI.this;
            Class var10003 = this.getClass();
            ModelCraps var10004 = GUI.this.modelDados;
            String var10005 = "dado" + String.valueOf(GUI.this.botones.size());
            var10000.imageDado = new ImageIcon(var10003.getResource("/recursos/" + var10004.getAccionDado(var10005, "activos") + ".png"));
            GUI.this.mappingJButton("activos", "dado" + String.valueOf(GUI.this.botones.size())).setIcon(new ImageIcon(GUI.this.imageDado.getImage().getScaledInstance(80, 80, 1)));
            GUI.this.actualizarPanel("activos");
            GUI.this.actualizarPanel("inactivos");

            int boton;
            for(boton = 0; boton < GUI.this.botonesInactivos.size(); ++boton) {
                ((JButton)GUI.this.botonesInactivos.get(boton)).removeMouseListener(this);
                ((JButton)GUI.this.botonesInactivos.get(boton)).setEnabled(false);
            }

            for(boton = 0; boton < GUI.this.botones.size(); ++boton) {
                ((JButton)GUI.this.botones.get(boton)).removeMouseListener(this);
                ((JButton)GUI.this.botones.get(boton)).addMouseListener(GUI.this.escucha);
            }

            GUI.this.nuevoEscucha = 0;
            GUI.this.escuchas();
            GUI.this.rondas();
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    private class AccionCohete implements MouseListener {
        private AccionCohete() {
        }

        public void mouseClicked(MouseEvent e) {
            String botonSecundario = "";
            botonSecundario = e.getComponent().getName();
            GUI.this.modelDados.accionCohete(botonSecundario);
            GUI.this.mappingJButton("activos", botonSecundario).setEnabled(false);
            GUI.this.mappingJButton("activos", botonSecundario).removeMouseListener(this);
            GUI.this.botonesInactivos.add(GUI.this.mappingJButton("activos", botonSecundario));
            GUI.this.renombrarBotones("inactivos");
            GUI.this.botones.remove(GUI.this.mappingJButton("activos", botonSecundario));
            GUI.this.renombrarBotones("activos");
            GUI.this.actualizarPanel("inactivos");
            GUI.this.actualizarPanel("activos");

            for(int boton = 0; boton < GUI.this.botones.size(); ++boton) {
                ((JButton)GUI.this.botones.get(boton)).removeMouseListener(this);
                ((JButton)GUI.this.botones.get(boton)).addMouseListener(GUI.this.escucha);
            }

            GUI.this.nuevoEscucha = 0;
            GUI.this.escuchas();
            GUI.this.rondas();
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

}
