package ma.emsi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "voiture")
public class Voiture {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voiture_id")
	private int voitureId;
	@Column(name = "marque")
	private String marque;
	@Column(name = "model")
	private String model;
	@Column(name = "type_carburant")
	private String typeCarburant;
	@Column(name = "nb_cylindre")
	private int nbCylindre;
	@Column(name = "type_transmission")
	private String typeTransmission;
	@Column(name = "prix")
	private int prix;
	@Column(name = "res")
	private int res=0;
	
	
	
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	
	
	public int getVoitureId() {
		return voitureId;
	}
	public void setVoitureId(int voitureId) {
		this.voitureId = voitureId;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getTypeCarburant() {
		return typeCarburant;
	}
	public void setTypeCarburant(String typeCarburant) {
		this.typeCarburant = typeCarburant;
	}
	public int getNbCylindre() {
		return nbCylindre;
	}
	public void setNbCylindre(int nbCylindre) {
		this.nbCylindre = nbCylindre;
	}
	public String getTypeTransmission() {
		return typeTransmission;
	}
	public void setTypeTransmission(String typeTransmission) {
		this.typeTransmission = typeTransmission;
	}

	

}
