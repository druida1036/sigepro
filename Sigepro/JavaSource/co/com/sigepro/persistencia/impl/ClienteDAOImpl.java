package co.com.sigepro.persistencia.impl;

import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Cliente;
import co.com.sigepro.persistencia.dao.ClienteDao;

@Repository("clienteDao")
public class ClienteDAOImpl extends
		AbstractHibernateDaoImpl<Cliente, Integer> implements ClienteDao {


	public ClienteDAOImpl() {
		super();
	}

}