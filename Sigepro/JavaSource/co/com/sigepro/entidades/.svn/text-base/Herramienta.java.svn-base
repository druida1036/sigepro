package co.com.sigepro.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the herramientas database table.
 * 
 */
@Entity
@Table(name = "herramientas")
public class Herramienta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "HERRAMIENTAS_ID_GENERATOR", sequenceName = "HERRAMIENTAS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HERRAMIENTAS_ID_GENERATOR")
	private Integer id;

	private String descripcion;

	private String nombre;
	//bi-directional many-to-many association to Actividad
	@ManyToMany(mappedBy="herramientas")
	private List<Actividad> actividades;

	public Herramienta() {
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
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	

}