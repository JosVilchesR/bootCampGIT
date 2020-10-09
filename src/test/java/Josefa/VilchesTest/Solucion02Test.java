package Josefa.VilchesTest;

import Josefa.Vilches.Fighter;
import Josefa.Vilches.Raiden;
import Josefa.Vilches.Scorpion;
import Josefa.Vilches.Solucion02;
import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class Solucion02Test {
    private Fighter peleador1;
    private Fighter peleador2;

    @Before
    public void setUp() {
        peleador1 = new Raiden();
        peleador2 = new Scorpion();
    }

    /**
     * El retorno debe ser un String
     */
    @Test
    public void tipoRetorno() {
        try {
            Solucion02 sol = new Solucion02();
            peleador1 = new Raiden ();
            peleador2 = new Scorpion();
            // Atrapo el retorno de la clase
            Object quienGana =  sol.pelea(peleador1, peleador2);
            // Comparo si la clase del retorno es igual a un String
            assertEquals(quienGana.getClass(), String.class);
            System.out.println("El retorno corresponde a un String");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * El retorno no debe ser nulo
     */
    @Test
    public void retornoNoNulo() {
        try {
            Solucion02 sol = new Solucion02();
            peleador1 = new Raiden ();
            peleador2 = new Scorpion();
            assertNotNull( sol.pelea(peleador1, peleador2));
            System.out.println("El retorno no es nulo");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Los luchadores no deben ser iguales
     */
    @Test
    public void distintosLuchadores() {
        try {
            peleador1 = new Raiden();
            peleador2 = new Scorpion();
            assertNotEquals(peleador1, peleador2);
            System.out.print("Luchador 1 (" + peleador1.getName() + ")");
            System.out.print(" es distinto a Luchador 2 (" + peleador2.getName() + ")");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * El retorno no es vacío
     */
    @Test
    public void retornoNoVacio() {
        try {
            Solucion02 sol = new Solucion02();
            peleador1 = new Raiden ();
            peleador2 = new Scorpion();
            assertFalse(sol.pelea(peleador1, peleador2) == "");
            System.out.println("El retorno no es vacío");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Ejecución debe tardar menos de 1 segundo
     */
    @Test(timeout = 1000)
    public void TiempodeEjecucion() {
        try {
            Solucion02 sol = new Solucion02();
            assertEquals("Scorpion", sol.pelea(peleador1,peleador2));
            System.out.println("La pelea se ejecuta en menos de 1 segundo");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

