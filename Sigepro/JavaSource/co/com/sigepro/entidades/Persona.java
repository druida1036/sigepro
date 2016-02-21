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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.Email;
import org.hibernate.validator.Pattern;

/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
public class Persona extends EntidadGenerica {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PERSONA_ID_GENERATOR", sequenceName = "PERSONA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONA_ID_GENERATOR")
	private Integer id;

	private String apellido;

	private String cargo;

	private String contrasena;

	@Email
	private String correo;

	@Column(name = "costo_hora")
	private Integer costoHora;

	private String direccion;

	@Column(unique = true)
	private String login;

	private String nombre;

	@Pattern(regex = "\\d{3,}-\\d{7}")
	private String telefono;
	@Column(name = "numero_documento")
	private Integer numeroDocumento;

	// bi-directional many-to-one association to Actividad
	@OneToMany(mappedBy = "persona")
	@ForeignKey(name = "persona_actividad_fk")
	private List<Actividad> actividades = new ArrayList<Actividad>();

	// bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name = "clientes_id")
	@ForeignKey(name = "persona_cliente_fk")
	private Cliente cliente;

	// bi-directional many-to-one association to PersonaEspecialidad
	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
	@ForeignKey(name = "persona_persona_especialidad_fk")
	private List<PersonaEspecialidad> personaEspecialidads;

	// bi-directional many-to-many association to Proyecto
	@ManyToMany(mappedBy = "personas")
	@ForeignKey(name = "persona_proyecto_fk")
	private List<Proyecto> proyectos;

	// bi-directional many-to-many association to Reunion
	@ManyToMany(mappedBy = "personas")
	@ForeignKey(name = "persona_reuniones_fk")
	private List<Reunion> reuniones;

	// bi-directional many-to-many association to Rol
	@ManyToMany(mappedBy = "personas")
	@ForeignKey(name = "persona_rol_fk")
	private List<Rol> rols;
	@Transient()
	private Credencial credencial;

	public Persona() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getCostoHora() {
		return this.costoHora;
	}

	public void setCostoHora(Integer costoHora) {
		this.costoHora = costoHora;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public List<Actividad> getActividades() {
		return this.actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<PersonaEspecialidad> getPersonaEspecialidads() {
		return this.personaEspecialidads;
	}

	public void setPersonaEspecialidads(
			List<PersonaEspecialidad> personaEspecialidads) {
		this.personaEspecialidads = personaEspecialidads;
	}

	public List<Reunion> getReuniones() {
		return this.reuniones;
	}

	public void setReuniones(List<Reunion> reuniones) {
		this.reuniones = reuniones;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
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
		if (!(obj instanceof Persona))
			return false;
		Persona other = (Persona) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;

	}

	public Credencial getCredencial() {
		return credencial;
	}

}