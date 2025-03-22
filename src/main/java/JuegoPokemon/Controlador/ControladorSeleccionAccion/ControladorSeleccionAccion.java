package JuegoPokemon.Controlador.ControladorSeleccionAccion;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Jugador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class ControladorSeleccionAccion implements IControladorSeleccionAccion {

	private FXMLLoader loader;

	private Object solicitanteAtaque;

	private Method retornoAtaque;

	private Object solicitanteCambiarPokemon;

	private Method retornoCambiarPokemon;

	private Object solicitanteUsarItem;

	private Method retornoUsarItem;

	private Object solicitanteRendirse;

	private Method retornorRendirse;

	private Image imageAtaqueSeleccionado;

	private Image imageAtaqueSinSeleccionar;

	private Image imageCambiarPokemonSeleccionado;

	private Image imageCambiarPokemonSinSeleccionar;

	private Image imageUsarItemSeleccionado;

	private Image imageUsarItemSinSeleccionar;

	private Image imageRendirseSeleccionado;

	private Image imageRendirseSinSeleccionar;

	@FXML
	private Label mensaje;

	public void initialize() throws FileNotFoundException {
		this.imageAtaqueSeleccionado = new Image(new FileInputStream("src/main/resources/SeleccionAccion/AtacarSeleccionado.png"));
		this.imageAtaqueSinSeleccionar = new Image(new FileInputStream("src/main/resources/SeleccionAccion/AtacarSinSeleccionar.png"));
		this.imageCambiarPokemonSeleccionado = new Image(new FileInputStream("src/main/resources/SeleccionAccion/PokemonSeleccionado.png"));
		this.imageCambiarPokemonSinSeleccionar = new Image(new FileInputStream("src/main/resources/SeleccionAccion/PokemonSinSeleccionar.png"));
		this.imageUsarItemSeleccionado = new Image(new FileInputStream("src/main/resources/SeleccionAccion/MochilaSeleccionada.png"));
		this.imageUsarItemSinSeleccionar = new Image(new FileInputStream("src/main/resources/SeleccionAccion/MochilaSinSeleccionar.png"));
		this.imageRendirseSeleccionado = new Image(new FileInputStream("src/main/resources/SeleccionAccion/RendirseSeleccionado.png"));
		this.imageRendirseSinSeleccionar = new Image(new FileInputStream("src/main/resources/SeleccionAccion/RendirseSinSeleccionar.png"));
		Font font =new Font(Constantes.TAMAÑOLETRA);
		this.mensaje.setFont(font);
	}

	@Override
	public void encender() throws IOException {
		File file = new File("src/main/resources/SeleccionAccion/SeleccionAccionView.fxml");
		this.loader = new FXMLLoader(file.toURI().toURL());
		this.loader.setController(this);
		this.loader.load();
	}

	@Override
	public Object getRoot() {
		return this.loader.getRoot();
	}

	@Override
	public void setRetornoAtaque(Object solicitante, Method retorno) {
		this.solicitanteAtaque = solicitante;
		this.retornoAtaque = retorno;
	}

	@Override
	public void setRetornoCambiarPokemon(Object solicitante, Method retorno) {
		this.solicitanteCambiarPokemon = solicitante;
		this.retornoCambiarPokemon = retorno;
	}

	@Override
	public void setRetornoUsarItem(Object solicitante, Method retorno) {
		this.solicitanteUsarItem = solicitante;
		this.retornoUsarItem = retorno;
	}

	@Override
	public void setRetornoRendirse(Object solicitante, Method retorno) {
		this.solicitanteRendirse = solicitante;
		this.retornorRendirse = retorno;
	}

	@Override
	public void setJugador(Jugador jugador) {
		this.mensaje.setText("Seleccione la accion que desea realizar " + jugador.getNombre());
	}

	@FXML
	private void atacar() throws InvocationTargetException, IllegalAccessException {
		this.retornoAtaque.invoke(this.solicitanteAtaque);
	}

	@FXML
	private void cambiarPokemon() throws InvocationTargetException, IllegalAccessException {
		this.retornoCambiarPokemon.invoke(this.solicitanteCambiarPokemon);
	}

	@FXML
	private void usarItem() throws InvocationTargetException, IllegalAccessException {
		this.retornoUsarItem.invoke(this.solicitanteUsarItem);
	}

	@FXML
	private void rendirse() throws InvocationTargetException, IllegalAccessException {
		this.retornorRendirse.invoke(this.solicitanteRendirse);
	}

	@FXML
	private void entroAtacar(MouseEvent mouseEvent) {
		ImageView imageView = (ImageView) mouseEvent.getSource();
		imageView.setImage(this.imageAtaqueSeleccionado);
	}

	@FXML
	private void salioAtacar(MouseEvent mouseEvent) {
		ImageView imageView = (ImageView) mouseEvent.getSource();
		imageView.setImage(this.imageAtaqueSinSeleccionar);
	}

	@FXML
	private void entroCambiarPokemon(MouseEvent mouseEvent) {
		ImageView imageView = (ImageView) mouseEvent.getSource();
		imageView.setImage(this.imageCambiarPokemonSeleccionado);
	}

	@FXML
	private void salioCambiarPokemon(MouseEvent mouseEvent) {
		ImageView imageView = (ImageView) mouseEvent.getSource();
		imageView.setImage(this.imageCambiarPokemonSinSeleccionar);
	}

	@FXML
	private void entroUsarItem(MouseEvent mouseEvent) {
		ImageView imageView = (ImageView) mouseEvent.getSource();
		imageView.setImage(this.imageUsarItemSeleccionado);
	}

	@FXML
	private void salioUsarItem(MouseEvent mouseEvent) {
		ImageView imageView = (ImageView) mouseEvent.getSource();
		imageView.setImage(this.imageUsarItemSinSeleccionar);
	}

	@FXML
	private void entroRendirse(MouseEvent mouseEvent) {
		ImageView imageView = (ImageView) mouseEvent.getSource();
		imageView.setImage(this.imageRendirseSeleccionado);
	}

	@FXML
	private void salioRendirse(MouseEvent mouseEvent) {
		ImageView imageView = (ImageView) mouseEvent.getSource();
		imageView.setImage(this.imageRendirseSinSeleccionar);
	}

}
