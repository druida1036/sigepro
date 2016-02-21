package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Calendario;
import co.com.sigepro.entidades.Festivo;
import co.com.sigepro.negocio.serv.CalendarioServicio;

@Controller
@Scope("session")
public class CalendarioBean {
	private Calendario calendario = new Calendario();
	private Festivo festivo = new Festivo();
	@Autowired
	private CalendarioServicio calendarioServicio;
	private List<Festivo> festivos = new ArrayList<Festivo>();

	public String verInicializar() {

		return ConstantesNavegacion.DETALLE_CALENDARIO;
	}

	public String crearInicializar() {
		festivos = new ArrayList<Festivo>();
		calendario = new Calendario();
		calendario.setDiasTrabajo(new Boolean[7]);
		return ConstantesNavegacion.CREAR_CALENDARIO;
	}

	public String crearAceptar() {
		java.util.Date fechaActual = new java.util.Date();
		calendario.setCtlFecAlta(fechaActual);
		calendario = calendarioServicio.guardar(calendario);
		FacesUtils.agregarMensajeInformacion("msg.crearCorrecto", true);
		festivos = new ArrayList<Festivo>();
		calendario = new Calendario();
		calendario.setDiasTrabajo(new Boolean[7]);
		return ConstantesNavegacion.CONSULTA_CALENDARIO;
	}

	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_CALENDARIO;
	}

	public String crearCancelar() {

		return ConstantesNavegacion.CONSULTA_CALENDARIO;
	}

	public String editarInicializar() {
		if (calendario.getDiasTrabajo() == null) {
			calendario.setDiasTrabajo(new Boolean[7]);
		}
		Integer id = calendario.getId();
		calendario = new Calendario();
		calendario = calendarioServicio.cargar(id);
		return ConstantesNavegacion.EDITAR_CALENDARIO;
	}

	public String editarAceptar() {
		calendario = calendarioServicio.guardar(calendario);
		FacesUtils.agregarMensajeInformacion("msg.editarCorrecto", true);
		return ConstantesNavegacion.CONSULTA_CALENDARIO;
	}

	public String editarCancelar() {
		return ConstantesNavegacion.CONSULTA_CALENDARIO;
	}

	public String limpiar() {
		festivos = new ArrayList<Festivo>();
		calendario = new Calendario();
		return ConstantesNavegacion.NO_ACCION;
	}

	public String agregarFestivo() {
		calendario.getFestivos().add(festivo);
		festivo = new Festivo();
		return ConstantesNavegacion.EDITAR_CALENDARIO;
	}

	public String eliminarFestivo() {
		calendario.getFestivos().remove(festivo);
		festivo = new Festivo();
		return ConstantesNavegacion.EDITAR_CALENDARIO;
	}

	@PostConstruct
	public void postcostructor() {
		calendario.setDiasTrabajo(new Boolean[7]);
	}

	public List<Festivo> getFestivos() {
		return festivos;
	}

	public void setFestivos(List<Festivo> festivos) {
		this.festivos = festivos;
	}

	public Festivo getFestivo() {
		return festivo;
	}

	public void setFestivo(Festivo festivo) {
		this.festivo = festivo;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

}
