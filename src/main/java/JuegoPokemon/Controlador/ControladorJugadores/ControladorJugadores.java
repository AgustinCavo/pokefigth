package JuegoPokemon.Controlador.ControladorJugadores;

import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;
import javafx.fxml.FXML;
import javafx.scene.Parent;

import java.io.FileNotFoundException;

public class ControladorJugadores implements IControladorJugadores {

	@FXML
	private ControladorJugadorInferior jugadorInferiorController;

	@FXML
	private ControladorJugadorSuperior jugadorSuperiorController;

	private Jugador jugadorPrincipal;

	private Jugador jugadorSecundario;

	private Parent root;

	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void setJugadores(Jugador jugadorPrincipal, Jugador jugadorSecundario) throws FileNotFoundException {
		this.jugadorPrincipal = jugadorPrincipal;
		this.jugadorSecundario = jugadorSecundario;
		this.setDatosJugadores();
	}

	@Override
	public void comenzarEfectoVida(Pokemon pokemon) {
		if (this.jugadorPrincipal.getPokemonSuelto().equals(pokemon))
			this.jugadorInferiorController.iniciarEfectoVida();
		else if (this.jugadorSecundario.getPokemonSuelto().equals(pokemon))
			this.jugadorSuperiorController.iniciarEfectoVida();
	}

	@Override
	public void addIconoEstado(Pokemon pokemon) throws FileNotFoundException {
		if (this.jugadorPrincipal.getPokemonSuelto().equals(pokemon))
			this.jugadorInferiorController.setEstados(this.jugadorPrincipal);
		else this.jugadorSuperiorController.setEstados(this.jugadorSecundario);
	}

	private void setDatosJugadores() throws FileNotFoundException {
		this.jugadorSuperiorController.setJugador(this.jugadorSecundario);
		this.jugadorInferiorController.setJugador(this.jugadorPrincipal);
	}

	@Override
	public void cargarNuevaImagen(Pokemon pokemon) throws FileNotFoundException {
		if (this.jugadorPrincipal.getPokemonSuelto().equals(pokemon))
			this.jugadorInferiorController.cargarNuevaImagen();
		else this.jugadorSuperiorController.cargarNuevaImagen();
	}

	@Override
	public void comenzarEfectoRecibirDamage(Pokemon pokemon) {
		if (this.jugadorPrincipal.getPokemonSuelto().equals(pokemon))
			this.jugadorInferiorController.cargarEfectoRecibirDamage();
		else this.jugadorSuperiorController.cargarEfectoRecibirDamage();
	}

	@Override
	public void setJugador(Jugador jugador) throws FileNotFoundException {
		if (this.jugadorPrincipal.equals(jugador))
			this.jugadorInferiorController.setJugador(jugador);
		else this.jugadorSuperiorController.setJugador(jugador);
	}

}
