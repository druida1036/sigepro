package co.com.sigepro.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the detalle database table.
 * 
 */
@Entity
public class Detalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DETALLE_ID_GENERATOR", sequenceName="DETALLE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETALLE_ID_GENERATOR")
	private Integer id;

	@Column(name="ctl_estado")
	private String ctlEstado;

    @Temporal( TemporalType.DATE)
	@Column(name="ctl_fec_alta")
	private Date ctlFecAlta;

    @Temporal( TemporalType.DATE)
	@Column(name="ctl_fec_mod")
	private Date ctlFecMod;

	@Column(name="ctl_ip")
	private String ctlIp;

	@Column(name="ctl_usuario")
	private String ctlUsuario;

	private String descripcion;

	//bi-directional many-to-one association to Actividad
    @ManyToOne
	@JoinColumn(name="actividades_id")
	private Actividad actividade;

    public Detalle() {
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

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Actividad getActividade() {
		return this.actividade;
	}

	public void setActividade(Actividad actividade) {
		this.actividade = actividade;
	}
	
}