package JuegoPokemon.modelo.game.estado;

public class Normal implements Estado{
    public Normal() {
    }

    @Override
    public EstadoEnum getEstado() {
        return EstadoEnum.Normal;
    }

    @Override
    public Double danioPorTurno(Double numeroDelCalculo) {
        return 0.0;
    }
    @Override
    public Boolean puedeAtacar() {
        return true;
    }
}
