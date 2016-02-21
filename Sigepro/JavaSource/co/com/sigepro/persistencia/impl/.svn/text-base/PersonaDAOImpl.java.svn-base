package co.com.sigepro.persistencia.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Cliente;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.persistencia.dao.PersonaDao;

@Repository("PersonaDao")
public class PersonaDAOImpl extends
		AbstractHibernateDaoImpl<Persona, Integer> implements PersonaDao {


	public PersonaDAOImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listado(Cliente cliente){
		createCriteriaLike(cliente);
		return getHibernateTemplate().findByCriteria(getCriteria());
	}
}