package co.com.sigepro.entidades;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the menus database table.
 * 
 */
@Entity
@Table(name="menus")
public class Menu extends EntidadGenerica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MENUS_ID_GENERATOR", sequenceName="MENUS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MENUS_ID_GENERATOR")
	private Integer id;

	private String descripcion;

	private String nombre;
	
	private String icono;
	
	private Integer orden;

	
	//bi-directional many-to-one association to Submenu
	@OneToMany(mappedBy="menu")
	@JoinColumn(name = "menu_id", referencedColumnName = "id")
	private List<Submenu> submenus;
	
	@ManyToOne
	private Subsistema subsistema;

    public Menu() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Submenu> getSubmenus() {
		return this.submenus;
	}

	public void setSubmenus(List<Submenu> submenus) {
		this.submenus = submenus;
	}

	public Subsistema getSubsistema() {
		return subsistema;
	}

	public void setSubsistema(Subsistema subsistema) {
		this.subsistema = subsistema;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
}