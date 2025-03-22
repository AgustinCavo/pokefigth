package JuegoPokemon.Controlador.ControladorSeleccionHabilidad;

import JuegoPokemon.Controlador.IEncendible;
import JuegoPokemon.Controlador.IComplemento;
import JuegoPokemon.Controlador.IMostrable;
import JuegoPokemon.modelo.game.Jugador;

import java.lang.reflect.Method;

public interface IControladorSeleccionHabilidad extends IEncendible, IComplemento {

	void setJugador(Jugador jugador) throws NoSuchMethodException;

	void setSalida(Object solicitante, Method salida);

	void setRetorno(Object solicitante, Method retorno);

}
