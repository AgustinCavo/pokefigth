package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class SucesoLiberoPokemon implements Suceso {

	private final IControladorJugadores controladorJugadores;

	private final Jugador jugador;

	private Stage stage;

	private Label label;

	public SucesoLiberoPokemon(IControladorJugadores controladorJugadores, Jugador jugador) {
		this.controladorJugadores = controladorJugadores;
		this.jugador = jugador;
	}

	@Override
	public void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
		this.label.setText("Se libero a " + jugador.getPokemonSuelto().getNombre());
		this.controladorJugadores.setJugador(jugador);
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
