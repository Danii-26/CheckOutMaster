package modelo;

import java.util.Random;

/**
 * Clase dado que genera un numero aleatorio entre 1 y 6
 * @autor Jose Erley Murillo Torres
 * @version v.0.0.0 data 18/04/2023
 * */
public class Dado {
    //Atributo que retorna un lado de la cara del dado
    private int cara;
    //Si presiono "Alt + Insert" genera un método predefinido
    public int get_cara() {
        //Creo un objeto randon que me permite crear un numeros aleatorios
        Random aleatorio = new Random();
        cara = aleatorio.nextInt(6)+1;
        return cara;
    }



}