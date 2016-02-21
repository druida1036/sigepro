package co.com.sigepro.persistencia.dao;

import java.util.List;

import co.com.sigepro.entidades.Metodologia;

public interface MetodologiaDao extends GenericDao<Metodologia, Integer> {
	public List<Metodologia> listado(Metodologia metodologia);
	public List<Metodologia> metodologiasConcretas(Metodologia metodologia);

}
