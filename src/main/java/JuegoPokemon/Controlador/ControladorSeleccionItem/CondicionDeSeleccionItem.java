package JuegoPokemon.Controlador.ControladorSeleccionItem;

import JuegoPokemon.modelo.game.Item;

public interface CondicionDeSeleccionItem {

	Boolean cumpleCondicion(Item item, Integer cantidad);

}
