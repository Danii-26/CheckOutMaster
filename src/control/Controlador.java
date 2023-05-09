package control;

import modelo.Model_craps;
import modelo.Tirar_dados;

import java.util.Vector;

/**
 * Esta clase aplica las reglas del juego
 * */
public class Controlador {
    private int numero_de_dados;
    Vector<Integer> cara;
    int flag, estado, punto;
    Vector<String>Estado_string;

    public Controlador() {
        this.numero_de_dados = numero_de_dados;
    }

    public Controlador(int numero_de_dados) {
        this.numero_de_dados = numero_de_dados;
        inicio(numero_de_dados);
    }

    public void inicio(int numero_de_dados){
        System.out.println("Entro");
        //Creo un objeto donde tiro unos dados el cual recibe n cantidad de dados
        //creo un objeto que aplicara la regla
        Tirar_dados _tirar_dados = new Tirar_dados();
        Model_craps _model_craps = new Model_craps();

        //Aqui tiro el o los dados
        _tirar_dados.iniciar(numero_de_dados);

        //Recojo el resultado de los dados y lo capturo en la clase de regla
        _model_craps.setPunto(getPunto());
        _model_craps.setValidacion_tiro(getFlag());
        _model_craps.setTiro(_tirar_dados.getSumatoria());
        _model_craps.ronda_tiro();
        setPunto(_model_craps.getPunto());

        setCara(_tirar_dados.getCaras());
        setFlag( _model_craps.getValidacion_tiro());
        setEstado(_model_craps.getEstado());
        setEstado_string(_model_craps.getEstado_string());

        System.out.println("\nCONTROL estado del juego = "+_model_craps.getEstado()+" || El tiro fue "+_tirar_dados.getSumatoria());
        System.out.println("CONTROL |Bandera|"+_model_craps.getValidacion_tiro());
    }

    public int getNumero_de_dados() {
        return numero_de_dados;
    }

    public void setNumero_de_dados(int numero_de_dados) {
        this.numero_de_dados = numero_de_dados;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Vector<Integer> getCara() {
        return cara;
    }

    public void setCara(Vector<Integer> cara) {
        this.cara = cara;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Vector<String> getEstado_string() {
        return Estado_string;
    }

    public void setEstado_string(Vector<String> estado_string) {
        Estado_string = estado_string;
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }
}