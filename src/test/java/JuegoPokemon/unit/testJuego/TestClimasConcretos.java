package JuegoPokemon.unit.testJuego;

import JuegoPokemon.modelo.game.Pokemon;

import JuegoPokemon.modelo.game.Tipo;
import JuegoPokemon.modelo.game.clima.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestClimasConcretos {


    @DisplayName("Crea el clima soleado y comprueba que tenga el nombre correcto")
    @Test
    void nombreVisibleClimaSoleado() {
        //Arrange
        Clima climaTest= new Soleado();
        //Act
        String nombreRetornado = climaTest.getNombreVisible();

        //Assert
        assertEquals("Soleado",nombreRetornado);
    }

    @DisplayName("Crea el clima soleado y comprueba que los tipos beneficiados sean los correctos")
    @Test
    void beneficiaTiposCorrectamenteSoleado() {
        //Arrange
        Clima climaTest= new Soleado();
        //Act
        HashMap<Tipo, Tipo> tiposBeneficiados= climaTest.tiposBeneficiados();

        //Assert
        assertTrue(tiposBeneficiados.containsKey(Tipo.Fuego));
    }
    @DisplayName("Crea el clima normal (Sin clima) y comprueba que tenga el nombre correcto")
    @Test
    void nombreVisibleClimaSinClima() {
        //Arrange
        Clima climaTest= new SinClima();
        //Act
        String nombreRetornado = climaTest.getNombreVisible();

        //Assert
        assertEquals("Normal",nombreRetornado);
    }

    @DisplayName("Crea el clima normal (Sin clima) y comprueba que los tipos beneficiados sean los correctos")
    @Test
    void beneficiaTiposCorrectamenteSinClima() {
        //Arrange
        Clima climaTest= new SinClima();
        //Act
        HashMap<Tipo, Tipo> tiposBeneficiados= climaTest.tiposBeneficiados();

        //Assert
        assertEquals(0, tiposBeneficiados.size());
    }
    @DisplayName("Crea el clima niebla y comprueba que tenga el nombre correcto")
    @Test
    void nombreVisibleClimaNiebla() {
        //Arrange
        Clima climaTest= new Niebla();
        //Act
        String nombreRetornado = climaTest.getNombreVisible();

        //Assert
        assertEquals("Neblinoso",nombreRetornado);
    }

    @DisplayName("Crea el clima niebla y comprueba que los tipos beneficiados sean los correctos")
    @Test
    void beneficiaTiposCorrectamenteNiebla() {
        //Arrange
        Clima climaTest= new Niebla();
        //Act
        HashMap<Tipo, Tipo> tiposBeneficiados= climaTest.tiposBeneficiados();

        //Assert
        assertTrue(tiposBeneficiados.containsKey(Tipo.Fantasma));
        assertTrue(tiposBeneficiados.containsKey(Tipo.Psiquico));
    }
    @DisplayName("Crea el clima Lluvia y comprueba que tenga el nombre correcto")
    @Test
    void nombreVisibleClimaLluvia() {
        //Arrange
        Clima climaTest= new Lluvia();
        //Act
        String nombreRetornado = climaTest.getNombreVisible();

        //Assert
        assertEquals("Lluvioso",nombreRetornado);
    }

    @DisplayName("Crea el clima Lluvia y comprueba que los tipos beneficiados sean los correctos")
    @Test
    void beneficiaTiposCorrectamenteLluvia() {
        //Arrange
        Clima climaTest= new Lluvia();
        //Act
        HashMap<Tipo, Tipo> tiposBeneficiados= climaTest.tiposBeneficiados();

        //Assert
        assertTrue(tiposBeneficiados.containsKey(Tipo.Agua));
        assertTrue(tiposBeneficiados.containsKey(Tipo.Planta));
    }
    @DisplayName("Crea el clima Huracan y comprueba que tenga el nombre correcto")
    @Test
    void nombreVisibleClimaHuracan() {
        //Arrange
        Clima climaTest= new Huracan();
        //Act
        String nombreRetornado = climaTest.getNombreVisible();

        //Assert
        assertEquals("Huracán",nombreRetornado);
    }

    @DisplayName("Crea el clima Huracan y comprueba que los tipos beneficiados sean los correctos")
    @Test
    void beneficiaTiposCorrectamenteHuracan() {
        //Arrange
        Clima climaTest= new Huracan();
        //Act
        HashMap<Tipo, Tipo> tiposBeneficiados= climaTest.tiposBeneficiados();

        //Assert
        assertTrue(tiposBeneficiados.containsKey(Tipo.Volador));
    }

    @DisplayName("Crea el clima Huracan y comprueba que el daño por turno sea correcto")
    @Test
    void dañoTipoCorrectomenteHuracan() {
        //Arrange
        Clima climaTest= new Huracan();

        ArrayList<Tipo> tiposLista = new ArrayList<>();
        tiposLista.add(Tipo.Dragon);

        Pokemon pokemonMock= mock(Pokemon.class);
        when(pokemonMock.getTipos()).thenReturn(tiposLista);
        when(pokemonMock.getVidaMaxima()).thenReturn(100.00);

        //Act
        double dañoRecibidoPorTurno= climaTest.danoPorTurno(pokemonMock);

        //Assert
        assertEquals(3,dañoRecibidoPorTurno);
    }
    @DisplayName("Crea el clima Huracan y comprueba que el daño a beneficiado sea 0")
    @Test
    void noDañaTipoCorrectomenteHuracan() {
        //Arrange
        Clima climaTest= new Huracan();

        ArrayList<Tipo> tiposLista = new ArrayList<>();
        tiposLista.add(Tipo.Volador);

        Pokemon pokemonMock= mock(Pokemon.class);
        when(pokemonMock.getTipos()).thenReturn(tiposLista);
        when(pokemonMock.getVidaMaxima()).thenReturn(100.00);

        //Act
        double dañoRecibidoPorTurno= climaTest.danoPorTurno(pokemonMock);

        //Assert
        assertEquals(0,dañoRecibidoPorTurno);
    }
    @DisplayName("Crea el clima TormentaDeArena y comprueba que tenga el nombre correcto")
    @Test
    void nombreVisibleClimaTormentaDeArena() {
        //Arrange
        Clima climaTest= new TormentaDeArena();
        //Act
        String nombreRetornado = climaTest.getNombreVisible();

        //Assert
        assertEquals("Tormenta de Arena",nombreRetornado);
    }

    @DisplayName("Crea el clima TormentaDeArena y comprueba que los tipos beneficiados sean los correctos")
    @Test
    void beneficiaTiposCorrectamenteTormentaDeArena() {
        //Arrange
        Clima climaTest= new TormentaDeArena();
        //Act
        HashMap<Tipo, Tipo> tiposBeneficiados= climaTest.tiposBeneficiados();

        //Assert
        assertTrue(tiposBeneficiados.containsKey(Tipo.Tierra));
        assertTrue(tiposBeneficiados.containsKey(Tipo.Roca));
    }

    @DisplayName("Crea el clima TormentaDeArena y comprueba que el daño por turno sea correcto")
    @Test
    void dañoTipoCorrectomenteTormentaDeArena() {
        //Arrange
        Clima climaTest= new TormentaDeArena();

        ArrayList<Tipo> tiposLista = new ArrayList<>();
        tiposLista.add(Tipo.Dragon);

        Pokemon pokemonMock= mock(Pokemon.class);
        when(pokemonMock.getTipos()).thenReturn(tiposLista);
        when(pokemonMock.getVidaMaxima()).thenReturn(100.00);

        //Act
        double dañoRecibidoPorTurno= climaTest.danoPorTurno(pokemonMock);

        //Assert
        assertEquals(3,dañoRecibidoPorTurno);
    }
    @DisplayName("Crea el clima TormentaDeArena y comprueba que el daño a beneficiado sea 0")
    @Test
    void noDañaTipoCorrectomenteTormentaDeArena() {
        //Arrange
        Clima climaTest= new TormentaDeArena();

        ArrayList<Tipo> tiposLista = new ArrayList<>();
        tiposLista.add(Tipo.Tierra);

        Pokemon pokemonMock= mock(Pokemon.class);
        when(pokemonMock.getTipos()).thenReturn(tiposLista);
        when(pokemonMock.getVidaMaxima()).thenReturn(100.00);

        ArrayList<Tipo> tiposLista2 = new ArrayList<>();
        tiposLista2.add(Tipo.Roca);

        Pokemon pokemonMock2= mock(Pokemon.class);
        when(pokemonMock2.getTipos()).thenReturn(tiposLista2);
        when(pokemonMock2.getVidaMaxima()).thenReturn(100.00);

        //Act
        double dañoRecibidoPorTurno= climaTest.danoPorTurno(pokemonMock);
        double dañoRecibidoPorTurno2= climaTest.danoPorTurno(pokemonMock2);

        //Assert
        assertEquals(0,dañoRecibidoPorTurno);
        assertEquals(0,dañoRecibidoPorTurno2);
    }
    @DisplayName("Crea el clima TormentaDeRayos y comprueba que tenga el nombre correcto")
    @Test
    void nombreVisibleClimaTormentaDeRayos() {
        //Arrange
        Clima climaTest= new TormentaDeRayos();
        //Act
        String nombreRetornado = climaTest.getNombreVisible();

        //Assert
        assertEquals("Tormenta Eléctrica",nombreRetornado);
    }

    @DisplayName("Crea el clima TormentaDeRayos y comprueba que los tipos beneficiados sean los correctos")
    @Test
    void beneficiaTiposCorrectamenteTormentaDeRayos() {
        //Arrange
        Clima climaTest= new TormentaDeRayos();
        //Act
        HashMap<Tipo, Tipo> tiposBeneficiados= climaTest.tiposBeneficiados();

        //Assert
        assertTrue(tiposBeneficiados.containsKey(Tipo.Electrico));

    }

    @DisplayName("Crea el clima TormentaDeRayos y comprueba que el daño por turno sea correcto")
    @Test
    void dañoTipoCorrectomenteTormentaDeRayos() {
        //Arrange
        Clima climaTest= new TormentaDeRayos();

        ArrayList<Tipo> tiposLista = new ArrayList<>();
        tiposLista.add(Tipo.Dragon);

        Pokemon pokemonMock= mock(Pokemon.class);
        when(pokemonMock.getTipos()).thenReturn(tiposLista);
        when(pokemonMock.getVidaMaxima()).thenReturn(100.00);

        //Act
        double dañoRecibidoPorTurno= climaTest.danoPorTurno(pokemonMock);

        //Assert
        assertEquals(3,dañoRecibidoPorTurno);
    }
    @DisplayName("Crea el clima TormentaDeRayos y comprueba que el daño a beneficiado sea 0")
    @Test
    void noDañaTipoCorrectomenteTormentaDeRayos() {
        //Arrange
        Clima climaTest= new TormentaDeRayos();

        ArrayList<Tipo> tiposLista = new ArrayList<>();
        tiposLista.add(Tipo.Electrico);

        Pokemon pokemonMock= mock(Pokemon.class);
        when(pokemonMock.getTipos()).thenReturn(tiposLista);
        when(pokemonMock.getVidaMaxima()).thenReturn(100.00);


        //Act
        double dañoRecibidoPorTurno= climaTest.danoPorTurno(pokemonMock);


        //Assert
        assertEquals(0,dañoRecibidoPorTurno);

    }
}
