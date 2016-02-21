package co.com.sigepro.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.entidades.Subsistema;
import co.com.sigepro.negocio.serv.CredencialServicio;
import co.com.sigepro.negocio.serv.PersonaServicio;
import co.com.sigepro.negocio.serv.SubsistemaServicio;
import co.com.sigepro.persistencia.util.SessionUtil;

@Component("ingresoBean")
@Scope("session")
public class IngresoBean {

	private static final String NOMBRE_INVALIDO = "error.login.usuario-invalido";
	private static final String MENSAJE_TAMANO_MAXIMO = "jsp.usuario.tamano";

	@Autowired
	private PersonaServicio personaServicio;
	@Autowired
	private SessionUtil sessionUtil;

	@Resource(name = "captchaBean")
	private CaptchaBean captchaBean;

	@Resource(name = "credencialServicio")
	private CredencialServicio credencialServicio;
	//
	@Resource(name = "subsistemaServicio")
	private SubsistemaServicio subsistemaServicio;

	@Resource(name = "menuUsuarioBean")
	private MenuUsuarioBean menuUsuarioBean;
	//
	// @Resource(name = "claveDelegado")
	// private ClaveDelegado claveDelegado;
	//
	// @Resource(name = "tipoIdentificacionDelegado")
	// private TipoIdentificacionDelegado tipoIdentificacionDelegado;

	private Persona usuario;

	String email;
	String clave;
	int numeroIngresoClave = 0;
	boolean renderModalCambioClave = false;
	private String respuestaSecreta;
	private List<Subsistema> subsistemas;
	private Subsistema subsistema;
	boolean renderModalSeleccionaSubsistema = false;
	boolean iniciaSesion = false;
	String mensajeValidacion;

	public String loginAction() {
		String accion = ConstantesNavegacion.NO_ACCION;

		if (usuario.getLogin().length() > 100) {
			FacesUtils.agregarMensajeError(MENSAJE_TAMANO_MAXIMO);
			return ConstantesNavegacion.NO_ACCION;
		}
		if (!captchaBean.validarCaptcha()) {
			FacesUtils.agregarMensajeError("jsp.usuario.noCaptcha");
			accion = ConstantesNavegacion.NO_ACCION;
		} else {

			// Usuario invalido
			if (usuario != null) {

				// Ingreso por primera vez

				accion = this.validarPoliticasSeguridad(accion);

			} else {
				FacesUtils.agregarMensajeError(NOMBRE_INVALIDO);
			}
		}
		return accion;
	}

	public String validarPoliticasSeguridad(String accion) {
		// Contrase�a no coinciden

		if (personaServicio.validarClaveIngreso(usuario)) {
			// Cuenta Activa?
			this.setRenderModalCambioClave(false);

			// usuario = personaServicio.getUsuarioPorLogin(usuario.getLogin());
			FacesUtils.setUsuario(personaServicio.getUsuarioPorLogin(usuario
					.getLogin()));
			sessionUtil.cerrarSession();
			usuario = new Persona();
			this.cargarSubsistemas();
		} else {
			FacesUtils.agregarMensajeError(NOMBRE_INVALIDO);
		}
		return accion;
	}

	public String volverLogin() {
		this.limpiarCampos();
		return ConstantesNavegacion.VOLVER_LOGIN;
	}

	public void cargarSubsistemas() {
		this.renderModalSeleccionaSubsistema = true;
		Persona usuarioLogueado = FacesUtils.getUsuario();
		usuarioLogueado = personaServicio.cargar(usuarioLogueado.getId());
		FacesUtils.setUsuario(usuarioLogueado);
		subsistemas = this.subsistemaServicio
				.getSubsistemasXRol(usuarioLogueado.getRols().get(0).getId());
	}

	public String elegirSubsistema() {
		renderModalSeleccionaSubsistema = false;
		credencialServicio.asignarCredencialUsuario(subsistema);
		this.iniciaSesion = true;
		return ConstantesNavegacion.IR_BIENVENIDA;
	}

	public String ingresar() {
		return null;
		// return ConstantesNavegacion.VOLVER_LOGIN;
	}

	public String cerrarSesion() {
		FacesUtils.invalidarSesion();
		sessionUtil.cerrarSession();
		this.menuUsuarioBean.setUsuario(null);
		this.iniciaSesion = false;
		return ConstantesNavegacion.VOLVER_LOGIN;
	}

	public void limpiarCampos() {
		this.usuario = new Persona();
		this.email = null;
		this.respuestaSecreta = null;
		this.captchaBean.setTexto(null);
		this.renderModalSeleccionaSubsistema = false;
	}

	/**
	 * @return the persona
	 */
	public Persona getUsuario() {
		if (null == usuario) {
			this.usuario = new Persona();
		}
		return usuario;
	}

	/**
	 * @param persona
	 *            the persona to set
	 */
	public void setUsuario(Persona persona) {
		this.usuario = persona;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the respuestaSecreta
	 */
	public String getRespuestaSecreta() {
		return respuestaSecreta;
	}

	/**
	 * @param respuestaSecreta
	 *            the respuestaSecreta to set
	 */
	public void setRespuestaSecreta(String respuestaSecreta) {
		this.respuestaSecreta = respuestaSecreta;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave
	 *            the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the renderModalCambioClave
	 */
	public boolean isRenderModalCambioClave() {
		return renderModalCambioClave;
	}

	/**
	 * @param renderModalCambioClave
	 *            the renderModalCambioClave to set
	 */
	public void setRenderModalCambioClave(boolean renderModalCambioClave) {
		this.renderModalCambioClave = renderModalCambioClave;
	}

	/**
	 * @return the subsistemas
	 */
	public List<Subsistema> getSubsistemas() {
		return subsistemas;
	}

	/**
	 * @param subsistemas
	 *            the subsistemas to set
	 */
	public void setSubsistemas(List<Subsistema> subsistemas) {
		this.subsistemas = subsistemas;
	}

	/**
	 * @return the renderModalSeleccionaSubsistema
	 */
	public boolean isRenderModalSeleccionaSubsistema() {
		return renderModalSeleccionaSubsistema;
	}

	/**
	 * @param renderModalSeleccionaSubsistema
	 *            the renderModalSeleccionaSubsistema to set
	 */
	public void setRenderModalSeleccionaSubsistema(
			boolean renderModalSeleccionaSubsistema) {
		this.renderModalSeleccionaSubsistema = renderModalSeleccionaSubsistema;
	}

	/**
	 * @return the mensajeValidacion
	 */
	public String getMensajeValidacion() {
		return mensajeValidacion;
	}

	/**
	 * @param mensajeValidacion
	 *            the mensajeValidacion to set
	 */
	public void setMensajeValidacion(String mensajeValidacion) {
		this.mensajeValidacion = mensajeValidacion;
	}

	/**
	 * @return the iniciaSesion
	 */
	public boolean isIniciaSesion() {
		return iniciaSesion;
	}

	/**
	 * @param iniciaSesion
	 *            the iniciaSesion to set
	 */
	public void setIniciaSesion(boolean iniciaSesion) {
		this.iniciaSesion = iniciaSesion;
	}

	public Subsistema getSubsistema() {
		return subsistema;
	}

	public void setSubsistema(Subsistema subsistema) {
		this.subsistema = subsistema;
	}

}
