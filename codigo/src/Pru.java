/*
Autor: Jorge
Fecha: 2026/27
Descripción: El juego Pekemen trata de que el jugador elige un elemento para enfrentarse a la máquina en una batalla individual o en un modo historia. Durante el combate, puedes atacar y otras opciones.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Pru {
    public static void mundo_abierto(BufferedReader save) {
        do {
            System.out.println("Que quieres hacer 1. luchar contra el lider o 2. luchar contra pekemens salvajes");
            eleccion = Integer.parseInt(save.readLine());
            switch (eleccion) {
                case 1:
                    lider = true;
                    contador_lider = Integer.parseInt(pekemens_rival[7][0]);
                    pekemen_rival = Integer.parseInt(pekemens_rival[7][1]);
                    pekemens_rival = rival(contador_lider, pekemens, pekemen_rival);
                    posicion_rival = 0;
                    inicial_rival = pekemens_rival[posicion_rival][1];
                    pocion_rival = true;
                    perder = false; 
                    quemar = false;
                    envenenar = false;
                    contador_ahogar = 9;
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
                    pekemen_salvaje[0] = pekemens[salvaje][1];
                    pekemen_salvaje[1] = pekemens[salvaje][7];
                    System.out.println("Has salido: " + pekemen_salvaje[0]);
                    System.out.println("Que quieres hacer matar(1) o capturar(2)");
                    eleccion_salvaje = Integer.parseInt(save.readLine());
                    switch (eleccion_salvaje) {
                        case 1:
                            for (int i = 0; i < tus_pekemens.length; i++) {
                                if (tus_pekemens[i] != null && tus_pekemens[i][1] != null) {
                                    experiencia = Integer.parseInt(tus_pekemens[i][6]);
                                    experiencia += Integer.parseInt(pekemen_salvaje[1]);
                                    if (experiencia >= 100 && (Integer.parseInt(tus_pekemens[i][4]) == 0
                                            || Integer.parseInt(tus_pekemens[i][4]) == 1
                                            || Integer.parseInt(tus_pekemens[i][4]) == 2
                                            || Integer.parseInt(tus_pekemens[i][4]) == 3)) {
                                        experiencia -= 100;
                                        tus_pekemens[i][4] = "" + (1 + Integer.parseInt(tus_pekemens[i][4]));
                                        tus_pekemens[i][6] = "" + experiencia;
                                    }
                                }
                            }
                            break;
                        case 2:
                            for (int i = 0; i < tus_pekemens.length; i++) {
                                if (tus_pekemens[i][1] == null) {
                                    if (ram.nextInt(100) < 60) {
                                        System.out.println("Le has capturado");
                                        tus_pekemens[i] = pekemens[salvaje].clone();
                                        break;
                                    } else {
                                        System.out.println("No le has capturado");
                                        break;
                                    }
                                } else {
                                    System.out.println("En la posicion " + (i + 1) + " esta "
                                            + tus_pekemens[i][1] + " ¿Le quieres cambiar? (si/no)");
                                    equipo = save.readLine();
                                    if (equipo.equalsIgnoreCase("si")) {
                                        if (ram.nextInt(100) < 70) {
                                            System.out.println("Le has capturado");
                                            tus_pekemens[i] = pekemens[salvaje].clone();
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
    }

    public static String[][] elegir_pekemen(BufferedReader save, String[][] pekemens)throws Exception {
        boolean salir = false;
        String[][] tus_pekemens;
        int eleccion_pekemen;
        do {
            tus_pekemens = new String[6][8];
            System.out.println("Escoge un pekemen entre 1. Peplet, 2. Terteg y 3. Chemchemp");
            eleccion_pekemen = Integer.parseInt(save.readLine());
            switch (eleccion_pekemen) {
                case 1:
                    tus_pekemens[0] = pekemens[0].clone();
                    salir = true;
                    break;
                case 2:
                    tus_pekemens[0] = pekemens[3].clone();
                    salir = true;
                    break;
                case 3:
                    tus_pekemens[0] = pekemens[6].clone();
                    salir = true;
                    break;
                default:
                    System.out.println("Escoge entre el 1 y el 3");
            }
        } while (salir == false);
        return tus_pekemens;
    }

    public static boolean comprobar_equipo_vivo(String[][] equipo) {
        for (int i = 0; i < equipo.length; i++) {
            if (equipo[i] != null && equipo[i][1] != null && equipo[i][3] != null) {
                if (Double.parseDouble(equipo[i][3]) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int pregunta_historia(BufferedReader save) throws Exception {
        int eleccion;
        do {
            System.out.printf("Que quieres hacer:%n1. Modo historia.%n2. Combate singular%n3. Salir%nEleccion: ");
            eleccion = Integer.parseInt(save.readLine());
            
            if (eleccion > 3 || eleccion < 1) {
                System.out.println("Por favor, elige un número del 1 al 3.");
            }
        } while (eleccion != 1 && eleccion != 2 && eleccion != 3);
        
        return eleccion; 
    }

    public static String[][] rival(int contador_lider, String pekemens[][], int pekemen_rival) {
        Random ram = new Random();
        String[][] resultado = new String[8][8];
        if (pekemen_rival == 0) {
            pekemen_rival = ram.nextInt(2) + 1;
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
            resultado[0] = pekemens[9].clone();
            resultado[1] = pekemens[12].clone();
            resultado[2] = pekemens[14].clone();
            resultado[3] = pekemens[16].clone();
            resultado[4] = pekemens[18].clone();
            resultado[5] = pekemens[(pekemen_rival * 3 - 1)].clone();
            contador_lider = 6;
        }
        resultado[7][0] = "" + contador_lider;
        resultado[7][1] = "" + pekemen_rival;
        return resultado;
    }

    public static String[] cambiar_pekemen(String tus_pekemens[][], int posicion, boolean cambio, boolean quemar,
            boolean envenenar, int contador_ahogar, BufferedReader save, String tipo_jugador, String tipo_rival,
            double vida_jugador, int fuerza_maxima_jugador) throws Exception {

        for (int i = 0; i < tus_pekemens.length; i++) {
            if (tus_pekemens[i] != null && tus_pekemens[i][1] != null && i != posicion
                    && Double.parseDouble(tus_pekemens[i][3]) > 0) {
                
                System.out.println("¿Quieres cambiar a " + tus_pekemens[posicion][1] + " por "
                        + tus_pekemens[i][1] + " (Vida: " + tus_pekemens[i][3] + ")? (si/no)");
                String cambiar = save.readLine();

                if (cambiar.equalsIgnoreCase("si")) {
                    tus_pekemens[posicion][3] = String.valueOf((int) Math.max(0, vida_jugador));
                    posicion = i;
                    tipo_jugador = tus_pekemens[posicion][2];
                    vida_jugador = Double.parseDouble(tus_pekemens[posicion][3]);
                    fuerza_maxima_jugador = Integer.parseInt(tus_pekemens[posicion][5]);
                    quemar = false;
                    envenenar = false;
                    contador_ahogar = 9;
                    ventaja_tipos(tipo_jugador, tipo_rival);

                    System.out.println("¡Adelante " + tus_pekemens[posicion][1] + "!");
                    cambio = true;
                    break;
                }
            }
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

    public static double ventaja_tipos(String tipo_jugador, String tipo_rival) {
        double ventaja;
        if ((tipo_jugador == "fuego" && tipo_rival == "planta")
                || (tipo_jugador == "planta" && tipo_rival == "agua")
                || ((tipo_jugador == "agua" && tipo_rival == "fuego"))) {
            ventaja = 1.25;
        } else if ((tipo_jugador == "planta" && tipo_rival == "fuego")
                || (tipo_jugador == "fuego" && tipo_rival == "agua")
                || ((tipo_jugador == "agua" && tipo_rival == "planta"))) {
            ventaja = 0.75;
        } else {
            ventaja = 1.0;
        }
        return ventaja;
    }

    public static int ataque_jugador(BufferedReader save, double vida_jugador, double vida_rival) throws Exception {
        System.out.printf(
                "Tu vida es %s %.2f y la del rival es %s %.2f%nQue quieres hacer%n1. Atacar%n2. Curarte%n3. Explotar%n4. Atacar fuerte%n5. Protegerte%n6. Efecto especial%n7. Cambiar pekemen%nElecion: ",
                "#".repeat(Math.max(0, (int) vida_jugador / 10)), vida_jugador,
                "#".repeat(Math.max(0, (int) vida_rival / 10)), vida_rival);
        int eleccion = Integer.parseInt(save.readLine());
        return eleccion;
    }

    public static double danho(Random ram, int fuerza_maxima, double ventaja, double CRITICO) {
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

    public static void main(String[] args) throws Exception {
        // constantes
        final double CRITICO = 1.1;
        final String FUEGO = "fuego";
        final String AGUA = "agua";
        final String PLANTA = "planta";

        // variables
        BufferedReader save = new BufferedReader(new InputStreamReader(System.in));
        Random ram = new Random();

        String[][] tus_pekemens;
        tus_pekemens = new String[6][8];
        String[][] pekemens_rival;
        pekemens_rival = new String[8][8];
        String[] pekemen_salvaje;
        pekemen_salvaje = new String[2];
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

        String tipo_jugador, tipo_rival, inicial_rival, equipo;
        int eleccion, contador_ahogar, fuerza_maxima_jugador, fuerza_maxima_rival,
                eleccion_salvaje, salvaje, experiencia, posicion, posicion_rival, contador_lider, pekemen_rival,
                modo_juego ;
        double ventaja, vida_jugador, vida_rival, danho_echo;
        Boolean huir, pocion_jugador, pocion_rival, quemar, envenenar, historia, lider, cambio,
                perder;

        // cuerpo
        huir = false;
        historia = false;
        lider = false;
        cambio = false;
        perder = false;
        pekemen_rival = 0;
        pekemens_rival[7][0] = "1";
        pekemens_rival[7][1] = "" + pekemen_rival;

        // bucle principal
        while (huir == false) {
            posicion = 0;
            posicion_rival = 0;
            modo_juego = pregunta_historia(save);
            if (modo_juego == 1) {
                historia = true;
            } else if (modo_juego == 2) {
                historia = false;
            } else if (modo_juego == 3) {
                System.out.println("¡Adiós!");
                huir = true;
                break;
            }
            if (huir == true) {
                break;
            }

            // eleccion pekemen inicial
            tus_pekemens = elegir_pekemen(save, pekemens);

            // pekemes rival
            contador_lider = Integer.parseInt(pekemens_rival[7][0]);
            pekemen_rival = Integer.parseInt(pekemens_rival[7][1]);
            pekemens_rival = rival(contador_lider, pekemens, pekemen_rival);
            posicion_rival = 0;

            // combate
            pocion_jugador = true;
            pocion_rival = true;
            contador_ahogar = 9;
            quemar = false;
            envenenar = false;
            inicial_rival = pekemens_rival[posicion_rival][1];

            do {
                tipo_jugador = tus_pekemens[posicion][2];
                vida_jugador = Double.parseDouble(tus_pekemens[posicion][3]);
                fuerza_maxima_jugador = Integer.parseInt(tus_pekemens[posicion][5]);
                tipo_rival = pekemens_rival[posicion_rival][2];
                vida_rival = Double.parseDouble(pekemens_rival[posicion_rival][3]);
                fuerza_maxima_rival = Integer.parseInt(pekemens_rival[posicion_rival][5]);
                ventaja = ventaja_tipos(tipo_jugador, tipo_rival);

                System.out.println("Tu tipo es " + tipo_jugador + " y el de tu rival es " + tipo_rival + " porque tiene a " + inicial_rival);

                // ataques
                do {
                    eleccion = ataque_jugador(save, vida_jugador, vida_rival);
                    switch (eleccion) {
                        case 1:
                            danho_echo = danho(ram, fuerza_maxima_jugador, ventaja, CRITICO);
                            vida_rival -= danho_echo;
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
                            vida_rival = 0;
                            vida_jugador = vida_jugador / 2;
                            break;
                        case 4:
                            if (ram.nextInt(100) < 40) {
                                danho_echo = danho(ram, fuerza_maxima_jugador, ventaja, CRITICO);
                                vida_rival -= danho_echo * 2;
                            } else {
                                System.out.println("Ha fallado el ataque");
                            }
                            break;
                        case 5:
                            System.out.println("Te has protegido");
                            break;
                        case 6:
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
                        case 7:
                            String[] resultado_cambio = cambiar_pekemen(tus_pekemens, posicion, cambio, quemar, envenenar,
                                    contador_ahogar, save, tipo_jugador, tipo_rival, vida_jugador,
                                    fuerza_maxima_jugador);
                            int nueva_pos = Integer.parseInt(resultado_cambio[1]);
                            
                            if (nueva_pos != posicion) {
                                posicion = nueva_pos;
                                cambio = Boolean.parseBoolean(resultado_cambio[0]);
                                tipo_jugador = tus_pekemens[posicion][2];
                                vida_jugador = Double.parseDouble(tus_pekemens[posicion][3]);
                                fuerza_maxima_jugador = Integer.parseInt(tus_pekemens[posicion][5]);
                                quemar = false;
                                envenenar = false;
                                contador_ahogar = 9;
                                ventaja = ventaja_tipos(tipo_jugador, tipo_rival);
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
                        } else if (vida_rival <= 10 && ram.nextInt(100) < 90 && eleccion != 5) {
                            vida_jugador = 0;
                            vida_rival = vida_rival / 2;
                        } else {
                            danho_echo = danho(ram, fuerza_maxima_rival, ventaja, CRITICO);
                            vida_jugador -= danho_echo;
                        }
                    }

                    if (vida_jugador <= 0) {
                        System.out.println("¡Tu " + tus_pekemens[posicion][1] + " ha sido derrotado!");
                        tus_pekemens[posicion][3] = "0";

                        if (!comprobar_equipo_vivo(tus_pekemens)) {
                            perder = true;
                        } else {
                            String[] resultado_muerte = cambiar_pekemen(tus_pekemens, posicion, cambio, quemar, envenenar,
                                    contador_ahogar, save, tipo_jugador, tipo_rival, vida_jugador,
                                    fuerza_maxima_jugador);
                            int pos_tras_muerte = Integer.parseInt(resultado_muerte[1]);
                            
                            if (pos_tras_muerte != posicion) {
                                posicion = pos_tras_muerte;
                                cambio = Boolean.parseBoolean(resultado_muerte[0]);
                                tipo_jugador = tus_pekemens[posicion][2];
                                vida_jugador = Double.parseDouble(tus_pekemens[posicion][3]);
                                fuerza_maxima_jugador = Integer.parseInt(tus_pekemens[posicion][5]);
                                quemar = false;
                                envenenar = false;
                                contador_ahogar = 9;
                                ventaja = ventaja_tipos(tipo_jugador, tipo_rival);
                            } else {
                                System.out.println("Debes elegir un Pekemen con vida para continuar el combate.");
                            }
                            perder = false;
                        }

                    } else if (vida_rival <= 0) {
                        System.out
                                .println("¡El " + pekemens_rival[posicion_rival][1] + " del rival ha sido derrotado!");
                        pekemens_rival[posicion_rival][3] = "0";
                        if (!comprobar_equipo_vivo(pekemens_rival)) {
                            perder = true;
                        } else {
                            // Buscar al siguiente rival vivo
                            int siguienteRival = -1;
                            for (int i = 0; i < pekemens_rival.length; i++) {
                                if (pekemens_rival[i] != null && Double.parseDouble(pekemens_rival[i][3]) > 0) {
                                    siguienteRival = i;
                                    break;
                                }
                            }
                            posicion_rival = siguienteRival;
                            tipo_rival = pekemens_rival[posicion_rival][2];
                            inicial_rival = pekemens_rival[posicion_rival][1];
                            vida_rival = Double.parseDouble(pekemens_rival[posicion_rival][3]);
                            fuerza_maxima_rival = Integer.parseInt(pekemens_rival[posicion_rival][5]);
                            pocion_rival = true;
                            quemar = false;
                            envenenar = false;
                            contador_ahogar = 9;
                            ventaja = ventaja_tipos(tipo_jugador, tipo_rival);

                            System.out.println("¡El rival envía a " + inicial_rival + "!");
                            perder = false;
                        }
                    }
                } while (perder == false);

                if (vida_jugador <= 0) {
                    System.out.println("Has perdido el combate");
                    historia = false;
                    break;
                } else if (vida_rival <= 0) {
                    System.out.println("Has ganado");
                    if (pekemens_rival[7][0].equals("6")) {
                        lider = true;
                        historia = false;
                    }else{
                        lider = false;
                    }
                    if (historia == false) {
                        break;
                    }
                }
                if (pekemens_rival[7][1] == "false"){
                    break;
                }

                // elecciones del mundo
                mundo_abierto(save);
            } while (historia == true);
        }
    }
}