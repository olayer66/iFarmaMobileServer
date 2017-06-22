package es.ucm.fdi.iw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
//Imports de JPA
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.util.StringUtils;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //Esto divide las tablas por herencia.(paciente,medico,farmaceutico) y las organiza por el id de usuario.
@NamedQueries({
    @NamedQuery(name="Usuario.login",
                query="SELECT u FROM Usuario u WHERE u.contrasenia=:password AND u.usuario=:ssoId")
}) 
public class Usuario implements Serializable {
	private static final long serialVersionUID = 3918714646456852426L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	
	//datos del usuario
	@Column(name = "nombre", nullable = false)
	protected String nombre;
	@Column(name = "apellidos", nullable = false)
	protected String apellidos;
	@Column(name = "email", nullable = false)
	protected String email;
	@Column(name = "telefono", nullable = false)
	protected String telefono;
	
	//Estado del usuario(0=validacion,1=activo,2=inactivo(borrado logico))
	@Column(name = "estado", nullable = false)
	protected int estado;
	
	//Tipo de usuario
	@Column(name = "role", nullable = false)
	protected String role;
	//Usuario y contraseña para le login del usuario
	@Column(name = "username", nullable = false, unique = true)
	protected String usuario;
	@Column(name = "contra", nullable = true)
	protected String contrasenia;
	
	//Mensajes
	@OneToMany(targetEntity=Mensaje.class, cascade=CascadeType.REMOVE)
	@JoinColumn(name="destinatario_id")
	protected List<Mensaje> mensajesRecibidos= new ArrayList<>();
	@OneToMany(targetEntity=Mensaje.class, cascade=CascadeType.REMOVE)
	@JoinColumn(name="remitente_id")
	protected List<Mensaje> mensajesEnviados= new ArrayList<>();
	
	//Getters y setters de la entidad
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<Mensaje> getMensajesRecibidos() {
		return mensajesRecibidos;
	}
	public void setMensajesRecibidos(List<Mensaje> mensajesRecibidos) {
		this.mensajesRecibidos = mensajesRecibidos;
	}
	public List<Mensaje> getMensajesEnviados() {
		return mensajesEnviados;
	}
	public void setMensajesEnviados(List<Mensaje> mensajesEnviados) {
		this.mensajesEnviados = mensajesEnviados;
	}
	public long getId() {
		return id;
	}
	public void setId(long iDUsuario) {
		id = iDUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String toString() {
		return StringUtils.capitalize(this.nombre) + " " 
				+ StringUtils.capitalize(this.apellidos);
	}
}
