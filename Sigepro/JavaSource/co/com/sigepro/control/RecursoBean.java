package co.com.sigepro.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Recurso;
import co.com.sigepro.negocio.serv.RecursoServicio;

@Controller
@Scope("session")
public class RecursoBean {
	private Recurso recurso = new Recurso();

	@Autowired
	private RecursoServicio recursoServicio;

	public String verInicializar() {

		return ConstantesNavegacion.DETALLE_RECURSO;
	}

	public String crearInicializar() {
		recurso = new Recurso();
		return ConstantesNavegacion.CREAR_RECURSO;
	}

	public String crearAceptar() {
		recurso = recursoServicio.guardar(recurso);
		FacesUtils.agregarMensajeInformacion("msg.crearCorrecto", true);
		return ConstantesNavegacion.CONSULTA_RECURSO;
	}

	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_RECURSO;
	}

	public String crearCancelar() {

		return ConstantesNavegacion.CONSULTA_RECURSO;
	}

	public String editarInicializar() {
		return ConstantesNavegacion.EDITAR_RECURSO;
	}

	public String editarAceptar() {
		recurso = recursoServicio.guardar(recurso);
		FacesUtils.agregarMensajeInformacion("msg.editarCorrecto", true);
		return ConstantesNavegacion.CONSULTA_RECURSO;
	}

	public String editarCancelar() {
		recurso = recursoServicio.cargar(recurso.getId());
		return ConstantesNavegacion.CONSULTA_RECURSO;
	}

	public String limpiar() {
		recurso = new Recurso();
		return "";
	}

	public boolean isAsignadoUsuario() {
		return recurso.getUsuario().getId() == null ? false : true;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

}
