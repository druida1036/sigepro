package co.com.sigepro.negocio.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.control.util.Constantes;
import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Notificacion;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.negocio.serv.PersonaServicio;
import co.com.sigepro.negocio.utilidades.ConstantesNotificacion;
import co.com.sigepro.negocio.utilidades.SeguridadUtil;
import co.com.sigepro.persistencia.dao.PersonaDao;

@Service
public class PersonaServicioImpl extends ServicioGenerico implements
		PersonaServicio {

	private Notificacion notificacion = new Notificacion();

	/**
	 * Constantes encargadas de controlar el tipo de caracter de la contrase�a
	 */
	private static final String TIPO_NUMERO = "numero";
	private static final String TIPO_MAYUSCULA = "mayuscula";
	private static final String TIPO_MINUSCULA = "minuscula";
	private static final String TIPO_ESPECIAL = "especial";
	private static final String TIPO_ASCII = "ascii";

	@Autowired
	private NotificacionServicioImpl notificacionServicio;

	@Autowired
	private PersonaDao dao;

	public Persona guardar(Persona persona) {
		actualizarCaposAuditoria(persona);
		generarPasswordYnotificar(persona);
		persona = dao.guardar(persona);
		
		return persona;
	}
	
	public Persona actualizar(Persona persona) {
		actualizarCaposAuditoria(persona);
		generarPasswordYnotificar(persona);
		dao.actualizar(persona);		
		return persona;
	}

	public List<Persona> listado(Persona persona) {
		return dao.listado(persona);
	}

	public Persona cargar(Integer id) {
		return dao.cargar(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.chartis.portal.negocio.interfaz.UsuarioServicio#generarPasswordUsuario
	 * (co.com.chartis.portal.entidad.Usuario)
	 */

	public String generarPasswordUsuario(Persona persona) {

		Integer longitudMinimaClave = 4;
		Character[] password = new Character[longitudMinimaClave];
		List<String> tipoCaracter = new ArrayList<String>();
		tipoCaracter.add(TIPO_NUMERO);
		tipoCaracter.add(TIPO_MINUSCULA);
		tipoCaracter.add(TIPO_MAYUSCULA);
		tipoCaracter.add(TIPO_ESPECIAL);
		password = ingresarCaracter(password, TIPO_ESPECIAL);
		System.out.println(password);
		password = ingresarCaracter(password, TIPO_NUMERO);
		System.out.println(password);
		password = ingresarCaracter(password, TIPO_MAYUSCULA);
		System.out.println(password);

		if (tipoCaracter.size() == 4) {
			boolean bandera = false;
			do {
				bandera = false;
				for (int i = 0; i < password.length; i++) {
					if (password[i] == null) {
						bandera = true;
						break;
					}
				}
				password = ingresarCaracter(password, TIPO_ASCII);
			} while (bandera);
		} else {
			boolean bandera = false;
			do {
				bandera = false;
				for (int i = 0; i < password.length; i++) {
					if (password[i] == null) {
						bandera = true;
						break;
					}
				}
				int numero = (int) (Math.random() * (tipoCaracter.size() - 1));
				password = ingresarCaracter(password, tipoCaracter.get(numero));
			} while (bandera);
		}
		String clave = "";
		for (int i = 0; i < password.length; i++) {
			clave += password[i];
		}
		persona.setContrasena(clave);
		SecretKey generarClave = SeguridadUtil
				.generarClave(SeguridadUtil.secretKey);
		String encriptada = SeguridadUtil.encriptar(persona.getContrasena(),
				generarClave);
		return encriptada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.chartis.portal.negocio.interfaz.UsuarioServicio#
	 * generarPasswordYnotificar(co.com.chartis.portal.entidad.Usuario)
	 */

	public boolean generarPasswordYnotificar(Persona persona) {
		if (persona.getId() == null) {
			String password = generarPasswordUsuario(persona);
			persona.setContrasena(password);
		}
		persona.setCtlEstado(Constantes.ESTADO_ANULADO);
		persona.setCtlIp("127.0.0.1");
		persona.setCtlUsuario(Constantes.SISTEMA);
		notificacion = cargarValoresNotificacion(persona);
		notificacionServicio.enviarNotificacion(persona.getCorreo(),
				notificacion);
		return true;

	}

	private Notificacion cargarValoresNotificacion(Persona persona) {
		Notificacion notificacion = notificacionServicio.cargar(1);
		String usuario = persona.getNombre() + " ";
		usuario = usuario + persona.getApellido() + " ";
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		SecretKey secretKey = SeguridadUtil
				.generarClave(SeguridadUtil.secretKey);
		String clave = SeguridadUtil.desencriptar(persona.getContrasena(), secretKey);
		FacesUtils.setValorVariable(ConstantesNotificacion.VN_USUARIO, usuario);
		FacesUtils.setValorVariable(ConstantesNotificacion.VN_FECHA,
				format.format(new Date()));
		FacesUtils.setValorVariable(ConstantesNotificacion.VN_LOGIN,
				persona.getLogin());
		FacesUtils.setValorVariable(ConstantesNotificacion.VN_CLAVE,
				clave);
		return notificacion;
	}

	private Character[] ingresarCaracter(Character[] password,
			String tipoDeCaracter) {
		List<Integer> numero = new ArrayList<Integer>();
		for (int i = 0; i < password.length; i++) {
			if (password[i] == null) {
				numero.add(i);
			}
		}
		int posicion = (int) (Math.random() * (numero.size() - 1));
		if (numero.size() != 0) {
			password[numero.get(posicion).intValue()] = obtenerCarater(tipoDeCaracter);
		}
		return password;
	}

	private char obtenerCarater(String tipoCaracter) {
		char caracter = ' ';
		if (TIPO_MINUSCULA.equals(tipoCaracter)) {
			caracter = (char) (Math.random() * (122 - 97) + 97);
		} else if (TIPO_MAYUSCULA.equals(tipoCaracter)) {
			caracter = (char) (Math.random() * (90 - 65) + 65);
		} else if (TIPO_NUMERO.equals(tipoCaracter)) {
			caracter = (char) (Math.random() * (57 - 48) + 48);
		} else if (TIPO_ESPECIAL.equals(tipoCaracter)) {
			caracter = (char) (Math.random() * (47 - 33) + 33);
		} else if (TIPO_ASCII.equals(tipoCaracter)) {
			caracter = (char) (Math.random() * (125 - 33) + 33);
		}
		return caracter;

	}

	public Persona getUsuarioPorLogin(String login) {
		Persona persona = new Persona();
		List<Persona> personas;
		persona.setLogin(login);
		personas = dao.listado(persona);
		if (personas.size() > 0)
			return cargar(personas.get(0).getId());
		return null;
	}

	public boolean validarClaveIngreso(Persona usuario) {
		Persona persona = getUsuarioPorLogin(usuario.getLogin());
		SecretKey secretKey = SeguridadUtil
				.generarClave(SeguridadUtil.secretKey);
		String claveIngresada = SeguridadUtil.encriptar(
				usuario.getContrasena(), secretKey);
		if(persona == null)
			return false;
		return claveIngresada.equals(persona.getContrasena());
	}

}
