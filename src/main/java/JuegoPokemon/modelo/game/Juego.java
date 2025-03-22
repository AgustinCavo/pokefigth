package JuegoPokemon.modelo.game;

import JuegoPokemon.modelo.game.acciones.*;
import JuegoPokemon.modelo.game.clima.*;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    List<Jugador> jugadores;

    private final AdministradorDeTurnos administradorDeTurnos;

    private EstadoEvento estadoEvento;

    protected Clima clima;

    double damageEstado;

    double damageClima;

    public Juego(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.administradorDeTurnos = new AdministradorDeTurnos(jugadores);
        this.clima = Clima.climaRandom();
        estadoEvento = EstadoEvento.Iniciado;
    }

    public void reiniciarClimaSiNecesario(){
        if (clima.getTurnosRestantes() <= 0)
            clima = new SinClima();
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Jugador getJugadorActual() {
        return administradorDeTurnos.jugadorDelTurnoActual();
    }

    public Jugador getJugadorInactivoActual () {
        return administradorDeTurnos.jugadorInactivoDelTurnoActual();
    }

    public boolean realizarAccion(Accion accion) {

        boolean funciono = accion.realizarAccion();

        if(accion.getTipo() == TipoAccion.LANZAR_HABILIDAD && funciono) {
           LanzarHabilidad lanzarHabilidad =  (LanzarHabilidad) accion;
           Clima clima = lanzarHabilidad.getClima();
           cambiarClima(clima);
        }

        if (getGanador() != null || accion.getTipo() == TipoAccion.RENDIRSE) {
            estadoEvento = EstadoEvento.Finalizado;
            return funciono;
        }


        return funciono;
    }


    public Pokemon getPokemonAtacante() {
        return getJugadorActual().getPokemonSuelto();
    }

    public void reiniciarTurnos() {
        administradorDeTurnos.reiniciarTurnos();
    }

    public Jugador getGanador() {
        ArrayList<Jugador> ganador = new ArrayList<Jugador>();
        for (Jugador jugador : administradorDeTurnos.getJugadores()) {
            if (jugador.tienePokemonsActivos() && !jugador.rendido())
                ganador.add(jugador);
        }
        if (ganador.size() == 1)
            return ganador.get(0);
        return null;
    }

    public EstadoEvento getEstadoEvento() {
        return estadoEvento;
    }
    public boolean pokemonSueltoAtacanteEstaMuerto() {
        return getJugadorActual().pokemonSueltoMuerto();
    }

    public Double getDamageEstado() {
        Double damage = this.damageEstado;
        return damage;
    }

    public Double getDamageClima() {
        Double damage = this.damageClima;
        return damage;
    }

    public void reduccionVidaPorTurno() {
        Pokemon pokemonInactivo = getPokemonInactivo();
        if (pokemonInactivo != null) {
            this.damageClima = clima.danoPorTurno(getPokemonInactivo());
            clima.reducirTurnos();
            this.damageEstado = getPokemonAtacante().reduccionVidaPorTurnoEstado();
            pokemonInactivo.reducirVida(damageEstado);
            if (pokemonInactivo.estaDebilitado()) {
                getJugadorActual().reducirPokemonsActivos();
            }
        }
    }
    public void actualizarOrdenJugadores() {
        administradorDeTurnos.actualizarJugadores();
    }
    public void cambiarTurno() {
        administradorDeTurnos.siguienteTurno();
    }

    public Clima getClima() {
        return clima;
    }

    public  Pokemon getPokemonInactivo() {
        return getJugadorInactivoActual().getPokemonSuelto();
    }

    public boolean pokemonAtacanteEstaDebilitado() {
        return getJugadorActual().pokemonSueltoEstaDebilitado();
    }

    public void cambiarClima(Clima clima) {
        if (clima.getTipo() != ClimaEnum.SinClima && clima.getTipo() != this.clima.getTipo()) {
            this.clima = clima;
        }
        if(this.clima.getTipo() == clima.getTipo()) {
            this.clima.aumentarTurnos();
        }
    }
}
