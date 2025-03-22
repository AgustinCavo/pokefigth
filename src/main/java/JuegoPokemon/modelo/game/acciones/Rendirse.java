package JuegoPokemon.modelo.game.acciones;

import JuegoPokemon.modelo.game.Jugador;


public class Rendirse implements Accion {
    private Jugador jugador;

    @Override
    public boolean realizarAccion() {
        jugador.setRendido();
        return true;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public TipoAccion getTipo() {
        return TipoAccion.RENDIRSE;
    }
}
