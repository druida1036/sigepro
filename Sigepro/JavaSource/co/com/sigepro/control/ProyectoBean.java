package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.html.HtmlDataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.control.util.ListaGenerica;
import co.com.sigepro.entidades.Actividad;
import co.com.sigepro.entidades.Cliente;
import co.com.sigepro.entidades.Metodologia;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.entidades.Proyecto;
import co.com.sigepro.entidades.Recurso;
import co.com.sigepro.entidades.Rol;
import co.com.sigepro.negocio.serv.ActividadServicio;
import co.com.sigepro.negocio.serv.ClienteServicio;
import co.com.sigepro.negocio.serv.MetodologiaServicio;
import co.com.sigepro.negocio.serv.ProyectoServicio;
import co.com.sigepro.negocio.serv.RecursoServicio;
import co.com.sigepro.negocio.serv.RolServicio;
import co.com.sigepro.negocio.utilidades.Utilidades;

@Controller
@Scope("session")
public class ProyectoBean {
	private static final String ROL_DIRECTOR = "Director de Proyecto";
	private static final String PROYECTO_NO_SELECCIONADO = "error.validacion.proyectoNoSeleccionado";
	private Proyecto proyecto = new Proyecto();
	private Persona persona = new Persona();
	private String[] etiquetasEstadosProyecto = { "En planeacion",
			"En ejecucion", "Cerrado" };
	private ListaGenerica<Persona> listadoDirectores = new ListaGenerica<Persona>();
	private ListaGenerica<Metodologia> listadoMetodologias = new ListaGenerica<Metodologia>();
	private ListaGenerica<Cliente> listadoClientes = new ListaGenerica<Cliente>();
	private ListaGenerica<Persona> listadoUsuarios = new ListaGenerica<Persona>();
	private ListaGenerica<Recurso> listadoRecursos = new ListaGenerica<Recurso>();
	private ListaGenerica<String> estadosProyecto;
	private List<Actividad> actividades = new ArrayList<Actividad>();
	private HtmlDataTable tablaActividades;
	@Autowired
	private ProyectoServicio proyectoServicio;
	@Autowired
	private RolServicio rolServicio;
	@Autowired
	private MetodologiaServicio metodologiaServicio;
	@Autowired
	private ClienteServicio clienteServicio;
	@Autowired
	private ActividadServicio actividadServicio;
	@Autowired
	private RecursoServicio recursoServicio;
	@Autowired
	private MetodologiaBean metodologiaBean;
	@Autowired
	private GanttBean ganttBean;
	@Autowired
	private IndicadoresBean indicadoresBean;

	private int diferenciaFechaAct;
	private int indiceFechaAct;
	private List<Persona> personasPorCliente;
	private String vistaProyecto;

	@PostConstruct
	public void inicializarProyectoBean() {
		Rol rol = new Rol();
		Metodologia metodologia = new Metodologia();
		metodologia.setCtlEstado("2");
		rol.setNombre(ROL_DIRECTOR);
		List<Rol> roles = rolServicio.listado(rol);
		if (!roles.isEmpty()) {
			rol = roles.get(0);
		}
		rol = rolServicio.cargar(rol.getId());
		listadoDirectores = new ListaGenerica<Persona>(rol.getPersonas(), "id",
				"nombre");
		listadoDirectores.mostrarItemNulo(false);
		listadoMetodologias = new ListaGenerica<Metodologia>(
				metodologiaServicio.metodologiasConcretas(null), "id", "nombre");
		listadoMetodologias.mostrarItemNulo(false);
		List<Cliente> clientes = clienteServicio.listado(null);
		listadoClientes = new ListaGenerica<Cliente>(clientes, "id", "nombre");

		listadoRecursos = new ListaGenerica<Recurso>(
				recursoServicio.listado(null), "id", "usuario.nombre");
		estadosProyecto = new ListaGenerica<String>(etiquetasEstadosProyecto);

		if (!clientes.isEmpty() && clientes.get(0) != null) {
			Integer id = clientes.get(0).getId();
			Cliente cliente = clienteServicio.cargar(id);
			if(cliente != null){
				personasPorCliente = cliente.getPersonas();
				listadoUsuarios = new ListaGenerica<Persona>(personasPorCliente,
						"id", "nombre");
				listadoUsuarios.mostrarItemNulo(false);
			}else{
				listadoUsuarios.mostrarItemNulo(true);
			}
			
		}
		listadoClientes.mostrarItemNulo(false);
		estadosProyecto.mostrarItemNulo(false);
	}

	public String verInicializar() {

		return ConstantesNavegacion.DETALLE_PROYECTO;
	}

	public String crearInicializar() {
		inicializarProyectoBean();
		proyecto = new Proyecto();

		return ConstantesNavegacion.CREAR_PROYECTO;
	}

