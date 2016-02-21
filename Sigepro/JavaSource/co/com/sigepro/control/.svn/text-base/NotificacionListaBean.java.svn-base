package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.entidades.Notificacion;
import co.com.sigepro.negocio.serv.NotificacionServicio;

@Controller
@Scope("session")
public class NotificacionListaBean {
	private Notificacion notificacion = new Notificacion();
	private Notificacion notificacionSeleccionado;
	private List<Notificacion> notificaciones = new ArrayList<Notificacion>();
	private Map<Notificacion, Boolean> notificacionesSeleccionadas = new HashMap<Notificacion, Boolean>();

	@Autowired
	private NotificacionServicio notificacionServicio;
	@Autowired
	private NotificacionBean notificacionBean;
	@Autowired
	private MetodologiaBean metodologiaBean;

	public String consultar() {
		limpiar();
		notificaciones = notificacionServicio.listado(notificacion);
		for (Notificacion notificacion : metodologiaBean.getActividad()
				.getNotificaciones()) {
			if (notificaciones.contains(notificacion)) {
				notificacionesSeleccionadas
						.put(notificacion, new Boolean(true));
			}
		}
		return "";
	}

	public String ver() {

		notificacionBean.setNotificacion(notificacionSeleccionado);
		return notificacionBean.verInicializar();
	}

	public String crear() {

		limpiar();

		return notificacionBean.crearInicializar();
	}

	public String editar() {

		notificacionBean.setNotificacion(notificacionSeleccionado);

		return notificacionBean.editarInicializar();
	}

	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_NOTIFICACION;
	}

	public String editarInicializar() {

		return ConstantesNavegacion.EDITAR_NOTIFICACION;
	}

	public String limpiar() {
		notificacion = new Notificacion();
		notificaciones = new ArrayList<Notificacion>();
		notificacionesSeleccionadas = new HashMap<Notificacion, Boolean>();
		return "";
	}

	public String agregarNotificacionActividad() {
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();

		Iterator<Entry<Notificacion, Boolean>> itr = notificacionesSeleccionadas
				.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<Notificacion, Boolean> e = (Map.Entry<Notificacion, Boolean>) itr
					.next();
			if (e.getValue()) {
				System.out.println("clave: " + e.getKey().getNombre());
				notificaciones.add(e.getKey());
			}
		}
		metodologiaBean.agregarNotificacion(notificaciones);
		limpiar();

		return "";
	}

	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}

	public Notificacion getNotificacionSeleccionado() {
		return notificacionSeleccionado;
	}

	public void setNotificacionSeleccionado(
			Notificacion notificacionSeleccionado) {
		this.notificacionSeleccionado = notificacionSeleccionado;
	}

	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public int getNumeroResultados() {
		return this.notificaciones != null ? this.notificaciones.size() : 0;
	}

	public Map<Notificacion, Boolean> getNotificacionesSeleccionadas() {
		return notificacionesSeleccionadas;
	}

	public void setNotificacionesSeleccionadas(
			Map<Notificacion, Boolean> notificacionesSeleccionadas) {
		this.notificacionesSeleccionadas = notificacionesSeleccionadas;
	}

}
