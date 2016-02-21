package co.com.sigepro.negocio.serv;

import java.util.List;

import co.com.sigepro.entidades.Actividad;
import co.com.sigepro.entidades.Paginador;

public interface ActividadServicio {
	public Actividad guardar(Actividad actividad);

	public List<Actividad> listado(Actividad actividad);

	public Actividad cargar(Integer id);

	public void mezlcar(Object entidad);

	public void copiarPrecedentes(Integer idMetodologiaBase,
			Integer idMetodologiaDestino);

	public List<Actividad> actividadesPorMetodologia(Integer idMetodologia);

	public List<Actividad> actividadesPorMetodologia(Integer idMetodologia,
			Paginador paginador);
	
	public Integer calcularTotalHorasEstimadas(Integer idMetodologia);
	public Integer calcularTotalHorasEjecutadas(Integer idMetodologia);
	public List<Actividad> noPrecedidas(Integer idMetdologia);
}
