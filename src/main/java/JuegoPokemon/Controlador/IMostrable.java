package JuegoPokemon.Controlador;

import javafx.stage.Stage;

import java.io.IOException;

public interface IMostrable {

	void setStage(Stage stage);

	void mostrar() throws IOException, NoSuchMethodException;

}
