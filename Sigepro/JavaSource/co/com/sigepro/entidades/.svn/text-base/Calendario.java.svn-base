package co.com.sigepro.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ForeignKey;

@Entity
public class Calendario extends EntidadGenerica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "CALENDARIO_ID_GENERATOR", sequenceName = "CALENDARIO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CALENDARIO_ID_GENERATOR")
	private Integer id;
	private String nombre;
	private String descripcion;
	@Column(name = "horas_trabajo")
	private Double horasTrabajo;
	@Column(name = "dias_trabajo")
	private Boolean[] diasTrabajo;
   
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "festivos_calendario")
	@ForeignKey(name = "fk_calendario_festivo")
	private List<Festivo> festivos = new ArrayList<Festivo>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getHorasTrabajo() {
		return horasTrabajo;
	}

	public void setHorasTrabajo(Double horasTrabajo) {
		this.horasTrabajo = horasTrabajo;
	}

	public Boolean[] getDiasTrabajo() {
		return diasTrabajo;
	}

	public void setDiasTrabajo(Boolean[] diasTrabajo) {
		this.diasTrabajo = diasTrabajo;
	}

	public List<Festivo> getFestivos() {
		return festivos;
	}

	public void setFestivos(List<Festivo> festivos) {
		this.festivos = festivos;
	}
	
	public String getNombre() {
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

}
