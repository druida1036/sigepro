package co.com.sigepro.persistencia.impl;

import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Herramienta;
import co.com.sigepro.persistencia.dao.HerramientaDao;

@Repository("herramientaDao")
public class HerramientaDAOImpl extends
		AbstractHibernateDaoImpl<Herramienta, Integer> implements HerramientaDao {


	public HerramientaDAOImpl() {
		super();
	}

}