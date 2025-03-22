package JuegoPokemon.modelo;

import java.util.ArrayList;
import java.util.List;

public class Constantes {

    public static final Integer CANTIDAD_DE_POKEMONS_POR_JUGADOR = 6;


    public static final ArrayList<String> OPCIONES_DE_BATALLA;

    static {
        OPCIONES_DE_BATALLA = new ArrayList<>(List.of("Lanzar una Habilidad", "Cambiar de Pokemon", "Utilizar un Item", "Rendirse"));
    }

    public static final String DIRECCION_EFECTIVIDAD = "src/main/resources/csv/Efectividad.csv";

    public static final String DIRECCION_POKEMONS = "src/main/resources/JSONs/Pokemones.json";

    public static final String DIRECCION_HABILIDADES = "src/main/resources/JSONs/Habilidades.json";

    public static final String DIRECCION_ITEMS = "src/main/resources/JSONs/Items.json";

    public static final String DIRECCION_JUGADORES = "src/main/resources/JSONs/Jugadores.json";

    public static final String DIRECCION_INFORME = "src/main/resources/JSONs/Informe.json";


    public static final String PATH_CLASE_CLIMA = "JuegoPokemon.juego.clima.";

    public static final Double REVIVIR = 0.5;
    public static final Double PROBABILIADADPARALIZADO = 0.5;
    public static final Double PROBABILIADADBASEDESPERTAR = 0.25;
    public static final Double PROBABILIADADGOLPEARSE= 0.33;
    public static final Double DANIOOENVENAMIENTO = 0.05;
    public static final Double DANIOPORCONFUSION = 0.15;
    public static final Double PROBABILIDADCRITICO = 0.9;

    public static final int TAMAÑOLETRA= 15;
    public static final Double PODERPOCION = 20.0;
    public static final Double PODERHYPERPOCION =100.0;
    public static final Double ESTADISTICAX = 1.1;


    // Es el porcentaje de la vida maxima del pokemon que se tiene que reducir al estar desfavorecido por el clima.
    public static final Double PORCENTAJE_REDUCCION_VIDA_POR_CLIMA = 0.03;

    // Es el porcentaje al que hay que multiplicar el daño final para modificarlo si el clima favorece al atacante.
    public static final Double PORCENTAJE_AUMENTO_DANO_POR_CLIMA_AFECTADO = 1.10;

    // Es el porcentaje al que hay que multiplicar el daño final para modificarlo si el clima no favorece al atacante.(no es muy necesario pero qcy)
    public static final Double PORCENTAJE_AUMENTO_DANO_POR_CLIMA_NO_AFECTADO = 1.00;

    public static final Integer CANTIDAD_TURNOS_QUE_DURA_UN_CLIMA = 5;

    // Es el porcentaje de probabs de que al crear un clima random, este sea SinClima
    public static final Double PORCENTAJE_PROBABS_GENRERAR_SIN_CLIMA = 0.66;

}