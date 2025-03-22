package JuegoPokemon.Controlador.ControladorConsultarRendicion;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Juego;
import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.acciones.Accion;
import JuegoPokemon.modelo.game.acciones.FactoryAcciones;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class ControladorConsultarRendicion implements IControladorConsultarRendicion {

	private final FXMLLoader loader;

	private StackPane barraInferior;

	@FXML
	private Label mensaje;

	@FXML
	private Label labelSi;

	@FXML
	private Label labelNo;

	@FXML
	private ImageView cuadroSi;

	@FXML
	private ImageView cuadroNo;

	private Image cuadroSeleccionado;

	private Image cuadroNoSeleccionado;

	private Object receptor;

	private Method aviso;

	private Jugador jugador;

	private final Juego juego;

	public void initialize() throws FileNotFoundException {
		this.cuadroSeleccionado = new Image(new FileInputStream("src/main/resources/SeleccionHabilidad/CuadroHabilidadSeleccionada.png"));
		this.cuadroNoSeleccionado = new Image(new FileInputStream("src/main/resources/SeleccionHabilidad/CuadroHabilidadSinSeleccionar.png"));
		Font font = new Font(Constantes.TAMAÑOLETRA);
		this.mensaje.setFont(font);
		this.labelSi.setFont(font);
		this.labelNo.setFont(font);
	}

	public ControladorConsultarRendicion(Juego juego) throws IOException {
		File file = new File("src/main/resources/ConsultarRendicion/ConsultarRendicionView.fxml");
		this.loader = new FXMLLoader(file.toURI().toURL());
		this.loader.setController(this);
		this.loader.load();
		this.juego = juego;
	}

	@Override
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
		this.mensaje.setText("¿Esta seguro que quiere rendirse " + jugador.getNombre() + "?");
	}

	@Override
	public void setAviso(Object repector, Method aviso) {
		this.receptor = repector;
		this.aviso = aviso;
	}

	@Override
	public Object getRoot() {
		return this.loader.getRoot();
	}

	@FXML
	private void clickSi() throws InvocationTargetException, IllegalAccessException {
		Accion rendirse = FactoryAcciones.crearAccionRendirse(this.jugador);
		this.juego.realizarAccion(rendirse);
		this.aviso.invoke(this.receptor, true);
	}

	@FXML
	private void clickNo() throws InvocationTargetException, IllegalAccessException {
		this.aviso.invoke(this.receptor, false);
	}

	@FXML
	private void ingresoNo() {
		this.cuadroNo.setImage(this.cuadroSeleccionado);
	}

	@FXML
	private void salioNo() {
		this.cuadroNo.setImage(this.cuadroNoSeleccionado);
	}

	@FXML
	private void ingresoSi() {
		this.cuadroSi.setImage(this.cuadroSeleccionado);
	}

	@FXML
	private void salioSi() {
		this.cuadroSi.setImage(this.cuadroNoSeleccionado);
	}

}
