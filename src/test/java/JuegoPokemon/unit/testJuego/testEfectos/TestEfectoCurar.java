package JuegoPokemon.unit.testJuego.testEfectos;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.Tipo;
import JuegoPokemon.modelo.game.efectos.Curar;
import JuegoPokemon.modelo.game.efectos.Efecto;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestEfectoCurar {
    @DisplayName("Unitario: Crea el efecto curar y compruebo el metodo aplicarEfecto sobre un pokemonMock mockeado y" +
            " verifico que se le mande la cantidad correcta a curar al pokemonMock")
    @Test
    void elPokemonSeCuraDentroDelMaximo(){
        Efecto efecto  = new Curar(Constantes.PODERPOCION);
        Pokemon pokemonMock=mock(Pokemon.class);
        when(pokemonMock.getVidaMaxima()).thenReturn(100.0);
        when(pokemonMock.getVida()).thenReturn(50.0);

        boolean resultado = efecto.sePuedoAplicarEfecto(pokemonMock);
        efecto.aplicarEfecto(pokemonMock);

        verify(pokemonMock).reducirVida(-Constantes.PODERPOCION);
        assertTrue(resultado);
    }
    @DisplayName("Unitario: Crea el efecto curar y compruebo el metodo aplicarEfecto sobre un pokemonMock mockeado y" +
            " verifico que no se invoce reducirVida para curar al pokemonMock")
    @Test
    void elPokemonNoSeCuraPorQueTieneVidaMaxima(){
        Efecto efecto  = new Curar(Constantes.PODERPOCION);
        Pokemon pokemonMock=mock(Pokemon.class);
        when(pokemonMock.getVidaMaxima()).thenReturn(100.0);
        when(pokemonMock.getVida()).thenReturn(100.0);

        boolean resultado = efecto.sePuedoAplicarEfecto(pokemonMock);
        verify(pokemonMock, never()).reducirVida(anyDouble());

        assertFalse(resultado);
    }

    @DisplayName("Unitario: Crea el efecto curar y compruebo el metodo aplicarEfecto sobre un pokemonMock mockeado y" +
            " verifico que no se invoce reducirVida para curar al pokemonMock")
    @Test
    void elPokemonSeCuraHastaLaVidaMaxima(){
        Efecto efecto  = new Curar(Constantes.PODERPOCION);
        Pokemon pokemonMock=mock(Pokemon.class);
        when(pokemonMock.getVidaMaxima()).thenReturn(100.0);
        when(pokemonMock.getVida()).thenReturn(85.0);

        boolean resultado = efecto.sePuedoAplicarEfecto(pokemonMock);
        efecto.aplicarEfecto(pokemonMock);
        verify(pokemonMock).setVida(100.0);

        assertTrue(resultado);
    }
    @DisplayName("Integracion: Crea el efecto curar y compruebo que se aplique el efecto sobre el pokemonMock")
    @Test
    void comprobarSiCuraAlPokemon(){
        Efecto efecto  = new Curar(Constantes.PODERHYPERPOCION);
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);
        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",400.0,10.0,10.0,10.0,null);
        //Act
        pokemon.reducirVida(100.0);
        efecto.aplicarEfecto(pokemon);
        //Assert
        assertEquals(400.00, pokemon.getVida());

    }

    @DisplayName("Unitario: Crea el efecto curar y compruebo que el efecto sea nulo")
    @Test
    void comprobarNoProduceEstado(){
        //Arrange
        Efecto efecto= new Curar(Constantes.PODERPOCION);
        //Act
        EstadoEnum estado = efecto.getEstado();
        //Assert
        assertNull(estado);

    }
}

