package co.com.sigepro.negocio.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Correo;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.negocio.serv.CorreoServicio;
import co.com.sigepro.negocio.utilidades.MyAuthenticator;
import co.com.sigepro.negocio.utilidades.PropiedadesUtils;

@Service
public class CorreoServicioImpl implements CorreoServicio {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -2967833162597022693L;

	/**
	 * Variables para el envio del correo
	 */
	private static final String SENDER_NAME = PropiedadesUtils.getMensaje(
			"co.com.sigepro.configuracion.correo", "correo.sender.adress");
	private static final String SERVIDOR = PropiedadesUtils.getMensaje(
			"co.com.sigepro.configuracion.correo", "correo.servidor");

	private static final boolean DEBUG = false;

	/**
	 * Metodo utilizado para el envio de correo mediante un servidor SMTP
	 * Utiliza un objeto Correo con toda la info necesaria para el envío
	 */
	@Override
	public boolean enviarCorreo(Correo correo) {

		Address sender = null;
		try {
			// Se crea la direccion que envia los correos
			sender = new InternetAddress(correo.getOrigen().toString());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MimeMultipart multipart = new MimeMultipart();
		Authenticator auth = new MyAuthenticator();

		Properties properties = new Properties();
		properties.put("mail.smtp.host", SERVIDOR);
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getDefaultInstance(properties, auth);
		session.setDebug(DEBUG);

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(sender);
			// Se coloca el destinatario del correo
			msg.addRecipients(Message.RecipientType.TO, correo.getDireccion()
					.toString());
			msg.setSubject(correo.getAsunto());
			msg.setSentDate(new Date());

			// Si el correo tiene un archivo adjunto
			if (correo.getAdjunto() != null) {
				BodyPart messageBodyPart2 = new MimeBodyPart();
				FileDataSource source = new FileDataSource(correo.getAdjunto());
				messageBodyPart2.setDataHandler(new DataHandler(source));
				messageBodyPart2.setFileName(correo.getNombreAdjunto());
				multipart.addBodyPart(messageBodyPart2);
			}
			// Pone las partes en el mensaje
			msg.setContent(correo.getCuerpo(), "text/plain");
			msg.setText(correo.getCuerpo(), "US-ASCII", "plain");
			Transport.send(msg);
		} catch (Exception ex) {
			System.out.println("error = " + ex);
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Metodo que crea un objeto de tipo Correo con toda la info. necesaria,
	 * ademas de reeplazar los parametros necesarios en el String del archivo
	 */
	public Correo crearCorreo(Persona persona, String nombre[]) {

		String datos = null;
		Correo correo = new Correo();
		String archivo = nombre[0];
		datos = archivo.replaceAll("\\#usuario", persona.getNombre() + " "
				+ persona.getApellido());
		datos = datos.replaceAll("\\#fecha", fechaActual());
		datos = datos.replaceAll("\\#login", persona.getLogin());
		datos = datos.replaceAll("\\#asunto", persona.getLogin());
		datos = datos.replaceAll("\\#lugar", persona.getLogin());

		if (persona.getContrasena() != null) {
			// StringBuffer buffer = retornaCadenaEspecial(usuario.getClave());
			datos = datos.replaceAll("\\#clave", persona.getContrasena());
		}
		correo.setCuerpo(datos);
		correo.setDireccion(persona.getCorreo());
		correo.setOrigen(SENDER_NAME);
		correo.setAsunto(nombre[1]);
		return correo;
	}

	/**
	 * Metodo que crea un objeto de tipo Correo con toda la info. necesaria,
	 * ademas de reeplazar los parametros necesarios en el String del archivo
	 * @param destinatario 
	 */
	public Correo crearCorreo(String destinatario, String asunto, String cuerpo) {
		Correo correo = new Correo();
		correo.setOrigen(SENDER_NAME);
		correo.setDireccion(destinatario);
		correo.setAsunto(asunto);
		correo.setCuerpo(cuerpo);
		return correo;

	}
	public StringBuffer retornaCadenaEspecial(String clave) {

		Hashtable<String, String> hash = new Hashtable<String, String>();
		hash.put("<", "&lt;");
		hash.put(">", "&gt;");
		hash.put("&", "&amp;");
		hash.put("\"", "&quot;");

		StringBuffer append = new StringBuffer();

		for (int i = 0; i < clave.length(); i++) {
			String caracter = clave.substring(i, i + 1);
			if (hash.get(caracter) != null) {
				append.append(hash.get(caracter));
			} else {
				append.append(caracter);
			}
		}
		return append;
	}

	/**
	 * Devuelve la fecha actual
	 */
	@Override
	public String fechaActual() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return (c.get(Calendar.DAY_OF_MONTH) + "/"
				+ (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
	}

}
