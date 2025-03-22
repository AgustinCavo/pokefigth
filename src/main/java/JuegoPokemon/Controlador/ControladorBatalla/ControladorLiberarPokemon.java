package JuegoPokemon.Controlador.ControladorBatalla;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.Controlador.ControladorSeleccionPokemon.ControladorSeleccionarPokemon;
import JuegoPokemon.Controlador.ControladorSeleccionPokemon.IControladorSeleccionarPokemon;
import JuegoPokemon.Controlador.ControladorSeleccionPokemon.PokemonVivo;
import JuegoPokemon.Controlador.ControladorSucesos.IControladorSucesos;
import JuegoPokemon.Controlador.ControladorSucesos.SucesoLiberoPokemon;
import JuegoPokemon.Controlador.ControladorSucesos.Suceso;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.acciones.Accion;
import JuegoPokemon.modelo.game.acciones.FactoryAcciones;
import JuegoPokemon.modelo.game.Juego;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ControladorLiberarPokemon implements IControladorLiberarPokemon {

	private final IControladorSeleccionarPokemon seleccionarPokemon;
	private IControladorJugadores jugadoresController;

	private IControladorSucesos controladorSucesos;

	private Object solicitante;

	private Method aviso;

	private final Juego juego;

	public ControladorLiberarPokemon(Juego juego) {
		this.seleccionarPokemon = new ControladorSeleccionarPokemon();
		this.juego = juego;
	}

	@Override
	public void setJugadoresController(IControladorJugadores jugadoresController) {
		this.jugadoresController = jugadoresController;
	}

	@Override
	public void encender() throws IOException, NoSuchMethodException {
		this.seleccionarPokemon.encender();
		this.seleccionarPokemon.setRetorno(this, getClass().getMethod("pokemonSeleccionado", Jugador.class, Pokemon.class));
		this.seleccionarPokemon.setVisibilidadSalida(false);
		this.seleccionarPokemon.addCondicionSeleccion(new PokemonVivo());
	}

	@Override
	public void setStage(Stage stage) {
		this.seleccionarPokemon.setStage(stage);
	}

	@Override
	public void setAviso(Object solicitante, Method aviso) {
		this.solicitante = solicitante;
		this.aviso = aviso;
	}

	@Override
	public void liberarPokemon(Jugador jugador) throws IOException, NoSuchMethodException {

		this.seleccionarPokemon.setJugador(jugador);
		this.seleccionarPokemon.mostrar();
	}

	@Override
	public void setControladorSuceso(IControladorSucesos controladorSucesos) {
		this.controladorSucesos = controladorSucesos;
	}

	public void pokemonSeleccionado(Jugador jugador, Pokemon pokemon) throws InvocationTargetException, IllegalAccessException, FileNotFoundException, NoSuchMethodException {
		Accion accion = FactoryAcciones.crearAccionLiberarPokemon(jugador, pokemon);
		juego.realizarAccion(accion);

		if (this.controladorSucesos != null) {
			this.controladorSucesos.limpiarSucesos();
			List<Suceso> sucesosEnCadena = new ArrayList<>();
			sucesosEnCadena.add(new SucesoLiberoPokemon(this.jugadoresController, jugador));

			for (Suceso suceso : sucesosEnCadena) {
				this.controladorSucesos.addSucesoSiguiente(suceso);
			}
			this.controladorSucesos.setFinalizacion(this, getClass().getMethod("finalizarLiberoPokemon"));
			this.controladorSucesos.iniciarSucesos();
		} else this.finalizarLiberoPokemon();
	}

	public void finalizarLiberoPokemon() throws InvocationTargetException, IllegalAccessException {
		this.aviso.invoke(this.solicitante, true);
	}

}