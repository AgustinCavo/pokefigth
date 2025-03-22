package JuegoPokemon.Controlador.ControladorBatalla;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.Controlador.ControladorSucesos.IControladorSucesos;
import JuegoPokemon.modelo.game.Jugador;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Method;

public interface IControladorUsarItem {

    void setAviso(Object solicitante, Method avisar);

    void setStage(Stage stage);

    void usarItem(Jugador jugador) throws IOException, NoSuchMethodException;

    void setControladorJugadores(IControladorJugadores controladorJugadores);

    void setControladorSuceso(IControladorSucesos controladorSucesos);

}