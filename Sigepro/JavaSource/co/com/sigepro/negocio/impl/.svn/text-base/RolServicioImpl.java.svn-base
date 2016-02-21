package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Rol;
import co.com.sigepro.negocio.serv.RolServicio;
import co.com.sigepro.persistencia.dao.RolDao;

@Service
public class RolServicioImpl extends ServicioGenerico implements RolServicio {
	@Autowired
	private RolDao dao;
		
	public Rol cargar(Integer id){
		return dao.cargar(id);
	}
	
	public Rol guardar(Rol rol){
		actualizarCaposAuditoria(rol);
		return dao.guardar(rol);
	}

	@Override
	public List<Rol> listado(Rol rol) {
		return dao.listado(rol);
	}
}
