package JuegoPokemon.Controlador.ControladorSeleccionPokemon;

import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;

public class PokemonGuardado implements CondicionDeSeleccionPokemon {

	private Jugador jugador;

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Override
	public Boolean cumpleCondicion(Pokemon pokemon) {
		return jugador.getPokemonSuelto() != pokemon && jugador.contienePokemon(pokemon);
	}
}
