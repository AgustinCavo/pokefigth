package JuegoPokemon.unit.testJuego;

import JuegoPokemon.modelo.game.Item;
import JuegoPokemon.modelo.game.ItemsEnum;
import JuegoPokemon.modelo.game.Mochila;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestMochila {
    private Mochila mochila;

    private Item revivir;

    private Item curaTodo;

    private Item ataqueX;

    private Item pocion;

    private Item defensaX;

    @BeforeEach
    public void setup() {


        revivir = mock(Item.class);
        when(revivir.getNombre()).thenReturn(ItemsEnum.Revivir);

        curaTodo = mock(Item.class);
        when(curaTodo.getNombre()).thenReturn(ItemsEnum.CuraTodo);

        pocion = mock(Item.class);
        when(pocion.getNombre()).thenReturn(ItemsEnum.Pocion);

        ataqueX = mock(Item.class);
        when(ataqueX.getNombre()).thenReturn(ItemsEnum.AtaqueX);

        defensaX = mock(Item.class);
        when(defensaX.getNombre()).thenReturn(ItemsEnum.DefensaX);

        HashMap<Item,Integer> items = new HashMap<>();
        items.put(revivir,1);
        items.put(curaTodo,2);
        items.put(pocion,4);
        items.put(ataqueX,5);
        items.put(defensaX,6);

        this.mochila = new Mochila(items);

    }

    @DisplayName("Agrega items y verifica que solo devuelva los disponibles")
    @Test
    public void testGetItemsDisponibles() {

        HashMap<Item, Integer> items = new HashMap<>();
        int cont=0;
        items.put(revivir, 3);
        items.put(curaTodo, 0);
        items.put(pocion, 1);
        items.put(ataqueX, 0);

        mochila.setItems(items);

        List<ItemsEnum> itemsDisponibles = new ArrayList<>();
        itemsDisponibles.add(revivir.getNombre());
        itemsDisponibles.add(pocion.getNombre());

        List<ItemsEnum> itemsEnMochila=mochila.getItemsDisponibles();

        for (ItemsEnum enMochila : itemsEnMochila){
            if (itemsDisponibles.contains(enMochila)){
                cont++;
            }
        }

        assertEquals(2,cont);
    }

    @DisplayName("Agrega items y verifica que devuelva las cantidadas correctas")
    @Test
    public void testGetCantidadItem() {

        HashMap<Item, Integer> items = new HashMap<>();

        items.put(revivir, 2);
        items.put(curaTodo, 1);
        items.put(ataqueX, 44);

        mochila.setItems(items);

        assertEquals(mochila.getCantidadItem(ItemsEnum.AtaqueX), 44);
        assertEquals(mochila.getCantidadItem(ItemsEnum.CuraTodo), 1);
        assertEquals(mochila.getCantidadItem(ItemsEnum.Revivir), 2);
        assertEquals(mochila.getCantidadItem(ItemsEnum.Pocion), null);
    }

    @DisplayName("Agrega items y  luego verifica que al removerlo no se encuantre mas")
    @Test
    public void testQuitarItem() {

        HashMap<Item, Integer> items = new HashMap<>();
        items.put(revivir, 3);
        items.put(ataqueX, 1);
        items.put(pocion, 5);
        mochila.setItems(items);

        Map<Item, Integer> itemsRestantes = new HashMap<>();
        itemsRestantes.put(revivir, 2);
        itemsRestantes.put(ataqueX, 0);
        itemsRestantes.put(pocion, 4);

        mochila.quitarItem(ataqueX);
        mochila.quitarItem(revivir);
        mochila.quitarItem(pocion);
        assertEquals(mochila.getItems(), itemsRestantes);
    }

    @DisplayName("Agrega items y  luego verifica que el string para imprimir sea el correcto")
    @Test
    public void testOpcionesParaImprimir() {
        ArrayList<ItemsEnum> itemsEnums = new ArrayList<>();
        itemsEnums.add(ItemsEnum.AtaqueX);
        itemsEnums.add(ItemsEnum.DefensaX);

        HashMap<Item, Integer> items = new HashMap<>();
        items.put(ataqueX, 1);
        items.put(defensaX, 5);
        mochila.setItems(items);

        ArrayList<String> opciones = new ArrayList<>();
        opciones.add(ItemsEnum.AtaqueX + " | " + 1);
        opciones.add(ItemsEnum.DefensaX + " | " + 5);

        assertEquals(mochila.opcionesParaImprimir(itemsEnums), opciones);
    }
    @DisplayName("Agrega items y verifica que este un item especifico")
    @Test
    public void testVerificarUnItemEspecifico() {
        ArrayList<ItemsEnum> itemsEnums = new ArrayList<>();
        itemsEnums.add(ItemsEnum.AtaqueX);
        itemsEnums.add(ItemsEnum.DefensaX);


        HashMap<Item, Integer> items = new HashMap<>();
        items.put(ataqueX, 1);
        items.put(defensaX, 5);
        mochila.setItems(items);

        assertEquals(mochila.getItem(ItemsEnum.Revivir), null);
        assertEquals(mochila.getItem(ItemsEnum.AtaqueX), ataqueX);


    }

}
