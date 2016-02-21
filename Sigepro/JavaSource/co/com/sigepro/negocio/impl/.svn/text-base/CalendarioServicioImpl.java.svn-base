package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Calendario;
import co.com.sigepro.negocio.serv.CalendarioServicio;
import co.com.sigepro.persistencia.dao.CalendarioDao;

@Service
public class CalendarioServicioImpl extends ServicioGenerico implements
		CalendarioServicio {
	@Autowired
	private CalendarioDao dao;

	public Calendario guardar(Calendario calendario) {
		actualizarCaposAuditoria(calendario);
		return dao.guardar(calendario);
	}

	public Calendario cargar(Integer id) {
		return dao.cargar(id);
	}

	@Override
	public List<Calendario> listado(Calendario calendario) {
		return dao.listado(calendario);
	}
}
