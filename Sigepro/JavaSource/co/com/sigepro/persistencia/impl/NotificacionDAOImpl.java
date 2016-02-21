package co.com.sigepro.persistencia.impl;

import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Notificacion;
import co.com.sigepro.persistencia.dao.NotificacionDao;

@Repository("NotificacionDao")
public class NotificacionDAOImpl extends
		AbstractHibernateDaoImpl<Notificacion, Integer> implements NotificacionDao {


	public NotificacionDAOImpl() {
		super();
	}


}