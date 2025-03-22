package JuegoPokemon.modelo.game.efectos;

import JuegoPokemon.modelo.game.estado.EstadoEnum;
import JuegoPokemon.modelo.game.Pokemon;

public interface Efecto {
    void aplicarEfecto(Pokemon pokemon);

    EstadoEnum getEstado();

    boolean sePuedoAplicarEfecto(Pokemon pokemon);

    Double potenciaEfecto(Pokemon pokemon);

    EfectoEnum getEfectoEnum();
}
