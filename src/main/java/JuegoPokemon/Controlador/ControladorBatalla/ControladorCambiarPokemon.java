package JuegoPokemon.Controlador.ControladorBatalla;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.Controlador.ControladorSeleccionPokemon.ControladorSeleccionarPokemon;
import JuegoPokemon.Controlador.ControladorSeleccionPokemon.IControladorSeleccionarPokemon;
import JuegoPokemon.Controlador.ControladorSeleccionPokemon.PokemonGuardado;
import JuegoPokemon.Controlador.ControladorSeleccionPokemon.PokemonVivo;
import JuegoPokemon.Controlador.ControladorSucesos.IControladorSucesos;
import JuegoPokemon.Controlador.ControladorSucesos.SucesoLiberoPokemon;
import JuegoPokemon.Controlador.ControladorSucesos.Suceso;
import JuegoPokemon.modelo.game.Juego;
import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.acciones.Accion;
import JuegoPokemon.modelo.game.acciones.FactoryAcciones;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ControladorCambiarPokemon implements IControladorCambiarPokemon {

	private IControladorSucesos controladorSucesos;

	private final IControladorSeleccionarPokemon seleccionarPokemon;

	private IControladorJugadores controladorJugadores;

	private Object solicitante;

	private Method aviso;

	private final Juego juego;

	public ControladorCambiarPokemon(Juego juego) {
		this.seleccionarPokemon = new ControladorSeleccionarPokemon();
		this.juego=juego;
	}

	@Override
	public void setControladorJugadores(IControladorJugadores controladorJugadores) {
		this.controladorJugadores = controladorJugadores;
	}

	@Override
	public void encender() throws IOException, NoSuchMethodException {
		this.seleccionarPokemon.encender();
		this.seleccionarPokemon.setRetorno(this, getClass().getMethod("pokemonSeleccionado", Jugador.class, Pokemon.class));
		this.seleccionarPokemon.setSalida(this, getClass().getMethod("canceloAccion", Jugador.class));
		this.seleccionarPokemon.setVisibilidadSalida(true);
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
	public void cambiarPokemon(Jugador jugador) throws IOException, NoSuchMethodException {

		this.seleccionarPokemon.limpiarCondiciones();
		this.seleccionarPokemon.addCondicionSeleccion(new PokemonVivo());
		PokemonGuardado pokemonGuardado = new PokemonGuardado();
		pokemonGuardado.setJugador(jugador);
		this.seleccionarPokemon.addCondicionSeleccion(pokemonGuardado);

		this.seleccionarPokemon.setJugador(jugador);

		this.seleccionarPokemon.mostrar();
	}

	@Override
	public void setControladorSucesos(IControladorSucesos controladorSucesos) {
		this.controladorSucesos = controladorSucesos;
	}

	public void pokemonSeleccionado(Jugador jugador, Pokemon pokemon) throws InvocationTargetException, IllegalAccessException, FileNotFoundException, NoSuchMethodException {
		Accion accion = FactoryAcciones.crearAccionLiberarPokemon(jugador, pokemon);
		juego.realizarAccion(accion);

		this.controladorSucesos.limpiarSucesos();
		List<Suceso> sucesosEnCadena = new ArrayList<>();
		sucesosEnCadena.add(new SucesoLiberoPokemon(this.controladorJugadores, jugador));

		this.controladorSucesos.setFinalizacion(this, getClass().getMethod("finalizarCambioPokemon"));
		for (Suceso suceso : sucesosEnCadena) {
			this.controladorSucesos.addSucesoSiguiente(suceso);
		}
		this.controladorSucesos.iniciarSucesos();
	}

	public void setControladorSuceso(IControladorSucesos controladorSucesos) {
		this.controladorSucesos = controladorSucesos;
	}
	public void finalizarCambioPokemon() throws InvocationTargetException, IllegalAccessException {
		this.aviso.invoke(this.solicitante, true);
	}
	public void canceloAccion(Jugador jugador) throws InvocationTargetException, IllegalAccessException {
		this.aviso.invoke(this.solicitante, false);
	}
}