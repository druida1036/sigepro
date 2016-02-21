package co.com.sigepro.entidades;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;


/**
 * The persistent class for the actas database table.
 * 
 */
@Entity
@Table(name="actas")
public class Acta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACTAS_ID_GENERATOR", sequenceName="ACTAS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACTAS_ID_GENERATOR")
	private Integer id;

	private String descripcion;

	private Integer version;

	//bi-directional many-to-one association to Documento
    @ManyToOne(cascade = CascadeType.ALL)
    @Cascade({ org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private Documento documento;

	//bi-directional many-to-one association to Reunion
    @ManyToOne
	@JoinColumn(name="reuniones_id")
	private Reunion reunione;

    public Acta() {
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

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	public Reunion getReunione() {
		return this.reunione;
	}

	public void setReunione(Reunion reunione) {
		this.reunione = reunione;
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
		if (!(obj instanceof Acta))
			return false;
		Acta other = (Acta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}