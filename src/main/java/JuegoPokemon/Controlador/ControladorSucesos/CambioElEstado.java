package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CambioElEstado implements Suceso {

	private final IControladorJugadores controladorJugadores;

	private final Map<EstadoEnum, String> respuestasNuevoEstado;

	private final Pokemon pokemon;

	private final EstadoEnum estadoEnum;

	private Stage stage;

	private Label label;

	public CambioElEstado(IControladorJugadores controladorJugadores, EstadoEnum estadoEnum, Pokemon pokemon) {
		this.controladorJugadores = controladorJugadores;
		this.estadoEnum= estadoEnum;
		this.pokemon = pokemon;
		this.respuestasNuevoEstado = new HashMap<>();
		respuestasNuevoEstado.put(EstadoEnum.Normal, pokemon.getNombre() + " regresa a su estado Normal");
		respuestasNuevoEstado.put(EstadoEnum.Envenenado, pokemon.getNombre() + " se a envenenado");
		respuestasNuevoEstado.put(EstadoEnum.Dormido, pokemon.getNombre() + " se tomara una siesta");
		respuestasNuevoEstado.put(EstadoEnum.Paralizado, pokemon.getNombre() + " se a paralizado");
		respuestasNuevoEstado.put(EstadoEnum.Confuso, pokemon.getNombre() + " se a confundido");
		respuestasNuevoEstado.put(EstadoEnum.Debilitado, pokemon.getNombre() + " no puede seguir en la batalla");

	}

	@Override
	public void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
		this.label.setText(this.respuestasNuevoEstado.get(this.estadoEnum));
		this.controladorJugadores.addIconoEstado(pokemon);
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
