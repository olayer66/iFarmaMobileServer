package es.ucm.fdi.iw.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
//Imports basicos para JPA
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Mensaje implements Serializable {
	private static final long serialVersionUID = -5547637084846310049L;
	
	//ID
	@Id
	@Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//Si esta leido por parte del destinatario
	@Column (name="leido",nullable = false)
	private boolean leido;
	
	//remitente y destinatario
	@ManyToOne(targetEntity=Usuario.class)
	private Usuario remitente;
	@ManyToOne(targetEntity=Usuario.class)
	private Usuario destinatario;
	
	//Cuerpo del mensaje
	@Column(name = "fecha_mensaje", nullable = false)
	private Date fechaMensaje;
	@Column(name = "asunto", nullable = false)
	private String asunto;
	@Column(name = "mensaje", nullable = false)
	private String mensaje;
	
	//Getters y Setters de los campos de la tabla
	public long getid() {
		return id;
	}
	public void setIDMensaje(long id) {
		this.id = id;
	}
	public boolean isLeido() {
		return leido;
	}
	public void setLeido(boolean leido) {
		this.leido = leido;
	}
	public Usuario getRemitente() {
		return remitente;
	}
	public void setRemitente(Usuario remitente) {
		this.remitente = remitente;
	}
	public Usuario getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Date getFechaMensaje() {
		return fechaMensaje;
	}
	public void setFechaMensaje(Date fechaMensaje) {
		this.fechaMensaje = fechaMensaje;
	}
}
