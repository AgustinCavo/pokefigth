package JuegoPokemon.modelo.game.inicializadores;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Habilidad;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.Tipo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CreadorPokemons {

    // <id, pokemon>
    private static HashMap<Integer, Pokemon> hashPokemons;
    private static String path;


    //Pre: El tree de Habilidades no puede ser null.
    //Post:XXXX Crea y devuelve un arbol con los pokemons del archivo json cuyo path es 'DIRECCION_POKEMONS'
    // Post: Crea un arbol con los pokemons del archivo json cuyo path es 'DIRECCION_POKEMONS' y lo setea como atributo.
    public static void setHashPokemons(){
        HashMap<Integer, Habilidad> hashHabilidades = CreadorHabilidades.getHabilidades();
        HashMap<Integer, Pokemon> hashPokemons = new HashMap<>();
        try {
            StringBuilder info = new StringBuilder();
            File archivo = new File(path);
            Scanner scanner = new Scanner(archivo);

            while (scanner.hasNextLine())
                info.append(scanner.nextLine());


            JSONArray pokemones = new JSONArray(info.toString());
            JSONObject pokemonJsonActual;
            for (int i = 0; i < pokemones.length(); i++){
                pokemonJsonActual = pokemones.getJSONObject(i);
                String nombre = pokemonJsonActual.getString("nombre");
                Integer id = pokemonJsonActual.getInt("id");
                String tipoSTR = pokemonJsonActual.getString("tipo");
                ArrayList<Tipo> tipos = new ArrayList<>();
                tipos.add(Tipo.valueOf(tipoSTR));
                String historia = pokemonJsonActual.getString("historia");
                Double vidaMaxima = pokemonJsonActual.getDouble("vidaMaxima");
                Double velocidad = pokemonJsonActual.getDouble("velocidad");
                Double defensa = pokemonJsonActual.getDouble("defensa");
                Double ataque = pokemonJsonActual.getDouble("ataque");
                JSONArray habilidadesJSON = pokemonJsonActual.getJSONArray("habilidades");
                ArrayList<Integer> idHabilidades = new ArrayList<>();
                for (int j = 0; j < habilidadesJSON.length(); j++)
                    idHabilidades.add(habilidadesJSON.getInt(j));

                List<Habilidad> habilidades = new ArrayList<>();

                for (Habilidad habilidad : hashHabilidades.values()){
                    if (idHabilidades.contains(habilidad.getId()))
                        habilidades.add((Habilidad)habilidad.clonar());
                }

                hashPokemons.put(id, new Pokemon(nombre, id, tipos, historia, vidaMaxima, velocidad, defensa, ataque, habilidades));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        CreadorPokemons.hashPokemons = hashPokemons;
    }

    //Pre: Un pokemon tiene que tener esa id.
    //post: Devuelve una instancia de pokemon inicializado cuyo id es el ingresado.
    public static Pokemon pokemonPorId(Integer pokemonId){
        return (Pokemon) hashPokemons.get(pokemonId).clonar();
    }

    public static HashMap<Integer, Pokemon> getHashPokemons() {
        return CreadorPokemons.hashPokemons;
    }
    public static void setPath(String path) {
        CreadorPokemons.path = path;
    }

}
