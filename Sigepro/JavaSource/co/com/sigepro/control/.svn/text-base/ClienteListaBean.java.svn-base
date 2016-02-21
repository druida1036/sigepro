package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.entidades.Cliente;
import co.com.sigepro.negocio.serv.ClienteServicio;

@Controller
@Scope("session")
public class ClienteListaBean {
	private Cliente cliente = new Cliente();
	private Cliente clienteSeleccionado;
	private boolean busquedaExacta = false;
	private List<Cliente> clientes = new ArrayList<Cliente>();
	@Autowired
	private ClienteServicio clienteServicio;
	@Autowired
	private ClienteBean clienteBean;
	public String consultar() {
		clientes = new ArrayList<Cliente>();
		clientes = clienteServicio.listado(cliente);
		return "";
	}

	public String ver() {

		
		clienteBean.setCliente(cliente);

		limpiar();

		return clienteBean.verInicializar();
	}
	public String crear() {

		limpiar();

		return clienteBean.crearInicializar();
	}
	public String editar() {

		clienteBean.setCliente(cliente);

		limpiar();

		return clienteBean.editarInicializar();
	}
	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_CLIENTE;
	}

	public String editarInicializar() {

		return ConstantesNavegacion.EDITAR_CLIENTE;
	}
	public String limpiar() {
		cliente = new Cliente();
		//clientes = new ArrayList<Cliente>();
		return "";
	}
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(Cliente clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

	public boolean getBusquedaExacta() {
		return busquedaExacta;
	}

	public void setBusquedaExacta(boolean busquedaExacta) {
		this.busquedaExacta = busquedaExacta;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public int getNumeroResultados() {
		return this.clientes != null ? this.clientes.size() : 0;
	}

}
