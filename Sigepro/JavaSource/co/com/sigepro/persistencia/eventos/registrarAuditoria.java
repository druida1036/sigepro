package co.com.sigepro.persistencia.eventos;

import java.lang.reflect.Field;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.event.PreCollectionRemoveEvent;
import org.hibernate.event.PreCollectionRemoveEventListener;
import org.hibernate.event.PreDeleteEvent;
import org.hibernate.event.PreDeleteEventListener;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Actividad;
import co.com.sigepro.entidades.EntidadGenerica;
import co.com.sigepro.entidades.Persona;

public class registrarAuditoria implements PreUpdateEventListener,
		PreInsertEventListener, PreDeleteEventListener,
		PreCollectionRemoveEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean onPreUpdate(PreUpdateEvent event) {

		if (event.getEntity() instanceof EntidadGenerica) {
			EntidadGenerica entidadGenerica = (EntidadGenerica) event
					.getEntity();
			HttpServletRequest request = FacesUtils.getRequest();
			entidadGenerica.setCtlIp(request.getRemoteHost());

			Persona usuario = FacesUtils.getUsuario();
			if (usuario != null) {
				entidadGenerica.setCtlUsuario(usuario.getLogin());
			}
			entidadGenerica.setCtlFecMod(new Date());

			String[] propertyNames = event.getPersister().getEntityMetamodel()
					.getPropertyNames();
			Object[] state = event.getState();
			// updates
			setValue(state, propertyNames, entidadGenerica);

		}

		return false;
	}

	public boolean onPreInsert(PreInsertEvent event) {
		if (event.getEntity() instanceof EntidadGenerica) {
			EntidadGenerica entidadGenerica = (EntidadGenerica) event
					.getEntity();
			HttpServletRequest request = FacesUtils.getRequest();
			entidadGenerica.setCtlIp(request.getRemoteHost());

			Persona usuario = FacesUtils.getUsuario();
			entidadGenerica.setCtlUsuario(usuario.getLogin());
			entidadGenerica.setCtlFecMod(new Date());
			entidadGenerica.setCtlFecAlta(new Date());
			String[] propertyNames = event.getPersister().getEntityMetamodel()
					.getPropertyNames();
			Object[] state = event.getState();
			// updates
			setValue(state, propertyNames, entidadGenerica);

		}
		return false;
	}

	void setValue(Object[] currentState, String[] propertyNames,
			EntidadGenerica entity) {
		Class<?> superClase = (Class<?>) entity.getClass()
				.getGenericSuperclass();
		Field[] propiedades = superClase.getDeclaredFields();
		for (Field propiedad : propiedades) {
			System.out.println(propiedad.getName());
			int index = ArrayUtils.indexOf(propertyNames, propiedad.getName());
			if (index >= 0) {
				propiedad.setAccessible(true);
				try {
					if (propiedad.get(entity) != null) {
						currentState[index] = propiedad.get(entity);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} else {
				// Log.error("Field '" + propertyToSet +
				// "' not found on entity '" + entity.getClass().getName() +
				// "'.");
			}
		}
	}

	@Override
	public boolean onPreDelete(PreDeleteEvent arg0) {
		Object entidad = arg0.getEntity();
		if (entidad instanceof Actividad) {

		}
		return false;
	}

	@Override
	public void onPreRemoveCollection(PreCollectionRemoveEvent arg0) {
		Object entidad = arg0.getAffectedOwnerOrNull();
		if (entidad instanceof Actividad) {

		}

	}

}
