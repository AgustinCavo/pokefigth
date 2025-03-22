package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.modelo.game.Pokemon;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class PokemonPerdioVida implements Suceso {

	private Stage stage;

	private StackPane stackPane;

	private final Pokemon pokemon;

	private final IControladorJugadores controladorJugador;

	private Label label;

	public PokemonPerdioVida(IControladorJugadores controladorJugador, Pokemon pokemon) {
		this.pokemon = pokemon;
		this.controladorJugador = controladorJugador;
	}

	@Override
	public void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
		this.label.setText("El pokemon " + pokemon.getNombre()  + " recibio un ataque!");
		this.controladorJugador.comenzarEfectoRecibirDamage(pokemon);
		this.controladorJugador.comenzarEfectoVida(pokemon);
		this.stage.show();
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void addLabelMensaje(Label label) {
		this.label = label;
	}
}
