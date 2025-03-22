package JuegoPokemon.modelo.game.estado;


import static JuegoPokemon.modelo.Constantes.PROBABILIADADPARALIZADO;
import JuegoPokemon.modelo.game.Calculadora;

public class Paralizado implements Estado{
    public Paralizado() {
    }

    //pre:-
    //post:devuelve el estado enumerativo asociado a la clase
    @Override
    public EstadoEnum getEstado() {
        return EstadoEnum.Paralizado;
    }

    //pre:-
    //post: devuelve true si el random generado supera la probabiliadad de atacar
    @Override
    public Boolean puedeAtacar() {
        double intento= Calculadora.generarRandomProbabilidad();
        return intento>PROBABILIADADPARALIZADO;
    }

    @Override
    public Double danioPorTurno(Double numeroDelCalculo) {
        return 0.0;
    }
}
