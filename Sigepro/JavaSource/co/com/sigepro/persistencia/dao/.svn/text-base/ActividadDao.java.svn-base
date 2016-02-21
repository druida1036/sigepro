package co.com.sigepro.persistencia.dao;

import java.util.List;
import java.util.Map;

import co.com.sigepro.entidades.Actividad;

public interface ActividadDao extends GenericDao<Actividad, Integer> {
	public List<Actividad> listado(Actividad actividad);

	public List<Actividad> noPrecedidas(Integer idMetdologia);

	public Actividad actividadPorMetodologia(Integer idMetdologia,
			String nombreProceso, String nombreActividad);

	public List<Actividad> actividadesPorMetodologia(Integer idMetdologia);

	public List<Actividad> actividadesPorMetodologia(Integer idMetdologia,
			int primerResultado, int maxResultado);

	List<Actividad> filtroActivida(Actividad actividad,
			Map<String, String> filtro);

	public List<Object[]> horasPorMesEstimadas(Integer idMetodologia);

	public List<Object[]> horasPorMesEjecutadas(Integer idMetodologia);
}
