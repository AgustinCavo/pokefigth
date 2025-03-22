package JuegoPokemon.Controlador.ControladorSeleccionHabilidad;

import JuegoPokemon.modelo.Constantes;
import JuegoPokemon.modelo.game.Habilidad;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class ControladorBotonHabilidad implements IControladorBotonHabilidad {

	private Object solicitante;

	private Method retorno;

	private Habilidad habilidad;

	private Integer cantidad;

	private Object receptorEntrada;

	private Method mostrarInfo;

	private Object receptorSalida;

	private Method deleteInfo;

	@FXML
	private ImageView cuadroHabilidad;

	@FXML
	private Label nombreHabilidad;

	private Image cuadroSinSeleccionar;

	private Image cuadroSeleccionado;

	public void initialize() throws FileNotFoundException {
		this.cuadroSinSeleccionar = new Image(new FileInputStream("src/main/resources/SeleccionHabilidad/CuadroHabilidadSinSeleccionar.png"));
		this.cuadroSeleccionado = new Image(new FileInputStream("src/main/resources/SeleccionHabilidad/CuadroHabilidadSeleccionada.png"));
		Font font = new Font(Constantes.TAMAÑOLETRA);
		this.nombreHabilidad.setFont(font);
	}

	@Override
	public void setHabilidad(Habilidad habilidad) {
		this.habilidad = habilidad;
		this.nombreHabilidad.setText(habilidad.getNombre());
	}

	@Override
	public void setRetorno(Object solicitante, Method retorno) {
		this.solicitante = solicitante;
		this.retorno = retorno;
	}

	@Override
	public void setInfoEntrada(Object receptor, Method mostrar) {
		this.receptorEntrada = receptor;
		this.mostrarInfo = mostrar;
	}

	@Override
	public void setDeleteInfo(Object receptor, Method deleteInfo) {
		this.receptorSalida = receptor;
		this.deleteInfo = deleteInfo;
	}

	@FXML
	private void clickHabilidad() throws InvocationTargetException, IllegalAccessException {
		if (this.habilidad != null && this.habilidad.getCantidadUsos() != 0)
			this.retorno.invoke(this.solicitante, this.habilidad);
	}

	@FXML
	private void entroHabilidad() throws InvocationTargetException, IllegalAccessException {
		if (this.habilidad != null && this.habilidad.getCantidadUsos() != 0) {
			this.cuadroHabilidad.setImage(this.cuadroSeleccionado);
			this.mostrarInfo.invoke(this.receptorEntrada, this.habilidad);
		}
	}

	@FXML
	private void salioHabilidad() throws InvocationTargetException, IllegalAccessException {
		if (this.habilidad != null && this.habilidad.getCantidadUsos() != 0) {
			this.cuadroHabilidad.setImage(this.cuadroSinSeleccionar);
			this.deleteInfo.invoke(this.receptorSalida);
		}
	}
}
