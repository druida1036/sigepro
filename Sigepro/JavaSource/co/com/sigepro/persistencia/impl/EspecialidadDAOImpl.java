package co.com.sigepro.persistencia.impl;

import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Especialidad;
import co.com.sigepro.persistencia.dao.EspecialidadDao;

@Repository("EspecialidadDao")
public class EspecialidadDAOImpl extends
		AbstractHibernateDaoImpl<Especialidad, Integer> implements EspecialidadDao {


	public EspecialidadDAOImpl() {
		super();
	}

}