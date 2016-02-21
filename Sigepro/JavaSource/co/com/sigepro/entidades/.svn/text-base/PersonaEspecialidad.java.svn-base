package co.com.sigepro.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the persona_especialidad database table.
 * 
 */
@Entity
@Table(name="persona_especialidad")
public class PersonaEspecialidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonaEspecialidadPK id;

	private Integer meses;

	//bi-directional many-to-one association to Especialidad
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "especialidad_id", insertable = false, updatable = false)
	private Especialidad especialidad;

	//bi-directional many-to-one association to Persona
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", insertable = false, updatable = false)
	private Persona persona;

    public PersonaEspecialidad() {
    }

	public PersonaEspecialidadPK getId() {
		return this.id;
	}

	public void setId(PersonaEspecialidadPK id) {
		this.id = id;
	}
	
	public Integer getMeses() {
		return this.meses;
	}

	public void setMeses(Integer meses) {
		this.meses = meses;
	}

	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
}