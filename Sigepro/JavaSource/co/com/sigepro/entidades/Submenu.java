package co.com.sigepro.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the submenus database table.
 * 
 */
@Entity
@Table(name="submenus")
public class Submenu extends EntidadGenerica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SUBMENUS_ID_GENERATOR", sequenceName="SUBMENUS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUBMENUS_ID_GENERATOR")
	private Integer id;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Pagina
	@OneToMany(mappedBy="submenu")
	private List<Pagina> paginas;

	//bi-directional many-to-one association to Menu
    @ManyToOne
	private Menu menu;

    public Submenu() {
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

	public List<Pagina> getPaginas() {
		return this.paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}
	
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}