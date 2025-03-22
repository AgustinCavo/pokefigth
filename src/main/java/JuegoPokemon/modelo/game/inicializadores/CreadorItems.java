package JuegoPokemon.modelo.game.inicializadores;

import JuegoPokemon.modelo.game.Item;
import JuegoPokemon.modelo.game.ItemsEnum;
import JuegoPokemon.modelo.game.efectos.*;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static JuegoPokemon.modelo.Constantes.*;

public class CreadorItems {

    // <id, Item>
    private static HashMap<Integer, Item> items;


    // Carga el atributo con los items del json DIRECCION_ITEMS
    public static void setHashItems() {
        HashMap<Integer, Item> items = new HashMap<>();
        try {
            StringBuilder info = new StringBuilder();
            File archivo = new File(DIRECCION_ITEMS);
            Scanner scanner = new Scanner(archivo);

            while (scanner.hasNextLine())
                info.append(scanner.nextLine());


            JSONArray itemsJson = new JSONArray(info.toString());
            JSONObject itemJsonActual;
            for (int i = 0; i < itemsJson.length(); i++){
                itemJsonActual = itemsJson.getJSONObject(i);
                String nombre = itemJsonActual.getString("nombre");
                ItemsEnum nombreEnum = ItemsEnum.valueOf(nombre);
                Integer id = itemJsonActual.getInt("id");
                Double potencia = itemJsonActual.getDouble("potencia");

                JSONArray efectosJSON = itemJsonActual.getJSONArray("efectos");
                ArrayList<String> efectosStr = new ArrayList<>();
                for (int j = 0; j < efectosJSON.length(); j++)
                    efectosStr.add(efectosJSON.getString(j));

                ArrayList<Efecto> efectos = new ArrayList<>();
                for (String efectoStr : efectosStr)
                    efectos.add(strAEfecto(efectoStr, nombreEnum, potencia));

                items.put(id, new Item(nombreEnum, id, efectos));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        CreadorItems.items = items;
    }

    public static Efecto strAEfecto(String efectoStr, ItemsEnum nombre, Double potencia){
        switch (efectoStr) {
            case "Curar":
                return new Curar(potencia);
            case "CambiarEstado":
                return new CambiarEstado(EstadoEnum.Normal);
            case "ModificarEstadistica":
                if (nombre == ItemsEnum.AtaqueX)
                    return new ModificarEstadistica(EstadisticasEnum.Ataque, potencia);
                if (nombre == ItemsEnum.DefensaX)
                    return new ModificarEstadistica(EstadisticasEnum.Defensa, potencia);
            case "CurarPorcentual":
                return new CurarPorcentual(potencia);
            case "Revivir":
                return new Revivir();
        }
        return null;
    }

    //Pre: Un item tiene que tener esa id.
    //post: Devuelve una instancia de item inicializado cuyo id es el ingresado.
    public static Item itemPorId(Integer itemId){
        return (Item) items.get(itemId).clonar();
    }

    public static HashMap<Integer, Item> getItems() {
        return items;
    }
}