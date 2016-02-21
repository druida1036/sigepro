package co.com.sigepro.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.control.util.ListaGenerica;
import co.com.sigepro.entidades.Cliente;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.negocio.serv.ClienteServicio;
import co.com.sigepro.negocio.serv.PersonaServicio;

@Controller
@Scope("session")
public class PersonaBean {
	private Persona persona = new Persona();
	private ListaGenerica<Cliente> clientes;
	@Autowired
	private ClienteServicio clienteServicio;
	@Autowired
	private PersonaServicio personaServicio;

	public String verInicializar() {

		return ConstantesNavegacion.DETALLE_PERSONA;
	}

	public String crearInicializar() {
		persona = new Persona();
		persona.setCliente(new Cliente());
		clientes = new ListaGenerica<Cliente>(clienteServicio.listado(null), "id", "nombre");
		return ConstantesNavegacion.CREAR_PERSONA;
	}

	public String crearAceptar() {
		persona = personaServicio.guardar(persona);
		FacesUtils.agregarMensajeInformacion("msg.crearCorrecto", true);
		return ConstantesNavegacion.CONSULTA_PERSONA;
	}

	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_PERSONA;
	}

	public String crearCancelar() {

		return ConstantesNavegacion.CONSULTA_PERSONA;
	}

	public String editarInicializar() {
		clientes = new ListaGenerica<Cliente>(clienteServicio.listado(null), "id", "nombre");
		return ConstantesNavegacion.EDITAR_PERSONA;
	}

	public String editarAceptar() {
		persona = personaServicio.guardar(persona);
		FacesUtils.agregarMensajeInformacion("msg.editarCorrecto", true);
		return ConstantesNavegacion.CONSULTA_PERSONA;
	}

	public String editarCancelar() {
		persona = personaServicio.cargar(persona.getId());
		return ConstantesNavegacion.CONSULTA_PERSONA;
	}

	public String limpiar() {
		persona = new Persona();
		return "";
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public ListaGenerica<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ListaGenerica<Cliente> clientes) {
		this.clientes = clientes;
	}

}
