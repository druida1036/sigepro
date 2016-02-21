package co.com.sigepro.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ForeignKey;

@Entity
public class Recurso extends EntidadGenerica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "RECURSO_ID_GENERATOR", sequenceName = "RECURSO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECURSO_ID_GENERATOR")
	private Integer id;
	private Double costo;
	private Double disponibilidad;
	@OneToOne
	@ForeignKey(name = "recurso_persona_fk")
	private Persona usuario = new Persona();
	
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public Double getDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(Double disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public Persona getUsuario() {
		return usuario;
	}
	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

}
