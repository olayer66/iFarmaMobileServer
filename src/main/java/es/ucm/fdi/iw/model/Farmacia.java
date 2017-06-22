package es.ucm.fdi.iw.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name="Farmacia.countFARMA",
                query="SELECT COUNT(f) FROM Farmacia f WHERE f.estado=0"),
    @NamedQuery(name="Farmacia.findValidar",
    			query="SELECT f FROM Farmacia f WHERE f.estado=:estado"),
    @NamedQuery(name="Farmacia.updateEstado", 
    			query="UPDATE Farmacia f SET f.estado=1 WHERE f.id=:id")
})
public class Farmacia implements Serializable {
	private static final long serialVersionUID = 3729954187317206752L;
	
	//Datos de una farmacia
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "telefono", nullable = false)
	private String telefono;
	@Column(name = "direccion", nullable = false)
	private String direccion;
	@Column(name = "ciudad", nullable = false)
	private String ciudad;
	@Column(name = "cod_Postal", nullable = false)
	private String codPostal;
	@Column(name = "provincia", nullable = false)
	private String provincia;
	@Column(name = "com_Autonoma", nullable = true)
	private String comAutonoma;
	
	//Estado de la farmacia (0=validacion,1=activo,2=inactivo(borrado logico))
	@Column(name = "estado", nullable = false)
	private int estado;
	

	//getters y setters
	public long getId() {
		return id;
	}

	public void setId(long iDFarmacia) {
		id= iDFarmacia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

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

	public void setComAutonoma(String comAutonoma) {
		this.comAutonoma = comAutonoma;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}
