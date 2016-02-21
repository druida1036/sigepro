package co.com.sigepro.persistencia.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Pagina;
import co.com.sigepro.entidades.Rol;
import co.com.sigepro.entidades.Subsistema;
import co.com.sigepro.persistencia.dao.PaginaDao;

@Repository("PaginaDao")
public class PaginaDAOImpl extends
		AbstractHibernateDaoImpl<Pagina, Integer> implements PaginaDao {

	private static final String PAGINAS_POR_ROL_Y_SUBSISTEMA = "SELECT DISTINCT b FROM RolPaginaAccion a, Pagina b, Submenu c, Menu d,"+
		"Subsistema e, Accion f WHERE a.id.pagina.id = b.id and "+
		"c.id = b.submenu.id and "+
		"d.id = c.menu.id and "+
		"e.id = d.subsistema.id and "+
		"a.id.accion.id = f.id and "+
		"f.ctlEstado  = :estado and "+
		"b.ctlEstado  = :estado and "+
		"c.ctlEstado  = :estado and "+
		"d.ctlEstado  = :estado and "+
		"e.ctlEstado  = :estado and "+
		"e.id = :idSubsistema and "+
		"a.id.rol.id  = :idRol ";


	public PaginaDAOImpl() {
		super();
	}

	
	public List<Pagina> consultaPaginaXidRolYsubsistema(Rol rol,
			Subsistema subsistema) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idRol", rol.getId());
		parametros.put("idSubsistema", subsistema.getId());
		parametros.put("estado", "1");
		return ejecutarConsulta(PAGINAS_POR_ROL_Y_SUBSISTEMA, parametros);
	}

}