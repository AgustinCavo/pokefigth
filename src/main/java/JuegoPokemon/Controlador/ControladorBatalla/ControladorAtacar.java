package JuegoPokemon.Controlador.ControladorBatalla;

import JuegoPokemon.Controlador.ControladorCampoBatalla.IControladorCampoBatalla;
import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.Controlador.ControladorSeleccionHabilidad.ControladorSeleccionHabilidad;
import JuegoPokemon.Controlador.ControladorSeleccionHabilidad.IControladorSeleccionHabilidad;
import JuegoPokemon.Controlador.ControladorSucesos.*;
import JuegoPokemon.modelo.game.EnumObjetivo;
import JuegoPokemon.modelo.game.Habilidad;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.acciones.FactoryAcciones;
import JuegoPokemon.modelo.game.acciones.Accion;
import JuegoPokemon.modelo.game.clima.Clima;
import JuegoPokemon.modelo.game.efectos.Efecto;
import JuegoPokemon.modelo.game.efectos.EfectoEnum;
import JuegoPokemon.modelo.game.efectos.ModificarEstadistica;
import JuegoPokemon.modelo.game.estado.Estado;
import JuegoPokemon.modelo.game.estado.EstadoEnum;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import JuegoPokemon.modelo.game.Juego;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControladorAtacar implements IControladorAtacar {

	private final IControladorSeleccionHabilidad seleccionHabilidad;

	private IControladorCampoBatalla controladorCampoBatalla;

	private IControladorJugadores controladorJugadores;

	private IControladorSucesos controladorSucesos;

	private Object solicitante;

	private Method aviso;

	private Stage stage;

	private StackPane barra;

	private final Juego juego;

	public ControladorAtacar(Juego juego) throws NoSuchMethodException, IOException {
		this.seleccionHabilidad = new ControladorSeleccionHabilidad();
		this.seleccionHabilidad.encender();
		this.seleccionHabilidad.setRetorno(this, getClass().getMethod("seleccionoHabilidad", Habilidad.class));
		this.seleccionHabilidad.setSalida(this, getClass().getMethod("canceloAccion"));
		this.juego = juego;
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void setControladorJugadores(IControladorJugadores controladorJugadores) {
		this.controladorJugadores = controladorJugadores;
	}

	@Override
	public void setBarraInferior(StackPane stackPane) {
		this.barra = stackPane;
	}

	@Override
	public void setAviso(Object solicitante, Method aviso) {
		this.solicitante = solicitante;
		this.aviso = aviso;
	}

	@Override
	public void setControladorCampoBatalla(IControladorCampoBatalla controladorCampoBatalla) {
		this.controladorCampoBatalla = controladorCampoBatalla;
	}

	@Override
	public void setControladorSucesos(IControladorSucesos controladorSucesos) {
		this.controladorSucesos = controladorSucesos;
	}

	@Override
	public void realizarAtaque() throws NoSuchMethodException {
		this.barra.getChildren().clear();
		this.seleccionHabilidad.setJugador(juego.getJugadorActual());
		Pane panel = (Pane) this.seleccionHabilidad.getRoot();
		this.barra.getChildren().add(panel);
		this.stage.show();
	}

	public void canceloAccion() throws InvocationTargetException, IllegalAccessException {
		this.aviso.invoke(this.solicitante, false);
	}

	public void seleccionoHabilidad(Habilidad habilidad) throws InvocationTargetException, IllegalAccessException, FileNotFoundException, NoSuchMethodException {
		this.controladorSucesos.limpiarSucesos();
		List<Suceso> sucesos = this.getSucesos(habilidad);
		for (Suceso suceso : sucesos)
			this.controladorSucesos.addSucesoSiguiente(suceso);
		this.controladorSucesos.setFinalizacion(this, getClass().getMethod("finalizarAtaque"));
		this.controladorSucesos.iniciarSucesos();
	}

	private List<Suceso> getSucesos(Habilidad habilidad) {
		Accion accion = FactoryAcciones.crearAccionLanzarHabilidad(habilidad, this.juego.getJugadorInactivoActual(), this.juego.getPokemonAtacante(), habilidad.getClimaQueGenera());
		List<Suceso> sucesosEnCadena = new ArrayList<>();
		Clima climaAnterior = juego.getClima();
		Pokemon pokemonAtacado = juego.getPokemonInactivo();
		Double vidaAnterior= juego.getPokemonAtacante().getVida();

		HashMap<EstadoEnum, Estado> estadosPokemonAtacadoAH = new HashMap<>(pokemonAtacado.getEstados());

		boolean funciono = juego.realizarAccion(accion);

		List<EstadoEnum> estadosPokemonAtacadoActual = pokemonAtacado.getListaEstados();

		sucesosEnCadena.add(new PokemonLanzoHabilidad(juego.getPokemonAtacante(), habilidad));
		modificaionClima(sucesosEnCadena, climaAnterior);

		System.out.println("Actuales " + estadosPokemonAtacadoActual + " en " + pokemonAtacado.getNombre());

		if (!funciono) {
			return sucesosNoAtaco(sucesosEnCadena);
		}
		if (habilidad.getObjetivo() == EnumObjetivo.Otro) {
			if(habilidad.getEfecto().getEfectoEnum().equals(EfectoEnum.ModificarEstadistica)){
				sucesosEnCadena.add(new AlteracionEstadisticas(this.controladorJugadores, juego.getPokemonInactivo(),habilidad.getEfecto(),habilidad));
			}
			return sucesoDeDanio(sucesosEnCadena,estadosPokemonAtacadoActual,estadosPokemonAtacadoAH,pokemonAtacado);
		} else {
			return sucesoDeEstadisticas(habilidad,sucesosEnCadena,vidaAnterior);
		}
	}

	private void modificaionClima(List<Suceso> sucesosEnCadena, Clima climaAnterior){
		if (!climaAnterior.equals(juego.getClima())) {
			sucesosEnCadena.add(new CambioClima(this.controladorCampoBatalla, juego.getClima().getTipo()));
		}
	}
	private List<Suceso> sucesosNoAtaco(List<Suceso> sucesosEnCadena ){
		Pokemon pokemonAtacante = juego.getPokemonAtacante();
		List<EstadoEnum> estadosPokemonAtacante = pokemonAtacante.getListaEstados();
		System.out.println(estadosPokemonAtacante);
		sucesosEnCadena.add(new PokemonNoAtaco(this.controladorCampoBatalla, estadosPokemonAtacante, pokemonAtacante));
		return sucesosEnCadena;
	}

	private List<Suceso> sucesoDeDanio(List<Suceso> sucesosEnCadena,List<EstadoEnum> estadosPokemonAtacadoActual,HashMap<EstadoEnum, Estado> estadosPokemonAtacadoAH,Pokemon pokemonAtacado ){
		EstadoEnum nuevo = null;
		sucesosEnCadena.add(new PokemonPerdioVida(this.controladorJugadores, pokemonAtacado));
		if (estadosPokemonAtacadoActual.size() != estadosPokemonAtacadoAH.size() || (estadosPokemonAtacadoAH.containsKey(EstadoEnum.Normal) && !estadosPokemonAtacadoActual.contains(EstadoEnum.Normal))) {
			for (EstadoEnum estadoActual : estadosPokemonAtacadoActual) {
				if (!estadosPokemonAtacadoAH.containsKey(estadoActual)) {
					nuevo = estadoActual;
					sucesosEnCadena.add(new CambioEstadoContrario(this.controladorJugadores, pokemonAtacado, nuevo));
				}
			}
		}
		return sucesosEnCadena;
	}
	private List<Suceso> sucesoDeEstadisticas(Habilidad habilidad,List<Suceso> sucesosEnCadena, Double vidaAnterior){
		if (habilidad.getEstado() == null) {
			Efecto efectohabilidad = habilidad.getEfecto();
			if ( habilidad.getEstado()== null && efectohabilidad.getEfectoEnum().equals(EfectoEnum.ModificarEstadistica)) {
				sucesosEnCadena.add(new AlteracionEstadisticas(this.controladorJugadores, juego.getPokemonAtacante(),habilidad.getEfecto(),habilidad));
				return sucesosEnCadena;
			}
			if ((efectohabilidad.getEfectoEnum().equals(EfectoEnum.Curar) || efectohabilidad.getEfectoEnum().equals(EfectoEnum.CurarPorcentual))){
				sucesosEnCadena.add(new AutoCuracion(this.controladorJugadores, juego.getPokemonAtacante(), vidaAnterior));
				return sucesosEnCadena;
			}
		}
		return  sucesosEnCadena;
	}
	public void finalizarAtaque() throws InvocationTargetException, IllegalAccessException {
		this.aviso.invoke(this.solicitante, true);
	}

}