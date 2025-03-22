package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.Controlador.ControladorCampoBatalla.IControladorCampoBatalla;
import JuegoPokemon.modelo.game.clima.ClimaEnum;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CambioClima implements Suceso {

	private static Map<ClimaEnum, String> respuestasANuevoClima;

	private static Map<ClimaEnum, String> respuestasAPerdidaClima;

	private final IControladorCampoBatalla controladorCampoBatalla;

	private final ClimaEnum nuevoClima;

	private Stage stage;

	private Label label;

	public CambioClima(IControladorCampoBatalla controladorCampoBatalla, ClimaEnum climaEnum) {
		this.controladorCampoBatalla = controladorCampoBatalla;
		this.nuevoClima = climaEnum;

		if (respuestasANuevoClima == null)
			respuestasANuevoClima = this.cargarRespuestasANuevosClimas();

		if (respuestasAPerdidaClima == null)
			respuestasAPerdidaClima = this.cargarRespuestasAPerdidaClima();

	}

	private Map<ClimaEnum, String> cargarRespuestasANuevosClimas() {
		Map<ClimaEnum, String> respuestasANuevoClima = new HashMap<>();
		respuestasANuevoClima.put(ClimaEnum.Soleado, "El sol y su calor abrasa el dia de hoy!");
		respuestasANuevoClima.put(ClimaEnum.Lluvia, "Ha comenzado a llover!");
		respuestasANuevoClima.put(ClimaEnum.Huracan, "Los vientos estan imparables, un Huracan ha comenzado!!");
		respuestasANuevoClima.put(ClimaEnum.Niebla, "Una neblina se prenta en el campo de batalla!");
		respuestasANuevoClima.put(ClimaEnum.TormentaDeArena, "Una tormenta de arena inunda el campo de batalla!");
		respuestasANuevoClima.put(ClimaEnum.TormentaDeRayos, "Un campo electrico se precenta en el campo de batalla!!");
		return respuestasANuevoClima;
	}

	private Map<ClimaEnum, String> cargarRespuestasAPerdidaClima() {
		Map<ClimaEnum, String> respuestasAPerdidaClima = new HashMap<>();
		respuestasAPerdidaClima.put(ClimaEnum.Soleado, "Las nubes cubren el Sol el calor comienza a seder!");
		respuestasAPerdidaClima.put(ClimaEnum.Lluvia, "Ha terminado de llover!");
		respuestasAPerdidaClima.put(ClimaEnum.Niebla, "La neblina a terminado!");
		respuestasAPerdidaClima.put(ClimaEnum.Huracan, "Los vientos se calma, el Huracan a termiando!!");
		respuestasAPerdidaClima.put(ClimaEnum.TormentaDeArena, "Ha terminado al Tormenta de Arena!");
		respuestasAPerdidaClima.put(ClimaEnum.TormentaDeRayos, "El campo de batalla se a descargado!!");
		return respuestasAPerdidaClima;
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void addLabelMensaje(Label label) {
		this.label = label;
	}

	@Override
	public void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
		ClimaEnum climaVisible = this.controladorCampoBatalla.getClimaVicible();
		this.controladorCampoBatalla.setClima(this.nuevoClima);

		if (nuevoClima.equals(ClimaEnum.SinClima))
			this.label.setText(respuestasAPerdidaClima.get(climaVisible));
		else{
			this.label.setText(respuestasANuevoClima.get(this.nuevoClima));
		}

		this.stage.show();
	}

}
