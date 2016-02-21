package co.com.sigepro.persistencia.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Recurso;
import co.com.sigepro.persistencia.dao.RecursoDao;

@Repository("RecursoDao")
public class RecursoDAOImpl extends
		AbstractHibernateDaoImpl<Recurso, Integer> implements RecursoDao {


	public RecursoDAOImpl() {
		super();
	}

	public List<Recurso> listado(Recurso recurso){
		if(recurso == null){
			recurso = new Recurso();
		}
		List<Recurso> recursos = new ArrayList<Recurso>();
		Example example = Example.create(recurso)
		.ignoreCase() //perform case insensitive string comparisons
		.enableLike(); //use like for string comparisons
		Criteria criteria = getSession().createCriteria(Recurso.class)
		.add(example);
		Criteria criteriaUsuario = criteria.createCriteria("usuario");
		if (!StringUtils.isEmpty(recurso.getUsuario().getNombre())) {
			criteriaUsuario.add(Restrictions.ilike("nombre", recurso
					.getUsuario().getNombre(), MatchMode.ANYWHERE));
		}
		if (!StringUtils.isEmpty(recurso.getUsuario().getApellido())) {
			criteriaUsuario.add(Restrictions.ilike("apellido", recurso
					.getUsuario().getApellido(), MatchMode.ANYWHERE));
		}
		if (!StringUtils.isEmpty(recurso.getUsuario().getLogin())) {
			criteriaUsuario.add(Restrictions.ilike("login", recurso
					.getUsuario().getLogin(), MatchMode.ANYWHERE));
		}
		if (!StringUtils.isEmpty(recurso.getUsuario().getCorreo())) {
			criteriaUsuario.add(Restrictions.ilike("correo", recurso
					.getUsuario().getCorreo(), MatchMode.ANYWHERE));
		}
		for (Object object : criteria.list()) {
			recursos.add(cargar(((Recurso) object).getId()));
			
		}
		return recursos;
	}
	
}