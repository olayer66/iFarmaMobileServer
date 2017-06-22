package es.ucm.fdi.iw.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class ExistenciaMedicamento implements Serializable {
	private static final long serialVersionUID = 4000356623437817386L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private Medicamento medicamento;
	
	@ManyToOne(targetEntity=Farmacia.class)
	private Farmacia farmacia;
	
	@Column(name = "cantidad", nullable = false)
	private int cantidad;
	@Column(name = "fecha_caducidad", nullable = false)
	private String fechaCaducidad;
	
	
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
	public String getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	public void setFarmacia(Farmacia f){
		this.farmacia=f;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Farmacia getFarmacia() {
		return farmacia;
	}
}
