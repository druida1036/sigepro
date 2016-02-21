package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Notificacion;
import co.com.sigepro.entidades.Variable;
import co.com.sigepro.negocio.serv.NotificacionServicio;
import co.com.sigepro.negocio.serv.VariableServicio;

@Controller
@Scope("session")
public class NotificacionBean {

	private int posicionCursor;
	private Notificacion notificacion = new Notificacion();
	private Variable variableNotificacion;
	@Autowired
	private NotificacionServicio notificacionServicio;
	@Autowired
	private VariableServicio variableNotificacionServicio;
	private List<Variable> variablesNotificacion;

	public String verInicializar() {

		return ConstantesNavegacion.DETALLE_NOTIFICACION;
	}

	public String crearInicializar() {

		notificacion = new Notificacion();

		return ConstantesNavegacion.CREAR_NOTIFICACION;
	}

	public String crearAceptar() {
		notificacion = notificacionServicio.guardar(notificacion);
		FacesUtils.agregarMensajeInformacion("msg.crearCorrecto", true);
		return ConstantesNavegacion.CONSULTA_NOTIFICACION;
	}

	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_NOTIFICACION;
	}

	public String crearCancelar() {

		return ConstantesNavegacion.CONSULTA_NOTIFICACION;
	}

	public String editarInicializar() {
		variablesNotificacion = new ArrayList<Variable>();
		variablesNotificacion = variableNotificacionServicio
				.listado(new Variable());
		notificacion = notificacionServicio.cargar(notificacion.getId());
		return ConstantesNavegacion.EDITAR_NOTIFICACION;
	}

	public String editarAceptar() {
		notificacion = notificacionServicio.guardar(notificacion);
		FacesUtils.agregarMensajeInformacion("msg.editarCorrecto", true);
		return ConstantesNavegacion.CONSULTA_NOTIFICACION;
	}

	public String editarCancelar() {
		notificacion = notificacionServicio.cargar(notificacion.getId());
		return ConstantesNavegacion.CONSULTA_NOTIFICACION;
	}

	public String guardar() {
		notificacionServicio.guardar(notificacion);
		notificacion = new Notificacion();
		FacesUtils.agregarMensajeInformacion("Notificacion creado con exito",
				false);
		return "";
	}

	public String limpiar() {
		notificacion = new Notificacion();
		return ConstantesNavegacion.NO_ACCION;
	}

	public String agregarVariable() {
		if (notificacion != null && variableNotificacion != null) {
			StringBuilder textoNotificacion;

			textoNotificacion = new StringBuilder(
					StringUtils.defaultString(notificacion.getDescripcion()));
			textoNotificacion.insert(posicionCursor,
					variableNotificacion.getReferencia());
			posicionCursor += variableNotificacion.getReferencia().length(); 
			notificacion.setDescripcion(textoNotificacion.toString());

			variableNotificacion = null;
		}
		return ConstantesNavegacion.NO_ACCION;
	}

	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}

	public List<Variable> getVariablesNotificacion() {
		return variablesNotificacion;
	}

	public void setVariablesNotificacion(
			List<Variable> variablesNotificacion) {
		this.variablesNotificacion = variablesNotificacion;
	}

	@PostConstruct
	public void postcostructor() {
		System.err.println("prueba exitosa");
	}

	public Variable getVariableNotificacion() {
		return variableNotificacion;
	}

	public void setVariableNotificacion(
			Variable variableNotificacion) {
		this.variableNotificacion = variableNotificacion;
	}

	public int getPosicionCursor() {
		return posicionCursor;
	}

	public void setPosicionCursor(int posicionCursor) {
		this.posicionCursor = posicionCursor;
	}
}
