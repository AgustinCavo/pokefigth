package JuegoPokemon.modelo.game;

import java.util.ArrayList;
import java.util.List;

public class AdministradorDeTurnos {
    private int turno;
    private List<Jugador> jugadores;

    public AdministradorDeTurnos(List<Jugador> jugadores) {
        this.turno = 0;
        this.jugadores = jugadores;
    }


    // Pre: Los jugadores tienen que tener su pokemon suelto.
    // Post: Devuelve la lista ordenada de jugadores según su prioridad.
    private List<Jugador> jugadoresOrdenados(List<Jugador> jugadores) {
        Double velocidadJugadorUno = jugadores.get(0).getVelocidadDefaultPokSuelto();
        Double velocidadJugadorDos = jugadores.get(1).getVelocidadDefaultPokSuelto();
        List<Jugador> jugadoresOrdenados = new ArrayList<>();
        if(velocidadJugadorUno > velocidadJugadorDos){
            jugadoresOrdenados.add(jugadores.get(0));
            jugadoresOrdenados.add(jugadores.get(1));
            return jugadoresOrdenados;
        }
        jugadoresOrdenados.add(jugadores.get(1));
        jugadoresOrdenados.add(jugadores.get(0));
        return jugadoresOrdenados;
    }

    // Reordena la lista de jugadores según su prioridad.
    public void actualizarJugadores(){
        this.jugadores = jugadoresOrdenados(this.jugadores);
    }

    public void siguienteTurno() {
        this.turno++;
    }


    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Jugador jugadorDelTurnoActual() {
        return this.jugadores.get(this.turno % 2);
    }

    public Jugador jugadorInactivoDelTurnoActual() {
        if (this.turno % 2 == 0)
            return this.jugadores.get(1);
        return this.jugadores.get(0);
    }

    public void reiniciarTurnos() {
        this.turno = 0;
    }

    public int getTurno(){
        return this.turno;
    }

}