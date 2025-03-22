package JuegoPokemon.Controlador.ControladorBatalla;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.Controlador.ControladorSeleccionItem.ControladorSeleccionItem;
import JuegoPokemon.Controlador.ControladorSeleccionItem.IControladorSeleccionItem;
import JuegoPokemon.Controlador.ControladorSeleccionItem.PoseePokemonsParaUtilizar;
import JuegoPokemon.Controlador.ControladorSeleccionItem.SePoseeItem;
import JuegoPokemon.Controlador.ControladorSeleccionPokemon.ControladorSeleccionarPokemon;
import JuegoPokemon.Controlador.ControladorSeleccionPokemon.IControladorSeleccionarPokemon;
import JuegoPokemon.Controlador.ControladorSeleccionPokemon.ItemAplicable;
import JuegoPokemon.Controlador.ControladorSucesos.*;
import JuegoPokemon.modelo.game.*;
import JuegoPokemon.modelo.game.acciones.Accion;
import JuegoPokemon.modelo.game.acciones.FactoryAcciones;
import JuegoPokemon.modelo.game.efectos.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ControladorUsarItem implements IControladorUsarItem {

	private final IControladorSeleccionItem seleccionItem;

	private IControladorSucesos controladorSucesos;

	private final IControladorSeleccionarPokemon seleccionarPokemon;

	private IControladorJugadores controladorJugadores;

	private Object solicitante;

	private Method aviso;

	private Item itemSeleccionado;

	private final Juego juego;

	public ControladorUsarItem(Juego juego) throws IOException, NoSuchMethodException {
		this.seleccionItem = new ControladorSeleccionItem();
		this.seleccionItem.encender();
		this.seleccionItem.setSalida(this, getClass().getMethod("cancelarAccion", Jugador.class));
		this.seleccionItem.setRetorno(this, getClass().getMethod("seleccionoItem", Jugador.class, Item.class));
		this.seleccionarPokemon = new ControladorSeleccionarPokemon();
		this.seleccionarPokemon.encender();
		this.seleccionarPokemon.setSalida(this, getClass().getMethod("cancelarAccion", Jugador.class));
		this.seleccionarPokemon.setRetorno(this, getClass().getMethod("seleccionoPokemon", Jugador.class, Pokemon.class));
		this.seleccionarPokemon.setVisibilidadSalida(true);
		this.juego = juego;
	}

	public void setAviso(Object solicitante, Method aviso) {
		this.solicitante = solicitante;
		this.aviso = aviso;
	}

	public void setStage(Stage stage) {
		this.seleccionarPokemon.setStage(stage);
		this.seleccionItem.setStage(stage);
	}

	public void setControladorJugadores(IControladorJugadores controladorJugadores) {
		this.controladorJugadores = controladorJugadores;
	}

	public void usarItem(Jugador jugador) throws IOException, NoSuchMethodException {

		this.seleccionItem.limpiarCondiciones();
		PoseePokemonsParaUtilizar condicion = new PoseePokemonsParaUtilizar();
		condicion.setJugador(jugador);
		this.seleccionItem.addCondicionSeleccionItem(new SePoseeItem());
		this.seleccionItem.addCondicionSeleccionItem(condicion);

		this.seleccionItem.setJugador(jugador);

		this.seleccionItem.mostrar();
	}

	public void seleccionoItem(Jugador jugador, Item item) throws IOException, NoSuchMethodException {
		this.itemSeleccionado = item;

		this.seleccionarPokemon.limpiarCondiciones();
		ItemAplicable itemAplicable = new ItemAplicable();
		itemAplicable.setItem(item);
		this.seleccionarPokemon.addCondicionSeleccion(itemAplicable);

		this.seleccionarPokemon.setJugador(jugador);

		this.seleccionarPokemon.mostrar();
	}

	public void seleccionoPokemon(Jugador jugador, Pokemon pokemon) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, FileNotFoundException {
		Accion accion = FactoryAcciones.crearAccionUtilizarItem(jugador, pokemon,this.itemSeleccionado.getNombre());

		juego.realizarAccion(accion);

		this.controladorSucesos.limpiarSucesos();
		List<Suceso> sucesosEnCadena = new ArrayList<>();

		List<Efecto> efectos = itemSeleccionado.getEfectos();

		sucesosEnCadena.add(new SeAplicoItem(this.itemSeleccionado));

		for (Efecto efecto : efectos) {
			if (efecto.getClass() == Curar.class || efecto.getClass() == CurarPorcentual.class) {
				sucesosEnCadena.add(new AplicoCurar(this.controladorJugadores, efecto.potenciaEfecto(pokemon), pokemon));
			} else if (efecto.getClass() == CambiarEstado.class) {
				sucesosEnCadena.add(new CambioElEstado(this.controladorJugadores,efecto.getEstado(),pokemon));

			} else if (efecto.getClass() == ModificarEstadistica.class) {
				sucesosEnCadena.add(new ModificoEstadistica(efecto, pokemon));
			}
		}

		this.controladorSucesos.setFinalizacion(this, getClass().getMethod("finalizarUtilizarItem"));
		for (Suceso suceso : sucesosEnCadena)
			this.controladorSucesos.addSucesoSiguiente(suceso);

		this.controladorSucesos.iniciarSucesos();
	}

	public void finalizarUtilizarItem() throws InvocationTargetException, IllegalAccessException {
		this.aviso.invoke(this.solicitante, true);
	}

	public void cancelarAccion(Jugador jugador) throws InvocationTargetException, IllegalAccessException {
		this.aviso.invoke(this.solicitante, false);
	}

	public void setControladorSuceso(IControladorSucesos controladorSucesos) {
		this.controladorSucesos = controladorSucesos;
	}

}