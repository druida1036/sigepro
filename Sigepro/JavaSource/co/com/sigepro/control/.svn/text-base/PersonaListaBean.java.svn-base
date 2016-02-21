package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.entidades.Persona;
import co.com.sigepro.negocio.serv.PersonaServicio;

@Controller
@Scope("session")
public class PersonaListaBean {
	private Persona Persona = new Persona();
	private Persona PersonaSeleccionado;
	private boolean busquedaExacta = false;
	private List<Persona> personas = new ArrayList<Persona>();
	@Autowired
	private PersonaServicio PersonaServicio;
	@Autowired
	private PersonaBean PersonaBean;
	@Autowired
	private RecursoBean recursoBean;
	
	public String consultar() {
		personas = new ArrayList<Persona>();
		personas = PersonaServicio.listado(Persona);
		return "";
	}

	public String ver() {

		
		PersonaBean.setPersona(PersonaSeleccionado);

		limpiar();

		return PersonaBean.verInicializar();
	}
	public String crear() {

		limpiar();

		return PersonaBean.crearInicializar();
	}
	public String editar() {

		PersonaBean.setPersona(PersonaSeleccionado);

		limpiar();

		return PersonaBean.editarInicializar();
	}
	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_PERSONA;
	}

	public String editarInicializar() {

		return ConstantesNavegacion.EDITAR_PERSONA;
	}
	public String agregarPersonaRecurso(){
		recursoBean.getRecurso().setUsuario(PersonaSeleccionado);
		return ConstantesNavegacion.NO_ACCION;
	}
	public String limpiar() {
		Persona = new Persona();
		personas = new ArrayList<Persona>();
		return ConstantesNavegacion.NO_ACCION;
	}
	public Persona getPersona() {
		return Persona;
	}

	public void setPersona(Persona Persona) {
		this.Persona = Persona;
	}

	public Persona getPersonaSeleccionado() {
		return PersonaSeleccionado;
	}

	public void setPersonaSeleccionado(Persona PersonaSeleccionado) {
		this.PersonaSeleccionado = PersonaSeleccionado;
	}

	public boolean getBusquedaExacta() {
		return busquedaExacta;
	}

	public void setBusquedaExacta(boolean busquedaExacta) {
		this.busquedaExacta = busquedaExacta;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> Personas) {
		this.personas = Personas;
	}
	public int getNumeroResultados() {
		return this.personas != null ? this.personas.size() : 0;
	}

}
