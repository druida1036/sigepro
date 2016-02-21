package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Accion;
import co.com.sigepro.negocio.serv.AccionServicio;
import co.com.sigepro.persistencia.dao.AccionDao;

@Service("accionServicio")
public class AccionServicioImpl implements AccionServicio {
	@Autowired
	private AccionDao dao;
	
	public List<Accion> listado(Accion accion){
		return dao.listado(accion);
	}
	
	public Accion cargar(Integer id){
		return dao.cargar(id);
	}
}
