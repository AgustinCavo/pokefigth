package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.clima.ClimaEnum;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class DamageEstado implements Suceso {
	private IControladorJugadores controladorJugadores;

	private final List<EstadoEnum> estados;

	private final Pokemon pokemon;

	private final Double damage;

	private Stage stage;

	private Label label;

	public DamageEstado(IControladorJugadores controladorJugadores, Pokemon pokemon, Double damage, List<EstadoEnum> estados) {
		this.controladorJugadores = controladorJugadores;
		this.pokemon = pokemon;
		this.damage = damage;
		this.estados = estados;
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void addLabelMensaje(Label label) {
		this.label = label;
	}

	@Override
	public void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {

		this.label.setText(pokemon.getNombre()+ " recibe" + damage + "de daño por el estado" + estados.get(0));
		this.controladorJugadores.comenzarEfectoRecibirDamage(pokemon);
		this.stage.show();
	}

}
