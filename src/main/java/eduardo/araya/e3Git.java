package eduardo.araya;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class e3Git {

    /*
    ORDENAR CADENA
     Cada palabra de la cadena contendrá un solo número
     Este número es la posición que debería tener la palabra en el resultado
     Nota: Los números pueden ser del 1 al 9. Por lo tanto, 1 será la primera palabra (no 0).
     Si la cadena de entrada está vacía, devuelve una cadena vacía.
     Las palabras en la cadena de entrada solo contendrán números consecutivos válidos.

     Ejemplo:
       e2s ho1y mierc3oles => ho1y 2es 3miercoles
     */

    /*
    Se agrega nuevos comentarios para mejorrar entendimiento del codigo
     */

    public String ordenarCadena(String s1) {

        //1. dividir String en un Lista de String
        List<String> listadoPalabras = Arrays.asList(s1.split(" "));
        List<String> ordenCadena = new ArrayList<String>();
        int buscadornumero = 1;
        String delim =" ";

        //recorrer Lista buscando los numeros y ordenandolos
        do {
            for (int i = 0; i < listadoPalabras.size(); i++) {
                if(listadoPalabras.get(i).contains(""+buscadornumero)){
                    ordenCadena.add(buscadornumero-1,listadoPalabras.get(i));
                    buscadornumero++;
                }
            }
        }while(listadoPalabras.size() != ordenCadena.size());

        //System.out.println(ordenCadena);

        //convertir la Lista ordenCadena en un String con espacios
        String res = String.join(delim,ordenCadena);
        return res;
    }




}
