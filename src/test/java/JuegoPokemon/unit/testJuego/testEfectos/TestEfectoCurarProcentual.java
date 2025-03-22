package JuegoPokemon.unit.testJuego.testEfectos;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.Tipo;
import JuegoPokemon.modelo.game.efectos.CurarPorcentual;
import JuegoPokemon.modelo.game.efectos.Efecto;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

public class TestEfectoCurarProcentual {
    @DisplayName("Unitario: Crea el efecto curarporcentual y compruebo el metodo aplicarEfecto sobre un pokemon mockeado y" +
            " verifico que se le mande la cantidad correcta a curar al pokemon")
    @Test
    void elPokemonSeCuraDentroDelMaximo(){
        //Arrange
        Efecto efecto= new CurarPorcentual(Constantes.REVIVIR);
        Pokemon pokemon=mock(Pokemon.class);
        when(pokemon.getVidaMaxima()).thenReturn(100.0);
        when(pokemon.getVida()).thenReturn(0.0);
        //Act
        boolean resultado = efecto.sePuedoAplicarEfecto(pokemon);
        efecto.aplicarEfecto(pokemon);
        //Assert
        verify(pokemon).reducirVida(-50.0);
        assertEquals (resultado, true);
    }
    @DisplayName("Unitario: Crea el efecto curar y compruebo el metodo aplicarEfecto sobre un pokemon mockeado y" +
            " verifico que no se invoce reducirVida para curar al pokemon")
    @Test
    void elPokemonNoSeCuraPorQueTieneVidaMaxima(){
        //Arrange
        Efecto efecto= new CurarPorcentual(Constantes.REVIVIR);
        Pokemon pokemon=mock(Pokemon.class);
        when(pokemon.getVidaMaxima()).thenReturn(100.0);
        when(pokemon.getVida()).thenReturn(100.0);
        //Act
        boolean resultado = efecto.sePuedoAplicarEfecto(pokemon);
        efecto.aplicarEfecto(pokemon);
        //Assert
        verify(pokemon, never()).reducirVida(anyDouble());
        assertEquals (resultado, false);
    }

    @DisplayName("Unitario: Crea el efecto curar y compruebo el metodo aplicarEfecto sobre un pokemon mockeado y" +
            " verifico que no se invoce reducirVida para curar al pokemon")
    @Test
    void elPokemonSeCuraHastaLaVidaMaxima(){
        //Arrange
        Efecto efecto= new CurarPorcentual(Constantes.REVIVIR);
        Pokemon pokemon=mock(Pokemon.class);
        when(pokemon.getVidaMaxima()).thenReturn(100.0);
        when(pokemon.getVida()).thenReturn(85.0);
        //Act
        boolean resultado = efecto.sePuedoAplicarEfecto(pokemon);
        efecto.aplicarEfecto(pokemon);
        //Assert
        verify(pokemon).setVida(100.0);
        assertEquals (resultado, true);
    }
    @DisplayName("Integracion: Crea el efecto curarPorcentual y compruebo que se aplique el efecto sobre el pokemon mockeado")
    @Test
    void comprobarSiCuraAlPokemon(){
        //Arrange
        Efecto efecto= new CurarPorcentual(Constantes.REVIVIR);
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);
        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);
        //Act
        pokemon.reducirVida(50.0);
        efecto.sePuedoAplicarEfecto(pokemon);
        efecto.aplicarEfecto(pokemon);
        //Assert
        assertEquals (100.0, pokemon.getVida());

    }
    @DisplayName("Unitario: Crea el efecto curarPorcentual y compruebo que el efecto sea nulo")
    @Test
    void comprobarNoProduceEstado(){
        //Arrange
        Efecto efecto= new CurarPorcentual(Constantes.REVIVIR);
        //Act
        EstadoEnum estado = efecto.getEstado();
        //Assert
        assertEquals (null,estado);

    }

}
