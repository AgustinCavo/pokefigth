package JuegoPokemon.unit.testJuego;

import JuegoPokemon.modelo.game.*;
import JuegoPokemon.modelo.game.clima.Clima;
import JuegoPokemon.modelo.game.clima.SinClima;
import JuegoPokemon.modelo.game.efectos.EstadisticasEnum;
import JuegoPokemon.modelo.game.efectos.ModificarEstadistica;
import JuegoPokemon.modelo.game.estado.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


class TestPokemon {




    @DisplayName("Crea el pokemon y verifico su estado actual sea normal ")
    @Test
    void elPokemonSeCreaConEstadoNormal() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        HashMap<EstadoEnum,Estado> estadosTest= pokemon.getEstados();

        //Assert
        assertEquals(1,estadosTest.size());
        assertTrue(estadosTest.containsKey(EstadoEnum.Normal));
    }
    @DisplayName("Crea el pokemon y comprueba que debilitar lo cambie del estado Normal a Debilitado")
    @Test
    void testDebilitar() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu", 1, tipos, "solo existe para test", 100.0, 10.0, 10.0, 10.0, null);

        //Assert
        assertTrue(pokemon.getEstados().containsKey(EstadoEnum.Normal));

        //Act
        pokemon.debilitar();

        //Assert
        assertTrue(pokemon.getEstados().containsKey(EstadoEnum.Debilitado));
        assertEquals(1, pokemon.getEstados().size());
    }
    @DisplayName("Crea el pokemon y se llama al metodo reducionVida")
    @Test
    void testVerificarReduccionVida() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        pokemon.reducirVida(50.0);
        //Assert
        assertEquals(50.0,pokemon.getVida());
        assertEquals(100.0,pokemon.getVidaMaxima());
    }
    @DisplayName("Crea el pokemon y se llama al metodo reducionVida pero con valor negativo para curarlo")
    @Test
    void testVerificarReduccionInversaDeVida() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        pokemon.reducirVida(50.0);

        //Assert
        assertEquals(50.0,pokemon.getVida());

        //Act
        pokemon.reducirVida(-20.0);

        //Assert
        assertEquals(70.0,pokemon.getVida());
    }

        @DisplayName("Crea el pokemon y se llama al metodo reducionVida para chequear si lo debilita")
        @Test
        void testVerificarReduccionDeVidaDebilita() {
            //Arrange
            Tipo tipoFuegoMock = mock(Tipo.class);
            List<Tipo> tipos = new ArrayList<>();
            tipos.add(tipoFuegoMock);

            Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

            //Act
            pokemon.reducirVida(100.0);
            HashMap<EstadoEnum,Estado> estadosTest = pokemon.getEstados();

            //Assert
            assertEquals(0.00,pokemon.getVida());
            assertFalse(estadosTest.containsKey(EstadoEnum.Normal));
            assertTrue(estadosTest.containsKey(EstadoEnum.Debilitado));
        }

    @DisplayName("Crea el pokemon y se verifica atravez del metodo que posea los estados que se le van seteando")
    @Test
    void testTieneEstados() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        HashMap<EstadoEnum,Estado> estadosTest= pokemon.getEstados();

        //Assert
        assertTrue(estadosTest.containsKey(EstadoEnum.Normal));
        assertTrue(pokemon.tieneEsteEstado(EstadoEnum.Normal));

        //Act
        HashMap<EstadoEnum,Estado> nuevosEstados= new HashMap<>();
        nuevosEstados.put(EstadoEnum.Envenenado,new Envenenado());
        pokemon.setEstados(nuevosEstados);

        //Assert
        assertTrue(nuevosEstados.containsKey(EstadoEnum.Envenenado));
        assertTrue(pokemon.tieneEsteEstado(EstadoEnum.Envenenado));
    }


    @DisplayName("Crea el pokemon y se verifica que se cambie el estado a Normal al llamar al metodo resetearEstadoANormal")
    @Test
    void testResetearEstadoANormal() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        pokemon.cambiarEstado(EstadoEnum.Confuso);
        pokemon.resetearEstadoANormal();
        HashMap<EstadoEnum,Estado> estadosTest = pokemon.getEstados();

        //Assert
        assertTrue(estadosTest.containsKey(EstadoEnum.Normal));
        assertFalse(estadosTest.containsKey(EstadoEnum.Confuso));
        assertEquals(1,estadosTest.size());
    }


    @DisplayName("Crea el pokemon y se verifica que se cambie el estado al llamar al metodo cambiarEstado")
    @Test
    void testCambiarEstado() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        pokemon.cambiarEstado(EstadoEnum.Confuso);
        HashMap<EstadoEnum,Estado> estadosTest = pokemon.getEstados();

        //Assert
        assertFalse(estadosTest.containsKey(EstadoEnum.Normal));
        assertTrue(estadosTest.containsKey(EstadoEnum.Confuso));
        assertEquals(1,estadosTest.size());
    }
    @DisplayName("Crea el pokemon y se cambia su estado a dormido y se verifica que se despierte correctamente")
    @Test
    void testDespertarDormido() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        pokemon.cambiarEstado(EstadoEnum.Dormido);
        //Assert
        assertTrue(pokemon.tieneEsteEstado(EstadoEnum.Dormido));

        //Act
        pokemon.despertar();
        //Assert
        assertTrue(pokemon.tieneEsteEstado(EstadoEnum.Normal));
        assertFalse(pokemon.tieneEsteEstado(EstadoEnum.Dormido));
        assertEquals(1,pokemon.getEstados().size());

    }

    @DisplayName("Crea el pokemon y se verifica que el metodo Despertar no haga ningun cambio")
    @Test
    void testDespertarNoDormido() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        pokemon.despertar();

        //Assert
        assertTrue(pokemon.tieneEsteEstado(EstadoEnum.Normal));
        assertEquals(1, pokemon.getEstados().size());

    }
    @DisplayName("Crea el pokemon y se cambia su estado a confuso y se verifica que se remueve el confuso correctamente")
    @Test
    void testDesconfundirConfuso() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        pokemon.cambiarEstado(EstadoEnum.Confuso);
        //Assert
        assertTrue(pokemon.tieneEsteEstado(EstadoEnum.Confuso));

        //Act
        pokemon.desconfundir();
        //Assert
        assertTrue(pokemon.tieneEsteEstado(EstadoEnum.Normal));
        assertFalse(pokemon.tieneEsteEstado(EstadoEnum.Confuso));
        assertEquals(1,pokemon.getEstados().size());

    }

    @DisplayName("Crea el pokemon y se verifica que el metodo desconfundir no haga ningun cambio")
    @Test
    void testDesconfundirNoConfuso() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        pokemon.despertar();

        //Assert
        assertTrue(pokemon.tieneEsteEstado(EstadoEnum.Normal));
        assertEquals(1,pokemon.getEstados().size());

    }
    @DisplayName("Crea el pokemon y se verifica que pueda atacar en estado estandar")
    @Test
    void testPuedeAtacarNormal() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        EstadoEnum res = pokemon.puedeAtacar();
        //Assert
        assertNull(res);

    }
    @DisplayName("Crea el pokemon y se verifica que puede atacar en estados adversos sin probabilidades")
    @Test
    void testPuedeAtacarEstadosPosibles() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        pokemon.cambiarEstado(EstadoEnum.Envenenado);
        EstadoEnum res = pokemon.puedeAtacar();

        //Assert
        assertNull(res);

    }
    @DisplayName("Crea el pokemon y se verifica que el metodo atacar disminuya vida y no cambie el estado en caso de que la habilidad no tenga efecto")
    @Test
    void testAtacar() {
        //Arrange
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);
        Habilidad habilidadMock = mock(Habilidad.class);
        Clima climaMock = mock(Clima.class);

        Pokemon pokemonAtacante = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);
        Pokemon pokemonAtacado = new Pokemon("Jose",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        when(habilidadMock.sePuedeUsar()).thenReturn(true);
        when(habilidadMock.sePuedeAplicarEfecto()).thenReturn(false);

        try (MockedStatic mockStatic = mockStatic(Calculadora.class)) {

            mockStatic.when(()-> Calculadora.calcularDamage(pokemonAtacante,pokemonAtacado,habilidadMock,climaMock)).thenReturn(40.0);

            //Act
            pokemonAtacante.atacar(pokemonAtacado, habilidadMock, climaMock);

            //Assert
            assertEquals(60.00,pokemonAtacado.getVida());
            assertTrue(pokemonAtacado.tieneEsteEstado(EstadoEnum.Normal));
            assertEquals(1,pokemonAtacado.getEstados().size());

        }

    }

    @DisplayName("Crea el pokemon y se verifica que el metodo que retorna los estados funcione correctamente")
    @Test
    void testStringDeEstados() {
        //Arrange


        AtomicInteger cont= new AtomicInteger();
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Habilidad habilidadMock = mock(Habilidad.class);
        Clima climaMock = mock(Clima.class);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        pokemon.cambiarEstado(EstadoEnum.Envenenado);
        pokemon.cambiarEstado(EstadoEnum.Paralizado);


        HashMap<EstadoEnum,Estado> estadosPokemon=pokemon.getEstados();

        HashMap<EstadoEnum,Estado> estadosDeberiaTener= new HashMap<>();

        estadosDeberiaTener.put(EstadoEnum.Envenenado,new Envenenado());
        estadosDeberiaTener.put(EstadoEnum.Paralizado,new Paralizado());

        estadosDeberiaTener.forEach((clave,valor)->{
            if (estadosPokemon.containsKey((clave))){
                cont.getAndIncrement();
            }
        });

        //Assert
        assertEquals(2, cont.get());

    }
    @DisplayName("Crea el pokemon y se verifica que el metodo que retorna los estados funcione correctamente")
    @Test
    void testModificarMoteYGetConvencionales() {
        //Arrange

        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Habilidad habilidadMock = mock(Habilidad.class);
        Clima climaMock = mock(Clima.class);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Assert

        assertEquals(pokemon.getNombre(),"Pikachu");
        assertEquals(pokemon.getVelocidadDeafault(),10.0);

    }
    @DisplayName("Crea el pokemon, le agrega estados y verifica que la lista devuelta sea correcta")
    @Test
    void testObtenerListaDeEstados() {
        //Arrange
        int cont =0;
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Pokemon pokemon = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        pokemon.cambiarEstado(EstadoEnum.Envenenado);
        pokemon.cambiarEstado(EstadoEnum.Paralizado);

        //Assert
        List<EstadoEnum> listaEstados = pokemon.getListaEstados();

        for (EstadoEnum estado : listaEstados) {
            if(estado.equals(EstadoEnum.Paralizado) || estado.equals(EstadoEnum.Envenenado)){
                cont++;
            }
        }
        assertEquals(2,cont);

    }
    @DisplayName("Crea el pokemon, le agrega una habilidad personal y verifica que la lista devuelta sea correcta")
    @Test
    void testCambiarObjetivo() {
        //Arrange
        int cont =0;
        Tipo tipoFuegoMock = mock(Tipo.class);
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipoFuegoMock);

        Clima climaMock = mock(Clima.class);

        List<Habilidad> habilidades= new ArrayList<>();
        habilidades.add(new Habilidad("Latigo",0,Tipo.Electrico,0.0,new ModificarEstadistica(EstadisticasEnum.Velocidad,1.1),1.0,4,"sarasa","SinClima",EnumObjetivo.Personal));
        Pokemon pokemonAtacante = new Pokemon("Pikachu",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,habilidades);
        Pokemon pokemonAtacado = new Pokemon("Polygon",1,tipos,"solo existe para test",100.0,10.0,10.0,10.0,null);

        //Act
        try (MockedStatic mockStatic = mockStatic(Calculadora.class)) {

            mockStatic.when(()-> Calculadora.calcularDamage(pokemonAtacante,pokemonAtacado,habilidades.get(0),climaMock)).thenReturn(0.0);

            //Act
            pokemonAtacante.atacar(pokemonAtacado,habilidades.get(0),climaMock);

            //Assert
            assertEquals(11.0,pokemonAtacante.getVelocidad());

        }

    }


}