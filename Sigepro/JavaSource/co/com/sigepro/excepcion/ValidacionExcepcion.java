package co.com.sigepro.excepcion;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import co.com.sigepro.control.util.Constantes;
import co.com.sigepro.control.util.PropiedadesUtils;

/**
 * Representa una excepci�n originada al validar un elemento incorrecto. Contiene un mensaje que describe por qu� el
 * elemento es inv�lido.
 * 
 * @author Luis Fontalvo (lfontalvo@aliaddos.com)
 * @version 1.0 04-02-2010
 * 
 */
public class ValidacionExcepcion extends AplicacionExcepcion {

	/** use serialVersionUID from JDK 1.0.2 for interoperability */
	private static final long serialVersionUID = 352209840834422001L;

	private List<String> erroresValidacion;

	/**
	 * Crea una nueva excepci�n de validaci�n
	 */
	public ValidacionExcepcion() {

		super();

		this.erroresValidacion = new LinkedList<String>();
	}

	/**
	 * Crea una nueva excepci�n de validaci�n
	 * 
	 * @param clave
	 *            La clave del error en el archivo de idioma por defecto.
	 */
	public ValidacionExcepcion(String clave) {

		this();

		agregarErrorValidacion(clave);
	}

	/**
	 * Crea una nueva excepci�n de validaci�n con el mensaje de error especificado.
	 * 
	 * @param clave
	 *            La clave del error en el archivo de idioma por defecto.
	 * @param parametros
	 *            Los par�metros del error.
	 */
	public ValidacionExcepcion(String clave, Object[] parametros) {

		this();

		agregarErrorValidacion(clave, parametros);
	}

	/**
	 * Crea una nueva excepci�n de validaci�n con el mensaje de error especificado.
	 * 
	 * @param nombreArchivo
	 *            El nombre del archivo de idioma del error.
	 * @param clave
	 *            La clave del error en el archivo de idioma.
	 */
	public ValidacionExcepcion(String nombreArchivo, String clave) {

		this();

		agregarErrorValidacion(nombreArchivo, clave);
	}

	/**
	 * Crea una nueva excepci�n de validaci�n con el mensaje de error especificado.
	 * 
	 * @param nombreArchivo
	 *            El nombre del archivo de idioma del error.
	 * @param clave
	 *            La clave del error en el archivo de idioma.
	 * @param parametros
	 *            Los par�metros del error.
	 */
	public ValidacionExcepcion(String nombreArchivo, String clave,
			Object[] parametros) {

		this();

		agregarErrorValidacion(nombreArchivo, clave, parametros);
	}

	@Override
	public String getLocalizedMessage() {

		return this.erroresValidacion.toString();
	}

	@Override
	public String getMessage() {

		return this.erroresValidacion.toString();
	}

	/**
	 * Devuelve una lista (no modificable) de los errores de validaci�n almacenados en esta excepci�n.
	 * 
	 * @return Una lista (no modificable) de los errores de validaci�n almacenados en esta excepci�n.
	 */
	public List<String> getErroresValidacion() {

		return Collections.unmodifiableList(this.erroresValidacion);
	}

	/**
	 * Agrega un nuevo error de validaci�n
	 * 
	 * @param clave
	 *            La clave del error en el archivo de idioma por defecto.
	 */
	public void agregarErrorValidacion(String clave) {

		agregarErrorValidacion(null, clave, null);
	}

	/**
	 * Agrega un nuevo error de validaci�n con el mensaje de error especificado.
	 * 
	 * @param clave
	 *            La clave del error en el archivo de idioma por defecto.
	 * @param parametros
	 *            Los par�metros del error.
	 */
	public void agregarErrorValidacion(String clave, Object[] parametros) {

		agregarErrorValidacion(null, clave, parametros);
	}

	/**
	 * Agrega un nuevo error de validaci�n con el mensaje de error especificado.
	 * 
	 * @param nombreArchivo
	 *            El nombre del archivo de idioma del error.
	 * @param clave
	 *            La clave del error en el archivo de idioma.
	 */
	public void agregarErrorValidacion(String nombreArchivo, String clave) {

		agregarErrorValidacion(nombreArchivo, clave, null);
	}

	/**
	 * Agrega un nuevo error de validaci�n con el mensaje de error especificado.
	 * 
	 * @param nombreArchivo
	 *            El nombre del archivo de idioma del error.
	 * @param clave
	 *            La clave del error en el archivo de idioma.
	 * @param parametros
	 *            Los par�metros del error.
	 */
	public void agregarErrorValidacion(String nombreArchivo, String clave,
			Object[] parametros) {

		nombreArchivo = nombreArchivo != null ? nombreArchivo
				: Constantes.ARCHIVO_MENSAJES;

		this.erroresValidacion.add(PropiedadesUtils.getMensaje(nombreArchivo,
				clave, parametros));
	}

	/**
	 * Lanza esta excepcion si existen errores de validaci�n agregados a esta excepci�n. Equivale a: <code>
	 * if (!getErroresValidacion().isEmpty()) {	throw this;	}
	 * </code>
	 * 
	 * @throws ValidacionExcepcion
	 *             Si existen errores de validaci�n agregados.
	 */
	public void lanzarExcepcionSiHayErrores() throws ValidacionExcepcion {

		if (!this.erroresValidacion.isEmpty()) {
			throw this;
		}
	}
}
