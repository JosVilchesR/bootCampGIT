package Josefa.Vilches;

public abstract class Fighter {
    public String name;
    public float health;

    public Fighter(String name) {
        this.name = name;
        this.health = 1000;
    }

    public Fighter() {
        this.name = "";
        this.health = 1000;
    }

    /**
     * Función que retorna el daño de un ataque tipo Golpe.
     * @return daño del Golpe.
     */
    public float golpe() {
        float danio = 0;
        danio = (float)Math.floor(Math.random()*(100-10+1)+50);
        return danio;
    }

    /**
     * Función que retorna el daño de un ataque tipo Patada.
     * @return daño de la Patada.
     */
    public float patada() {
        float danio = 0;
        danio = (float)Math.floor(Math.random()*(120-10+1)+30);
        return danio;
    }

    /**
     * Función que retorna el daño de un ataque tipo Salto.
     * @return daño del Salto.
     */
    public float salto() {
        float danio = 0;
        return danio;
    }

    /**
     * Función que retorna el daño del ataque especial.
     * @return float, cantidad de daño.
     */
    public abstract float ataqueEspecial();

    /**
     * Función que retorna el daño recibido, tomando en cuenta las condiciones de debilidad
     * ante los ataques especiales de los otros campeones.
     * @param oponente campeón que realiza el ataque.
     * @return float, daño recibido al ser atacado por un ataque especial.
     */
    public abstract float debilidad(Fighter oponente);

    /**
     * Función que retorna el nombre del ataque especial, según el campeón.
     * @return String, nombre del ataque especial.
     */
    public abstract String obtenerNombreAtaqueEspecial();

    //Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }
}
