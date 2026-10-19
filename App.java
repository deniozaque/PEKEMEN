/*
Autor: Jorge
Fecha: 2026/27
Descrpcion: El jugador elige un elemento para enfrentarse a la máquina en una batalla individual o en un modo torneo eliminatorio. Durante el combate, puedes atacar y otras obciones.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception{
        //constantes
        final String FUEGO="fuego";
        final String AGUA="agua";
        final String PLANTA="planta";
        final String[] TIPOS={FUEGO,AGUA,PLANTA};

        //variables
        int[] potenciales={10,20,30,40,50,60};
        BufferedReader save=new BufferedReader(new InputStreamReader(System.in));
        String tipo, tipo_jugador, tipo_maquina;
        Boolean salir;

        //cuerpo
        salir=false;
        do {
            System.out.println("Escoge un tipo entre "+TIPOS);
            tipo=save.readLine();
            for (int contador=0;contador<TIPOS.length;contador++){
                if (tipo.equals(TIPOS[contador])){
                    salir=true;
                }
            }
        }while(salir==false);
    }
}
