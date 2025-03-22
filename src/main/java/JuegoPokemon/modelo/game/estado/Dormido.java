package JuegoPokemon.modelo.game.estado;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Calculadora;

public class Dormido implements Estado {

    private int turnosDormido = 0;
    public Dormido() {
    }

    @Override
    public EstadoEnum getEstado() {
        return EstadoEnum.Dormido;
    }

    @Override
    public Boolean puedeAtacar() {

        double intentoDespertarse =Constantes.PROBABILIADADBASEDESPERTAR + this.turnosDormido * Constantes.PROBABILIADADBASEDESPERTAR;
        double fuerzaSuenio= Calculadora.generarRandomProbabilidad();
        this.turnosDormido += 1;
        return fuerzaSuenio<intentoDespertarse;
    }

    @Override
    public Double danioPorTurno(Double numeroDelCalculo) {
        return 0.0;
    }
}
