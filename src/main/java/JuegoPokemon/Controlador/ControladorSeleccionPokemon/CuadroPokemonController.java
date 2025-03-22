package JuegoPokemon.Controlador.ControladorSeleccionPokemon;


import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class CuadroPokemonController implements ICuadroPokemonController {

	private FXMLLoader loader;

	private Object solicitante;

	private Method metodoRetorno;

	@FXML
	private ProgressBar vida;

	@FXML
	private ImageView icono;

	@FXML
	private Label nombre;

	@FXML
	private Label nivel;

	@FXML
	private HBox estados;

	@FXML
	private ImageView cuadro;

	private Image cuadroSeleccionado;

	private Image cuadroNoSeleccionado;

	private Pokemon pokemon;

	private Boolean activado;

	public void initialize() throws FileNotFoundException {
		this.cuadroSeleccionado = new Image(new FileInputStream("src/main/resources/SeleccionPokemon/CuadroPokemonSeleccionado.png"));
		this.cuadroNoSeleccionado = new Image(new FileInputStream("src/main/resources/SeleccionPokemon/CuadroPokemonNoSeleccionado.png"));
		Font font = new Font(Constantes.TAMAÑOLETRA);
		this.nombre.setFont(font);
		this.nivel.setFont(font);
	}

	@Override
	public void encender() throws IOException {
		File file = new File("src/main/resources/SeleccionPokemon/CuadroPokemonView.fxml");
		this.loader = new FXMLLoader(file.toURI().toURL());

		loader.setController(this);
		this.loader.load();
	}

	@Override
	public void setPokemon(Pokemon pokemon) throws FileNotFoundException {
		this.pokemon = pokemon;
		this.vida.setProgress(pokemon.getVida() / pokemon.getVidaMaxima());
		this.nivel.setText(pokemon.getNivel().toString());
		this.nombre.setText(pokemon.getNombre());
		this.icono.setImage(new Image(new FileInputStream("src/main/resources/IconosPokemons/" + pokemon.getNombre() + ".png")));
		for (EstadoEnum estado : pokemon.getListaEstados()){
			this.estados.getChildren().add(new ImageView(new Image(new FileInputStream("src/main/resources/Estados/" + estado.toString() + ".png"))));
		}
	}

	@Override
	public void setActivacion(Boolean activacion) {
		this.activado = activacion;
	}

	@Override
	public void setRetorno(Object solicitante, Method retorno) {
		this.solicitante = solicitante;
		this.metodoRetorno = retorno;
	}

	@Override
	public Object getRoot() {
		return this.loader.getRoot();
	}

	@FXML
	private void clickCuadro() throws InvocationTargetException, IllegalAccessException {
		if (this.activado)
			this.metodoRetorno.invoke(this.solicitante, this.pokemon);
	}

	@FXML
	private void entroCuadro() {
		if (this.activado)
			this.cuadro.setImage(this.cuadroSeleccionado);
	}

	@FXML
	private void salioCuadro() {
		if (this.activado)
			this.cuadro.setImage(this.cuadroNoSeleccionado);
	}
}
