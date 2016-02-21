package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Correo;
import co.com.sigepro.entidades.Notificacion;
import co.com.sigepro.entidades.Variable;
import co.com.sigepro.negocio.serv.NotificacionServicio;
import co.com.sigepro.negocio.serv.VariableServicio;
import co.com.sigepro.persistencia.dao.NotificacionDao;

@Service
public class NotificacionServicioImpl extends ServicioGenerico implements
		NotificacionServicio {
	private static final String COMODIN_REFERENCIA = "#";
	@Autowired
	private NotificacionDao dao;
	@Autowired
	private VariableServicio variableServicio;
	@Autowired
	private CorreoServicioImpl correoServicio;

	public Notificacion guardar(Notificacion notificacion) {
		actualizarVariables(notificacion);
		return dao.guardar(notificacion);
	}

	private void actualizarVariables(Notificacion notificacion) {
		String descripcion = notificacion.getDescripcion();
		String[] palabras = descripcion.split("\\s+");

		notificacion.getVariablesNotificacion().clear();
		for (String palabra : palabras) {
			if (palabra.startsWith(COMODIN_REFERENCIA)) {
				Variable variable = variableServicio
						.cargarPorReferencia(palabra);
				if (variable != null
						&& !notificacion.getVariablesNotificacion().contains(
								variable)) {
					notificacion.getVariablesNotificacion().add(variable);
				}
			}
		}

	}

	public void enviarNotificacion(String destinatario,
			Notificacion notificacion) {
		String archivo = notificacion.getDescripcion();
		for (Variable variable : notificacion.getVariablesNotificacion()) {
			if(FacesUtils.getValorVariable(variable.getReferencia()) !=null)
			archivo = archivo.replaceAll(variable.getReferencia(),
					FacesUtils.getValorVariable(variable.getReferencia()));
		}
		Correo correo = new Correo();
		correo = correoServicio.crearCorreo(destinatario,
				notificacion.getAsunto(), archivo);
		correoServicio.enviarCorreo(correo);
	}

	public List<Notificacion> listado(Notificacion notificacion) {
		return dao.listado(notificacion);
	}

	public Notificacion cargar(Integer id) {
		return dao.cargar(id);
	}
}
