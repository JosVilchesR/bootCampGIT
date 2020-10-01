package eduardo.araya.test;
import eduardo.araya.fighter;
import static org.junit.Assert.*;
import eduardo.araya.solucion01;
import org.junit.Test;

public class solucion01Test {

    @Test
    public void Test(){
        assertEquals("edu", solucion01.declararGanador(new fighter("edu", 10, 2),new fighter("frank", 5, 4)));

    }
}
