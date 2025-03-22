package JuegoPokemon.modelo.game.estado;

public class Debilitado implements Estado{
    public Debilitado() {
    }

    @Override
    public EstadoEnum getEstado() {
        return EstadoEnum.Debilitado;
    }

    @Override
    public Double danioPorTurno(Double numeroDelCalculo) {
        return 0.0;
    }

    @Override
    public Boolean puedeAtacar() {
        return false;
    }
}
