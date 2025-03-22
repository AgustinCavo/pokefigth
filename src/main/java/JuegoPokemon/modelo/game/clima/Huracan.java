package JuegoPokemon.modelo.game.clima;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.Tipo;
import JuegoPokemon.modelo.game.clima.Clima;


import java.util.HashMap;

public class Huracan extends Clima {

    public Huracan(){
        super();
        this.nombreVisible = "Huracán";
    }

    @Override
    public Double danoPorTurno(Pokemon pokemon){
        if (hayTipoEnComun(pokemon.getTipos(), this.tiposBeneficiados()))
            return 0.0;
        return pokemon.getVidaMaxima()* Constantes.PORCENTAJE_REDUCCION_VIDA_POR_CLIMA;
    }

    @Override
    public HashMap<Tipo, Tipo> tiposBeneficiados(){
        HashMap<Tipo, Tipo> tiposBeneficiados = new HashMap<>();
        tiposBeneficiados.put(Tipo.Volador, Tipo.Volador);
        return tiposBeneficiados;
    }

    @Override
    public ClimaEnum getTipo() {
        return ClimaEnum.Huracan;
    }
}
