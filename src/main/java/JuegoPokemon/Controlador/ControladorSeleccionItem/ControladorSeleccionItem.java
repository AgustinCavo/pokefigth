package JuegoPokemon.Controlador.ControladorSeleccionItem;

import JuegoPokemon.modelo.game.Item;
import JuegoPokemon.modelo.game.Jugador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControladorSeleccionItem implements IControladorSeleccionItem {

	private Stage stage;

	private Scene scene;

	private Object solicitante;

	private Method retornoItem;

	private Object objectSalida;

	private Method salida;

	private Jugador jugador;

	private List<CondicionDeSeleccionItem> condiciones;

	@FXML
	private VBox panelItems;

	@FXML
	private ImageView iconoItem;

	@FXML
	private Label informacionItem;

	@FXML
	private ImageView botonSalir;

	public ControladorSeleccionItem() {
		this.condiciones = new ArrayList<>();
	}

	@Override
	public void encender() throws IOException {
		File file = new File("src/main/resources/SeleccionItem/SeleccionItemView.fxml");
		FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
		loader.setController(this);
		this.scene = new Scene(loader.load());
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
		this.stage.setScene(this.scene);
	}

	@Override
	public void addCondicionSeleccionItem(CondicionDeSeleccionItem condicion) {
		this.condiciones.add(condicion);
	}

	@Override
	public void limpiarCondiciones() {
		this.condiciones.clear();
	}

	@Override
	public void mostrar() {
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	@Override
	public void setRetorno(Object solicitante, Method retorno) {
		this.solicitante = solicitante;
		this.retornoItem = retorno;
	}

	@Override
	public void setSalida(Object solicitante, Method salida) {
		this.objectSalida = solicitante;
		this.salida = salida;
	}

	@Override
	public void setJugador(Jugador jugador) throws NoSuchMethodException, IOException {
		this.limpiar();
		this.jugador = jugador;
		for (Map.Entry<Item, Integer> entrada : jugador.getItems().entrySet()) {
			this.crearCuadroItem(entrada.getKey(), entrada.getValue());
		}
	}

	private void crearCuadroItem(Item item, Integer cantidad) throws IOException, NoSuchMethodException {
		IControladorCuadroItem controladorCuadroItem = new ControladorCuadroItem();
		controladorCuadroItem.encender();
		controladorCuadroItem.setItem(item, cantidad);
		controladorCuadroItem.setMostrarDatos(this, this.getClass().getMethod("mostrarDatos", Item.class));
		controladorCuadroItem.setEliminarDatos(this, this.getClass().getMethod("eliminarDatos"));
		controladorCuadroItem.setRetorno(this, this.getClass().getMethod("subRetorno", Item.class));
		controladorCuadroItem.setUtilizable(this.cumpleUnaCondicion(item, cantidad));
		Pane pane = (Pane) controladorCuadroItem.getRoot();
		this.panelItems.getChildren().add(pane);
	}

	private Boolean cumpleUnaCondicion(Item item, Integer cantidad) {
		Integer cont = 0;
		for (CondicionDeSeleccionItem condicion : this.condiciones) {
			if (condicion.cumpleCondicion(item, cantidad))
				cont++;
		}
		return cont.equals(this.condiciones.size());
	}

	private void limpiar() {
		this.panelItems.getChildren().clear();
	}

	public void mostrarDatos(Item item) throws FileNotFoundException {
		this.iconoItem.setImage(new Image(new FileInputStream("src/main/resources/Items/" + item.getNombre() + ".png")));
		this.iconoItem.setVisible(true);
		this.informacionItem.setText(item.getNombre().toString()); //NECESITO PERDIR DEL MODULO INFORMACION DEL ITEM (Agregar a JSON)
	}

	public void eliminarDatos() {
		this.iconoItem.setVisible(false);
		this.informacionItem.setText("");
	}

	public void subRetorno(Item item) throws InvocationTargetException, IllegalAccessException {
		this.retornoItem.invoke(this.solicitante, this.jugador,  item);
	}

	@FXML
	private void clickSalir() throws InvocationTargetException, IllegalAccessException {
		this.salida.invoke(this.objectSalida, this.jugador);
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
