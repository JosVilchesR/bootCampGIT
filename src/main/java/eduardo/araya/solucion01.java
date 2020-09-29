package eduardo.araya;

public class solucion01 {

    public String declararGanador(fighter pj1,fighter pj2){

        double valor = Math.floor(Math.random()*(100-10+1)+10); // Valor entre M y N, ambos incluidos.

        pj1 = new fighter("edu",1000, 10);
        pj2 = new fighter("ude",1000,100);


        return "ganador de la batalla es: "+pj1.name;
    }


}
