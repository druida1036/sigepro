package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.entidades.Recurso;
import co.com.sigepro.negocio.serv.RecursoServicio;

@Controller
@Scope("session")
public class RecursoListaBean {
	private Recurso Recurso = new Recurso();
	private Recurso recursoSeleccionado;
	private boolean busquedaExacta = false;
	private List<Recurso> recursos = new ArrayList<Recurso>();
	@Autowired
	private RecursoServicio RecursoServicio;
	@Autowired
	private RecursoBean recursoBean;
	public String consultar() {
		recursos = new ArrayList<Recurso>();
		recursos = RecursoServicio.listado(Recurso);
		return "";
	}

	public String ver() {

		
		recursoBean.setRecurso(recursoSeleccionado);

		limpiar();

		return recursoBean.verInicializar();
	}
	public String crear() {

		limpiar();

		return recursoBean.crearInicializar();
	}
	public String editar() {

		recursoBean.setRecurso(recursoSeleccionado);

		limpiar();

		return recursoBean.editarInicializar();
	}
	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_RECURSO;
	}

	public String editarInicializar() {

		return ConstantesNavegacion.EDITAR_RECURSO;
	}
	public String limpiar() {
		Recurso = new Recurso();
		recursos.clear();
		return "";
	}
	public Recurso getRecurso() {
		return Recurso;
	}

	public void setRecurso(Recurso Recurso) {
		this.Recurso = Recurso;
	}

	public Recurso getRecursoSeleccionado() {
		return recursoSeleccionado;
	}

	public void setRecursoSeleccionado(Recurso RecursoSeleccionado) {
		this.recursoSeleccionado = RecursoSeleccionado;
	}

	public boolean getBusquedaExacta() {
		return busquedaExacta;
	}

	public void setBusquedaExacta(boolean busquedaExacta) {
		this.busquedaExacta = busquedaExacta;
	}

	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> Recursos) {
		this.recursos = Recursos;
	}
	public int getNumeroResultados() {
		return this.recursos != null ? this.recursos.size() : 0;
	}

}
