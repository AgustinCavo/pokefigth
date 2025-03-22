package JuegoPokemon.modelo.game.estado;

import java.util.Map;

public class CreadorEstados {

    public static Estado crear(EstadoEnum estadoNuevo){

        Map<EstadoEnum,Estado> estadosPosibles= Map.ofEntries(
                Map.entry(EstadoEnum.Normal,new Normal()),
                Map.entry(EstadoEnum.Debilitado,new Debilitado()),
                Map.entry(EstadoEnum.Paralizado,new Paralizado()),
                Map.entry(EstadoEnum.Dormido,new Dormido()),
                Map.entry(EstadoEnum.Envenenado,new Envenenado()),
                Map.entry(EstadoEnum.Confuso,new Confuso())
        );

        return estadosPosibles.get(estadoNuevo);
    }

}

