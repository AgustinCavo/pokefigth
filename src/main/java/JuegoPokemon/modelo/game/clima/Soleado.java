
package JuegoPokemon.modelo.game.clima;

import JuegoPokemon.modelo.game.Tipo;
import JuegoPokemon.modelo.game.clima.Clima;


import java.util.HashMap;

public class Soleado extends Clima {

    public Soleado(){
        super();
        this.nombreVisible = "Soleado";
    }

    @Override
    public HashMap<Tipo, Tipo> tiposBeneficiados(){
        HashMap<Tipo, Tipo> tiposBeneficiados = new HashMap<>();
        tiposBeneficiados.put(Tipo.Fuego, Tipo.Fuego);
        return tiposBeneficiados;
    }
    @Override
    public ClimaEnum getTipo() {
        return ClimaEnum.Soleado;
    }
}
