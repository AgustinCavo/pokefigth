package JuegoPokemon.Controlador.ControladorPartida;

import JuegoPokemon.Controlador.ControladorBatalla.ControladorLiberarPokemon;
import JuegoPokemon.Controlador.ControladorBatalla.IControladorLiberarPokemon;
import JuegoPokemon.modelo.controladores.ControladorJuego;
import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Juego;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ControladorPartida implements IControladorPartida {

	private final IControladorLiberarPokemon liberarPokemon;

	private final ControladorJuego controladorJuego;

	private final Stage stage;

	private final Scene scene;

	private final List<Jugador> jugadores;

	private Integer contJugador;

	@FXML
	private ImageView boton;

	public ControladorPartida(Juego juego, Stage stage, ControladorJuego controladorJuego) throws IOException, NoSuchMethodException {
		this.liberarPokemon = new ControladorLiberarPokemon(juego);
		this.liberarPokemon.encender();
		this.liberarPokemon.setAviso(this, getClass().getMethod("liberoPokemon", Boolean.class));

		this.stage = stage;
		this.liberarPokemon.setStage(stage);

		File file = new File("src/main/resources/Partida/PartidaView.fxml");
		FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
		loader.setController(this);
		this.scene = new Scene(loader.load());

		this.jugadores = juego.getJugadores();
		this.contJugador = 0;
		this.controladorJuego = controladorJuego;
	}

	@Override
	public void cargarPantallaInicio() {
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public void comenzarLiberandoPokemons() throws IOException, NoSuchMethodException {
		Jugador jugador = this.jugadores.get(contJugador);
		this.liberarPokemon.liberarPokemon(jugador);
	}

	public void liberoPokemon(Boolean bol) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		if (this.contJugador != 1) {
			this.contJugador++;
			this.comenzarLiberandoPokemons();
		} else this.controladorJuego.ejecutar();
	}

	@FXML
	private void clickBoton() throws IOException, NoSuchMethodException {
		this.comenzarLiberandoPokemons();
	}

	@FXML
	private void entroBoton() {
		this.boton.setOpacity(1.0);
	}

	@FXML
	private void salioBoton() {
		this.boton.setOpacity(0.85);
	}

}
