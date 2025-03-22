package JuegoPokemon.unit.testJuego;

import JuegoPokemon.modelo.game.AdministradorDeTurnos;
import JuegoPokemon.modelo.game.Jugador;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestAdministradorDeTurnos {
    @DisplayName("Verifico que el ordenamiento sea el correcto")
    @Test
    public void testJugadoresOrdenados() {
        //Arrange
        List<Jugador> jugadores = new ArrayList<>();
        Jugador ramiro = mock(Jugador.class);
        when(ramiro.getVelocidadDefaultPokSuelto()).thenReturn(10.0);
        jugadores.add(ramiro);

        Jugador federico = mock(Jugador.class);
        when(federico.getVelocidadDefaultPokSuelto()).thenReturn(5.0);
        jugadores.add(federico);

        List<Jugador> jugadoresOrdenados = new ArrayList<>();
        jugadoresOrdenados.add(ramiro);
        jugadoresOrdenados.add(federico);

        AdministradorDeTurnos administradorDeTurnos = new AdministradorDeTurnos(jugadores);

        //Act
        administradorDeTurnos.actualizarJugadores();

        //Assert
        assertEquals(administradorDeTurnos.getJugadores(), jugadoresOrdenados);
    }

    @DisplayName("Verifico que el ordenamiento sea el correcto inversamente")
    @Test
    public void testJugadoresOrdenInverso() {
        //Arrange
        List<Jugador> jugadores = new ArrayList<>();
        Jugador ramiro = mock(Jugador.class);
        when(ramiro.getVelocidadDefaultPokSuelto()).thenReturn(5.0);
        jugadores.add(ramiro);

        Jugador federico = mock(Jugador.class);
        when(federico.getVelocidadDefaultPokSuelto()).thenReturn(10.0);
        jugadores.add(federico);

        List<Jugador> jugadoresOrdenados = new ArrayList<>();
        jugadoresOrdenados.add(federico);
        jugadoresOrdenados.add(ramiro);

        AdministradorDeTurnos administradorDeTurnos = new AdministradorDeTurnos(jugadores);

        //Act
        administradorDeTurnos.actualizarJugadores();

        //Assert
        assertEquals(administradorDeTurnos.getJugadores(), jugadoresOrdenados);
    }

    @DisplayName("Verifico que avanze de los turnos")
    @Test
    public void avanzeDeTurnos() {
        //Arrange
        List<Jugador> jugadores = new ArrayList<>();
        Jugador ramiro = mock(Jugador.class);
        when(ramiro.getVelocidadDefaultPokSuelto()).thenReturn(5.0);
        jugadores.add(ramiro);

        Jugador federico = mock(Jugador.class);
        when(federico.getVelocidadDefaultPokSuelto()).thenReturn(10.0);
        jugadores.add(federico);

        AdministradorDeTurnos administradorDeTurnos = new AdministradorDeTurnos(jugadores);

        //Act
        administradorDeTurnos.actualizarJugadores();
        //Assert
        assertEquals(0,administradorDeTurnos.getTurno());

        //Act
        administradorDeTurnos.siguienteTurno();
        //Assert
        assertEquals(1,administradorDeTurnos.getTurno());

        //Act
        administradorDeTurnos.siguienteTurno();
        //Assert
        assertEquals(2,administradorDeTurnos.getTurno());

        //Act
        administradorDeTurnos.reiniciarTurnos();
        //Assert
        assertEquals(0,administradorDeTurnos.getTurno());

    }

    @DisplayName("Verifico que el primer jugador actual sea el correcto")
    @Test
    public void jugadorActual() {
        //Arrange
        List<Jugador> jugadores = new ArrayList<>();
        Jugador ramiro = mock(Jugador.class);
        when(ramiro.getVelocidadDefaultPokSuelto()).thenReturn(5.0);
        jugadores.add(ramiro);

        Jugador federico = mock(Jugador.class);
        when(federico.getVelocidadDefaultPokSuelto()).thenReturn(10.0);
        jugadores.add(federico);

        AdministradorDeTurnos administradorDeTurnos = new AdministradorDeTurnos(jugadores);

        //Act
        administradorDeTurnos.actualizarJugadores();
        //Assert
        assertTrue(federico.equals(administradorDeTurnos.jugadorDelTurnoActual()));
        assertTrue(ramiro.equals(administradorDeTurnos.jugadorInactivoDelTurnoActual()));

        //Act
        administradorDeTurnos.siguienteTurno();
        //Assert
        assertTrue(ramiro.equals(administradorDeTurnos.jugadorDelTurnoActual()));
        assertTrue(federico.equals(administradorDeTurnos.jugadorInactivoDelTurnoActual()));

    }
}