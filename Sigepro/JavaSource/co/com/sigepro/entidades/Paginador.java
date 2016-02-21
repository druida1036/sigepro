package co.com.sigepro.entidades;

import java.io.Serializable;

public class Paginador implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int indice = 0;
	private int tamañoPagina = 0;
	private int maxResultados = 0;
	public Paginador() {
		super();
	}
	
	public Paginador(int tamañoPagina) {
		super();
		this.tamañoPagina = tamañoPagina;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public int getTamañoPagina() {
		return tamañoPagina;
	}
	public void setTamañoPagina(int tamañoPagina) {
		this.tamañoPagina = tamañoPagina;
	}
	public int getMaxResultados() {
		return maxResultados;
	}
	public void setMaxResultados(int maxResultados) {
		this.maxResultados = maxResultados;
	}
	
}
