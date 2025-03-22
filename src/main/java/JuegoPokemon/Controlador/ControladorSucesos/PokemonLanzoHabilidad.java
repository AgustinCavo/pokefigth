package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.modelo.game.Habilidad;
import JuegoPokemon.modelo.game.Pokemon;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class PokemonLanzoHabilidad implements Suceso {

	private final Pokemon pokemon;

	private final Habilidad habilidad;

	private Stage stage;

	private Label mensaje;

	public PokemonLanzoHabilidad(Pokemon pokemon, Habilidad habilidad) {
		this.pokemon = pokemon;
		this.habilidad = habilidad;
	}

	@Override
	public void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
		this.mensaje.setText(pokemon.getNombre() + " lanzo " + habilidad.getNombre());
		this.stage.show();
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void addLabelMensaje(Label label) {
		this.mensaje = label;
	}
}
