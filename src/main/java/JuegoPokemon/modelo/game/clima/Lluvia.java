
package JuegoPokemon.modelo.game.clima;

import JuegoPokemon.modelo.game.Tipo;
import JuegoPokemon.modelo.game.clima.Clima;

import java.util.HashMap;

public class Lluvia extends Clima {

    public Lluvia(){
        super();
        this.nombreVisible = "Lluvioso";
    }

    @Override
    public HashMap<Tipo, Tipo> tiposBeneficiados(){
        HashMap<Tipo, Tipo> tiposBeneficiados = new HashMap<>();
        tiposBeneficiados.put(Tipo.Agua, Tipo.Agua);
        tiposBeneficiados.put(Tipo.Planta, Tipo.Planta);
        return tiposBeneficiados;
    }
    @Override
    public ClimaEnum getTipo() {
        return ClimaEnum.Lluvia;
    }
}
