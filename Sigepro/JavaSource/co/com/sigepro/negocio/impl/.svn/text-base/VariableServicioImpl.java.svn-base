package co.com.sigepro.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Variable;
import co.com.sigepro.negocio.serv.VariableServicio;
import co.com.sigepro.persistencia.dao.VariableDao;
@Service
public class VariableServicioImpl implements VariableServicio {
	@Autowired
	private VariableDao dao;
	
	public Variable guardar(Variable variableNotificacion) {
		return dao.guardar(variableNotificacion);
	}
	

	public Variable cargar(Integer id){
		return dao.cargar(id);
	}
	
	public List<Variable> listado(
			Variable variableNotificacion) {
		return dao.listado(variableNotificacion);
	}


	public Variable cargarPorReferencia(String referencia) {
		List<Variable> variables;
		Variable variable = new Variable();
		variable.setReferencia(referencia);
		variables = dao.listado(variable);
		if(variables.size() > 0){
			return variables.get(0);
		}
			
		return null;
	}
}
