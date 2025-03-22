module BatallaPokemon {

	requires javafx.controls;
	requires javafx.fxml;

	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	requires reflections;
	requires org.json;

	exports JuegoPokemon;
	opens JuegoPokemon to javafx.controls, javafx.fxml, com.fasterxml.jackson.core, com.fasterxml.jackson.databind, com.fasterxml.jackson.annotation;

	exports JuegoPokemon.Controlador.ControladorPartida;
	opens JuegoPokemon.Controlador.ControladorPartida to javafx.controls, javafx.fxml, com.fasterxml.jackson.core, com.fasterxml.jackson.databind, com.fasterxml.jackson.annotation;

	exports JuegoPokemon.modelo.game;
	opens JuegoPokemon.modelo.game to javafx.controls, javafx.fxml, com.fasterxml.jackson.core, com.fasterxml.jackson.databind, com.fasterxml.jackson.annotation;

	exports JuegoPokemon.modelo.game.efectos;
	opens JuegoPokemon.modelo.game.efectos to com.fasterxml.jackson.core, com.fasterxml.jackson.databind, javafx.controls, javafx.fxml, com.fasterxml.jackson.annotation;

	exports JuegoPokemon.modelo.game.clima;
	opens JuegoPokemon.modelo.game.clima to com.fasterxml.jackson.core, com.fasterxml.jackson.databind, javafx.controls, javafx.fxml, com.fasterxml.jackson.annotation;

	exports JuegoPokemon.modelo.game.estado;
	opens JuegoPokemon.modelo.game.estado to com.fasterxml.jackson.core, com.fasterxml.jackson.databind, javafx.controls, javafx.fxml, com.fasterxml.jackson.annotation;

	exports JuegoPokemon.modelo.game.acciones;
	opens JuegoPokemon.modelo.game.acciones to com.fasterxml.jackson.annotation, com.fasterxml.jackson.core, com.fasterxml.jackson.databind, javafx.controls, javafx.fxml;

	exports JuegoPokemon.modelo.controladores;
	opens JuegoPokemon.modelo.controladores to com.fasterxml.jackson.annotation, com.fasterxml.jackson.core, com.fasterxml.jackson.databind, javafx.controls, javafx.fxml;

	exports JuegoPokemon.Controlador;
	opens JuegoPokemon.Controlador to com.fasterxml.jackson.annotation, com.fasterxml.jackson.core, com.fasterxml.jackson.databind, javafx.controls, javafx.fxml;


	exports JuegoPokemon.Controlador.ControladorSeleccionPokemon;
	opens JuegoPokemon.Controlador.ControladorSeleccionPokemon to javafx.fxml, javafx.controls;

	exports JuegoPokemon.Controlador.ControladorSeleccionAccion;
	opens JuegoPokemon.Controlador.ControladorSeleccionAccion to javafx.fxml, javafx.controls;

	exports JuegoPokemon.Controlador.ControladorSeleccionHabilidad;
	opens JuegoPokemon.Controlador.ControladorSeleccionHabilidad to javafx.fxml, javafx.controls;

	exports JuegoPokemon.Controlador.ControladorSeleccionItem;
	opens JuegoPokemon.Controlador.ControladorSeleccionItem to javafx.fxml, javafx.controls;

	exports JuegoPokemon.Controlador.ControladorCampoBatalla;
	opens JuegoPokemon.Controlador.ControladorCampoBatalla to javafx.fxml, javafx.controls;

	exports JuegoPokemon.Controlador.ControladorJugadores;
	opens JuegoPokemon.Controlador.ControladorJugadores to javafx.fxml, javafx.controls;

	exports JuegoPokemon.Controlador.ControladorBatalla;
	opens JuegoPokemon.Controlador.ControladorBatalla to javafx.controls, javafx.fxml;

	exports JuegoPokemon.modelo.game.inicializadores;
	opens JuegoPokemon.modelo.game.inicializadores to javafx.fxml, javafx.controls;

	exports JuegoPokemon.Controlador.ControladorConsultarRendicion;
	opens JuegoPokemon.Controlador.ControladorConsultarRendicion to javafx.fxml, javafx.controls;

	exports JuegoPokemon.Controlador.ControladorSucesos;
	opens JuegoPokemon.Controlador.ControladorSucesos to javafx.controls, javafx.fxml;

}