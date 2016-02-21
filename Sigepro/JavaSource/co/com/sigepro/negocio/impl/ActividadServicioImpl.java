package co.com.sigepro.negocio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Actividad;
import co.com.sigepro.entidades.Paginador;
import co.com.sigepro.negocio.serv.ActividadServicio;
import co.com.sigepro.persistencia.dao.ActividadDao;

@Service
public class ActividadServicioImpl implements ActividadServicio {
	@Autowired
	private ActividadDao dao;

	public Actividad guardar(Actividad actividad) {
		return dao.guardar(actividad);
	}

	public List<Actividad> listado(Actividad actividad) {
		return dao.listado(actividad);
	}

	public Actividad cargar(Integer id) {
		return dao.cargar(id);
	}

	public void mezlcar(Object entidad) {
		dao.mezclar(entidad);

	}

	public void copiarPrecedentes(Integer idMetodologiaBase,
			Integer idMetodologiaDestino) {
		for (Actividad actividad : dao
				.actividadesPorMetodologia(idMetodologiaBase)) {
			Actividad actividadCopia = dao.actividadPorMetodologia(
					idMetodologiaDestino, actividad.getProceso().getNombre(), actividad.getNombre());
			for (Actividad precedente : actividad.getPrecedentes()) {
				Actividad precedenteCopia = dao.actividadPorMetodologia(
						idMetodologiaDestino, precedente.getProceso().getNombre(), precedente.getNombre());
				actividadCopia.getPrecedentes().add(precedenteCopia);
			}
			dao.guardar(actividadCopia);
		}

	}

	public List<Actividad> actividadesPorMetodologia(Integer idMetdologia) {
		return dao.actividadesPorMetodologia(idMetdologia);
	}

	public List<Actividad> actividadesPorMetodologia(Integer idMetdologia,
			Paginador paginador) {

		int maxResultados = dao.actividadesPorMetodologia(idMetdologia).size() - 1;
		paginador.setMaxResultados(maxResultados);

		return dao.actividadesPorMetodologia(idMetdologia,
				paginador.getIndice(), paginador.getTamañoPagina());
	}

	public Integer calcularTotalHorasEstimadas(Integer idMetodologia) {
		List<Object[]> resultados = new ArrayList<Object[]>();
		resultados = dao.horasPorMesEstimadas(idMetodologia);
		Integer total = 0;
		for (Object[] registrio : resultados) {
			total = total + Integer.parseInt(registrio[1].toString());
		}

		return total;
	}

	public Integer calcularTotalHorasEjecutadas(Integer idMetodologia) {
		List<Object[]> resultados = new ArrayList<Object[]>();
		resultados = dao.horasPorMesEjecutadas(idMetodologia);
		Integer total = 0;
		for (Object[] registrio : resultados) {
			total = total + Integer.parseInt(registrio[1].toString());
		}

		return total;
	}

	public List<Actividad> noPrecedidas(Integer idMetdologia) {
		return dao.noPrecedidas(idMetdologia);
	}
}
