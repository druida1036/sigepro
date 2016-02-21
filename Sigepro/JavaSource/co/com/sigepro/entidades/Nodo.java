package co.com.sigepro.entidades;

import java.io.Serializable;

import co.com.sigepro.control.ConstantesNavegacion;

public class Nodo<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String nombre;
	private String descripcion;
	private String forma;
	private String formaSubcategoria;
	private T valor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		configurarNodo();
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Nodo(T valor) {
		super();
		this.valor = valor;
		configurarNodo();
	}

	private void configurarNodo() {
		if (valor instanceof Metodologia) {
			nombre = ((Metodologia) valor).getNombre();
			forma = ConstantesNavegacion.FORMULARIO_METODOLOGIA;

		} else if (valor instanceof Categoria) {
			nombre = ((Categoria) valor).getNombre();
			forma = ConstantesNavegacion.FORMULARIO_CATEGORIA;
		} else if (valor instanceof Proceso) {
			nombre = ((Proceso) valor).getNombre();
			forma = ConstantesNavegacion.FORMULARIO_PROCESO;
		} else if (valor instanceof Actividad) {
			nombre = ((Actividad) valor).getNombre();
			forma = ConstantesNavegacion.FORMULARIO_ACTIVIDAD;
		}
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public String getForma() {
		return forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

	public String getFormaSubcategoria() {
		return formaSubcategoria;
	}

	public void setFormaSubcategoria(String formaSubcategoria) {
		this.formaSubcategoria = formaSubcategoria;
	}

}
