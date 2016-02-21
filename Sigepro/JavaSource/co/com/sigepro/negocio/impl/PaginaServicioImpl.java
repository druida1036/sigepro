package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Pagina;
import co.com.sigepro.entidades.Rol;
import co.com.sigepro.entidades.Subsistema;
import co.com.sigepro.negocio.serv.PaginaServicio;
import co.com.sigepro.persistencia.dao.PaginaDao;

@Service("paginaServicio")
public class PaginaServicioImpl implements PaginaServicio {
	@Autowired
	private PaginaDao dao;
	
	public Pagina cargar(Integer id){
		return dao.cargar(id);
	}

	@Override
	public List<Pagina> listado(Pagina pagina) {
		return dao.listado(pagina);
	}

	public List<Pagina> consultaPaginaXidRolYsubsistema(Rol rol,
			Subsistema subsistema) {
		return dao.consultaPaginaXidRolYsubsistema(rol, subsistema);
	}
}
