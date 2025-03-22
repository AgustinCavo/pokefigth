package JuegoPokemon.Controlador.ControladorBatalla;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.Controlador.ControladorSucesos.IControladorSucesos;
import JuegoPokemon.Controlador.IEncendible;
import JuegoPokemon.modelo.game.Jugador;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Method;

public interface IControladorLiberarPokemon extends IEncendible {

	void setStage(Stage stage);

	void setJugadoresController(IControladorJugadores jugadoresController);

	void setAviso(Object solicitante, Method aviso);

	void liberarPokemon(Jugador jugador) throws IOException, NoSuchMethodException;

	void setControladorSuceso(IControladorSucesos controladorSucesos);

}
