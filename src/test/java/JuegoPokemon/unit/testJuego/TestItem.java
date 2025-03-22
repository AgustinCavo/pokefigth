package JuegoPokemon.unit.testJuego;

import JuegoPokemon.modelo.game.*;
import JuegoPokemon.modelo.game.efectos.Efecto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestItem {

    @DisplayName("Crea un Item con efectos y verifico que no apliquen")
    @Test
    void testSePuedoAplicarEfecto() {
        //Arrange
        Pokemon pokemonMock = mock(Pokemon.class);
        Efecto efectoMock = mock(Efecto.class);
        when(efectoMock.sePuedoAplicarEfecto(pokemonMock)).thenReturn(false);

        List<Efecto> efectos = new ArrayList<>();
        efectos.add(efectoMock);

        Item item = new Item(ItemsEnum.Pocion,0,efectos);
        assertFalse(item.puedeAplicarse(pokemonMock));
    }
    @DisplayName("Crea un Item, lo clona y verifica que sean iguales")
    @Test
    void testClonar() {
        //Arrange

        Efecto efectoMock = mock(Efecto.class);

        List<Efecto> efectos = new ArrayList<>();
        efectos.add(efectoMock);

        Item item = new Item(ItemsEnum.Pocion,0,efectos);

        Item item2= (Item) item.clonar();

        assertEquals(item2.getNombre(),item.getNombre());
        assertEquals(item2.getEfectos(),item.getEfectos());
        assertEquals(item2.toString(1),item.toString(1));

    }
}
