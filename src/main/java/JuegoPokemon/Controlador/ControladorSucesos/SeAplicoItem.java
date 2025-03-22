package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.Controlador.ControladorCampoBatalla.IControladorCampoBatalla;
import JuegoPokemon.modelo.game.Item;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class SeAplicoItem implements Suceso {
	private Stage stage;

	private StackPane stackPane;

	private final Item itemUsado;

	private Label label;

	public SeAplicoItem(Item itemUsado) {
		this.itemUsado = itemUsado;
	}

	@Override
	public void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
		this.label.setText("Se esta utilizando " + this.itemUsado.getNombre());
		this.stage.show();
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void addLabelMensaje(Label label) {
		this.label = label;
	}
}
