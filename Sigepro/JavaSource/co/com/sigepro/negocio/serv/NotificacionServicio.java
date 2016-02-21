package co.com.sigepro.negocio.serv;

import java.util.List;

import co.com.sigepro.entidades.Notificacion;

public interface NotificacionServicio {
	public Notificacion guardar(Notificacion notificacion);
	public List<Notificacion> listado(Notificacion notificacion);
	public Notificacion cargar(Integer id);

}
