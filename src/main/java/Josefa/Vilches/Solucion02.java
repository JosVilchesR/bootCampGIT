package Josefa.Vilches;

import java.util.ArrayList;

public class Solucion02 {
    String red="\033[31m";
    //String green="\033[32m";
    String yellow="\033[33m";
    String blue="\033[34m";
    //String purple="\033[35m";
    //String cyan="\033[36m";
    //String white="\033[37m";
    String reset="\u001B[0m";

    /**
     * Función que calcula la probabilidad de golpe crítico.
     * @return booleano que indica si el golpe es crítico o no.
     */
    public boolean esGolpeCritico() {
        boolean esCritico = false;
        // Genera un int del 1 al 10, si sale 10 se habilita el daño crítico.
        int critChance = (int)(Math.random()*10)+1;
        if (critChance == 10) {
            esCritico = true;
        }
        return esCritico;
    }

    /**
     * Función que determina que campéon parte golpeando, si es 1 el campeón 1 parte y viceversa.
     * @return int, 1 o 2.
     */
    public int golpeaPrimero() {
        return (int)(Math.random()*2)+1;
    }

    /**
     * Función que obtiene el tipo de ataque, a través de un número aleatorio entre 1 y 4.
     * Donde 1 corresponde a Golpe, 2 a Patada, 3 a Salto y 4 al ataque Especial
     * @return int, número del 1 al 4
     */
    public int obtenerTipoAtaque() {
        return (int)(Math.random()*4)+1;
    }

    /**
     * Función que calcula el daño del ataque.
     * @param atacante campeón que ataca.
     * @param receptor campeón que recibe el ataque.
     * @return ArrayList que contiene floats, donde [0] es el tipo de Ataque y [1] la cantidad de daño.
     */
    public ArrayList<Float> calcularDanio(Fighter atacante, Fighter receptor) {
        ArrayList<Float> describeAtaques = new ArrayList<>();
        int tipoAtaque = this.obtenerTipoAtaque();
        float danio;
        // Determinamos si el ataque va a ser crítico o no.
        // Un golpe con daño critico corresponde al 130% del daño normal
        boolean esCritico = this.esGolpeCritico();

        if(tipoAtaque == 1) {
            danio = atacante.golpe();
            if (esCritico) {
                danio = danio*(float)1.3;
            }
            // Agregamos el tipo de ataque al arrayList
            describeAtaques.add((float)1);
        } else if (tipoAtaque == 2) {
            danio = atacante.patada();
            if (esCritico) {
                danio = danio*(float)1.3;
            }
            describeAtaques.add((float)2);
        } else if (tipoAtaque == 3) {
            danio = atacante.salto();
            if (esCritico) {
                danio = danio*(float)1.3;
            }
            describeAtaques.add((float)3);
        } else {
            // Si el ataque corresponde al ataque especial
            danio = receptor.debilidad(atacante);
            describeAtaques.add((float)4);
        }
        // Agregamos el daño del ataque al arrayList
        describeAtaques.add(danio);
        return describeAtaques;
    }

    /**
     * Función que retorna el nombre del ataque.
     * @param describeAtaques arrayList que contiene el tipo y daño del ataque.
     * @param atacante campeón que ataca.
     * @return String, nombre del ataque.
     */
    public String nombreAtaque(ArrayList<Float> describeAtaques, Fighter atacante) {
        String nombre;
        // Comparamos el valor de la primera posicion del arrayList (tipo de ataque)
        if (describeAtaques.get(0) == 1) {
            nombre = "Golpe";
        } else if (describeAtaques.get(0) == 2) {
            nombre = "Patada";
        } else if (describeAtaques.get(0) == 3) {
            nombre = "Salto";
        } else {
            nombre = atacante.obtenerNombreAtaqueEspecial().toUpperCase();
        }
        return nombre;
    }

