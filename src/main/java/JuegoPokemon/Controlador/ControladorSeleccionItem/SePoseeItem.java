package JuegoPokemon.Controlador.ControladorSeleccionItem;

import JuegoPokemon.modelo.game.Item;

public class SePoseeItem implements CondicionDeSeleccionItem {

	@Override
	public Boolean cumpleCondicion(Item item, Integer cantidad) {
		return cantidad > 0;
	}
}
