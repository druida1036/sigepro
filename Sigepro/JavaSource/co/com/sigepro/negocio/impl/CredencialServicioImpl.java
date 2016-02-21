package co.com.sigepro.negocio.impl;

import java.util.List;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Credencial;
import co.com.sigepro.entidades.Menu;
import co.com.sigepro.entidades.Pagina;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.entidades.Rol;
import co.com.sigepro.entidades.RolPaginaAccion;
import co.com.sigepro.entidades.Submenu;
import co.com.sigepro.entidades.Subsistema;
import co.com.sigepro.negocio.serv.CredencialServicio;
import co.com.sigepro.negocio.serv.MenuServicio;
import co.com.sigepro.negocio.serv.PaginaServicio;
import co.com.sigepro.negocio.serv.RolPaginaAccionServicio;
import co.com.sigepro.negocio.serv.SubmenuServicio;

/**
 * @author Jorge Armando Martinez Sanchez(jomartinez@aliaddos.com)
 * 
 */
@Service("credencialServicio")
public class CredencialServicioImpl implements CredencialServicio {

	private static final long serialVersionUID = 147467286662854138L;

	@Resource(name = "submenuServicio")
	private SubmenuServicio submenuService;

	@Resource(name = "menuServicio")
	private MenuServicio menuService;

	@Resource(name = "rolPaginaAccionServicio")
	private RolPaginaAccionServicio rolPaginaAccionServicio;

	@Resource(name = "paginaServicio")
	private PaginaServicio paginaServicio;

	public static final String SEPARADORLLAVE = "#";

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.chartis.portal.negocio.interfaz.CredencialServicio#crearCredencial
	 * (co.com.chartis.portal.entidad.Usuario, co.com.chartis.portal.entidad.Subsistema)
	 */
	@Override
	public Credencial crearCredencial(Persona usuario) {
		Credencial credencial = new Credencial();

		TreeMap<String, Boolean> permisos = new TreeMap<String, Boolean>();
		TreeMap<String, String> reglas = new TreeMap<String, String>();
		Rol rol = usuario.getRols().get(0);
		Subsistema subsistema = usuario.getCredencial().getSubsistema();
		/*
		 * Consulta los menus correspondientes al subsistema y al usuario
		 */
		List<Menu> menus = menuService
				.consultaXrolYsubsistema(rol, subsistema);
		/* Consulta los submenus correspondientes al subsistema y al usuario */
		List<Submenu> submenus = submenuService
				.consultaSubmenusXidRolYsubsistema(rol, subsistema);
		/* Consulta las paginas correspondientes al subsistema y esl usuario */
		List<Pagina> paginas = paginaServicio.consultaPaginaXidRolYsubsistema(
				rol, subsistema);
		/* Consulta las acciones correspondientes al subsistema y esl usuario */
		List<RolPaginaAccion> acciones = rolPaginaAccionServicio
				.consultaAccionPaginaRolXRolYSubsistema(rol, subsistema);
		for (Menu menu : menus) {
			Integer idMenu = menu.getId();
			for (Submenu submenu : submenus) {
				if (submenu.getMenu().getId().compareTo(idMenu) == 0) {
					Integer idSubmenu = submenu.getId();
					for (Pagina pagina : paginas) {
						if (idSubmenu.compareTo(pagina.getSubmenu()
								.getId()) == 0) {
							Integer idPagina = pagina.getId();
							for (RolPaginaAccion rolPaginaAccion : acciones) {
								if (idPagina.compareTo(rolPaginaAccion
										.getId().getPagina().getId()) == 0) {
									/*
									 * Se inserta la accion a la aplicacion correspondiente
									 */
//									pagina.getAcciones().add(
//											rolPaginaAccion.getId().getAccion());
									/*
									 * Se inserta la accion a la lista de permisos
									 */
									permisos.put(idMenu
											+ SEPARADORLLAVE
											+ idSubmenu
											+ SEPARADORLLAVE
											+ idPagina
											+ SEPARADORLLAVE
											+ rolPaginaAccion.getId().getAccion()
													.getId(), true);
									reglas
											.put(pagina.getUrlFisica(),
													idMenu
															+ SEPARADORLLAVE
															+ idSubmenu
															+ SEPARADORLLAVE
															+ idPagina);

								}

							}
							/* inserta las paginas dentro del submenu padre */
//							if (pagina.getSubmenu().getId().compareTo(
//									idSubmenu) == 0) {
//								if (pagina.getVisible().equals("1")) {
//									submenu.getPaginas().add(pagina);
//								}
//							}
						}
					}
//					/* inserta los Submenus dentro del menu padre */
//					if (submenu.getMenu().getId().compareTo(
//							idMenu) == 0) {
//						menu.getSubmenus().add(submenu);
//					}
				}
			}
		}
		credencial.setMenus(menus);
		credencial.setPermisos(permisos);
		credencial.setLlavesPorReglaDeNavegacion(reglas);
		credencial.setSubsistema(subsistema);
		return credencial;
	}

	/*
	 * @seeco.com.chartis.portal.negocio.interfaz.CredencialServicio# crearCredencialUsuarioActivo()
	 */
	@Override
	public Credencial crearCredencialUsuarioActivo() {
		Credencial crearCredencial = crearCredencial(FacesUtils.getUsuario());
		return crearCredencial;
	}

	public boolean consultarPermisoOpcion(Long idAccion) {
		Credencial credencial = FacesUtils.getUsuario().getCredencial();
		return credencial.getPermisos().containsKey(
				credencial.getMenuActual() + SEPARADORLLAVE
						+ credencial.getSubmenuActual() + SEPARADORLLAVE
						+ credencial.getPaginaActual() + SEPARADORLLAVE
						+ idAccion);
	}

	public Persona asignarCredencialUsuario(Subsistema subsistema) {
		Persona usuario = FacesUtils.getUsuario();

		if (null != usuario) {
			usuario.setCredencial(new Credencial());
			usuario.getCredencial().setSubsistema(subsistema);
			usuario.setCredencial(this.crearCredencial(usuario));
			FacesUtils.setUsuario(usuario);

		}
		return usuario;
	}
}
