package co.com.sigepro.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the especialidad database table.
 * 
 */
@Entity
public class Especialidad extends EntidadGenerica {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESPECIALIDAD_ID_GENERATOR", sequenceName="ESPECIALIDAD_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESPECIALIDAD_ID_GENERATOR")
	private Integer id;

	private String grado;

	@Column(unique= true)
	private String nombre;

	//bi-directional many-to-one association to PersonaEspecialidad
	@OneToMany(mappedBy="especialidad")
	private List<PersonaEspecialidad> personaEspecialidads;

    public Especialidad() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getGrado() {
		return this.grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PersonaEspecialidad> getPersonaEspecialidads() {
		return this.personaEspecialidads;
	}

	public void setPersonaEspecialidads(List<PersonaEspecialidad> personaEspecialidads) {
		this.personaEspecialidads = personaEspecialidads;
	}
	
}