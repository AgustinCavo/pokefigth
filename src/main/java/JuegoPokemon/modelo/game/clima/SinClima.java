package JuegoPokemon.modelo.game.clima;

import JuegoPokemon.modelo.game.Tipo;
import JuegoPokemon.modelo.game.clima.Clima;


import java.util.HashMap;

public class SinClima extends Clima {

    public SinClima(){
        super();
        this.nombreVisible = "Normal";
    }
    @Override
    public HashMap<Tipo, Tipo> tiposBeneficiados(){
        return new HashMap<>();
    }

    @Override
    public void reducirTurnos(){}
    @Override
    public ClimaEnum getTipo() {
        return ClimaEnum.SinClima;

    }
}
