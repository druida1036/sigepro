package co.com.sigepro.entidades;

import java.io.Serializable;

public class Paginador implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int indice = 0;
	private int tama�oPagina = 0;
	private int maxResultados = 0;
	public Paginador() {
		super();
	}
	
	public Paginador(int tama�oPagina) {
		super();
		this.tama�oPagina = tama�oPagina;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public int getTama�oPagina() {
		return tama�oPagina;
	}
	public void setTama�oPagina(int tama�oPagina) {
		this.tama�oPagina = tama�oPagina;
	}
	public int getMaxResultados() {
		return maxResultados;
	}
	public void setMaxResultados(int maxResultados) {
		this.maxResultados = maxResultados;
	}
	
}
