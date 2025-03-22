package JuegoPokemon.Controlador.ControladorCampoBatalla;

import JuegoPokemon.modelo.game.clima.ClimaEnum;

import java.io.FileNotFoundException;

public interface IControladorCampoBatalla {

	void setClima(ClimaEnum clima) throws FileNotFoundException;

	ClimaEnum getClimaVicible();

}
