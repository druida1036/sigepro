package co.com.sigepro.negocio.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Actividad;
import co.com.sigepro.entidades.Metodologia;
import co.com.sigepro.negocio.serv.ActividadServicio;
import co.com.sigepro.negocio.serv.MetodologiaServicio;
import co.com.sigepro.negocio.utilidades.Utilidades;
import co.com.sigepro.negocio.utilidades.Utilidades.TipoOperacion;
import co.com.sigepro.persistencia.dao.MetodologiaDao;
@Service
public class MetodologiaServicioImpl implements MetodologiaServicio {
	@Autowired
	private MetodologiaDao dao;
	@Autowired
	private ActividadServicio actividadServicio;
	
	public Metodologia guardar(Metodologia metodologia) {
		return dao.guardar(metodologia);
	}
	
	public List<Metodologia> listado(Metodologia metodologia){
		return dao.listado(metodologia);
	}
	
	public Metodologia cargar(Integer id){
		return dao.cargar(id);
	}

	
	public void mezlcar(Object entidad) {
		dao.mezclar(entidad);
		
	}
	
	public Metodologia copiar(Metodologia fuente, String nuevoNombre){
		Metodologia copia = new Metodologia();
		HashMap<String, TipoOperacion> propiedades = new HashMap<String, TipoOperacion>();
		
		propiedades.put("metodologia.categorias", TipoOperacion.REPLICA_TOTAL);
		propiedades.put("categoria.procesos", TipoOperacion.REPLICA_TOTAL);
		propiedades.put("proceso.actividades", TipoOperacion.REPLICA_TOTAL);
		propiedades.put("actividad.plantillas", TipoOperacion.REPLICA_TOTAL);
		propiedades.put("plantilla.documento", TipoOperacion.REPLICA_TOTAL);
		
		// relaciones con objetos quedeben mantenerse no crearse
		propiedades.put("actividad.notificaciones", TipoOperacion.COPIA);
		propiedades.put("actividad.herramientas", TipoOperacion.COPIA);
		propiedades.put("actividad.rol", TipoOperacion.COPIA);
		propiedades.put("actividad.persona", TipoOperacion.COPIA);


		
		Utilidades.copiarObjeto(fuente, copia, propiedades);
		copia.setNombre(nuevoNombre);
		copia.setTipo("Instancia");
		dao.guardar(copia);
		actividadServicio.copiarPrecedentes(fuente.getId(), copia.getId());
		
	return dao.cargar(copia.getId());
	}

	
	public void borrar(Metodologia metodologia) {
		dao.borrar(metodologia);
		
	}

	public List<Metodologia> metodologiasConcretas(Metodologia metodologia) {
		return dao.metodologiasConcretas(metodologia);
	}
	
	public void actualizarLineaEjecucion(Integer idMetodologia){
		List<Actividad> actividades = actividadServicio.actividadesPorMetodologia(idMetodologia);
		for (Actividad actividad : actividades) {
			actividad.setFechaInicioReal(actividad.getFechaInicio());
			actividad.setDuracionReal(actividad.getDuracion());
		}
	}
}
