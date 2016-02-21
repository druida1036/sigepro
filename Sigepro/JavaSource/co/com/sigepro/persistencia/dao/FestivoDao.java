package co.com.sigepro.persistencia.dao;

import java.util.List;

import co.com.sigepro.entidades.Festivo;

public interface FestivoDao extends GenericDao<Festivo, Integer> {
	public List<Festivo> listado(Festivo festivo);


}
