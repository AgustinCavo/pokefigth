package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.Controlador.ControladorCampoBatalla.IControladorCampoBatalla;
import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CambioEstadoContrario implements Suceso{

	private Stage stage;

	private StackPane stackPane;

	private final IControladorJugadores controladorJugadores;

	private final EstadoEnum estadoNuevo;

	private final Pokemon pokemonQueCambio;

	private Label label;

	public CambioEstadoContrario(IControladorJugadores controladorJugadores, Pokemon pokemonQueCambio, EstadoEnum estadoNuevo) {
		this.controladorJugadores = controladorJugadores;
		this.pokemonQueCambio = pokemonQueCambio;
		this.estadoNuevo = estadoNuevo;
	}

	@Override
	public void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
		this.label.setText("El ataque afecto a " + pokemonQueCambio.getNombre()+ " ahora esta " + this.estadoNuevo);
		this.controladorJugadores.addIconoEstado(this.pokemonQueCambio);
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
