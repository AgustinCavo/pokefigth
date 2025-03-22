package JuegoPokemon.modelo.game.clima;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.Tipo;


import java.util.HashMap;

public class TormentaDeRayos extends Clima {

    public TormentaDeRayos(){
        super();
        this.nombreVisible = "Tormenta Eléctrica";
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
        tiposBeneficiados.put(Tipo.Electrico, Tipo.Electrico);
        return tiposBeneficiados;
    }
    @Override
    public ClimaEnum getTipo() {
        return ClimaEnum.TormentaDeRayos;
    }
}
