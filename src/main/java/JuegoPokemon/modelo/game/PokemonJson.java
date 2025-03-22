package JuegoPokemon.modelo.game;

import java.util.List;

public class PokemonJson {

    public String nombre;

    public int id;

    public Double vida;

    public List<String> estados;

    public PokemonJson(String nombre, int id, Double vida, List<String> estados) {
        this.nombre = nombre;
        this.id = id;
        this.vida = vida;
        this.estados = estados;
    }
}
