package JuegoPokemon.modelo.game.estado;

public interface Estado {
     EstadoEnum getEstado();

     Double danioPorTurno(Double numeroDelCalculo);

     Boolean puedeAtacar();
}
