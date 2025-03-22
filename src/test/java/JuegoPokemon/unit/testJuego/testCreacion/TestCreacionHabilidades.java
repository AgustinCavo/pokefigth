package JuegoPokemon.unit.testJuego.testCreacion;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.*;
import JuegoPokemon.modelo.game.efectos.CambiarEstado;
import JuegoPokemon.modelo.game.efectos.Efecto;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import JuegoPokemon.modelo.game.inicializadores.CreadorHabilidades;
import org.mockito.MockedStatic;

import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class TestCreacionHabilidades {
    @DisplayName("Crea el listado de habilidades y se verifica que tenga los mismos atributos que en el JSON")
    @Test
    void testCreacionHabilidades() {

        //Arrange

        /*try (MockedStatic mockStatic = mockStatic(CreadorHabilidades.class)) {
            when(CreadorHabilidades.getHabilidades()).thenReturn(CreadorHabilidades.getHabilidades());
           */

        CreadorHabilidades.setPath(Constantes.DIRECCION_HABILIDADES);
        CreadorHabilidades.setHashHabilidades();
        HashMap<Integer, Habilidad> habilidades = CreadorHabilidades.getHabilidades();
        System.out.println(habilidades);
        Efecto envenenado = new CambiarEstado(EstadoEnum.Envenenado);
        Efecto paralizado = new CambiarEstado(EstadoEnum.Paralizado);
        Habilidad llamaradaCreadaAMano= new Habilidad("Llamarada",0, Tipo.Fuego,900.0,envenenado,0.3,4,"Tira fuego","Soleado",EnumObjetivo.Otro);
        Habilidad rayoTruenoCreadaAMano= new Habilidad("Rayo Trueno",1, Tipo.Electrico,1100.0,paralizado,0.2,2,"Lanza un rayo truenoso que puede paralizar al oponente.","TormentaDeRayos",EnumObjetivo.Otro);

        //Assert
        assertTrue(habilidades.containsKey(0));
        assertTrue(habilidades.containsKey(1));

        //Act
        Habilidad llamaradaDelArbol = habilidades.get(0);
        Habilidad rayoTruenoDelArbol = habilidades.get(1);

        //Assert
        assertEquals(llamaradaDelArbol.getId(),llamaradaCreadaAMano.getId());
        assertEquals(llamaradaDelArbol.getTipo(),llamaradaCreadaAMano.getTipo());
        assertEquals(llamaradaDelArbol.getPoder(),llamaradaCreadaAMano.getPoder());
        assertEquals(llamaradaDelArbol.getEstado(),llamaradaCreadaAMano.getEstado());
        assertEquals(llamaradaDelArbol.getProbaInducirEstado(),llamaradaCreadaAMano.getProbaInducirEstado());
        assertEquals(llamaradaDelArbol.getInfo(),llamaradaCreadaAMano.getInfo());
        assertEquals(llamaradaDelArbol.getClimaQueGenera().getNombreVisible(),llamaradaCreadaAMano.getClimaQueGenera().getNombreVisible());

        assertEquals(rayoTruenoDelArbol.getId(),rayoTruenoCreadaAMano.getId());
        assertEquals(rayoTruenoDelArbol.getTipo(),rayoTruenoCreadaAMano.getTipo());
        assertEquals(rayoTruenoDelArbol.getPoder(),rayoTruenoCreadaAMano.getPoder());
        assertEquals(rayoTruenoDelArbol.getEstado(),rayoTruenoCreadaAMano.getEstado());
        assertEquals(rayoTruenoDelArbol.getProbaInducirEstado(),rayoTruenoCreadaAMano.getProbaInducirEstado());
        assertEquals(rayoTruenoDelArbol.getInfo(),rayoTruenoCreadaAMano.getInfo());
        assertEquals(rayoTruenoDelArbol.getClimaQueGenera().getNombreVisible(),rayoTruenoCreadaAMano.getClimaQueGenera().getNombreVisible());




    }
}


