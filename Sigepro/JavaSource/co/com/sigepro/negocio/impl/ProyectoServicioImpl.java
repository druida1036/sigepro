package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Metodologia;
import co.com.sigepro.entidades.Proyecto;
import co.com.sigepro.negocio.serv.MetodologiaServicio;
import co.com.sigepro.negocio.serv.ProyectoServicio;
import co.com.sigepro.persistencia.dao.ProyectoDao;

@Service
public class ProyectoServicioImpl implements ProyectoServicio {
	@Autowired
	private ProyectoDao dao;
	@Autowired
	private MetodologiaServicio metodologiaServicio;

	public Proyecto guardar(Proyecto proyecto) {
		return dao.guardar(proyecto);
	}

	public List<Proyecto> listado(Proyecto proyecto) {
		return dao.listado(proyecto);
	}

	public Proyecto cargar(Integer id) {
		return dao.cargar(id);
	}

	public void agregarMetodologia(Proyecto proyecto, Metodologia metodologia) {

		String nombreMetodologia;
		Metodologia instancia;

		metodologia = metodologiaServicio.cargar(metodologia.getId());

		nombreMetodologia = metodologia.getNombre() + "-"
				+ proyecto.getNombre();

		if (metodologia.getInstancias().contains(proyecto.getMetodologia())) {
			proyecto.getMetodologia().setNombre(nombreMetodologia);

		} else {
			if (proyecto.getMetodologia() != null) {
				metodologiaServicio.borrar(proyecto.getMetodologia());
			}

			instancia = metodologiaServicio.copiar(metodologia,
					nombreMetodologia);
			metodologia.getInstancias().add(instancia);
			instancia.setMetodologia(metodologia);
//			metodologiaServicio.guardar(metodologia);
			metodologiaServicio.guardar(instancia);
			proyecto.setMetodologia(null);
			proyecto.setMetodologia(instancia);
		}

	}
}
