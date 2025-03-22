package JuegoPokemon.modelo.game.estado;

import JuegoPokemon.modelo.Constantes;

public class Envenenado implements Estado {
    public Envenenado() {
    }

    @Override
    public EstadoEnum getEstado() {
        return EstadoEnum.Envenenado;
    }

    @Override
    public Double danioPorTurno(Double numeroDelCalculo) {

        return numeroDelCalculo*Constantes.DANIOOENVENAMIENTO;
    }

    @Override
    public Boolean puedeAtacar() {
        return true;
    }

}
