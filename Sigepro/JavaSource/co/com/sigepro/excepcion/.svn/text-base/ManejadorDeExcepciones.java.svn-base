package co.com.sigepro.excepcion;

import java.io.Serializable;
import java.sql.BatchUpdateException;
import java.sql.SQLException;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.hibernate.AssertionFailure;
import org.hibernate.LazyInitializationException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.ObjectDeletedException;
import org.hibernate.TransientObjectException;
import org.hibernate.exception.NestableRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.persistencia.util.SessionUtil;

/**
 * @author Luis Francisco Fontalvo Romero (lfontalvo@aliaddos.com)
 */
public class ManejadorDeExcepciones implements MethodInterceptor, Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -3790720316523982893L;
	@Autowired
	private SessionUtil sessionUtil;

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {

		Object retorno = null;
		try {
			// Invocacion del método (Action, ActionListener o
			// ValueChangeListener)
			retorno = methodInvocation.proceed();
		} catch (ValidacionExcepcion e) {
			// Se muestra la excepción de validacion
			for (String errorValidacion : e.getErroresValidacion()) {
				FacesUtils
						.agregarMensajeError(errorValidacion, errorValidacion);
			}
		} catch (AplicacionExcepcion e) {
			// Se muestra la excepción de la aplicacion
			FacesUtils.agregarMensajeError(e.getMessage(), e.toString());
		} catch (NestableRuntimeException e) {
			e.printStackTrace();
			Throwable causa = obtenerPrimeraCausa(e);
			if (causa instanceof AssertionFailure) {
				// exepcion invalidante la session debe ser cerrada
				sessionUtil.cerrarSession();
			} else if (causa instanceof BatchUpdateException) {
				System.out.println();
				FacesUtils.agregarMensajeError(((BatchUpdateException) causa)
						.getNextException().getMessage(), "");
				sessionUtil.cerrarSession();
			} else if (causa instanceof TransientObjectException) {
				FacesUtils.agregarMensajeError(causa.getMessage(), "");
				sessionUtil.cerrarSession();
			} else if (causa instanceof NonUniqueObjectException) {
				FacesUtils
						.agregarMensajeError(
								"Hubo un error de comunicacion. Favor intente la operacion nuevamente",
								"");
				sessionUtil.cerrarSession();

			} else if (causa instanceof LazyInitializationException) {
			} else if (causa instanceof ObjectDeletedException) {
				((ObjectDeletedException) causa).getIdentifier();
				sessionUtil.cerrarSession();
			} else {

				sessionUtil.cerrarSession();
			}
		} catch (Exception e) {
			// Verificar las excepciones de base de datos para mostrar el
			// mensaje correspondiente al usuario.
			Throwable causa = obtenerPrimeraCausa(e);

			e.toString();
			e.printStackTrace();
			if (causa instanceof SQLException) {
				System.out.println(causa.getMessage());
				if (causa.getClass().isAssignableFrom(
						BatchUpdateException.class)) {
					BatchUpdateException batchUpdateException = ((BatchUpdateException) causa);
					FacesUtils.agregarMensajeError(batchUpdateException
							.getNextException().getMessage(),
							batchUpdateException.getNextException()
									.getMessage().toString());
				} else {
					FacesUtils.agregarMensajeError(causa.getMessage(),
							causa.toString());
				}
			} else if (causa instanceof NonUniqueObjectException) {
				NonUniqueObjectException objectException = ((NonUniqueObjectException) causa);
				String mensaje = "La entidad '"
						+ objectException.getEntityName()
						+ "' con el identificador '"
						+ objectException.getIdentifier()
						+ "', ya se encuentra asociada.";
				FacesUtils.agregarMensajeError(mensaje, "");

			} else {
				throw e;
			}
		}

		return retorno;
	}

	/**
	 * Busca la primera causa de la excepción.
	 * 
	 * @param <T>
	 *            El tipo de la excepción buscada.
	 * @param clase
	 *            La clase de la excepción buscada.
	 * @param ex
	 *            La excepción original, sobre la cual buscar.
	 * @return La primera causa de la excepción.
	 */
	protected Throwable obtenerPrimeraCausa(Throwable ex) {

		Throwable cause = ex.getCause() != null ? ex.getCause() : ex;
		while ((cause.getCause() != null && cause != cause.getCause())) {
			cause = cause.getCause();
		}
		return cause;
	}
}