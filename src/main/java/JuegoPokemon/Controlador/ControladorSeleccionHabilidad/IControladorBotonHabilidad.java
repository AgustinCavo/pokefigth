package JuegoPokemon.Controlador.ControladorSeleccionHabilidad;

import JuegoPokemon.modelo.game.Habilidad;

import java.lang.reflect.Method;

public interface IControladorBotonHabilidad {

	void setHabilidad(Habilidad habilidad);

	void setRetorno(Object solicitante, Method retorno);

	void setInfoEntrada(Object receptor, Method mostrar);

	void setDeleteInfo(Object receptor, Method deleteInfo);

}