    /**
     * Función que itera sobre los turnos de la pelea, entre 2 campeones y
     * retorna el nombre del ganador.
     * @param campeon1 instancia del luchador que va a combatir.
     * @param campeon2 instancia del luchador que va a combatir.
     * @return String, nombre del ganador de la pelea.
     */
    public String pelea(Fighter campeon1, Fighter campeon2) {
        // Esta variable sirve como flag para detener la pelea, cuando un campeón muere
        boolean continuaPelea = true;
        ArrayList<Float> describeAtaques;
        float danioGolpe;
        float tipoAtaque = 0;
        String ganador = "";

        imprimeLog("Pelean " + campeon1.getName().toUpperCase() + " vs " + campeon2.getName().toUpperCase());
        imprimeLog("-------------------------------");

        // Resetear vidas
        campeon1.setHealth(1000);
        campeon2.setHealth(1000);

        // Llamamos a este método para saber que campeón golpea primero
        this.golpeaPrimero();
        // Campeón 1 golpea primero
        if (this.golpeaPrimero() == 1) {
            while (campeon1.getHealth() > 0 && campeon2.getHealth() > 0) {
                if (continuaPelea) {
                    // Guardamos el tipo de ataque y el daño en esta variable
                    describeAtaques = this.calcularDanio(campeon1, campeon2);
                    // Guardamos el daño del ataque
                    danioGolpe = (describeAtaques).get(1);
                    // Guardamos el tipo de ataque
                    tipoAtaque = (describeAtaques).get(0);

                    // Si el campeón 1 es Scorpion
                    if (campeon1 instanceof Scorpion && tipoAtaque == 4) {
                        if (campeon1.getHealth() < 100) {
                            // Scorpion tira fatality
                            float danioFatality = campeon2.getHealth();
                            campeon2.setHealth(campeon2.getHealth() - danioFatality);
                            imprimeLog("Scorpion tira FATALITY!!!!!!!");
                            imprimeLog(campeon2.getName()  +" queda con: " + blue + campeon2.getHealth() + blue + " HP");
                        } else if (campeon1.getHealth() >= 100) {
                            // Scorpion tira Lanzallamas
                            campeon2.setHealth(campeon2.getHealth() - danioGolpe);
                            imprimeLog(campeon1.getName() + " ataca con " + yellow + this.nombreAtaque(describeAtaques, campeon1) + reset + " y causa " + red + danioGolpe + reset +" DMG");
                            imprimeLog(campeon2.getName() + " queda con: " + blue + campeon2.getHealth() + reset + " HP");
                        }
                    } else {
                        // Entran todas las otras combinaciones de ataques y campeones
                        campeon2.setHealth(campeon2.getHealth() - danioGolpe);
                        imprimeLog(campeon1.getName() + " ataca con " + yellow + this.nombreAtaque(describeAtaques, campeon1)+ reset + " y causa " + red + danioGolpe + reset + " DMG");
                        imprimeLog(campeon2.getName() + " queda con: " + blue + campeon2.getHealth() +reset+ " HP");
                    }

                    //Revisamos si hay KO y de ser así, detenemos la pelea
                    if (campeon1.getHealth() <= 0) {
                        ganador = campeon2.getName();
                        continuaPelea = false;
                    } else if (campeon2.getHealth() <= 0) {
                        ganador = campeon1.getName();
                        continuaPelea = false;
                    }
                }

                // Entramos si se genera un segundo golpe del campeon 1
                if (continuaPelea) {
                    // Si el campeon 1 es SubZero o Scorpion
                    if (campeon1 instanceof SubZero || campeon1 instanceof Scorpion) {
                        if (tipoAtaque == 4) {
                            //Volvemos a generar un ataque y lo guardamos en las variables
                            describeAtaques = this.calcularDanio(campeon1, campeon2);
                            danioGolpe = (describeAtaques).get(1);
                            tipoAtaque = (describeAtaques).get(0);
                            if (campeon1 instanceof SubZero && tipoAtaque == 4) {
                                campeon1.setHealth(campeon1.getHealth() - 100);
                                imprimeLog(campeon1.getName() + " lanza *❆❅CONGELAMIENTO❅❆*, pero se congeló y queda con: " +blue+ campeon1.getHealth() +reset+ " HP");
                            }  else {
                                campeon2.setHealth(campeon2.getHealth() - danioGolpe);
                                imprimeLog(campeon1.getName() + " da un Segundo Golpe!! Y ataca con " +yellow+ this.nombreAtaque(describeAtaques, campeon1) +reset+ " y causa " +red+ danioGolpe +reset+ " DMG");
                                imprimeLog(campeon2.getName() +  " queda con: " +blue+ campeon2.getHealth() +reset+ " HP");
                            }
                        }
                    }

                    //Revisamos si hay KO y de ser así, detenemos la pelea
                    if (campeon1.getHealth() <= 0) {
                        ganador = campeon2.getName();
                        continuaPelea = false;
                    } else if (campeon2.getHealth() <= 0) {
                        ganador = campeon1.getName();
                        continuaPelea = false;
                    }
                }

                // Ahora golpea el campeón 2
                if (continuaPelea) {
                    describeAtaques = this.calcularDanio(campeon2, campeon1);
                    danioGolpe = (describeAtaques).get(1);
                    tipoAtaque = (describeAtaques).get(0);
                    if (campeon2 instanceof Scorpion && tipoAtaque == 4) {
                        if (campeon2.getHealth() < 100) {
                            float danioFatality = campeon1.getHealth();
                            campeon1.setHealth(campeon1.getHealth() - danioFatality);
                            imprimeLog("Scorpion tira FATALITY!!!!!!!");
                            imprimeLog(campeon1.getName() + " queda con: " +blue+ campeon1.getHealth() +reset+ " HP");
                        } else if (campeon2.getHealth() >= 100) {
                            campeon1.setHealth(campeon1.getHealth() - danioGolpe);
                            imprimeLog(campeon2.getName() + " ataca con " +yellow+ this.nombreAtaque(describeAtaques, campeon2) + " y causa " +red+ danioGolpe +reset+ " DMG");
                            imprimeLog(campeon1.getName() + " queda con: " +blue+ campeon1.getHealth() +reset+ " HP");
                        }
                    } else {
                        campeon1.setHealth(campeon1.getHealth() - danioGolpe);
                        imprimeLog(campeon2.getName() + " ataca con " +yellow+ this.nombreAtaque(describeAtaques, campeon2) +reset+ " y causa " +red+ danioGolpe +reset+ " DMG");
                        imprimeLog(campeon1.getName() + " queda con: " +blue+ campeon1.getHealth() +reset+ " HP");
                    }

                    //Revisamos si hay KO y de ser así, detenemos la pelea
                    if (campeon1.getHealth() <= 0) {
                        ganador = campeon2.getName();
                        continuaPelea = false;
                    } else if (campeon2.getHealth() <= 0) {
                        ganador = campeon1.getName();
                        continuaPelea = false;
                    }
                }

                // Entramos si se genera un segundo golpe del campeon 2
                if (continuaPelea) {
                    if (campeon2 instanceof SubZero || campeon2 instanceof Scorpion) {
                        if (tipoAtaque == 4) {
                            describeAtaques = this.calcularDanio(campeon2, campeon1);
                            danioGolpe = (describeAtaques).get(1);
                            tipoAtaque = (describeAtaques).get(0);
                            if (campeon2 instanceof SubZero && tipoAtaque == 4) {
                                campeon2.setHealth(campeon2.getHealth() - 100);
                                imprimeLog(campeon2.getName() + " lanza *❆❅CONGELAMIENTO❅❆*, pero se congeló y queda con: " +blue+ campeon2.getHealth() +reset+ " HP");
                            } else {
                                campeon1.setHealth(campeon1.getHealth() - danioGolpe);
                                imprimeLog(campeon2.getName() + " da un Segundo Golpe!! Y ataca con " +yellow+ this.nombreAtaque(describeAtaques, campeon2) +reset+ " y causa " +red+ danioGolpe +reset+ " DMG");
                                imprimeLog(campeon1.getName() + " queda con: " +blue+ campeon1.getHealth() +reset+ " HP");
                            }
                        }
                    }

                    //Revisamos si hay KO y de ser así, detenemos la pelea
                    if (campeon1.getHealth() <= 0) {
                        ganador = campeon2.getName();
                        continuaPelea = false;
                    } else if (campeon2.getHealth() <= 0) {
                        ganador = campeon1.getName();
                        continuaPelea = false;
                    }
                }
            }

        // En este caso el campeon 2 ataca primero
        } else {
            while (campeon1.getHealth() > 0 && campeon2.getHealth() > 0) {
                if (continuaPelea) {
                    describeAtaques = this.calcularDanio(campeon2, campeon1);
                    danioGolpe = (describeAtaques).get(1);
                    tipoAtaque = (describeAtaques).get(0);
                    if (campeon2 instanceof Scorpion && tipoAtaque == 4) {
                        if (campeon2.getHealth() < 100) {
                            float danioFatality = campeon1.getHealth();
                            campeon1.setHealth(campeon1.getHealth() - danioFatality);
                            imprimeLog("Scorpion tira FATALITY!!!!!!!");
                            imprimeLog(campeon1.getName() + " queda con: " +blue+ campeon1.getHealth() +reset+ " HP");
                        } else if (campeon2.getHealth() >= 100) {
                            campeon1.setHealth(campeon1.getHealth() - danioGolpe);
                            imprimeLog(campeon2.getName() + " ataca con " +yellow+ this.nombreAtaque(describeAtaques, campeon2) +reset+ " y causa " +red+ danioGolpe +reset+ " DMG");
                            imprimeLog(campeon1.getName() + " queda con: " +blue+ campeon1.getHealth() +reset+ " HP");
                        }
                    } else {
                        campeon1.setHealth(campeon1.getHealth() - danioGolpe);
                        imprimeLog(campeon2.getName() + " ataca con " +yellow+ this.nombreAtaque(describeAtaques, campeon2) +reset+ " y causa " +red+ danioGolpe +reset+ " DMG");
                        imprimeLog(campeon1.getName() + " queda con: " +blue+ campeon1.getHealth() +reset+ " HP");
                    }

                    //Revisamos si hay KO y de ser así, detenemos la pelea
                    if (campeon1.getHealth() <= 0) {
                        ganador = campeon2.getName();
                        continuaPelea = false;
                    } else if (campeon2.getHealth() <= 0) {
                        ganador = campeon1.getName();
                        continuaPelea = false;
                    }
                }

                // Entramos si se genera un segundo golpe del campeon 2
                if (continuaPelea) {
                    if (campeon2 instanceof SubZero || campeon2 instanceof Scorpion) {
                        if (tipoAtaque == 4) {
                            describeAtaques = this.calcularDanio(campeon2, campeon1);
                            danioGolpe = (describeAtaques).get(1);
                            tipoAtaque = (describeAtaques).get(0);
                            if (campeon2 instanceof SubZero && tipoAtaque == 4){
                                campeon2.setHealth(campeon2.getHealth() - 100);
                                imprimeLog(campeon2.getName() + " lanza *❆❅CONGELAMIENTO❅❆*, pero se congeló y queda con: " +blue+ campeon2.getHealth() +reset+ " HP");
                            } else {
                                campeon1.setHealth(campeon1.getHealth() - danioGolpe);
                                imprimeLog(campeon2.getName() + " da un Segundo Golpe!! Y ataca con " +yellow+ this.nombreAtaque(describeAtaques, campeon2) +reset+ " y causa " +red+ danioGolpe +reset+ " DMG");
                                imprimeLog(campeon1.getName() + " queda con " +blue+ campeon1.getHealth() +reset+ " HP");
                            }
                        }
                    }

                    //Revisamos si hay KO y de ser así, detenemos la pelea
                    if (campeon1.getHealth() <= 0) {
                        ganador = campeon2.getName();
                        continuaPelea = false;
                    } else if (campeon2.getHealth() <= 0) {
                        ganador = campeon1.getName();
                        continuaPelea = false;
                    }
                }

                // Ahora golpea el campeon 1
                if (continuaPelea) {
                    describeAtaques = this.calcularDanio(campeon1, campeon2);
                    danioGolpe = (describeAtaques).get(1);
                    tipoAtaque = (describeAtaques).get(0);
                    if (campeon1 instanceof Scorpion && tipoAtaque == 4) {
                        if (campeon1.getHealth() < 100) {
                            float danioFatality = campeon2.getHealth();
                            campeon2.setHealth(campeon2.getHealth() - danioFatality);
                            imprimeLog("Scorpion tira FATALITY!!!!!!!");
                            imprimeLog(campeon2.getName() + " queda con: " +blue+ campeon2.getHealth() +reset+ " HP");
                        } else if (campeon1.getHealth() >= 100) {
                            campeon2.setHealth(campeon2.getHealth() - danioGolpe);
                            imprimeLog(campeon1.getName() + " ataca con " + yellow + this.nombreAtaque(describeAtaques, campeon1) +reset+ " y causa " +red+ danioGolpe +reset+ " DMG");
                            imprimeLog(campeon2.getName() + " queda con: " + blue + campeon2.getHealth() + reset + " HP");
                        }
                    } else {
                        campeon2.setHealth(campeon2.getHealth() - danioGolpe);
                        imprimeLog(campeon1.getName() + " ataca con " + yellow + this.nombreAtaque(describeAtaques, campeon1) +reset+ " y causa " +red+ danioGolpe +reset+ " DMG");
                        imprimeLog(campeon2.getName() + " queda con: " + blue + campeon2.getHealth() + reset + " HP");
                    }

                    //Revisamos si hay KO y de ser así, detenemos la pelea
                    if (campeon1.getHealth() <= 0) {
                        ganador = campeon2.getName();
                        continuaPelea = false;
                    } else if (campeon2.getHealth() <= 0) {
                        ganador = campeon1.getName();
                        continuaPelea = false;
                    }
                }

                // Entramos si se genera un segundo golpe del campeon 1
                if (continuaPelea) {
                    if (campeon1 instanceof SubZero || campeon1 instanceof Scorpion) {
                        if (tipoAtaque == 4) {
                            describeAtaques = this.calcularDanio(campeon1, campeon2);
                            danioGolpe = (describeAtaques).get(1);
                            tipoAtaque = (describeAtaques).get(0);
                            if (campeon1 instanceof SubZero && tipoAtaque == 4) {
                                campeon1.setHealth(campeon1.getHealth() - 100);
                                imprimeLog(campeon1.getName() + " lanza *❆❅CONGELAMIENTO❅❆*, pero se congeló y queda con: " +blue+ campeon1.getHealth() +reset+ " HP");
                            } else {
                                campeon2.setHealth(campeon2.getHealth() - danioGolpe);
                                imprimeLog(campeon1.getName() + " da un Segundo Golpe!! Y ataca con " + yellow + this.nombreAtaque(describeAtaques, campeon1) +reset+ " y causa " +red+ danioGolpe +reset+ " DMG");
                                imprimeLog(campeon2.getName() + " queda con: " + blue + campeon2.getHealth() + reset + " HP");
                            }
                        }
                    }

                    //Revisamos si hay KO y de ser así, detenemos la pelea
                    if (campeon1.getHealth() <= 0) {
                        ganador = campeon2.getName();
                        continuaPelea = false;
                    } else if (campeon2.getHealth() <= 0) {
                        ganador = campeon1.getName();
                        continuaPelea = false;
                    }
                }
            }
        }
        imprimeLog("GANADOR: " + ganador.toUpperCase());
        return ganador;
    }

    /**
     * Función que permite imprimir y retrasar la impresión.
     * @param texto lo que será impreso.
     */
    public void imprimeLog(String texto){
        try {
            System.out.println(texto);
            //Thread.sleep(1500);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    public static void main(String[] args) {
        Raiden raiden = new Raiden();
        Scorpion sco = new Scorpion();
        //SubZero sub = new SubZero();
        Solucion02 sol = new Solucion02();
        sol.pelea(raiden, sco);
    }*/

}

