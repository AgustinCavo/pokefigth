package JuegoPokemon.unit.testJuego.testEstados;

import JuegoPokemon.modelo.game.Calculadora;
import JuegoPokemon.modelo.game.estado.*;
import org.junit.jupiter.api.*;
import JuegoPokemon.modelo.Constantes;
import org.mockito.MockedStatic;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestEstadoParalizado {

    @DisplayName("Crea el estado paralizado y compruebo el metodo getEstado sea paralizado")
    @Test
    void devulveEnumEstadoParalizado(){
        Estado paralizado = new Paralizado();
        assertEquals (EstadoEnum.Paralizado,paralizado.getEstado());
    }

    @DisplayName("Crea el estado paralizado y compruebo el metodo danioPorTurno sea 0")
    @Test
    void devuelve0Siempre(){
        Estado paralizado = new Paralizado();
        assertEquals( 0.0, paralizado.danioPorTurno(15.00));
    }

    @DisplayName("Crea el estado paralizado y mockeo que no puede atacar")
    @Test
    void paralizadoNoAtaca(){
        Estado paralizado = new Paralizado();
        double cont=0.0;

        try (MockedStatic mockStatic = mockStatic(Calculadora.class)) {

            mockStatic.when(Calculadora::generarRandomProbabilidad).thenReturn(0.1);

            //Inside scope
            assertFalse(paralizado.puedeAtacar());
            mockStatic.verify(Calculadora::generarRandomProbabilidad);
        }
    }
    @DisplayName("Crea el estado paralizado y mockeo que puede atacar")
    @Test
    void paralizadoAtaca(){
        Estado paralizado = new Paralizado();
        double cont=0.0;

        try (MockedStatic mockStatic = mockStatic(Calculadora.class)) {

            mockStatic.when(Calculadora::generarRandomProbabilidad).thenReturn(0.8);

            //Inside scope
            assertTrue(paralizado.puedeAtacar());
            mockStatic.verify(Calculadora::generarRandomProbabilidad);
        }
    }
}