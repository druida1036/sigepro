package co.com.sigepro.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the paginas database table.
 * 
 */
@Entity
@Table(name = "paginas")
public class Pagina extends EntidadGenerica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PAGINAS_ID_GENERATOR", sequenceName = "PAGINAS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAGINAS_ID_GENERATOR")
	private Integer id;

	private String descripcion;

	private String nombre;

	@OneToMany(mappedBy = "id.pagina")
	private List<RolPaginaAccion> rolPaginaAcciones;

	// bi-directional many-to-one association to Submenu
	@ManyToOne
	private Submenu submenu;
	@OneToMany
	@JoinTable(name = "pagina_accion", joinColumns = { @JoinColumn(name = "pagina_id") }, inverseJoinColumns = { @JoinColumn(name = "accion_id") })
	private List<Accion> acciones;
	
	@Column(name = "url_fisica")
	private String urlFisica;
	private String visible;

	private String url;

	public Pagina() {
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

	public Submenu getSubmenu() {
		return this.submenu;
	}

	public void setSubmenu(Submenu submenu) {
		this.submenu = submenu;
	}

	public List<RolPaginaAccion> getRolPaginaAcciones() {
		return rolPaginaAcciones;
	}

	public void setRolPaginaAcciones(List<RolPaginaAccion> rolPaginaAcciones) {
		this.rolPaginaAcciones = rolPaginaAcciones;
	}

	public List<Accion> getAcciones() {
		return this.acciones;
	}

	public void setAcciones(List<Accion> acciones) {
		this.acciones = acciones;
	}

	public String getUrlFisica() {
		return this.urlFisica;
	}

	public void setUrlFisica(String urlFisica) {
		this.urlFisica = urlFisica;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}