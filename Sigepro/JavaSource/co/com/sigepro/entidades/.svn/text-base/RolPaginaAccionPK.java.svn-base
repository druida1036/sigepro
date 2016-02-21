package co.com.sigepro.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the rol_pagina_accion database table.
 * 
 */
@Embeddable
public class RolPaginaAccionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Accion
    @ManyToOne
	@JoinColumn(name="acciones_id")
	private Accion accion;

	//bi-directional many-to-one association to Pagina
    @ManyToOne
	@JoinColumn(name="paginas_id")
	private Pagina pagina;

	//bi-directional many-to-one association to Rol
    @ManyToOne
	private Rol rol;
    public RolPaginaAccionPK() {
    }
	
	public Pagina getPagina() {
		return pagina;
	}
	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accion == null) ? 0 : accion.hashCode());
		result = prime * result + ((pagina == null) ? 0 : pagina.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolPaginaAccionPK other = (RolPaginaAccionPK) obj;
		if (accion == null) {
			if (other.accion != null)
				return false;
		} else if (!accion.equals(other.accion))
			return false;
		if (pagina == null) {
			if (other.pagina != null)
				return false;
		} else if (!pagina.equals(other.pagina))
			return false;
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;
		return true;
	}

	public Accion getAccion() {
		return accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}
	
}