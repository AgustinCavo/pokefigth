package JuegoPokemon.Controlador.ControladorJugadores;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class ControladorJugadorSuperior extends ControladorJugador {

	private Pokemon pokemonGuardado;

	@FXML
	private Label nombre;

	public void initialize() {
		Font font = new Font(Constantes.TAMAÑOLETRA);
		this.nombre.setFont(font);
		this.numVida.setFont(font);
	}

	@Override
	public void setJugador(Jugador jugador) throws FileNotFoundException {
		this.pokemonGuardado = jugador.getPokemonSuelto();
		this.imagenPokemon.setImage(new Image(new FileInputStream("src/main/resources/PokemonsFrontales/" + jugador.getPokemonSuelto().getNombre() + ".png")));
		this.nombre.setText(jugador.getPokemonSuelto().getNombre());
		this.barraVida.setProgress(jugador.getVida()/jugador.getVidamaxima());
		this.numVida.setText(jugador.getVida().toString());
		this.setPokeballs(jugador);
		this.setEstados(jugador);
	}

	@Override
	public void cargarNuevaImagen() throws FileNotFoundException {
		this.imagenPokemon.setImage(new Image(new FileInputStream("src/main/resources/PokemonsFrontales/" + jugador.getPokemonSuelto().getNombre() + ".png")));
	}

}
