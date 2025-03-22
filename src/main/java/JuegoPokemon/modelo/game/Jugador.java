package JuegoPokemon.modelo.game;

import JuegoPokemon.modelo.game.estado.EstadoEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Jugador {

    private final String nombre;

    private final Integer id;

    private final ArrayList<Pokemon> pokemons;

    private Integer cantidadPokemonsActivos;

    private Pokemon pokemonSuelto;

    private Mochila mochila;

    private boolean rendido = false;

    //Pre: el nombre no debe ser identico al de otro jugador
    //Post: crea un jugador con 0 pokemons y una mochila vacía
    public Jugador(String nombre, Integer id, ArrayList<Pokemon> pokemons, Mochila mochila) {
        this.nombre = nombre;
        this.id = id;
        this.mochila = mochila;
        this.pokemons = pokemons;
        this.cantidadPokemonsActivos = pokemons.size();
        this.pokemonSuelto = null;
    }

    public void setRendido(){ this.rendido = true;}

    public boolean rendido(){ return this.rendido;}

    //Pre: ninguna
    // Post: devuelve el nombre del jugador
    public String getNombre() {
        return nombre;
    }

    //Pre: ninguna
    // Post: devuelve la mochila del jugador
    public Mochila getMochila() { return mochila; }

    public ArrayList<ItemsEnum> getItemsDisponibles(){
        return mochila.getItemsDisponibles();
    }

    public Item getItem(ItemsEnum nombreItem){
        return mochila.getItem(nombreItem);
    }

    // Devuelve true si el pokemon suelto esta muerto, caso contrario devuelve false.
    public boolean pokemonSueltoMuerto(){
        return pokemonSuelto.getVida() <= 0;
    }

    //Pre: ninguna
    // Post: devuelve true en caso de que se haya seleccionado un pokemon, false en caso contrario
    //      permitiendo dejar libre a un pokemon y guardando el que estaba libre
    // EDIT: libera el pokemon cuya posicion en su lista de pokemons es el indice.
    public void liberarPokemon(Pokemon pokemonElegido) {
        int indexPokemon = pokemons.indexOf(pokemonElegido);
        this.pokemonSuelto = pokemons.get(indexPokemon);
    }



    //Pre: ninguna
    //Post: devuelve el pokemon que tenga libre
    public Pokemon getPokemonSuelto() {
        return pokemonSuelto;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public ArrayList<Pokemon> getPokemonsMuertos(){
        ArrayList<Pokemon> pokemonsMuertos = new ArrayList<>();
        for (Pokemon pokemon : pokemons){
            if (pokemon.tieneEsteEstado(EstadoEnum.Debilitado))
                pokemonsMuertos.add(pokemon);
        }
        return pokemonsMuertos;
    }

    public ArrayList<Pokemon> getPokemonsGuardados(){
        ArrayList<Pokemon> pokemonsGuardados = new ArrayList<>(this.pokemons);
        pokemonsGuardados.remove(pokemonSuelto);
        return pokemonsGuardados;
    }



    public ArrayList<Pokemon> getPokemonsVivos() {
        ArrayList<Pokemon> pokemonsVivos = new ArrayList<>(this.pokemons);
        pokemonsVivos.removeAll(getPokemonsMuertos());
        return pokemonsVivos;
    }

    public Double getVelocidadDefaultPokSuelto(){
        return pokemonSuelto.getVelocidadDeafault();
    }

    public boolean aplicarItem(ItemsEnum nombreItem, Pokemon pokemonSeleccionado) {
        Item itemSeleccionado = null;
        for (Map.Entry<Item, Integer> par : mochila.getItems().entrySet()){
            if (nombreItem == par.getKey().getNombre() && par.getValue() > 0) {
                itemSeleccionado = par.getKey();
                break;
            }
        }

        if (itemSeleccionado != null && itemSeleccionado.puedeAplicarse(pokemonSeleccionado)) {
            itemSeleccionado.aplicarEfectos(pokemonSeleccionado);
            this.mochila.quitarItem(itemSeleccionado);
            if (itemSeleccionado.getNombre() == ItemsEnum.Revivir) {
                aumentarPokemonsActivos();
            }
            return true;
        }
        return false;
    }

    public boolean tienePokemonsActivos(){
        return cantidadPokemonsActivos != 0;
    }

    public void reducirPokemonsActivos() {
        this.cantidadPokemonsActivos --;
    }

    public void aumentarPokemonsActivos() {
        this.cantidadPokemonsActivos ++;
    }

    public List<EstadoEnum> getEstadosPokemonSuelto() {
        return pokemonSuelto.getListaEstados();
    }

    public Double getVida(){
        return this.pokemonSuelto.getVida();
    }
    public Double getVidamaxima(){
        return pokemonSuelto.getVidaMaxima();
    }

    public boolean pokemonSueltoEstaDebilitado() {
        return this.pokemonSuelto.estaDebilitado();
    }

    public boolean contienePokemon(Pokemon pokemon) {
        for(Pokemon pok : pokemons)
            if (pok == pokemon)
                return true;
        return false;
    }

    public List<Pokemon> getPokemonsAplicables(Item item) {
        List<Pokemon> pokemonesAplicables = new ArrayList<>();
        for (Pokemon pokemon : pokemons){
            if (item.puedeAplicarse(pokemon))
                pokemonesAplicables.add(pokemon);
        }
        return pokemonesAplicables;
    }

    public HashMap<Item, Integer> getItems(){
        return mochila.getItems();
    }
}
