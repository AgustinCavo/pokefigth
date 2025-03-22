package JuegoPokemon.modelo.game.acciones;

import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;

public class LiberarPokemon implements Accion {
    private Pokemon pokemon;
    private Jugador jugador;
    @Override
    public boolean realizarAccion() {
        jugador.liberarPokemon(pokemon);
        return true;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setPokemon(Pokemon eleccionpokemon) {
        this.pokemon = eleccionpokemon;
    }

    public TipoAccion getTipo() {
        return TipoAccion.LIBERAR_POKEMON;
    }
}
