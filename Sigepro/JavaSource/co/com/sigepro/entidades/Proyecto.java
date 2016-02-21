package co.com.sigepro.entidades;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the proyecto database table.
 * 
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Proyecto extends EntidadGenerica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PROYECTO_ID_GENERATOR", sequenceName = "PROYECTO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROYECTO_ID_GENERATOR")
	private Integer id;

	@Column(name = "aprobacion_cierre")
	private String aprobacionCierre;

	@Column(name = "aprobacion_cronograma")
	private String aprobacionCronograma;

	@Column(name = "aprobacion_inicio")
	private String aprobacionInicio;
	
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio")
	private Date fechaInicio;

	@Column(unique = true)
	private String nombre;

	// bi-directional many-to-many association to Persona
	@ManyToMany
	@JoinTable(name = "patrocinadores", joinColumns = { @JoinColumn(name = "proyecto_id") }, inverseJoinColumns = { @JoinColumn(name = "persona_id") })
	private List<Persona> personas = new ArrayList<Persona>();

	@ManyToOne()
	private Persona persona;

	@ManyToOne(cascade = CascadeType.ALL)
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE})	
	private Metodologia metodologia;

	public Proyecto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAprobacionCierre() {
		return this.aprobacionCierre;
	}

	public void setAprobacionCierre(String aprobacionCierre) {
		this.aprobacionCierre = aprobacionCierre;
	}

	public String getAprobacionCronograma() {
		return this.aprobacionCronograma;
	}

	public void setAprobacionCronograma(String aprobacionCronograma) {
		this.aprobacionCronograma = aprobacionCronograma;
	}

	public String getAprobacionInicio() {
		return this.aprobacionInicio;
	}

	public void setAprobacionInicio(String aprobacionInicio) {
		this.aprobacionInicio = aprobacionInicio;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Persona> getPersonas() {
		return this.personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Metodologia getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}

}