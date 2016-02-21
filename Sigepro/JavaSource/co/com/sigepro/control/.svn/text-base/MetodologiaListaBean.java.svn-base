package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.entidades.Metodologia;
import co.com.sigepro.negocio.serv.MetodologiaServicio;

@Controller
@Scope("session")
public class MetodologiaListaBean {
	private Metodologia metodologia = new Metodologia();
	private boolean busquedaExacta = false;
	private List<Metodologia> metodologias = new ArrayList<Metodologia>();
	@Autowired
	private MetodologiaServicio metodologiaServicio;
	@Autowired
	private MetodologiaBean metodologiaBean;
	public String consultar() {
		metodologias = new ArrayList<Metodologia>();
		metodologia.setAvance(null);
		metodologias = metodologiaServicio.listado(metodologia);
		return "";
	}

	public String ver() {

		
		metodologiaBean.setMetodologia(metodologia);

		limpiar();

		return metodologiaBean.verInicializar();
	}
	public String verPrecedencias() {
		metodologiaBean.setMetodologia(metodologia);
		limpiar();

		return metodologiaBean.verPrecedenciasInicializar();
	}
	public String crear() {

		limpiar();

		return metodologiaBean.crearInicializar();
	}
	public String editar() {
		metodologiaBean.setMetodologia(metodologia);
		limpiar();

		return metodologiaBean.editarInicializar();
	}
	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_METODOLOGIA;
	}

	public String editarInicializar() {

		return ConstantesNavegacion.EDITAR_METODOLOGIA;
	}
	public String limpiar() {
		metodologia = new Metodologia();
		metodologias = new ArrayList<Metodologia>();
		return ConstantesNavegacion.NO_ACCION;
	}
	public Metodologia getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}

	public boolean getBusquedaExacta() {
		return busquedaExacta;
	}

	public void setBusquedaExacta(boolean busquedaExacta) {
		this.busquedaExacta = busquedaExacta;
	}

	public List<Metodologia> getMetodologias() {
		return metodologias;
	}

	public void setMetodologias(List<Metodologia> metodologias) {
		this.metodologias = metodologias;
	}
	public int getNumeroResultados() {
		return this.metodologias != null ? this.metodologias.size() : 0;
	}

}
