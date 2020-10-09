package Josefa.Vilches;

public class Raiden extends Fighter {
    // sin incluir el primer Rayo: son la cantidad de veces adicionales que se emite Rayo
    private int cantidad;

    public Raiden() {
        super("Raiden");
        cantidad = 0;
    }

    /**
     * Función que retorna el daño actual de Rayo.
     * @return float, daño actual de Rayo.
     */
    public float obtenerDanioEspecialActual() {
        float ataque = 40 + (20 * (cantidad - 1));
        if (ataque >=100) {
            ataque = 100;
        }
        return ataque;
    }

    public float ataqueEspecial() {
        float ataque = 40 + (20 * cantidad);
        cantidad++;
        if (ataque >=100) {
            ataque = 100;
        }
        return ataque;
    }

    // Esta función nos permite obtener cuanto daño de un ataque especial le pega a Raiden
    public float debilidad(Fighter oponente) {
        float ataque = 0;
        // Si SubZero ataca a Raiden
        if (oponente instanceof SubZero && this.getHealth() > 500 ) {
            //cambiamos a > 50  porque nunca iba a ser mayor a 100 el rayo
            if (this.obtenerDanioEspecialActual() > 50) {
                ataque = oponente.ataqueEspecial() * (float) 2.5;
            } else {
                ataque = oponente.ataqueEspecial() * (float) 1.5;
            }
        } else if (oponente instanceof SubZero && this.getHealth() <= 500 ) {
            ataque = oponente.ataqueEspecial() * (float) 1.5;
        }

        // Si Scorpion ataca a Raiden
        if (oponente instanceof Scorpion && this.getHealth() < 500) {
            ataque = oponente.ataqueEspecial() * (float)1.3;
        }
        if (oponente instanceof Scorpion && this.getHealth() >= 500) {
               ataque = 80;
        }
        return ataque;
    }

    public String obtenerNombreAtaqueEspecial() {
        String nombre = ">->->Rayo->->->";
        return nombre;
    }
}

