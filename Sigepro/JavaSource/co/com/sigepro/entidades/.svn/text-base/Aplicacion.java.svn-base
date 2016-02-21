package co.com.sigepro.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *@author Jorge Armando Martinez Sanchez (jamartinez@aliaddos.com)
 */

public class Aplicacion extends EntidadGenerica {

	private static final long serialVersionUID = 3550605563591043329L;
	private Long id;
	private Subsistema subsistema;
	private String nombre;
	private String descripcion;
	private List<Componente> componentes = new ArrayList<Componente>();

	/**
	 * Relacion con la tabla de subsistemas @
	 */
	public Subsistema getSubsistema() {
		return this.subsistema;
	}

	public void setSubsistema(Subsistema subsistema) {
		this.subsistema = subsistema;
	}

	/**
	 * 
	 * Columma: nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * Colummna: descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * Columna: id_aplicacion
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Relacion con la tabla componentes @
	 */
	public List<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}

}
