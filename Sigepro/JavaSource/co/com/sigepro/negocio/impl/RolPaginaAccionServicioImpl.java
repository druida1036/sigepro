package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Rol;
import co.com.sigepro.entidades.RolPaginaAccion;
import co.com.sigepro.entidades.RolPaginaAccionPK;
import co.com.sigepro.entidades.Subsistema;
import co.com.sigepro.negocio.serv.RolPaginaAccionServicio;
import co.com.sigepro.persistencia.dao.RolPaginaAccionDao;

@Service("rolPaginaAccionServicio")
public class RolPaginaAccionServicioImpl implements RolPaginaAccionServicio {
	@Autowired
	private RolPaginaAccionDao dao;
	
	public List<RolPaginaAccion> listado(RolPaginaAccion rolPaginaAccion){
		return dao.listado(rolPaginaAccion);
	}
	
	public RolPaginaAccion cargar(RolPaginaAccionPK id){
		return dao.cargar(id);
	}

	public List<RolPaginaAccion> consultaAccionPaginaRolXRolYSubsistema(
			Rol rol, Subsistema subsistema) {
		return dao.consultaAccionPaginaRolXRolYSubsistema(rol,subsistema);
	}

}