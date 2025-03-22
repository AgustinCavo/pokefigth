package JuegoPokemon.modelo.game;

import JuegoPokemon.Controlador.ControladorBatalla.*;
import JuegoPokemon.Controlador.ControladorConsultarRendicion.ControladorConsultarRendicion;
import JuegoPokemon.Controlador.ControladorPartida.ControladorPartida;
import JuegoPokemon.Controlador.ControladorPartida.IControladorPartida;
import JuegoPokemon.Controlador.ControladorSeleccionAccion.ControladorSeleccionAccion;
import JuegoPokemon.Controlador.ControladorSucesos.ControladorSucesos;
import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.inicializadores.CreadorItems;
import JuegoPokemon.modelo.controladores.ControladorJuego;
import JuegoPokemon.modelo.game.inicializadores.CreadorHabilidades;
import JuegoPokemon.modelo.game.inicializadores.CreadorJugadores;
import JuegoPokemon.modelo.game.inicializadores.CreadorPokemons;

import java.io.IOException;
import java.util.*;

import javafx.stage.Stage;

public class Partida {

    private final Stage stage;

    private IControladorBatalla controladorBatalla;

    private Juego juego;

    private List<Jugador> jugadores;

    public Partida(Stage stage) {
        this.stage = stage;
    }

    public void ejecutarPartida() throws IOException, NoSuchMethodException {

        //Creo los Jugadores
        this.crearJugadores();

        //Crer Juego con Jugadores
        this.juego = new Juego(jugadores);

        //Crear ControladorBatalla con Juego
        this.crearControladorBatalla();

        //Crear ControladorJuego con juego y controlador Batalla
        ControladorJuego controladorJuego = new ControladorJuego(this.juego, this.controladorBatalla);

        //Creo el ControladorDePartida y ejecuto
        IControladorPartida controladorPartida = new ControladorPartida(this.juego, this.stage, controladorJuego);
        controladorPartida.cargarPantallaInicio();
    }

    private void crearJugadores() {
        CreadorJugadores.setPath(Constantes.DIRECCION_JUGADORES);
        CreadorHabilidades.setPath(Constantes.DIRECCION_HABILIDADES);
        CreadorPokemons.setPath(Constantes.DIRECCION_POKEMONS);
        CreadorHabilidades.setHashHabilidades();
        CreadorPokemons.setHashPokemons();
        CreadorItems.setHashItems();
        this.jugadores = CreadorJugadores.jugadoresInicializados();
    }

    private void crearControladorBatalla() throws IOException, NoSuchMethodException {
        this.controladorBatalla = new ControladorBatalla(this.juego,
                this.stage, new ControladorSeleccionAccion(),
                new ControladorAtacar(this.juego),
                new ControladorCambiarPokemon(this.juego),
                new ControladorUsarItem(this.juego),
                new ControladorLiberarPokemon(this.juego),
                new ControladorConsultarRendicion(this.juego));
    }

}