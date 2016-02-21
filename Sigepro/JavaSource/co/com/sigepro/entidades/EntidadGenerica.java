package co.com.sigepro.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidad genérica donde se encuentran los datos de auditoria
 * 
 */
@MappedSuperclass
public class EntidadGenerica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	public EntidadGenerica() {
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

}