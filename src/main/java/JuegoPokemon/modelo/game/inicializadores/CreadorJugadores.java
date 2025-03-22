package JuegoPokemon.modelo.game.inicializadores;

import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.Mochila;
import JuegoPokemon.modelo.game.Item;
import JuegoPokemon.modelo.Constantes;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CreadorJugadores {

    private static String path;

    //Pre: nada creo
    //Post: Crea dos jugadores (con nombre ingresado por usuario) y los devuelve.
    public static List<Jugador> jugadoresInicializados() {

        List<Jugador> listaJugadores = new ArrayList<>();
        try {
            StringBuilder info = new StringBuilder();
            File archivo = new File(CreadorJugadores.path);
            Scanner scanner = new Scanner(archivo);

            while (scanner.hasNextLine())
                info.append(scanner.nextLine());

            JSONArray jugadores = new JSONArray(info.toString());
            JSONObject jugadorJsonActual;
            for (int i = 0; i < jugadores.length(); i++) {
                jugadorJsonActual = jugadores.getJSONObject(i);
                String nombre = jugadorJsonActual.getString("nombre");
                Integer id = jugadorJsonActual.getInt("id");

                JSONObject itemsJSON = jugadorJsonActual.getJSONObject("items");
                Map<Integer, Integer> idItems = new HashMap<>();
                for (String key : itemsJSON.keySet()) {
                    int itemId = Integer.parseInt(key);
                    int cantidad = itemsJSON.getInt(key);
                    idItems.put(itemId, cantidad);
                }
                HashMap<Item,Integer> items = new HashMap<>();
                for (Map.Entry<Integer, Integer> par : idItems.entrySet())
                    items.put(CreadorItems.itemPorId(par.getKey()), par.getValue());

                Mochila mochila = new Mochila(items);


                JSONArray pokemonsJSON = jugadorJsonActual.getJSONArray("pokemons");
                ArrayList<Integer> idPokemones = new ArrayList<>();
                for (int j = 0; j < pokemonsJSON.length(); j++)
                    idPokemones.add(pokemonsJSON.getInt(j));
                ArrayList<Pokemon> pokemons = new ArrayList<>();
                for (Integer idPokemonActual : idPokemones) {
                    pokemons.add(CreadorPokemons.pokemonPorId(idPokemonActual));
                }
                listaJugadores.add(new Jugador(nombre, id, pokemons, mochila));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listaJugadores;
    }

    public static void setPath(String path) {
        CreadorJugadores.path = path;
    }
}
