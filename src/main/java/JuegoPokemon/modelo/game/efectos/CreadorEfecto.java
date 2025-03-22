package JuegoPokemon.modelo.game.efectos;

import JuegoPokemon.modelo.game.clima.*;
import JuegoPokemon.modelo.game.estado.EstadoEnum;

import java.util.Map;

public class CreadorEfecto {

    public static Efecto crearEfecto(String efecto,Double poder){
        Efecto efectoCreado;

        try {
            efectoCreado = new CambiarEstado(EstadoEnum.valueOf(efecto));
        } catch (IllegalArgumentException e) {
            Map<String, Efecto> efectosPosibles= Map.ofEntries(
                    Map.entry("Curar",new Curar(poder*-1)),
                    Map.entry("VelocidadX",new ModificarEstadistica(EstadisticasEnum.Velocidad,poder)),
                    Map.entry("AtaqueX",new ModificarEstadistica(EstadisticasEnum.Ataque,poder)),
                    Map.entry("DefensaX",new ModificarEstadistica(EstadisticasEnum.Defensa,poder))
            );
            efectoCreado = efectosPosibles.get(efecto);
        }
        return efectoCreado;
    }

}
