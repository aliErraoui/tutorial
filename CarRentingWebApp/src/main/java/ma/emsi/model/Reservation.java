package ma.emsi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reservation_id")
	private int reservationId;
	@Column(name = "date")
	private String date;
	@Column(name = "promotion")
	private boolean promotion;
	@Column(name = "date_debut")
	private String dateDebut;
	@Column(name = "date_fin")
	private String dateFin;
	@Column(name = "resp")
	private int resp=0;
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="voiture_id")
	private Voiture voiture ;
	
	@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="client_id")
	//private Client client ;
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="responsable_id")
	private Responsable responsable ;
	
	
	
	
	
	
	public Responsable getResponsable() {
		return responsable;
	}
	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}
	public Reservation() {
		super();
	}
	public Reservation(Voiture voiture) {
		super();
		this.voiture = voiture;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Voiture getVoiture() {
		return voiture;
	}
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
	
	
	
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	
	public boolean isPromotion() {
		return promotion;
	}
	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	public int getResp() {
		return resp;
	}
	public void setResp(int resp) {
		this.resp = resp;
	}
	
	
	
	
	

}
