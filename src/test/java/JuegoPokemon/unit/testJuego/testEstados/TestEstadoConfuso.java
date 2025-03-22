package JuegoPokemon.unit.testJuego.testEstados;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Calculadora;
import JuegoPokemon.modelo.game.estado.Confuso;
import JuegoPokemon.modelo.game.estado.Estado;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestEstadoConfuso {


    @DisplayName("Crea el estado confuso y compruebo el metodo getEstado sea confuso")
    @Test
    void devulveEnumEstadoConfuso(){
        Estado confuso = new Confuso();
        assertEquals (EstadoEnum.Confuso,confuso.getEstado());
    }

    @DisplayName("Crea el estado confuso y compruebo el metodo danioPorTurno genere danio mockeando que se puede pegar")
    @Test
    void comprobacionDañoPorGolpearse(){
        Estado confuso = new Confuso();

        try (MockedStatic mockStatic = mockStatic(Calculadora.class)) {

            mockStatic.when(Calculadora::generarRandomProbabilidad).thenReturn(0.2);

            assertEquals(confuso.danioPorTurno(100.0),15.0);
            mockStatic.verify(Calculadora::generarRandomProbabilidad);
        }

    }

    @DisplayName("Crea el estado confuso y compruebo el metodo danioPorTurno genere danio mockeando que se puede pegar")
    @Test
    void comprobacionDañoNoGolpearse(){
        Estado confuso = new Confuso();

        try (MockedStatic mockStatic = mockStatic(Calculadora.class)) {

            mockStatic.when(Calculadora::generarRandomProbabilidad).thenReturn(0.7);

            assertEquals(confuso.danioPorTurno(100.0),0.0);
            mockStatic.verify(Calculadora::generarRandomProbabilidad);
        }

    }

    @DisplayName("Crea el estado confuso y compruebo el metodo puedeAtacar solo devuelva false por 3 turnos")
    @Test
    void devuelveFalsePorDosLlamados(){
        Estado confuso = new Confuso();


        assertEquals( false , confuso.puedeAtacar());
        assertEquals( false , confuso.puedeAtacar());
        assertEquals( true , confuso.puedeAtacar());
    }
}
