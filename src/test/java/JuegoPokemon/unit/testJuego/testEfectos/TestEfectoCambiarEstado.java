package JuegoPokemon.unit.testJuego.testEfectos;


import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.Tipo;
import JuegoPokemon.modelo.game.efectos.CambiarEstado;
import JuegoPokemon.modelo.game.efectos.Efecto;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestEfectoCambiarEstado{
    @DisplayName("Unitario: Crea el efecto cambiarEstado y compruebo que el estado asignado a cambiar este en el efecto")
    @Test
    void testEfectoAsignadoCorrectamente(){
        Efecto efecto  = new CambiarEstado(EstadoEnum.Envenenado);
        assertEquals (EstadoEnum.Envenenado,efecto.getEstado());
    }
    @DisplayName("Unitario: Crea el efecto cambiarEstado y compruebo que el invoque cambiar estado del pokemon mockeado")
    @Test
    void testAplicarItemLLamaCambiarEstado(){
        //Arrange
        Efecto efecto  = new CambiarEstado(EstadoEnum.Envenenado);
        Pokemon pokemon = Mockito.mock(Pokemon.class);
        when(pokemon.tieneEsteEstado(EstadoEnum.Normal)).thenReturn(true);
        //Act
        boolean resultado= efecto.sePuedoAplicarEfecto(pokemon);
        efecto.aplicarEfecto(pokemon);

        //Assert
        verify(pokemon).cambiarEstado(EstadoEnum.Envenenado);
        assertTrue(resultado);
    }
    @DisplayName("Unitario: Crea el efecto cambiarEstado y compruebo que no invoque cambiar estado ni resetar estado normal del pokemon mockeado")
    @Test
    void testAplicarItemNoLLamaCambiarEstadoNiResetear(){
        //Arrange
        Efecto efecto  = new CambiarEstado(EstadoEnum.Normal);
        Pokemon pokemon = Mockito.mock(Pokemon.class);
        when(pokemon.tieneEsteEstado(EstadoEnum.Normal)).thenReturn(true);
        //Act
        boolean resultado=efecto.sePuedoAplicarEfecto(pokemon);
        //Assert
        verify(pokemon, never()).cambiarEstado(any());
        assertFalse(false);
    }
    @DisplayName("Unitario: Crea el efecto cambiarEstado y compruebo que invoque resetear estado normal")
    @Test
    void testAplicarItemLLamaResetearEstadoNormal(){
        //Arrange
        Efecto efecto  = new CambiarEstado(EstadoEnum.Normal);
        Pokemon pokemon = Mockito.mock(Pokemon.class);
        when(pokemon.tieneEsteEstado(EstadoEnum.Normal)).thenReturn(false);
        //Act
        boolean resultado=efecto.sePuedoAplicarEfecto(pokemon);
        efecto.aplicarEfecto(pokemon);
        //Assert
        assertTrue(resultado);
        verify(pokemon).resetearEstadoANormal();

    }
    @DisplayName("Integracion: Crea el efecto cambiarEstado y compruebo que cambie el estado actual del pokemon")
    @Test
    void testAplicarItemModificaLaInstanciaDePokemonCambiandoEstado(){
        //Arrange
        Efecto efecto  = new CambiarEstado(EstadoEnum.Envenenado);
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);
        Pokemon pokemon = new Pokemon("Porygon",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Assert
        assertTrue(pokemon.tieneEsteEstado(EstadoEnum.Normal));
        assertEquals(EstadoEnum.Envenenado,efecto.getEstado());

        //Act
        efecto.aplicarEfecto(pokemon);

        //Assert
        assertTrue(pokemon.tieneEsteEstado(EstadoEnum.Envenenado));
        assertFalse(pokemon.tieneEsteEstado(EstadoEnum.Normal));
    }
    @DisplayName("Integracion: Crea el efecto cambiarEstado y compruebo que el pokemon vuelva al estado normal(curatodo)")
    @Test
    void testAplicarItemModificaLaInstanciaDePokemonVolviendoANormal(){
        //Arrange
        Efecto efecto  = new CambiarEstado(EstadoEnum.Normal);
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);
        Pokemon pokemon = new Pokemon("Porygon",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        pokemon.cambiarEstado(EstadoEnum.Envenenado);

        //Assert
        assertFalse(pokemon.tieneEsteEstado(EstadoEnum.Normal));
        assertTrue(pokemon.tieneEsteEstado(EstadoEnum.Envenenado));
        assertEquals(EstadoEnum.Normal,efecto.getEstado());

        //Act
        efecto.aplicarEfecto(pokemon);

        //Assert
        assertFalse(pokemon.tieneEsteEstado(EstadoEnum.Envenenado));
        assertTrue(pokemon.tieneEsteEstado(EstadoEnum.Normal));
    }

}
