package JuegoPokemon.modelo.game.efectos;

import JuegoPokemon.modelo.game.estado.EstadoEnum;
import JuegoPokemon.modelo.game.Pokemon;

public class Curar implements Efecto {

    private final Double cantidad;

    public Curar(Double potencia) {
        this.cantidad = potencia;

    }
    @Override
    public void aplicarEfecto(Pokemon pokemon) {
        Double vidaActual = pokemon.getVida();
        Double vidaMaxima = pokemon.getVidaMaxima();
        if (vidaActual+this.cantidad >= vidaMaxima){
            pokemon.setVida(pokemon.getVidaMaxima());
        }else{
            pokemon.reducirVida(-this.cantidad);
        }

    }

    public boolean sePuedoAplicarEfecto(Pokemon pokemon) {
        if (pokemon.getVidaMaxima().equals(pokemon.getVida()) || pokemon.tieneEsteEstado(EstadoEnum.Debilitado)) {
            return false;
        }
        return true;
    }

    @Override
    public EstadoEnum getEstado() {
        return null;
    }
    @Override
    public Double potenciaEfecto(Pokemon pokemon) {
        return this.cantidad;
    }
    public EfectoEnum getEfectoEnum() {
        return EfectoEnum.Curar;
    }
}
