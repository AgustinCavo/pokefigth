package JuegoPokemon.unit.testJuego.testCreacion;

import JuegoPokemon.modelo.game.inicializadores.CreadorItems;
import JuegoPokemon.modelo.game.Item;
import JuegoPokemon.modelo.game.ItemsEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCreacionItems {

    @DisplayName("Crea los items por default y comprueba que sea cantidades y tipos correctos")
    @Test
    void testGenerarItemsDefault(){

        /*CreadorItems.setHashItems();
        HashMap<Integer, Item> items = CreadorItems.getItems();

        HashMap<ItemsEnum, Integer> listadoDefaults= new HashMap<>();
        listadoDefaults.put(ItemsEnum.Pocion,1);
        listadoDefaults.put(ItemsEnum.MegaPocion,1);
        listadoDefaults.put(ItemsEnum.HiperPocion,1);
        listadoDefaults.put(ItemsEnum.Revivir,1);
        listadoDefaults.put(ItemsEnum.CuraTodo,1);
        listadoDefaults.put(ItemsEnum.AtaqueX,2);
        listadoDefaults.put(ItemsEnum.DefensaX,1);
        listadoDefaults.put(ItemsEnum.PocionMolestaAlumnos,3);


        for (Map.Entry<Integer, Item> elemento : items.entrySet()) {

                ItemsEnum item = elemento.getKey().getNombre();
                Integer cantidad = elemento.getValue();

                assertTrue(listadoDefaults.containsKey(item));
                assertTrue(listadoDefaults.get(item).equals(cantidad));

        }*/




    }
}
