package JuegoPokemon.unit.testJuego;

import JuegoPokemon.modelo.game.*;
import JuegoPokemon.modelo.game.clima.Clima;
import JuegoPokemon.modelo.game.clima.Lluvia;
import JuegoPokemon.modelo.game.clima.SinClima;
import JuegoPokemon.modelo.game.clima.Soleado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestJuego {


    @DisplayName("Crea el juego y verifico que el estado sea iniciado")
    @Test
    void testCrearJuegoEstadoIniciado() {

        //Arrange
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jugadorMock1 = mock(Jugador.class);
        Jugador jugadorMock2 = mock(Jugador.class);
        jugadores.add(jugadorMock1);
        jugadores.add(jugadorMock2);

        Juego juego = new Juego(jugadores);

        //Act
        EstadoEvento estadoJuego = juego.getEstadoEvento();

        //Assert
        assertEquals(EstadoEvento.Iniciado, estadoJuego);
    }


    @DisplayName("Crea el juego y verifico que el ganador sea Null")
    @Test
    void testSinGanadorEnCreacion() {

        //Arrange
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jugadorMock1 = mock(Jugador.class);
        Jugador jugadorMock2 = mock(Jugador.class);
        jugadores.add(jugadorMock1);
        jugadores.add(jugadorMock2);

        Juego juego = new Juego(jugadores);

        //Act
        Jugador ganador = juego.getGanador();

        //Assert
        assertNull(ganador);
    }

    @DisplayName("Crea el juego y verifico que el jugador que comienza es el correcto")
    @Test
    void testJugadorActualCorrecto() {

        //Arrange
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jugadorMock1 = mock(Jugador.class);
        Jugador jugadorMock2 = mock(Jugador.class);

        when(jugadorMock1.getVelocidadDefaultPokSuelto()).thenReturn(20.00);
        when(jugadorMock2.getVelocidadDefaultPokSuelto()).thenReturn(10.00);

        jugadores.add(jugadorMock1);
        jugadores.add(jugadorMock2);

        Juego juego = new Juego(jugadores);

        //Act
        juego.actualizarOrdenJugadores();
        Jugador jugadorActual = juego.getJugadorActual();

        //Assert
        assertEquals(jugadorActual, jugadorMock1);
    }

    @Test
    void testJugadorGanador() {

        //Arrange
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jugadorMock1 = mock(Jugador.class);
        Jugador jugadorMock2 = mock(Jugador.class);

        when(jugadorMock1.tienePokemonsActivos()).thenReturn(true);
        when(jugadorMock2.tienePokemonsActivos()).thenReturn(false);

        jugadores.add(jugadorMock1);
        jugadores.add(jugadorMock2);

        Juego juego = new Juego(jugadores);

        //Act
        Jugador jugadorGanador = juego.getGanador();

        //Assert
        assertEquals(jugadorGanador, jugadorMock1);
    }


    @Test
    void testJugadorActualPokemonMuerto() {

        //Arrange
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jugadorMock1 = mock(Jugador.class);
        Jugador jugadorMock2 = mock(Jugador.class);

        when(jugadorMock1.getVelocidadDefaultPokSuelto()).thenReturn(20.00);
        when(jugadorMock2.getVelocidadDefaultPokSuelto()).thenReturn(10.00);
        when(jugadorMock1.pokemonSueltoMuerto()).thenReturn(true);

        jugadores.add(jugadorMock1);
        jugadores.add(jugadorMock2);

        Juego juego = new Juego(jugadores);

        //Act
        juego.actualizarOrdenJugadores();
        boolean pokemonAtacanteEstaMuerto = juego.pokemonSueltoAtacanteEstaMuerto();

        //Assert
        assertTrue(pokemonAtacanteEstaMuerto);
    }

    @DisplayName("Crea el juego y verifico que el cambio de turno se realice de manera correcta")
    @Test
    void testCambioDeTurno() {

        //Arrange
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jugadorMock1 = mock(Jugador.class);
        Jugador jugadorMock2 = mock(Jugador.class);

        when(jugadorMock1.getVelocidadDefaultPokSuelto()).thenReturn(20.00);
        when(jugadorMock2.getVelocidadDefaultPokSuelto()).thenReturn(10.00);
        jugadores.add(jugadorMock1);
        jugadores.add(jugadorMock2);

        Juego juego = new Juego(jugadores);

        //Act
        juego.actualizarOrdenJugadores();
        juego.cambiarTurno();
        Jugador jugadorActual = juego.getJugadorActual();

        //Assert
        assertEquals(jugadorActual, jugadorMock2);
    }

    @Test
    void testCambioDeClima() {

        //Arrange
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jugadorMock1 = mock(Jugador.class);
        Jugador jugadorMock2 = mock(Jugador.class);

        Clima lluviaMock = mock(Lluvia.class);

        jugadores.add(jugadorMock1);
        jugadores.add(jugadorMock2);

        try (MockedStatic mockStatic = mockStatic(Clima.class)) {

            mockStatic.when(Clima::climaRandom).thenReturn(new Soleado());
            Juego juego = new Juego(jugadores);
            //Act
            Clima climaAnterior = juego.getClima();
            juego.cambiarClima(lluviaMock);
            Clima climaActual = juego.getClima();

            //Assert
            assertNotEquals(climaActual.getNombreVisible(), climaAnterior.getNombreVisible());
        }
    }
}