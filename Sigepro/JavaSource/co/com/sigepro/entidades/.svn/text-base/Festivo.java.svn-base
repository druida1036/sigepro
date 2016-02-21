package co.com.sigepro.entidades;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.ForeignKey;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the festivos database table.
 * 
 */
@Entity
@Table(name = "festivos")
public class Festivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FESTIVOS_ID_GENERATOR", sequenceName = "FESTIVOS_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FESTIVOS_ID_GENERATOR")
	private Integer id;
	private String razon;
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name = "ctl_estado")
	private String ctlEstado;

	@Temporal(TemporalType.DATE)
	@Column(name = "ctl_fec_alta")
	private Date ctlFecAlta;

	@Temporal(TemporalType.DATE)
	@Column(name = "ctl_fec_mod")
	private Date ctlFecMod;

	@Column(name = "ctl_ip")
	private String ctlIp;

	@Column(name = "ctl_usuario")
	private String ctlUsuario;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "festivos")
	@JoinTable(name = "festivos_calendario")
	@ForeignKey(name = "fk_festivo_calendario")
	private List<Calendario> calendarios;

	public Festivo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCtlEstado() {
		return this.ctlEstado;
	}

	public void setCtlEstado(String ctlEstado) {
		this.ctlEstado = ctlEstado;
	}

	public Date getCtlFecAlta() {
		return this.ctlFecAlta;
	}

	public void setCtlFecAlta(Date ctlFecAlta) {
		this.ctlFecAlta = ctlFecAlta;
	}

	public Date getCtlFecMod() {
		return this.ctlFecMod;
	}

	public void setCtlFecMod(Date ctlFecMod) {
		this.ctlFecMod = ctlFecMod;
	}

	public String getCtlIp() {
		return this.ctlIp;
	}

	public void setCtlIp(String ctlIp) {
		this.ctlIp = ctlIp;
	}

	public String getCtlUsuario() {
		return this.ctlUsuario;
	}

	public void setCtlUsuario(String ctlUsuario) {
		this.ctlUsuario = ctlUsuario;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getRazon() {
		return this.razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	public List<Calendario> getCalendarios() {
		return calendarios;
	}

	public void setCalendarios(List<Calendario> calendarios) {
		this.calendarios = calendarios;
	}

}