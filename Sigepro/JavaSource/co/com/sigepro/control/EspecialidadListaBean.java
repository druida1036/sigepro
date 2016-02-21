package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.entidades.Especialidad;
import co.com.sigepro.negocio.serv.EspecialidadServicio;

@Controller
@Scope("session")
public class EspecialidadListaBean {
	private Especialidad especialidad = new Especialidad();
	private Especialidad especialidadSeleccionado;
	private boolean busquedaExacta = false;
	private List<Especialidad> especialidades = new ArrayList<Especialidad>();
	@Autowired
	private EspecialidadServicio especialidadServicio;
	@Autowired
	private EspecialidadBean especialidadBean;
	public String consultar() {
		especialidades = new ArrayList<Especialidad>();
		especialidades = especialidadServicio.listado(especialidad);
		return "";
	}

	public String ver() {

		
	especialidadBean.setEspecialidad(especialidadSeleccionado);

		limpiar();

		return especialidadBean.verInicializar();
	}
	public String crear() {

		limpiar();

		return especialidadBean.crearInicializar();
	}
	public String editar() {

		especialidadBean.setEspecialidad(especialidadSeleccionado);

		limpiar();

		return especialidadBean.editarInicializar();
	}
	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_ESPECIALIDAD;
	}

	public String editarInicializar() {

		return ConstantesNavegacion.EDITAR_ESPECIALIDAD;
	}
	public String limpiar() {
		especialidad = new Especialidad();
		//especialidades = new ArrayList<Especialidad>();
		return "";
	}
	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Especialidad getEspecialidadSeleccionado() {
		return especialidadSeleccionado;
	}

	public void setEspecialidadSeleccionado(Especialidad especialidadSeleccionado) {
		this.especialidadSeleccionado = especialidadSeleccionado;
	}

	public boolean getBusquedaExacta() {
		return busquedaExacta;
	}

	public void setBusquedaExacta(boolean busquedaExacta) {
		this.busquedaExacta = busquedaExacta;
	}

	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidad>especialidades) {
		this.especialidades =especialidades;
	}
	public int getNumeroResultados() {
		return this.especialidades != null ? this.especialidades.size() : 0;
	}

}
