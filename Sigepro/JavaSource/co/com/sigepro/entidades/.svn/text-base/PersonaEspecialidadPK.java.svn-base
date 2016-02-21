package co.com.sigepro.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the persona_especialidad database table.
 * 
 */
@Embeddable
public class PersonaEspecialidadPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Especialidad
    @ManyToOne
	private Especialidad especialidad;

	//bi-directional many-to-one association to Persona
    @ManyToOne
	private Persona persona;

    public PersonaEspecialidadPK() {
    }

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((especialidad == null) ? 0 : especialidad.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonaEspecialidadPK other = (PersonaEspecialidadPK) obj;
		if (especialidad == null) {
			if (other.especialidad != null)
				return false;
		} else if (!especialidad.equals(other.especialidad))
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		return true;
	}
	
}