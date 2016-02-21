package co.com.sigepro.persistencia.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import co.com.sigepro.entidades.Actividad;
import co.com.sigepro.persistencia.dao.ActividadDao;

@Repository("actividadDao")
public class ActividadDAOImpl extends
		AbstractHibernateDaoImpl<Actividad, Integer> implements ActividadDao {

	private static final String ACTIVIDAD_NO_ASIGNADA_PRECEDENTE = " and a.precedentes.size = 0";
	private static final String ACTIVIDAD_POR_METODOLOGIA = "select a from Actividad a, Metodologia m, Categoria c, Proceso p where c.metodologia.id = m.id and p.categoria.id = c.id and a.proceso.id = p.id "
			+ "and m.id = :id  and p.nombre = :nombreProceso and a.nombre = :nombreActividad";
	private static final String ACTIVIDADES_POR_METODOLOGIA = "select a from Actividad a, Metodologia m, Categoria c, Proceso p where c.metodologia.id = m.id and p.categoria.id = c.id and a.proceso.id = p.id "
			+ "and m.id = :id ";
	private static final String ORDENACION = " order by p.id, a.orden";
	private static final String SUMA_HORAS_MES_ESTIMADAS = "select to_char(a.fechaInicio , 'YYYY-MM'),"
			+ " sum(a.duracion) "
			+ " from Actividad a, Metodologia m, Categoria c, Proceso p"
			+ " where c.metodologia.id = m.id and p.categoria.id = c.id and a.proceso.id = p.id"
			+ " and m.id = :id group by 1";
	private static final String SUMA_HORAS_MES_EJECUTADAS = "select to_char(a.fechaInicioReal , 'YYYY-MM'),"
			+ " sum(a.duracionReal) "
			+ " from Actividad a, Metodologia m, Categoria c, Proceso p"
			+ " where c.metodologia.id = m.id and p.categoria.id = c.id and a.proceso.id = p.id"
			+ " and m.id = :id group by 1";

	public ActividadDAOImpl() {
		super();
	}

	public List<Object[]> horasPorMesEstimadas(Integer idMetodologia) {
		List<Object[]> resultados = new ArrayList<Object[]>();

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", idMetodologia);
		resultados = ejecutarConsulta1(SUMA_HORAS_MES_ESTIMADAS, parametros);
		return resultados;
	}

	public List<Object[]> horasPorMesEjecutadas(Integer idMetodologia) {
		List<Object[]> resultados = new ArrayList<Object[]>();

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", idMetodologia);
		resultados = ejecutarConsulta1(SUMA_HORAS_MES_EJECUTADAS, parametros);
		return resultados;
	}

	public List<Actividad> noPrecedidas(Integer idMetdologia) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", idMetdologia);
		return ejecutarConsulta(ACTIVIDADES_POR_METODOLOGIA
				+ ACTIVIDAD_NO_ASIGNADA_PRECEDENTE + ORDENACION, parametros);
	}

	public Actividad actividadPorMetodologia(Integer idMetdologia, String nombreProceso,
			String nombreActividad) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", idMetdologia);
		parametros.put("nombreProceso", nombreProceso);
		parametros.put("nombreActividad", nombreActividad);
		return consultaUnicoResultado(ACTIVIDAD_POR_METODOLOGIA + ORDENACION,
				parametros);
	}

	public List<Actividad> actividadesPorMetodologia(Integer idMetdologia) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", idMetdologia);
		return ejecutarConsulta(ACTIVIDADES_POR_METODOLOGIA, parametros);
	}

	public List<Actividad> actividadesPorMetodologia(Integer idMetdologia,
			int primerResultado, int maxResultado) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", idMetdologia);
		return ejecutarConsulta(ACTIVIDADES_POR_METODOLOGIA, parametros,
				primerResultado, maxResultado);
	}

	@SuppressWarnings("unchecked")
	public List<Actividad> filtroActivida(Actividad actividad,
			Map<String, String> filtro) {
		List<Actividad> actividades = new ArrayList<Actividad>();

		Criteria criteriaActividad = getSession()
				.createCriteria(Actividad.class)
				.createAlias("proceso", "proceso")
				.createAlias("categoria", "categoria")
				.createAlias("metodologia", "metodologia");

		actividades = criteriaActividad.list();
		return actividades;
	}

}