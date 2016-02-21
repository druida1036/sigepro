package co.com.sigepro.negocio.impl;

import javax.servlet.http.HttpServletRequest;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.EntidadGenerica;

public class ServicioGenerico  {
	protected void actualizarCaposAuditoria(EntidadGenerica entidadGenerica){
		HttpServletRequest request = FacesUtils.getRequest();
		entidadGenerica.setCtlIp(request.getRemoteHost());
		//TODO cuando se implemente la seguridad, ingresar esta variable.  
		entidadGenerica.setCtlUsuario("Sistema");		
		java.util.Date fechaActual = new java.util.Date();
		entidadGenerica.setCtlFecMod(fechaActual);
	}
	
}
