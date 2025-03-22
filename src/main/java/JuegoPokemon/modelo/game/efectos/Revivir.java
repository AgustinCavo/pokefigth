package JuegoPokemon.modelo.game.efectos;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.estado.EstadoEnum;

import java.util.Map;

public class Revivir implements Efecto{




    @Override
    public void aplicarEfecto(Pokemon pokemon) {
        pokemon.resetearEstadoANormal();
        Double vidaMaxima = pokemon.getVidaMaxima();
        Double vidaACurar = vidaMaxima * Constantes.REVIVIR;
        pokemon.reducirVida(-(vidaACurar));

    }

    @Override
    public EstadoEnum getEstado() {
        return null;
    }

    @Override
    public boolean sePuedoAplicarEfecto(Pokemon pokemon) {
        if(pokemon.estaDebilitado()) {
            return true;
        }
        return false;
    }
    public Double potenciaEfecto(Pokemon pokemon) {
        return pokemon.getVidaMaxima() * Constantes.REVIVIR;
    }
    public EfectoEnum getEfectoEnum() {
        return EfectoEnum.Revivir;
    }
}
