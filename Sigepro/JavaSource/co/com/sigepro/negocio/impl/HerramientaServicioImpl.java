package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Herramienta;
import co.com.sigepro.negocio.serv.HerramientaServicio;
import co.com.sigepro.persistencia.dao.HerramientaDao;
@Service
public class HerramientaServicioImpl implements HerramientaServicio {
	@Autowired
	private HerramientaDao dao;
	
	public Herramienta guardar(Herramienta herramienta) {
		return dao.guardar(herramienta);
	}
	
	public List<Herramienta> listado(Herramienta herramienta){
		return dao.listado(herramienta);
	}
	
	public Herramienta cargar(Integer id){
		return dao.cargar(id);
	}
}
