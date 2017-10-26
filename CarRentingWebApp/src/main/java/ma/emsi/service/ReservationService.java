package ma.emsi.service;

import java.util.List;

import ma.emsi.model.Reservation;

public interface ReservationService {
	
	
	public void saveReservation(Reservation reservation) ;
	
	public int saveOrUpdateReservation() ;
	
	public void updateReservation(Reservation reservation) ;
	
	public List<Reservation> listReservation() ;
	
	public List<Reservation> listReservationRespo() ;
	
	public Reservation getReservationById(int reservationId) ;
	
	public void DeleteReservation(Reservation reservation) ;
	
	public void DeleteReservation(int voitureId) ;
	
	public void DeleteReservUser(int id) ;
	
	

}
