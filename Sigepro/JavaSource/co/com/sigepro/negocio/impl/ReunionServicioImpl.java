package co.com.sigepro.negocio.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Notificacion;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.entidades.Reunion;
import co.com.sigepro.negocio.serv.ReunionServicio;
import co.com.sigepro.negocio.utilidades.ConstantesNotificacion;
import co.com.sigepro.persistencia.dao.ReunionDao;

@Service
public class ReunionServicioImpl extends ServicioGenerico implements
		ReunionServicio {
	@Autowired
	private ReunionDao dao;
	@Autowired
	private NotificacionServicioImpl notificacionServicio;
	private Notificacion notificacion = new Notificacion();

	public Reunion guardar(Reunion reunion) {
		actualizarCaposAuditoria(reunion);

		for (int x = 0; x < reunion.getPersonas().size(); x++) {
			notificar(reunion.getPersonas().get(x),reunion);
		}
		return dao.guardar(reunion);
	}

	public Reunion cargar(Integer id) {
		return dao.cargar(id);
	}

	@Override
	public List<Reunion> listado(Reunion reunion) {
		return dao.listado(reunion);
	}

	public boolean notificar(Persona persona, Reunion reunion) {

		notificacion = cargarValoresNotificacion(persona, reunion);
		notificacionServicio.enviarNotificacion(persona.getCorreo(),
				notificacion);
		return true;
	}

	private Notificacion cargarValoresNotificacion(Persona persona, Reunion reunion) {
		Notificacion notificacion = notificacionServicio.cargar(9);
		String usuario = persona.getNombre() + " ";
		usuario = usuario + persona.getApellido() + " ";
		FacesUtils.setValorVariable(ConstantesNotificacion.VN_USUARIO, usuario);
		FacesUtils.setValorVariable(ConstantesNotificacion.VN_LOGIN,
				persona.getLogin());
		FacesUtils.setValorVariable(ConstantesNotificacion.RN_ASUNTO ,
				reunion.getAsunto());
		FacesUtils.setValorVariable(ConstantesNotificacion.RN_LUGAR ,
				reunion.getLugar());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");		
		FacesUtils.setValorVariable(ConstantesNotificacion.VN_FECHA ,
				formatter.format(reunion.getFecha()));
		formatter = new SimpleDateFormat("HH:mm");
		FacesUtils.setValorVariable(ConstantesNotificacion.VN_HORA ,
				formatter.format(reunion.getFecha()));
		return notificacion;
	}
}
