package JuegoPokemon.unit.testJuego.testEstados;

import JuegoPokemon.modelo.game.estado.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEstadoNormal {

    @DisplayName("Crea el estado normal y compruebo el metodo getEstado sea normal")
    @Test
    void devulveEnumEstadoNormal(){
        Estado normal = new Normal();
        assertEquals (EstadoEnum.Normal,normal.getEstado());
    }

    @DisplayName("Crea el estado normal y compruebo el metodo danioPorTurno sea 0")
    @Test
    void devuelve0Siempre(){
        Estado normal = new Normal();
        assertEquals( 0.0, normal.danioPorTurno(15.00));
    }

    @DisplayName("Crea el estado normal y compruebo el metodo puedeAtacar sea true")
    @Test
    void devuelveTrueSiempre(){
        Estado normal = new Normal();
        assertEquals( true , normal.puedeAtacar());
    }
}
