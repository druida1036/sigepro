package co.com.sigepro.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.Length;

import co.com.sigepro.persistencia.util.NotWhiteSpace;

/**
 * The persistent class for the metodologia database table.
 * 
 */
@Entity
public class Metodologia extends EntidadGenerica {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "METODOLOGIA_ID_GENERATOR", sequenceName = "METODOLOGIA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "METODOLOGIA_ID_GENERATOR")
	private Integer id;

	@Column(unique = true)
	@NotWhiteSpace(message = "")
	private String nombre;
	@Length(max = 255)
	private String descripcion;

	private String tipo;

	private Double avance = 0d;

	@OneToMany(mappedBy = "metodologia", cascade = { CascadeType.ALL })
	@Cascade({ org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	@ForeignKey(name = "metodologia_categoria_fk")
	@Fetch(FetchMode.SELECT)
	@OrderBy("orden asc")
	private List<Categoria> categorias = new ArrayList<Categoria>();

	@OneToMany(mappedBy = "metodologia")
	private List<Proyecto> proyectos = new ArrayList<Proyecto>();

	@OneToMany(mappedBy = "metodologia", cascade = CascadeType.ALL)
	@JoinColumn(name = "id_instancia")
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private List<Metodologia> instancias;

	@ManyToOne()
	@JoinColumn(name = "id_instancia", referencedColumnName = "id")
	private Metodologia metodologia;

	public Metodologia() {
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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public List<Metodologia> getInstancias() {
		return instancias;
	}

	public void setInstancias(List<Metodologia> instancias) {
		this.instancias = instancias;
	}

	public Metodologia getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
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
		if (!(obj instanceof Metodologia))
			return false;
		Metodologia other = (Metodologia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Double getAvance() {
		return avance;
	}

	public void setAvance(Double avance) {
		this.avance = avance;
	}

}