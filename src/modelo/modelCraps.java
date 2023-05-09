package modelo;

import java.util.Vector;

public class Model_craps {
    private int estado, validacion_tiro, punto, tiro;
    private Vector<String> estado_string;

    public Model_craps(int estado) {
        this.estado = estado;
    }

    public Model_craps() {
        estado = 0;
        validacion_tiro = 0;
        punto = 0;
        tiro = 0;
        estado_string = new Vector<String>();
    }

    public int getValidacion_tiro() {
        return validacion_tiro;
    }

    public Vector<String> getEstado_string() {
        switch (getEstado()){
            case 0:
                setEstado_string("Tu tiro de salida fue ("+punto+")");
                setEstado_string("Sique lansando hasta sacar \""+punto+"\"\n"
                    +"Si sacas \"7\" antes que \""+punto+"\" perderás");
                    break;
            case 1:
                setEstado_string("Tiro de salida ("+tiro+")");
                setEstado_string("|Haz sacado un natural !GANASTE¡|");
                break;
            case 2:
                setEstado_string("Tu tiro de salida fue ("+tiro+")");
                setEstado_string("Sacaste Craps, has perdido!!");
                break;
            case 3:
                setEstado_string("Tu tiro fue ("+tiro+")"+"\nHiciste punto" );
                setEstado_string("Estableaste Punto en \""+punto+"\" Debes seguir lanzando!!\n" +
                    "Pero si sacas \"7\" antes que \""+punto+"\" perderás");
                break;
            case 4:
                setEstado_string("Tu tiro de salida fue ("+punto+")");
                setEstado_string("Volviste a sacar \""+punto+"\" haz ganado!!");
                break;
            case 5:
                setEstado_string("Tu tiro fue ("+tiro+")");
                setEstado_string("Sacaste \"7\" antes que "+punto+" haz perdido!!");
                break;
        }
        return estado_string;
    }

    public void setEstado_string(String texto) {
        estado_string.addElement(texto);
    }

    public void setValidacion_tiro(int validacion_tiro) {
        this.validacion_tiro = validacion_tiro;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

    public int getTiro() {
        return tiro;
    }

    public void setTiro(int tiro) {
        this.tiro = tiro;
    }

    public void ronda_tiro(){
        System.out.println("|Inicia la ronda tiro|");
        int ronda = getValidacion_tiro();
        if ( ronda== 0) {
            if (getTiro() == 7 || getTiro() == 11) {
                setEstado(1);
                System.out.println("|!GANASTE¡|");
            } else if (getTiro() == 3 || getTiro() == 2 || getTiro() == 12) {
                setEstado(2);
                System.out.println("|!¡CRAPS|");
            }else {
                setEstado(3);
                setPunto(getTiro());
                setValidacion_tiro(1);
                System.out.println("|!PUNTO¡|"+getPunto());
            }
        }
        else {
            System.out.println("va ausar ronda punto|");
            ronda_punto();
        }
    }
    public void ronda_punto(){
        System.out.println("|entro a ronda punto|");
        if (getTiro() == getPunto()){
            setEstado(4);
            setValidacion_tiro(0);
            System.out.println("|!GANASTE EN RONDA PUNTO¡|");
        }
        else if (getTiro() == 7){
            setEstado(5);
            setValidacion_tiro(0);
            System.out.println("|CRAPS EN RONDA PUNTO|");
        }
        System.out.println("tiro "+getTiro()+ " punto " +punto);
    }

}