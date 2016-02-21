package co.com.sigepro.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the propuestas database table.
 * 
 */
@Entity
@Table(name = "propuestas")
public class Propuesta extends EntidadGenerica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PROPUESTAS_ID_GENERATOR", sequenceName = "PROPUESTAS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROPUESTAS_ID_GENERATOR")
	private Integer id;

	private String descripcion;

	private String nombre;

	// bi-directional many-to-one association to Documento
	@ManyToOne
	private Documento documento;

	public Propuesta() {
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

	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

}