package co.com.sigepro.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

/**
 * The persistent class for the documento database table.
 * 
 */
@Entity
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DOCUMENTO_ID_GENERATOR", sequenceName = "DOCUMENTO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCUMENTO_ID_GENERATOR")
	private Integer id;

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

	@Column(name = "ctl_usurio")
	private String ctlUsurio;

	private byte[] documento;
	
	@Column( name = "tipo_contenido")
	private String tipoContenido;
	
	private String extencion;

	private Boolean estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String nombre;

	private Integer version;

	// bi-directional many-to-one association to Acta
	@OneToMany(mappedBy = "documento")
	private List<Acta> actas;

	// bi-directional many-to-one association to Plantilla
	@OneToMany(mappedBy = "documento")
	private List<Plantilla> plantillas;

	// bi-directional many-to-one association to Propuesta
	@OneToMany(mappedBy = "documento")
	@Cascade({ org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private List<Propuesta> propuestas;


	public Documento() {
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

	public String getCtlUsurio() {
		return this.ctlUsurio;
	}

	public void setCtlUsurio(String ctlUsurio) {
		this.ctlUsurio = ctlUsurio;
	}

	public byte[] getDocumento() {
		return this.documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<Acta> getActas() {
		return this.actas;
	}

	public void setActas(List<Acta> actas) {
		this.actas = actas;
	}

	public List<Plantilla> getPlantillas() {
		return this.plantillas;
	}

	public void setPlantillas(List<Plantilla> plantillas) {
		this.plantillas = plantillas;
	}

	public List<Propuesta> getPropuestas() {
		return this.propuestas;
	}

	public void setPropuestas(List<Propuesta> propuestas) {
		this.propuestas = propuestas;
	}

	public String getTipoContenido() {
		return tipoContenido;
	}

	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}

	public String getExtencion() {
		return extencion;
	}

	public void setExtencion(String extencion) {
		this.extencion = extencion;
	}

}