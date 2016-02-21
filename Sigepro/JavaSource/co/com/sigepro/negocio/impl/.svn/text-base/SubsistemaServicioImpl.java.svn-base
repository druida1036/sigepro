package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Subsistema;
import co.com.sigepro.negocio.serv.SubsistemaServicio;
import co.com.sigepro.persistencia.dao.SubsistemaDao;

@Service("subsistemaServicio")
public class SubsistemaServicioImpl implements SubsistemaServicio {
	@Autowired
	private SubsistemaDao dao;
	
	public List<Subsistema> listado(Subsistema subsistema){
		return dao.listado(subsistema);
	}
	
	public Subsistema cargar(Integer id){
		return dao.cargar(id);
	}


	public List<Subsistema> getSubsistemasXRol(Integer id) {
		return dao.getSubsistemasXRol(id);
	}
}
