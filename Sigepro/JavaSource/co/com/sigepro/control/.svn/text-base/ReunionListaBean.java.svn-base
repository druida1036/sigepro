package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.entidades.Reunion;
import co.com.sigepro.negocio.serv.ReunionServicio;

@Controller
@Scope("session")
public class ReunionListaBean {
	private Reunion reunion = new Reunion();
	private Reunion reunionSeleccionado;
	private boolean busquedaExacta = false;
	private List<Reunion> reuniones = new ArrayList<Reunion>();
	@Autowired
	private ReunionServicio reunionServicio;
	@Autowired
	private ReunionBean reunionBean;
	private Date desde;
	private Date hasta;

	public String consultar() {
		reuniones = new ArrayList<Reunion>();
		reuniones = reunionServicio.listado(reunion);
		return "";
	}

	public String ver() {
		reunionBean.setReunion(reunionSeleccionado);
		
		limpiar();
		return reunionBean.verInicializar();
	}

	public String crear() {
		limpiar();
		return reunionBean.crearInicializar();
	}

	public String editar() {
		reunionBean.setReunion(reunionSeleccionado);
		limpiar();
		return reunionBean.editarInicializar();
	}

	public String verAceptar() {
		return ConstantesNavegacion.CONSULTA_CALENDARIO;
	}

	public String editarInicializar() {
		return ConstantesNavegacion.EDITAR_CALENDARIO;
	}

	public String limpiar() {
		reunion = new Reunion();
		// reunions = new ArrayList<Reunion>();
		return "";
	}

	public Reunion getReunion() {
		return reunion;
	}

	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}

	public Reunion getReunionSeleccionado() {
		return reunionSeleccionado;
	}

	public void setReunionSeleccionado(Reunion reunionSeleccionado) {
		this.reunionSeleccionado = reunionSeleccionado;
	}

	public boolean getBusquedaExacta() {
		return busquedaExacta;
	}

	public void setBusquedaExacta(boolean busquedaExacta) {
		this.busquedaExacta = busquedaExacta;
	}

	public List<Reunion> getReuniones() {
		return reuniones;
	}

	public void setReuniones(List<Reunion> reuniones) {
		this.reuniones = reuniones;
	}

	public int getNumeroResultados() {
		return this.reuniones != null ? this.reuniones.size() : 0;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

}
