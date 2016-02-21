package co.com.sigepro.persistencia.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Persona;

/**
 * Clase que garantiza la existencia de una unica session en la la aplicacion
 * 
 * @author Jorge Martinez
 * 
 */
@Component
public class SessionUtil {

	private SessionFactory sessionFactory;
	private Session session;
	private Map<String, Session> sessiones = new HashMap<String, Session>();

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public SessionUtil() {
		super();
	}

	public Session openSession() {
		Persona usuario = FacesUtils.getUsuario();
		if (usuario == null) {
			session = sessiones.get("general");
			if (session == null) {
				session = sessionFactory.openSession();
				sessiones.put("general", session);
			} else if (!session.isOpen()) {
				session = sessionFactory.openSession();
			}
		} else {
			session = sessiones.get(usuario.getLogin());
			if (session == null) {
				session = sessionFactory.openSession();
				sessiones.put(usuario.getLogin(), session);
			} else if (!session.isOpen()) {
				session = sessionFactory.openSession();
			}
		}

		return session;
	}

	public Session getCurrentSession() {
		if (session == null) {
			session = openSession();
		} else if (!session.isOpen()) {
			session = openSession();
		}
		return session;
	}

	public void cerrarSession() {
		getCurrentSession().clear();
		getCurrentSession().close();
	}
}
