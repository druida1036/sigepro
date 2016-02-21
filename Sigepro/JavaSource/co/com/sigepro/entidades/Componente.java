package co.com.sigepro.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Tabla: componente
 * 
 * @author Luis Francisco Fontalvo Romero (lfontalvo@aliaddos.com)
 */
public class Componente extends EntidadGenerica {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -154449264117626742L;

	private Long id;
	private String nombre;
	private String descripcion;

	private Aplicacion aplicacion;
	private List<Pagina> paginas = new ArrayList<Pagina>();

	/**
	 * Columna: id_componente
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Columna: id_componente
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Columna: nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Columna: nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Columna: descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Columna: descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Tabla: aplicacion
	 */
	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	/**
	 * Tabla: aplicacion
	 */
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	/**
	 * Tabla: pagina
	 */
	public List<Pagina> getPaginas() {
		return paginas;
	}

	/**
	 * Tabla: pagina
	 */
	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

}
