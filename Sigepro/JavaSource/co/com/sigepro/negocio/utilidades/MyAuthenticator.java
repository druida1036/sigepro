package co.com.sigepro.negocio.utilidades;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {

	private static final String SENDER_NAME = PropiedadesUtils.getMensaje(
			"co.com.sigepro.configuracion.correo",
			"correo.sender.adress");

	private static final String SENDER_PASS = PropiedadesUtils.getMensaje(
			"co.com.sigepro.configuracion.correo", "correo.sender.pass");

	protected PasswordAuthentication getPasswordAuthentication() {
		PasswordAuthentication pass = new PasswordAuthentication(SENDER_NAME,
				SENDER_PASS);
		return pass;
	}

}
