/*
Autor: Jorge
Fecha: 2026/27
Descripción: El juego Pekemen trata de que el jugador elige un elemento para enfrentarse a la máquina en una batalla individual o en un modo historia. Durante el combate, puedes atacar y otras opciones.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        // constantes
        final double CRITICO = 1.1;
        final String FUEGO = "fuego";
        final String AGUA = "agua";
        final String PLANTA = "planta";

        // variables
        BufferedReader save = new BufferedReader(new InputStreamReader(System.in));
        Random ram = new Random();

        String[][] tusPekemens;
        tusPekemens = new String[6][8];
        String[][] liderAgua;
        liderAgua = new String[3][8];
        String[][] liderFuego;
        liderFuego = new String[3][8];
        String[][] liderPlanta;
        liderPlanta = new String[3][8];
        String[][] pekemensRival;
        pekemensRival = new String[6][8];
        String[] pekemenSalvaje;
        pekemenSalvaje = new String[2];
        // pekemens:numero, nombre, tipo, vida, nivel, daño maximo, experiencia que va
        String[][] pekemens = {
                { "1", "Peplet", "agua", "40", "0", "2", "0", "40" },
                { "2", "Prenplet", "agua", "70", "0", "4", "0", "60" },
                { "3", "Enpeleen", "agua", "100", "0", "6", "0", "75" },

                { "4", "Terteg", "planta", "40", "0", "2", "0", "40" },
                { "5", "Tercel", "planta", "70", "0", "4", "0", "60" },
                { "6", "Terterra", "planta", "100", "0", "6", "0", "75" },

                { "7", "Chemchemp", "fuego", "40", "0", "2", "0", "40" },
                { "8", "Menferme", "fuego", "70", "0", "4", "0", "60" },
                { "9", "Enfermet", "fuego", "100", "0", "6", "0", "75" },

                { "10", "Bedef", "normal", "30", "0", "2", "0", "20" },

                { "11", "Shex", "normal", "30", "0", "2", "0", "30" },
                { "12", "Lexe", "normal", "65", "0", "4", "0", "50" },
                { "13", "Lexrrey", "normal", "85", "0", "6", "0", "60" },

                { "14", "Tepexes", "agua", "35", "0", "3", "0", "35" },
                { "15", "Mereene", "agua", "70", "0", "5", "0", "50" },

                { "16", "Slegme", "fuego", "40", "0", "4", "0", "40" },
                { "17", "Megcerge", "fuego", "75", "0", "5", "0", "65" },

                { "18", "Cecnee", "planta", "45", "0", "5", "0", "45" },
                { "19", "Cecterne", "planta", "100", "0", "6", "0", "55" }
        };

        String tipoJugador, tipoRival, inicialRival, equipo, cambiar;
        int eleccion, contadorAhogar, eleccionPekemen, pekemenRival, fuerzaMaximaJugador, fuerzaMaximaRival,
                eleccionSalvaje, salvaje, experiencia, posicion, posicionRival;
        double ventaja, vidaJugador, vidaRival, potencia;
        Boolean salir, huir, pocionJugador, pocionRival, quemar, envenenar, historia, lider, cambio,
                enfrentamientoLiderAgua, enfrentamientoLiderFuego, enfrentamientoLiderPlanta, otrosDisponibles,
                perder, enfrentamientoRival;

        // cuerpo
        salir = false;
        huir = false;
        historia = false;
        ventaja = 1.0;
        lider = false;
        enfrentamientoLiderAgua = true;
        posicion = 0;
        posicionRival = 0;
        enfrentamientoRival = true;

        // bucle principal
        while (huir == false) {
            posicion = 0;
            posicionRival = 0;
            salir = false;
            do {
                System.out.printf("Que quieres hacer:%n1. Modo historia.%n2. Combate singular%n3. Salir%nEleccion: ");
                eleccion = Integer.parseInt(save.readLine());
                if (eleccion == 1 || eleccion == 2 || eleccion == 3) {
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
                } else {
                    System.out.println("del 1 al 3");
                }
            } while (eleccion != 1 && eleccion != 2 && eleccion != 3);

            if (huir == true) {
                break;
            }

            // eleccion pekemen inicial

            do {
                tusPekemens = new String[6][8];
                System.out.println("Escoge un pekemen entre 1. Peplet, 2. Terteg y 3. Chemchemp");
                eleccionPekemen = Integer.parseInt(save.readLine());
                switch (eleccionPekemen) {
                    case 1:
                        tusPekemens[0] = pekemens[0].clone();
                        salir = true;
                        break;
                    case 2:
                        tusPekemens[0] = pekemens[3].clone();
                        salir = true;
                        break;
                    case 3:
                        tusPekemens[0] = pekemens[6].clone();
                        salir = true;
                        break;
                    default:
                        System.out.println("Escoge entre el 1 y el 3");
                }
            } while (salir == false);

            // pekemen del rival
            pekemensRival = new String[6][8];
            posicionRival = 0;
            pekemenRival = 1 + ram.nextInt(3);
            if (pekemenRival == 1) {
                pekemensRival[0] = pekemens[0].clone();
            } else if (pekemenRival == 2) {
                pekemensRival[0] = pekemens[3].clone();
            } else if (pekemenRival == 3) {
                pekemensRival[0] = pekemens[6].clone();
            } else {
                System.out.println("Error en pekemen del rival");
            }

            // combate
            pocionJugador = true;
            pocionRival = true;
            contadorAhogar = 9;
            quemar = false;
            enfrentamientoLiderFuego = false;
            enfrentamientoLiderPlanta = false;
            envenenar = false;
            inicialRival = pekemensRival[posicionRival][1];

            do {
                perder = false;
                cambio = false;
                if (lider == true) {
                    if (enfrentamientoLiderAgua == true) {
                        enfrentamientoLiderAgua = false;
                        enfrentamientoLiderFuego = true;
                        liderAgua[0] = pekemens[13].clone();
                        liderAgua[1] = pekemens[0].clone();
                        liderAgua[2] = pekemens[14].clone();
                        pekemensRival = liderAgua;
                        posicionRival = 0;
                    } else if (enfrentamientoLiderAgua == false && enfrentamientoLiderFuego == true) {
                        enfrentamientoLiderFuego = false;
                        enfrentamientoLiderPlanta = true;
                        liderFuego[0] = pekemens[7].clone();
                        liderFuego[1] = pekemens[15].clone();
                        liderFuego[2] = pekemens[16].clone();
                        pekemensRival = liderFuego;
                        posicionRival = 0;
                    } else if (enfrentamientoLiderFuego == false && enfrentamientoLiderPlanta == true) {
                        enfrentamientoLiderPlanta = false;
                        liderPlanta[0] = pekemens[5].clone();
                        liderPlanta[1] = pekemens[17].clone();
                        liderPlanta[2] = pekemens[18].clone();
                        pekemensRival = liderPlanta;
                        posicionRival = 0;
                    } else if (enfrentamientoLiderPlanta == false && enfrentamientoRival == true) {
                        pekemensRival = new String[6][8];
                        pekemensRival[0] = pekemens[9].clone();
                        pekemensRival[1] = pekemens[12].clone();
                        pekemensRival[2] = pekemens[14].clone();
                        pekemensRival[3] = pekemens[16].clone();
                        pekemensRival[4] = pekemens[18].clone();
                        pekemensRival[5] = pekemens[(pekemenRival + 1)].clone();
                        posicionRival = 0;
                        enfrentamientoRival = false;
                    }
                }

                tipoJugador = tusPekemens[posicion][2];
                vidaJugador = Double.parseDouble(tusPekemens[posicion][3]);
                fuerzaMaximaJugador = Integer.parseInt(tusPekemens[posicion][5]);
                tipoRival = pekemensRival[posicionRival][2];
                vidaRival = Double.parseDouble(pekemensRival[posicionRival][3]);
                fuerzaMaximaRival = Integer.parseInt(pekemensRival[posicionRival][5]);

                // calcular ventaja
                if ((tipoJugador.equals("fuego") && tipoRival.equals("planta"))
                        || (tipoJugador.equals("planta") && tipoRival.equals("agua"))
                        || (tipoJugador.equals("agua") && tipoRival.equals("fuego"))) {
                    ventaja = 1.25;
                } else if ((tipoJugador.equals("planta") && tipoRival.equals("fuego"))
                        || (tipoJugador.equals("fuego") && tipoRival.equals("agua"))
                        || (tipoJugador.equals("agua") && tipoRival.equals("planta"))) {
                    ventaja = 0.75;
                } else if (tipoJugador == tipoRival || tipoJugador == "normal" || tipoRival == "normal") {
                    ventaja = 1.0;
                } else {
                    System.out.println("error en ventaja");
                }

                System.out.println("Tu tipo es " + tipoJugador + " y el de tu rival es " + tipoRival
                        + " porque tiene a " + inicialRival);

                // ataques
                do {
                    System.out.printf(
                            "Tu vida es %s %.2f y la del rival es %s %.2f%nQue quieres hacer%n1. Atacar%n2. Curarte%n3. Explotar%n4. Atacar fuerte%n5. Protegerte%n6. Efecto especial%n7. Cambiar pekemen%nElecion: ",
                            "#".repeat((int) vidaJugador / 10), vidaJugador, "#".repeat((int) vidaRival / 10),
                            vidaRival);
                    eleccion = Integer.parseInt(save.readLine());
                    switch (eleccion) {
                        case 1:
                            if (ram.nextInt(100) < 10) {
                                potencia = (ram.nextDouble(fuerzaMaximaJugador) + 1) * 10;
                                vidaRival -= potencia * ventaja * CRITICO;
                                System.out.println("Has hecho un critico");
                            } else {
                                potencia = (ram.nextDouble(fuerzaMaximaJugador) + 1) * 10;
                                vidaRival -= potencia * ventaja;
                            }
                            break;
                        case 2:
                            if (pocionJugador == true) {
                                System.out.println("Has gastado tu ultima pocion");
                                vidaJugador += 20;
                                pocionJugador = false;
                            } else {
                                System.out.println("No te has podido curar porque no tienes pociones");
                            }
                            break;
                        case 3:
                            vidaRival = 0;
                            vidaJugador = vidaJugador / 2;
                            break;
                        case 4:
                            if (ram.nextInt(100) < 40) {
                                potencia = (ram.nextDouble(fuerzaMaximaJugador) + 1) * 10;
                                vidaRival -= potencia * ventaja * 2;
                            } else {
                                System.out.println("Ha fallado el ataque");
                            }
                            break;
                        case 5:
                            System.out.println("Te has protegido");
                            break;
                        case 6:
                            if (tipoJugador == FUEGO && tipoRival != FUEGO) {
                                System.out.println("Le has quemado");
                                quemar = true;
                            } else if (tipoJugador == PLANTA && tipoRival != PLANTA) {
                                System.out.println("Le has envenenado");
                                envenenar = true;
                            } else if (tipoJugador == AGUA && tipoRival != AGUA) {
                                System.out.println("Le has ahogado");
                                contadorAhogar = 0;
                            }
                            break;
                        case 7:
                            otrosDisponibles = false;
                            for (int i = 0; i < tusPekemens.length; i++) {
                                if (tusPekemens[i] != null && tusPekemens[i][1] != null && i != posicion
                                        && Double.parseDouble(tusPekemens[i][3]) > 0) {
                                    otrosDisponibles = true;
                                    System.out.println("¿Quieres cambiar a " + tusPekemens[posicion][1] + " por "
                                            + tusPekemens[i][1] + " (Vida: " + tusPekemens[i][3] + ")? (si/no)");
                                    cambiar = save.readLine();
                                    if (cambiar.equalsIgnoreCase("si")) {
                                        tusPekemens[posicion][3] = String.valueOf((int) Math.max(0, vidaJugador));
                                        posicion = i;
                                        tipoJugador = tusPekemens[posicion][2];
                                        vidaJugador = Double.parseDouble(tusPekemens[posicion][3]);
                                        fuerzaMaximaJugador = Integer.parseInt(tusPekemens[posicion][5]);
                                        quemar = false;
                                        envenenar = false;
                                        contadorAhogar = 9;
                                        if ((tipoJugador.equals("fuego") && tipoRival.equals("planta"))
                                                || (tipoJugador.equals("planta") && tipoRival.equals("agua"))
                                                || (tipoJugador.equals("agua") && tipoRival.equals("fuego"))) {
                                            ventaja = 1.25;
                                        } else if ((tipoJugador.equals("planta") && tipoRival.equals("fuego"))
                                                || (tipoJugador.equals("fuego") && tipoRival.equals("agua"))
                                                || (tipoJugador.equals("agua") && tipoRival.equals("planta"))) {
                                            ventaja = 0.75;
                                        } else if (tipoJugador == tipoRival || tipoJugador == "normal" || tipoRival == "normal") {
                                            ventaja = 1.0;
                                        } else {
                                            System.out.println("error en ventaja");
                                        }

                                        System.out.println("¡Adelante " + tusPekemens[posicion][1] + "!");
                                        cambio = true;
                                        break;
                                    }
                                
                                }
                            }
                            if (!otrosDisponibles) {
                                System.out.println("No tienes otros Pekemens disponibles con vida para cambiar.");
                            }
                            break;
                        default:
                            System.out.println("Por favor, elige una opción válida del 1 al 7.");
                    }

                    if (quemar == true) {
                        vidaRival -= 15;
                    } else if (envenenar == true) {
                        vidaRival -= 25;
                    } else if (contadorAhogar == 0 || contadorAhogar == 1 || contadorAhogar == 2) {
                        contadorAhogar++;
                    } else if (contadorAhogar == 3) {
                        System.out.println("El rival se ha ahogado");
                        vidaRival = 0;
                    }

                    // ataque maquina
                    if (vidaRival > 0) {
                        if ((vidaRival >= 40 && ram.nextInt(100) < 20 && pocionRival == true)
                                || (vidaRival < 60 && ram.nextInt(100) < 60 && pocionRival == true)) {
                            pocionRival = false;
                            vidaRival += 20;
                        } else if ((vidaRival >= 90 && ram.nextInt(100) < 80)
                                || (vidaRival < 90 && ram.nextInt(100) <= 10)) {
                            potencia = (ram.nextDouble(fuerzaMaximaRival) + 1) * 10;
                            vidaJugador -= potencia * ventaja * CRITICO;
                            System.out.println("Te han hecho un critico");
                        } else if (vidaRival <= 10 && ram.nextInt(100) < 90 && eleccion != 5) {
                            vidaJugador = 0;
                            vidaRival = vidaRival / 2;
                        } else {
                            if (eleccion != 5) {
                                potencia = (ram.nextDouble(fuerzaMaximaRival) + 1) * 10;
                                if (quemar == true) {
                                    vidaJugador -= potencia - 10;
                                } else {
                                    vidaJugador -= potencia;
                                }
                            }
                        }
                    }

                    if (vidaJugador <= 0) {
                        System.out.println("¡Tu " + tusPekemens[posicion][1] + " ha sido derrotado!");
                        tusPekemens[posicion][3] = "0";

                        boolean quedanVivos = false;
                        for (int i = 0; i < tusPekemens.length; i++) {
                            if (tusPekemens[i] != null && tusPekemens[i][1] != null && tusPekemens[i][3] != null) {
                                if (Double.parseDouble(tusPekemens[i][3]) > 0) {
                                    quedanVivos = true;
                                    break;
                                }
                            }
                        }

                        if (!quedanVivos) {
                            perder = true;
                        } else {
                            boolean cambiado = false;
                            while (!cambiado) {
                                for (int i = 0; i < tusPekemens.length; i++) {
                                    if (tusPekemens[i] != null && tusPekemens[i][1] != null && i != posicion
                                            && Double.parseDouble(tusPekemens[i][3]) > 0) {
                                        System.out.println("¿Quieres sacar a " + tusPekemens[i][1] + " (Vida: "
                                                + tusPekemens[i][3] + ")? (si/no)");
                                        cambiar = save.readLine();
                                        if (cambiar.equalsIgnoreCase("si")) {
                                            posicion = i;
                                            tipoJugador = tusPekemens[posicion][2];
                                            vidaJugador = Double.parseDouble(tusPekemens[posicion][3]);
                                            fuerzaMaximaJugador = Integer.parseInt(tusPekemens[posicion][5]);
                                            quemar = false;
                                            envenenar = false;
                                            contadorAhogar = 9;

                                            if ((tipoJugador.equals("fuego") && tipoRival.equals("planta"))
                                                    || (tipoJugador.equals("planta") && tipoRival.equals("agua"))
                                                    || (tipoJugador.equals("agua") && tipoRival.equals("fuego"))) {
                                                ventaja = 1.25;
                                            } else if ((tipoJugador.equals("planta") && tipoRival.equals("fuego"))
                                                    || (tipoJugador.equals("fuego") && tipoRival.equals("agua"))
                                                    || (tipoJugador.equals("agua") && tipoRival.equals("planta"))) {
                                                ventaja = 0.75;
                                            } else if (tipoJugador == tipoRival || tipoJugador == "normal" || tipoRival == "normal") {
                                                ventaja = 1.0;
                                            } else {
                                                System.out.println("error en ventaja");
                                            }

                                            System.out.println("¡Adelante " + tusPekemens[posicion][1] + "!");
                                            cambio = true;
                                            cambiado = true;
                                            break;
                                        }
                                    }
                                }
                                if (!cambiado) {
                                    System.out.println("Debes elegir un Pekemen con vida para continuar el combate.");
                                }
                            }
                            perder = false;
                        }
                    } else if (vidaRival <= 0) {
                        System.out
                                .println("¡El " + pekemensRival[posicionRival][1] + " del rival ha sido derrotado!");
                        pekemensRival[posicionRival][3] = "0";
                        boolean rivalQuedanVivos = false;
                        int siguienteRival = -1;
                        for (int i = 0; i < pekemensRival.length; i++) {
                            if (pekemensRival[i] != null && pekemensRival[i][1] != null
                                    && pekemensRival[i][3] != null) {
                                if (Double.parseDouble(pekemensRival[i][3]) > 0) {
                                    rivalQuedanVivos = true;
                                    siguienteRival = i;
                                    break;
                                }
                            }
                        }

                        if (!rivalQuedanVivos) {
                            perder = true;
                        } else {
                            posicionRival = siguienteRival;
                            tipoRival = pekemensRival[posicionRival][2];
                            inicialRival = pekemensRival[posicionRival][1];
                            vidaRival = Double.parseDouble(pekemensRival[posicionRival][3]);
                            fuerzaMaximaRival = Integer.parseInt(pekemensRival[posicionRival][5]);
                            pocionRival = true;
                            quemar = false;
                            envenenar = false;
                            contadorAhogar = 9;
                            if ((tipoJugador.equals("fuego") && tipoRival.equals("planta"))
                                    || (tipoJugador.equals("planta") && tipoRival.equals("agua"))
                                    || (tipoJugador.equals("agua") && tipoRival.equals("fuego"))) {
                                ventaja = 1.25;
                            } else if ((tipoJugador.equals("planta") && tipoRival.equals("fuego"))
                                    || (tipoJugador.equals("fuego") && tipoRival.equals("agua"))
                                    || (tipoJugador.equals("agua") && tipoRival.equals("planta"))) {
                                ventaja = 0.75;
                            } else if (tipoJugador.equals(tipoRival) || tipoJugador.equals("normal") || tipoRival.equals("normal")) {
                                ventaja = 1.0;
                            } else {
                                System.out.println("error en ventaja");
                            }

                            System.out.println("¡El rival envía a " + inicialRival + "!");
                            perder = false;
                        }
                    }
                } while (perder == false);

                if (vidaJugador <= 0) {
                    System.out.println("Has perdido el combate");
                    historia = false;
                    break;
                } else if (vidaRival <= 0) {
                    System.out.println("Has ganado");
                    lider = false;
                    if (historia == false) {
                        break;
                    }
                }
                if (enfrentamientoRival == false) {
                    break;
                }

                // elecciones del mundo
                do {
                    System.out.println(
                            "Que quieres hacer 1. luchar contra el lider o 2. luchar contra pekemens salvajes");
                    eleccion = Integer.parseInt(save.readLine());
                    switch (eleccion) {
                        case 1:
                            lider = true;
                            break;
                        case 2:
                            salvaje = 0;
                            if (ram.nextInt(100) < 60) {
                                salvaje = 9;
                            } else if (ram.nextInt(100) < 60) {
                                salvaje = 10;
                            } else if (ram.nextInt(100) < 80) {
                                salvaje = 11;
                            } else {
                                salvaje = 12;
                            }
                            pekemenSalvaje[0] = pekemens[salvaje][1];
                            pekemenSalvaje[1] = pekemens[salvaje][7];
                            System.out.println("Has salido: " + pekemenSalvaje[0]);
                            System.out.println("Que quieres hacer matar(1) o capturar(2)");
                            eleccionSalvaje = Integer.parseInt(save.readLine());
                            switch (eleccionSalvaje) {
                                case 1:
                                    for (int i = 0; i < tusPekemens.length; i++) {
                                        if (tusPekemens[i] != null && tusPekemens[i][1] != null) {
                                            experiencia = Integer.parseInt(tusPekemens[i][6]);
                                            experiencia += Integer.parseInt(pekemenSalvaje[1]);
                                            if (experiencia >= 100 && (Integer.parseInt(tusPekemens[i][4]) == 0
                                                    || Integer.parseInt(tusPekemens[i][4]) == 1
                                                    || Integer.parseInt(tusPekemens[i][4]) == 2
                                                    || Integer.parseInt(tusPekemens[i][4]) == 3)) {
                                                experiencia -= 100;
                                                tusPekemens[i][4] = "" + (1 + Integer.parseInt(tusPekemens[i][4]));
                                                tusPekemens[i][6] = "" + experiencia;
                                            }
                                        }
                                    }
                                    break;
                                case 2:
                                    for (int i = 0; i < tusPekemens.length; i++) {
                                        if (tusPekemens[i][1] == null) {
                                            if (ram.nextInt(100) < 60) {
                                                System.out.println("Le has capturado");
                                                tusPekemens[i] = pekemens[salvaje].clone();
                                                break;
                                            } else {
                                                System.out.println("No le has capturado");
                                                break;
                                            }
                                        } else {
                                            System.out.println("En la posicion " + (i + 1) + " esta "
                                                    + tusPekemens[i][1] + " ¿Le quieres cambiar? (si/no)");
                                            equipo = save.readLine();
                                            if (equipo.equalsIgnoreCase("si")) {
                                                if (ram.nextInt(100) < 70) {
                                                    System.out.println("Le has capturado");
                                                    tusPekemens[i] = pekemens[salvaje].clone();
                                                    break;
                                                } else {
                                                    System.out.println("No le has capturado");
                                                    break;
                                                }
                                            } else if (equipo.equalsIgnoreCase("no")) {
                                            } else {
                                                System.out.println("si o no");
                                            }
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("1 o 2");
                                    break;
                            }
                    }
                } while (lider == false);
            } while (historia == true);
        }
    }
}