package co.com.sigepro.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.negocio.serv.PersonaServicio;

@Controller
@Scope("session")
public class CuentaBean {

	private Persona cuenta = new Persona();
	
	@Autowired
	private PersonaServicio personaServicio;
		
	public CuentaBean() {
		cuenta = new Persona();
		cuenta = FacesUtils.getUsuario();
	}

	public Persona getCuenta() {
		return cuenta;
	}

	public void setCuenta(Persona cuenta) {
		this.cuenta = cuenta;
	}

	public PersonaServicio getPersonaServicio() {
		return personaServicio;
	}


	public void setPersonaServicio(PersonaServicio personaServicio) {
		this.personaServicio = personaServicio;
	}
}
