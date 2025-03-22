package JuegoPokemon.modelo.game.efectos;

import JuegoPokemon.modelo.game.estado.EstadoEnum;
import JuegoPokemon.modelo.game.Pokemon;

public class CurarPorcentual implements Efecto {
    private final Double porcentaje;



    public CurarPorcentual(Double porcentaje) {
        this.porcentaje=porcentaje;
    }

    public void aplicarEfecto(Pokemon pokemon) {
        Double vidaActual=pokemon.getVida();
        Double vidaMaxima=pokemon.getVidaMaxima();
        Double vidaACurar= vidaMaxima * porcentaje;

        if (vidaActual + vidaACurar >= vidaMaxima) {
            pokemon.setVida(pokemon.getVidaMaxima());
            } else {
                pokemon.reducirVida(-(vidaACurar));
        }

    }
    public EfectoEnum getEfectoEnum() {
        return EfectoEnum.CurarPorcentual;
    }


    public boolean sePuedoAplicarEfecto(Pokemon pokemon) {
        if (pokemon.getVidaMaxima().equals(pokemon.getVida()) || pokemon.tieneEsteEstado(EstadoEnum.Debilitado)) {
            return false;
        }
        return true;
    }

    public EstadoEnum getEstado() {
        return null;
    }
    public Double potenciaEfecto(Pokemon pokemon) {
        return pokemon.getVidaMaxima() * porcentaje;
    }
}
