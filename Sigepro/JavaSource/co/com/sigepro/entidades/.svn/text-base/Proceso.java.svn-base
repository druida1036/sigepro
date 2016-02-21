package co.com.sigepro.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.Length;

import co.com.sigepro.persistencia.util.NotWhiteSpace;

/**
 * The persistent class for the procesos database table.
 * 
 */
@Entity
@Table(name = "procesos")
public class Proceso extends EntidadGenerica implements Serializable,
		Cloneable, Comparable<Proceso> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PROCESOS_ID_GENERATOR", sequenceName = "PROCESOS_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCESOS_ID_GENERATOR")
	private Integer id;
	@Length(max = 255)
	private String descripcion;
	@NotWhiteSpace(message = "")
	private String nombre;

	private Integer peso;

	private Double avance = 0d;

	// bi-directional many-to-one association to Actividad
	@OneToMany(mappedBy = "proceso", cascade = CascadeType.ALL)
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	@ForeignKey(name = "procesos_actividad_fk")
	@OrderBy("orden asc")
	private List<Actividad> actividades = new ArrayList<Actividad>();

	@ManyToOne()
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;

	public Proceso() {
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public Proceso clone() throws CloneNotSupportedException {
		Proceso clon = (Proceso) super.clone();
		clon.setActividades(new ArrayList<Actividad>());
		for (Actividad actividad : actividades) {
			clon.getActividades().add(actividad.clone());
		}
		return clon;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public int compareTo(Proceso o) {
		return id < o.getId() ? -1 : id > o.getId() ? 1 : 0;
	}

	public Double getAvance() {
		return avance;
	}

	public void setAvance(Double avance) {
		this.avance = avance;
	}

}