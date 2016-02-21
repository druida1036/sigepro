package co.com.sigepro.persistencia.impl;

import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Reunion;
import co.com.sigepro.persistencia.dao.ReunionDao;

@Repository("reunionDao")
public class ReunionDAOImpl extends AbstractHibernateDaoImpl<Reunion, Integer>
		implements ReunionDao {
	
	public ReunionDAOImpl() {
		super();
	}
}