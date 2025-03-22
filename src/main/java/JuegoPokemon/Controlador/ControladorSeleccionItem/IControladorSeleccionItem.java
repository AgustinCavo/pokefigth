package JuegoPokemon.Controlador.ControladorSeleccionItem;

import JuegoPokemon.Controlador.IEncendible;
import JuegoPokemon.Controlador.IMostrable;
import JuegoPokemon.modelo.game.Jugador;

import java.io.IOException;
import java.lang.reflect.Method;

public interface IControladorSeleccionItem extends IEncendible, IMostrable {

	void setRetorno(Object solicitante, Method retorno);

	void setSalida(Object solicitante, Method salida);

	void setJugador(Jugador jugador) throws NoSuchMethodException, IOException;

	void addCondicionSeleccionItem(CondicionDeSeleccionItem condicion);

	void limpiarCondiciones();

}
