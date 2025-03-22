package JuegoPokemon.integration;

import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.acciones.Accion;
import JuegoPokemon.modelo.game.acciones.FactoryAcciones;
import JuegoPokemon.modelo.game.inicializadores.CreadorHabilidades;
import JuegoPokemon.modelo.game.inicializadores.CreadorItems;
import JuegoPokemon.modelo.game.inicializadores.CreadorJugadores;
import JuegoPokemon.modelo.game.inicializadores.CreadorPokemons;
import JuegoPokemon.modelo.game.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


public class TestJuegoIntegrado {


    @DisplayName("Test integracion pelea dos pokemones diferentes habilidades y items")
    @Test
    public void integracionJuego() {
        CreadorJugadores.setPath("src/main/resources/JSONs/DatosTest.json");
        CreadorHabilidades.setPath("src/main/resources/JSONs/HabilidadesTest.json");
        CreadorPokemons.setPath("src/main/resources/JSONs/PokemonesTest.json");
        CreadorHabilidades.setHashHabilidades();
        CreadorPokemons.setHashPokemons();
        CreadorItems.setHashItems();
        List<Jugador> jugadores = CreadorJugadores.jugadoresInicializados();

        Juego juego = new Juego(jugadores);
        HashMap<Integer, Pokemon> pokemons = CreadorPokemons.getHashPokemons();

        Jugador jugador1 = jugadores.get(0);
        Jugador jugador2 = jugadores.get(1);

        List<Pokemon>pokemonsJugador1 = jugador1.getPokemons();
        List<Pokemon>pokemonsJugador2 = jugador2.getPokemons();

        jugador1.liberarPokemon(pokemonsJugador1.get(1));
        jugador2.liberarPokemon(pokemonsJugador2.get(0));

        juego.actualizarOrdenJugadores();

        Pokemon pokemonAtacante = juego.getPokemonAtacante();
        Pokemon pokemonAtacado = juego.getPokemonInactivo();
        boolean cumpleVelocidad = pokemonAtacante.getVelocidadDeafault() > pokemonAtacado.getVelocidadDeafault();
        assertTrue(cumpleVelocidad);


        Habilidad habilidad = pokemonAtacante.getHabilidades().get(0);

        Accion lanzarHabilidad = FactoryAcciones.crearAccionLanzarHabilidad(habilidad, juego.getJugadorInactivoActual(), juego.getPokemonAtacante(), habilidad.getClimaQueGenera());
        juego.realizarAccion(lanzarHabilidad);

        Double vidaActual = pokemonAtacado.getVida();
        Double vidaMaxima = pokemonAtacado.getVidaMaxima();

        assertNotEquals(vidaMaxima, vidaActual);

        juego.cambiarTurno();

        pokemonAtacante = juego.getPokemonAtacante();

        double vidaAnterior = pokemonAtacante.getVida();

        Jugador jugadorActual = juego.getJugadorActual();

        Accion utilizarItem = FactoryAcciones.crearAccionUtilizarItem(jugadorActual, pokemonAtacante,ItemsEnum.Pocion);

        juego.realizarAccion(utilizarItem);

        vidaActual = pokemonAtacante.getVida();


        double vidaEsperada = vidaAnterior+ 20.0;

        if(vidaAnterior+ 20.0 >= pokemonAtacante.getVidaMaxima()) {
            vidaEsperada = pokemonAtacante.getVidaMaxima();
        }

        assertEquals(vidaActual,vidaEsperada);

        juego.cambiarTurno();

        pokemonAtacante = juego.getPokemonAtacante();

        utilizarItem = FactoryAcciones.crearAccionUtilizarItem(juego.getJugadorActual(), pokemonAtacante,ItemsEnum.AtaqueX);

        juego.realizarAccion(utilizarItem);


        assertEquals(pokemonAtacante.getAtaqueDefault()*1.1, pokemonAtacante.getAtaque());

        juego.cambiarTurno();

        pokemonAtacante = juego.getPokemonAtacante();
        pokemonAtacado = juego.getPokemonInactivo();

        habilidad = pokemonAtacante.getHabilidades().get(2);


        lanzarHabilidad = FactoryAcciones.crearAccionLanzarHabilidad(habilidad, juego.getJugadorInactivoActual(),pokemonAtacante, habilidad.getClimaQueGenera());

        vidaAnterior = pokemonAtacado.getVida();

        juego.realizarAccion(lanzarHabilidad);

        vidaActual = pokemonAtacado.getVida();

        assertNotEquals(vidaAnterior, vidaActual);


        juego.cambiarTurno();

        pokemonAtacante = juego.getPokemonAtacante();

        utilizarItem = FactoryAcciones.crearAccionUtilizarItem(juego.getJugadorActual(), pokemonAtacante,ItemsEnum.Pocion);


        vidaAnterior = pokemonAtacante.getVida();


        vidaEsperada = vidaAnterior+ 20.0;

        if(vidaAnterior+ 20.0 >= pokemonAtacante.getVidaMaxima()) {
            vidaEsperada = pokemonAtacante.getVidaMaxima();
        }

        juego.realizarAccion(utilizarItem);

        vidaActual =pokemonAtacante.getVida();

        assertEquals(vidaEsperada,vidaActual);

        juego.cambiarTurno();

        pokemonAtacante = juego.getPokemonAtacante();

        utilizarItem = FactoryAcciones.crearAccionUtilizarItem(juego.getJugadorActual(), pokemonAtacante,ItemsEnum.DefensaX);

        juego.realizarAccion(utilizarItem);


        assertEquals(pokemonAtacante.getDefensaDefault()*1.1, pokemonAtacante.getDefensa());

        juego.cambiarTurno();

        Accion rendirse = FactoryAcciones.crearAccionRendirse(juego.getJugadorActual());

        EstadoEvento anterior = juego.getEstadoEvento();

        juego.realizarAccion(rendirse);


        EstadoEvento actual = juego.getEstadoEvento();

        assertNotEquals(anterior, actual);

        assertNotEquals(juego.getGanador(), null);

    }
}
