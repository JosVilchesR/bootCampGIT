package Josefa.Vilches;

public class SubZero extends Fighter {

    public SubZero(){
        super("SubZero");
    }

    public float ataqueEspecial() {
        float ataque = 40;
        return ataque;
    }

    // Esta función nos permite obtener cuanto daño de un ataque especial le pega a SubZero
    public float debilidad(Fighter oponente) {
        float ataque = 0;
        // Si Scorpion ataca a SubZero
        if (oponente instanceof Scorpion && this.getHealth() < 500) {
            ataque = oponente.ataqueEspecial()*(float)1.3;
        } else if (oponente instanceof Scorpion && this.getHealth() >= 500) {
            ataque = 80;
        }
        // Si Raiden ataca a SubZero
        if (oponente instanceof Raiden && this.getHealth() < 300) {
            ataque = oponente.ataqueEspecial()*2;
        } else if (oponente instanceof Raiden && this.getHealth() >= 300) {
            ataque = oponente.ataqueEspecial();
        }
        return ataque;
    }

    public String obtenerNombreAtaqueEspecial(){
        String nombre = "*❆❅Congelamiento❅❆*";
        return nombre;
    }
}
