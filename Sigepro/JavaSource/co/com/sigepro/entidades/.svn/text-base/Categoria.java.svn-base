package co.com.sigepro.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.Length;

import co.com.sigepro.persistencia.util.NotWhiteSpace;

/**
 * The persistent class for the Categories database table.
 * 
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "nombre",
		"metodologia_id" }))
public class Categoria extends EntidadGenerica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CATEGORIA_ID_GENERATOR", sequenceName = "CATEGORIA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIA_ID_GENERATOR")
	private Integer id;

	@Length(max = 255)
	private String descripcion;

	@NotWhiteSpace(message = "")
	private String nombre;

	private Integer peso;
	
	private Double avance = 0d;
	
	private Integer orden;
	
	@ManyToOne()
	@JoinColumn(name = "metodologia_id", referencedColumnName = "id")
	private Metodologia metodologia;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	@ForeignKey(name = "categoria_procesos_fk")
	@OrderBy
	private List<Proceso> procesos = new ArrayList<Proceso>();

	public Categoria() {
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

	public Metodologia getMetodologia() {
		return this.metodologia;
	}

	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}

	public List<Proceso> getProcesos() {
		return procesos;
	}

	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Double getAvance() {
		return avance;
	}

	public void setAvance(Double avance) {
		this.avance = avance;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

}