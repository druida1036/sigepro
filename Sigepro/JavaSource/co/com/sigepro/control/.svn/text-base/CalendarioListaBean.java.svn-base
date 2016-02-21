package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.entidades.Calendario;
import co.com.sigepro.negocio.serv.CalendarioServicio;

@Controller
@Scope("session")
public class CalendarioListaBean {
	private Calendario calendario = new Calendario();
	private Calendario calendarioSeleccionado;
	private boolean busquedaExacta = false;
	private List<Calendario> calendarios = new ArrayList<Calendario>();
	@Autowired
	private CalendarioServicio calendarioServicio;
	@Autowired
	private CalendarioBean calendarioBean;
	public String consultar() {
		calendarios = new ArrayList<Calendario>();
		calendarios = calendarioServicio.listado(calendario);
		return "";
	}

	public String ver() {

		
	calendarioBean.setCalendario(calendarioSeleccionado);

		limpiar();

		return calendarioBean.verInicializar();
	}
	public String crear() {

		limpiar();

		return calendarioBean.crearInicializar();
	}
	public String editar() {

		calendarioBean.setCalendario(calendarioSeleccionado);

		limpiar();

		return calendarioBean.editarInicializar();
	}
	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_CALENDARIO;
	}

	public String editarInicializar() {

		return ConstantesNavegacion.EDITAR_CALENDARIO;
	}
	public String limpiar() {
		calendario = new Calendario();
		//calendarios = new ArrayList<Calendario>();
		return "";
	}
	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public Calendario getCalendarioSeleccionado() {
		return calendarioSeleccionado;
	}

	public void setCalendarioSeleccionado(Calendario calendarioSeleccionado) {
		this.calendarioSeleccionado = calendarioSeleccionado;
	}

	public boolean getBusquedaExacta() {
		return busquedaExacta;
	}

	public void setBusquedaExacta(boolean busquedaExacta) {
		this.busquedaExacta = busquedaExacta;
	}

	public List<Calendario> getCalendarios() {
		return calendarios;
	}

	public void setCalendarios(List<Calendario>calendarios) {
		this.calendarios =calendarios;
	}
	public int getNumeroResultados() {
		return this.calendarios != null ? this.calendarios.size() : 0;
	}

}
