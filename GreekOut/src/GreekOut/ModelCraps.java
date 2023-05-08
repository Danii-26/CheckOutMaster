package GreekOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ModelCraps {
    private int dadoRandom;
    private ArrayList<Dado> dados;
    private ArrayList<Dado> dadosInactivos = new ArrayList();
    private ArrayList<Dado> dadosUtilizados = new ArrayList();
    private HashMap<String, String> nombreAAccion = new HashMap();
    private HashMap<String, String> nombreAEstado = new HashMap();
    private HashMap<String, Dado> nombreAObjeto = new HashMap();

    public ModelCraps() {
    }

    public ArrayList listaDados(String nombreArray) {
        ArrayList auxiliar;
        if (nombreArray == "activos") {
            auxiliar = this.dados;
        } else if (nombreArray == "inactivos") {
            auxiliar = this.dadosInactivos;
        } else {
            auxiliar = this.dadosUtilizados;
        }

        return auxiliar;
    }

    public void lanzamientoDados(){

        //Creación de los 10 dados
        dados = new ArrayList<>();
        for(int dado=0; dado < 10; dado++){
            dados.add(new Dado());
        }

        asignacionAcciones(); // Asigna todas las acciones del ArrayList dados
        setActivo(); // Establece estado activo a todos los dados
        dadosInactivos(); // Selecciona 3 dados inactivos y los borra del Arraylist dados
        identidadDado("activos"); // Actualiza los nombres del ArrayList dados
        identidadDado("inactivos"); // Actualiza los nombres del ArrayList dadosInactivos
    }

    public void dadosInactivos(){
        for(int inactivo=0; inactivo < 3; inactivo++){
            Random random = new Random();
            dadoRandom = random.nextInt(dados.size());
            dados.get(dadoRandom).setActivoInactivo("inactivo");
            dadosInactivos.add(dados.get(dadoRandom));
            dados.remove(dadoRandom);
        }

        identidadDado("activos");
        identidadDado("inactivos");
    }

    /**
     * Asigna el nombre a cada dado dependiendo del ArrayList
     * @param array
     */
    public void identidadDado(String array){
        if(array == "activos"){
            for(int dado=0; dado < dados.size(); dado++){
                dados.get(dado).setNombreDado("dado" + String.valueOf(dado+1));
            }
        }else{
            if(array == "inactivos"){
                for(int dado=0; dado < dadosInactivos.size(); dado++){
                    dadosInactivos.get(dado).setNombreDado("dado" + String.valueOf(dado+1));
                }
            }else{
                for(int dado=0; dado < dadosUtilizados.size(); dado++){
                    dadosUtilizados.get(dado).setNombreDado("dado" + String.valueOf(dado+1));
                }
            }
        }
    }

    /**
     * Establece el estado inicial (activo) del ArrayList dados
     */
    public void setActivo(){
        for(int dado=0; dado < dados.size(); dado++){
            dados.get(dado).setActivoInactivo("activo");
        }
    }

    public void asignacionAcciones(){
        for(int i=0; i < dados.size(); i++){
            dados.get(i).setNumAccion();
        }

        listaAcciones();
    }

    public void listaAcciones(){
        for(int numero=0; numero < dados.size(); numero++){
            switch(dados.get(numero).getNumAccion()){
                case 1:
                    dados.get(numero).setAccion("mepple");
                    break;
                case 2:
                    dados.get(numero).setAccion("superheroe");
                    break;
                case 3:
                    dados.get(numero).setAccion("dragon");
                    break;
                case 4:
                    dados.get(numero).setAccion("corazon");
                    break;
                case 5:
                    dados.get(numero).setAccion("cohete");
                    break;
                case 6:
                    dados.get(numero).setAccion("42");
                    break;
                default:
                    break;
            }
        }
    }

    public String getAccionDado(String _nombreDado, String nombreArray){
        String accionDado = "";
        if(nombreArray == "activos"){
            for(int dado=0; dado < dados.size(); dado++){
                nombreAAccion.put(dados.get(dado).getNombreDado(), dados.get(dado).getAccion());
            }
            accionDado = nombreAAccion.get(_nombreDado);
        }else{
            for(int dado=0; dado < dadosInactivos.size(); dado++){
                nombreAAccion.put(dados.get(dado).getNombreDado(), dados.get(dado).getAccion());
            }
            accionDado = nombreAAccion.get(_nombreDado);
        }
        return accionDado;
    }

    public void accionCorazon(String nombreDado) {
        for (int dado = 0; dado < dadosInactivos.size(); dado++) {
            nombreAObjeto.put(dadosInactivos.get(dado).getNombreDado(), dadosInactivos.get(dado));
        }
    }
    public void accionMepple(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            nombreAObjeto.put(dados.get(dado).getNombreDado(), dados.get(dado));
        }

        nombreAObjeto.get(nombreDado).setNumAccion();
        listaAcciones();
        nombreAObjeto.clear();
    }
    public void dadosUtilizados(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            nombreAObjeto.put(dados.get(dado).getNombreDado(), dados.get(dado));
        }

        dadosUtilizados.add(nombreAObjeto.get(nombreDado));
        nombreAObjeto.get(nombreDado).setActivoInactivo("utilizado");
        dados.remove(nombreAObjeto.get(nombreDado));
        identidadDado("activos");
        identidadDado("utilizados");
        identidadDado("inactivos");
        nombreAObjeto.clear();
    }

    public void accionSuperHeroe(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            nombreAObjeto.put(dados.get(dado).getNombreDado(), dados.get(dado));
        }

        if(nombreAObjeto.get(nombreDado).getAccion() == "mepple"){
            nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(5);
            listaAcciones();
        }else{
            if(nombreAObjeto.get(nombreDado).getAccion() == "superheroe"){
                nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(3);
                listaAcciones();
            }else{
                if(nombreAObjeto.get(nombreDado).getAccion() == "dragon"){
                    nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(2);
                    listaAcciones();
                }else{
                    if(nombreAObjeto.get(nombreDado).getAccion() == "corazon"){
                        nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(6);
                        listaAcciones();
                    }else{
                        if(nombreAObjeto.get(nombreDado).getAccion() == "cohete"){
                            nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(1);
                            listaAcciones();
                        }else{
                            if(nombreAObjeto.get(nombreDado).getAccion() == "42"){
                                nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(4);
                                listaAcciones();
                            }
                        }
                    }
                }
            }
        }
        nombreAObjeto.clear();
    }

    public void accionCohete(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            nombreAObjeto.put(dados.get(dado).getNombreDado(), dados.get(dado));
        }

        nombreAObjeto.get(nombreDado).setActivoInactivo("inactivo");
        dadosInactivos.add(nombreAObjeto.get(nombreDado));
        dados.remove(nombreAObjeto.get(nombreDado));
        identidadDado("activos");
        identidadDado("inactivos");
        nombreAObjeto.clear();
    }
}