package JuegoPokemon.modelo.game.clima;

import JuegoPokemon.modelo.game.Tipo;
import JuegoPokemon.modelo.game.clima.Clima;


import java.util.HashMap;

public class Niebla extends Clima {

    public Niebla(){
        super();
        this.nombreVisible = "Neblinoso";
    }

    @Override
    public HashMap<Tipo, Tipo> tiposBeneficiados(){
        HashMap<Tipo, Tipo> tiposBeneficiados = new HashMap<>();
        tiposBeneficiados.put(Tipo.Fantasma, Tipo.Fantasma);
        tiposBeneficiados.put(Tipo.Psiquico, Tipo.Psiquico);
        return tiposBeneficiados;
    }
    @Override
    public ClimaEnum getTipo() {
        return ClimaEnum.Niebla;
    }
}
