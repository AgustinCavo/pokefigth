package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.modelo.game.efectos.Efecto;
import JuegoPokemon.modelo.game.Item;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.efectos.ModificarEstadistica;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class ModificoEstadistica implements Suceso {

	private final Pokemon pokemon;

	private final Efecto efecto;

	private Stage stage;

	private Label label;

	public ModificoEstadistica(Efecto efecto, Pokemon pokemon) {
		this.efecto = efecto;
		this.pokemon = pokemon;
	}

	@Override
	public void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
		ModificarEstadistica modificoEstadistica = (ModificarEstadistica) this.efecto;
		this.label.setText("Aumento el "+ modificoEstadistica.getEstadistica() + " en " + efecto.potenciaEfecto(this.pokemon));
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

