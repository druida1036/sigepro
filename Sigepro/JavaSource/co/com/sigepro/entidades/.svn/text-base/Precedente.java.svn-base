package co.com.sigepro.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the precedentes database table.
 * 
 */
@Entity
@Table(name="precedentes")
public class Precedente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRECEDENTES_ACTIVIDADESID_GENERATOR", sequenceName="PRECEDENTES_ACTIVIDADES_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRECEDENTES_ACTIVIDADESID_GENERATOR")
	@Column(name="actividades_id")
	private Integer actividadesId;

	

    public Precedente() {
    }

	public Integer getActividadesId() {
		return this.actividadesId;
	}

	public void setActividadesId(Integer actividadesId) {
		this.actividadesId = actividadesId;
	}

	
}