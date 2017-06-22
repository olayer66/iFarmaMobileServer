
package es.ucm.fdi.iw.model;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
//Imports basicos para JPA
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
//Peticiones a la tabla
@NamedQueries({
	@NamedQuery(name="Paciente.findByCodAut",
				query="SELECT p FROM Paciente p WHERE p.codigoAut=:codigo AND p.estado=0")
})
public class Paciente extends Usuario {
	private static final long serialVersionUID = 2117067448004216461L;

	//direccion de envio de pedidos
	@Column(name = "direccion", nullable = true)
	private String direccion;
	@Column(name = "ciudad", nullable = true)
	private String ciudad;
	@Column(name = "cod_Postal", nullable = true)
	private String codPostal;
	@Column(name = "provincia", nullable = true)
	private String provincia;
	@Column(name = "com_Autonoma", nullable = true)
	private String comAutonoma;

	//Codigo para la confirmacion del paciente
	@Column(name = "codigo_aut", nullable = false)
	private String codigoAut;

	//medico de cabecera (N/1)
	@ManyToOne(targetEntity=Medico.class)
	private Medico medCabecera;

	//farmacia (N/1)
	@ManyToOne(targetEntity=Farmacia.class)
	private Farmacia farmacia;

	//Lista de medicamentos del tratamiento (1/N)
	@OneToMany(targetEntity=Tratamiento.class, cascade=CascadeType.REMOVE)
	@JoinColumn(name="paciente_id")
	private List<Tratamiento> tratamiento;


	//Lista de pedidos
	@OneToMany(targetEntity=Pedidos.class, cascade=CascadeType.REMOVE)
	@JoinColumn(name="paciente_id")
	private List<Pedidos> listaPedidos;

	//forma de pago(0=paypal ,1=tarjeta, 2= contrareembolso)
	@Column(name = "forma_pago", nullable = true)
	private int formaPago;

	//datos tarjeta (si forma de pago es tarjeta)
	@Column(name = "num_tarjeta", nullable = true)
	private long numTarjeta;
	@Column(name = "cod_seg_tarjeta", nullable = true)
	private int codSegTarjeta;
	@Column(name = "fecha_cad_tarjeta", nullable = true)
	private String fechaCadTarjeta;

	public Paciente() {
		super();

		this.role = "PAC";
		this.usuario = "";
		this.codigoAut = this.generarCodigoAut();
		this.listaPedidos = new ArrayList<Pedidos>();
		this.tratamiento = new ArrayList<Tratamiento>();
	}

	//Getters y Setters de los campos de la tabla
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getComAutonoma() {
		return comAutonoma;
	}
	public void setComAutonona(String comAutonoma) {
		this.comAutonoma = comAutonoma;
	}
	public Medico getMedCabecera() {
		return medCabecera;
	}
	public void setMedCabecera(Medico medCabecera) {
		this.medCabecera = medCabecera;
	}
	public List<Tratamiento> getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(List<Tratamiento> tratamiento) {
		this.tratamiento = tratamiento;
	}
	public int getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(int formaPago) {
		this.formaPago = formaPago;
	}
	public long getNumTarjeta() {
		return numTarjeta;
	}
	public void setNumTarjeta(long numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	public int getCodSegTarjeta() {
		return codSegTarjeta;
	}
	public void setCodSegTarjeta(int codSegTarjeta) {
		this.codSegTarjeta = codSegTarjeta;
	}
	public String getFechaCadTarjeta() {
		return fechaCadTarjeta;
	}
	public void setFechaCadTarjeta(String fechaCadTarjeta) {
		this.fechaCadTarjeta = fechaCadTarjeta;
	}
	public String getCodigoAut() {
		return this.codigoAut;
	}
	String generarCodigoAut() {
		return new BigInteger(130, new SecureRandom()).toString().substring(0, 6);
	}

	public Farmacia  getFarmacia() {
		// TODO Auto-generated method stub
		return farmacia;
	}
	public void setFarmacia(Farmacia far){
		farmacia=far;
	}

	public List<Pedidos> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<Pedidos> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public void setComAutonoma(String comAutonoma) {
		this.comAutonoma = comAutonoma;
	}

	public void setCodigoAut(String codigoAut) {
		this.codigoAut = codigoAut;
	}

	public void actualizaListaPedidos(long id) {
		int i=0;
		boolean encontrado = false;
		while (i<listaPedidos.size()&&!encontrado){
			if(listaPedidos.get(i).getId()==id){
				listaPedidos.get(i).setEstadoPedido(1);
				encontrado=true;
			}
		}

	}

	public Boolean existeTratamientoEnCurso(Long medicamentoID) {
		Date fecha = new Date(System.currentTimeMillis());

		for (Tratamiento tratamiento: tratamiento) {
			if (tratamiento.getMedicamento().getId() == medicamentoID && tratamiento.getFechaFin().after(fecha)) {
				return true;
			}
		}

		return false;
	}
}
