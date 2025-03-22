package JuegoPokemon.unit.testJuego.testEfectos;

import JuegoPokemon.modelo.game.Calculadora;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.efectos.*;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestCreacionEfecto {
    @DisplayName("Unitario: Crea el efecto cambiarEstado y compruebo que el estado asignado a cambiar este en el efecto")
    @Test
    void testEfectoCurarAsignadoCorrectamente(){
        Efecto efecto = CreadorEfecto.crearEfecto("Curar",120.0);
        Pokemon pokemonMock = mock(Pokemon.class);
        assertEquals(efecto.potenciaEfecto(pokemonMock),-120.0);
    }
    @DisplayName("Unitario: Crea el efecto cambiarEstado y compruebo que el estado asignado a cambiar este en el efecto")
    @Test
    void testEfectoModificarEstadisticaAsignadoCorrectamente(){
        Efecto efecto = CreadorEfecto.crearEfecto("VelocidadX",1.1);
        Pokemon pokemonMock = mock(Pokemon.class);
        when(pokemonMock.getVelocidad()).thenReturn(10.0);
        assertEquals(efecto.potenciaEfecto(pokemonMock),11.0);
        ModificarEstadistica efectoCasteado = (ModificarEstadistica) efecto;
        assertEquals(efectoCasteado.getEstadistica(), EstadisticasEnum.Velocidad);
    }
}
