package JuegoPokemon.modelo.controladores;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.Controlador.ControladorSucesos.DamageClima;
import JuegoPokemon.Controlador.ControladorSucesos.DamageEstado;
import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.Controlador.ControladorBatalla.IControladorBatalla;
import JuegoPokemon.modelo.game.Juego;
import JuegoPokemon.modelo.game.EstadoEvento;
import JuegoPokemon.modelo.game.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ControladorJuego {

    private final Juego juego;

    private final IControladorBatalla controladorBatalla;

    private IControladorJugadores controladorJugadores;

    private Boolean unIntentoMas;

    public ControladorJuego(Juego juego, IControladorBatalla controladorBatalla) throws NoSuchMethodException {
        this.juego = juego;
        this.controladorBatalla = controladorBatalla;
        this.controladorBatalla.setRetorno(this, getClass().getDeclaredMethod("realizoAccion"));
    }

    public void setControladorJugadores(IControladorJugadores controladorJugadores) {
        this.controladorJugadores = controladorJugadores;
    }

    public void ejecutar() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        juego.actualizarOrdenJugadores();
        juego.reiniciarTurnos();
        this.consultarSeguimiento();
    }

    public void consultarSeguimiento() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (juego.getEstadoEvento() != EstadoEvento.Finalizado)
            this.jugarTurno();
        else terminarPartida();
    }

    public void terminarPartida() throws FileNotFoundException {
        System.out.println("Terminó el juego. El ganador es: " + juego.getGanador().getNombre());
        this.controladorBatalla.mostrarGanador(juego.getGanador());
        try {
            escribirInformePartida();
        }catch (IOException e){
            System.out.println("No se escribio el informe de la partida");
        }
    }

    public void jugarTurno() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        juego.reiniciarClimaSiNecesario();
        juego.reduccionVidaPorTurno();
        unIntentoMas = juego.pokemonSueltoAtacanteEstaMuerto();
        if (unIntentoMas) {
            this.controladorBatalla.liberarPokemon();
        }

        this.controladorBatalla.realizarAccion();
    }

    public void realizoAccion() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Double damageClima = this.juego.getDamageClima();
        if (damageClima != null) {
            DamageClima damagePorClimaSuceso = new DamageClima(this.controladorJugadores, juego.getClima().getTipo(), juego.getPokemonInactivo(), damageClima);
        }

        Double damageEstado = this.juego.getDamageEstado();
        if (damageEstado != null) {
            DamageEstado damageEstado1 = new DamageEstado(this.controladorJugadores, juego.getPokemonInactivo(), damageEstado, juego.getPokemonInactivo().estadosQueHacenDaño());
        }

        if (!unIntentoMas)
            juego.cambiarTurno();
        this.consultarSeguimiento();
    }

    private void escribirInformePartida() throws IOException {
        List<Jugador> jugadores = this.juego.getJugadores();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);


        List<JugadorJson> jugadoresJson = new ArrayList<>();
        for (Jugador jugador : jugadores){
            String nombre = jugador.getNombre();
            boolean ganador = (juego.getGanador().equals(jugador));
            List<Pokemon> pokemons = jugador.getPokemons();
            HashMap<Item, Integer> items = jugador.getMochila().getItems();
            jugadoresJson.add(new JugadorJson(nombre, ganador, pokemons, items));
        }
        objectMapper.writeValue(new File(Constantes.DIRECCION_INFORME), jugadoresJson);
    }

}
