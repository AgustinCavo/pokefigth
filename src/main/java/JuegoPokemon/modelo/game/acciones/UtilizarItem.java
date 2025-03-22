package JuegoPokemon.modelo.game.acciones;

import JuegoPokemon.modelo.game.ItemsEnum;
import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;


public class UtilizarItem implements Accion {

    private Jugador jugador;
    private Pokemon pokemonSeleccionado;
    private ItemsEnum nombreItem;

    @Override
    public boolean realizarAccion() {
        return jugador.aplicarItem(nombreItem, pokemonSeleccionado);
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setPokemon(Pokemon pokemonSeleccionado) {
        this.pokemonSeleccionado = pokemonSeleccionado;
    }

    public void setItem(ItemsEnum item) {
        nombreItem = item;
    }

    public TipoAccion getTipo() {
        return TipoAccion.UTILIZAR_ITEM;
    }

}
