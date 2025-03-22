package JuegoPokemon.unit.testJuego;

import JuegoPokemon.modelo.game.*;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TestJugador {


    @DisplayName("Crea el jugador y verifico que no tenga pokemons")
    @Test
    void testJugadorSeCreaSinPokemons() {

        //Arrange
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        Mochila mockMochila = mock(Mochila.class);
        Jugador jugador = new Jugador("Ash",1,pokemons,mockMochila);


        //Act
        List<Pokemon> pokemonsJugador = jugador.getPokemons();

        //Assert
        assertEquals(0,pokemonsJugador.size());
    }


    @DisplayName("Crea el jugador y verifico que la cantidad de pokemons activos sea 0")
    @Test
    void testJugadorCantidadDePokemonsActivos() {

        //Arrange
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        Mochila mockMochila = mock(Mochila.class);
        Jugador jugador = new Jugador("Ash",1,pokemons,mockMochila);


        //Act
        boolean tienePokemonsActivos = jugador.tienePokemonsActivos();

        //Assert
        assertFalse(tienePokemonsActivos);
    }


    @DisplayName("Crea el jugador y verifica no tenga un Pokemon suelto")
    @Test
    void testJugadorNoTengaPokemonSuelto() {

        //Arrange
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        Mochila mockMochila = mock(Mochila.class);
        Jugador jugador = new Jugador("Ash",1,pokemons,mockMochila);


        //Act
        Pokemon pokemonSuelto = jugador.getPokemonSuelto();

        //Assert
        assertNull(pokemonSuelto);
    }



    @DisplayName("Crea el jugador, agrega un pokemon y lo libera")
    @Test
    void testLiberarPokemon() {
        //Arrange
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        Pokemon pokemonMock = mock(Pokemon.class);
        pokemons.add(pokemonMock);
        Mochila mockMochila = mock(Mochila.class);
        Jugador jugador = new Jugador("Ash",1,pokemons,mockMochila);

        //Act
        jugador.liberarPokemon(pokemonMock);
        Pokemon pokemonSuelto = jugador.getPokemonSuelto();


        //Assert
        assertEquals(pokemonMock,pokemonSuelto);
    }

    @DisplayName("Crea el jugador, verifica que no esta rendido y se puede rendir")
    @Test
    void testRendicion() {
        //Arrange
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        Pokemon pokemonMock = mock(Pokemon.class);
        pokemons.add(pokemonMock);
        Mochila mockMochila = mock(Mochila.class);
        Jugador jugador = new Jugador("Ash",1,pokemons,mockMochila);


        //Assert
        assertFalse(jugador.rendido());

        //Act
        jugador.setRendido();

        //Assert
        assertTrue(jugador.rendido());

    }
    @DisplayName("Crea el jugador, verifica que tenga el nombre y la mochila correcto ")
    @Test
    void testNombreyMochilaCorrecto() {
        //Arrange
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        Pokemon pokemonMock = mock(Pokemon.class);
        pokemons.add(pokemonMock);
        Mochila mockMochila = mock(Mochila.class);
        Jugador jugador = new Jugador("Ash",1,pokemons,mockMochila);


        //Assert
        assertEquals("Ash",jugador.getNombre());
        assertEquals(mockMochila,jugador.getMochila());

    }
    @DisplayName("Crea el jugador, verifica que tenga los items correctos ")
    @Test
    void testComprobacionItemsDetroDeMochilaMedianteJugador() {
        //Arrange
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        Pokemon pokemonMock = mock(Pokemon.class);
        pokemons.add(pokemonMock);

        Item revivir = mock(Item.class);
        when(revivir.getNombre()).thenReturn(ItemsEnum.Revivir);

        Item curaTodo = mock(Item.class);
        when(curaTodo.getNombre()).thenReturn(ItemsEnum.CuraTodo);

        Item pocion = mock(Item.class);
        when(pocion.getNombre()).thenReturn(ItemsEnum.Pocion);

        Item ataqueX = mock(Item.class);
        when(ataqueX.getNombre()).thenReturn(ItemsEnum.AtaqueX);

        Item defensaX = mock(Item.class);
        when(defensaX.getNombre()).thenReturn(ItemsEnum.DefensaX);

        HashMap<Item,Integer> itemsFalsos = new HashMap<>();
        itemsFalsos.put(revivir,1);
        itemsFalsos.put(curaTodo,2);
        itemsFalsos.put(pocion,4);
        itemsFalsos.put(ataqueX,5);
        itemsFalsos.put(defensaX,6);

        Mochila mochilaCargada = new Mochila(itemsFalsos);

        Jugador jugador = new Jugador("Ash",1,pokemons,mochilaCargada);

        //Assert
        assertEquals(mochilaCargada.getItemsDisponibles(),jugador.getItemsDisponibles());
        assertEquals(mochilaCargada.getItem(ItemsEnum.DefensaX),jugador.getItem(ItemsEnum.DefensaX));

    }
    @DisplayName("Crea el jugador, verifica que tenga aplique el item")
    @Test
    public void testAplicarItem() {

        // Arrange
        Mochila mochilaMock = mock(Mochila.class);
        ItemsEnum ataqueXMock = ItemsEnum.AtaqueX;
        Item itemMock = mock(Item.class);
        Pokemon pokemonMock = mock(Pokemon.class);
        ArrayList <Pokemon> pokemons = new ArrayList<>();
        pokemons.add(pokemonMock);

        Jugador jugador = new Jugador("Ash",1,pokemons,mochilaMock);

        HashMap<Item, Integer> items = new HashMap<>();
        items.put(itemMock, 1);
        when(mochilaMock.getItems()).thenReturn(items);
        when(itemMock.getNombre()).thenReturn(ItemsEnum.AtaqueX);
        when(itemMock.puedeAplicarse(pokemonMock)).thenReturn(true);

        //Act
        boolean resultado = jugador.aplicarItem(ataqueXMock, pokemonMock);

        //Assert
        assertTrue(resultado);
        verify(mochilaMock).quitarItem(itemMock);
    }

    @DisplayName("Crea el jugador, y verifica que retorne correctamente los muertos")
    @Test
    public void testPokemonesMuertos() {
        Mochila mochilaMock = mock(Mochila.class);

        Pokemon pokemonMock1 = mock(Pokemon.class);
        when(pokemonMock1.tieneEsteEstado(EstadoEnum.Debilitado)).thenReturn(true);
        Pokemon pokemonMock2 = mock(Pokemon.class);
        when(pokemonMock2.tieneEsteEstado(EstadoEnum.Debilitado)).thenReturn(true);
        Pokemon pokemonMock3 = mock(Pokemon.class);
        when(pokemonMock3.tieneEsteEstado(EstadoEnum.Debilitado)).thenReturn(false);

        ArrayList <Pokemon> pokemons = new ArrayList<>();
        pokemons.add(pokemonMock1);
        pokemons.add(pokemonMock2);
        pokemons.add(pokemonMock3);


        Jugador jugador = new Jugador("Ash",1,pokemons,mochilaMock);


        //Act

        ArrayList <Pokemon> pokemonsMuertos = new ArrayList<>();
        pokemonsMuertos.add(pokemonMock1);
        pokemonsMuertos.add(pokemonMock2);

        ArrayList <Pokemon> pokemonsVivos = new ArrayList<>();
        pokemonsVivos.add(pokemonMock3);


        //Assert
        assertEquals(pokemonsMuertos,jugador.getPokemonsMuertos());
        assertEquals(pokemonsVivos,jugador.getPokemonsVivos());

    }

    }