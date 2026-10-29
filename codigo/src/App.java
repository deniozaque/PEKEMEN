/*
Autor: Jorge
Fecha: 2026/27
Descripción: El juego Pekemen trata de que el jugador elige un elemento para enfrentarse a la máquina en una batalla individual o en un modo torneo eliminatorio. Durante el combate, puedes atacar y otras opciones.
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

        // variables
        BufferedReader save = new BufferedReader(new InputStreamReader(System.in));
        Random ram = new Random();

        String[][] tus_pekemens;
        tus_pekemens = new String[6][8];
        String[][] lider_agua;
        lider_agua = new String[3][8];
        String[][] pekemens_rival;
        pekemens_rival = new String[6][8];
        String[] pekemen_salvaje;
        pekemen_salvaje = new String[2];
        //pekemens:numero, nombre, tipo, vida, nivel, daño maximo, experiencia que tiene, experiencia que da.
        String[][] pekemens = {
            {"1", "Peplet", "agua", "40", "0", "2", "0", "40"},
            {"2", "Prenplet", "agua", "70", "0", "4", "0", "60"},
            {"3", "Enpeleen", "agua", "100", "0", "6", "0", "75"},

            {"4", "Terteg", "planta", "40", "0", "2", "0", "40"},
            {"5", "Tercel", "planta", "70", "0", "4", "0", "60"},
            {"6", "Terterra", "planta", "100", "0", "6", "0", "75"},

            {"7", "Chemchemp", "fuego", "40", "0", "2", "0", "40"},
            {"8", "Menferme", "fuego", "70", "0", "4", "0", "60"},
            {"9", "Enfermet", "fuego", "100", "0", "6", "0", "75"},

            {"10", "Bedef", "normal", "30", "0", "2", "0", "20"},

            {"11", "Shex", "normal", "30", "0", "2", "0", "30"},
            {"12", "Lexe", "normal", "65", "0", "4", "0", "50"},
            {"13", "Lexrrey", "normal", "85", "0", "6", "0", "60"},

            {"14", "Tepexes", "agua", "35", "0", "3", "0", "40"},
            {"15", "Mereene", "agua", "70", "0", "5", "0", "30"}
        };

        String tipo_jugador, tipo_rival, inicial_rival, equipo, cambiar;
        int eleccion, contador_ahogar, eleccion_pekemen, pekemen_rival, fuerza_maxima_jugador, fuerza_maxima_rival, eleccion_salvaje, salvaje, experiencia, posicion;
        double ventaja, vida_jugador, vida_rival;
        Boolean salir, huir, pocion_jugador,pocion_rival, quemar, envenenar, historia, lider, cambio, enfrentamiento_lider_agua, enfrentamiento_lider_fuego, enfrentamiento_lider_planta, hayOtrosDisponibles;
        Double potencia;

        // cuerpo
        salir = false;
        huir = false;
        historia = false;
        ventaja = 1.0;
        lider=false;
        enfrentamiento_lider_agua=true;
        posicion=0;
        lider_agua[0]=pekemens[13];
        lider_agua[1]=pekemens[0];
        lider_agua[2]=pekemens[14];


        // bucle principal
        while (huir == false) {
            do{
                System.out.printf("Que quieres hacer:%n1. Modo historia.%n2. Combate singular%n3. Salir%nEleccion: ");
                eleccion = Integer.parseInt(save.readLine());
                if (eleccion == 1 || eleccion == 2 || eleccion == 3){
                    switch (eleccion) {
                        case 1:
                            historia = true;
                            break;
                        case 2:
                            historia = false;
                            break;
                        case 3:
                            System.out.println("Adios");
                            huir = true;
                            break;
                        default:
                            System.out.println("error el el primer switch");
                    }
                }else{System.out.println("del 1 al 3");}
            }while (eleccion != 1 && eleccion != 2 && eleccion != 3);
            if (huir == true) {
                break;
            }
            //modo historia
            do {
                tus_pekemens = new String[6][8];
                System.out.println("Escoge un pekemen entre 1. Peplet, 2. Terteg y 3. Chemchemp");
                eleccion_pekemen = Integer.parseInt(save.readLine());
                switch (eleccion_pekemen) {
                    case 1:
                        tus_pekemens[0] = pekemens[0];
                        salir=true;
                        break;
                    case 2:
                        tus_pekemens[0] = pekemens[3];
                        salir=true;
                        break;
                    case 3:
                        tus_pekemens[0] = pekemens[6];
                        salir=true;
                        break;
                    default:
                        System.out.println("Escoge entre el 1 y el 3");
                }
            } while (salir == false);

            //pekemen del rival
            pekemen_rival = 1+ram.nextInt(2);
            if (pekemen_rival == 1){
                pekemens_rival[0] = pekemens[0];
            }else if (pekemen_rival==2){
                pekemens_rival[0] = pekemens[3];
            }else if (pekemen_rival==3){
                pekemens_rival[0] = pekemens[6];
            }else{ System.out.println("Error en pekemen del rival");}

            // combate
            pocion_jugador = true;
            pocion_rival = true;
            contador_ahogar = 9;
            quemar = false;
            enfrentamiento_lider_fuego=false;
            enfrentamiento_lider_planta=false;
            envenenar = false;
            tipo_jugador = tus_pekemens[posicion][2];
            vida_jugador = Integer.parseInt(tus_pekemens[posicion][3]);
            fuerza_maxima_jugador = Integer.parseInt(tus_pekemens[posicion][5]);

            do {
                cambio = false;
                if (lider == true){
                    if (enfrentamiento_lider_agua == true){
                        enfrentamiento_lider_agua = false;
                        enfrentamiento_lider_fuego = true;
                        pekemens_rival=lider_agua;
                    } else if( enfrentamiento_lider_agua == false && enfrentamiento_lider_fuego==true){
                        enfrentamiento_lider_fuego = false;
                        enfrentamiento_lider_planta = true;
                    } else if( enfrentamiento_lider_fuego == false && enfrentamiento_lider_planta==true){
                        enfrentamiento_lider_planta = false;
                    }

                }
                tipo_rival = pekemens_rival[0][2];
                inicial_rival = pekemens_rival[0][1];
                vida_rival = Integer.parseInt(pekemens_rival[0][3]);
                fuerza_maxima_rival = Integer.parseInt(pekemens_rival[0][5]);
                // calcular ventaja
                if ((tipo_jugador == "fuego" && tipo_rival == "planta")
                        || (tipo_jugador == "planta" && tipo_rival == "agua")
                        || ((tipo_jugador == "agua" && tipo_rival == "fuego"))) {
                    ventaja = 1.25;
                } else if ((tipo_jugador == "planta" && tipo_rival == "fuego")
                        || (tipo_jugador == "fuego" && tipo_rival == "agua")
                        || ((tipo_jugador == "agua" && tipo_rival == "planta"))) {
                    ventaja = 0.75;
                } else if (tipo_jugador == tipo_rival || tipo_jugador=="normal" || tipo_rival=="normal") {
                    ventaja = 1.0;
                } else {System.out.println("error en ventaja");}

                System.out.println("Tu tipo es "+ tipo_jugador + " y el de tu rival es " + tipo_rival+" porque tiene a "+inicial_rival);

                //ataques
                do {
                    System.out.printf(
                            "Tu vida es %s %.2f y la del rival es %s %.2f%nQue quieres hacer%n1. Atacar%n2. Curarte%n3. Explotar%n4. Atacar fuerte%n5. Protegerte%n6. Efecto especial%n7. Cambiar pekemen%nElecion: ",
                            "#".repeat((int) vida_jugador/10),vida_jugador,"#".repeat((int) vida_rival/10), vida_rival);
                    eleccion = Integer.parseInt(save.readLine());
                    switch (eleccion) {
                        case 1:
                            if (ram.nextInt(100) < 10){
                                potencia = (ram.nextDouble(fuerza_maxima_jugador) + 1) * 10;
                                vida_rival -= potencia * ventaja * CRITICO;
                                System.out.println("Has hecho un critico");
                            } else{
                                potencia = (ram.nextDouble(fuerza_maxima_jugador) + 1) * 10;
                                vida_rival -= potencia * ventaja;
                            }
                            break;
                        case 2:
                            if (pocion_jugador == true) {
                                System.out.println("Has gastado tu ultima pocion");
                                vida_jugador += 20;
                                pocion_jugador = false;
                            } else {
                                System.out.println("No te has podido curar porque no tienes pociones");
                            }
                            break;
                        case 3:
                            vida_rival=0;
                            vida_jugador = vida_jugador/2;
                            break;
                        case 4:
                            if (ram.nextInt(100) < 40){
                                potencia = (ram.nextDouble(fuerza_maxima_jugador) + 1) * 10;
                                vida_rival -= potencia * ventaja * 2;
                            }else{
                                System.out.println("Ha fallado el ataque");
                            }
                            break;
                        case 5:
                            System.out.println("Te has protegido");
                            break;
                        case 6:
                            if (tipo_jugador == FUEGO && tipo_rival != FUEGO){
                                System.out.println("Le has quemado");
                                quemar=true;
                            } else if (tipo_jugador == PLANTA && tipo_rival != PLANTA){
                                System.out.println("Le has envenenado");
                                envenenar = true;
                            } else if (tipo_jugador == AGUA && tipo_rival != AGUA){
                                System.out.println("Le has ahogado");
                                contador_ahogar=0;
                            }
                            break;
                        case 7: 
                            hayOtrosDisponibles = false;
                            for (int i = 0; i < tus_pekemens.length; i++) { 
                                if (tus_pekemens[i] != null && tus_pekemens[i][1] != null && i != posicion) {
                                    hayOtrosDisponibles = true;
                                    System.out.println("¿Quieres cambiar a " + tus_pekemens[posicion][1] + " por " + tus_pekemens[i][1] + "? (si/no)");
                                    cambiar = save.readLine(); 
                                    if (cambiar.equalsIgnoreCase("si")) { 
                                        tus_pekemens[posicion][3] = String.valueOf((int) vida_jugador);
                                        posicion = i;
                                        tipo_jugador = tus_pekemens[posicion][2];
                                        vida_jugador = Double.parseDouble(tus_pekemens[posicion][3]);
                                        fuerza_maxima_jugador = Integer.parseInt(tus_pekemens[posicion][5]);
                                        quemar = false;
                                        envenenar = false;
                                        contador_ahogar = 9;

                                        System.out.println("¡Adelante " + tus_pekemens[posicion][1] + "!");
                                        cambio = true; 
                                        break;
                                    } 
                                } 
                            }
                            if (!hayOtrosDisponibles) {
                                System.out.println("No tienes otros Pekemens en tu equipo para cambiar.");
                            }
                            break;

                        default:
                            System.out.println("Por favor, elige una opción válida del 1 al 7.");
                    }
                    if (quemar == true){
                        vida_rival-=15;
                    } else if (envenenar == true){
                        vida_rival-=25;
                    } else if (contador_ahogar == 0 || contador_ahogar == 1 || contador_ahogar == 2){
                        contador_ahogar++;
                    } else if (contador_ahogar == 3){
                        System.out.println("El rival se ha ahogado");
                        vida_rival=0;
                    }
                        
                    //ataque maquina
                    if (vida_rival > 0) {
                        if ((vida_rival >= 40 && ram.nextInt(100) < 20 && pocion_rival == true)
                            || (vida_rival < 60 && ram.nextInt(100) < 60 && pocion_rival == true)){
                            pocion_rival = false;
                            vida_rival += 20;
                        }else if ((vida_rival >= 90 && ram.nextInt(100) < 80)
                            || (vida_rival < 90 && ram.nextInt(100) <= 10)){
                            potencia = (ram.nextDouble(fuerza_maxima_rival) + 1) * 10;
                            vida_jugador -= potencia * ventaja * CRITICO;
                            System.out.println("Te han hecho un critico");
                        } else if (vida_rival<=10 && ram.nextInt(100) < 90 && eleccion!=5){
                            vida_jugador=0;
                            vida_rival=vida_rival/2;
                        }else{
                            if (eleccion != 5){
                                potencia = (ram.nextDouble(fuerza_maxima_rival) + 1) * 10;
                                if (quemar == true){
                                    vida_jugador -= potencia - 10;
                                }else{
                                    vida_jugador -= potencia;
                                }
                            }
                        }  
                    }
                } while (vida_jugador > 0 && vida_rival > 0);
                if (vida_rival > 0){
                    System.out.println("Has perdido");
                    historia=false;
                    break;
                } else if (vida_jugador > 0){
                    for (int i=0; i<pekemens_rival.length;i++){
                        if (pekemens_rival[i][3] != "0" || pekemens_rival[i][3] != null){
                            
                        }
                    }
                    System.out.println("Has ganado");
                    if (historia == false){
                        break;
                    }
                }

                //eleciones del mundo
                do {
                    System.out.println("Que quieres hacer 1. luchar contra el lider o 2. luchar contra pekemens salvajes");
                    eleccion=Integer.parseInt(save.readLine());
                    switch (eleccion) {
                        case 1:
                            lider=true;
                            break;
                        case 2:
                            salvaje=0;
                            if (ram.nextInt(100) < 60){
                                salvaje=9;
                            }else if (ram.nextInt(100) < 60){
                                salvaje=10;
                            }else if (ram.nextInt(100) < 80){
                                salvaje=11;
                            }else {
                                salvaje=12;
                            }
                            pekemen_salvaje[0]=pekemens[salvaje][1];
                            pekemen_salvaje[1]=pekemens[salvaje][7];
                            System.out.println("Has salido: " + pekemen_salvaje[0]);
                            System.out.println("Que quieres hacer matar(1) o capturar(2)");
                            eleccion_salvaje=Integer.parseInt(save.readLine());
                            switch (eleccion_salvaje){
                                case 1:
                                    for (int i = 0; i < tus_pekemens.length;i++){
                                        if (tus_pekemens[i][1] != null){
                                            experiencia = Integer.parseInt(tus_pekemens[i][6]);
                                            experiencia += Integer.parseInt(pekemen_salvaje[1]);
                                            if (experiencia >= 100 && (Integer.parseInt(tus_pekemens[i][4]) == 0)
                                                    || Integer.parseInt(tus_pekemens[i][4]) == 1
                                                    || Integer.parseInt(tus_pekemens[i][4]) == 2
                                                    || Integer.parseInt(tus_pekemens[i][4]) == 3){
                                                    experiencia -= 100;
                                                    tus_pekemens[i][4] = "" + (1 + Integer.parseInt(tus_pekemens[i][4]));
                                                    tus_pekemens[i][6] = "" + experiencia;
                                            }
                                        }
                                    }
                                    break;
                                case 2:
                                    for (int i = 0; i < tus_pekemens.length;i++){
                                        if (tus_pekemens[i][1] == null){
                                            if (ram.nextInt(100) < 60){
                                                System.out.println("Le has capturado");
                                                tus_pekemens[i] = pekemens[salvaje];
                                                break;
                                            }else {
                                                System.out.println("No le has capturado");
                                                break;
                                            }
                                        }else{
                                            System.out.println("En la posicion "+(i+1)+" esta "+tus_pekemens[i][1]+" ¿Le quieres cambiar? (si/no)");
                                            equipo=save.readLine();
                                            if (equipo.equalsIgnoreCase("si")){
                                                if (ram.nextInt(100) < 70){
                                                    System.out.println("Le has capturado");
                                                    tus_pekemens[i] = pekemens[salvaje];
                                                    break;
                                                }else {
                                                    System.out.println("No le has capturado");
                                                    break;
                                                }
                                            }else if (equipo.equalsIgnoreCase("no")){
                                            }else {System.out.println("si o no");}
                                            
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("1 o 2");
                                break;
                            }
                    }
                }while(lider == false);
            } while (historia == true);
        }
    }
}