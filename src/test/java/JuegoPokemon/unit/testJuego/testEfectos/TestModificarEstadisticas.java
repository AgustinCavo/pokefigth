package JuegoPokemon.unit.testJuego.testEfectos;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.Tipo;
import JuegoPokemon.modelo.game.efectos.Efecto;
import JuegoPokemon.modelo.game.efectos.EstadisticasEnum;
import JuegoPokemon.modelo.game.efectos.ModificarEstadistica;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestModificarEstadisticas {
    @DisplayName("Unitario: Crea el efecto modificarEstadistica y compruebo que el estado sea null ")
    @Test
    void testEfectoAsignadoCorrectamente(){
        Efecto efecto  = new ModificarEstadistica(EstadisticasEnum.Ataque, Constantes.ESTADISTICAX);
        assertEquals (null,efecto.getEstado());
    }
    @DisplayName("Unitario: Crea el efecto modificarEstadistica de ataque y compruebo que este asignada la correcta")
    @Test
    void testEstadisticaCorrecta(){
        ModificarEstadistica efecto  = new ModificarEstadistica(EstadisticasEnum.Ataque, Constantes.ESTADISTICAX);
        assertEquals (EstadisticasEnum.Ataque,efecto.getEstadistica());
    }
    @DisplayName("Unitario: Crea el efecto cambiarEstadistica y compruebo que el invoque cambiarEstadistica del pokemon mockeado")
    @Test
    void testAplicarEfectoLLamaCambiarEstado(){
        //Arrange
        Efecto efecto  = new ModificarEstadistica(EstadisticasEnum.Ataque,Constantes.ESTADISTICAX);
        Pokemon pokemon = Mockito.mock(Pokemon.class);
        when(pokemon.getAtaque()).thenReturn(10.0);
        //Act
        boolean resultado=efecto.sePuedoAplicarEfecto(pokemon);
        efecto.aplicarEfecto(pokemon);
        //Assert
        verify(pokemon).cambiarEstadistica(EstadisticasEnum.Ataque,11.0);
        assertEquals (true,resultado);
    }
    @DisplayName("Integracion: Crea el efecto cambiarEstadistica y compruebo que efectivamente se cambia la instancia de pokemon")
    @Test
    void testAplicarEfectoAfectaPokemon(){
        //Arrange
        ModificarEstadistica efecto  = new ModificarEstadistica(EstadisticasEnum.Ataque,Constantes.ESTADISTICAX);
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);
        Pokemon pokemon = new Pokemon("Porygon",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Assert
        assertEquals(10.0, pokemon.getAtaque());
        assertEquals(EstadisticasEnum.Ataque, efecto.getEstadistica());

        //Act
        boolean resultado=efecto.sePuedoAplicarEfecto(pokemon);
        efecto.aplicarEfecto(pokemon);

        //Assert
        assertEquals(11.0, pokemon.getAtaque());
        assertEquals(true, resultado);

    }
}
