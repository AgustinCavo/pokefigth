package JuegoPokemon.modelo.game.clima;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Calculadora;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.Tipo;
import JuegoPokemon.modelo.game.estado.*;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


public abstract class Clima {

    protected String nombreVisible;

    private int turnosRestantes;

    public Clima(){
        this.turnosRestantes = Constantes.CANTIDAD_TURNOS_QUE_DURA_UN_CLIMA;
    }

    // Devuelve un hash con los tipos que se benefician en el clima.
    public HashMap<Tipo, Tipo> tiposBeneficiados(){return null;}

    // Devuelve el número al que hay que multiplicar el daño final a realizar para modificarlo según el clima actual.
    // Ej: Si el daño a realizar es de 50: Si hay que agregarle 50% mas de daño devuelve 1.5. Si no hay que modificarlo devuelve 1.
    public Double   porcentajeDanoARealizar(Pokemon pokemonAtacante){
        if (hayTipoEnComun(pokemonAtacante.getTipos(), this.tiposBeneficiados()))
            return Constantes.PORCENTAJE_AUMENTO_DANO_POR_CLIMA_AFECTADO;
        return Constantes.PORCENTAJE_AUMENTO_DANO_POR_CLIMA_NO_AFECTADO;
    }

    public Double danoPorTurno(Pokemon pokemon){
        return 0.0;
    }


    // Devuelve true si hay mínimo un tipo contenido en ambos conjuntos.
    protected boolean hayTipoEnComun(List<Tipo> listaTipos, HashMap<Tipo, Tipo> hashTipos){
        for (Tipo tipo : listaTipos){
            if (hashTipos.containsKey(tipo))
                return true;
        }
        return false;
    }

    //Reduce un turno de la cantidad restantes para que desaparezca el clima
    public void reducirTurnos(){
        this.turnosRestantes --;
    }

    public void aumentarTurnos() {
        this.turnosRestantes++;
    }

    // Crea y devuelve un clima aleatorio. Tiene dos tercios de probabs de que sea SinClima y el restante de cualquiera de los otros.
    public static Clima climaRandom() {
        Random random = new Random();
        double doubleRandom = random.nextDouble();
        if (doubleRandom <= Constantes.PORCENTAJE_PROBABS_GENRERAR_SIN_CLIMA)
            return new SinClima();

        List<Clima> otrosClimas = new ArrayList<>();
        otrosClimas.add(new Soleado());
        otrosClimas.add(new Lluvia());
        otrosClimas.add(new Huracan());
        otrosClimas.add(new Niebla());
        otrosClimas.add(new TormentaDeArena());
        otrosClimas.add(new TormentaDeRayos());

        Random random2 = new Random();
        int indice = random2.nextInt(6);
        return otrosClimas.get(indice);
    }

    // stringClima es el nombre de la clase del Clima
    // Devuelve una instancia de Clima del tipo stringClima
    public static Clima stringAClimaMEJORADOPERONOUSADOPORLOSBOBIS(String stringClima){
        try {
            String nombreClaseCompleto = Constantes.PATH_CLASE_CLIMA + stringClima;
            Class<?> claseClima = Class.forName(nombreClaseCompleto);
            return (Clima) claseClima.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Clima stringAClima(String stringClima){
        Map<String, Clima> estadosPosibles= Map.ofEntries(
            Map.entry("SinClima",new SinClima()),
            Map.entry("Soleado",new Soleado()),
            Map.entry("Lluvia",new Lluvia()),
            Map.entry("Huracan",new Huracan()),
            Map.entry("Niebla",new Niebla()),
            Map.entry("TormentaDeArena",new TormentaDeArena()),
            Map.entry("TormentaDeRayos",new TormentaDeRayos())
        );
        return estadosPosibles.get(stringClima);
    }
    public static String climaAString(Clima clima){
        String string = clima.getClass().getName();
        return string.substring(string.lastIndexOf('.') + 1);
    }


    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    public String getNombreVisible(){
        return this.nombreVisible;
    }

    public abstract ClimaEnum getTipo();
}
