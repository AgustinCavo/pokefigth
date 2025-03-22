package JuegoPokemon.Controlador.ControladorBatalla;

import JuegoPokemon.Controlador.ControladorCampoBatalla.IControladorCampoBatalla;
import JuegoPokemon.Controlador.ControladorConsultarRendicion.IControladorConsultarRendicion;
import JuegoPokemon.Controlador.ControladorJugadores.ControladorJugadores;
import JuegoPokemon.Controlador.ControladorSeleccionAccion.IControladorSeleccionAccion;
import JuegoPokemon.Controlador.ControladorSucesos.ControladorSucesos;
import JuegoPokemon.Controlador.ControladorSucesos.DamageEstado;
import JuegoPokemon.Controlador.ControladorSucesos.IControladorSucesos;
import JuegoPokemon.Controlador.ControladorSucesos.Suceso;
import JuegoPokemon.Controlador.ControladorSucesos.DamageClima;
import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

public class ControladorBatalla implements IControladorBatalla {

	private final IControladorSeleccionAccion controladorSeleccionarAccion;

	private final IControladorAtacar controladorAtacar;

	private final IControladorCambiarPokemon controladorCambiarPokemon;

	private final IControladorUsarItem controladorUsarItem;

	private final IControladorLiberarPokemon controladorLiberarPokemon;

	private final IControladorConsultarRendicion controladorConsultarRendicion;

	private final Scene scene;

	private final Stage stage;

	private final Juego juego;

	@FXML
	private ImageView imagenSuperior;

	@FXML
	private ControladorJugadores jugadoresController;

	@FXML
	private IControladorCampoBatalla campoBatallaController;

	@FXML
	private StackPane barraInferior;

	@FXML
	private Label nombreGanador;

	private Object solicitante;

	private Method retorno;

	private final Parent root;

	public ControladorBatalla(Juego juego, Stage stage, IControladorSeleccionAccion seleccionAccion, IControladorAtacar controladorAtacar, IControladorCambiarPokemon controladorCambiarPokemon, IControladorUsarItem controladorUsarItem, IControladorLiberarPokemon controladorLiberarPokemon, IControladorConsultarRendicion controladorConsultarRendicion) throws IOException, NoSuchMethodException {
		this.juego = juego;

		File file = new File("src/main/resources/Batalla/BatallaView.fxml");
		FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
		loader.setController(this);
		this.scene = new Scene(loader.load());
		this.root = loader.getRoot();
		this.stage = stage;

		IControladorSucesos controladorSucesos = new ControladorSucesos(this.stage, this.barraInferior, this.scene);

		this.controladorSeleccionarAccion = seleccionAccion;
		this.controladorSeleccionarAccion.encender();
		this.controladorSeleccionarAccion.setRetornoAtaque(this, getClass().getMethod("seleccionoAtacar"));
		this.controladorSeleccionarAccion.setRetornoCambiarPokemon(this, getClass().getMethod("seleccionoCambiarPokemon"));
		this.controladorSeleccionarAccion.setRetornoUsarItem(this, getClass().getMethod("seleccionoMochila"));
		this.controladorSeleccionarAccion.setRetornoRendirse(this, getClass().getMethod("seleccionoRendirse"));

		this.controladorAtacar = controladorAtacar;
		this.controladorAtacar.setStage(stage);
		this.controladorAtacar.setAviso(this, getClass().getMethod("recibirAviso", Boolean.class));
		this.controladorAtacar.setControladorCampoBatalla(this.campoBatallaController);
		this.controladorAtacar.setControladorJugadores(this.jugadoresController);
		this.controladorAtacar.setControladorSucesos(controladorSucesos);

		this.controladorCambiarPokemon = controladorCambiarPokemon;
		this.controladorCambiarPokemon.encender();
		this.controladorCambiarPokemon.setStage(stage);
		this.controladorCambiarPokemon.setAviso(this, getClass().getMethod("recibirAviso", Boolean.class));
		this.controladorCambiarPokemon.setControladorJugadores(this.jugadoresController);
		this.controladorCambiarPokemon.setControladorSucesos(controladorSucesos);

		this.controladorUsarItem = controladorUsarItem;
		this.controladorUsarItem.setStage(stage);
		this.controladorUsarItem.setAviso(this, getClass().getMethod("recibirAviso", Boolean.class));
		this.controladorUsarItem.setControladorSuceso(controladorSucesos);
		this.controladorUsarItem.setControladorJugadores(this.jugadoresController);

		this.controladorLiberarPokemon = controladorLiberarPokemon;
		this.controladorLiberarPokemon.setStage(stage);
		this.controladorLiberarPokemon.encender();
		this.controladorLiberarPokemon.setAviso(this, getClass().getMethod("recibirAviso", Boolean.class));
		this.controladorLiberarPokemon.setControladorSuceso(controladorSucesos);
		this.controladorLiberarPokemon.setJugadoresController(this.jugadoresController);

		this.controladorConsultarRendicion = controladorConsultarRendicion;
		this.controladorConsultarRendicion.setAviso(this, getClass().getMethod("recibirAviso", Boolean.class));
	}

