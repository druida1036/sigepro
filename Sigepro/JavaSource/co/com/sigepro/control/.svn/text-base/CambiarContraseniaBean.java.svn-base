package co.com.sigepro.control;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.control.util.Constantes;
import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.control.util.PropiedadesUtils;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.negocio.serv.PersonaServicio;
import co.com.sigepro.negocio.utilidades.SeguridadUtil;

@Controller
@Scope("session")
public class CambiarContraseniaBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IngresoBean ingresoBean;
	@Autowired
	private PersonaServicio usuarioDelegado;

	private Persona usuario;

	private static final String CONTRASENIA_INCORRECTA = "error.cuenta.contraseniaIncorrecta";
	private static final String CAMPOS_CONTRASENIA_NO_SON_IGUALES = "error.cuenta.contraseniaNoIgual";
	private static final String CAMBIO_CONTRASENIA_VALIDO = "confirmacion.cuenta.cambioContrasenia";
	private static final String CAMBIO_CONTRASENIA_INVALIDO = "confirmacion.login.respuesta-invalida";

	private String clave;
	private String nuevaClave;
	private String confirmaClave;

	private String mensajeValidacion;

	public CambiarContraseniaBean() {
		usuario = new Persona();
		usuario = FacesUtils.getUsuario();
	}

	public String inicializarCambioContrasenia() {
		this.limpiar();
		return ConstantesNavegacion.CAMBIAR_CONTRASENIA;
	}

	public String guardar() {
		String accion = ConstantesNavegacion.IR_BIENVENIDA;
		accion = this.validarCambioContrasenia(accion);
		return ConstantesNavegacion.IR_BIENVENIDA;
	}

	public String guardarEnLogin() {
		this.validarCambioContrasenia(null);
		this.ingresoBean.setClave(this.nuevaClave);
		return this.ingresoBean
				.validarPoliticasSeguridad(ConstantesNavegacion.NO_ACCION);
	}

	public String validarCambioContrasenia(String accion) {
		Persona usuarioTmp = new Persona();
		usuarioTmp.setContrasena(clave);
		usuarioTmp.setLogin(usuario.getLogin());
		//usuario.setContrasena(clave);
		if (usuarioDelegado.validarClaveIngreso(usuarioTmp)) {
			usuario = new Persona();
			usuario = usuarioDelegado.cargar(FacesUtils.getUsuario().getId());
			usuario.setContrasena(nuevaClave);
		
				if (nuevaClave.equals(confirmaClave)) {

					SecretKey secretKey = SeguridadUtil
							.generarClave(SeguridadUtil.secretKey);
					usuario.setContrasena(SeguridadUtil.encriptar(
							usuario.getContrasena(), secretKey));
					usuarioDelegado.guardar(usuario);

					this.mensajeValidacion = PropiedadesUtils.getMensaje(
							Constantes.ARCHIVO_MENSAJES,
							CAMBIO_CONTRASENIA_VALIDO);
					FacesUtils.agregarMensajeInformacion(mensajeValidacion, false);
				} else {
					this.mensajeValidacion = PropiedadesUtils.getMensaje(
							Constantes.ARCHIVO_MENSAJES,
							CAMBIO_CONTRASENIA_INVALIDO);
					FacesUtils
					.agregarMensajeError(CAMPOS_CONTRASENIA_NO_SON_IGUALES);
					accion = ConstantesNavegacion.CONFIRMAR_CAMBIO_CLAVE;			
				}
			}  else {
			FacesUtils.agregarMensajeError(CONTRASENIA_INCORRECTA);
		}
		return accion;
	}

	/**
	 * Metodo encargado de cancelar y limpiar los campos
	 * 
	 * @return
	 */
	public String cancelar() {
		this.limpiar();
		return ConstantesNavegacion.IR_BIENVENIDA;
	}

	public String cancelarLogin() {
		this.limpiar();
		return ConstantesNavegacion.VOLVER_LOGIN;
	}

	public void limpiar() {
		this.clave = null;
		this.nuevaClave = null;
		this.confirmaClave = null;
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
	 * @return the nuevaClave
	 */
	public String getNuevaClave() {
		return nuevaClave;
	}

	/**
	 * @param nuevaClave
	 *            the nuevaClave to set
	 */
	public void setNuevaClave(String nuevaClave) {
		this.nuevaClave = nuevaClave;
	}

	/**
	 * @return the confirmaClave
	 */
	public String getConfirmaClave() {
		return confirmaClave;
	}

	/**
	 * @param confirmaClave
	 *            the confirmaClave to set
	 */
	public void setConfirmaClave(String confirmaClave) {
		this.confirmaClave = confirmaClave;
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

	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}

	public Persona getUsuario() {
		if (usuario == null) {
			usuario = FacesUtils.getUsuario();
		}
		return usuario;
	}
}
