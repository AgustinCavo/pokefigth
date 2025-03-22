package JuegoPokemon.Controlador.ControladorJugadores;

import JuegoPokemon.modelo.game.Jugador;

import java.io.FileNotFoundException;

public interface IControladorJugador {

	void setJugador(Jugador jugador) throws FileNotFoundException;

	void iniciarEfectoVida();

	void cargarNuevaImagen() throws FileNotFoundException;

	void cargarEfectoRecibirDamage();

}
