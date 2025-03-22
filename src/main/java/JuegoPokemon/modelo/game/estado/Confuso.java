package JuegoPokemon.modelo.game.estado;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Calculadora;

public class Confuso implements Estado {

    private int turnosConfuso = 0;

    public Confuso() {
    }

    @Override
    public EstadoEnum getEstado() {
        return EstadoEnum.Confuso;
    }

    @Override
    public Double danioPorTurno(Double numeroDelCalculo) {
        double fuerzaConfusion = Calculadora.generarRandomProbabilidad();
        if (fuerzaConfusion < Constantes.PROBABILIADADGOLPEARSE) {
            return (numeroDelCalculo * Constantes.DANIOPORCONFUSION);
        }
        return 0.0;

    }

    @Override
    public Boolean puedeAtacar() {
        this.turnosConfuso += 1;
        return this.turnosConfuso >= 3;
    }
}
