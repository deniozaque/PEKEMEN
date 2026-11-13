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
        final String NORMAL = "normal";
        final int NUMERO = 0;
        final int NOMBRE = 1;
        final int TIPO = 2;
        final int VIDA = 3;
        final int NIVEL = 4;
        final int DANHO = 5;
        final int EXPERIENCIA = 6;

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
        String[] pekemenSalvaje;
        pekemenSalvaje = new String[2];
        // pekemens:numero, nombre, tipo, vida, nivel, daño maximo, experiencia que va
        String[][] pekemens = {
                { "1", "Peplet", AGUA, "40", "0", "2", "0", "40" },
                { "2", "Prenplet", AGUA, "70", "0", "4", "0", "60" },
                { "3", "Enpeleen", AGUA, "100", "0", "6", "0", "75" },

                { "4", "Terteg", PLANTA, "40", "0", "2", "0", "40" },
                { "5", "Tercel", PLANTA, "70", "0", "4", "0", "60" },
                { "6", "Terterra", PLANTA, "100", "0", "6", "0", "75" },

                { "7", "Chemchemp", FUEGO, "40", "0", "2", "0", "40" },
                { "8", "Menferme", FUEGO, "70", "0", "4", "0", "60" },
                { "9", "Enfermet", FUEGO, "100", "0", "6", "0", "75" },

                { "10", "Bedef", NORMAL, "30", "0", "2", "0", "20" },

                { "11", "Shex", NORMAL, "30", "0", "2", "0", "30" },
                { "12", "Lexe", NORMAL, "65", "0", "4", "0", "50" },
                { "13", "Lexrrey", NORMAL, "85", "0", "6", "0", "60" },

                { "14", "Tepexes", AGUA, "35", "0", "3", "0", "35" },
                { "15", "Mereene", AGUA, "70", "0", "5", "0", "50" },

                { "16", "Slegme", FUEGO, "40", "0", "4", "0", "40" },
                { "17", "Megcerge", FUEGO, "75", "0", "5", "0", "65" },

                { "18", "Cecnee", PLANTA, "45", "0", "5", "0", "45" },
                { "19", "Cecterne", PLANTA, "100", "0", "6", "0", "55" }
        };

        String tipoJugador, tipoRival, inicialRival, equipo, cambiar;
        int eleccion, contadorAhogar, eleccionPekemen, pekemenRival, fuerzaMaximaJugador, fuerzaMaximaRival,
                eleccionSalvaje, salvaje, experiencia, posicion, posicionRival, contador;
        double ventaja, vidaJugador, vidaRival, potencia;
        boolean salir, huir, pocionJugador, pocionRival, quemar, envenenar, historia, lider,
                enfrentamientoLiderAgua, enfrentamientoLiderFuego, enfrentamientoLiderPlanta, otrosDisponibles,
                perder, enfrentamientoRival;

        // cuerpo
        huir = false;
        historia = false;
        ventaja = 1.0;
        lider = false;
        enfrentamientoLiderAgua = true;
        enfrentamientoRival = true;

        // bucle principal
        while (huir == false) {
            posicion = 0;
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
            enfrentamientoLiderAgua = true;

            do {
                tusPekemens = new String[6][8];
                System.out.println("Escoge un pekemen entre 1. Peplet, 2. Terteg y 3. Chemchemp");
                eleccionPekemen = Integer.parseInt(save.readLine());
                switch (eleccionPekemen) {
                    case 1:
                        tusPekemens[NUMERO] = pekemens[0].clone();
                        salir = true;
                        break;
                    case 2:
                        tusPekemens[NUMERO] = pekemens[3].clone();
                        salir = true;
                        break;
                    case 3:
                        tusPekemens[NUMERO] = pekemens[6].clone();
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
            switch (pekemenRival) {
                case 1:
                    pekemensRival[NUMERO] = pekemens[0].clone();
                    break;
                case 2:
                    pekemensRival[NUMERO] = pekemens[3].clone();
                    break;
                case 3:
                    pekemensRival[NUMERO] = pekemens[6].clone();
                    break;
                default:
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
            inicialRival = pekemensRival[posicionRival][NOMBRE];

            do {
                perder = false;
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

                tipoJugador = tusPekemens[posicion][TIPO];
                vidaJugador = Double.parseDouble(tusPekemens[posicion][VIDA]);
                fuerzaMaximaJugador = Integer.parseInt(tusPekemens[posicion][DANHO]);
                tipoRival = pekemensRival[posicionRival][TIPO];
                vidaRival = Double.parseDouble(pekemensRival[posicionRival][VIDA]);
                fuerzaMaximaRival = Integer.parseInt(pekemensRival[posicionRival][DANHO]);

                // calcular ventaja
                if ((tipoJugador.equals(FUEGO) && tipoRival.equals(PLANTA))
                        || (tipoJugador.equals(PLANTA) && tipoRival.equals(AGUA))
                        || (tipoJugador.equals(AGUA) && tipoRival.equals(FUEGO))) {
                    ventaja = 1.25;
                } else if ((tipoJugador.equals(PLANTA) && tipoRival.equals(FUEGO))
                        || (tipoJugador.equals(FUEGO) && tipoRival.equals(AGUA))
                        || (tipoJugador.equals(AGUA) && tipoRival.equals(PLANTA))) {
                    ventaja = 0.75;
                } else if (tipoJugador.equals(tipoRival) || tipoJugador.equals(NORMAL) || tipoRival.equals(NORMAL)) {
                    ventaja = 1.0;
                } else {
                    System.out.println("error en ventaja");
                }

                System.out.println("Tu tipo es " + tipoJugador + " y el de tu rival es " + tipoRival
                        + " porque tiene a " + inicialRival);

                // ataques
                do {
                    salir = false;
                    do {
                        System.out.printf(
                                "Tu vida es %s %.2f y la del rival es %s %.2f%nQue quieres hacer%n1. Atacar%n2. Curarte%n3. Explotar%n4. Atacar fuerte%n5. Protegerte%n6. Efecto especial%n7. Cambiar pekemen%nEleción: ",
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
                                salir = true;
                                break;
                            case 2:
                                if (pocionJugador == true) {
                                    System.out.println("Has gastado tu ultima poción");
                                    vidaJugador += 20;
                                    pocionJugador = false;
                                } else {
                                    System.out.println("No te has podido curar porque no tienes pociones");
                                }
                                salir = true;
                                break;
                            case 3:
                                vidaRival = 0;
                                vidaJugador = vidaJugador / 2;
                                salir = true;
                                break;
                            case 4:
                                if (ram.nextInt(100) < 40) {
                                    potencia = (ram.nextDouble(fuerzaMaximaJugador) + 1) * 10;
                                    vidaRival -= potencia * ventaja * 2;
                                } else {
                                    System.out.println("Ha fallado el ataque");
                                }
                                salir = true;
                                break;
                            case 5:
                                System.out.println("Te has protegido");
                                salir = true;
                                break;
                            case 6:
                                if (tipoJugador.equals(FUEGO) && !tipoRival.equals(FUEGO)) {
                                    System.out.println("Le has quemado");
                                    quemar = true;
                                } else if (tipoJugador.equals(PLANTA) && !tipoRival.equals(PLANTA)) {
                                    System.out.println("Le has envenenado");
                                    envenenar = true;
                                } else if (tipoJugador.equals(AGUA) && !tipoRival.equals(AGUA)) {
                                    System.out.println("Le has ahogado");
                                    contadorAhogar = 0;
                                }
                                salir = true;
                                break;
                            case 7:
                                otrosDisponibles = false;
                                contador = 0;
                                while (contador < tusPekemens.length) {
                                    if (tusPekemens[contador] != null && tusPekemens[contador][NOMBRE] != null
                                            && contador != posicion
                                            && Double.parseDouble(tusPekemens[contador][VIDA]) > 0) {
                                        otrosDisponibles = true;
                                        System.out
                                                .println("¿Quieres cambiar a " + tusPekemens[posicion][NOMBRE] + " por "
                                                        + tusPekemens[contador][NOMBRE] + " (Vida: "
                                                        + tusPekemens[contador][VIDA] + ")? (si/no)");
                                        cambiar = save.readLine();
                                        if (cambiar.equalsIgnoreCase("si")) {
                                            tusPekemens[posicion][VIDA] = String
                                                    .valueOf((int) Math.max(0, vidaJugador));
                                            posicion = contador;
                                            tipoJugador = tusPekemens[posicion][TIPO];
                                            vidaJugador = Double.parseDouble(tusPekemens[posicion][VIDA]);
                                            fuerzaMaximaJugador = Integer.parseInt(tusPekemens[posicion][DANHO]);
                                            quemar = false;
                                            envenenar = false;
                                            contadorAhogar = 9;
                                            if ((tipoJugador.equals(FUEGO) && tipoRival.equals(PLANTA))
                                                    || (tipoJugador.equals(PLANTA) && tipoRival.equals(AGUA))
                                                    || (tipoJugador.equals(AGUA) && tipoRival.equals(FUEGO))) {
                                                ventaja = 1.25;
                                            } else if ((tipoJugador.equals(PLANTA) && tipoRival.equals(FUEGO))
                                                    || (tipoJugador.equals(FUEGO) && tipoRival.equals(AGUA))
                                                    || (tipoJugador.equals(AGUA) && tipoRival.equals(PLANTA))) {
                                                ventaja = 0.75;
                                            } else if (tipoJugador.equals(tipoRival) || tipoJugador.equals(NORMAL)
                                                    || tipoRival.equals(NORMAL)) {
                                                ventaja = 1.0;
                                            } else {
                                                System.out.println("error en ventaja");
                                            }

                                            System.out.println("¡Adelante " + tusPekemens[posicion][NOMBRE] + "!");
                                            break;
                                        }

                                    }
                                    contador++;
                                }
                                if (!otrosDisponibles) {
                                    System.out.println("No tienes otros Pekemens disponibles con vida para cambiar.");
                                }
                                salir = true;
                                break;
                            default:
                                System.out.println("Por favor, elige una opción válida del 1 al 7.");
                        }
                    } while (salir == false);

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
                        System.out.println("¡Tu " + tusPekemens[posicion][NOMBRE] + " ha sido derrotado!");
                        tusPekemens[posicion][VIDA] = "0";

                        boolean quedanVivos = false;
                        contador = 0;
                        while (contador < tusPekemens.length) {
                            if (tusPekemens[contador] != null && tusPekemens[contador][NOMBRE] != null
                                    && tusPekemens[contador][VIDA] != null) {
                                if (Double.parseDouble(tusPekemens[contador][VIDA]) > 0) {
                                    quedanVivos = true;
                                    break;
                                }
                            }
                            contador++;
                        }

                        if (!quedanVivos) {
                            perder = true;
                        } else {
                            boolean cambiado = false;
                            while (!cambiado) {
                                contador = 0;
                                while (contador < tusPekemens.length) {
                                    if (tusPekemens[contador] != null && tusPekemens[contador][NOMBRE] != null
                                            && contador != posicion
                                            && Double.parseDouble(tusPekemens[contador][VIDA]) > 0) {
                                        System.out.println(
                                                "¿Quieres sacar a " + tusPekemens[contador][NOMBRE] + " (Vida: "
                                                        + tusPekemens[contador][VIDA] + ")? (si/no)");
                                        cambiar = save.readLine();
                                        if (cambiar.equalsIgnoreCase("si")) {
                                            posicion = contador;
                                            tipoJugador = tusPekemens[posicion][TIPO];
                                            vidaJugador = Double.parseDouble(tusPekemens[posicion][VIDA]);
                                            fuerzaMaximaJugador = Integer.parseInt(tusPekemens[posicion][DANHO]);
                                            quemar = false;
                                            envenenar = false;
                                            contadorAhogar = 9;

                                            if ((tipoJugador.equals(FUEGO) && tipoRival.equals(PLANTA))
                                                    || (tipoJugador.equals(PLANTA) && tipoRival.equals(AGUA))
                                                    || (tipoJugador.equals(AGUA) && tipoRival.equals(FUEGO))) {
                                                ventaja = 1.25;
                                            } else if ((tipoJugador.equals(PLANTA) && tipoRival.equals(FUEGO))
                                                    || (tipoJugador.equals(FUEGO) && tipoRival.equals(AGUA))
                                                    || (tipoJugador.equals(AGUA) && tipoRival.equals(PLANTA))) {
                                                ventaja = 0.75;
                                            } else if (tipoJugador.equals(tipoRival) || tipoJugador.equals(NORMAL)
                                                    || tipoRival.equals(NORMAL)) {
                                                ventaja = 1.0;
                                            } else {
                                                System.out.println("error en ventaja");
                                            }

                                            System.out.println("¡Adelante " + tusPekemens[posicion][NOMBRE] + "!");
                                            cambiado = true;
                                            break;
                                        }
                                    }
                                    contador++;
                                }
                                if (!cambiado) {
                                    System.out.println("Debes elegir un Pekemen con vida para continuar el combate.");
                                }
                            }
                            perder = false;
                        }
                    } else if (vidaRival <= 0) {
                        System.out
                                .println("¡El " + pekemensRival[posicionRival][NOMBRE]
                                        + " del rival ha sido derrotado!");
                        pekemensRival[posicionRival][VIDA] = "0";
                        boolean rivalQuedanVivos = false;
                        int siguienteRival = -1;
                        contador = 0;
                        while (contador < pekemensRival.length) {
                            if (pekemensRival[contador] != null && pekemensRival[contador][NOMBRE] != null
                                    && pekemensRival[contador][VIDA] != null) {
                                if (Double.parseDouble(pekemensRival[contador][VIDA]) > 0) {
                                    rivalQuedanVivos = true;
                                    siguienteRival = contador;
                                    break;
                                }
                            }
                            contador++;
                        }

                        if (!rivalQuedanVivos) {
                            perder = true;
                        } else {
                            posicionRival = siguienteRival;
                            tipoRival = pekemensRival[posicionRival][TIPO];
                            inicialRival = pekemensRival[posicionRival][NOMBRE];
                            vidaRival = Double.parseDouble(pekemensRival[posicionRival][VIDA]);
                            fuerzaMaximaRival = Integer.parseInt(pekemensRival[posicionRival][DANHO]);
                            pocionRival = true;
                            quemar = false;
                            envenenar = false;
                            contadorAhogar = 9;
                            if ((tipoJugador.equals(FUEGO) && tipoRival.equals(PLANTA))
                                    || (tipoJugador.equals(PLANTA) && tipoRival.equals(AGUA))
                                    || (tipoJugador.equals(AGUA) && tipoRival.equals(FUEGO))) {
                                ventaja = 1.25;
                            } else if ((tipoJugador.equals(PLANTA) && tipoRival.equals(FUEGO))
                                    || (tipoJugador.equals(FUEGO) && tipoRival.equals(AGUA))
                                    || (tipoJugador.equals(AGUA) && tipoRival.equals(PLANTA))) {
                                ventaja = 0.75;
                            } else if (tipoJugador.equals(tipoRival) || tipoJugador.equals(NORMAL)
                                    || tipoRival.equals(NORMAL)) {
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
                            if (ram.nextInt(100) < 60) {
                                salvaje = 9;
                            } else if (ram.nextInt(100) < 60) {
                                salvaje = 10;
                            } else if (ram.nextInt(100) < 80) {
                                salvaje = 11;
                            } else {
                                salvaje = 12;
                            }
                            pekemenSalvaje[0] = pekemens[salvaje][NOMBRE];
                            pekemenSalvaje[1] = pekemens[salvaje][EXPERIENCIA];
                            System.out.println("Has salido: " + pekemenSalvaje[0]);
                            System.out.println("Que quieres hacer matar(1) o capturar(2)");
                            eleccionSalvaje = Integer.parseInt(save.readLine());
                            switch (eleccionSalvaje) {
                                case 1:
                                    for (int i = 0; i < tusPekemens.length; i++) {
                                        if (tusPekemens[i] != null && tusPekemens[i][NOMBRE] != null) {
                                            experiencia = Integer.parseInt(tusPekemens[i][EXPERIENCIA]);
                                            experiencia += Integer.parseInt(pekemenSalvaje[1]);
                                            if (experiencia >= 100 && (Integer.parseInt(tusPekemens[i][NIVEL]) == 0
                                                    || Integer.parseInt(tusPekemens[i][NIVEL]) == 1
                                                    || Integer.parseInt(tusPekemens[i][NIVEL]) == 2
                                                    || Integer.parseInt(tusPekemens[i][NIVEL]) == 3)) {
                                                experiencia -= 100;
                                                tusPekemens[i][NIVEL] = ""
                                                        + (1 + Integer.parseInt(tusPekemens[i][NIVEL]));
                                                tusPekemens[i][EXPERIENCIA] = "" + experiencia;
                                            }
                                        }
                                    }
                                    break;
                                case 2:
                                    contador = 0;
                                    while (contador < tusPekemens.length) {
                                        if (tusPekemens[contador][NOMBRE] == null) {
                                            if (ram.nextInt(100) < 60) {
                                                System.out.println("Le has capturado");
                                                tusPekemens[contador] = pekemens[salvaje].clone();
                                                break;
                                            } else {
                                                System.out.println("No le has capturado");
                                                break;
                                            }
                                        } else {
                                            System.out.println("En la posicion " + (contador + 1) + " esta "
                                                    + tusPekemens[contador][NOMBRE] + " ¿Le quieres cambiar? (si/no)");
                                            equipo = save.readLine();
                                            if (equipo.equalsIgnoreCase("si")) {
                                                if (ram.nextInt(100) < 70) {
                                                    System.out.println("Le has capturado");
                                                    tusPekemens[contador] = pekemens[salvaje].clone();
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
                                        contador++;
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