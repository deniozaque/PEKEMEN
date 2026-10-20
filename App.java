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
        int vida_jugador, vida_maquina, elecion, contador, contador_torneo;
        Boolean salir, huir, pocion, torneo;

        BufferedReader save = new BufferedReader(new InputStreamReader(System.in));
        Random ram = new Random();

        // cuerpo
        salir = false;
        huir = false;
        torneo = false;
        contador_torneo = 1;
        // vucle principal
        while (huir == false) {
            System.out.printf("Que quieres hacer:%n1. Torneo%n2. Combate singular%n3. Salir%nElecion: ");
            elecion = Integer.parseInt(save.readLine());
            if (elecion == 1) {
                torneo = true;
            } else if (elecion == 2) {
                torneo = false;
            } else if (elecion == 3) {
                System.out.println("Adios");
                huir = true;
                return;
            }

            // escoger tipos
            do {
                System.out.println("Escoge un tipo entre " + TIPOS.length);
                tipo = save.readLine();
                for (contador = 0; contador < TIPOS.length; contador++) {
                    if (tipo.equals(TIPOS[contador])) {
                        tipo_jugador = TIPOS[contador];
                        salir = true;
                    }
                }
            } while (salir == false);

            // combate
            tipo_maquina = TIPOS[ram.nextInt(TIPOS.length)];
            System.out.println("Tu tipo es " + tipo_jugador + " y el de tu rival es " + tipo_maquina);
            pocion = true;
            do {
                vida_jugador = 100;
                vida_maquina = 100;
                do {
                    System.out.printf(
                            "Tu vida es %d y la del rival es %d%nQue quieres hacer%n1. Atacar%n2. Curarte%nElecion: ",
                            vida_jugador, vida_maquina);
                    elecion = Integer.parseInt(save.readLine());
                    if (elecion == 1) {
                        vida_maquina -= (potencias[ram.nextInt(potencias.length)]);
                    } else if (elecion == 2 && pocion == true) {
                        System.out.println("Has gastado tu ultima pocion");
                        vida_jugador += 20;
                        pocion = false;
                    } else if (elecion == 2 && pocion == false) {
                        System.out.println("No te has podido curar porque no tienes pociones");
                    } else {
                        System.out.println("porfavor de 1 al 2");
                    }
                    if (vida_maquina > 0) {
                        vida_jugador -= (potencias[ram.nextInt(potencias.length)]);
                    }
                } while (vida_jugador > 0 && vida_maquina > 0);
                if (vida_jugador > 0) {
                    System.out.println("Has ganado");
                    if (contador_torneo == 1) {
                        System.out.println("Siguiente combate");
                        contador_torneo = 2;
                    } else if (contador_torneo == 2) {
                        System.out.println("Siguiente combate");
                        contador_torneo = 3;
                    } else if (contador_torneo == 3) {
                        torneo = false;
                        System.out.println("Has ganado el torneo");
                    } else {
                        System.out.println("Error en el el if de 82");
                    }
                } else {
                    System.out.println("Has perdido");
                    torneo = false;
                }
            } while (torneo == true);

        }

    }
}