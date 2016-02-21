package co.com.sigepro.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

/**
 * Tabla: subsistema
 * 
 * @author Jorge Armando Martinez (jamartinez@aliaddos.com)
 * @version 1.0 <
 */
@Entity
public class Subsistema extends EntidadGenerica {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 6014650811045517896L;
	@Id
	private Integer id;
	private String nombre;
	private String descripcion;
	@OneToMany(mappedBy = "subsistema")
	@ForeignKey(name = "subsistema_menu_fk")
	@Fetch(FetchMode.SELECT)
	private List<Menu> menus;

	/**
	 * Columna: id_subsistema
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Columna: id_subsistema
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Columna: nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Columna: nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Columna: descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Columna: descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	

}
