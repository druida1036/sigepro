package co.com.sigepro.control.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.Expression;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import co.com.sigepro.entidades.Persona;
import co.com.sigepro.excepcion.AplicacionExcepcion;

/**
 * @author Juan Lizarazo (jlizarazo@aliaddos.com)
 * 
 */
public final class FacesUtils {

	public static final String MSJ = "MSJ";
	public static final String USUARIO = "USUARIO";
	private static final String VARIABLES = "VARIABLES";

	/**
	 * Constructor privado. Esta clase no se puede instanciar.
	 */
	private FacesUtils() {
		super();
	}

	/**
	 * Obtiene la instancia de un bean administrado identificado en el Contexto
	 * Faces con el nombre proporcionado
	 * 
	 * @param nombre
	 *            El nombre del bean.
	 * @param clase
	 *            El tipo de bean esperado.
	 * @return La instancia del bean administrado.
	 */
	public static <Bean> Bean getBean(String nombre, Class<Bean> clase) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elContext = facesContext.getELContext();
		ValueExpression valueExpression = (ValueExpression) crearExpresionEL(
				nombre, Constantes.EXPRESION_VALOR);
		return clase.cast(valueExpression.getValue(elContext));
	}

	/**
	 * Crea una nueva expresion del Expression Language (EL) del tipo
	 * especificado. Los tipos pueden ser valor o métodos action, actionListener
	 * y valueChangeListener.
	 * 
	 * @param expresion
	 *            La cadena de texto que representa la expresion.
	 * @param tipo
	 *            El tipo de la expresion.
	 * @return La expresion creada.
	 * @see Constantes#EXPRESION_VALOR
	 * @see Constantes#EXPRESION_ACTION
	 * @see Constantes#EXPRESION_ACTION_LISTENER
	 * @see Constantes#EXPRESION_VALUE_CHANGE_LISTENER
	 */
	public static Expression crearExpresionEL(String expresion, String tipo) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elContext = facesContext.getELContext();
		ExpressionFactory expressionFactory = facesContext.getApplication()
				.getExpressionFactory();

		Expression expresionEL = null;

		if (Constantes.EXPRESION_VALOR.equals(tipo)) {
			expresionEL = expressionFactory.createValueExpression(elContext,
					expresion, Object.class);
		} else if (Constantes.EXPRESION_ACTION.equals(tipo)) {
			expresionEL = expressionFactory.createMethodExpression(elContext,
					expresion, String.class, new Class<?>[0]);
		} else if (Constantes.EXPRESION_ACTION_LISTENER.equals(tipo)) {
			expresionEL = expressionFactory.createMethodExpression(elContext,
					expresion, null, new Class<?>[] { ActionEvent.class });
		} else if (Constantes.EXPRESION_VALUE_CHANGE_LISTENER.equals(tipo)) {
			expresionEL = expressionFactory.createMethodExpression(elContext,
					expresion, null, new Class<?>[] { ValueChangeEvent.class });
		}

		return expresionEL;
	}

	/**
	 * Devuelve el valor del parámetro en el request del Contexto Externo de
	 * Faces.
	 * 
	 * @param nombre
	 *            El nombre del parámetro
	 * @return El valor del parámetro en el request.
	 */
	public static Object getParametroDelRequest(String nombre) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext eContext = context.getExternalContext();
		return eContext.getRequestParameterMap().get(nombre);
	}

	/**
	 * Coloca el valor especificado en el mapa de sesión del sitio web. Permite
	 * el paso de parámetros entre beans de scope request.
	 * 
	 * @param clave
	 *            La clave del objeto a guardar.
	 * @param valor
	 *            El objeto a guardar.
	 */
	public static void colocarEnMapaSesion(String clave, Object valor) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext eContext = context.getExternalContext();
		Map<String, Object> mapa = eContext.getSessionMap();
		mapa.put(clave, valor);
	}

	/**
	 * Devuelve el objeto especificado por la clave en el mapa de sesión del
	 * sitio web.
	 * 
	 * @param clave
	 *            La clave del objeto a recuperar.
	 * @return El objeto especificado por la clave proporcionada.
	 */
	public static Object obtenerDelMapaSesion(String clave) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext eContext = context.getExternalContext();
		Map<String, Object> mapa = eContext.getSessionMap();
		return mapa.get(clave);
	}

	/**
	 * Invalida la sesión HTTP actual.
	 * 
	 * @see HttpSession#invalidate()
	 */
	public static void invalidarSesion() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext eContext = context.getExternalContext();
		HttpSession session = (HttpSession) eContext.getSession(false);
		session.invalidate();
	}

	/**
	 * Devuelve la sesion http actual de la aplicacion.
	 * 
	 * @return La sesion http actual.
	 */
	public static HttpSession getSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(context == null)
			return null;
		ExternalContext eContext = context.getExternalContext();
		return (HttpSession) eContext.getSession(false);
	}

	/**
	 * Devuelve la peticion http actual de la aplicacion.
	 * 
	 * @return La peticion http actual.
	 */
	public static HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext eContext = context.getExternalContext();
		return (HttpServletRequest) eContext.getRequest();
	}

	/**
	 * Devuelve la respuesta http actual de la aplicacion.
	 * 
	 * @return La respuesta http actual.
	 */
	public static HttpServletResponse getResponse() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext eContext = context.getExternalContext();
		return (HttpServletResponse) eContext.getResponse();
	}

	/**
	 * Devuelve el servletContext
	 * 
	 * @return
	 */
	public static ServletContext getServletContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext eContext = context.getExternalContext();
		return (ServletContext) eContext.getContext();
	}

	/**
	 * Crea una lista de {@link SelectItem} en la cual el <code>label</code> y
	 * el <code>value</code> son los valores de las propiedad
	 * <code>propertyLabel</code> y <code>propertyValue</code> en los objetos de
	 * la coleccion.
	 * 
	 * @param objects
	 *            La coleccion de objetos a partir de la cual se construyen los
	 *            {@link SelectItem}.
	 * @param propertyValue
	 *            El nombre de la propiedad de los objetos de la cual se obtiene
	 *            el valor del {@link SelectItem}.
	 * @param propertyLabel
	 *            El nombre de la propiedad de los objetos de la cual se obtiene
	 *            el nombre del {@link SelectItem}.
	 * @return La lista de {@link SelectItem} creada.
	 */
	public static List<SelectItem> crearSelectItems(Collection<?> objects,
			String propertyValue, String propertyLabel) {

		List<SelectItem> selectItems = new ArrayList<SelectItem>();

		if (objects != null) {
			try {
				for (Object object : objects) {
					Object value = BeanUtils.getProperty(object, propertyValue);
					Object label = BeanUtils.getProperty(object, propertyLabel);

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(value);
					selectItem.setLabel(String.valueOf(label));

					selectItems.add(selectItem);
				}
			} catch (Exception e) {
				throw new AplicacionExcepcion(
						"Error al crear la lista de Select Items", e);
			}
		}

		return selectItems;
	}

	/**
	 * Agrega un mensaje al contexto de JSF.
	 * 
	 * @param claveMensaje
	 *            La clave del mensaje.
	 */
	public static void agregarMensajeInformacion(String claveMensaje,
			boolean desdeArchivo) {

		if (desdeArchivo) {
			agregarMensajeDesdeArchivo(FacesMessage.SEVERITY_INFO,
					claveMensaje, null);
		} else {
			agregarMensaje(FacesMessage.SEVERITY_INFO, claveMensaje, null);

		}
	}

	/**
	 * Agrega un mensaje al contexto de JSF.
	 * 
	 * @param claveMensaje
	 *            La clave del mensaje.
	 * @param params
	 *            Los parametros opcionales del mensaje.
	 */
	public static void agregarMensajeInformacion(String claveMensaje,
			Object[] params, boolean desdeArchivo) {

		if (desdeArchivo) {
			agregarMensajeDesdeArchivo(FacesMessage.SEVERITY_INFO,
					claveMensaje, params);
		} else {
			agregarMensaje(FacesMessage.SEVERITY_INFO, claveMensaje, params);
		}
	}

	/**
	 * Agrega un mensaje al contexto de JSF.
	 * 
	 * @param claveMensaje
	 *            La clave del mensaje.
	 */
	public static void agregarMensajeAdvertencia(String claveMensaje) {

		agregarMensajeDesdeArchivo(FacesMessage.SEVERITY_WARN, claveMensaje,
				null);
	}

	/**
	 * Agrega un mensaje al contexto de JSF.
	 * 
	 * @param claveMensaje
	 *            La clave del mensaje.
	 * @param params
	 *            Los parametros opcionales del mensaje.
	 */
	public static void agregarMensajeAdvertencia(String claveMensaje,
			Object[] params) {

		agregarMensajeDesdeArchivo(FacesMessage.SEVERITY_WARN, claveMensaje,
				params);
	}

	/**
	 * Agrega un mensaje al contexto de JSF.
	 * 
	 * @param claveError
	 *            La clave del mensaje.
	 */
	public static void agregarMensajeError(String claveError) {

		agregarMensajeDesdeArchivo(FacesMessage.SEVERITY_ERROR, claveError,
				null);
	}

	/**
	 * Agrega un mensaje al contexto de JSF.
	 * 
	 * @param claveError
	 *            La clave del mensaje.
	 * @param params
	 *            Los parametros opcionales del mensaje.
	 */
	public static void agregarMensajeError(String claveError, Object[] params) {

		agregarMensajeDesdeArchivo(FacesMessage.SEVERITY_ERROR, claveError,
				params);
	}

	/**
	 * Agrega un mensaje al contexto de JSF.
	 * 
	 * @param severity
	 *            La severidad del mensaje.
	 * @param claveMensaje
	 *            La clave del mensaje.
	 * @param params
	 *            Los parametros opcionales del mensaje.
	 */
	@SuppressWarnings("unchecked")
	private static void agregarMensajeDesdeArchivo(Severity severity,
			String claveMensaje, Object[] params) {

		String message = PropiedadesUtils.getMensaje(
				Constantes.ARCHIVO_MENSAJES, claveMensaje, params);
		FacesMessage facesMessage = new FacesMessage(severity, message, message);
		List<FacesMessage> messages = (List<FacesMessage>) getSession()
				.getAttribute(MSJ);
		if (messages == null) {
			messages = new ArrayList<FacesMessage>();
		}
		messages.add(facesMessage);
		getSession().setAttribute(MSJ, messages);

	}

	/**
	 * Agrega un mensaje al contexto de JSF.
	 * 
	 * @param severity
	 *            La severidad del mensaje.
	 * @param mensaje
	 *            La clave del mensaje.
	 * @param params
	 *            Los parametros opcionales del mensaje.
	 */
	@SuppressWarnings("unchecked")
	private static void agregarMensaje(Severity severity, String mensaje,
			Object[] params) {

		String message = PropiedadesUtils.formatearMensaje(mensaje, params);
		FacesMessage facesMessage = new FacesMessage(severity, message, message);
		List<FacesMessage> messages = (List<FacesMessage>) getSession()
				.getAttribute(MSJ);
		if (messages == null) {
			messages = new ArrayList<FacesMessage>();
		}
		messages.add(facesMessage);
		getSession().setAttribute(MSJ, messages);

	}

	/**
	 * Muestra el mensaje de error al usuario.
	 * 
	 * @param resumen
	 *            El mensaje de resumen del error.
	 * @param descripcion
	 *            La descripcion detallada del error.
	 */
	@SuppressWarnings("unchecked")
	public static void agregarMensajeError(String resumen, String descripcion) {

		FacesMessage facesMessage = new FacesMessage(
				FacesMessage.SEVERITY_ERROR, resumen, descripcion);
		List<FacesMessage> messages = (List<FacesMessage>) getSession()
				.getAttribute(MSJ);
		if (messages == null) {
			messages = new ArrayList<FacesMessage>();
		}
		messages.add(facesMessage);
		getSession().setAttribute(MSJ, messages);
	}

	/**
	 * Encargado de mostrar los monsajes sobre la jsp actual
	 */
	@SuppressWarnings("unchecked")
	public static void mostrarMensajes() {
		if (getSession() != null) {
			List<FacesMessage> messages = (List<FacesMessage>) getSession()
					.getAttribute(MSJ);
			if (messages != null) {
				for (Iterator<FacesMessage> iterator = messages.iterator(); iterator
						.hasNext();) {
					FacesMessage facesMessage = (FacesMessage) iterator.next();
					FacesContext.getCurrentInstance().addMessage(
							Constantes.ID_CLIENTE_GLOBAL, facesMessage);
				}
			}
			getSession().removeAttribute(MSJ);
		}

	}

	/**
	 * Metodo encargado de retornar el usuario actual en session
	 * 
	 * @return
	 */
	public static Persona getUsuario() {
		HttpSession session = getSession();
		Persona usuario = null;
		if (session != null) {
			usuario = (Persona) session.getAttribute(USUARIO);
		}
		return usuario;
	}

	/**
	 * Metodo encargado de insertar el usuario en la session actual
	 * 
	 * @param usuario
	 */
	public static void setUsuario(Persona usuario) {
		HttpSession session = getSession();
		if (session != null) {
			session.setAttribute(USUARIO, usuario);
		}
	}

	/**
	 * Metodo encargado de obtner el valor de una variable
	 * 
	 * @param variable
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getValorVariable(String variable) {
		HttpSession session = getSession();
		Map<String, String> valores = null;
		if (session != null) {
			valores = (Map<String, String>) session.getAttribute(VARIABLES);
		}
		return valores.get(variable);
	}

	/**
	 * Metodo encargado de agregar el valor de una variable
	 * 
	 * @param nombreVariable
	 * @param valor
	 */
	public static void setValorVariable(String nombreVariable, String valor) {
		HttpSession session = getSession();

		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<String, String> valores = (Map<String, String>) session
					.getAttribute(VARIABLES);
			if (valores == null) {
				valores = new HashMap<String, String>();
				session.setAttribute(VARIABLES, valores);
			}
			valores.put(nombreVariable, valor);
		}
	}

	public static boolean tieneMensajes() {
		return getSession().getAttribute(MSJ) == null ? false : true;
	}
}
