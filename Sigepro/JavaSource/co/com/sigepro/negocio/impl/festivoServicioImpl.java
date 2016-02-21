package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Festivo;
import co.com.sigepro.negocio.serv.FestivoServicio;
import co.com.sigepro.persistencia.dao.FestivoDao;

@Service
public class festivoServicioImpl implements FestivoServicio {
	@Autowired
	private FestivoDao dao;

	public Festivo guardar(Festivo festivo) {
		return dao.guardar(festivo);
	}

	public Festivo cargar(Integer id) {
		return dao.cargar(id);
	}
	
	@Override
	public List<Festivo> listado(Festivo festivo) {
		return dao.listado(festivo);
	}
}
