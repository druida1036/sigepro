package co.com.sigepro.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.Length;

import co.com.sigepro.negocio.utilidades.Utilidades;
import co.com.sigepro.persistencia.util.NotWhiteSpace;

/**
 * The persistent class for the actividades database table.
 * 
 */
@Entity
@Table(name = "actividades")
public class Actividad extends EntidadGenerica implements Serializable,
		Cloneable, Comparable<Actividad> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ACTIVIDADES_ID_GENERATOR", sequenceName = "ACTIVIDADES_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACTIVIDADES_ID_GENERATOR")
	private Integer id;

	@Column(name = "tipo_aprobacion")
	private Boolean tipoAprobacion;

	private Boolean aprobacion;

	private Double avance = 0d;

	private Integer duracion = new Integer(1);

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio")
	private Date fechaInicio = new Date();

	private Integer duracionReal = new Integer(1);

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio_real")
	private Date fechaInicioReal = new Date();

	@NotWhiteSpace(message = "")
	private String nombre;

	@Length(max = 255)
	private String descripcion;

	private Integer orden;

	private Integer peso = 1;

	// bi-directional many-to-many association to Actividad
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(name = "precedentes", joinColumns = { @JoinColumn(name = "precedente") }, inverseJoinColumns = { @JoinColumn(name = "actividades_id") })
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "fk_precedente",inverseName = "fk_precedida")
	private List<Actividad> precedentes = new ArrayList<Actividad>();

	@ManyToMany(mappedBy = "precedentes",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "precedentes", joinColumns = { @JoinColumn(name = "actividades_id") }, inverseJoinColumns = { @JoinColumn(name = "precedente") })
	private List<Actividad> precedidas = new ArrayList<Actividad>();

	@OneToMany(mappedBy = "actividadAprobadora")
	@JoinTable(name = "aprobaciones", joinColumns = { @JoinColumn(name = "id_aprobadora") }, inverseJoinColumns = { @JoinColumn(name = "id_aprobada") })
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "fk_aprobadora",inverseName = "fk_aprobada")
	private List<Actividad> actividadesPorAprobar = new ArrayList<Actividad>();

	@ManyToOne()
	@JoinTable(name = "aprobaciones", joinColumns = { @JoinColumn(name = "id_aprobada") }, inverseJoinColumns = { @JoinColumn(name = "id_aprobadora") })
	private Actividad actividadAprobadora;

	// bi-directional many-to-many association to Notificacion
	@ManyToMany()
	@JoinTable(name = "actividades_notificaciones", joinColumns = { @JoinColumn(name = "actividades_id") }, inverseJoinColumns = { @JoinColumn(name = "notificaciones_id") })
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private List<Notificacion> notificaciones = new ArrayList<Notificacion>();

	// bi-directional many-to-one association to Persona
	@ManyToOne()
	@JoinColumn(name = "persona_id", referencedColumnName = "id")
	@Fetch(FetchMode.SELECT)
	private Persona persona;

	// bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name = "procesos_id")
	private Proceso proceso;

	// bi-directional many-to-one association to Rol
	@ManyToOne()
	@JoinColumn(name = "rol_id", referencedColumnName = "id")
	@Fetch(FetchMode.SELECT)
	private Rol rol;

	

	// bi-directional many-to-one association to Plantilla
	@OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL)
	@Cascade({ org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@Fetch(FetchMode.SELECT)
	private List<Plantilla> plantillas = new ArrayList<Plantilla>();
	@ManyToMany()
	@JoinTable(name = "actividades_herramientas", joinColumns = { @JoinColumn(name = "actividades_id") }, inverseJoinColumns = { @JoinColumn(name = "herramientas_id") })
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@Fetch(FetchMode.SELECT)
	private List<Herramienta> herramientas = new ArrayList<Herramienta>();

	public Actividad() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAprobacion() {
		return this.aprobacion;
	}

	public void setAprobacion(Boolean aprobacion) {
		this.aprobacion = aprobacion;
	}

	public Double getAvance() {
		return this.avance;
	}

	public void setAvance(Double avance) {
		this.avance = avance;
	}

	public Integer getDuracion() {
		return this.duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return Utilidades.sumarHoras(fechaInicio, duracion.intValue());
	}

	public Date getFechaFinReal() {
		if (fechaInicioReal != null && duracionReal != null) {
			return Utilidades.sumarHoras(fechaInicioReal,
					duracionReal.intValue());
		} else {
			return null;
		}
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Integer getPeso() {
		return this.peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public List<Notificacion> getNotificaciones() {
		return this.notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Plantilla> getPlantillas() {
		return this.plantillas;
	}

	public void setPlantillas(List<Plantilla> plantillas) {
		this.plantillas = plantillas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Actividad> getPrecedentes() {
		return precedentes;
	}

	public void setPrecedentes(List<Actividad> precedentes) {
		this.precedentes = precedentes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Actividad))
			return false;
		Actividad other = (Actividad) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Actividad clone() throws CloneNotSupportedException {
		Actividad clon = new Actividad();
		clon.setId(id);
		clon.setNombre(nombre);
		return clon;
	}

	public List<Herramienta> getHerramientas() {
		return herramientas;
	}

	public void setHerramientas(List<Herramienta> herramientas) {
		this.herramientas = herramientas;
	}

	@Override
	public String toString() {
		return "procesoId " + proceso.getId() + " id " + id + " nombre "
				+ nombre;
	}

	public int compareTo(Actividad o) {
		return orden.compareTo(o.getOrden());
	}

	public Integer getDuracionReal() {
		return duracionReal;
	}

	public void setDuracionReal(Integer duracionReal) {
		this.duracionReal = duracionReal;
	}

	public Date getFechaInicioReal() {
		return fechaInicioReal;
	}

	public void setFechaInicioReal(Date fechaInicioReal) {
		this.fechaInicioReal = fechaInicioReal;
	}

	public List<Actividad> getActividadesPorAprobar() {
		return actividadesPorAprobar;
	}

	public void setActividadesPorAprobar(List<Actividad> actividadesPorAprobar) {
		this.actividadesPorAprobar = actividadesPorAprobar;
	}

	public Actividad getActividadAprobadora() {
		return actividadAprobadora;
	}

	public void setActividadAprobadora(Actividad actividadAprobadora) {
		this.actividadAprobadora = actividadAprobadora;
	}

	public void setTipoAprobacion(Boolean tipoAprobacion) {
		this.tipoAprobacion = tipoAprobacion;
	}

	public List<Actividad> getPrecedidas() {
		return precedidas;
	}

	public void setPrecedidas(List<Actividad> precedidas) {
		this.precedidas = precedidas;
	}

	public Boolean getTipoAprobacion() {
		return tipoAprobacion;
	}

}