package JuegoPokemon.unit.testJuego;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.*;
import JuegoPokemon.modelo.game.clima.Clima;
import JuegoPokemon.modelo.game.clima.SinClima;
import JuegoPokemon.modelo.game.efectos.CambiarEstado;
import JuegoPokemon.modelo.game.efectos.Efecto;
import JuegoPokemon.modelo.game.estado.EstadoEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCalculadora {
    @DisplayName("Se mockean los pokemones, habilidad y clima sin daño, para comprar que el daño este entre los valores de la cuenta original")
    @Test
    void testDañoEfectividadComunSinCLima(){

        //Arrange
        List<Tipo> listaTipos = new ArrayList<>();
        listaTipos.add(Tipo.Fuego);

        List<Tipo> listaTipos2 = new ArrayList<>();
        listaTipos2.add(Tipo.Veneno);


        Pokemon pokemonAtacanteMock =  mock(Pokemon.class);
        when(pokemonAtacanteMock.getNivel()).thenReturn(10);
        when(pokemonAtacanteMock.getAtaque()).thenReturn(10.0);
        when(pokemonAtacanteMock.getTipos()).thenReturn(listaTipos);

        Pokemon pokemonAtacadoMock =  mock(Pokemon.class);
        when(pokemonAtacadoMock.getDefensa()).thenReturn(1.0);
        when(pokemonAtacadoMock.getTipos()).thenReturn(listaTipos2);

        Habilidad habilidadMock =  mock(Habilidad.class);
        when(habilidadMock.getPoder()).thenReturn(100.00);
        when(habilidadMock.getTipo()).thenReturn(Tipo.Electrico);

        Clima climaMock =  mock(Clima.class);
        when(climaMock.porcentajeDanoARealizar(pokemonAtacanteMock)).thenReturn(Constantes.PORCENTAJE_AUMENTO_DANO_POR_CLIMA_NO_AFECTADO);


        //Act
        //Assert
        //cuenta original  (((2 * nivelAtacante * critico * poderHabilidad * (ataqueAtacante / defensaAtacado) / 5) + 2) / 50) * mismoTipo * efectividad * numRamdom * porcentajedañoclima
        //minimo posible con estos datos mockeados (((2*10*1*100* (10/1)/5)+2)/50)*1*1*0.85098039215*1.0= 68.1124705877
        //maximo posible con estos datos mockeados (((2*10*2*100* (10/1)/5)+2)/50)*1*1*1*1= 160.04

        Double daño = Calculadora.calcularDamage(pokemonAtacanteMock,pokemonAtacadoMock,habilidadMock,climaMock);
        assertTrue(daño<=160.04 && daño>=68.1124705877);
    }
    @DisplayName("Se mockean la, habilidad y clima con daño, para comprar que el daño este entre los valores de la cuenta original")
    @Test
    void testDañoEfectividadComunConCLima(){

        //Arrange
        int cantidadPruebas=10;
        List<Tipo> listaTipos = new ArrayList<>();
        listaTipos.add(Tipo.Fuego);

        List<Tipo> listaTipos2 = new ArrayList<>();
        listaTipos2.add(Tipo.Veneno);

        Pokemon pokemonAtacante = new Pokemon("Pikachu",1,listaTipos,"solo existe para test",100.0,10.0,10.0,10.0,null);
        Pokemon pokemonAtacado = new Pokemon("Jose",1,listaTipos2,"solo existe para test",100.0,10.0,1.0,10.0,null);
        Clima clima = new SinClima();

        Efecto envenenado = new CambiarEstado(EstadoEnum.Envenenado);
        Habilidad habilidad =  new Habilidad("Llamarada",0, Tipo.Fuego,100.0,envenenado,0.3,4,"Tira fuego","Soleado",EnumObjetivo.Otro);


        //Act
        //Assert
        //cuenta original  (((2 * nivelAtacante * critico * poderHabilidad * (ataqueAtacante / defensaAtacado) / 5) + 2) / 50) * mismoTipo * efectividad * numRamdom * porcentajedañoclima
        //minimo posible con estos datos mockeados (((2*1*1*100* (10/1)/5)+2)/50)*1*1*0.85098039215*1.10= 7.52607058817
        //maximo posible con estos datos mockeados (((2*100*2*100* (10/1)/5)+2)/50)*1*1*1*1.1= 1760.044

        Double danio = Calculadora.calcularDamage(pokemonAtacante,pokemonAtacado,habilidad,clima);
        assertTrue(danio<=1760.044   && danio>=7.52607058817);

    }
    @DisplayName("Se mockean los pokemones, habilidad y clima sin daño, para comprar que el daño este entre los valores de la cuenta original")
    @Test
    void testDañoEfectividadAlta(){

        //Arrange
        int cantidadPruebas=10000;
        List<Tipo> listaTipos = new ArrayList<>();
        listaTipos.add(Tipo.Fuego);

        List<Tipo> listaTipos2 = new ArrayList<>();
        listaTipos2.add(Tipo.Planta);


        Pokemon pokemonAtacanteMock =  mock(Pokemon.class);
        when(pokemonAtacanteMock.getNivel()).thenReturn(10);
        when(pokemonAtacanteMock.getAtaque()).thenReturn(10.0);
        when(pokemonAtacanteMock.getTipos()).thenReturn(listaTipos);

        Pokemon pokemonAtacadoMock =  mock(Pokemon.class);
        when(pokemonAtacadoMock.getDefensa()).thenReturn(1.0);
        when(pokemonAtacadoMock.getTipos()).thenReturn(listaTipos2);

        Habilidad habilidadMock =  mock(Habilidad.class);
        when(habilidadMock.getPoder()).thenReturn(100.00);
        when(habilidadMock.getTipo()).thenReturn(Tipo.Fuego);

        Clima climaMock =  mock(Clima.class);
        when(climaMock.porcentajeDanoARealizar(pokemonAtacanteMock)).thenReturn(Constantes.PORCENTAJE_AUMENTO_DANO_POR_CLIMA_NO_AFECTADO);


        //Act
        //Assert
        //cuenta original  (((2 * nivelAtacante * critico * poderHabilidad * (ataqueAtacante / defensaAtacado) / 5) + 2) / 50) * mismoTipo * efectividad * numRamdom * porcentajedañoclima
        //minimo posible con estos datos mockeados (((2*10*1*100* (10/1)/5)+2)/50)*1.5*2*0.85098039215*1.0= 204.337411763
        //maximo posible con estos datos mockeados (((2*10*2*100* (10/1)/5)+2)/50)*1.5*2*1*1.0= 480.12

        for (int i = 0; i < cantidadPruebas; i++) {
            Double daño = Calculadora.calcularDamage(pokemonAtacanteMock,pokemonAtacadoMock,habilidadMock,climaMock);
            assertTrue(daño<=480.12  && daño>=204.337411763);
        }
    }

    @DisplayName("Se mockean los pokemones, habilidad y clima sin daño, para comprar que el daño sea el correcto")
    @Test
    void testDañoEfectividadNula(){

        //Arrange
        int cantidadPruebas=10000;
        List<Tipo> listaTipos = new ArrayList<>();
        listaTipos.add(Tipo.Lucha);

        List<Tipo> listaTipos2 = new ArrayList<>();
        listaTipos2.add(Tipo.Fantasma);


        Pokemon pokemonAtacanteMock =  mock(Pokemon.class);
        when(pokemonAtacanteMock.getNivel()).thenReturn(10);
        when(pokemonAtacanteMock.getAtaque()).thenReturn(10.0);
        when(pokemonAtacanteMock.getTipos()).thenReturn(listaTipos);

        Pokemon pokemonAtacadoMock =  mock(Pokemon.class);
        when(pokemonAtacadoMock.getDefensa()).thenReturn(1.0);
        when(pokemonAtacadoMock.getTipos()).thenReturn(listaTipos2);

        Habilidad habilidadMock =  mock(Habilidad.class);
        when(habilidadMock.getPoder()).thenReturn(10.00);
        when(habilidadMock.getTipo()).thenReturn(Tipo.Lucha);

        Clima climaMock =  mock(Clima.class);
        when(climaMock.porcentajeDanoARealizar(pokemonAtacanteMock)).thenReturn(Constantes.PORCENTAJE_AUMENTO_DANO_POR_CLIMA_NO_AFECTADO);


        //Act
        //Assert
        for (int i = 0; i < cantidadPruebas; i++) {
            Double daño = Calculadora.calcularDamage(pokemonAtacanteMock,pokemonAtacadoMock,habilidadMock,climaMock);
            assertEquals(0, (double) daño);
        }
    }
    @DisplayName("Se mockean los pokemones, habilidad con poder 0 y clima sin daño, para comprar que el daño sea 0 siempre")
    @Test
    void testHabilidadSinPoder(){

        //Arrange
        int cantidadPruebas=10000;
        List<Tipo> listaTipos = new ArrayList<>();
        listaTipos.add(Tipo.Roca);

        List<Tipo> listaTipos2 = new ArrayList<>();
        listaTipos2.add(Tipo.Roca);


        Pokemon pokemonAtacanteMock =  mock(Pokemon.class);
        when(pokemonAtacanteMock.getNivel()).thenReturn(10);
        when(pokemonAtacanteMock.getAtaque()).thenReturn(10.0);
        when(pokemonAtacanteMock.getTipos()).thenReturn(listaTipos);

        Pokemon pokemonAtacadoMock =  mock(Pokemon.class);
        when(pokemonAtacadoMock.getDefensa()).thenReturn(1.0);
        when(pokemonAtacadoMock.getTipos()).thenReturn(listaTipos2);

        Habilidad habilidadMock =  mock(Habilidad.class);
        when(habilidadMock.getPoder()).thenReturn(0.0);
        when(habilidadMock.getTipo()).thenReturn(Tipo.Veneno);

        Clima climaMock =  mock(Clima.class);
        when(climaMock.porcentajeDanoARealizar(pokemonAtacanteMock)).thenReturn(Constantes.PORCENTAJE_AUMENTO_DANO_POR_CLIMA_NO_AFECTADO);


        //Act
        //Assert
        for (int i = 0; i < cantidadPruebas; i++) {
            Double daño = Calculadora.calcularDamage(pokemonAtacanteMock,pokemonAtacadoMock,habilidadMock,climaMock);
            assertEquals(0.0, daño);
        }
    }
}

