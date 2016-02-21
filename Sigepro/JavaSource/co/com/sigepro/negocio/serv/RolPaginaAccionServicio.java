package co.com.sigepro.negocio.serv;

import java.util.List;

import co.com.sigepro.entidades.Rol;
import co.com.sigepro.entidades.RolPaginaAccion;
import co.com.sigepro.entidades.RolPaginaAccionPK;
import co.com.sigepro.entidades.Subsistema;

public interface RolPaginaAccionServicio {
	public List<RolPaginaAccion> listado(RolPaginaAccion rolPaginaAccion);
	public RolPaginaAccion cargar(RolPaginaAccionPK id);
	public List<RolPaginaAccion> consultaAccionPaginaRolXRolYSubsistema(
			Rol rol, Subsistema subsistema);
}
