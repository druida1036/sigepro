package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Cliente;
import co.com.sigepro.negocio.serv.ClienteServicio;
import co.com.sigepro.persistencia.dao.ClienteDao;
@Service
public class ClienteServicioImpl extends ServicioGenerico implements ClienteServicio {
	@Autowired
	private ClienteDao dao;
	
	public ClienteDao getDao() {
		return dao;
	}

	public void setDao(ClienteDao dao) {
		this.dao = dao;
	}

	public Cliente guardar(Cliente cliente) {
		actualizarCaposAuditoria(cliente);
		return dao.guardar(cliente);
	}
	
	public List<Cliente> listado(Cliente cliente){
		return dao.listado(cliente);
	}
	
	public Cliente cargar(Integer id){
		return dao.cargar(id);
	}
}
