package co.com.sigepro.persistencia.dao;

import java.util.List;

import co.com.sigepro.entidades.Proyecto;

public interface ProyectoDao extends GenericDao<Proyecto, Integer> {
	public List<Proyecto> listado(Proyecto proyecto);

}
