package JuegoPokemon.Controlador.ControladorSeleccionHabilidad;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.Habilidad;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControladorSeleccionHabilidad implements IControladorSeleccionHabilidad {

	private FXMLLoader loader;

	private Jugador jugador;

	private Object objectSalida;

	private Method salida;

	private Object solicitante;

	private Method retorno;

	@FXML
	private ImageView botonSalir;

	@FXML
	private Label info;

	@FXML
	private ControladorBotonHabilidad cuadro1Controller;

	@FXML
	private ControladorBotonHabilidad cuadro2Controller;

	@FXML
	private ControladorBotonHabilidad cuadro3Controller;

	@FXML
	private ControladorBotonHabilidad cuadro4Controller;

	private List<ControladorBotonHabilidad> subControladores;

	public void initialize() {
		this.subControladores = new ArrayList<>();
		this.subControladores.add(this.cuadro1Controller);
		this.subControladores.add(this.cuadro2Controller);
		this.subControladores.add(this.cuadro3Controller);
		this.subControladores.add(this.cuadro4Controller);
		Font font = new Font(Constantes.TAMAÑOLETRA);
		this.info.setFont(font);
	}

	@Override
	public void encender() throws IOException {
		File file = new File("src/main/resources/SeleccionHabilidad/SeleccionHabilidadView.fxml");
		this.loader = new FXMLLoader(file.toURI().toURL());

		this.loader.setController(this);
		this.loader.load();
	}

	@Override
	public void setSalida(Object solicitante, Method salida) {
		this.objectSalida = solicitante;
		this.salida = salida;
	}

	@Override
	public void setRetorno(Object solicitante, Method retorno) {
		this.solicitante = solicitante;
		this.retorno = retorno;
	}

	@Override
	public void setJugador(Jugador jugador) throws NoSuchMethodException {
		this.jugador = jugador;
		Pokemon suelto = jugador.getPokemonSuelto();
		if (suelto != null)
			this.crearCuadros(suelto);
	}

	private void crearCuadros(Pokemon pokemon) throws NoSuchMethodException {
		for (int i = 0; i < pokemon.getHabilidades().size(); i++) {
			IControladorBotonHabilidad subcontrol = this.subControladores.get(i);
			subcontrol.setHabilidad(pokemon.getHabilidades().get(i));
			subcontrol.setRetorno(this.solicitante, this.retorno);
			subcontrol.setInfoEntrada(this, this.getClass().getMethod("mostrarInfo", Habilidad.class));
			subcontrol.setDeleteInfo(this, this.getClass().getMethod("deleteInfo"));
		}
	}

	public void mostrarInfo(Habilidad habilidad) {
		this.info.setText(habilidad.getNombre() + " |  Cantidad: " + habilidad.getCantidadUsos().toString());
	}

	public void deleteInfo() {
		this.info.setText("");
	}

	@Override
	public Object getRoot() {
		return this.loader.getRoot();
	}

	@FXML
	private void clickSalir() throws InvocationTargetException, IllegalAccessException {
		this.salida.invoke(this.objectSalida);
	}

	@FXML
	private void entroSalir() {
		this.botonSalir.setOpacity(1.0);
	}

	@FXML
	private void salioSalir() {
		this.botonSalir.setOpacity(0.8);
	}

}
