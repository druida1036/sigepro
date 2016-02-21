package co.com.sigepro.negocio.serv;

import java.util.List;

import co.com.sigepro.entidades.Especialidad;

public interface EspecialidadServicio {
	public Especialidad guardar(Especialidad especialidad);
	public List<Especialidad> listado(Especialidad especialidad);
	public Especialidad cargar(Integer id);

}
