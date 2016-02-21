package co.com.sigepro.control;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Especialidad;
import co.com.sigepro.negocio.serv.EspecialidadServicio;

@Controller
@Scope("session")
public class EspecialidadBean {
	private Especialidad especialidad = new Especialidad();
	@Autowired
	private EspecialidadServicio especialidadServicio;
	

	public String verInicializar() {

		return ConstantesNavegacion.DETALLE_ESPECIALIDAD;
	}

	public String crearInicializar() {

		especialidad = new Especialidad();

		return ConstantesNavegacion.CREAR_ESPECIALIDAD;
	}

	public String crearAceptar() {
		java.util.Date fechaActual = new java.util.Date();
		especialidad.setCtlFecAlta(fechaActual);
		especialidad = especialidadServicio.guardar(especialidad);
		FacesUtils.agregarMensajeInformacion("msg.crearCorrecto", true);
		return ConstantesNavegacion.CONSULTA_ESPECIALIDAD;
	}

	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_ESPECIALIDAD;
	}

	public String crearCancelar() {

		return ConstantesNavegacion.CONSULTA_ESPECIALIDAD;
	}

	public String editarInicializar() {
		especialidad = especialidadServicio.cargar(especialidad.getId());
		return ConstantesNavegacion.EDITAR_ESPECIALIDAD;
	}

	public String editarAceptar() {
		especialidad = especialidadServicio.guardar(especialidad);
		FacesUtils.agregarMensajeInformacion("msg.editarCorrecto", true);
		return ConstantesNavegacion.CONSULTA_ESPECIALIDAD;
	}

	public String editarCancelar() {
		return ConstantesNavegacion.CONSULTA_ESPECIALIDAD;
	}

	public String limpiar() {
		especialidad = new Especialidad();
		return ConstantesNavegacion.NO_ACCION;
	}


	

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	

	@PostConstruct
	public void postcostructor() {
		System.err.println("prueba exitosa");
	}

	
}
