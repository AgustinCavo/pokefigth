package JuegoPokemon.modelo.game;

import JuegoPokemon.modelo.game.estado.Estado;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import JuegoPokemon.modelo.game.PokemonJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JugadorJson {
    public String nombre;

    public boolean ganador;

    public List<PokemonJson> pokemons;

    public HashMap<Integer, Integer> items;

    public JugadorJson(String nombre, boolean ganador, List<Pokemon> pokemons, HashMap<Item, Integer> items) {
        this.nombre = nombre;
        this.ganador = ganador;
        List<PokemonJson> pokemonsJson = new ArrayList<PokemonJson>();
        for (Pokemon pokemon : pokemons){
            List<String> estados = new ArrayList<>();
            for (Map.Entry<EstadoEnum, Estado> par : pokemon.getEstados().entrySet())
                estados.add(par.getKey().toString());

            pokemonsJson.add(new PokemonJson(pokemon.getNombre(), pokemon.getId(), pokemon.getVida(), estados));
        }

        HashMap<Integer, Integer> itemsJson = new HashMap<>();
        for (Map.Entry<Item, Integer> par : items.entrySet())
            itemsJson.put(par.getKey().getId(), par.getValue());


        this.pokemons = pokemonsJson;
        this.items = itemsJson;
    }
}
