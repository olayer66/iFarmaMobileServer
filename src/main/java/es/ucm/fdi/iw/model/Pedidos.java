package es.ucm.fdi.iw.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Pedidos implements Serializable {

	
	private static final long serialVersionUID = 9079583133750628192L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(targetEntity=Farmacia.class)
	private Farmacia farmacia;
	@ManyToOne(targetEntity=Paciente.class)
	private Paciente paciente;
	
	@OneToMany(targetEntity=ExistenciaPedido.class)
	@JoinColumn(name="pedido_id")
	private List<ExistenciaPedido> existenciasPedido;
	
	@Column(name = "fecha_Pedido", nullable = false)
	private Date fechaPedido;
	@Column(name = "estado_Pedido", nullable = false)
	private int estadoPedido;//0 recibido 1 entregado
	public long getId() {
		return id;
	}
	public Farmacia getFarmacia() {
		return farmacia;
	}
	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public long getPacienteID() {
		return paciente.getId();
	}
	public long getFarmaciaID() {
		return farmacia.getId();
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public List<ExistenciaPedido> getExistenciasPedido() {
		return existenciasPedido;
	}
	public void setExistenciasPedido(List<ExistenciaPedido> existenciasPedido) {
		this.existenciasPedido = existenciasPedido;
	}
	public Date getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public int getEstadoPedido() {
		return estadoPedido;
	}
	public void setEstadoPedido(int estadoPedido) {
		this.estadoPedido = estadoPedido;
	}
	public double getPrecioTotal() {
		
		double suma=0;
		for(ExistenciaPedido m :existenciasPedido){
			for (int i=0; i< m.getCantidad();i++) {
				suma=suma +m.getMedicamento().getPrecio();
			}
		}

		return suma;
	}
	public boolean realizarPedido() {
		boolean exito=true;
		if (estadoPedido ==0){
			for(ExistenciaPedido ePed :existenciasPedido){
				for (ExistenciaMedicamento eMed : farmacia.getStock()) {
					if (ePed.getMedicamento() == eMed.getMedicamento()){
						if(ePed.getCantidad()<= eMed.getCantidad()){//si hay menos pedido que stock
							
						}else{
							exito=false;
						}
					}
				}
			}	
		}else{
			exito=false;
		}
		if(exito){
			realizo();
		}
		
		return exito;
	}
	
		private void realizo(){
			List<ExistenciaMedicamento> listaStock =  farmacia.getStock();
			paciente.actualizaListaPedidos(id);
			
				for(ExistenciaPedido ePed :existenciasPedido){
					for (ExistenciaMedicamento eMed :listaStock) {
						if (ePed.getMedicamento() == eMed.getMedicamento()){
							eMed.setCantidad(eMed.getCantidad()-ePed.getCantidad());//quito del stock las cantidades del pedido
						}
					}	
				}
				
				estadoPedido=1;//entregado
				farmacia.setStock(listaStock);
				
		}
}
