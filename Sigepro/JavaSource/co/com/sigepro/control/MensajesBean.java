package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.springframework.stereotype.Component;
@Component
public class MensajesBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2840555007419966083L;
	private List<FacesMessage> mensajes = new ArrayList<FacesMessage>();

	public List<FacesMessage> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<FacesMessage> mensajes) {
		this.mensajes = mensajes;
	}

	public boolean isHayMensajes(){
		return mensajes.size() > 0;
	}
	
	public void limpiarMensajes(){
		mensajes.clear();
	}
}
