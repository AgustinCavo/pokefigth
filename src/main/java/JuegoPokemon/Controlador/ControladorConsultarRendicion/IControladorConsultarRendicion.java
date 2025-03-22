package JuegoPokemon.Controlador.ControladorConsultarRendicion;

import JuegoPokemon.modelo.game.Jugador;

import java.lang.reflect.Method;

public interface IControladorConsultarRendicion {

	void setAviso(Object repector, Method aviso);

	void setJugador(Jugador jugador);

	Object getRoot();

}
