package JuegoPokemon.modelo.game;

import JuegoPokemon.modelo.game.clima.Clima;
import JuegoPokemon.modelo.game.efectos.EstadisticasEnum;
import JuegoPokemon.modelo.game.estado.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class Pokemon implements Clonable{

    private final String nombre;

    private final Integer id;

    private final String historia;

    private final Integer nivel;

    private final Estadisticas estadisticasDefault;

    private final Estadisticas estadisticasActuales;

    private final List<Habilidad> habilidades;

    private final List<Tipo> tipos;

    private HashMap<EstadoEnum,Estado> estados;

    public HashMap<EstadoEnum, Estado> getEstados() {
        return estados;
    }

    //Pre: ninguna
    //Post: crea un pokemon, el nivel es aleatorio (entre 1-100)
    public Pokemon(String nombre, Integer id, List<Tipo> tipos, String historia, Double vidaMaxima, Double velocidad, Double defensa, Double ataque, List<Habilidad> habilidades){
        this.nombre = nombre;
        this.id = id;
        this.tipos = new ArrayList<>();
        this.tipos.add(tipos.get(0));
        this.historia = historia;
        this.nivel = (int)(Math.random()*100 + 1);
        this.estadisticasActuales = new Estadisticas(vidaMaxima, velocidad, defensa, ataque);
        this.estadisticasDefault = new Estadisticas(vidaMaxima, velocidad, defensa, ataque);
        this.habilidades = habilidades;
        this.estados =  new HashMap<>();
        estados.put(EstadoEnum.Normal,new Normal());
    }

    //Pre: ninguna
    //Post: devuelve el nombre del pokemon
    public String getNombre() {
        return nombre;
    }


    //Pre: ninguna
    //Post: devuelve el nivel del pokemon
    public Integer getNivel() {
        return nivel;
    }


    //Pre: ninguna
    //Post: devuelve un string que contiene el nombre y mote del pokemon
    //      haciendo posible imprimir por pantalla el pokemon
    public void setEstados(HashMap<EstadoEnum, Estado> estados) {
        this.estados = estados;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass()!= Pokemon.class){ return false; }
        Pokemon pokemonNuevo= (Pokemon) obj;
        return this.estadisticasActuales.equals(pokemonNuevo.estadisticasActuales) && this.estados.equals(pokemonNuevo.estados);
    }

    public boolean tieneEsteEstado( EstadoEnum estadoAComprobar){
        return estados.containsKey(estadoAComprobar);
    }


    public Double getVelocidadDeafault(){
        return estadisticasDefault.getVelocidad();
    }

    //Pre: ninguna
    //Post: devuelve una lista con los tipos que posee el pokemon
    public List<Tipo> getTipos() {
        return tipos;
    }

    //Pre: ninguna
    //Post: reduce la vida del pokemon dependiendo del damage realizado. Devuelve true si murio, false si sigue vivo.
    public void reducirVida(Double damage){
        if (this.getVida()-damage<=0){
            this.debilitar();
            return;
        }
        this.setVida(Math.round((this.getVida()-damage) * 100.0) / 100.0);
    }

    public void setVida(Double nuevaVida){
        this.estadisticasActuales.setVida(nuevaVida);
    }

    @Override
    public Object clonar() {
        return new Pokemon(nombre, id, tipos, historia, estadisticasDefault.getVida(), estadisticasDefault.getVelocidad(), estadisticasDefault.getDefensa(), estadisticasDefault.getAtaque(), habilidades);
    }
    public Double getVida(){
        return this.estadisticasActuales.getVida();
    }
    public Double getAtaque(){
        return this.estadisticasActuales.getAtaque();
    }

    public Double getAtaqueDefault() {
        return this.estadisticasDefault.getAtaque();
    }

    public Double getDefensa(){
        return this.estadisticasActuales.getDefensa();
    }

    public Double getDefensaDefault() {
        return this.estadisticasDefault.getDefensa();
    }


    public Double getVelocidad(){
        return this.estadisticasActuales.getVelocidad();
    }

    public Double getVidaMaxima(){
        return this.estadisticasDefault.getVida();
    }

    public void debilitar(){
        this.estadisticasActuales.setVida(0.00);
        HashMap<EstadoEnum,Estado> nuevosEstados= new HashMap<>();
        nuevosEstados.put(EstadoEnum.Debilitado,new Debilitado());
        this.estados = nuevosEstados;
    }

    public void cambiarEstado(EstadoEnum nuevoEstado) {
        if (!nuevoEstado.equals(EstadoEnum.Normal)){
           estados.remove(EstadoEnum.Normal);
        }
        if (!this.tieneEsteEstado(nuevoEstado)){
            this.estados.put(nuevoEstado,CreadorEstados.crear(nuevoEstado));
        }
    }

    public void resetearEstadoANormal(){
        HashMap<EstadoEnum,Estado> nuevosEstados= new HashMap<>();
        nuevosEstados.put(EstadoEnum.Normal,new Normal());
        this.estados = nuevosEstados;
    }
    public void cambiarEstadistica(EstadisticasEnum estadistica, Double nuevaEstadistica){
            switch (estadistica){
                case Ataque -> this.estadisticasActuales.setAtaque(nuevaEstadistica);
                case Defensa -> this.estadisticasActuales.setDefensa(nuevaEstadistica);
                case Velocidad -> this.estadisticasActuales.setVelocidad(nuevaEstadistica);
            }
    }
    public void despertar(){
        if (this.tieneEsteEstado(EstadoEnum.Dormido)){
            this.removerEstado(EstadoEnum.Dormido);
        }
        if (this.estados.isEmpty()) {
            estados.put(EstadoEnum.Normal,new Normal());
        }
    }
    public void desconfundir(){
        if (this.tieneEsteEstado(EstadoEnum.Confuso)){
            this.removerEstado(EstadoEnum.Confuso);
        }
        if (this.estados.isEmpty()) {
            estados.put(EstadoEnum.Normal,new Normal());
        }
    }

    //Devuelve null si puede atacar, en caso contrario devuelve el estado que se lo impide.
    public EstadoEnum puedeAtacar(){
        if (estados.containsKey(EstadoEnum.Normal)){
            return null;
        }
        for (Map.Entry<EstadoEnum,Estado> estado : estados.entrySet()){
            if(!estado.getValue().puedeAtacar()){
                return estado.getKey();
            }
        }
        return null;
    }

    public List<EstadoEnum> estadosQueHacenDaño() {
        return estados.values().stream()
                .filter(estado -> estado.danioPorTurno(estadisticasActuales.getVida()) > 0)
                .map(Estado::getEstado)
                .collect(Collectors.toList());
    }

    public Double reduccionVidaPorTurnoEstado() {
        double damage = 0.0;
        for (Map.Entry<EstadoEnum,Estado> estado : estados.entrySet()){
            damage = damage + estado.getValue().danioPorTurno(estadisticasActuales.getVida());
            this.reducirVida(estado.getValue().danioPorTurno(estadisticasDefault.getVida()));
        }
        return damage;
    }

    private void removerEstado(EstadoEnum remover){
        estados.remove(remover);
    }


    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public boolean atacar(Pokemon pokemonAtacado, Habilidad habilidad, Clima climaActual) {
        EstadoEnum estadoBloqueante = this.puedeAtacar();
        if (estadoBloqueante == null) {
            this.despertar();
            this.desconfundir();

            if(habilidad.getObjetivo()==EnumObjetivo.Personal){
                pokemonAtacado = this;
            }

            if (habilidad.sePuedeUsar()) {
                habilidad.gastarUso();
                Double damage = Calculadora.calcularDamage(this, pokemonAtacado, habilidad, climaActual);
                pokemonAtacado.reducirVida(damage);
            }

            if (habilidad.sePuedeAplicarEfecto() && !pokemonAtacado.estaDebilitado()){
                if (habilidad.getEstado()!=null) {
                    pokemonAtacado.cambiarEstado(habilidad.getEstado());
                }else{
                    habilidad.aplicarEfecto(pokemonAtacado);
                }
            }

            return true;
        }else if(estadoBloqueante==EstadoEnum.Confuso){
            this.estados.get(EstadoEnum.Confuso).danioPorTurno(pokemonAtacado.getVida());
        }
        return false;
    }

    public String getHistoria() {
        return historia;
    }

    public Integer getId() {
        return id;
    }

    public List<EstadoEnum> getListaEstados(){

        HashMap<EstadoEnum, Estado> estados = this.getEstados();
        List<EstadoEnum> listaEstadosEnum = new ArrayList<>();

        for (HashMap.Entry<EstadoEnum, Estado> estado : estados.entrySet()) {

            listaEstadosEnum.add(estado.getKey());
        }
        return listaEstadosEnum;
    }

    public boolean estaDebilitado(){
        return tieneEsteEstado(EstadoEnum.Debilitado);
    }

}

