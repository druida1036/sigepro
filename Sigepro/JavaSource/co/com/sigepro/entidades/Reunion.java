package co.com.sigepro.entidades;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the reuniones database table.
 * 
 */
@Entity
@Table(name = "reuniones")
public class Reunion extends EntidadGenerica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "REUNIONES_ID_GENERATOR", sequenceName = "REUNIONES_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REUNIONES_ID_GENERATOR")
	private Integer id;

	private String asunto;

	private String descripcion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private String lugar;

	// bi-directional many-to-one association to Acta
	@OneToMany(mappedBy = "reunione", cascade = CascadeType.ALL)
	@Cascade({ org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private List<Acta> actas;

	// bi-directional many-to-many association to Persona
	@ManyToMany()
	@JoinTable(name = "asistentes", joinColumns = { @JoinColumn(name = "reuniones_id") }, inverseJoinColumns = { @JoinColumn(name = "persona_id") })
	private List<Persona> personas = new ArrayList<Persona>();

	public Reunion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return this.lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public List<Acta> getActas() {
		return this.actas;
	}

	public void setActas(List<Acta> actas) {
		this.actas = actas;
	}

	public List<Persona> getPersonas() {
		return this.personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

}