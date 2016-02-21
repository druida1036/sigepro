package co.com.sigepro.persistencia.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface GenericDao<T, ID extends Serializable> {
	 
    T cargar(ID id);
    void actualizar(T entidad);
    T guardar(T entidad);
    void borrar(T entidad);
    void borrarPorIdentificador(ID id);
    void borrarTodos();
    int count();
    List<T> busquedaPorCriterio(Criterion... criterio);
	public List<T> listado(T entidad);
	/**
	 * Este metodo se encargar de mezclar dos objetos que haya cargado en una
	 * session diferente a la de trabajo
	 * 
	 * @param entidad
	 */
	public void mezclar(Object entidad);
	
	public T replicar(ID id);

}

