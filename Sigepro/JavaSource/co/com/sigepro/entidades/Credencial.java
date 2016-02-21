package co.com.sigepro.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.Resource;

import co.com.sigepro.negocio.serv.CredencialServicio;

/**
 * Representa las credenciales de acceso del usuario
 * 
 * @author Jorge Armando Martinez Sanchez(jamatinez@aliaddos.com)
 * 
 */
public class Credencial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8987486421087849419L;
	private TreeMap<String, Boolean> permisos;
	private TreeMap<String, String> llavesPorReglaDeNavegacion;

	/**
	 * Retorna un(a) llavesPorReglaDeNavegacion
	 * 
	 * @return the llavesPorReglaDeNavegacion
	 */
	public TreeMap<String, String> getLlavesPorReglaDeNavegacion() {
		return llavesPorReglaDeNavegacion;
	}

	/**
	 * Inserta un(a) llavesPorReglaDeNavegacion
	 * 
	 * @param llavesPorReglaDeNavegacion
	 *            the llavesPorReglaDeNavegacion to set
	 */
	public void setLlavesPorReglaDeNavegacion(
			TreeMap<String, String> llavesPorReglaDeNavegacion) {
		this.llavesPorReglaDeNavegacion = llavesPorReglaDeNavegacion;
	}

	private Integer menuActual;
	private Integer submenuActual;
	private Integer paginaActual;
	private List<Menu> menus;
	private Subsistema subsistema;

	@Resource(name = "credencialServicio")
	private CredencialServicio credencialServicio;

	/**
	 * Retorna un(a) aplicacionesMenu
	 * 
	 * @return the aplicacionesMenu
	 */
	public List<Menu> getMenus() {
		return menus;
	}

	/**
	 * Inserta un(a) menu
	 * 
	 * @param aplicacionesMenu
	 *            the aplicacionesMenu to set
	 */
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	/**
	 * Retorna un(a) subsistema
	 * 
	 * @return the subsistema
	 */
	public Subsistema getSubsistema() {
		return subsistema;
	}

	/**
	 * Inserta un(a) subsistema
	 * 
	 * @param subsistema
	 *            the subsistema to set
	 */
	public void setSubsistema(Subsistema subsistema) {
		if (this.subsistema != null
				&& !(subsistema.getId().compareTo(this.subsistema.getId()) == 0)) {
			Credencial crearCredencialUsuarioActivo = credencialServicio
					.crearCredencialUsuarioActivo();
			this.menus = crearCredencialUsuarioActivo
					.getMenus();
			this.permisos = crearCredencialUsuarioActivo.getPermisos();
			this.llavesPorReglaDeNavegacion = crearCredencialUsuarioActivo
					.getLlavesPorReglaDeNavegacion();
		}
		this.subsistema = subsistema;
	}

	/**
	 * Retorna un(a) permisos
	 * 
	 * @return the permisos
	 */
	public TreeMap<String, Boolean> getPermisos() {
		return permisos;
	}

	/**
	 * Inserta un(a) permisos
	 * 
	 * @param permisos
	 *            the permisos to set
	 */
	public void setPermisos(TreeMap<String, Boolean> permisos) {
		this.permisos = permisos;
	}

	/**
	 * Retorna un(a) aplicacionActual
	 * 
	 * @return the aplicacionActual
	 */
	public Integer getMenuActual() {
		return menuActual;
	}

	/**
	 * Inserta un(a) menuActual
	 * 
	 * @param menuActual
	 *            the menuActual to set
	 */
	public void setMenuActual(Integer aplicacionActual) {
		this.menuActual = aplicacionActual;
	}

	/**
	 * Inserta un(a) submenuActual
	 * 
	 * @param submenuActual
	 *            the submenuActual to set
	 */
	public Integer getSubmenuActual() {
		return submenuActual;
	}

	/**
	 * Inserta un(a) submenuActual
	 * 
	 * @param submenuActual
	 *            the submenuActual to set
	 */
	public void setSubmenuActual(Integer submenuActual) {
		this.submenuActual = submenuActual;
	}

	/**
	 * Retorna un(a) paginaActual
	 * 
	 * @return the paginaActual
	 */
	public Integer getPaginaActual() {
		return paginaActual;
	}

	/**
	 * Inserta un(a) paginaActual
	 * 
	 * @param paginaActual
	 *            the paginaActual to set
	 */
	public void setPaginaActual(Integer paginaActual) {
		this.paginaActual = paginaActual;
	}

}
