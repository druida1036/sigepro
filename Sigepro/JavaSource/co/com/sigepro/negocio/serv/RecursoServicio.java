package co.com.sigepro.negocio.serv;

import java.util.List;

import co.com.sigepro.entidades.Recurso;

public interface RecursoServicio {
	public Recurso guardar(Recurso recurso);
	public List<Recurso> listado(Recurso recurso);
	public Recurso cargar(Integer id);

}
