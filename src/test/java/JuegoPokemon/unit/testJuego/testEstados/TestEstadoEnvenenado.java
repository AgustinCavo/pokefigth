package JuegoPokemon.unit.testJuego.testEstados;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.estado.Envenenado;
import JuegoPokemon.modelo.game.estado.Estado;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEstadoEnvenenado {

    @DisplayName("Crea el estado envenenado y compruebo el metodo getEstado sea envenenado")
    @Test
    void devulveEnumEstadoEnvenenado(){
        Estado envenenado = new Envenenado();
        assertEquals (EstadoEnum.Envenenado,envenenado.getEstado());
    }

    @DisplayName("Crea el estado envenenado y compruebo el metodo danioPorTurno retire el % de vida acorde a las constantes")
    @Test
    void  comprobacionDañoPorTurnoSeaProcentual(){
            Estado envenenado = new Envenenado();
            double vidaInicial=100.0;

            for (int i=0;i<=3;i++) {
                double aux=vidaInicial;
                vidaInicial= envenenado.danioPorTurno(vidaInicial);
                assertEquals(vidaInicial,aux * Constantes.DANIOOENVENAMIENTO);
            }

    }

    @DisplayName("Crea el estado envenenado y compruebo el metodo puedeAtacar sea false")
    @Test
    void devuelveTrueSiempre(){
        Estado envenenado = new Envenenado();
        assertEquals( true , envenenado.puedeAtacar());

    }
}
