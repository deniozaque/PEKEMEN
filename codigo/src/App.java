/*
Autor: Jorge
Fecha: 2026/27
Descripción: El juego Pekemen trata de que el jugador elige un elemento para enfrentarse a la máquina en una batalla individual o en un modo historia. Durante el combate, puedes atacar y otras opciones.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class App {
    static BufferedReader save = new BufferedReader(new InputStreamReader(System.in));
    static Random ram = new Random();

    // Constantes
    public static final String FUEGO = "fuego";
    public static final String AGUA = "agua";
    public static final String PLANTA = "planta";
    public static final String NORMAL = "normal";

    public static final int NUMERO = 0;
    public static final int NOMBRE = 1;
    public static final int TIPO = 2;
    public static final int VIDA = 3;
    public static final int NIVEL = 4;
    public static final int DANHO = 5;
    public static final int EXPERIENCIA = 6;
    public static final int EXP_DADA = 7;

    // funciones
    public static void manual() throws Exception {
        String opcion, opcion_modo;

        do {
            System.out.printf("¿De qué quieres preguntar?%n1. Modo historia 2. Modo un combate 3. Salir%n");
            opcion = save.readLine();
            switch (opcion) {
                case "1":
                    System.out.printf(
                            "Primero escoges a tu inicial entre 3. Este modo son 5 combates, entre combate y combate puedes atrapar o matar pekemens salvajes%n");
                    break;
                case "2":
                    System.out.printf("Primero escoges a tu inicial entre 3. Este modo es 1 combate%n");
                    break;
            }
            if (!opcion.equals("3")) {
                System.out.printf("¿Quieres saber más?%n1. Ataques 2. Combates contra salvajes 3. Salir%n");
                opcion_modo = save.readLine();
                switch (opcion_modo) {
                    case "1":
                        System.out.println(
                                "1. Atacar: Ataque estándar basado en la fuerza del Pekemen y las ventajas de tipo, con un 10% de probabilidad de asestar un golpe crítico.");
                        System.out.println(
                                "2. Curarte: Restaura 20 puntos de vida usando una poción (1 disponible por combate y sin importanca de tu vida maxima)");
                        System.out.println(
                                "3. Explotar: Sacrifica la mitad de tu vida actual para debilitar instantáneamente al Pekemen rival.");
                        System.out.println(
                                "4. Atacar fuerte: Ejecuta un golpe con el doble de potencia, pero con un 60% de probabilidad de fallar.");
                        System.out
                                .println("5. Protegerte: Te proteges durante el turno, bloqueando el daño del rival.");
                        System.out.printf(
                                "6. Efecto especial: Aplica un estado alterado sobre el rival según el tipo elemental de tu Pekemen:%nFuego (Quemar): El rival pierde 15 de vida en cada turno y su ataque se reduce en 10 puntos.%nPlanta (Envenenar): El rival pierde 25 de vida en cada turno.%nAgua (Ahogar): Contador de 3 turnos tras los cuales el rival queda debilitado automáticamente.%n");
                        System.out.println(
                                "7. Cambiar pekemen: Permite cambiar tu Pekemen activo por otro miembro con vida de tu equipo durante el combate, recalculando ventajas elementales y reiniciando estados alterados.");
                        break;
                    case "2":
                        System.out.println(
                                "Pekemens salvajes: Puedes combatirlos para ganar experiencia y subir de nivel, o intentar capturarlos para incorporarlos a tu equipo (máximo 6 Pekemens)");
                        break;
                }
            }
        } while (!opcion.equals("3"));
    }

    public static String preguntaHistoria() throws Exception {
        String eleccion;
        do {
            System.out.printf(
                    "Que quieres hacer:%n1. Modo historia.%n2. Combate singular%n3. Salir%n4. Manual%nEleccion: ");
            eleccion = save.readLine();

            if (!eleccion.equals("1") && !eleccion.equals("2") && !eleccion.equals("3") && !eleccion.equals("4")) {
                System.out.println("Por favor, elige un número del 1 al 4.");
            }
        } while (!eleccion.equals("1") && !eleccion.equals("2") && !eleccion.equals("3") && !eleccion.equals("4"));

        return eleccion;
    }

    public static String[][] elegirPekemen(String[][] pekemens) throws Exception {
        boolean salir = false;
        String[][] tus_pekemens;
        String eleccion_pekemen;
        do {
            tus_pekemens = new String[6][8];
            System.out.println("Escoge un pekemen entre 1. Peplet, 2. Terteg y 3. Chemchemp");
            eleccion_pekemen = save.readLine();
            switch (eleccion_pekemen) {
                case "1":
                    tus_pekemens[0] = pekemens[0].clone();
                    salir = true;
                    break;
                case "2":
                    tus_pekemens[0] = pekemens[3].clone();
                    salir = true;
                    break;
                case "3":
                    tus_pekemens[0] = pekemens[6].clone();
                    salir = true;
                    break;
                default:
                    System.out.println("Escoge entre el 1 y el 3");
            }
        } while (salir == false);
        return tus_pekemens;
    }

    public static String[][] rival(int contador_lider, String pekemens[][], int pekemen_rival) {
        String[][] resultado = new String[8][8];
        if (pekemen_rival == 0) {
            pekemen_rival = ram.nextInt(3) + 1;
        }
        if (contador_lider == 1) {
            contador_lider = 2;
            if (pekemen_rival == 1) {
                resultado[0] = pekemens[0].clone();
            } else if (pekemen_rival == 2) {
                resultado[0] = pekemens[3].clone();
            } else if (pekemen_rival == 3) {
                resultado[0] = pekemens[6].clone();
            } else {
                System.out.println("Error en pekemen del rival");
            }
        } else if (contador_lider == 2) {
            contador_lider = 3;
            resultado[0] = pekemens[13].clone();
            resultado[1] = pekemens[0].clone();
            resultado[2] = pekemens[14].clone();
        } else if (contador_lider == 3) {
            contador_lider = 4;
            resultado[0] = pekemens[7].clone();
            resultado[1] = pekemens[15].clone();
            resultado[2] = pekemens[16].clone();
        } else if (contador_lider == 4) {
            contador_lider = 5;
            resultado[0] = pekemens[5].clone();
            resultado[1] = pekemens[17].clone();
            resultado[2] = pekemens[18].clone();
        } else if (contador_lider == 5) {
            contador_lider = 6;
            resultado[0] = pekemens[9].clone();
            resultado[1] = pekemens[12].clone();
            resultado[2] = pekemens[14].clone();
            resultado[3] = pekemens[16].clone();
            resultado[4] = pekemens[18].clone();
            resultado[5] = pekemens[(pekemen_rival * 3 - 1)].clone();
        }
        resultado[7][0] = "" + contador_lider;
        resultado[7][1] = "" + pekemen_rival;
        return resultado;
    }

    public static double ventajaTipos(String tipo_jugador, String tipo_rival) {
        double ventaja;
        if ((tipo_jugador.equals(FUEGO) && tipo_rival.equals(PLANTA))
                || (tipo_jugador.equals(PLANTA) && tipo_rival.equals(AGUA))
                || ((tipo_jugador.equals(AGUA) && tipo_rival.equals(FUEGO)))) {
            ventaja = 1.25;
        } else if ((tipo_jugador.equals(PLANTA) && tipo_rival.equals(FUEGO))
                || (tipo_jugador.equals(FUEGO) && tipo_rival.equals(AGUA))
                || ((tipo_jugador.equals(AGUA) && tipo_rival.equals(PLANTA)))) {
            ventaja = 0.75;
        } else if (tipo_jugador.equals(tipo_rival) || tipo_jugador.equals(NORMAL) || tipo_rival.equals(NORMAL)) {
            ventaja = 1.0;
        } else {
            ventaja = 1.0;
        }
        return ventaja;
    }

    public static String barraVida(double vida) {
        return "#".repeat(Math.max(0, (int) vida / 10));
    }

    public static String ataqueJugador(double vida_jugador, double vida_rival) throws Exception {
        System.out.printf(
                "Tu vida es %s %.2f y la del rival es %s %.2f%nQue quieres hacer%n1. Atacar%n2. Curarte%n3. Explotar%n4. Atacar fuerte%n5. Protegerte%n6. Efecto especial%n7. Cambiar pekemen%nElecion: ",
                barraVida(vida_jugador), vida_jugador,
                barraVida(vida_rival), vida_rival);
        String eleccion = save.readLine();
        return eleccion;
    }

    public static double danho(int fuerza_maxima, double ventaja, double CRITICO) {
        double potencia, danho_echo;
        if (ram.nextInt(100) < 10) {
            potencia = (ram.nextDouble(fuerza_maxima) + 1) * 10;
            danho_echo = potencia * ventaja * CRITICO;
            System.out.println("Has hecho un critico");
        } else {
            potencia = (ram.nextDouble(fuerza_maxima) + 1) * 10;
            danho_echo = potencia * ventaja;
        }
        return danho_echo;
    }

    public static boolean comprobarEquipo(String[][] equipo) {
        int contador = 0;
        while (contador < equipo.length) {
            if (equipo[contador] != null && equipo[contador][NOMBRE] != null && equipo[contador][VIDA] != null) {
                if (Double.parseDouble(equipo[contador][VIDA]) > 0) {
                    return true;
                }
            }
            contador++;
        }
        return false;
    }

    public static int siguienteRival(String[][] pekemens_rival) {
        int contador = 0;
        while (contador < pekemens_rival.length) {
            if (pekemens_rival[contador] != null && pekemens_rival[contador][NOMBRE] != null
                    && pekemens_rival[contador][VIDA] != null) {
                if (Double.parseDouble(pekemens_rival[contador][VIDA]) > 0) {
                    return contador;
                }
            }
            contador++;
        }
        return -1;
    }

    public static boolean disponibles(String[][] tus_pekemens, int posicion) {
        int contador = 0;
        while (contador < tus_pekemens.length) {
            if (tus_pekemens[contador] != null && tus_pekemens[contador][NOMBRE] != null && contador != posicion
                    && Double.parseDouble(tus_pekemens[contador][VIDA]) > 0) {
                return true;
            }
            contador++;
        }
        return false;
    }

    public static String[] cambiarPekemen(String tus_pekemens[][], int posicion, boolean cambio, boolean quemar,
            boolean envenenar, int contador_ahogar, BufferedReader save, String tipo_jugador, String tipo_rival,
            double vida_jugador, int fuerza_maxima_jugador) throws Exception {
        int contador = 0;

        while (contador < tus_pekemens.length) {
            if (tus_pekemens[contador] != null && tus_pekemens[contador][NOMBRE] != null && contador != posicion
                    && Double.parseDouble(tus_pekemens[contador][VIDA]) > 0) {

                System.out.println("¿Quieres cambiar a " + tus_pekemens[posicion][NOMBRE] + " por "
                        + tus_pekemens[contador][NOMBRE] + " (Vida: " + tus_pekemens[contador][VIDA] + ")? (si/no)");
                String cambiar = save.readLine();

                if (cambiar.equalsIgnoreCase("si")) {
                    tus_pekemens[posicion][VIDA] = String.valueOf((int) Math.max(0, vida_jugador));
                    posicion = contador;
                    tipo_jugador = tus_pekemens[posicion][TIPO];
                    vida_jugador = Double.parseDouble(tus_pekemens[posicion][VIDA]);
                    fuerza_maxima_jugador = Integer.parseInt(tus_pekemens[posicion][DANHO]);
                    quemar = false;
                    envenenar = false;
                    contador_ahogar = 9;
                    ventajaTipos(tipo_jugador, tipo_rival);

                    System.out.println("¡Adelante " + tus_pekemens[posicion][NOMBRE] + "!");
                    cambio = true;
                    contador = 99;
                }
            }
            contador++;
        }

        return new String[] {
                ("" + cambio),
                ("" + posicion),
                ("" + quemar),
                ("" + envenenar),
                ("" + contador_ahogar),
                tipo_jugador,
                ("" + vida_jugador),
                ("" + fuerza_maxima_jugador)
        };
    }

    public static int salvaje(Random ram) {
        if (ram.nextInt(100) < 60) {
            return 9;
        } else if (ram.nextInt(100) < 60) {
            return 10;
        } else if (ram.nextInt(100) < 80) {
            return 11;
        } else {
            return 12;
        }
    }

    public static String[][] repartirExperiencia(String[][] tus_pekemens, int exp_ganada, String[][] pekemens) {
        Integer posicion;
        for (int i = 0; i < tus_pekemens.length; i++) {
            if (tus_pekemens[i] != null && tus_pekemens[i][NOMBRE] != null) {
                posicion = Integer.parseInt(tus_pekemens[i][NUMERO]);
                int experiencia = Integer.parseInt(tus_pekemens[i][EXPERIENCIA]);
                experiencia += exp_ganada;
                int nivelActual = Integer.parseInt(tus_pekemens[i][NIVEL]);

                if (experiencia >= 100 && nivelActual >= 0 && nivelActual <= 5) {
                    experiencia -= 100;
                    tus_pekemens[i][NIVEL] = String.valueOf(nivelActual + 1);
                    if (nivelActual + 1 == 5) {
                        if (!tus_pekemens[i][NUMERO].equals("3")
                                && !tus_pekemens[i][NUMERO].equals("6")
                                && !tus_pekemens[i][NUMERO].equals("9")
                                && !tus_pekemens[i][NUMERO].equals("10")
                                && !tus_pekemens[i][NUMERO].equals("13")
                                && !tus_pekemens[i][NUMERO].equals("15")
                                && !tus_pekemens[i][NUMERO].equals("17")
                                && !tus_pekemens[i][NUMERO].equals("19")) {
                            System.out.println("Tu pekemen ha evolucionado a " + pekemens[posicion][NOMBRE]);
                            tus_pekemens[i] = pekemens[posicion].clone();
                            tus_pekemens[i][NIVEL] = "0";
                        }
                    }
                }
                tus_pekemens[i][EXPERIENCIA] = String.valueOf(experiencia);
            }
        }
        return tus_pekemens;
    }

    public static String[][] mundoAbierto(String[][] pekemens, String[][] tus_pekemens, String[][] pekemens_rival,
            String[] pekemen_salvaje) throws Exception {
        int salvaje, contador_lider, pekemen_rival, contador = 0;
        String equipo, eleccion_salvaje, eleccion;
        boolean lider = false;

        do {
            System.out.println("Que quieres hacer 1. luchar contra el lider o 2. luchar contra pekemens salvajes");
            eleccion = save.readLine();

            switch (eleccion) {
                case "1":
                    lider = true;
                    contador_lider = Integer.parseInt(pekemens_rival[7][0]);
                    pekemen_rival = Integer.parseInt(pekemens_rival[7][1]);
                    pekemens_rival = rival(contador_lider, pekemens, pekemen_rival);
                    break;

                case "2":
                    salvaje = salvaje(ram);
                    pekemen_salvaje[0] = pekemens[salvaje][NOMBRE];
                    pekemen_salvaje[1] = pekemens[salvaje][EXP_DADA];
                    System.out.println("Has salido: " + pekemen_salvaje[0]);
                    System.out.println("Que quieres hacer matar(1) o capturar(2)");
                    eleccion_salvaje = save.readLine();

                    switch (eleccion_salvaje) {
                        case "1":
                            tus_pekemens = repartirExperiencia(tus_pekemens, Integer.parseInt(pekemen_salvaje[1]),
                                    pekemens);
                            break;

                        case "2":
                            contador = 0;
                            while (contador < tus_pekemens.length) {
                                if (tus_pekemens[contador][NOMBRE] == null) {
                                    if (ram.nextInt(100) < 60) {
                                        System.out.println("Le has capturado");
                                        tus_pekemens[contador] = pekemens[salvaje].clone();
                                    } else {
                                        System.out.println("No le has capturado");
                                    }
                                    contador = 99;
                                } else {
                                    System.out.println("En la posicion " + (contador + 1) + " esta "
                                            + tus_pekemens[contador][NOMBRE] + " ¿Le quieres cambiar? (si/no)");
                                    equipo = save.readLine();
                                    if (equipo.equalsIgnoreCase("si")) {
                                        if (ram.nextInt(100) < 70) {
                                            System.out.println("Le has capturado");
                                            tus_pekemens[contador] = pekemens[salvaje].clone();
                                        } else {
                                            System.out.println("No le has capturado");
                                        }
                                        break;
                                    } else if (!equipo.equalsIgnoreCase("no")) {
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
                    break;

                default:
                    System.out.println("1 o 2");
                    break;
            }
        } while (!lider);

        return pekemens_rival;
    }

    public static void main(String[] args) throws Exception {
        // constantes
        final double CRITICO = 1.1;

        // variables
        String[][] tus_pekemens;
        String[][] pekemens_rival;
        pekemens_rival = new String[8][8];
        String[] pekemen_salvaje;
        pekemen_salvaje = new String[2];

        // Catálogo de pekemens
        // numero, nombre, tipo, vida, nivel, daño maximo, experiencia que tiene, experiencia que da
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

        String tipo_jugador, tipo_rival, inicial_rival, eleccion, modo_juego;
        int contador_ahogar, fuerza_maxima_jugador, fuerza_maxima_rival,
                posicion, posicion_rival, contador_lider, pekemen_rival;
        double ventaja, vida_jugador, vida_rival, danho_echo;
        Boolean huir, pocion_jugador, pocion_rival, quemar, envenenar, historia, cambio,
                perder;

        // cuerpo
        huir = false;
        historia = false;
        cambio = false;
        pekemen_rival = 0;
        pekemens_rival[7][0] = "1";
        pekemens_rival[7][1] = "" + pekemen_rival;

        // bucle principal
        posicion = 0;
        while (huir == false) {
            do {
                modo_juego = preguntaHistoria();
                switch (modo_juego) {
                    case "1":
                        historia = true;
                        contador_lider = 0;
                        break;
                    case "2":
                        historia = false;
                        contador_lider = 0;
                        break;
                    case "3":
                        System.out.println("Adios");
                        huir = true;
                        break;
                    case "4":
                        manual();
                        break;
                    }
            } while (!modo_juego.equals("1") && !modo_juego.equals("2") && !modo_juego.equals("3") && !modo_juego.equals("4"));
            if (huir == false) {
                posicion = 0;
                pekemen_rival = 0;
                pekemens_rival = new String[8][8];
                pekemens_rival[7][0] = "1";
                pekemens_rival[7][1] = "" + pekemen_rival;

                // elección pekemen inicial
                tus_pekemens = elegirPekemen(pekemens);

                // pekemen rival
                contador_lider = Integer.parseInt(pekemens_rival[7][0]);
                pekemen_rival = Integer.parseInt(pekemens_rival[7][1]);
                pekemens_rival = rival(contador_lider, pekemens, pekemen_rival);

                // combate
                do {
                    posicion_rival = 0;
                    pocion_jugador = true;
                    pocion_rival = true;
                    contador_ahogar = 9;
                    quemar = false;
                    envenenar = false;
                    perder = false;
                    inicial_rival = pekemens_rival[posicion_rival][NOMBRE];
                    tipo_jugador = tus_pekemens[posicion][TIPO];
                    vida_jugador = Double.parseDouble(tus_pekemens[posicion][VIDA]);
                    fuerza_maxima_jugador = Integer.parseInt(tus_pekemens[posicion][DANHO]);
                    tipo_rival = pekemens_rival[posicion_rival][TIPO];
                    vida_rival = Double.parseDouble(pekemens_rival[posicion_rival][VIDA]);
                    fuerza_maxima_rival = Integer.parseInt(pekemens_rival[posicion_rival][DANHO]);
                    ventaja = ventajaTipos(tipo_jugador, tipo_rival);

                    System.out.println("Tu tipo es " + tipo_jugador + " y el de tu rival es " + tipo_rival
                            + " porque tiene a " + inicial_rival);

                    // ataques
                    do {
                        eleccion = ataqueJugador(vida_jugador, vida_rival);
                        switch (eleccion) {
                            case "1":
                                danho_echo = danho(fuerza_maxima_jugador, ventaja, CRITICO);
                                vida_rival -= danho_echo;
                                break;
                            case "2":
                                if (pocion_jugador == true) {
                                    System.out.println("Has gastado tu ultima pocion");
                                    vida_jugador += 20;
                                    pocion_jugador = false;
                                } else {
                                    System.out.println("No te has podido curar porque no tienes pociones");
                                }
                                break;
                            case "3":
                                vida_rival = 0;
                                vida_jugador = vida_jugador / 2;
                                break;
                            case "4":
                                if (ram.nextInt(100) < 40) {
                                    danho_echo = danho(fuerza_maxima_jugador, ventaja, CRITICO);
                                    vida_rival -= danho_echo * 2;
                                } else {
                                    System.out.println("Ha fallado el ataque");
                                }
                                break;
                            case "5":
                                System.out.println("Te has protegido");
                                break;
                            case "6":
                                if (tipo_jugador.equals(FUEGO) && !tipo_rival.equals(FUEGO)) {
                                    System.out.println("Le has quemado");
                                    quemar = true;
                                } else if (tipo_jugador.equals(PLANTA) && !tipo_rival.equals(PLANTA)) {
                                    System.out.println("Le has envenenado");
                                    envenenar = true;
                                } else if (tipo_jugador.equals(AGUA) && !tipo_rival.equals(AGUA)) {
                                    System.out.println("Le has ahogado");
                                    contador_ahogar = 0;
                                }
                                break;
                            case "7":
                                if (!disponibles(tus_pekemens, posicion)) {
                                    System.out.println("No tienes otros Pekemens disponibles con vida para cambiar.");
                                } else {
                                    String[] resultado_cambio = cambiarPekemen(tus_pekemens, posicion, cambio, quemar,
                                            envenenar, contador_ahogar, save, tipo_jugador, tipo_rival, vida_jugador,
                                            fuerza_maxima_jugador);
                                    int nueva_pos = Integer.parseInt(resultado_cambio[1]);

                                    if (nueva_pos != posicion) {
                                        posicion = nueva_pos;
                                        cambio = Boolean.parseBoolean(resultado_cambio[0]);
                                        tipo_jugador = tus_pekemens[posicion][TIPO];
                                        vida_jugador = Double.parseDouble(tus_pekemens[posicion][VIDA]);
                                        fuerza_maxima_jugador = Integer.parseInt(tus_pekemens[posicion][DANHO]);
                                        quemar = false;
                                        envenenar = false;
                                        contador_ahogar = 9;
                                        ventaja = ventajaTipos(tipo_jugador, tipo_rival);
                                    }
                                }
                                break;

                            default:
                                System.out.println("Por favor, elige una opción válida del 1 al 7.");
                        }
                        if (quemar == true) {
                            vida_rival -= 15;
                        } else if (envenenar == true) {
                            vida_rival -= 25;
                        } else if (contador_ahogar == 0 || contador_ahogar == 1 || contador_ahogar == 2) {
                            contador_ahogar++;
                        } else if (contador_ahogar == 3) {
                            System.out.println("El rival se ha ahogado");
                            vida_rival = 0;
                        }

                        // ataque maquina
                        if (vida_rival > 0) {
                            if ((vida_rival >= 40 && ram.nextInt(100) < 20 && pocion_rival == true)
                                    || (vida_rival < 60 && ram.nextInt(100) < 60 && pocion_rival == true)) {
                                pocion_rival = false;
                                vida_rival += 20;
                            } else if (vida_rival <= 10 && ram.nextInt(100) < 90 && !eleccion.equals("5")) {
                                vida_jugador = 0;
                                vida_rival = vida_rival / 2;
                            } else {
                                if (!eleccion.equals("5")) {
                                    danho_echo = danho(fuerza_maxima_rival, ventaja, CRITICO);
                                    if (quemar == true) {
                                        vida_jugador -= danho_echo - 10;
                                    } else {
                                        vida_jugador -= danho_echo;
                                    }
                                }
                            }
                        }

                        if (vida_jugador <= 0) {
                            System.out.println("¡Tu " + tus_pekemens[posicion][NOMBRE] + " ha sido derrotado!");
                            tus_pekemens[posicion][VIDA] = "0";

                            if (!comprobarEquipo(tus_pekemens)) {
                                perder = true;
                            } else {
                                String[] resultado_muerte = cambiarPekemen(tus_pekemens, posicion, cambio, quemar,
                                        envenenar, contador_ahogar, save, tipo_jugador, tipo_rival, vida_jugador,
                                        fuerza_maxima_jugador);
                                int pos_tras_muerte = Integer.parseInt(resultado_muerte[1]);

                                if (pos_tras_muerte != posicion) {
                                    posicion = pos_tras_muerte;
                                    tipo_jugador = tus_pekemens[posicion][TIPO];
                                    vida_jugador = Double.parseDouble(tus_pekemens[posicion][VIDA]);
                                    fuerza_maxima_jugador = Integer.parseInt(tus_pekemens[posicion][DANHO]);
                                    quemar = false;
                                    envenenar = false;
                                    contador_ahogar = 9;
                                    ventaja = ventajaTipos(tipo_jugador, tipo_rival);
                                } else {
                                    System.out.println("Debes elegir un Pekemen con vida para continuar el combate.");
                                }
                                perder = false;
                            }

                        } else if (vida_rival <= 0) {
                            System.out.println("¡El " + pekemens_rival[posicion_rival][NOMBRE]
                                    + " del rival ha sido derrotado!");
                            pekemens_rival[posicion_rival][VIDA] = "0";
                            if (!comprobarEquipo(pekemens_rival)) {
                                perder = true;
                            } else {
                                posicion_rival = siguienteRival(pekemens_rival);
                                tipo_rival = pekemens_rival[posicion_rival][TIPO];
                                inicial_rival = pekemens_rival[posicion_rival][NOMBRE];
                                vida_rival = Double.parseDouble(pekemens_rival[posicion_rival][VIDA]);
                                fuerza_maxima_rival = Integer.parseInt(pekemens_rival[posicion_rival][DANHO]);
                                pocion_rival = true;
                                quemar = false;
                                envenenar = false;
                                contador_ahogar = 9;
                                ventaja = ventajaTipos(tipo_jugador, tipo_rival);

                                System.out.println("¡El rival envía a " + inicial_rival + "!");
                                perder = false;
                            }
                        }
                    } while (perder == false);

                    if (vida_jugador <= 0) {
                        System.out.println("Has perdido el combate");
                        historia = false;
                    } else if (vida_rival <= 0) {
                        System.out.println("Has ganado");
                        if (pekemens_rival[7][0].equals("6")) {
                            System.out.println("Todos los rivales han sido vencidos");
                            historia = false;
                        }
                        if (historia == true) {
                            pekemens_rival = mundoAbierto(pekemens, tus_pekemens, pekemens_rival, pekemen_salvaje);
                        }
                    }
                } while (historia == true);
            }
        }
    }
}