package JuegoPokemon.Controlador.ControladorSeleccionItem;


import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class ControladorCuadroItem implements IControladorCuadroItem {

	private FXMLLoader loader;

	private Object solicitante;

	private Method retorno;

	private Object objectDatos;

	private Method mostrarDatos;

	private Object objectEliminarDatos;

	private Method eliminarDatos;

	private Item item;

	private Boolean seleccionable;

	@FXML
	private ImageView cuadro;

	@FXML
	private Label nombre;

	@FXML
	private Label cantidad;

	private Image cuadroSeleccionado;

	private Image cuadroNoSeleccionado;

	public void initialize() throws FileNotFoundException {
		this.cuadroSeleccionado = new Image(new FileInputStream("src/main/resources/SeleccionItem/CuadroItemSeleccionado.png"));
		this.cuadroNoSeleccionado = new Image(new FileInputStream("src/main/resources/SeleccionItem/CuadroItem.png"));
		Font font = new Font(Constantes.TAMAÑOLETRA);
		this.nombre.setFont(font);
		this.cantidad.setFont(font);
	}

	@Override
	public void encender() throws IOException {
		File file = new File("src/main/resources/SeleccionItem/CuadroItemView.fxml");
		this.loader = new FXMLLoader(file.toURI().toURL());
		this.loader.setController(this);
		this.loader.load();
	}

	@Override
	public void setUtilizable(Boolean bool) {
		this.seleccionable = bool;
	}

	@Override
	public void setMostrarDatos(Object receptor, Method mostrar) {
		this.objectDatos = receptor;
		this.mostrarDatos = mostrar;
	}

	@Override
	public void setEliminarDatos(Object receptor, Method deleteDatos) {
		this.objectEliminarDatos = receptor;
		this.eliminarDatos = deleteDatos;
	}

	@Override
	public void setRetorno(Object solicitante, Method retorno) {
		this.solicitante = solicitante;
		this.retorno = retorno;
	}

	@Override
	public void setItem(Item item, Integer cantidad) {
		this.item = item;
		this.nombre.setText(item.getNombre().toString());
		this.cantidad.setText(cantidad.toString());
	}

	@Override
	public Object getRoot() {
		return this.loader.getRoot();
	}

	@FXML
	private void click() throws InvocationTargetException, IllegalAccessException {
		if (this.seleccionable)
			this.retorno.invoke(this.solicitante, this.item);
	}

	@FXML
	private void entro() throws InvocationTargetException, IllegalAccessException {
		if (this.seleccionable) {
			this.cuadro.setImage(this.cuadroSeleccionado);
			this.mostrarDatos.invoke(this.objectDatos, this.item);
		}
	}

	@FXML
	private void salio() throws InvocationTargetException, IllegalAccessException {
		if (this.seleccionable) {
			this.cuadro.setImage(this.cuadroNoSeleccionado);
			this.eliminarDatos.invoke(this.objectEliminarDatos);
		}
	}

}
