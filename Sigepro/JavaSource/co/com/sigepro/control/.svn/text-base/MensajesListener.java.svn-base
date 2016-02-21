package co.com.sigepro.control;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import co.com.sigepro.control.util.FacesUtils;

public class MensajesListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8717499142091227743L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		

	}

	@Override
	public void beforePhase(PhaseEvent event) {

		FacesUtils.mostrarMensajes();

	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RENDER_RESPONSE;
	}

}
