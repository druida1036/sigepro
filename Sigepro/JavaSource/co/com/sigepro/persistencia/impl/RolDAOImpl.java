package co.com.sigepro.persistencia.impl;

import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Rol;
import co.com.sigepro.persistencia.dao.RolDao;

@Repository("RolDao")
public class RolDAOImpl extends
		AbstractHibernateDaoImpl<Rol, Integer> implements RolDao {


	public RolDAOImpl() {
		super();
	}

}