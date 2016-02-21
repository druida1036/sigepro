package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Especialidad;
import co.com.sigepro.negocio.serv.EspecialidadServicio;
import co.com.sigepro.persistencia.dao.EspecialidadDao;
@Service
public class EspecialidadServicioImpl extends ServicioGenerico implements EspecialidadServicio {
	@Autowired
	private EspecialidadDao dao;
	
	public Especialidad guardar(Especialidad especialidad) {
		actualizarCaposAuditoria(especialidad);
		return dao.guardar(especialidad);
	}
	
	public List<Especialidad> listado(Especialidad especialidad){
		return dao.listado(especialidad);
	}

	public Especialidad cargar(Integer id){
		return dao.cargar(id);
	}
}
