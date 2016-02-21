package co.com.sigepro.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ForeignKey;

/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
public class Rol extends EntidadGenerica {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ROL_ID_GENERATOR", sequenceName = "ROL_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROL_ID_GENERATOR")
	private Integer id;

	private String descripcion;

	private String nombre;

	// bi-directional many-to-one association to Actividad
	@OneToMany(mappedBy = "rol")
	@ForeignKey(name = "rol_actividad_fk")
	private List<Actividad> actividades = new ArrayList<Actividad>();

	// bi-directional many-to-many association to Persona
	@ManyToMany
	@JoinTable(name = "rol_persona", joinColumns = { @JoinColumn(name = "rol_id") }, inverseJoinColumns = { @JoinColumn(name = "persona_id") })
	private List<Persona> personas;

	@OneToMany(mappedBy = "id.rol")
	private List<RolPaginaAccion> rolPaginaAcciones;

	public Rol() {
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

	public List<Actividad> getActividades() {
		return this.actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public List<Persona> getPersonas() {
		return this.personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public List<RolPaginaAccion> getRolPaginaAcciones() {
		return rolPaginaAcciones;
	}

	public void setRolPaginaAcciones(List<RolPaginaAccion> rolPaginaAcciones) {
		this.rolPaginaAcciones = rolPaginaAcciones;
	}

}