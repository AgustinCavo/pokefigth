package JuegoPokemon.Controlador.ControladorSeleccionPokemon;

import JuegoPokemon.modelo.game.Item;
import JuegoPokemon.modelo.game.Pokemon;

public class ItemAplicable implements CondicionDeSeleccionPokemon {

	private Item item;

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public Boolean cumpleCondicion(Pokemon pokemon) {
		return this.item.puedeAplicarse(pokemon);
	}
}
