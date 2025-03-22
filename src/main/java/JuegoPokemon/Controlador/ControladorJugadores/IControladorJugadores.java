package JuegoPokemon.Controlador.ControladorJugadores;

import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;

import java.io.FileNotFoundException;

public interface IControladorJugadores {

	void setJugadores(Jugador jugadorPrincipal, Jugador jugadorSecundario) throws FileNotFoundException;

	void comenzarEfectoVida(Pokemon pokemon);

	void addIconoEstado(Pokemon pokemon) throws FileNotFoundException;

	void cargarNuevaImagen(Pokemon pokemon) throws FileNotFoundException;

	void comenzarEfectoRecibirDamage(Pokemon pokemon);

	void setJugador(Jugador jugador) throws FileNotFoundException;
}
