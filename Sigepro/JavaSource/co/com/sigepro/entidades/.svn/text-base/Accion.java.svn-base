package co.com.sigepro.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the acciones database table.
 * 
 */
@Entity
@Table(name="acciones")
public class Accion extends EntidadGenerica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACCIONES_ID_GENERATOR", sequenceName="ACCIONES_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCIONES_ID_GENERATOR")
	private Integer id;

	private String descripcion;

	private String nombre;
	
	@OneToMany(mappedBy = "id.accion")
	private List<RolPaginaAccion> rolPaginaAcciones;
	
	public Accion() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<RolPaginaAccion> getRolPaginaAcciones() {
		return rolPaginaAcciones;
	}

	public void setRolPaginaAcciones(List<RolPaginaAccion> rolPaginaAcciones) {
		this.rolPaginaAcciones = rolPaginaAcciones;
	}

}