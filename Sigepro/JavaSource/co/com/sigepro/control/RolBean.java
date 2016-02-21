package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.control.util.ListaGenerica;
import co.com.sigepro.control.util.PickListConverter;
import co.com.sigepro.entidades.Accion;
import co.com.sigepro.entidades.Pagina;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.entidades.Rol;
import co.com.sigepro.entidades.RolPaginaAccion;
import co.com.sigepro.negocio.serv.AccionServicio;
import co.com.sigepro.negocio.serv.PaginaServicio;
import co.com.sigepro.negocio.serv.RolServicio;

@Controller
@Scope("session")
public class RolBean {
	private Rol rol = new Rol();
	private Persona persona = new Persona();
	private List<Persona> personas = new ArrayList<Persona>();
	private List<Pagina> paginas1 = new ArrayList<Pagina>();
	private List<Pagina> paginasTmp1 = new ArrayList<Pagina>();

	//private RolPaginaAccion rolPaginaAccion = new RolPaginaAccion();
	//private List<RolPaginaAccion> rolesPaginas = new ArrayList<RolPaginaAccion>();
	private ListaGenerica<Rol> roles = new ListaGenerica<Rol>();
	private ListaGenerica<Pagina> paginas = new ListaGenerica<Pagina>();
	
	private ListaGenerica<Pagina> paginasTmp = new ListaGenerica<Pagina>();	
	public ListaGenerica<Pagina> getPaginasTmp() {
		return paginasTmp;}
	public void setPaginasTmp(ListaGenerica<Pagina> paginasTmp) {
		this.paginasTmp = paginasTmp;}
	
	private ListaGenerica<Accion> acciones = new ListaGenerica<Accion>();
	private ListaGenerica<Accion> accionesSeleccionadas = new ListaGenerica<Accion>();
	private PickListConverter pickListConverter;

	@Autowired
	private RolServicio rolServicio;
	@Autowired
	private PaginaServicio paginaServicio;
	@Autowired
	private AccionServicio accionServicio;
	private List<Accion> acciones1 = new ArrayList<Accion>();

	@PostConstruct
	public void inicializacion() {
		paginas1 = paginaServicio.listado(null);
		paginas = new ListaGenerica<Pagina>(paginaServicio.listado(null), "id", "nombre");
		paginas.mostrarItemNulo(false);
		paginasTmp = paginas;
		paginasTmp.mostrarItemNulo(false);
		
		for (int i=0;i<paginas.getLista().size();i++)
		{paginasTmp.getLista().remove(paginas.getLista().get(i));
		}
		paginasTmp1 = paginas1;
		acciones = new ListaGenerica<Accion>(accionServicio.listado(null), "id", "nombre");
		roles = new ListaGenerica<Rol>(rolServicio.listado(null), "id", "nombre");
		roles.mostrarItemNulo(false);		
	}

	public String cargar() {
		if (roles.getIdSeleccionado() != -1
				|| roles.getIdSeleccionado() != null) {
			rol = rolServicio.cargar(roles.getIdSeleccionado());
			//rolesPaginas = rol.getRolPaginaAcciones();
			
			//rol.getRolPaginaAcciones()x
			
			//rolesPaginas.setLista(rol.getRolPaginaAcciones().subList(0, rol.getRolPaginaAcciones().size()));			
		    acciones1 = new ArrayList<Accion>();
			for (RolPaginaAccion rolPaginaAccion : rol.getRolPaginaAcciones()) {
				acciones1.add(rolPaginaAccion.getId().getAccion());
			}
			pickListConverter = new PickListConverter(accionServicio.listado(null));
			accionesSeleccionadas = new ListaGenerica<Accion>(acciones1, "id", "nombre");
		}
		return "";
	}

	public String crear() {
		limpiar();
		return ConstantesNavegacion.CREAR_ROL;
	}
	
	public String guardar() {
		rol = rolServicio.guardar(rol);
		FacesUtils.agregarMensajeInformacion("msg.editarCorrecto", true);
		return ConstantesNavegacion.NO_ACCION;
	}

	public String crearAceptar() {
		rol = rolServicio.guardar(rol);
		FacesUtils.agregarMensajeInformacion("msg.crearCorrecto", true);
		inicializacion();
		return ConstantesNavegacion.CONSULTAR_ROL;
	}
	
	public String crearCancelar() {

		return ConstantesNavegacion.CONSULTAR_ROL;
	}
	public String usuarioBorrar(){
		return "";
	}	
	
	
	

	public String consultar() {
		personas = new ArrayList<Persona>();
		//personas = PersonaServicio.listado(Persona);
		return "";
	}
	
	
	
	
	
	public String limpiar() {
		rol = new Rol();
		return "";
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public ListaGenerica<Accion> getAccionesSeleccionadas() {
		return accionesSeleccionadas;
	}

	public void setAccionesSeleccionadas(ListaGenerica<Accion> accionesSeleccionadas) {
		this.accionesSeleccionadas = accionesSeleccionadas;
	}

	public List<Accion> getAcciones1() {
		return acciones1;
	}

	public void setAcciones1(List<Accion> acciones1) {
		this.acciones1 = acciones1;
	}

	public PickListConverter getPickListConverter() {
		return pickListConverter;
	}

	public void setPickListConverter(PickListConverter pickListConverter) {
		this.pickListConverter = pickListConverter;
	}

	public ListaGenerica<Rol> getRoles() {
		return roles;
	}

	public void setRoles(ListaGenerica<Rol> roles) {
		this.roles = roles;
	}

	public ListaGenerica<Pagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(ListaGenerica<Pagina> paginas) {
		this.paginas = paginas;
	}

	public ListaGenerica<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(ListaGenerica<Accion> acciones) {
		this.acciones = acciones;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	public List<Pagina> getPaginas1() {
		return paginas1;
	}
	public void setPaginas1(List<Pagina> paginas1) {
		this.paginas1 = paginas1;
	}
	public List<Pagina> getPaginasTmp1() {
		return paginasTmp1;
	}
	public void setPaginasTmp1(List<Pagina> paginasTmp1) {
		this.paginasTmp1 = paginasTmp1;
	}
}
