package JuegoPokemon.unit.testJuego.testEstados;

import JuegoPokemon.modelo.game.estado.Estado;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import JuegoPokemon.modelo.game.estado.Debilitado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEstadoDebilitado {
    @DisplayName("Crea el estado debilitado y compruebo el metodo getEstado sea debilitado")
    @Test
    void devulveEnumEstadoDebilitado(){
        Estado debilitado = new Debilitado();
        assertEquals (EstadoEnum.Debilitado,debilitado.getEstado());
    }

    @DisplayName("Crea el estado debilitado y compruebo el metodo danioPorTurno sea 0")
    @Test
    void devuelve0Siempre(){
        Estado debilitado = new Debilitado();
        assertEquals( 0.0, debilitado.danioPorTurno(15.00));
    }

    @DisplayName("Crea el estado debilitado y compruebo el metodo puedeAtacar sea false")
    @Test
    void devuelveTrueSiempre(){
        Estado debilitado = new Debilitado();
        assertEquals( false , debilitado.puedeAtacar());
    }
}
