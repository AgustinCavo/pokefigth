package JuegoPokemon.Controlador.ControladorSeleccionPokemon;

import JuegoPokemon.modelo.game.Pokemon;

public class PokemonVivo implements CondicionDeSeleccionPokemon {

	@Override
	public Boolean cumpleCondicion(Pokemon pokemon) {
		return !pokemon.estaDebilitado();
	}
}
