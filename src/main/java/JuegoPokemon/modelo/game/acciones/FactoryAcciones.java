package JuegoPokemon.modelo.game.acciones;

import JuegoPokemon.modelo.game.Habilidad;
import JuegoPokemon.modelo.game.ItemsEnum;
import JuegoPokemon.modelo.game.Jugador;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.clima.Clima;


public class FactoryAcciones {
    public static Accion crearAccionLanzarHabilidad(Habilidad habilidad, Jugador jugadorAtacado, Pokemon atacante, Clima clima) {
            LanzarHabilidad accion = new LanzarHabilidad();
            accion.setHabilidad(habilidad);
            accion.setAtacante(atacante);
            accion.setJugadoAtacado(jugadorAtacado);
            accion.setClima(clima);
            return accion;
    }

    public static Accion crearAccionUtilizarItem(Jugador jugador, Pokemon pokemonSeleccionado, ItemsEnum nombreItem) {
        UtilizarItem accion = new UtilizarItem();
        accion.setJugador(jugador);
        accion.setPokemon(pokemonSeleccionado);
        accion.setItem(nombreItem);
        return accion;
    }

        public static Accion crearAccionLiberarPokemon(Jugador jugador, Pokemon pokemon) {
        LiberarPokemon accion = new LiberarPokemon();
        accion.setJugador(jugador);
        accion.setPokemon(pokemon);
        return accion;
    }

    public static Accion crearAccionRendirse(Jugador jugador) {
        Rendirse accion = new Rendirse();
        accion.setJugador(jugador);
        return accion;
    }
}

