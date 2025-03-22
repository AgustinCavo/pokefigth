package JuegoPokemon.modelo.game.inicializadores;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.EnumObjetivo;
import JuegoPokemon.modelo.game.Habilidad;
import JuegoPokemon.modelo.game.Tipo;
import JuegoPokemon.modelo.game.efectos.CambiarEstado;
import JuegoPokemon.modelo.game.efectos.CreadorEfecto;
import JuegoPokemon.modelo.game.efectos.Efecto;
import JuegoPokemon.modelo.game.estado.EstadoEnum;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class CreadorHabilidades {

    private static HashMap<Integer, Habilidad> hashHabilidades;
    private static String path;

    //Pre: nada
    //Post: // Carga el atributo con los items del json DIRECCION_HABILIDADES
    public static void setHashHabilidades(){
        HashMap<Integer, Habilidad> hashHabilidades = new HashMap<>();
        CreadorEfecto creadorEfecto = new CreadorEfecto();

        try {
            StringBuilder info = new StringBuilder();
            File archivo = new File(path);
            Scanner scanner = new Scanner(archivo);

            while (scanner.hasNextLine()) {
                info.append(scanner.nextLine());
            }
            JSONArray habilidades = new JSONArray(info.toString());
            JSONObject habilidadActual;
            for (int i = 0; i < habilidades.length(); i++){
                habilidadActual = habilidades.getJSONObject(i);
                String nombre = habilidadActual.getString("nombre");
                Integer id = habilidadActual.getInt("id");
                Tipo tipo = Tipo.valueOf(habilidadActual.getString("tipo"));
                Double poder = habilidadActual.getDouble("poder");

                Efecto efecto = CreadorEfecto.crearEfecto(habilidadActual.getString("efecto"),poder);

                Double probaEfecto = habilidadActual.getDouble("probabilidad_aplicar_efecto");
                Integer cantidadUsos = habilidadActual.getInt("cantidad_usos");
                String descripcion = habilidadActual.getString("descripcion");
                String clima = habilidadActual.getString("clima");
                EnumObjetivo objetivo = EnumObjetivo.valueOf(habilidadActual.getString("objetivo"));

                Habilidad nuevaHabilidad = new Habilidad(nombre, id, tipo, poder, efecto, probaEfecto, cantidadUsos, descripcion, clima,objetivo);
                hashHabilidades.put(id, nuevaHabilidad);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        CreadorHabilidades.hashHabilidades = hashHabilidades;
    }

    public static HashMap<Integer, Habilidad> getHabilidades() {
        return hashHabilidades;
    }
    public static void setPath(String path) {
        CreadorHabilidades.path = path;
    }
}
