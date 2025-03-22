package JuegoPokemon.Controlador.ControladorSucesos;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface IControladorSucesos {

	void addSucesoSiguiente(Suceso suceso);

	void setFinalizacion(Object objetoFinal, Method metodoFinal);

	void iniciarSucesos() throws InvocationTargetException, IllegalAccessException, FileNotFoundException;

	void limpiarSucesos();

}
