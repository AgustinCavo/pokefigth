package JuegoPokemon.modelo.game;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.clima.Clima;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Calculadora {

    private final static HashMap<ArrayList<Tipo>, Double> efectividadPorTipo = cargarEfectividad();


    //Pre: ninguna
    //Post: devuelve el la vida que hay que bajar al pokemon atacado
    public static Double calcularDamage(Pokemon pokemonAtacante, Pokemon pokemonAtacado, Habilidad habilidadAtacante, Clima clima){

        Integer nivelAtacante = pokemonAtacante.getNivel();
        Integer critico = 1;
        Double poderHabilidad = habilidadAtacante.getPoder();
        Double ataqueAtacante = pokemonAtacante.getAtaque();
        Double defensaAtacado = pokemonAtacado.getDefensa();
        double mismoTipo = 1.0;

        if (Calculadora.esCritico()){
            critico=2;
        }
        if (pokemonAtacante.getTipos().contains(habilidadAtacante.getTipo()))
            mismoTipo = 1.5;
        double efectividad = 0.0;

        for (int i = 0; i < pokemonAtacado.getTipos().size(); i++)
            efectividad = efectividad + Calculadora.calcularEfectividad(habilidadAtacante.getTipo(), pokemonAtacado.getTipos().get(i));

        double numRamdom = Calculadora.generarRandom(217.00,255.00)/255.00;

        double cuentaFinal= (((2 * nivelAtacante * critico * poderHabilidad * (ataqueAtacante / defensaAtacado) / 5) + 2) / 50) * mismoTipo * efectividad * numRamdom;
        if (poderHabilidad==0.0){
            cuentaFinal=0.0;
        }
        return cuentaFinal * clima.porcentajeDanoARealizar(pokemonAtacante);
    }

    private static boolean esCritico(){
        Double intento =Calculadora.generarRandomProbabilidad();

        return intento> Constantes.PROBABILIDADCRITICO;
    }

    //Pre: ninguna
    //Post: devuelve la efectividad al infligir daño con un tipo de habilidad a un tipo específico de un pokemon
    private static Double calcularEfectividad(Tipo tipoHabilidadAtacante, Tipo tipoPokemonAtacado){
        ArrayList<Tipo> tuplaTipos = new ArrayList<Tipo>();
        tuplaTipos.add(tipoHabilidadAtacante);
        tuplaTipos.add(tipoPokemonAtacado);
        return Calculadora.efectividadPorTipo.get(tuplaTipos);
    }

    //pre: desde tiene que ser menor a hasta
    //post: devuelve un numero random en ese intervalo
    public static Double generarRandom(Double desde, Double hasta){
        Random random = new Random();
        double rango = hasta-desde;
        return  random.nextDouble()*rango+desde;
    }
    //pre: -
    //post: devuelve un numero entre 0 y 1
    public static Double generarRandomProbabilidad(){
        Random random = new Random();
        return random.nextDouble();
    }

    public static HashMap<ArrayList<Tipo>, Double> cargarEfectividad() {
        HashMap<ArrayList<Tipo>, Double> efectividad = new HashMap<>();

        String csvFile = Constantes.DIRECCION_EFECTIVIDAD, linea;

        try (BufferedReader bufer = new BufferedReader(new FileReader(csvFile))) {
            while ((linea = bufer.readLine()) != null) {
                String[] valores = linea.split(",");
                efectividad.put(new ArrayList<>(Arrays.asList(Tipo.valueOf(valores[0]), Tipo.valueOf(valores[1]))), Double.parseDouble(valores[2]));
            }
        } catch (IOException exception) {
            return null;
        }
        return efectividad;
    }

}
