package co.com.sigepro.persistencia.dao;

import java.util.List;

import co.com.sigepro.entidades.Calendario;

public interface CalendarioDao extends GenericDao<Calendario, Integer> {
	public List<Calendario> listado(Calendario calendario);


}
