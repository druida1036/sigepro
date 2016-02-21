package co.com.sigepro.negocio.serv;

import java.util.List;

import co.com.sigepro.entidades.Calendario;

public interface CalendarioServicio {
	public Calendario guardar(Calendario calendario);
	public Calendario cargar(Integer id);
	public List<Calendario> listado(Calendario calendario);


}
