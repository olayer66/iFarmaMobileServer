package es.ucm.fdi.iw.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class ExistenciaPedido implements Serializable {
	

	private static final long serialVersionUID = -305492674073684798L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "cantidad", nullable = false)
	private int cantidad;
	@Column(name = "fecha_caducidad", nullable = false)
	private Date fechaCaducidad;
	
	@ManyToOne(targetEntity=Pedidos.class)
	private Pedidos pedido;
	@ManyToOne(targetEntity=Medicamento.class)
	private Medicamento medicamento;
	
	//getters y setters
	public long getId() {
		return id;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Pedidos getPedido() {
		return pedido;
	}

	public void setPedido(Pedidos pedido) {
		this.pedido = pedido;
	}

}
