package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Propuesta;
import co.com.sigepro.negocio.serv.PropuestaServicio;
import co.com.sigepro.persistencia.dao.PropuestaDao;

@Service
public class PropuestaServicioImpl extends ServicioGenerico implements
		PropuestaServicio {
	@Autowired
	private PropuestaDao dao;

	public Propuesta guardar(Propuesta propuesta) {
		actualizarCaposAuditoria(propuesta);
		return dao.guardar(propuesta);
	}

	public Propuesta cargar(Integer id) {
		return dao.cargar(id);
	}
	
	public void borrar(Propuesta propuesta) {
		dao.borrar(propuesta);
	}

	@Override
	public List<Propuesta> listado(Propuesta propuesta) {
		return dao.listado(propuesta);
	}

}
