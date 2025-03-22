package JuegoPokemon.Controlador.ControladorBatalla;

import JuegoPokemon.Controlador.ControladorCampoBatalla.IControladorCampoBatalla;
import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.Controlador.ControladorSucesos.IControladorSucesos;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.lang.reflect.Method;

public interface IControladorAtacar {

    void setStage(Stage stage);

    void setBarraInferior(StackPane stackPane);

    void setAviso(Object solicitante, Method avisar);

    void realizarAtaque() throws NoSuchMethodException;

    void setControladorCampoBatalla(IControladorCampoBatalla controladorCampoBatalla);

	void setControladorSucesos(IControladorSucesos controladorSucesos);

    void setControladorJugadores(IControladorJugadores controladorJugadores);
}
