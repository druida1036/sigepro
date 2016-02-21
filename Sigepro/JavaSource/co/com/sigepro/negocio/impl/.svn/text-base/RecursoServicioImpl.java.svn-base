package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Recurso;
import co.com.sigepro.negocio.serv.RecursoServicio;
import co.com.sigepro.persistencia.dao.RecursoDao;

@Service
public class RecursoServicioImpl extends ServicioGenerico implements
		RecursoServicio {

	@Autowired
	private RecursoDao dao;

	public Recurso guardar(Recurso recurso) {
		recurso = dao.guardar(recurso);
		return recurso;
	}

	public List<Recurso> listado(Recurso recurso) {
		return dao.listado(recurso);
	}

	public Recurso cargar(Integer id) {
		return dao.cargar(id);
	}

}
