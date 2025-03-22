package JuegoPokemon.Controlador.ControladorSeleccionItem;

import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Item;

public class PoseePokemonsParaUtilizar implements CondicionDeSeleccionItem {

	private Jugador jugador;

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Override
	public Boolean cumpleCondicion(Item item, Integer cantidad) {
		return !this.jugador.getPokemonsAplicables(item).isEmpty();
	}
}
