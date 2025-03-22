package JuegoPokemon.Controlador.ControladorBatalla;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugador;
import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.Controlador.ControladorSucesos.IControladorSucesos;
import JuegoPokemon.Controlador.IEncendible;
import JuegoPokemon.modelo.game.Jugador;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Method;

public interface IControladorCambiarPokemon extends IEncendible {

	void setStage(Stage stage);

	void setControladorJugadores(IControladorJugadores controladorJugadores);

	void setAviso(Object solicitante, Method aviso);

	void cambiarPokemon(Jugador jugador) throws IOException, NoSuchMethodException;

	void setControladorSucesos(IControladorSucesos controladorSucesos);

}
