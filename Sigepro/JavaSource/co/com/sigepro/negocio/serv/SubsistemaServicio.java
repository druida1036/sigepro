package co.com.sigepro.negocio.serv;

import java.util.List;

import co.com.sigepro.entidades.Subsistema;

public interface SubsistemaServicio {
	public List<Subsistema> listado(Subsistema subsistema);
	public Subsistema cargar(Integer id);
	public List<Subsistema> getSubsistemasXRol(Integer id);
}
