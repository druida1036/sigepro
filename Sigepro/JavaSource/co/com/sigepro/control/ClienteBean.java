package co.com.sigepro.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Cliente;
import co.com.sigepro.negocio.serv.ClienteServicio;

@Controller
@Scope("session")
public class ClienteBean {
	private Cliente cliente = new Cliente();
	@Autowired
	private ClienteServicio clienteServicio;

	public String verInicializar() {

		return ConstantesNavegacion.DETALLE_CLIENTE;
	}

	public String crearInicializar() {

		cliente = new Cliente();

		return ConstantesNavegacion.CREAR_CLIENTE;
	}

	public String crearAceptar() {
		cliente.setId(null);
		java.util.Date fechaActual = new java.util.Date();
		cliente.setCtlFecAlta(fechaActual);
		cliente = clienteServicio.guardar(cliente);
		FacesUtils.agregarMensajeInformacion("msg.crearCorrecto", true);
		return ConstantesNavegacion.CONSULTA_CLIENTE;
	}

	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_CLIENTE;
	}

	public String crearCancelar() {

		return ConstantesNavegacion.CONSULTA_CLIENTE;
	}

	public String editarInicializar() {

		return ConstantesNavegacion.EDITAR_CLIENTE;
	}

	public String editarAceptar() {
		cliente = clienteServicio.guardar(cliente);
		FacesUtils.agregarMensajeInformacion("msg.editarCorrecto", true);
		return ConstantesNavegacion.CONSULTA_CLIENTE;
	}

	public String editarCancelar() {
		return ConstantesNavegacion.CONSULTA_CLIENTE;
	}

	public String guardar() {
		clienteServicio.guardar(cliente);
		cliente = new Cliente();
		FacesUtils.agregarMensajeInformacion("Cliente creado con exito", false);
		return "";
	}

	public String limpiar() {
		cliente = new Cliente();
		return "";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
