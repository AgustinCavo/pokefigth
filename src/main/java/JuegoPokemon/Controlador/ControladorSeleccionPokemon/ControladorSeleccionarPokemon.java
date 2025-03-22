package JuegoPokemon.Controlador.ControladorSeleccionPokemon;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControladorSeleccionarPokemon implements IControladorSeleccionarPokemon {

	private Stage stage;

	private Scene scene;

	private Object solicitante;

	private Object objectoSalida;

	private Method metodoRetorno;

	private Method metodoSalida;

	@FXML
	private ImageView botonSalir;

	@FXML
	private Label mensaje;

	@FXML
	private StackPane panePokemonSuelto;

	@FXML
	private VBox panePokemons;

	private Jugador jugador;

	private List<CondicionDeSeleccionPokemon> condiciones;

	public ControladorSeleccionarPokemon() {
		this.condiciones = new ArrayList<>();
	}

	@Override
	public void encender() throws IOException {
		File file = new File("src/main/resources/SeleccionPokemon/SeleccionPokemonView.fxml");
		FXMLLoader loader = new FXMLLoader(file.toURI().toURL());

		loader.setController(this);
		this.scene = new Scene(loader.load());
	}

	public void initialize() {
		Font font = new Font(Constantes.TAMAÑOLETRA);
		this.mensaje.setFont(font);
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
		this.stage.setScene(this.scene);
	}

	@Override
	public void setRetorno(Object solicitante, Method metodoRetorno) {
		this.solicitante = solicitante;
		this.metodoRetorno = metodoRetorno;
	}

	@Override
	public void setSalida(Object objetoSalida, Method metodoSalida) {
		this.objectoSalida = objetoSalida;
		this.metodoSalida = metodoSalida;
	}

	@Override
	public void setVisibilidadSalida(Boolean boleano) {
		this.botonSalir.setVisible(boleano);
	}

	@Override
	public void addCondicionSeleccion(CondicionDeSeleccionPokemon condicion) {
		this.condiciones.add(condicion);
	}

	@Override
	public void limpiarCondiciones() {
		this.condiciones = new ArrayList<>();
	}

	@Override
	public void setJugador(Jugador jugador) throws IOException, NoSuchMethodException {
		this.limpiar();
		this.jugador = jugador;
		this.mensaje.setText("Seleccione un pokemon " + jugador.getNombre());
		if (jugador.getPokemonSuelto() != null) {
			this.agregarPokemonSuelto(jugador.getPokemonSuelto());
		} else this.botonSalir.setVisible(false);

		for (Pokemon pokemon : jugador.getPokemonsGuardados())
			this.agregarPokemon(pokemon);
	}

	private void agregarPokemonSuelto(Pokemon pokemon) throws IOException, NoSuchMethodException {
		ICuadroPokemonController cuadroPokemonController = new CuadroPokemonController();
		cuadroPokemonController.encender();
		cuadroPokemonController.setPokemon(pokemon);
		cuadroPokemonController.setRetorno(this, getClass().getDeclaredMethod("subRetorno", Pokemon.class));
		cuadroPokemonController.setActivacion(this.verificarActivacion(pokemon));
		this.panePokemonSuelto.getChildren().add((Node) cuadroPokemonController.getRoot());
	}

	private void agregarPokemon(Pokemon pokemon) throws IOException, NoSuchMethodException {
		ICuadroPokemonController cuadroPokemonController = new CuadroPokemonController();
		cuadroPokemonController.encender();
		cuadroPokemonController.setPokemon(pokemon);
		cuadroPokemonController.setRetorno(this, getClass().getDeclaredMethod("subRetorno", Pokemon.class));
		cuadroPokemonController.setActivacion(this.verificarActivacion(pokemon));
		this.panePokemons.getChildren().add((Node) cuadroPokemonController.getRoot());
	}

	public void subRetorno(Pokemon pokemon) throws InvocationTargetException, IllegalAccessException {
		this.metodoRetorno.invoke(this.solicitante, this.jugador, pokemon);
	}

	private Boolean verificarActivacion(Pokemon pokemon) {
		Integer cant = 0;
		for (CondicionDeSeleccionPokemon condicion : this.condiciones) {
			if (condicion.cumpleCondicion(pokemon))
				cant++;
		}
		return cant.equals(this.condiciones.size());
	}

	private void limpiar() {
		this.panePokemons.getChildren().clear();
		this.panePokemonSuelto.getChildren().clear();
	}

	@Override
	public void mostrar() {
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	@FXML
	private void clickSalir() throws InvocationTargetException, IllegalAccessException {
		if (this.botonSalir.isVisible())
			this.metodoSalida.invoke(this.objectoSalida, this.jugador);
	}

	@FXML
	private void entroSalir() {
		if (this.botonSalir.isVisible())
			this.botonSalir.setOpacity(1.0);
	}

	@FXML
	private void salioSalir() {
		if (this.botonSalir.isVisible())
			this.botonSalir.setOpacity(0.8);
	}

}
