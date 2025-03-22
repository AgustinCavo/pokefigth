package JuegoPokemon.modelo.game;


import JuegoPokemon.modelo.game.efectos.Efecto;
import JuegoPokemon.modelo.game.clima.Clima;
import JuegoPokemon.modelo.game.estado.EstadoEnum;


public class Habilidad implements Clonable{

    private final String nombre;

    private final String info; //Es util para imprimir en terminal y que el usuario sepa lo que selecciona

    private Integer id;

    private final Efecto efecto;
    private final double poder;

    private final Tipo tipo; //Todas las habilidades solo poseen un tipo

    private final Double probaInducirEstado;

    private  Integer cantidadUsos;

    private Clima climaQueGenera;

    private EnumObjetivo objetivo;


    //Pre: ninguna
    //Post: crea una habilidad con nombre, poder, información y un tipo
    public Habilidad(String nombre, Integer id, Tipo tipo, Double poder , Efecto efecto, Double probaEfecto,
                     Integer cantidadUsos, String info, String climaQueGenera, EnumObjetivo objetivo){
        this.nombre = nombre;
        this.tipo = tipo;
        this.id = id;
        this.probaInducirEstado = probaEfecto;
        this.cantidadUsos = cantidadUsos;
        this.info = info;
        this.efecto = efecto;
        this.poder = poder;
        this.climaQueGenera = Clima.stringAClima(climaQueGenera);
        this.objetivo = objetivo;
    }


    //Pre: ninguna
    //Post: devuelve el nombre de la habilidad
    public String getNombre() {
        return nombre;
    }

    //Pre: ninguna
    //Post: devuelve la informacion acerca del efecto de la habilidad y lo que hace
    public String getInfo() {
        return info;
    }

    //Pre: ninguna
    //Post: devuelve el tipo de la habilidad
    public Tipo getTipo() {
        return tipo;
    }

    public Integer getId(){
        return id;
    }

    public Double getProbaInducirEstado() {
        return probaInducirEstado;
    }

    public Boolean sePuedeUsar() {
        return this.cantidadUsos>0;
    }
    public void gastarUso() {
       this.cantidadUsos=this.cantidadUsos-1;
    }

    //Pre: ninguna
    //Post: devuelve un string con todos los datos utiles acerca de la habilidad
    //      permitiendo imprimir una instancia de la habilidad
    public String toString(){
        return " " + this.nombre + " | " + this.poder + " | " + this.info+ " | " + this.climaQueGenera.getNombreVisible() + " | " + this.cantidadUsos;
    }


    @Override
    public Object clonar() {
        return  new Habilidad(this.getNombre(), this.id, this.getTipo(), this.poder, this.efecto, this.getProbaInducirEstado(), this.cantidadUsos, this.getInfo(), Clima.climaAString(this.climaQueGenera),this.objetivo);
    }

    public Double getPoder(){
        return this.poder;
    }
    public boolean sePuedeAplicarEfecto(){
            double intentoAplicar = Calculadora.generarRandomProbabilidad();
            return intentoAplicar < probaInducirEstado;
    }
    public EstadoEnum getEstado(){
        return efecto.getEstado();
    }

    public Clima getClimaQueGenera() {
        return climaQueGenera;
    }

    public Integer getCantidadUsos() {
        return cantidadUsos;
    }

    public EnumObjetivo getObjetivo(){
        return objetivo;
    }
    public void aplicarEfecto(Pokemon pokemon){
        this.efecto.aplicarEfecto(pokemon);
    }

    public Efecto getEfecto() {
        return efecto;
    }

}
