package co.com.sigepro.control.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Clase que proporciona métodos de utilidad para recuperar un mensaje de un
 * archivo de propiedades.
 * 
 * @author Luis Francisco Fontalvo Romero (lfontalvo@aliaddos.com)
 */
public final class PropiedadesUtils {

	/**
	 * Constructor privado. Esta clase no se puede instanciar.
	 */
	private PropiedadesUtils() {
		super();
	}

	/**
	 * Permite recuperar un mensaje de un archivo de propiedades a partir del
	 * nombre de dicho archivo y la clave del mensaje.
	 * 
	 * @param nombreArchivo
	 *            El nombre del archivo.
	 * @param clave
	 *            La clave del mensaje.
	 * @return El mensaje recuperado.
	 */
	public static String getMensaje(String nombreArchivo, String clave) {
		return getMensaje(nombreArchivo, clave, null);
	}

	/**
	 * Permite recuperar un mensaje de un archivo de propiedades a partir del
	 * nombre de dicho archivo y la clave del mensaje.
	 * 
	 * @param nombreArchivo
	 *            El nombre del archivo.
	 * @param clave
	 *            La clave del mensaje.
	 * @param parametros
	 *            Los parametros opcionales del mensaje.
	 * @return El mensaje recuperado.
	 */
	public static String getMensaje(String nombreArchivo, String clave,
			Object[] parametros) {

		String mensaje = null;
		ResourceBundle archivoPropiedades = ResourceBundle
				.getBundle(nombreArchivo);

		try {
			mensaje = archivoPropiedades.getString(clave);
		} catch (MissingResourceException e) {
			mensaje = "?? Clave '" + clave + "' no encontrada ??";
		}

		if (parametros != null) {
			MessageFormat formato = new MessageFormat(mensaje);
			mensaje = formato.format(parametros, new StringBuffer(), null)
					.toString();
		}
		return mensaje;
	}

	/**
	 * Permite remplazar los comodines (?) de un mensaje por los valores
	 * correspondientes.
	 * 
	 * @param mensaje
	 *            La cadena de texto con el mensaje
	 * @param parametros
	 *            Arreglo de parametros para ser remplazados
	 * @return Una cadena de texto con el mensaje formateado. si el arreglo del
	 *         parametros es vacio retorna el mismo mensaje
	 * 
	 */
	public static String formatearMensaje(String mensaje, Object[] parametros) {
		if (parametros != null) {
			MessageFormat formato = new MessageFormat(mensaje);
			mensaje = formato.format(parametros, new StringBuffer(), null)
					.toString();
		}
		return mensaje;
	}
}
