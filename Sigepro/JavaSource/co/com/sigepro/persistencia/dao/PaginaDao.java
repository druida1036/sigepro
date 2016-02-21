package co.com.sigepro.persistencia.dao;

import java.util.List;

import co.com.sigepro.entidades.Pagina;
import co.com.sigepro.entidades.Rol;
import co.com.sigepro.entidades.Subsistema;

public interface PaginaDao extends GenericDao<Pagina, Integer> {

	List<Pagina> consultaPaginaXidRolYsubsistema(Rol rol, Subsistema subsistema);

}
