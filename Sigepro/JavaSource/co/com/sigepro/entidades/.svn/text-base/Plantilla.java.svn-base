package co.com.sigepro.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cascade;

/**
 * The persistent class for the plantilla database table.
 * 
 */
@Entity
public class Plantilla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PLANTILLA_ID_GENERATOR", sequenceName = "PLANTILLA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLANTILLA_ID_GENERATOR")
	private Integer id;

	private String descripcion;

	private String nombre;

	private Boolean obligatoria;

	// bi-directional many-to-one association to Actividad
	@ManyToOne
	@JoinColumn(name = "actividades_id")
	private Actividad actividad;

	// bi-directional many-to-one association to Documento
	@ManyToOne(cascade = CascadeType.ALL)
	@Cascade({ org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private Documento documento;

	

	public Plantilla() {
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

	public Boolean getObligatoria() {
		return this.obligatoria;
	}

	public void setObligatoria(Boolean obligatoria) {
		this.obligatoria = obligatoria;
	}

	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

}