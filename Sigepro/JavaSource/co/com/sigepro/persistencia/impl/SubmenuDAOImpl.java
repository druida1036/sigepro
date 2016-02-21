package co.com.sigepro.persistencia.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Rol;
import co.com.sigepro.entidades.Submenu;
import co.com.sigepro.entidades.Subsistema;
import co.com.sigepro.persistencia.dao.SubmenuDao;

@Repository("SubMenuDao")
public class SubmenuDAOImpl extends
		AbstractHibernateDaoImpl<Submenu, Integer> implements SubmenuDao {

	private static final String SUBMENUS_POR_ROL_Y_SUBSISTEMA = "SELECT DISTINCT c FROM RolPaginaAccion a, Pagina b, Submenu c, Menu d,"+
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

	public SubmenuDAOImpl() {
		super();
	}

	
	public List<Submenu> consultaSubmenusXidRolYsubsistema(Rol rol,
			Subsistema subsistema) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idRol", rol.getId());
		parametros.put("idSubsistema", subsistema.getId());
		parametros.put("estado", "1");
		return ejecutarConsulta(SUBMENUS_POR_ROL_Y_SUBSISTEMA, parametros);
	}

}