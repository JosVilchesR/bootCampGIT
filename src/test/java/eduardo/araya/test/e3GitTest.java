package eduardo.araya.test;

import eduardo.araya.e3Git;
import static org.junit.Assert.*;
import org.junit.Test;

public class e3GitTest {

    @Test
    public void ordenarString2palabras() {
        e3Git ob1 = new e3Git();
        String s1 = "2tu ho1la";
        assertEquals("ho1la 2tu", ob1.ordenarCadena(s1));
        System.out.println("String Entrada: " + s1 + " .String de Salida: " + ob1.ordenarCadena(s1));

    }

    @Test
    public void ordenarString4palabras() {
        e3Git ob1 = new e3Git();
        String s1 = "2tu ho1la";
        assertEquals("ho1la 2tu", ob1.ordenarCadena(s1));
        System.out.println("String Entrada: " + s1 + " .String de Salida: " + ob1.ordenarCadena(s1));


    }
}
