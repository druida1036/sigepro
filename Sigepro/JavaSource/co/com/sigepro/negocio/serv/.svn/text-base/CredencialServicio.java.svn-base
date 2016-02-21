package co.com.sigepro.negocio.serv;

import co.com.sigepro.entidades.Credencial;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.entidades.Subsistema;



/**
 * @author Jorge Armando Martinez Sanchez (jamartinez@aliaddos.com)
 * 
 */
public interface CredencialServicio {

	/**
	 * Metodo encargado de crear la credencial segun el subsistema
	 * 
	 * @param usuario
	 * @return
	 */
	Credencial crearCredencial(Persona usuario);

	/**
	 * Metodo encargado de crear una credencial del usuario activo
	 * 
	 * @return
	 */
	Credencial crearCredencialUsuarioActivo();

	/**
	 * Metodo encargado de validar los permisos sobre la accion
	 * 
	 * @param idAccion
	 * @return
	 */
	boolean consultarPermisoOpcion(Long idAccion);

	/**
	 * Metodo encargado de asignar el subsistema al usuario
	 * 
	 * @param subsistema
	 * @return el Usuario con una nueva Credencia
	 */
	Persona asignarCredencialUsuario(Subsistema subsistema);
}
