package JuegoPokemon.modelo.game.efectos;

import JuegoPokemon.modelo.game.estado.EstadoEnum;
import JuegoPokemon.modelo.game.Pokemon;

public class CambiarEstado implements Efecto {
    private EstadoEnum nuevoEstado;

    //Pre: nada
    //Post: Devuelve el estado de pokemon a normal pisando cualquier estado adverso.

    public CambiarEstado (EstadoEnum nuevoEstado){
        this.nuevoEstado = nuevoEstado;
    }

    public void aplicarEfecto(Pokemon pokemon) {

        if (!pokemon.tieneEsteEstado(EstadoEnum.Normal) & (this.nuevoEstado==EstadoEnum.Normal)) {
            pokemon.resetearEstadoANormal();
        }else if (pokemon.tieneEsteEstado(EstadoEnum.Normal) & (this.nuevoEstado!=EstadoEnum.Normal)){
            pokemon.cambiarEstado(this.nuevoEstado);

        }
    }

    @Override
    public boolean sePuedoAplicarEfecto(Pokemon pokemon) {
        if(!pokemon.tieneEsteEstado(EstadoEnum.Normal) & (this.nuevoEstado==EstadoEnum.Normal) || (pokemon.tieneEsteEstado(EstadoEnum.Normal) & (this.nuevoEstado!=EstadoEnum.Normal))){
            return true;
        }
        return false;
    }

    @Override
    public EstadoEnum getEstado() {
        return nuevoEstado;
    }
    @Override
    public Double potenciaEfecto(Pokemon pokemon) {
        return 0.0;
    }

    @Override
    public EfectoEnum getEfectoEnum() {
        return EfectoEnum.CambiarEstado;
    }
}
