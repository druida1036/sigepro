package co.com.sigepro.entidades;

import java.io.Serializable;

public class Paginador implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int indice = 0;
	private int tamaņoPagina = 0;
	private int maxResultados = 0;
	public Paginador() {
		super();
	}
	
	public Paginador(int tamaņoPagina) {
		super();
		this.tamaņoPagina = tamaņoPagina;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public int getTamaņoPagina() {
		return tamaņoPagina;
	}
	public void setTamaņoPagina(int tamaņoPagina) {
		this.tamaņoPagina = tamaņoPagina;
	}
	public int getMaxResultados() {
		return maxResultados;
	}
	public void setMaxResultados(int maxResultados) {
		this.maxResultados = maxResultados;
	}
	
}
