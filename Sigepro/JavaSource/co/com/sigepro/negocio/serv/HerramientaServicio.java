package co.com.sigepro.negocio.serv;

import java.util.List;

import co.com.sigepro.entidades.Herramienta;

public interface HerramientaServicio {
	public Herramienta guardar(Herramienta herramienta);
	public List<Herramienta> listado(Herramienta herramienta);
	public Herramienta cargar(Integer id);
}
