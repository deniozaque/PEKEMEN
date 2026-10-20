/*
Autor: Jorge
Fecha: 2026/27
Descrpcion: El jugador elige un elemento para enfrentarse a la máquina en una batalla individual o en un modo torneo eliminatorio. Durante el combate, puedes atacar y otras obciones.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        // constantes
        final String FUEGO = "fuego";
        final String AGUA = "agua";
        final String PLANTA = "planta";
        final String[] TIPOS = { FUEGO, AGUA, PLANTA };

        // variables
        int[] potencias = { 10, 20, 30, 40, 50, 60 };
        String tipo, tipo_jugador = "", tipo_maquina;
        int vida_jugador = 100, vida_maquina = 100,elecion;
        Boolean salir, pocion;

        BufferedReader save = new BufferedReader(new InputStreamReader(System.in));
        Random ram = new Random();

        // cuerpo
        salir = false;
        do {
            System.out.println("Escoge un tipo entre " + TIPOS.length);
            tipo = save.readLine();
            for (int contador = 0; contador < TIPOS.length; contador++) {
                if (tipo.equals(TIPOS[contador])) {
                    tipo_jugador = TIPOS[contador];
                    salir = true;
                }
            }
        } while (salir == false);

        tipo_maquina = TIPOS[ram.nextInt(TIPOS.length)];
        System.out.println("Tu tipo es " + tipo_jugador + " y el de tu rival es" + tipo_maquina);
        pocion = true;
        do {
            System.out.printf("Tu vida es %d y la del rival es %d%nQue quieres hacer%n1.atacar%n2. curarte%n",vida_jugador, vida_maquina);
            elecion=Integer.parseInt(save.readLine());
            if (elecion==1){
                vida_maquina -= (potencias[ram.nextInt(potencias.length)]);
            }else if (elecion==2 && pocion==true){
                System.out.println("Has gastado tu ultima pocion");
                vida_jugador+=20;
                pocion=false;
            }else if (elecion==2 && pocion==false){
                System.out.println("No te has podido curar porque no tienes pociones");
            }
            vida_jugador -= (potencias[ram.nextInt(potencias.length)]);
        } while (vida_jugador > 0 && vida_maquina > 0);
        
        if (vida_jugador > 0) {
            System.out.println("Has ganado");
        } else {
            System.out.println("Has perdido");
        }
    }
}
