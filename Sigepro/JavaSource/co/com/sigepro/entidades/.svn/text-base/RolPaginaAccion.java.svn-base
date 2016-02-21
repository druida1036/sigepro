package co.com.sigepro.entidades;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the rol_pagina_accion database table.
 * 
 */
@Entity
@Table(name="rol_pagina_accion")
public class RolPaginaAccion extends EntidadGenerica implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RolPaginaAccionPK id;


    public RolPaginaAccion() {
    }

	public RolPaginaAccionPK getId() {
		return this.id;
	}

	public void setId(RolPaginaAccionPK id) {
		this.id = id;
	}

	
}