package co.com.sigepro.persistencia.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Metodologia;
import co.com.sigepro.persistencia.dao.MetodologiaDao;

@Repository("metodologiaDao")
public class MetodologiaDAOImpl extends
		AbstractHibernateDaoImpl<Metodologia, Integer> implements MetodologiaDao {


	public MetodologiaDAOImpl() {
		super();
	}
	
	public List<Metodologia> metodologiasConcretas(Metodologia metodologia){
		if(metodologia == null){
			metodologia = new Metodologia();
		}
		List<Metodologia> metodologias = new ArrayList<Metodologia>();
		Example example = Example.create(metodologia)
		.ignoreCase() //perform case insensitive string comparisons
		.enableLike() //use like for string comparisons
		.excludeProperty("tipo")
		.excludeProperty("avance"); 
		for (Object object : getSession().createCriteria(Metodologia.class)
				.add(example)
				.add(Restrictions.eq("tipo", "Base"))
				.list()) {
			metodologias.add(cargar(((Metodologia) object).getId()));
			
		}
		return metodologias;
	}

}