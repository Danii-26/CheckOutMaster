package control;

import modelo.modelCraps;
import modelo.lanzarDados;

import java.util.Vector;

/**
 * Esta clase aplica las reglas del juego
 * */
public class Controlador {
    private int cantidadDados;
    Vector<Integer> cara;
    int flag, estado, punto;
    Vector<String> estadoString;

    public Controlador() {
        this.cantidadDados = cantidadDados;
    }

    public Controlador(int numero_de_dados) {
        this.cantidadDados = numero_de_dados;
        inicio(numero_de_dados);
    }

    public void inicio(int cantidadDados){
        System.out.println("Entro");
        //Creo un objeto donde tiro unos dados el cual recibe n cantidad de dados
        //creo un objeto que aplicara la regla
        lanzarDados _lanzarDados = new lanzarDados();
        modelCraps _modelCraps = new modelCraps();

        //Aqui tiro el o los dados
        _lanzarDados.iniciar(cantidadDados);

        //Recojo el resultado de los dados y lo capturo en la clase de regla
        _modelCraps.setPunto(getPunto());
        _modelCraps.setTiroValido(getFlag());
        _modelCraps.setTiro(_lanzarDados.getSuma());
        _modelCraps.ronda();
        setPunto(_modelCraps.getPunto());

        setCara(_lanzarDados.getCaras());
        setFlag( _modelCraps.getTiroValido());
        setEstado(_modelCraps.getEstado());
        setEstadoString(_modelCraps.getEstadoString());

        System.out.println("\nCONTROL estado del juego = "+ _modelCraps.getEstado()+" || El tiro fue "+ _lanzarDados.getSuma());
        System.out.println("CONTROL |Bandera|"+ _modelCraps.getTiroValido());
    }

    public int getCantidadDados() {
        return cantidadDados;
    }

    public void setCantidadDados(int cantidadDados) {
        this.cantidadDados = cantidadDados;
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

    public Vector<String> getEstadoString() {
        return estadoString;
    }

    public void setEstadoString(Vector<String> estadoString) {
        this.estadoString = estadoString;
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }
}