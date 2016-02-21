package co.com.sigepro.persistencia.impl;

import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Accion;
import co.com.sigepro.persistencia.dao.AccionDao;

@Repository("AccionDao")
public class AccionDAOImpl extends
		AbstractHibernateDaoImpl<Accion, Integer> implements AccionDao {

	public AccionDAOImpl() {
		super();
	}

}