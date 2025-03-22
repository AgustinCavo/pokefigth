package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.modelo.Constantes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControladorSucesos implements IControladorSucesos {

	private final StackPane barraInferior;

	private final Scene scene;

	private final Parent parent;

	private final Stage stage;

	private final List<Suceso> sucesos;

	@FXML
	private ImageView botonSaltear;

	@FXML
	private ImageView botonContinuar;

	private Object objetoFinal;

	private Method accionFinal;

	private Integer contSucesos;

	@FXML
	private Label mensaje;

	private Image imagenContinuarSeleccionado;

	private Image imagenContinuarNoSeleccionado;

	private Image imagenSaltearSeleccionado;

	private Image imagenSaltearNoSeleccionado;

	public void initialize() throws FileNotFoundException {
		this.imagenContinuarSeleccionado = new Image(new FileInputStream("src/main/resources/Sucesos/BotonContinuarSeleccionado.png"));
		this.imagenContinuarNoSeleccionado = new Image(new FileInputStream("src/main/resources/Sucesos/BotonContinuarNoSeleccionado.png"));
		this.imagenSaltearNoSeleccionado = new Image(new FileInputStream("src/main/resources/Sucesos/BotonSaltearNoSeleccionado.png"));
		this.imagenSaltearSeleccionado = new Image(new FileInputStream("src/main/resources/Sucesos/BotonSaltearSeleccionado.png"));
		Font font = new Font(Constantes.TAMAÑOLETRA);
		this.mensaje.setFont(font);
	}

	public ControladorSucesos(Stage stage, StackPane stackPane, Scene scene) throws IOException {
		File file = new File("src/main/resources/Sucesos/SucesosView.fxml");
		FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
		loader.setController(this);
		this.parent = loader.load();
		this.scene = scene;
		this.stage = stage;
		this.barraInferior = stackPane;
		this.sucesos = new ArrayList<>();
		this.contSucesos = 0;
	}

	@Override
	public void addSucesoSiguiente(Suceso suceso) {
		suceso.addLabelMensaje(this.mensaje);
		suceso.setStage(this.stage);
		this.sucesos.add(suceso);
	}

	@Override
	public void limpiarSucesos() {
		this.sucesos.clear();
	}

	@Override
	public void setFinalizacion(Object objetoFinal, Method accionFinal) {
		this.accionFinal = accionFinal;
		this.objetoFinal = objetoFinal;
	}

	@Override
	public void iniciarSucesos() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
		if (!this.sucesos.isEmpty()) {
			this.barraInferior.getChildren().clear();
			this.barraInferior.getChildren().add(this.parent);

			Suceso suceso = this.sucesos.get(0);
			this.contSucesos++;
			this.stage.setScene(this.scene);
			suceso.mostrarSuceso();
		} else this.accionFinal.invoke(this.objetoFinal);
	}

	@FXML
	private void continuar() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
		if (this.contSucesos < this.sucesos.size()) {
			Suceso suceso = this.sucesos.get(this.contSucesos);
			this.contSucesos++;
			suceso.mostrarSuceso();
		} else {
			this.contSucesos = 0;
			this.sucesos.clear();
			this.accionFinal.invoke(this.objetoFinal);
		}
	}

	@FXML
	private void saltear() throws InvocationTargetException, IllegalAccessException {
		this.sucesos.clear();
		this.accionFinal.invoke(this.objetoFinal);
	}

	@FXML
	private void entroSaltear() {
		this.botonSaltear.setImage(this.imagenSaltearSeleccionado);
	}

	@FXML
	private void salioSaltear() {
		this.botonSaltear.setImage(this.imagenSaltearNoSeleccionado);
	}

	@FXML
	private void entroContinuar() {
		this.botonContinuar.setImage(this.imagenContinuarSeleccionado);
	}

	@FXML
	private void salioContinuar() {
		this.botonContinuar.setImage(this.imagenContinuarNoSeleccionado);
	}
}
