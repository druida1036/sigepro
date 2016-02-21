package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.entidades.Proyecto;
import co.com.sigepro.negocio.serv.ProyectoServicio;

@Controller
@Scope("session")
public class ProyectoListaBean {
	private Proyecto proyecto = new Proyecto();

	private boolean busquedaExacta = false;
	private List<Proyecto> proyectos = new ArrayList<Proyecto>();
	@Autowired
	private ProyectoServicio proyectoServicio;
	@Autowired
	private ProyectoBean proyectoBean;

	public String consultar() {
		proyectos = new ArrayList<Proyecto>();
		proyectos = proyectoServicio.listado(proyecto);
		return "";
	}

	public String ver() {

		proyectoBean.setProyecto(proyecto);

		limpiar();

		return proyectoBean.verInicializar();
	}

	public String crear() {

		limpiar();

		return proyectoBean.crearInicializar();
	}

	public String editar() {

		proyectoBean.setProyecto(proyecto);

		limpiar();

		return proyectoBean.editarInicializar();
	}

	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_PROYECTO;
	}

	public String editarInicializar() {

		return ConstantesNavegacion.EDITAR_PROYECTO;
	}

	public String limpiar() {
		proyecto = new Proyecto();
		proyectos = new ArrayList<Proyecto>();
		return "";
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public boolean getBusquedaExacta() {
		return busquedaExacta;
	}

	public void setBusquedaExacta(boolean busquedaExacta) {
		this.busquedaExacta = busquedaExacta;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public int getNumeroResultados() {
		return this.proyectos != null ? this.proyectos.size() : 0;
	}

}
