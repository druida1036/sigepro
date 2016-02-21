package co.com.sigepro.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.richfaces.component.UITree;
import org.richfaces.component.html.HtmlTree;
import org.richfaces.component.html.HtmlTreeNode;
import org.richfaces.component.state.TreeState;
import org.richfaces.event.NodeExpandedEvent;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeRowKey;
import org.richfaces.model.UploadItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.control.util.ListaGenerica;
import co.com.sigepro.entidades.Actividad;
import co.com.sigepro.entidades.Categoria;
import co.com.sigepro.entidades.Documento;
import co.com.sigepro.entidades.EntidadGenerica;
import co.com.sigepro.entidades.Herramienta;
import co.com.sigepro.entidades.Metodologia;
import co.com.sigepro.entidades.Nodo;
import co.com.sigepro.entidades.Notificacion;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.entidades.Plantilla;
import co.com.sigepro.entidades.Proceso;
import co.com.sigepro.entidades.Rol;
import co.com.sigepro.negocio.serv.ActividadServicio;
import co.com.sigepro.negocio.serv.HerramientaServicio;
import co.com.sigepro.negocio.serv.MetodologiaServicio;
import co.com.sigepro.negocio.serv.RolServicio;

@Controller
@Scope("session")
public class MetodologiaBean {
	private Metodologia metodologia = new Metodologia();
	private Categoria categoria = new Categoria();
	private Proceso proceso = new Proceso();
	private Actividad actividad = new Actividad();
	private Actividad precedente = new Actividad();
	private Actividad actividadPorAprobar = new Actividad();
	private Plantilla plantilla = new Plantilla();
	private Documento documento = new Documento();
	private Herramienta herramienta = new Herramienta();
	private ListaGenerica<Actividad> listadoPrecedentes;
	private ListaGenerica<Actividad> listadoAprobaciones;
	private ListaGenerica<Herramienta> listadoHerramientas;
	private ListaGenerica<Rol> listadoRoles;
	private Notificacion notificacion = new Notificacion();
	private TreeNodoImpl raiz = null;
	private TreeNodoImpl nodoSeleccionado = new TreeNodoImpl();
	private UITree tree;
	private boolean mostarModActividad = false;
	private boolean vistaProyecto = false;
	private boolean proyectoEnPlaneacion = false;
	private String vistaMetodologia = "M";

	@Autowired
	private MetodologiaServicio metodologiaServicio;
	@Autowired
	private ActividadServicio actividadServicio;
	@Autowired
	private HerramientaServicio herramientaServicio;
	@Autowired
	private RolServicio rolServicio;

	private ListaGenerica<Persona> listadoUsuarios;

	public String verInicializar() {

		return ConstantesNavegacion.DETALLE_METODOLOGIA;
	}
	
	public String verPrecedenciasInicializar() {
		Integer id = metodologia.getId();
		metodologia = new Metodologia();
		metodologia = metodologiaServicio.cargar(id);
		categoria = new Categoria();
		listadoRoles = new ListaGenerica<Rol>(rolServicio.listado(null), "id",
				"nombre");
		listadoRoles.mostrarItemNulo(true);
		listadoUsuarios = new ListaGenerica<Persona>();
		listadoUsuarios.mostrarItemNulo(true);
		tree = null;
		nodoSeleccionado = cargarArbolPrecedencia();
		vistaProyecto = false;
		proyectoEnPlaneacion = false;
		vistaMetodologia = "M";
		return ConstantesNavegacion.EDITAR_METODOLOGIA;
	}

	public String crearInicializar() {

		metodologia = new Metodologia();

		return ConstantesNavegacion.CREAR_METODOLOGIA;
	}

