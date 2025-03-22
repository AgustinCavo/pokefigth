package JuegoPokemon.Controlador.ControladorBatalla;

import JuegoPokemon.modelo.game.Jugador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

public interface IControladorBatalla {

	void setRetorno(Object solicitante, Method retorno);

	void realizarAccion() throws IOException, NoSuchMethodException;

	void liberarPokemon() throws IOException, NoSuchMethodException;

	void mostrarGanador(Jugador jugador) throws FileNotFoundException;

}
