package JuegoPokemon.unit.testJuego.testEstados;

import JuegoPokemon.modelo.game.Calculadora;
import JuegoPokemon.modelo.game.estado.Estado;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import JuegoPokemon.modelo.game.estado.Dormido;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TestEstadoDormido {
    @DisplayName("Crea el estado dormido y compruebo el metodo getEstado sea dormido")
    @Test
    void devulveEnumEstadoDormido(){
        Estado dormido = new Dormido();
        assertEquals (EstadoEnum.Dormido,dormido.getEstado());
    }

    @DisplayName("Crea el estado dormido y compruebo el metodo danioPorTurno sea 0")
    @Test
    void devuelve0Siempre(){
        Estado dormido = new Dormido();
        assertEquals( 0.0, dormido.danioPorTurno(15.00));
    }



    @DisplayName("Crea el estado dormido y mockeo el random para que despierte el 1 turno")
    @Test
    void despiertaPrimerTurno(){


        Estado dormido = new Dormido();

        try (MockedStatic mockStatic = mockStatic(Calculadora.class)) {

            mockStatic.when(Calculadora::generarRandomProbabilidad).thenReturn(0.2);

            assertTrue(dormido.puedeAtacar());

            mockStatic.verify(Calculadora::generarRandomProbabilidad);
        }

    }
    @DisplayName("Crea el estado dormido y mockeo el random para que despierte el 2 turno")
    @Test
    void despiertaSegundoTurno(){


        Estado dormido = new Dormido();

        try (MockedStatic mockStatic = mockStatic(Calculadora.class)) {

            mockStatic.when(Calculadora::generarRandomProbabilidad).thenReturn(0.7);

            assertFalse(dormido.puedeAtacar());

            mockStatic.when(Calculadora::generarRandomProbabilidad).thenReturn(0.2);

            assertTrue(dormido.puedeAtacar());
            mockStatic.verify(Calculadora::generarRandomProbabilidad,atLeast(2));
        }
    }
}
