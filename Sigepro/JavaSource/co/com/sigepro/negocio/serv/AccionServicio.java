package co.com.sigepro.negocio.serv;

import java.util.List;

import co.com.sigepro.entidades.Accion;

public interface AccionServicio {
	public List<Accion> listado(Accion accion);
	public Accion cargar(Integer id);
}
