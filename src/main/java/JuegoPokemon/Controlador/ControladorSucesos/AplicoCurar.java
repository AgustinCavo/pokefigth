package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.efectos.Efecto;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class AplicoCurar implements Suceso {

	private final IControladorJugadores controladorJugadores;

	private final Double curacion;

	private final Pokemon pokemon;

	private Stage stage;

	private Label label;

	public AplicoCurar(IControladorJugadores controladorJugadores, Double curacion, Pokemon pokemon) {
		this.curacion = curacion;
		this.controladorJugadores = controladorJugadores;
		this.pokemon = pokemon;
	}

	@Override
	public void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
		this.label.setText("El pokemon " + pokemon.getNombre() + " obtuvo una curacion por un total de " + curacion + " HP");
		this.controladorJugadores.comenzarEfectoVida(this.pokemon);
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