	@FXML
	public void initialize() {
		this.jugadoresController.setRoot(this.root);
		Font font = new Font(Constantes.TAMAÑOLETRA);
		this.nombreGanador.setFont(font);
		this.nombreGanador.setLineSpacing(15);
	}

	@Override
	public void setRetorno(Object solicitante, Method retorno) {
		this.solicitante = solicitante;
		this.retorno = retorno;
	}

	@Override
	public void realizarAccion() throws IOException, NoSuchMethodException {
		if (this.juego.getPokemonAtacante() == null || this.juego.pokemonAtacanteEstaDebilitado())
			this.controladorLiberarPokemon.liberarPokemon(this.juego.getJugadorActual());
		else this.seleccionAccion();
	}

	public void recibirAviso(Boolean aviso) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		if (aviso)
			this.retorno.invoke(this.solicitante);
		else this.realizarAccion();
	}



	private void seleccionAccion() throws FileNotFoundException {
		this.barraInferior.getChildren().clear();

		this.jugadoresController.setJugadores(this.juego.getJugadorActual(), this.juego.getJugadorInactivoActual());
		this.controladorSeleccionarAccion.setJugador(this.juego.getJugadorActual());

		this.campoBatallaController.setClima(this.juego.getClima().getTipo());

		Pane pane = (Pane) this.controladorSeleccionarAccion.getRoot();
		this.barraInferior.getChildren().add(pane);

		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public void seleccionoAtacar() throws NoSuchMethodException {
		this.controladorAtacar.setBarraInferior(this.barraInferior);
		this.controladorAtacar.realizarAtaque();
	}

	public void seleccionoMochila() throws IOException, NoSuchMethodException {
		this.controladorUsarItem.usarItem(this.juego.getJugadorActual());
	}

	public void seleccionoCambiarPokemon() throws IOException, NoSuchMethodException {
		this.controladorCambiarPokemon.cambiarPokemon(juego.getJugadorActual());
	}

	public void seleccionoRendirse() {
		this.controladorConsultarRendicion.setJugador(this.juego.getJugadorActual());

		this.barraInferior.getChildren().clear();
		Parent parent = (Parent) this.controladorConsultarRendicion.getRoot();
		this.barraInferior.getChildren().add(parent);

		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public void liberarPokemon() throws IOException, NoSuchMethodException {
		this.controladorLiberarPokemon.liberarPokemon(juego.getJugadorActual());
	}

	@Override
	public void mostrarGanador(Jugador jugador) throws FileNotFoundException {
		this.barraInferior.getChildren().clear();
		Image image = new Image(new FileInputStream("src/main/resources/Batalla/Final.png"));
		this.imagenSuperior.setImage(image);
		this.nombreGanador.setVisible(true);
		this.nombreGanador.setText(jugador.getNombre());
		this.nombreGanador.setTextFill(Color.WHITE);
	}

}