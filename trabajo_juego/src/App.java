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
        BufferedReader save = new BufferedReader(new InputStreamReader(System.in));
        Random ram = new Random();

        String tipo, tipo_jugador = "", tipo_maquina;
        int elecion, contador, contador_torneo;
        Double ventaja, vida_jugador, vida_maquina;
        Boolean salir, huir, pocion, torneo;
        Double potencia = (ram.nextDouble(6.0) + 1) * 10;

        // cuerpo
        salir = false;
        huir = false;
        torneo = false;
        ventaja = 1.0;

        // vucle principal
        while (huir == false) {
            contador_torneo = 1;
            vida_jugador = 100.0;
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
            } else {
                System.out.println("error en hacer del torneo");
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
            pocion = true;
            do {
                tipo_maquina = TIPOS[ram.nextInt(TIPOS.length)];

                // calcular ventaja
                if ((tipo_jugador == "fuego" && tipo_maquina == "planta")
                        || (tipo_jugador == "planta" && tipo_maquina == "agua")
                        || ((tipo_jugador == "agua" && tipo_maquina == "fuego"))) {
                    ventaja = 1.25;
                } else if ((tipo_jugador == "planta" && tipo_maquina == "fuego")
                        || (tipo_jugador == "fuego" && tipo_maquina == "agua")
                        || ((tipo_jugador == "agua" && tipo_maquina == "planta"))) {
                    ventaja = 0.75;
                } else if (tipo_jugador == tipo_maquina) {
                    ventaja = 1.0;
                } else {
                    System.out.println("error en ventaja");
                }

                System.out.println("Tu tipo es " + tipo_jugador + " y el de tu rival es " + tipo_maquina);
                vida_maquina = 100.0;
                do {
                    System.out.printf(
                            "Tu vida es %.2f y la del rival es %.2f%nQue quieres hacer%n1. Atacar%n2. Curarte%nElecion: ",
                            vida_jugador, vida_maquina);
                    elecion = Integer.parseInt(save.readLine());
                    if (elecion == 1) {
                        vida_maquina -= potencia * ventaja;
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
                        vida_jugador -= potencia;
                    }
                } while (vida_jugador > 0 && vida_maquina > 0);

                // comprovantes
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