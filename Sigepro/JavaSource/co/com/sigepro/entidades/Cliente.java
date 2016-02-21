package co.com.sigepro.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.Pattern;

/**
 * The persistent class for the clientes database table.
 * 
 */
@Entity
@Table(name = "clientes")
public class Cliente extends EntidadGenerica {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CLIENTES_ID_GENERATOR", sequenceName = "CLIENTES_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTES_ID_GENERATOR")
	private Integer id;
	
	private String direccion;
	
	@Column(unique= true)
	private String nombre;

	@Pattern(regex="\\d{3,}-\\d{7}")
	private String telefono;

	// bi-directional many-to-one association to Persona
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	private List<Persona> personas;

	public Cliente() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Persona> getPersonas() {
		return this.personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

}