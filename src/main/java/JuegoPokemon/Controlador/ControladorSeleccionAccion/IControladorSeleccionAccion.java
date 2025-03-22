package JuegoPokemon.Controlador.ControladorSeleccionAccion;

import JuegoPokemon.Controlador.IEncendible;
import JuegoPokemon.Controlador.IComplemento;
import JuegoPokemon.modelo.game.Jugador;

import java.lang.reflect.Method;

public interface IControladorSeleccionAccion extends IComplemento, IEncendible {

	void setRetornoAtaque(Object solicitante, Method retorno);

	void setRetornoCambiarPokemon(Object solicitante, Method retorno);

	void setRetornoUsarItem(Object solicitante, Method retorno);

	void setRetornoRendirse(Object solicitante, Method retorno);

	void setJugador(Jugador jugador);

}