	public String crearAceptar() {
		java.util.Date fechaActual = new java.util.Date();
		proyecto.setCtlFecAlta(fechaActual);
		proyecto.setPersona(listadoDirectores.getObjetoSeleccionado());
		proyectoServicio.agregarMetodologia(proyecto,
				listadoMetodologias.getObjetoSeleccionado());
		proyecto.setCtlEstado(estadosProyecto.getObjetoSeleccionado());
		proyecto = proyectoServicio.guardar(proyecto);
		FacesUtils.agregarMensajeInformacion("msg.crearCorrecto", true);
		return ConstantesNavegacion.CONSULTA_PROYECTO;
	}

	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_PROYECTO;
	}

	public String crearCancelar() {

		return ConstantesNavegacion.CONSULTA_PROYECTO;
	}

	public String editarInicializar() {
		inicializarProyectoBean();

		proyecto = proyectoServicio.cargar(proyecto.getId());
		actividades = actividadServicio.actividadesPorMetodologia(proyecto
				.getMetodologia().getId());
		listadoDirectores.setIdSeleccionado(proyecto.getPersona().getId());
		listadoMetodologias.setIdSeleccionado(proyecto.getMetodologia()
				.getMetodologia().getId());
		ganttBean.setProyecto(proyecto);
		for (int i = 0; i < etiquetasEstadosProyecto.length; i++) {
			if (etiquetasEstadosProyecto[i].equals(proyecto.getCtlEstado())) {
				estadosProyecto.setIdSeleccionado(new Integer(i));
			}
		}
		ganttBean.inicializarDiagrama();
		return ConstantesNavegacion.EDITAR_PROYECTO;
	}

	public String editarAceptar() {
		proyecto.setCtlEstado(estadosProyecto.getObjetoSeleccionado());
		proyecto.setPersona(listadoDirectores.getObjetoSeleccionado());
		proyectoServicio.agregarMetodologia(proyecto,
				listadoMetodologias.getObjetoSeleccionado());
		if (proyecto.getCtlEstado().equals(etiquetasEstadosProyecto[1])) {
			metodologiaServicio.actualizarLineaEjecucion(proyecto
					.getMetodologia().getId());
		}
		proyecto = proyectoServicio.guardar(proyecto);
		FacesUtils.agregarMensajeInformacion("msg.editarCorrecto", true);
		metodologiaBean.editarCancelar();
		return ConstantesNavegacion.CONSULTA_PROYECTO;
	}

	public String editarCancelar() {
		if (proyecto != null) {
			// borrar cualquier cambio no guardado
			proyecto = proyectoServicio.cargar(proyecto.getId());
			proyecto = new Proyecto();
		}
		metodologiaBean.editarCancelar();
		return ConstantesNavegacion.CONSULTA_PROYECTO;
	}

	public String limpiar() {
		proyecto = new Proyecto();
		return ConstantesNavegacion.NO_ACCION;
	}

	public String avanceActividadesEditar() {
		proyecto.setCtlEstado(estadosProyecto.getObjetoSeleccionado());
		proyecto.setPersona(listadoDirectores.getObjetoSeleccionado());
		proyectoServicio.agregarMetodologia(proyecto,
				listadoMetodologias.getObjetoSeleccionado());
		if (proyecto.getCtlEstado().equals(etiquetasEstadosProyecto[1])) {
			metodologiaServicio.actualizarLineaEjecucion(proyecto
					.getMetodologia().getId());
		}
		proyecto = proyectoServicio.guardar(proyecto);
		FacesUtils.agregarMensajeInformacion("msg.editarCorrecto", true);
		metodologiaBean.editarCancelar();
		return ConstantesNavegacion.NO_ACCION;
	}

	public String avanceActividadesCancelar() {
		if (proyecto != null) {
			proyecto = proyectoServicio.cargar(proyecto.getId());
		}
		return ConstantesNavegacion.EDITAR_PROYECTO;
	}

	public String cargarVistaArbol() {
		if (proyecto.getId() == null) {
			FacesUtils.agregarMensajeAdvertencia(PROYECTO_NO_SELECCIONADO);
			return ConstantesNavegacion.NO_ACCION;
		} else {
			metodologiaBean.setMetodologia(proyecto.getMetodologia());
			if (proyecto.getCtlEstado().equals("En planeacion")) {
				metodologiaBean.setProyectoEnPlaneacion(true);
				metodologiaBean.setVistaMetodologia("P");
			} else {
				metodologiaBean.setProyectoEnPlaneacion(false);
				metodologiaBean.setVistaMetodologia("E");

			}
			return metodologiaBean.cargarInicializar();
		}
	}

	public String cargarVistaPrecedencia() {
		if (proyecto.getId() == null) {
			FacesUtils.agregarMensajeAdvertencia(PROYECTO_NO_SELECCIONADO);
			return ConstantesNavegacion.NO_ACCION;
		} else {
			metodologiaBean.setMetodologia(proyecto.getMetodologia());
			if (proyecto.getCtlEstado().equals("En planeacion")) {
				metodologiaBean.setProyectoEnPlaneacion(true);
				metodologiaBean.setVistaMetodologia("P");
			} else {
				metodologiaBean.setProyectoEnPlaneacion(false);
				metodologiaBean.setVistaMetodologia("E");

			}
			return metodologiaBean.verPrecedenciasInicializar();
		}
	}

	public String cargarVistaTabla() {
		if (proyecto.getId() == null) {
			FacesUtils.agregarMensajeAdvertencia(PROYECTO_NO_SELECCIONADO);
			return ConstantesNavegacion.NO_ACCION;
		} else {
			ganttBean.setProyecto(proyecto);
			if (proyecto.getCtlEstado().equals("En planeacion")) {
				vistaProyecto = "P";
			} else {
				vistaProyecto = "E";
			}
			return ConstantesNavegacion.GESTIONAR_ACTIVIDADES;
		}
	}

	public String cargarIndicadores() {
		if (proyecto.getId() == null) {
			FacesUtils.agregarMensajeAdvertencia(PROYECTO_NO_SELECCIONADO);
			return ConstantesNavegacion.NO_ACCION;
		} else {
			indicadoresBean.setProyecto(proyecto);

			return indicadoresBean.cargarIndicadores();
		}
	}

	public void actualizarUsuariosPorCliente(ValueChangeEvent event) {

		Integer identificardor = listadoClientes.getDatos()
				.get(event.getNewValue()).getId();
		Cliente clienteSelecionado = clienteServicio.cargar(identificardor);
		personasPorCliente = clienteSelecionado.getPersonas();
		if (!personasPorCliente.isEmpty()) {
			personasPorCliente.removeAll(proyecto.getPersonas());
			listadoUsuarios = new ListaGenerica<Persona>(personasPorCliente,
					"id", "nombre");
			if (!personasPorCliente.isEmpty()) {
				listadoUsuarios.setIdSeleccionado(personasPorCliente.get(0)
						.getId());
			}
			listadoUsuarios.mostrarItemNulo(false);
		} else {
			listadoUsuarios = new ListaGenerica<Persona>();
			listadoUsuarios.mostrarItemNulo(true);
		}
	}

	public void agregarUsuario() {
		if (listadoUsuarios.getIdSeleccionado() != null
				&& listadoUsuarios.getIdSeleccionado() != -1) {
			Persona persona = listadoUsuarios.getObjetoSeleccionado();
			if (!proyecto.getPersonas().contains(persona)) {
				proyecto.getPersonas().add(persona);
			}
		}
	}

	public void eliminarUsuario() {
		proyecto.getPersonas().remove(persona);
	}

	public void calcularDifFecAct(ValueChangeEvent event) {

		Actividad actividadSeleccionada = (Actividad) tablaActividades
				.getRowData();
		GregorianCalendar fechaInicio = (GregorianCalendar) Calendar
				.getInstance();
		fechaInicio.setTime(actividadSeleccionada.getFechaInicio());
		GregorianCalendar fechaFinal = (GregorianCalendar) Calendar
				.getInstance();
		fechaFinal.setTime((Date) event.getNewValue());
		diferenciaFechaAct = Utilidades.restaFechas(fechaInicio, fechaFinal);
		indiceFechaAct = actividades.indexOf(actividadSeleccionada) + 1;

	}

	public void ajustarFechaIniActividades() {
		for (int i = indiceFechaAct; i < actividades.size(); i++) {
			Actividad actividad = actividades.get(i);
			Date nuevaFechaInicio = Utilidades.sumarHoras(
					actividad.getFechaInicio(), diferenciaFechaAct * 24);
			actividad.setFechaInicio(nuevaFechaInicio);
		}
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public ListaGenerica<Persona> getListadoDirectores() {
		return listadoDirectores;
	}

	public void setListadoDirectores(ListaGenerica<Persona> listadoDirectores) {
		this.listadoDirectores = listadoDirectores;
	}

	public ListaGenerica<Metodologia> getListadoMetodologias() {
		return listadoMetodologias;
	}

	public void setListadoMetodologias(
			ListaGenerica<Metodologia> listadoMetodologias) {
		this.listadoMetodologias = listadoMetodologias;
	}

	public ListaGenerica<Cliente> getListadoClientes() {
		return listadoClientes;
	}

	public void setListadoClientes(ListaGenerica<Cliente> listadoClientes) {
		this.listadoClientes = listadoClientes;
	}

	public ListaGenerica<Persona> getListadoUsuarios() {
		return listadoUsuarios;
	}

	public void setListadoUsuarios(ListaGenerica<Persona> listadoUsuarios) {
		this.listadoUsuarios = listadoUsuarios;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public ListaGenerica<Recurso> getListadoRecursos() {
		return listadoRecursos;
	}

	public void setListadoRecursos(ListaGenerica<Recurso> listadoRecursos) {
		this.listadoRecursos = listadoRecursos;
	}

	public ListaGenerica<String> getEstadosProyecto() {
		return estadosProyecto;
	}

	public void setEstadosProyecto(ListaGenerica<String> estadosProyecto) {
		this.estadosProyecto = estadosProyecto;
	}

	public HtmlDataTable getTablaActividades() {
		return tablaActividades;
	}

	public void setTablaActividades(HtmlDataTable tablaActividades) {
		this.tablaActividades = tablaActividades;
	}

	public String getVistaProyecto() {
		return vistaProyecto;
	}

	public void setVistaProyecto(String vistaProyecto) {
		this.vistaProyecto = vistaProyecto;
	}

}
