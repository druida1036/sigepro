package co.com.sigepro.persistencia.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Subsistema;
import co.com.sigepro.persistencia.dao.SubsistemaDao;

@Repository("SubsistemaDao")
public class SubsistemaDAOImpl extends
		AbstractHibernateDaoImpl<Subsistema, Integer> implements SubsistemaDao {
	
	private static final String SUBSISTEMAS_POR_ROL = 
		"SELECT DISTINCT e FROM RolPaginaAccion  a, Pagina b, Submenu c, Menu d, Subsistema e, Accion f "+
        "WHERE a.id.pagina.id = b.id and "+
		"c.id = b.submenu.id and "+
		"d.id = c.menu.id and "+
		"a.id.accion.id = f.id and "+
		"f.ctlEstado = :estado and "+
		"b.ctlEstado = :estado and "+
		"c.ctlEstado = :estado and "+
		"d.ctlEstado = :estado and "+
		"e.ctlEstado = :estado and "+
		"e.id = d.subsistema.id and "+
		"a.id.rol.id = :id";

	public SubsistemaDAOImpl() {
		super();
	}

	
	public List<Subsistema> getSubsistemasXRol(Integer id) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("estado", "1");
		return ejecutarConsulta(SUBSISTEMAS_POR_ROL, parametros);
	}

	

}