package JuegoPokemon.Controlador.ControladorSeleccionPokemon;

import JuegoPokemon.Controlador.IEncendible;
import JuegoPokemon.Controlador.IMostrable;
import JuegoPokemon.modelo.game.Jugador;

import java.io.IOException;
import java.lang.reflect.Method;

public interface IControladorSeleccionarPokemon extends IEncendible, IMostrable {

	void setJugador(Jugador jugador) throws IOException, NoSuchMethodException;

	void setRetorno(Object solicitante, Method retorno);

	void setSalida(Object objectSalida, Method metodoSalida);

	void setVisibilidadSalida(Boolean bolean);

	void addCondicionSeleccion(CondicionDeSeleccionPokemon condicion);

	void limpiarCondiciones();

}
