package co.com.sigepro.excepcion;

/**
 * @author Luis Francisco Fontalvo Romero (lfontalvo@aliaddos.com)
 * @version 1.0 04-02-2010
 * 
 */
public class AplicacionExcepcion extends RuntimeException {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -3575267805626821430L;

	/**
	 * Crea una nueva Excepción de Aplicación con un mensaje nulo y sin inicializar la causa.
	 */
	public AplicacionExcepcion() {
		super();
	}

	/**
	 * Crea una nueva Excepción de Aplicación con el mensaje y causa especificados.
	 * 
	 * @param mensaje
	 *            El mensaje de detalle.
	 * @param causa
	 *            La causa de la excepción. Un valor nulo es permitido, indicando que la causa no existe o es
	 *            desconocida.
	 */
	public AplicacionExcepcion(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	/**
	 * Crea una nueva Excepción de Aplicación con el mensaje especificado y sin inicializar la causa.
	 * 
	 * @param mensaje
	 *            El mensaje de detalle.
	 */
	public AplicacionExcepcion(String mensaje) {
		super(mensaje);
	}

	/**
	 * Crea una nueva Excepción de Aplicación con la causa especificada y un mensaje obtenido de la forma
	 * <code>(causa == null ? null : causa.toString())</code>.
	 * 
	 * @param causa
	 *            La causa de la excepción. Un valor nulo es permitido, indicando que la causa no existe o es
	 *            desconocida.
	 */
	public AplicacionExcepcion(Throwable causa) {
		super(causa);
	}
}
