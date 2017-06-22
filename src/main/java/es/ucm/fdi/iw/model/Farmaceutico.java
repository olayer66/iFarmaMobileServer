package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Farmaceutico extends Usuario {
	private static final long serialVersionUID = -6791548916804839L;
	
	@Column(name = "num_col_farmaceutico", nullable = false)
	private String numColFarmaceutico;
	//Lista de farmacias que le pertenecen
	
	@OneToMany(targetEntity=Farmacia.class, cascade=CascadeType.REMOVE)
	@JoinColumn(name="duenio_id")
	private List<Farmacia> farmaciasPropias=new ArrayList<>();
	
	//getters y setters
	public String getNumColFarmaceutico() {
		return numColFarmaceutico;
	}

	public void setNumColFarmaceutico(String numColFarmaceutico) {
		this.numColFarmaceutico = numColFarmaceutico;
	}

	public List<Farmacia> getFarmaciasPropias() {
		return farmaciasPropias;
	}

	public void setFarmaciasPropias(List<Farmacia> farmaciasPropias) {
		this.farmaciasPropias = farmaciasPropias;
	}
	public List<Farmacia> getFarmaciasActivas() {
		 List<Farmacia> activas = new ArrayList<Farmacia>();
		for(Farmacia f :farmaciasPropias ){
			
			if(f.getEstado()==1){
				activas.add(f);
			}
		}
		
		
		
		return activas;
	}
	
}
