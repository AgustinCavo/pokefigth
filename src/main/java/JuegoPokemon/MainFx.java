package JuegoPokemon;

import JuegoPokemon.modelo.game.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFx extends Application {

	@Override
	public void start(Stage stage) throws IOException, NoSuchMethodException {
		stage.setTitle("Batalla Pokemon");
		stage.setResizable(false);
		Partida partida = new Partida(stage);
		partida.ejecutarPartida();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
