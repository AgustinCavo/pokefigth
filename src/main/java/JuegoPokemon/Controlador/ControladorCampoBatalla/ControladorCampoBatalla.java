package JuegoPokemon.Controlador.ControladorCampoBatalla;

import JuegoPokemon.modelo.game.clima.ClimaEnum;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ControladorCampoBatalla implements IControladorCampoBatalla {

	@FXML
	private ImageView fondo;

	@FXML
	private ImageView plataformaInferior;

	@FXML
	private ImageView plataformaSuperior;

	private ClimaEnum climaVicible;

	@Override
	public void setClima(ClimaEnum clima) throws FileNotFoundException {
		this.climaVicible = clima;
		this.fondo.setImage(new Image(new FileInputStream("src/main/resources/Escenarios/" + clima + ".png")));
		this.plataformaInferior.setImage(new Image(new FileInputStream("src/main/resources/PlataformasInferiores/" + clima + ".png")));
		this.plataformaSuperior.setImage(new Image(new FileInputStream("src/main/resources/PlataformasSuperiores/" + clima + ".png")));
	}

	@Override
	public ClimaEnum getClimaVicible() {
		return climaVicible;
	}

}
