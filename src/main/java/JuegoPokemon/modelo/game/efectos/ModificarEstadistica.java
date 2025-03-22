package JuegoPokemon.modelo.game.efectos;

import JuegoPokemon.modelo.game.estado.EstadoEnum;
import JuegoPokemon.modelo.game.Pokemon;

import java.util.Map;
public class ModificarEstadistica implements Efecto {
    private final EstadisticasEnum estadistica;

    private final Double poder;
    public ModificarEstadistica(EstadisticasEnum estadistica, Double poder) {
        this.estadistica = estadistica;
        this.poder=poder;


    }

    @Override
    public void aplicarEfecto(Pokemon pokemon) {
        Map<EstadisticasEnum, Double> estadosPosibles= Map.ofEntries(
                Map.entry(EstadisticasEnum.Ataque,pokemon.getAtaque() * this.poder),
                Map.entry(EstadisticasEnum.Defensa,pokemon.getDefensa() * this.poder),
                Map.entry(EstadisticasEnum.Velocidad,pokemon.getVelocidad() * this.poder)
        );
        pokemon.cambiarEstadistica(estadistica,estadosPosibles.get(estadistica));
    }
    public boolean sePuedoAplicarEfecto(Pokemon pokemon) {
       return true;
    }

    public EstadisticasEnum getEstadistica() {
        return estadistica;
    }
    public EstadoEnum getEstado() {
        return null;
    }
    public Double potenciaEfecto(Pokemon pokemon) {
        Map<EstadisticasEnum, Double> estadosPosibles= Map.ofEntries(
                Map.entry(EstadisticasEnum.Ataque,pokemon.getAtaque() * this.poder),
                Map.entry(EstadisticasEnum.Defensa,pokemon.getDefensa() * this.poder),
                Map.entry(EstadisticasEnum.Velocidad,pokemon.getVelocidad() * this.poder)
        );
        return estadosPosibles.get(estadistica);
    }
    public EfectoEnum getEfectoEnum() {
        return EfectoEnum.ModificarEstadistica;
    }
}
