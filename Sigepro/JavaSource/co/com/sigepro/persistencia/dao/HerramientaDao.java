package co.com.sigepro.persistencia.dao;

import java.util.List;

import co.com.sigepro.entidades.Herramienta;

public interface HerramientaDao extends GenericDao<Herramienta, Integer> {
	public List<Herramienta> listado(Herramienta herramienta);

}
