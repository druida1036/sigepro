package co.com.sigepro.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the notificaciones database table.
 * 
 */
@Entity
@Table(name = "notificaciones")
public class Notificacion extends EntidadGenerica {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "NOTIFICACIONES_ID_GENERATOR", sequenceName = "NOTIFICACIONES_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTIFICACIONES_ID_GENERATOR")
	private Integer id;

	private String nombre;
	private String asunto;
	private String descripcion;

	

	// bi-directional many-to-many association to Actividad
	@ManyToMany(mappedBy = "notificaciones")
	private List<Actividad> actividades;
	@ManyToMany
	@JoinTable(name = "variable_notificacion", joinColumns = { @JoinColumn(name = "notificacion_id") }, inverseJoinColumns = { @JoinColumn(name = "variable_id") })
	private List<Variable> variablesNotificacion;

	public Notificacion() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Notificacion))
			return false;
		Notificacion other = (Notificacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<Variable> getVariablesNotificacion() {
		return variablesNotificacion;
	}

	public void setVariablesNotificacion(List<Variable> variablesNotificacion) {
		this.variablesNotificacion = variablesNotificacion;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

}