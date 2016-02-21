package co.com.sigepro.control.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.com.sigepro.entidades.Credencial;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.negocio.impl.CredencialServicioImpl;

public class FiltroSeguridad implements Filter {

	private String errorPage;
	private String loginPage;

	private static final String ERROR_CONFIGURACION = "error.seguridad.configuracion";
	private static final String ARCHIVO_DE_PROPIEDADES = "co.com.sigepro.configuracion.urlSinRestricciones";
	private static final String URL_SIN_RESTRICCIONES = "urlSinRestricciones";
	private static final String SEPARADOR = " ";
	private static final String PAGINA_LOGIN = "paginaLogin";
	private static final String PAGINA_ACCESO_DENEGADO = "paginaAccesoDenegado";
	private static final String URL_SIN_RESTRICCIONES_CADENA = PropiedadesUtils
			.getMensaje(ARCHIVO_DE_PROPIEDADES, URL_SIN_RESTRICCIONES);

	/**
	 * Metodo encargado de inicializar los parametro del filtro
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		if (filterConfig != null) {

			errorPage = PropiedadesUtils.getMensaje(ARCHIVO_DE_PROPIEDADES,
					PAGINA_ACCESO_DENEGADO);
			loginPage = PropiedadesUtils.getMensaje(ARCHIVO_DE_PROPIEDADES,
					PAGINA_LOGIN);

		}
	}

	/**
	 * Maneja la funcionalidad del filtro revisando cada url accesada
	 * permitiendo o negando el acceso a esta
	 **/
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		if (errorPage == null || loginPage == null) {
			returnError(request, response, ERROR_CONFIGURACION);
		}
		String url = servletRequest.getRequestURL().toString();
		url = url.split(servletRequest.getContextPath())[1];
		Persona usuario = (Persona) servletRequest.getSession().getAttribute(
				FacesUtils.USUARIO);
		if (url != null && usuario != null && usuario.getCredencial() != null) {
			Credencial credencial = usuario.getCredencial();
			boolean authorized = false;

			if (credencial.getLlavesPorReglaDeNavegacion() != null) {
				authorized = credencial.getLlavesPorReglaDeNavegacion()
						.containsKey(url);
			}
			actualizarCredencial(usuario, url, servletRequest.getSession());
			if (!authorized) {
				authorized = validarUrlSinRestricciones(url);
			}
			if (authorized) {
				chain.doFilter(request, response);
			} else {
				request.getRequestDispatcher(errorPage).forward(request,
						response);
			}

		} else {
			if (validarUrlSinRestricciones(url)) {
				chain.doFilter(request, response);
			} else {
				request.getRequestDispatcher(loginPage).forward(request,
						response);
			}
		}
	}

	public void destroy() {
	}

	/** Retorna el respectivo mensaje a la pagina de error */
	private void returnError(ServletRequest request, ServletResponse response,
			String errorString) throws ServletException, IOException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		agregarMensaje(FacesMessage.SEVERITY_ERROR, errorString, null,
				servletRequest.getSession());
		request.getRequestDispatcher(errorPage).forward(request, response);
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
	private static void agregarMensaje(Severity severity, String claveMensaje,
			Object[] params, HttpSession session) {
		String message = PropiedadesUtils.getMensaje(
				Constantes.ARCHIVO_MENSAJES, claveMensaje, params);
		FacesMessage facesMessage = new FacesMessage(severity, message, message);
		List<FacesMessage> messages = new ArrayList<FacesMessage>();
		messages.add(facesMessage);
		session.setAttribute(FacesUtils.MSJ, messages);
	}

	/**
	 * Metodo encargado de actualizar las credenciales del usuario
	 * 
	 * @param usuario
	 * @param url
	 * @param session
	 */
	private void actualizarCredencial(Persona usuario, String url,
			HttpSession session) {
		if (usuario != null && usuario.getCredencial() != null) {
			String llaves = usuario.getCredencial()
					.getLlavesPorReglaDeNavegacion().get(url);
			if (llaves != null) {
				String[] llavesArray = llaves
						.split(CredencialServicioImpl.SEPARADORLLAVE);
				usuario.getCredencial().setMenuActual(
						new Integer(llavesArray[0]));
				usuario.getCredencial().setSubmenuActual(
						new Integer(llavesArray[1]));
				usuario.getCredencial().setPaginaActual(
						new Integer(llavesArray[2]));
			}
			session.setAttribute(FacesUtils.USUARIO, usuario);
		}
	}

	/**
	 * Metodo encargado de validar urls sin restricciones
	 * 
	 * @return
	 */
	private boolean validarUrlSinRestricciones(String url) {
		String[] urls = URL_SIN_RESTRICCIONES_CADENA.split(SEPARADOR);
		if (url.equals("")) {
			return false;
		}
		for (int i = 0; i < urls.length; i++) {
			if (url.equals(urls[i])) {
				return true;
			}
		}
		return false;
	}
}
