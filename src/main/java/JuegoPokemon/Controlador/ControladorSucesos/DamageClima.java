package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.Controlador.ControladorCampoBatalla.IControladorCampoBatalla;
import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.clima.ClimaEnum;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class DamageClima implements Suceso {

	private IControladorJugadores controladorJugadores;

	private final ClimaEnum clima;

	private final Pokemon pokemon;

	private final Double damage;

	private Stage stage;

	private Label label;

	public DamageClima(IControladorJugadores controladorJugadores, ClimaEnum climaEnum, Pokemon pokemon, Double damage) {
		this.controladorJugadores = controladorJugadores;
		this.clima = climaEnum;
		this.pokemon = pokemon;
		this.damage = damage;
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

		this.label.setText(pokemon.getNombre()+ " recibe" + damage + "de daño por el  clima" + clima.name());
		this.controladorJugadores.comenzarEfectoRecibirDamage(pokemon);
		this.stage.show();
	}

}


