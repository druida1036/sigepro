package co.com.sigepro.negocio.serv;

import java.util.List;

import co.com.sigepro.entidades.Metodologia;
import co.com.sigepro.entidades.Proyecto;

public interface ProyectoServicio {
	public Proyecto guardar(Proyecto proyecto);
	public List<Proyecto> listado(Proyecto proyecto);
	public Proyecto cargar(Integer id);
	public void agregarMetodologia(Proyecto proyecto,
			Metodologia metodologia);
}
