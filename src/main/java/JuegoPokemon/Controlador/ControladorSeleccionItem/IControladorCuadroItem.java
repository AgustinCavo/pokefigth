package JuegoPokemon.Controlador.ControladorSeleccionItem;

import JuegoPokemon.Controlador.IEncendible;
import JuegoPokemon.Controlador.IComplemento;
import JuegoPokemon.modelo.game.Item;

import java.lang.reflect.Method;

public interface IControladorCuadroItem extends IEncendible, IComplemento {

	void setItem(Item item, Integer cantidad);

	void setMostrarDatos(Object receptor, Method mostrar);

	void setEliminarDatos(Object receptor, Method deleteDatos);

	void setRetorno(Object solicitante, Method retorno);

	void setUtilizable(Boolean bool);

}
