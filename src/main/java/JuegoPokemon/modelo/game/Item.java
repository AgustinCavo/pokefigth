package JuegoPokemon.modelo.game;


import java.util.List;

import JuegoPokemon.modelo.game.efectos.Efecto;
import JuegoPokemon.modelo.game.estado.EstadoEnum;


public class Item implements Clonable{
    private final ItemsEnum nombre;

    private final Integer id;
    private final List<Efecto> efectos;

    public Item(ItemsEnum nombre, Integer id, List<Efecto> efectos) {
        this.nombre = nombre;
        this.id = id;
        this.efectos = efectos;
    }

    public Integer getId() {
        return id;
    }

    public boolean puedeAplicarse(Pokemon pokemon){
        for (Efecto efecto: efectos ) {
            if(!efecto.sePuedoAplicarEfecto(pokemon))
                return false;
        }
        if(ItemsEnum.CuraTodo.equals(this.nombre) && pokemon.tieneEsteEstado(EstadoEnum.Debilitado)){
            return false;
        }
        return true;
    }

    //Pre: cantidad es la cantidad de este item que tiene en la mochila.
    //Post: devuelve un string con todos los datos utiles acerca del item
    //      permitiendo imprimir una instancia del item
    public String toString(int cantidad){
        return " " + this.nombre + " | " + cantidad;
    }

    public ItemsEnum getNombre() {
        return nombre;
    }

    public List<Efecto> getEfectos() {
        return efectos;
    }

    @Override
    public Object clonar() {
        return new Item(this.nombre, this.id, this.efectos);
    }

    public void aplicarEfectos(Pokemon pokemon){
        for (Efecto efecto: efectos ) {
            efecto.aplicarEfecto(pokemon);
        }
    }
}
