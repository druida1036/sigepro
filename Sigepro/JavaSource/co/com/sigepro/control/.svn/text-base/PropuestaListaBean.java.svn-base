package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.entidades.Propuesta;
import co.com.sigepro.negocio.serv.PropuestaServicio;

@Controller
@Scope("session")
public class PropuestaListaBean {
	private Propuesta propuesta = new Propuesta();
	private Propuesta propuestaSeleccionado;
	private boolean busquedaExacta = false;
	private List<Propuesta> propuestas = new ArrayList<Propuesta>();
	@Autowired
	private PropuestaServicio propuestaServicio;
	@Autowired
	private PropuestaBean propuestaBean;
	private Date desde;
	private Date hasta;

	public String consultar() {
		propuestas = new ArrayList<Propuesta>();
		propuestas = propuestaServicio.listado(propuesta);
		return "";
	}

	public String ver() {
		propuestaBean.setPropuesta(propuestaSeleccionado);		
		limpiar();
		return propuestaBean.verInicializar();
	}

	public String crear() {
		limpiar();
		return propuestaBean.crearInicializar();
	}

	public String borrar() {
		propuestaBean.setPropuesta(propuestaSeleccionado);
		propuestas.remove(propuestaSeleccionado);
		limpiar();
		return propuestaBean.verBorrar();
	}

	public String verAceptar() {
		return ConstantesNavegacion.CONSULTA_CALENDARIO;
	}

	public String limpiar() {
		propuesta = new Propuesta();
		// propuestas = new ArrayList<Propuesta>();
		return "";
	}

	public Propuesta getPropuesta() {
		return propuesta;
	}

	public void setPropuesta(Propuesta propuesta) {
		this.propuesta = propuesta;
	}

	public Propuesta getPropuestaSeleccionado() {
		return propuestaSeleccionado;
	}

	public void setPropuestaSeleccionado(Propuesta propuestaSeleccionado) {
		this.propuestaSeleccionado = propuestaSeleccionado;
	}

	public boolean getBusquedaExacta() {
		return busquedaExacta;
	}

	public void setBusquedaExacta(boolean busquedaExacta) {
		this.busquedaExacta = busquedaExacta;
	}

	public List<Propuesta> getPropuestas() {
		return propuestas;
	}

	public void setPropuestas(List<Propuesta> propuestas) {
		this.propuestas = propuestas;
	}

	public int getNumeroResultados() {
		return this.propuestas != null ? this.propuestas.size() : 0;
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