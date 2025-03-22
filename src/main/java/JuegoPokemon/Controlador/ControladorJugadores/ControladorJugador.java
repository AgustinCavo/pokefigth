package JuegoPokemon.Controlador.ControladorJugadores;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class ControladorJugador implements IControladorJugador {

	@FXML
	protected HBox pokeballs;

	@FXML
	protected HBox estados;

	@FXML
	protected ProgressBar barraVida;

	@FXML
	protected Label numVida;

	@FXML
	protected ImageView imagenPokemon;

	protected Jugador jugador;

	protected Pokemon pokemon;

	protected void setPokeballs(Jugador jugador) throws FileNotFoundException {
		this.jugador = jugador;
		this.pokemon = jugador.getPokemonSuelto();
		this.pokeballs.getChildren().clear();
		int cantPokemons = jugador.getPokemonsVivos().size();
		for (int i = 0; i < Constantes.CANTIDAD_DE_POKEMONS_POR_JUGADOR; i++) {
			ObservableList<Node> observableList = this.pokeballs.getChildren();
			if (i < cantPokemons) {
				observableList.add(new ImageView(new Image(new FileInputStream("src/main/resources/Jugadores/Pokeball.png"))));
			} else {
				observableList.add(new ImageView(new Image(new FileInputStream("src/main/resources/Jugadores/PokeballVacia.png"))));
			}
		}
	}

	protected void setEstados(Jugador jugador) throws FileNotFoundException {
		this.estados.getChildren().clear();
		ObservableList<Node> observableList = this.estados.getChildren();
		for (EstadoEnum estado : jugador.getEstadosPokemonSuelto()) {
			observableList.add(new ImageView(new Image(new FileInputStream("src/main/resources/Estados/" + estado + ".png"))));
		}
	}

	@Override
	public void iniciarEfectoVida() {
		Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		timeline.setAutoReverse(false);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
				new KeyValue(barraVida.progressProperty(), pokemon.getVida()/pokemon.getVidaMaxima())));
		timeline.play();
		this.barraVida.setProgress(this.pokemon.getVida() / this.pokemon.getVidaMaxima());
		this.numVida.setText(this.pokemon.getVida().toString());
	}

	@Override
	public void cargarEfectoRecibirDamage() {

		TranslateTransition transition = new TranslateTransition();
		transition.setNode(this.imagenPokemon);
		transition.setDuration(Duration.millis(100));
		transition.setCycleCount(10);
		transition.setByX(10);
		transition.setAutoReverse(true);
		transition.play();

	}

}
