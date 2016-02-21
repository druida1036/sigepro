package co.com.sigepro.persistencia.dao;

import java.util.List;

import co.com.sigepro.entidades.Cliente;

public interface ClienteDao extends GenericDao<Cliente, Integer> {
	public List<Cliente> listado(Cliente cliente);

}
