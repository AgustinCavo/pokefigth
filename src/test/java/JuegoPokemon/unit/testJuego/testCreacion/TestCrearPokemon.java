package JuegoPokemon.unit.testJuego.testCreacion;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.*;
import JuegoPokemon.modelo.game.inicializadores.CreadorHabilidades;
import JuegoPokemon.modelo.game.inicializadores.CreadorPokemons;
import JuegoPokemon.modelo.game.Habilidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestCrearPokemon {

    @DisplayName("Crea el listado de habilidades y se verifica que tenga los mismos atributos que en el JSON")
    @Test
    void testCreacionHabilidades() {

        //Arrange

        CreadorHabilidades.setPath(Constantes.DIRECCION_HABILIDADES);
        CreadorPokemons.setPath(Constantes.DIRECCION_POKEMONS);
        CreadorHabilidades.setHashHabilidades();
        CreadorPokemons.setHashPokemons();
        HashMap<Integer, Habilidad> habilidades = CreadorHabilidades.getHabilidades();
        
        HashMap<Integer, Pokemon> pokemones = CreadorPokemons.getHashPokemons();

        int contb=0;
        int contc=0;

        List<Tipo> tipoCharizard= new ArrayList<>();
        tipoCharizard.add(Tipo.Planta);

        List<Tipo> tipoBlastoise= new ArrayList<>();
        tipoBlastoise.add(Tipo.Fuego);

        List<Habilidad> habilidadesCharizard= new ArrayList<>();
        habilidadesCharizard.add(habilidades.get(0));
        habilidadesCharizard.add(habilidades.get(1));
        habilidadesCharizard.add(habilidades.get(2));

        List<Habilidad> habilidadesBlastoise= new ArrayList<>();
        habilidadesBlastoise.add(habilidades.get(3));
        habilidadesBlastoise.add(habilidades.get(4));
        habilidadesBlastoise.add(habilidades.get(5));

        Pokemon charizardCreadoAMano = new Pokemon("Charizard",0,tipoCharizard,"Es un pequeño Pokémon de color verde, con una planta brotando de su lomo.",90.0,45.0,49.0,49.0,habilidadesCharizard);
        Pokemon blastoiseCreadoAMano = new Pokemon("Blastoise",1,tipoBlastoise,"Blastoise es un pequeño lagarto bípedo. Su piel es de color naranja-rojizo.",80.0,65.0,43.0,52.0,habilidadesBlastoise);

        //Assert
        assertTrue(pokemones.containsKey(0));
        assertTrue(pokemones.containsKey(1));

        //Act
        Pokemon charizardDelArbol = pokemones.get(0);
        Pokemon blastoiseDelArbol = pokemones.get(1);

        //Assert
        assertEquals(charizardDelArbol.getId(),charizardCreadoAMano.getId());
        assertEquals(charizardDelArbol.getTipos(),charizardCreadoAMano.getTipos());
        assertEquals(charizardDelArbol.getHistoria(),charizardCreadoAMano.getHistoria());
        assertEquals(charizardDelArbol.getVida(),charizardCreadoAMano.getVida());
        assertEquals(charizardDelArbol.getVelocidad(),charizardCreadoAMano.getVelocidad());
        assertEquals(charizardDelArbol.getDefensa(),charizardCreadoAMano.getDefensa());
        assertEquals(charizardDelArbol.getAtaque(),charizardCreadoAMano.getAtaque());

        List<Habilidad> habilidadesEnPokemonB=charizardDelArbol.getHabilidades();

        //assertEquals(charizardDelArbol.getHabilidades(),charizardCreadoAMano.getHabilidades());
        for (Habilidad habilidadEnPokemon : habilidadesEnPokemonB){
            for (int i = 0; i < habilidadesCharizard.size() ; i++) {
                if (Objects.equals(habilidadesCharizard.get(i).getNombre(), habilidadEnPokemon.getNombre())){
                    contb++;
                }
            }
        }

        assertEquals(3,contb);

        assertEquals(blastoiseDelArbol.getId(),blastoiseCreadoAMano.getId());
        assertEquals(blastoiseDelArbol.getTipos(),blastoiseCreadoAMano.getTipos());
        assertEquals(blastoiseDelArbol.getHistoria(),blastoiseCreadoAMano.getHistoria());
        assertEquals(blastoiseDelArbol.getVida(),blastoiseCreadoAMano.getVida());
        assertEquals(blastoiseDelArbol.getVelocidad(),blastoiseCreadoAMano.getVelocidad());
        assertEquals(blastoiseDelArbol.getDefensa(),blastoiseCreadoAMano.getDefensa());
        assertEquals(blastoiseDelArbol.getAtaque(),blastoiseCreadoAMano.getAtaque());

        List<Habilidad> habilidadesEnPokemoC=charizardDelArbol.getHabilidades();

        for (Habilidad habilidadEnPokemon : habilidadesEnPokemoC){
            for (int i = 0; i < habilidadesCharizard.size() ; i++) {
                if (Objects.equals(habilidadesCharizard.get(i).getNombre(), habilidadEnPokemon.getNombre())){
                    contc++;
                }
            }
        }
        assertEquals(3,contc);

    }
}

