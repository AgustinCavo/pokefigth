package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.modelo.game.Habilidad;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.efectos.Efecto;
import JuegoPokemon.modelo.game.efectos.ModificarEstadistica;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class AlteracionEstadisticas implements Suceso {

    private final IControladorJugadores controladorJugadores;

    private final ModificarEstadistica efecto;

    private final Pokemon pokemon;
    private final Habilidad habilidad;

    private Stage stage;

    private Label label;

    public AlteracionEstadisticas(IControladorJugadores controladorJugadores, Pokemon pokemon, Efecto efecto, Habilidad habiliadad) {
        ModificarEstadistica efectoEstadistica = (ModificarEstadistica) efecto;
        this.efecto = efectoEstadistica;
        this.controladorJugadores = controladorJugadores;
        this.pokemon = pokemon;
        this.habilidad = habiliadad;
    }

    @Override
    public void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
        if(habilidad.getPoder()<1){
            this.label.setText("El pokemon " + pokemon.getNombre() + " perdio " + efecto.getEstadistica());
        }else{
            this.label.setText("El pokemon " + pokemon.getNombre() + " aumento su " + efecto.getEstadistica());
        }

        this.stage.show();
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void addLabelMensaje(Label label) {
        this.label = label;
    }
}
