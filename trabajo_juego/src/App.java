/*
Autor: Jorge
Fecha: 2026/27
Descrpcion: El juego Pekemen trata de que el jugador elige un elemento para enfrentarse a la máquina en una batalla individual o en un modo torneo eliminatorio. Durante el combate, puedes atacar y otras obciones.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        // constantes
        final double CRITICO=1.1;
        final String FUEGO = "fuego";
        final String AGUA = "agua";
        final String PLANTA = "planta";
        final String[] TIPOS = { FUEGO, AGUA, PLANTA };

        // variables
        BufferedReader save = new BufferedReader(new InputStreamReader(System.in));
        Random ram = new Random();

        String tipo, tipo_jugador = "", tipo_maquina;
        int elecion, contador, contador_torneo, contador_ahogar;
        double ventaja, vida_jugador, vida_maquina;
        Boolean salir, huir, pocion_jugador,pocion_maquina, torneo, quemar, envenenar;
        Double potencia;

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
                System.out.println("Escoge un tipo entre " +TIPOS[0]+" "+ TIPOS[1]+" "+TIPOS[2]);
                tipo = save.readLine();
                for (contador = 0; contador < TIPOS.length; contador++) {
                    if (tipo.equals(TIPOS[contador])) {
                        tipo_jugador = TIPOS[contador];
                        salir = true;
                    }
                }
            } while (salir == false);

            // combate
            pocion_jugador = true;
            pocion_maquina = true;
            contador_ahogar = 9;
            quemar=false;
            envenenar=false;
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

                System.out.println("Tu tipo es "+ tipo_jugador + " y el de tu rival es " + tipo_maquina);
                vida_maquina = 100.0;

                //ataques
                do {
                    System.out.printf(
                            "Tu vida es %s %.2f y la del rival es %s %.2f%nQue quieres hacer%n1. Atacar%n2. Curarte%n3. Explotar%n4. Atacar fuerte%n5. Protejerte%n6. Efecto especial%nElecion: ",
                            "#".repeat((int) vida_jugador/10),vida_jugador,"#".repeat((int) vida_maquina/10), vida_maquina);
                    elecion = Integer.parseInt(save.readLine());
                    if (elecion == 1) {
                        if (ram.nextInt(100) < 10){
                            potencia = (ram.nextDouble(6.0) + 1) * 10;
                            vida_maquina -= potencia * ventaja * CRITICO;
                            System.out.println("Has echo un critico");
                        } else{
                            potencia = (ram.nextDouble(6.0) + 1) * 10;
                            vida_maquina -= potencia * ventaja;
                        }       
                    } else if (elecion == 2 && pocion_jugador == true) {
                        System.out.println("Has gastado tu ultima pocion");
                        vida_jugador += 20;
                        pocion_jugador = false;
                    } else if (elecion == 2 && pocion_jugador == false) {
                        System.out.println("No te has podido curar porque no tienes pociones");
                    } else if (elecion == 3) {
                        vida_maquina=0;
                        vida_jugador = vida_jugador/2;
                    } else if (elecion == 4){
                        if (ram.nextInt(100) < 40){
                            potencia = (ram.nextDouble(6.0) + 1) * 10;
                            vida_maquina -= potencia * ventaja * 2;
                        }else{
                            System.out.println("Ha fallado el ataque");
                        }
                    } else if (elecion==5){
                            System.out.println("Te has protegido");
                    } else if (elecion == 6){
                        if (tipo_jugador == FUEGO && tipo_maquina != FUEGO){
                            System.out.println("Le has quemado");
                            quemar=true;
                        } else if (tipo_jugador == PLANTA && tipo_maquina != PLANTA){
                            System.out.println("Le has envenenado");
                            envenenar = true;
                        } else if (tipo_jugador == AGUA && tipo_maquina != AGUA){
                            System.out.println("Le has ahogado");
                            contador_ahogar=0;
                        }
                    } else {
                        System.out.println("porfavor del 1 al 6");
                    }
                    if (quemar == true){
                        vida_maquina-=15;
                    } else if (envenenar == true){
                        vida_maquina-=25;
                    } else if (contador_ahogar == 0 || contador_ahogar == 1 || contador_ahogar == 2){
                        contador_ahogar++;
                    } else if (contador_ahogar == 3){
                        System.out.println("El rival se ha ahogado");
                        vida_maquina=0;
                    }
                        
                    //ataque maquina
                    if (vida_maquina > 0) {
                        if ((vida_maquina > 60 && ram.nextInt(100) < 20 && pocion_maquina == true && vida_maquina!=100)
                            || (vida_maquina < 60 && ram.nextInt(100) < 60 && pocion_maquina == true)){
                            pocion_maquina = false;
                            vida_maquina += 20;
                        } else if (vida_maquina<=10 && ram.nextInt(100) < 90 && elecion!=5){
                            vida_jugador=0;
                            vida_maquina=vida_maquina/2;
                        }else{
                            if (elecion != 5){
                                potencia = (ram.nextDouble(6.0) + 1) * 10;
                                if (quemar == true){
                                    vida_jugador -= potencia - 10;
                                }else{
                                    vida_jugador -= potencia;
                                }
                            }
                        }  
                    }
                } while (vida_jugador > 0 && vida_maquina > 0);

                // comprovantes
                if (vida_jugador > 0) {
                    System.out.println("Has ganado");
                    if (contador_torneo == 1) {
                        System.out.println("Siguiente combate");
                        contador_torneo = 2;
                        if (vida_jugador!=100)
                            vida_jugador+=20;
                    } else if (contador_torneo == 2) {
                        System.out.println("Siguiente combate");
                        contador_torneo = 3;
                        if (vida_jugador!=100)
                            vida_jugador+=20;
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