package JuegoPokemon.Controlador.ControladorSucesos;

import JuegoPokemon.Controlador.ControladorJugadores.IControladorJugadores;
import JuegoPokemon.modelo.game.Pokemon;
import JuegoPokemon.modelo.game.efectos.Efecto;
import JuegoPokemon.modelo.game.efectos.ModificarEstadistica;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class AutoCuracion implements Suceso{
    private final IControladorJugadores controladorJugadores;

    private final Double vidaAnterior;

    private final Pokemon pokemon;

    private Stage stage;

    private Label label;

    public AutoCuracion(IControladorJugadores controladorJugadores, Pokemon pokemon,Double vidaAnterior) {
        this.vidaAnterior=vidaAnterior;
        this.controladorJugadores = controladorJugadores;
        this.pokemon = pokemon;
    }

    @Override
    public void mostrarSuceso() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
        if (pokemon.getVidaMaxima().equals(vidaAnterior)){
            this.label.setText("El pokemon tiene la vida maxima");
        }else{
            this.label.setText("El pokemon " + pokemon.getNombre() + " recupero HP");
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
