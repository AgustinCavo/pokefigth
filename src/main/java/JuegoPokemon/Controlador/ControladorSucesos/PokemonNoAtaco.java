package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.Controlador.ControladorCampoBatalla.IControladorCampoBatalla;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class PokemonNoAtaco implements Suceso{

	private Stage stage;

	private StackPane stackPane;

	private final IControladorCampoBatalla controladorCampoBatalla;

	private final List<EstadoEnum> estadosBloqueadores;

	private final Pokemon pokemonQueFallo;

	private Label label;

	public PokemonNoAtaco(IControladorCampoBatalla controladorCampoBatalla, List<EstadoEnum> estadosBloqueadores, Pokemon pokemonQueFallo) {
		this.estadosBloqueadores = estadosBloqueadores;
		this.pokemonQueFallo = pokemonQueFallo;
		this.controladorCampoBatalla = controladorCampoBatalla;
	}

	@Override
	public void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
		String cadena = "";
		for (EstadoEnum estado : estadosBloqueadores) {
			cadena += estado + ",";
		}
		String cadenaModificada = cadena.substring(0, cadena.length() - 1);
		this.label.setText(pokemonQueFallo.getNombre()+ " no pudo atacar ya que se encuentra " + cadenaModificada);
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
