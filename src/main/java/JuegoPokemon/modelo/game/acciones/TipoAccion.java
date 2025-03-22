package JuegoPokemon.modelo.game.acciones;

import java.util.ArrayList;
import java.util.Objects;

public enum TipoAccion {
    LANZAR_HABILIDAD(0, "Lanzar Habilidad"),
    LIBERAR_POKEMON(1, "Liberar/Cambiar Pokemon"),
    UTILIZAR_ITEM(2, "Utilizar Item"),
    RENDIRSE(3, "Rendirse");

    private final Integer valor;
    private final String nombreLegible;

    TipoAccion(int valor, String nombreLegible) {
        this.valor = valor;
        this.nombreLegible = nombreLegible;
    }

}
