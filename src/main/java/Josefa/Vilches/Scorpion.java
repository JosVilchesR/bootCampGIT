package Josefa.Vilches;

public class Scorpion extends Fighter {

    public Scorpion(){
        super("Scorpion");
    }

    public float ataqueEspecial() {
        float ataque = 40;
        return ataque;
    }

    // Esta función nos permite obtener cuanto daño de un ataque especial le pega Scorpion
    public float debilidad(Fighter oponente) {
        float ataque = 0;
        // Si SubZero ataca a Scorpion
        if(oponente instanceof SubZero && this.getHealth() > 500) {
            ataque = oponente.ataqueEspecial()*(float)1.5;
        //Si Raiden ataca a Scorpion
        } else if(oponente instanceof Raiden && this.getHealth() < 300) {
            ataque= oponente.ataqueEspecial()*2;
        } else {
            ataque = oponente.ataqueEspecial();
        }
        return ataque;
    }

    public String obtenerNombreAtaqueEspecial(){
        String nombre = "~~~Lanzallamas~~~";
        return nombre;
    }
}
