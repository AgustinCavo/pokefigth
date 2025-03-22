package JuegoPokemon.modelo.game;



public class Estadisticas {


    private Double vida;


    private Double velocidad;


    private Double ataque;


    private Double defensa;

    public Estadisticas(Double vida, Double velocidad, Double defensa, Double ataque) {
        this.vida = vida;
        this.velocidad = velocidad;
        this.defensa = defensa;
        this.ataque = ataque;
    }

    //Pre: ninguna
    //Post: devuelve la vida
    public Double getVida() {
        return vida;
    }

    //Pre: ninguna
    //Post: devuelve la velocidad
    public Double getVelocidad() {
        return velocidad;
    }

    //Pre: ninguna
    //Post: devuelve el ataque
    public Double getAtaque() {
        return ataque;
    }

    //Pre: ninguna
    //Post: devuelve la defensa
    public Double getDefensa() {
        return defensa;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj.getClass()!= Estadisticas.class){ return false; }
        Estadisticas estadisticasNuevas= (Estadisticas) obj;
        return this.vida.equals(estadisticasNuevas.vida) && this.velocidad.equals(estadisticasNuevas.velocidad) && this.ataque.equals(estadisticasNuevas.ataque) && this.defensa.equals(estadisticasNuevas.defensa);
    }

    //Pre: ninguna
    //Post: establece un nuevo valor de vida
    public void setVida(Double vida) {
        this.vida = vida;
    }

    //Pre: ninguna
    //Post: establece un nuevo valor de ataque
    public void setAtaque(Double ataque) {
        this.ataque = ataque;
    }

    //Pre: ninguna
    //Post: establece un nuevo valor de velocidad
    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }

    //Pre: ninguna
    //Post: establece un nuevo valor de defensa
    public void setDefensa(Double defensa) {
        this.defensa = defensa;
    }
}