	public String crearAceptar() {
		metodologia.setTipo("Base");
		metodologia = metodologiaServicio.guardar(metodologia);
		FacesUtils.agregarMensajeInformacion("msg.crearCorrecto", true);
		return ConstantesNavegacion.DETALLE_METODOLOGIA;
	}

	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_METODOLOGIA;
	}

	public String crearCancelar() {

		return ConstantesNavegacion.CONSULTA_METODOLOGIA;
	}

	public String editarInicializar() {
		Integer id = metodologia.getId();
		metodologia = new Metodologia();
		metodologia = metodologiaServicio.cargar(id);
		categoria = new Categoria();
		listadoRoles = new ListaGenerica<Rol>(rolServicio.listado(null), "id",
				"nombre");
		listadoRoles.mostrarItemNulo(true);
		listadoUsuarios = new ListaGenerica<Persona>();
		listadoUsuarios.mostrarItemNulo(true);
		tree = null;
		nodoSeleccionado = cargarArbol();
		vistaProyecto = false;
		proyectoEnPlaneacion = false;
		vistaMetodologia = "M";
		return ConstantesNavegacion.EDITAR_METODOLOGIA;
	}

	public String cargarInicializar() {
		Integer id = metodologia.getId();
		metodologia = new Metodologia();
		metodologia = metodologiaServicio.cargar(id);
		categoria = new Categoria();
		listadoRoles = new ListaGenerica<Rol>(rolServicio.listado(null), "id",
				"nombre");
		listadoRoles.mostrarItemNulo(false);
		listadoUsuarios = new ListaGenerica<Persona>();
		tree = null;
		nodoSeleccionado = cargarArbol();
		vistaProyecto = true;
		return ConstantesNavegacion.CARGAR_METODOLOGIA;
	}

	public String editarAceptar() {
		Persona responsable = listadoUsuarios.getObjetoSeleccionado();
		if (responsable != null && actividad != null
				&& actividad.getId() != null) {
			actividad.setPersona(responsable);
			responsable.getActividades().add(actividad);
		}
		if (vistaProyecto) {
			if (proceso != null && proceso.getId() != null) {
				Double avance = calcularAvance(proceso);
				proceso.setAvance(avance);
			}
			metodologia = metodologiaServicio.guardar(metodologia);
			FacesUtils.agregarMensajeInformacion("msg.editarCorrecto", true);
			return ConstantesNavegacion.NO_ACCION;
		} else {
			metodologia = metodologiaServicio.guardar(metodologia);
			return ConstantesNavegacion.NO_ACCION;
		}
	}

	public String editarCancelar() {
		if (metodologia == null) {
			metodologia = metodologiaServicio.cargar(metodologia.getId());
		}
		metodologia = new Metodologia();
		if (vistaProyecto) {
			return ConstantesNavegacion.IR_EDITAR_PROYECTO;
		} else {
			return ConstantesNavegacion.CONSULTA_METODOLOGIA;
		}
	}

	public String limpiar() {
		metodologia = new Metodologia();
		return "";
	}

	public String limpiarCategoria() {
		categoria = new Categoria();
		return "";
	}

	public String limpiarProceso() {
		proceso = new Proceso();
		return "";
	}

	public String limpiarActividad() {
		actividad = new Actividad();
		mostarModActividad = true;
		return "";
	}

	public String limpiarPlantilla() {
		plantilla = new Plantilla();
		return "";
	}

	public String agregarCategoria() {
		categoria.setMetodologia(metodologia);
		TreeNodoImpl nodoTrabajo = buscarNodo(raiz, nodoSeleccionado.getId());
		String llaveArbol = nodoTrabajo.getId();
		llaveArbol = llaveArbol + "." + (metodologia.getCategorias().size());
		Nodo<Categoria> nodo = new Nodo<Categoria>(categoria);
		TreeNodoImpl nodoActual = new TreeNodoImpl(nodo);
		nodoActual.setId(llaveArbol);
		nodoActual.setParent(nodoTrabajo);
		metodologia.getCategorias().add(categoria);
		nodoTrabajo.addChild(llaveArbol, nodoActual);
		categoria = new Categoria();
		metodologiaServicio.guardar(metodologia);
		return ConstantesNavegacion.EDITAR_METODOLOGIA;
	}

	public String eliminarCategoria() {
		metodologia.getCategorias().remove(categoria);
		TreeNodoImpl nodoPadre = buscarNodo(nodoSeleccionado,
				nodoSeleccionado.getId());
		TreeNodoImpl nodoPorBorrar = buscarNodoPorEntidad(nodoPadre, categoria);
		nodoPadre.removeChild(nodoPorBorrar.getId());
		categoria = new Categoria();
		metodologiaServicio.guardar(metodologia);
		return ConstantesNavegacion.NO_ACCION;
	}

	public String agregarProcesos() {
		proceso.setCategoria(categoria);
		TreeNodoImpl nodoTrabajo = buscarNodo(raiz, nodoSeleccionado.getId());
		String llaveArbol = nodoTrabajo.getId();
		llaveArbol = llaveArbol + "." + (categoria.getProcesos().size());
		Nodo<Proceso> nodo = new Nodo<Proceso>(proceso);
		TreeNodoImpl nodoActual = new TreeNodoImpl(nodo);
		nodoActual.setId(llaveArbol);
		nodoActual.setParent(nodoTrabajo);
		categoria.getProcesos().add(proceso);
		nodoTrabajo.addChild(llaveArbol, nodoActual);
		proceso = new Proceso();
		metodologiaServicio.guardar(metodologia);
		return ConstantesNavegacion.NO_ACCION;
	}

	public String eliminarProceso() {
		categoria.getProcesos().remove(proceso);
		TreeNodoImpl nodoPadre = buscarNodo(nodoSeleccionado,
				nodoSeleccionado.getId());
		TreeNodoImpl nodoPorBorrar = buscarNodoPorEntidad(nodoPadre, proceso);
		nodoPadre.removeChild(nodoPorBorrar.getId());
		proceso = new Proceso();
		metodologiaServicio.guardar(metodologia);
		return ConstantesNavegacion.NO_ACCION;
	}

	public String agregarActividades() {
		actividad.setProceso(proceso);
		TreeNodoImpl nodoTrabajo = buscarNodo(raiz, nodoSeleccionado.getId());
		String llaveArbol = nodoTrabajo.getId();
		llaveArbol = llaveArbol + "." + (proceso.getActividades().size());
		Nodo<Actividad> nodo = new Nodo<Actividad>(actividad);
		TreeNodoImpl nodoActual = new TreeNodoImpl(nodo);
		nodoActual.setId(llaveArbol);
		nodoActual.setParent(nodoTrabajo);
		proceso.getActividades().add(actividad);
		nodoTrabajo.addChild(llaveArbol, nodoActual);
		mostarModActividad = false;
		actividad.setOrden(proceso.getActividades().size());
		actividad = new Actividad();
		metodologiaServicio.guardar(metodologia);
		// this.tree.queueNodeExpand(this.rowKey);
		// this.tree.processUpdates(FacesContext.getCurrentInstance());
		return ConstantesNavegacion.NO_ACCION;
	}

	private TreeNodoImpl buscarNodo(TreeNodoImpl nodo, String id) {
		Map<Object, TreeNodoImpl> listado = new HashMap<Object, TreeNodoImpl>();
		recorrerArbol(raiz, listado);
		return listado.get(id);
	}

	private void recorrerArbol(TreeNodoImpl nodo,
			Map<Object, TreeNodoImpl> listado) {
		listado.put(nodo.getId(), nodo);
		for (Entry<Object, TreeNode<Nodo<?>>> entry : nodo.getChilds()
				.entrySet()) {
			TreeNodoImpl valor = (TreeNodoImpl) entry.getValue();
			recorrerArbol(valor, listado);
		}

	}

	public String eliminarActividad() {
		proceso.getActividades().remove(actividad);
		actividad.setProceso(null);
		TreeNodoImpl nodoPadre = buscarNodo(nodoSeleccionado,
				nodoSeleccionado.getId());
		TreeNodoImpl nodoPorBorrar = buscarNodoPorEntidad(nodoPadre, actividad);
		nodoPadre.removeChild(nodoPorBorrar.getId());
		metodologiaServicio.guardar(metodologia);
		actividad = new Actividad();
		return ConstantesNavegacion.NO_ACCION;
	}

	private TreeNodoImpl buscarNodoPorEntidad(TreeNodoImpl nodo,
			EntidadGenerica entidad) {
		for (Entry<Object, TreeNode<Nodo<?>>> entry : nodo.getChilds()
				.entrySet()) {
			TreeNodoImpl valor = (TreeNodoImpl) entry.getValue();
			if (valor.getData().getValor().equals(entidad)) {
				return valor;
			}
		}
		return null;
	}

	public String agregarPlantillas() {
		if (!actividad.getPlantillas().contains(plantilla)) {
			documento.setNombre(plantilla.getNombre());
			plantilla.setDocumento(documento);
			plantilla.setActividad(actividad);
			actividad.getPlantillas().add(plantilla);
		} else {
			plantilla.getDocumento().setNombre(plantilla.getNombre());
		}
		plantilla = new Plantilla();
		metodologiaServicio.guardar(metodologia);
		return ConstantesNavegacion.NO_ACCION;
	}

	public String eliminarPlantilla() {
		actividad.getPlantillas().remove(plantilla);
		plantilla = new Plantilla();
		metodologiaServicio.guardar(metodologia);
		return ConstantesNavegacion.NO_ACCION;
	}

	public void cargarDocumento(UploadEvent event) throws Exception {
		UploadItem item = event.getUploadItem();
		File archivo = item.getFile();
		InputStream inputStream = new FileInputStream(archivo);
		byte[] datos = new byte[item.getFileSize()];
		String[] partesNombre = item.getFileName().split("\\.");
		inputStream.read(datos);
		if (plantilla.getDocumento() == null) {
			documento = new Documento();
		} else {
			documento = plantilla.getDocumento();
		}
		documento.setDocumento(datos);
		documento.setTipoContenido(item.getContentType());
		documento.setExtencion(partesNombre[partesNombre.length - 1]);

	}

	public void descargarDocumento() {
		if (plantilla.getDocumento().getDocumento() != null) {
			HttpServletResponse response = (HttpServletResponse) FacesUtils
					.getResponse();

			response.setContentType(plantilla.getDocumento().getTipoContenido());
			response.addHeader("Content-disposition", "attachment; filename=\""
					+ plantilla.getDocumento().getNombre() + "."
					+ plantilla.getDocumento().getExtencion() + "\"");
			try {
				ServletOutputStream os = response.getOutputStream();
				os.write(plantilla.getDocumento().getDocumento());
				os.flush();
				os.close();
				FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private TreeNodoImpl cargarArbol() {

		raiz = new TreeNodoImpl();
		Nodo<Metodologia> nodoMetodologia = new Nodo<Metodologia>(metodologia);
		System.out.println(metodologia.getNombre());
		TreeNodoImpl tnodoMetdologia = new TreeNodoImpl(nodoMetodologia);
		tnodoMetdologia.setId("1");
		tnodoMetdologia.setParent(raiz);
		raiz.addChild("1", tnodoMetdologia);
		int contador = 0;
		for (Categoria categoria : metodologia.getCategorias()) {
			System.out.println("  " + categoria.getNombre());
			String llaveArbol = tnodoMetdologia.getId();
			llaveArbol = llaveArbol + "." + (contador);
			Nodo<Categoria> nodoCategoria = new Nodo<Categoria>(categoria);
			TreeNodoImpl tNodoCategoria = new TreeNodoImpl(nodoCategoria);
			tNodoCategoria.setId(llaveArbol);
			tNodoCategoria.setParent(raiz);

			int contadorProcesos = 0;
			for (Proceso proceso : categoria.getProcesos()) {
				System.out.println("    " + proceso.getNombre());
				llaveArbol = tNodoCategoria.getId();
				llaveArbol = llaveArbol + "." + (contadorProcesos);
				Nodo<Proceso> nodoProceso1 = new Nodo<Proceso>(proceso);
				TreeNodoImpl nodoProceso = new TreeNodoImpl(nodoProceso1);
				nodoProceso.setId(llaveArbol);
				nodoProceso.setParent(tNodoCategoria);
				tNodoCategoria.addChild(llaveArbol, nodoProceso);
				int contadorActividades = 0;
				for (Actividad actividad : proceso.getActividades()) {
					System.out.println("      " + actividad.getNombre());
					llaveArbol = nodoProceso.getId();
					llaveArbol = llaveArbol + "." + (contadorActividades);
					Nodo<Actividad> nodoActividad1 = new Nodo<Actividad>(
							actividad);
					TreeNodoImpl nodoActividad = new TreeNodoImpl(
							nodoActividad1);
					nodoActividad.setId(llaveArbol);
					nodoActividad.setParent(nodoProceso);
					nodoProceso.addChild(llaveArbol, nodoActividad);
					contadorActividades++;
				}
				contadorProcesos++;
			}
			tnodoMetdologia.addChild(llaveArbol, tNodoCategoria);
			// nodoSeleccionado = tnodoMetdologia;
			contador++;

		}
		return tnodoMetdologia;

	}

	private TreeNodoImpl cargarArbolPrecedencia() {

		raiz = new TreeNodoImpl();
		Nodo<Metodologia> nodoMetodologia = new Nodo<Metodologia>(metodologia);
		System.out.println(metodologia.getNombre());
		TreeNodoImpl tnodoMetodologia = new TreeNodoImpl(nodoMetodologia);
		tnodoMetodologia.setId("1");
		tnodoMetodologia.setParent(raiz);
		raiz.addChild("1", tnodoMetodologia);
		
		List<Actividad> noPrecedidas = actividadServicio.noPrecedidas(metodologia.getId());
		crearRamaPrecedencia(noPrecedidas, tnodoMetodologia);
		
		return tnodoMetodologia;

	}

	private void crearRamaPrecedencia(List<Actividad> lista,
			TreeNodoImpl nodoPadre) {
		int contadorActividades = 0;
		for (Actividad actividad : lista) {
			System.out.println("      " + actividad.getNombre());
			String llaveArbol = nodoPadre.getId();
			llaveArbol = llaveArbol + "." + (contadorActividades);
			Nodo<Actividad> nodoActividad1 = new Nodo<Actividad>(
					actividad);
			TreeNodoImpl nodoActividad = new TreeNodoImpl(
					nodoActividad1);
			nodoActividad.setId(llaveArbol);
			nodoPadre.addChild(llaveArbol, nodoActividad);
			nodoActividad.setParent(nodoPadre);
			if(!actividad.getPrecedidas().isEmpty()){
				crearRamaPrecedencia(actividad.getPrecedidas(), nodoActividad);
			}
			contadorActividades++;
		}
		
	}

	public void procesarExpansion(NodeExpandedEvent evt) {

		Object source = evt.getSource();
		if (source instanceof HtmlTreeNode) {
			UITree tree = ((HtmlTreeNode) source).getUITree();
			if (tree == null) {
				return;
			}
			// get the row key i.e. id of the given node.
			Object rowKey = tree.getRowKey();
			Nodo<?> titulo = (Nodo<?>) tree.getRowData();
			// get the model node of this node.
			TreeRowKey<?> key = TreeRowKey.class.cast(tree.getRowKey());

			TreeState state = (TreeState) tree.getComponentState();
			if (state.isExpanded(key)) {
				System.out.println(rowKey + " - expandido: valor: "
						+ titulo.getNombre());

			} else {
				System.out.println(rowKey + " - colapsado: valor: "
						+ titulo.getNombre());
			}
		}
	}

	public String procesarSeleccion(NodeSelectedEvent event) {
		HtmlTree tree = (HtmlTree) event.getComponent();
		Nodo<?> nodeTitle = (Nodo<?>) tree.getRowData();
		// guardar el progreso actual
		try {
			metodologiaServicio.guardar(metodologia);

			nodoSeleccionado = null;
			nodoSeleccionado = TreeNodoImpl.class.cast(tree
					.getModelTreeNode(tree.getRowKey()));
			if (nodoSeleccionado.getNodo().getValor() instanceof Metodologia) {
				metodologia = new Metodologia();
				metodologia = (Metodologia) nodoSeleccionado.getNodo()
						.getValor();
				if (vistaMetodologia.equals("E") && metodologia != null
						&& metodologia.getId() != null) {
					Double avance = calcularAvance(metodologia);
					metodologia.setAvance(avance);
					metodologiaServicio.guardar(metodologia);
				}
				if (vistaMetodologia.equals("E") && proceso != null
						&& proceso.getId() != null) {
					Double avance = calcularAvance(proceso);
					proceso.setAvance(avance);
					metodologiaServicio.guardar(metodologia);
				}
			} else if (nodoSeleccionado.getNodo().getValor() instanceof Categoria) {
				categoria = new Categoria();
				categoria = (Categoria) nodoSeleccionado.getNodo().getValor();
				if (vistaMetodologia.equals("E") && categoria != null
						&& categoria.getId() != null) {
					Double avance = calcularAvance(categoria);
					categoria.setAvance(avance);
					metodologiaServicio.guardar(metodologia);
				}
			} else if (nodoSeleccionado.getNodo().getValor() instanceof Proceso) {
				proceso = new Proceso();
				proceso = (Proceso) nodoSeleccionado.getNodo().getValor();
				if (vistaMetodologia.equals("E") && proceso != null
						&& proceso.getId() != null) {
					Double avance = calcularAvance(proceso);
					proceso.setAvance(avance);
					metodologiaServicio.guardar(metodologia);
				}
			} else if (nodoSeleccionado.getNodo().getValor() instanceof Actividad) {
				actividad = new Actividad();
				// refrecar la actividad cuando se producen borrado en cascada
				// en la db
				actividad = (Actividad) nodoSeleccionado.getNodo().getValor();
				actividad = actividadServicio.cargar(actividad.getId());
				inicializarActividad();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("nodo procesado " + nodeTitle.getNombre());
		return ConstantesNavegacion.NO_ACCION;
	}

	public void actualizarUsuariosPorRol(ValueChangeEvent event) {
		System.out.println("valor " + event.getNewValue());
		if (event.getNewValue() != null) {
			Rol rol = listadoRoles.getDatos().get(event.getNewValue());
			if (rol != null) {
				rol = rolServicio.cargar(rol.getId());
				actividad.setRol(rol);
				rol.getActividades().add(actividad);
				List<Persona> personasPorRol = rol.getPersonas();
				listadoUsuarios = new ListaGenerica<Persona>(personasPorRol,
						"id", "nombre");
			}
		}
	}

	public void asignarReponsable(ValueChangeEvent event) {
		if (event.getNewValue() != null) {
			Persona responsable = listadoUsuarios.getDatos().get(
					event.getNewValue());
			if (responsable != null) {
				actividad.setPersona(responsable);
				responsable.getActividades().add(actividad);
			}
		}
	}

	private void inicializarActividad() {
		List<Actividad> precedentes = new ArrayList<Actividad>();
		List<Actividad> aprobaciones = new ArrayList<Actividad>();
		precedentes = actividadServicio.actividadesPorMetodologia(metodologia
				.getId());
		precedentes.remove(actividad);
		precedentes.removeAll(actividad.getPrecedentes());

		aprobaciones = actividadServicio.actividadesPorMetodologia(metodologia
				.getId());
		aprobaciones.remove(actividad);
		aprobaciones.removeAll(actividad.getActividadesPorAprobar());
		listadoPrecedentes = new ListaGenerica<Actividad>(precedentes, "id",
				"nombre");
		listadoAprobaciones = new ListaGenerica<Actividad>(aprobaciones, "id",
				"nombre");
		List<Herramienta> herramientas = herramientaServicio
				.listado(new Herramienta());
		herramientas.removeAll(actividad.getHerramientas());
		listadoHerramientas = new ListaGenerica<Herramienta>(herramientas,
				"id", "nombre");

		if (listadoHerramientas.getLista().size() == 0) {
			listadoHerramientas.mostrarItemNulo(true);
		} else {
			listadoHerramientas.mostrarItemNulo(false);
		}
		if (listadoPrecedentes.getLista().size() == 0) {
			listadoPrecedentes.mostrarItemNulo(true);
		} else {
			listadoPrecedentes.mostrarItemNulo(false);
		}
		if (listadoAprobaciones.getLista().size() == 0) {
			listadoAprobaciones.mostrarItemNulo(true);
		} else {
			listadoAprobaciones.mostrarItemNulo(false);
		}
		if (actividad.getRol() != null) {
			listadoRoles.setIdSeleccionado(actividad.getRol().getId());
			Rol rol = rolServicio.cargar(actividad.getRol().getId());
			List<Persona> personasPorRol = rol.getPersonas();
			listadoUsuarios = new ListaGenerica<Persona>(personasPorRol, "id",
					"nombre");
			if (actividad.getPersona() != null) {
				listadoUsuarios.setIdSeleccionado(actividad.getPersona().getId());
			}else{
				listadoUsuarios.setIdSeleccionado(-1);
			}

		}else{
			listadoRoles.setIdSeleccionado(-1);
			listadoUsuarios.setIdSeleccionado(-1);
		}
			}

	public void agregarNotificacion(List<Notificacion> notificaciones) {
		actividad.setNotificaciones(notificaciones);
	}

	public String eliminarNotificacion() {
		actividad.getNotificaciones().remove(notificacion);
		metodologiaServicio.guardar(metodologia);
		notificacion = new Notificacion();
		return ConstantesNavegacion.NO_ACCION;
	}

	public void agregarPrecedente() {
		if (!actividad.getPrecedentes().contains(
				listadoPrecedentes.getObjetoSeleccionado())) {
			Actividad actividadSeleccionada = listadoPrecedentes
					.getObjetoSeleccionado();
			if (actividadSeleccionada != null) {
				actividadSeleccionada = actividadServicio
						.cargar(actividadSeleccionada.getId());
				if (!actividadSeleccionada.getPrecedentes().contains(actividad)) {
					actividad.getPrecedentes().add(actividadSeleccionada);
					precedente.getPrecedidas().add(actividad);

				} else {
					FacesUtils
							.agregarMensajeError("error.metodologia.precedenciaCiclica");
				}
			}
			metodologiaServicio.guardar(metodologia);
			inicializarActividad();
		}
	}

	public void eliminarPrecedente() {
		actividad.getPrecedentes().remove(precedente);
		precedente.getPrecedidas().remove(precedente);
		metodologiaServicio.guardar(metodologia);
		precedente = new Actividad();
		inicializarActividad();
	}

	public void agregarAprobacion() {
		if (!actividad.getActividadesPorAprobar().contains(
				listadoAprobaciones.getObjetoSeleccionado())) {
			Actividad actividadSeleccionada = listadoAprobaciones
					.getObjetoSeleccionado();
			if (actividadSeleccionada != null) {
				if (!actividadSeleccionada.getActividadesPorAprobar().contains(
						actividad)) {
					actividad.getActividadesPorAprobar().add(
							actividadServicio.cargar(actividadSeleccionada
									.getId()));
					actividadSeleccionada.setActividadAprobadora(actividad);
				} else {
					FacesUtils
							.agregarMensajeError("error.metodologia.aprobacionCiclica");
				}
			}
			metodologiaServicio.guardar(metodologia);
			inicializarActividad();
		}
	}

	public void eliminarAprobacion() {
		actividad.getActividadesPorAprobar().remove(actividadPorAprobar);
		actividad.setActividadAprobadora(null);
		metodologiaServicio.guardar(metodologia);
		inicializarActividad();
	}

	public void agregarHerramienta() {
		Herramienta herramientaSeleccionada = listadoHerramientas
				.getObjetoSeleccionado();

		if (herramientaSeleccionada != null
				&& !actividad.getHerramientas().contains(
						herramientaSeleccionada)) {
			actividad.getHerramientas().add(
					listadoHerramientas.getObjetoSeleccionado());
			metodologiaServicio.guardar(metodologia);
			inicializarActividad();
		}
	}

	public void eliminarHerramienta() {
		actividad.getHerramientas().remove(herramienta);
		metodologiaServicio.guardar(metodologia);
		inicializarActividad();
	}

	public String expandirArbol() {
		tree.queueExpandAll();
		return "";
	}

	public String colapsarArbol() {
		tree.queueCollapseAll();
		return "";
	}

	public void ordenar() {
		TreeNodoImpl nodoTrabajo = buscarNodo(raiz, nodoSeleccionado.getId());
		Object dato = nodoTrabajo.getData().getValor();

		if (dato instanceof Proceso) {
			Collections.sort(((Proceso) dato).getActividades());
			nodoTrabajo.getChilds().clear();
			String llaveArbol = " no";
			int contadorActividades = 0;
			for (Actividad actividad : ((Proceso) dato).getActividades()) {
				llaveArbol = nodoTrabajo.getId();
				llaveArbol = llaveArbol + "." + (contadorActividades);
				Nodo<Actividad> nodoActividad1 = new Nodo<Actividad>(actividad);
				TreeNodoImpl nodoActividad = new TreeNodoImpl(nodoActividad1);
				nodoActividad.setId(llaveArbol);
				nodoActividad.setParent(nodoTrabajo);
				nodoTrabajo.addChild(llaveArbol, nodoActividad);
				contadorActividades++;

			}

		}
	}

	private Double calcularAvance(Proceso proceso) {
		Double avance = 0d;
		Double ap = 0d;
		Double totalPesos = 0d;

		for (Actividad actividad : proceso.getActividades()) {
			if (actividad.getPeso() != null && actividad.getAvance() != null)
				ap = ap + (actividad.getPeso() * (actividad.getAvance() / 100));
			totalPesos = totalPesos + actividad.getPeso();

		}
		avance = (ap * 100) / totalPesos;
		avance = avance * 100;
		avance = (double) Math.round(avance);
		return avance / 100;
	}

	private Double calcularAvance(Categoria categoria) {
		Double avance = 0d;
		Double ap = 0d;
		Double totalPesos = 0d;

		for (Proceso proceso : categoria.getProcesos()) {
			if (proceso.getPeso() != null && proceso.getAvance() != null)
				ap = ap + (proceso.getPeso() * (proceso.getAvance() / 100));
			totalPesos = totalPesos + proceso.getPeso();

		}
		avance = (ap * 100) / totalPesos;
		avance = avance * 100;
		avance = (double) Math.round(avance);
		return avance / 100;
	}

	private Double calcularAvance(Metodologia metodologia) {
		Double avance = 0d;
		Double ap = 0d;
		Double totalPesos = 0d;

		for (Categoria categoria : metodologia.getCategorias()) {
			if (categoria.getPeso() != null && categoria.getAvance() != null)
				ap = ap + (categoria.getPeso() * (categoria.getAvance() / 100));
			totalPesos = totalPesos + categoria.getPeso();

		}
		avance = (ap * 100) / totalPesos;
		avance = avance * 100;
		avance = (double) Math.round(avance);
		return avance / 100;
	}

	public Metodologia getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public TreeNodoImpl getRaiz() {
		return raiz;
	}

	public void setRaiz(TreeNodoImpl raiz) {
		this.raiz = raiz;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public UITree getTree() {
		return tree;
	}

	public void setTree(UITree tree) {
		this.tree = tree;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public TreeNodoImpl getNodoSeleccionado() {
		return nodoSeleccionado;
	}

	public void setNodoSeleccionado(TreeNodoImpl nodoSeleccionado) {
		this.nodoSeleccionado = nodoSeleccionado;
	}

	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}

	public Actividad getPrecedente() {
		return precedente;
	}

	public void setPrecedente(Actividad precedente) {
		this.precedente = precedente;
	}

	public ListaGenerica<Actividad> getListadoPrecedentes() {
		return listadoPrecedentes;
	}

	public void setListadoPrecedentes(
			ListaGenerica<Actividad> listadoPrecedentes) {
		this.listadoPrecedentes = listadoPrecedentes;
	}

	public Plantilla getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(Plantilla plantilla) {
		this.plantilla = plantilla;
	}

	public ListaGenerica<Herramienta> getListadoHerramientas() {
		return listadoHerramientas;
	}

	public void setListadoHerramientas(
			ListaGenerica<Herramienta> listadoHerramientas) {
		this.listadoHerramientas = listadoHerramientas;
	}

	public Herramienta getHerramienta() {
		return herramienta;
	}

	public void setHerramienta(Herramienta herramienta) {
		this.herramienta = herramienta;
	}

	public RolServicio getRolServicio() {
		return rolServicio;
	}

	public void setRolServicio(RolServicio rolServicio) {
		this.rolServicio = rolServicio;
	}

	public ListaGenerica<Rol> getListadoRoles() {
		return listadoRoles;
	}

	public void setListadoRoles(ListaGenerica<Rol> listadoRoles) {
		this.listadoRoles = listadoRoles;
	}

	public ListaGenerica<Persona> getListadoUsuarios() {
		return listadoUsuarios;
	}

	public void setListadoUsuarios(ListaGenerica<Persona> listadoUsuarios) {
		this.listadoUsuarios = listadoUsuarios;
	}

	public boolean getTieneMensajes() {
		return FacesUtils.tieneMensajes();
	}

	public boolean isMostarModActividad() {
		return mostarModActividad;
	}

	public void setMostarModActividad(boolean mostarModActividad) {
		this.mostarModActividad = mostarModActividad;
	}

	public boolean isVistaProyecto() {
		return vistaProyecto;
	}

	public void setVistaProyecto(boolean vistaProyecto) {
		this.vistaProyecto = vistaProyecto;
	}

	public boolean isProyectoEnPlaneacion() {
		return proyectoEnPlaneacion;
	}

	public void setProyectoEnPlaneacion(boolean proyectoEnPlaneacion) {
		this.proyectoEnPlaneacion = proyectoEnPlaneacion;
	}

	public ListaGenerica<Actividad> getListadoAprobaciones() {
		return listadoAprobaciones;
	}

	public void setListadoAprobaciones(
			ListaGenerica<Actividad> listadoAprobaciones) {
		this.listadoAprobaciones = listadoAprobaciones;
	}

	public Actividad getActividadPorAprobar() {
		return actividadPorAprobar;
	}

	public void setActividadPorAprobar(Actividad actividadPorAprobar) {
		this.actividadPorAprobar = actividadPorAprobar;
	}

	public String getVistaMetodologia() {
		return vistaMetodologia;
	}

	public void setVistaMetodologia(String vistaMetodologia) {
		this.vistaMetodologia = vistaMetodologia;
	}

}
