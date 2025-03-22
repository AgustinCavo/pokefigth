package JuegoPokemon.Controlador.ControladorSeleccionPokemon;

import JuegoPokemon.Controlador.IEncendible;
import JuegoPokemon.Controlador.IComplemento;
import JuegoPokemon.modelo.game.Pokemon;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;

public interface ICuadroPokemonController extends IEncendible, IComplemento {

	void setPokemon(Pokemon pokemon) throws FileNotFoundException;

	void setActivacion(Boolean activacion);

	void setRetorno(Object solicitante, Method retorno);

}
