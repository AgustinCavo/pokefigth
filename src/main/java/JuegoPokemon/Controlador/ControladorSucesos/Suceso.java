package JuegoPokemon.Controlador.ControladorSucesos;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public interface Suceso {

	void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException;

	void setStage(Stage stage);

	void addLabelMensaje(Label label);

}
