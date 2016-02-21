package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Menu;
import co.com.sigepro.entidades.Rol;
import co.com.sigepro.entidades.Subsistema;
import co.com.sigepro.negocio.serv.MenuServicio;
import co.com.sigepro.persistencia.dao.MenuDao;

@Service("menuServicio")
public class MenuServicioImpl implements MenuServicio {
	@Autowired
	private MenuDao dao;
	
	public List<Menu> listado(Menu menu){
		return dao.listado(menu);
	}
	
	public Menu cargar(Integer id){
		return dao.cargar(id);
	}

	public List<Menu> consultaXrolYsubsistema(Rol rol, Subsistema subsistema) {
		return dao.consultaXrolYsubsistema(rol, subsistema);
	}
}
