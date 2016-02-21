package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Rol;
import co.com.sigepro.entidades.Submenu;
import co.com.sigepro.entidades.Subsistema;
import co.com.sigepro.negocio.serv.SubmenuServicio;
import co.com.sigepro.persistencia.dao.SubmenuDao;

@Service("submenuServicio")
public class SubmenuServicioImpl implements SubmenuServicio {
	@Autowired
	private SubmenuDao dao;
	
	public List<Submenu> listado(Submenu submenu){
		return dao.listado(submenu);
	}
	
	public Submenu cargar(Integer id){
		return dao.cargar(id);
	}

	
	public List<Submenu> consultaSubmenusXidRolYsubsistema(Rol rol,
			Subsistema subsistema) {
		return dao.consultaSubmenusXidRolYsubsistema(rol, subsistema);
	}
}
