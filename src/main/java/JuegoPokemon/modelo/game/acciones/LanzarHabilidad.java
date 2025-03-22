package JuegoPokemon.modelo.game.acciones;

import JuegoPokemon.modelo.game.Habilidad;
import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.clima.Clima;


public class LanzarHabilidad implements Accion {
    private Habilidad habilidad;
    private Pokemon pokemonAtacante;
    private Jugador jugadorAtacado;
    private Clima clima;

    @Override
    public boolean realizarAccion() {
        Pokemon pokemonAtacado = jugadorAtacado.getPokemonSuelto();
        boolean ataco =  pokemonAtacante.atacar(pokemonAtacado, habilidad, clima);
        if (pokemonAtacado.getVida() <= 0)
            jugadorAtacado.reducirPokemonsActivos();
        return ataco;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public void setAtacante(Pokemon atacante) {
        this.pokemonAtacante = atacante;
    }


    public void setJugadoAtacado(Jugador jugadoAtacado) {
        this.jugadorAtacado = jugadoAtacado;
    }

    public void setClima(Clima clima) {
        this.clima = clima;
    }

    @Override
    public TipoAccion getTipo() {
        return TipoAccion.LANZAR_HABILIDAD;
    }

    public Clima getClima() {
        return this.clima;
    }
}
